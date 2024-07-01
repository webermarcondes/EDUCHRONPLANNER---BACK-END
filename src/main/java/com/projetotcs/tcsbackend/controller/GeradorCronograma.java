package com.projetotcs.tcsbackend.controller;

import com.projetotcs.tcsbackend.requests.reqGerarCronograma;
import com.projetotcs.tcsbackend.utilitarios.CelulaExcel;
import com.projetotcs.tcsbackend.utilitarios.DadosCelulaDiaAula;
import com.projetotcs.tcsbackend.utilitarios.ManipularExcel;
import com.projetotcs.tcsbackend.model.*;
import com.projetotcs.tcsbackend.services.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("/api/cronograma")
public class GeradorCronograma {

    @Autowired
    AgendaProfessorService agendaProfessorService;

    @Autowired
    CursoService cursoService;

    @Autowired
    FaseService faseService;

    @Autowired
    DiaDaSemanaService diaDaSemanaService;

    @Autowired
    DiaExcecaoService diaExcecaoService;

    @PostMapping(value="/gerarcronograma/")
    public ResponseEntity<byte[]> gerarCronograma(@RequestBody reqGerarCronograma reqCronograma) throws IOException {


        String erros = "";

        String nomeArquivo = "src/modelo_cronograma/modelo_cronograma.xlsx";

        FileInputStream fis = new FileInputStream(nomeArquivo);

        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        workbook = ManipularExcel.preencherCelula(workbook,
                                        6,
                                        2,
                                        new CelulaExcel("Início das aulas: " + reqCronograma.getDataInicio(), "#Add8e6", "ESQUERDA"));

        workbook = ManipularExcel.preencherCelula(workbook,
                7,
                2,
                new CelulaExcel("Termino das aulas:" + reqCronograma.getDataFim(), "#Add8e6", "ESQUERDA"));



        CursoModel curso = cursoService.findByNome(reqCronograma.getNomeCurso());


        List<FaseModel> fases = faseService.getFasesByCurso(curso);

        int linha = 9;

        Map<String, Long> disciplinasRepetidas = new LinkedHashMap<>();
        List<String> meses = ManipularExcel.lerCelulasMescladas(nomeArquivo, 8, 7, 6);
        for (FaseModel fase : fases) {



            Map<String, List<AgendaProfessorModel>> agProfPorDiaSemana = new LinkedHashMap<>();
            for (DiaDaSemanaModel diaDaSemana : diaDaSemanaService.findAll()) {

                agProfPorDiaSemana.put(diaDaSemana.getDescricao(), new ArrayList<AgendaProfessorModel>());
            }


            Long qtdeRepeticoesDisciplina;
            for (AgendaProfessorModel agProf : agendaProfessorService.findByDisciplinaFase(fase)) {

                String diaDaSemanaDescri = agProf.getDiaDaSemana().getDescricao();


                if (diaDaSemanaDescri.equals("SEGUNDA-FEIRA")) {
                    agProfPorDiaSemana.get("SEGUNDA-FEIRA").add(agProf);


                } else if (diaDaSemanaDescri.equals("TERÇA-FEIRA")) {
                    agProfPorDiaSemana.get("TERÇA-FEIRA").add(agProf);
                } else if (diaDaSemanaDescri.equals("QUARTA-FEIRA")) {
                    agProfPorDiaSemana.get("QUARTA-FEIRA").add(agProf);
                } else if (diaDaSemanaDescri.equals("QUINTA-FEIRA")) {
                    agProfPorDiaSemana.get("QUINTA-FEIRA").add(agProf);
                } else if (diaDaSemanaDescri.equals("SEXTA-FEIRA")) {
                    agProfPorDiaSemana.get("SEXTA-FEIRA").add(agProf);
                }

                qtdeRepeticoesDisciplina = agendaProfessorService.countByDisciplinaId(agProf.getDisciplina().getId());

                if(qtdeRepeticoesDisciplina > 1 && !disciplinasRepetidas.containsKey(agProf.getDisciplina().getNome())) {
                    disciplinasRepetidas.put(agProf.getDisciplina().getNome(), qtdeRepeticoesDisciplina);
                }

            }

            for (String key : agProfPorDiaSemana.keySet()) {
                if(agProfPorDiaSemana.get(key).size() == 0) {
                    erros += "cronograma da "
                             + fase.getNumero() +
                            "ª fase não gerado devido a falta da disciplina para os dias da semana";
                    break;
                }
            }

            if(!erros.isEmpty()) {
                linha += 8;
                continue;
            }


            for (String key : agProfPorDiaSemana.keySet()) {
                    List<DadosCelulaDiaAula> dadosCelulaDiaAulas = new ArrayList<>();
                    List<CelulaExcel> conteudos = new ArrayList<>();
                    List<AgendaProfessorModel> agendaProfessores = agProfPorDiaSemana.get(key);


                    if (agendaProfessores.size() == 1) {
                        AgendaProfessorModel agProf = agendaProfessores.get(0);

                        CelulaExcel disciplinaHoras = new CelulaExcel(agProf.getDisciplina().getNome() +
                                                                    " (" + agProf.getDisciplina().getCargaHoraria() + "hr)",
                                                                    agProf.getDisciplina().getCodigoCor());


                        CelulaExcel nomeProfessores = new CelulaExcel(agProf.getProfessor().getNomeCompleto(),
                                agProf.getDisciplina().getCodigoCor());

                        CelulaExcel corDC1 = new CelulaExcel("",
                                agProf.getDisciplina().getCodigoCor());

                        CelulaExcel corDC2 = new CelulaExcel("###");

                        conteudos.add(disciplinaHoras);
                        conteudos.add(nomeProfessores);
                        conteudos.add(corDC1);
                        conteudos.add(corDC2);

                        int qtdePreenchivel = 0;
                        if(disciplinasRepetidas.containsKey(agProf.getDisciplina().getNome())) {

                            int qtdeRepeticoes = disciplinasRepetidas.get(agProf.getDisciplina().getNome()).intValue();
                            qtdePreenchivel = ((agProf.getDisciplina().getCargaHoraria() / 4) / qtdeRepeticoes);
                        }
                        else {
                            qtdePreenchivel = agProf.getDisciplina().getCargaHoraria() / 4;
                        }
                        dadosCelulaDiaAulas.add(new DadosCelulaDiaAula(qtdePreenchivel, agProf.getDisciplina().getCodigoCor()));

                        workbook =ManipularExcel.preencherCelulas(workbook, linha, 2, conteudos);
                        workbook = ManipularExcel.preencherDias(workbook, linha, 7, meses, dadosCelulaDiaAulas, diaExcecaoService.findAll(), reqCronograma.getDataInicio(), reqCronograma.getDataFim());

                        linha += 1;

                    }
                    else if (agendaProfessores.size() > 1) {


                        CelulaExcel disciplinasHoras = new CelulaExcel("");

                        CelulaExcel nomeProfessores = new CelulaExcel("");

                        CelulaExcel corDC1 = new CelulaExcel("");
                        CelulaExcel corDC2 = new CelulaExcel("");

                        for (int i = 0; i < agendaProfessores.size(); i++) {
                            AgendaProfessorModel agProf = agendaProfessores.get(i);

                            disciplinasHoras.setConteudo(
                                    disciplinasHoras.getConteudo() +
                                            agProf.getDisciplina().getNome() + " ("
                                            + agProf.getDisciplina().getCargaHoraria() + "hr)"
                                            + (i == 0 ? " / " : " ")
                            );


                            nomeProfessores.setConteudo(
                                    nomeProfessores.getConteudo() +
                                            agProf.getProfessor().getNomeCompleto() + (i == 0 ? " / " : "")
                            );


                            if (i == 0) {

                                corDC1 = new CelulaExcel("", agProf.getDisciplina().getCodigoCor());

                                disciplinasHoras.setHexCorFonte(agProf.getDisciplina().getCodigoCor());
                                nomeProfessores.setHexCorFonte(agProf.getDisciplina().getCodigoCor());
                            } else {

                                corDC2 = new CelulaExcel("", agProf.getDisciplina().getCodigoCor());

                                disciplinasHoras.setHexCorFonte(agProf.getDisciplina().getCodigoCor());
                                nomeProfessores.setHexCorFonte(agProf.getDisciplina().getCodigoCor());
                            }


                            int qtdePreenchivel = 0;
                            if(disciplinasRepetidas.containsKey(agProf.getDisciplina().getNome())) {

                                int qtdeRepeticoes = disciplinasRepetidas.get(agProf.getDisciplina().getNome()).intValue();
                                qtdePreenchivel = ((agProf.getDisciplina().getCargaHoraria() / 4) / qtdeRepeticoes);
                            }
                            else {
                                qtdePreenchivel = agProf.getDisciplina().getCargaHoraria() / 4;
                            }
                            dadosCelulaDiaAulas.add(new DadosCelulaDiaAula(qtdePreenchivel, agProf.getDisciplina().getCodigoCor()));
                        }

                        conteudos.add(disciplinasHoras);
                        conteudos.add(nomeProfessores);
                        conteudos.add(corDC1);
                        conteudos.add(corDC2);

                        workbook = ManipularExcel.preencherCelulas(workbook, linha, 2, conteudos);
                        workbook = ManipularExcel.preencherDias(workbook, linha, 7, meses, dadosCelulaDiaAulas, diaExcecaoService.findAll(), reqCronograma.getDataInicio(), reqCronograma.getDataFim());


                        linha += 1;
                    }

                }

                linha += 3;
                erros += "";

            }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        byte[] excelbytes = outputStream.toByteArray();
        String retornoErros = "";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);


        ZipEntry excelEntry = new ZipEntry("cronograma.xlsx");
        zos.putNextEntry(excelEntry);
        zos.write(excelbytes);
        zos.closeEntry();



        if(erros.isEmpty()) {
            retornoErros = "Sem erro. Cronograma gerado com sucesso";
        }
        else {
            retornoErros = erros;
        }

        ZipEntry textEntry = new ZipEntry("mensagem.txt");
        zos.putNextEntry(textEntry);
        zos.write(retornoErros.getBytes());
        zos.closeEntry();

        zos.close();

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=cronograma.zip");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/zip");

        return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);
    }


}
