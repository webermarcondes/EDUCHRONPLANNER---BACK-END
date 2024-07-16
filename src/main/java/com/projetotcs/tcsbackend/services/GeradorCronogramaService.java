package com.projetotcs.tcsbackend.services;

import com.projetotcs.tcsbackend.model.AgendaProfessorModel;
import com.projetotcs.tcsbackend.model.CursoModel;
import com.projetotcs.tcsbackend.model.DiaDaSemanaModel;
import com.projetotcs.tcsbackend.model.FaseModel;
import com.projetotcs.tcsbackend.requests.RequestGerarCronograma;
import com.projetotcs.tcsbackend.utilitarios.CelulaExcel;
import com.projetotcs.tcsbackend.utilitarios.DadoCelulaDiaAula;
import com.projetotcs.tcsbackend.utilitarios.ManipularExcel;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class GeradorCronogramaService {

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

    static final String NOME_ARQUIVO = "src/modelo_cronograma/modelo_cronograma.xlsx";

    public ResponseEntity<byte[]> gerarCronograma(RequestGerarCronograma requestGerarCronograma) throws IOException {

        String erros = "";

        FileInputStream fileInputStream = new FileInputStream(NOME_ARQUIVO);

        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

        workbook = ManipularExcel.preencherCelula(workbook,
                6,
                2,
                new CelulaExcel("Início das aulas: " + requestGerarCronograma.getDataInicio(), "#Add8e6", "ESQUERDA"));

        workbook = ManipularExcel.preencherCelula(workbook,
                7,
                2,
                new CelulaExcel("Termino das aulas:" + requestGerarCronograma.getDataFim(), "#Add8e6", "ESQUERDA"));

        CursoModel curso = cursoService.findByNome(requestGerarCronograma.getNomeCurso());

        List<FaseModel> fases = faseService.getFasesByCurso(curso);

        int linha = 9;

        Map<String, Long> disciplinasRepetidas = new LinkedHashMap<>();
        List<String> meses = ManipularExcel.lerCelulasMescladas(workbook, 8, 7, 6);
        for (FaseModel fase : fases) {
            String erroNovo = "";

            Map<String, List<AgendaProfessorModel>> agendaProfessorPorDiaDaSemana = new LinkedHashMap<>();
            for (DiaDaSemanaModel diaDaSemana : diaDaSemanaService.findAll()) {

                agendaProfessorPorDiaDaSemana.put(diaDaSemana.getDescricao(), new ArrayList<AgendaProfessorModel>());
            }


            Long quantidadeRepeticoesDisciplina;
            for (AgendaProfessorModel agendaProfessor : agendaProfessorService.findByDisciplinaFase(fase)) {

                String diaDaSemanaDescri = agendaProfessor.getDiaDaSemana().getDescricao();


                if (diaDaSemanaDescri.equals("SEGUNDA-FEIRA")) {
                    agendaProfessorPorDiaDaSemana.get("SEGUNDA-FEIRA").add(agendaProfessor);


                } else if (diaDaSemanaDescri.equals("TERÇA-FEIRA")) {
                    agendaProfessorPorDiaDaSemana.get("TERÇA-FEIRA").add(agendaProfessor);
                } else if (diaDaSemanaDescri.equals("QUARTA-FEIRA")) {
                    agendaProfessorPorDiaDaSemana.get("QUARTA-FEIRA").add(agendaProfessor);
                } else if (diaDaSemanaDescri.equals("QUINTA-FEIRA")) {
                    agendaProfessorPorDiaDaSemana.get("QUINTA-FEIRA").add(agendaProfessor);
                } else if (diaDaSemanaDescri.equals("SEXTA-FEIRA")) {
                    agendaProfessorPorDiaDaSemana.get("SEXTA-FEIRA").add(agendaProfessor);
                }

                quantidadeRepeticoesDisciplina = agendaProfessorService.countByDisciplinaId(agendaProfessor.getDisciplina().getId());

                if (quantidadeRepeticoesDisciplina > 1 && !disciplinasRepetidas.containsKey(agendaProfessor.getDisciplina().getNome())) {
                    disciplinasRepetidas.put(agendaProfessor.getDisciplina().getNome(), quantidadeRepeticoesDisciplina);
                }

            }

            for (String key : agendaProfessorPorDiaDaSemana.keySet()) {
                if (agendaProfessorPorDiaDaSemana.get(key).size() == 0) {
                    erroNovo += "cronograma da "
                            + fase.getNumero() +
                            "ª fase não gerado devido a falta da disciplina para os dias da semana";
                    break;
                }
            }

            if (!erroNovo.isEmpty()) {
                erros += erroNovo + "\n\n";
                linha += 8;
                continue;
            }


            for (String key : agendaProfessorPorDiaDaSemana.keySet()) {
                List<DadoCelulaDiaAula> dadoCelulaDiaAulas = new ArrayList<>();
                List<CelulaExcel> conteudos = new ArrayList<>();
                List<AgendaProfessorModel> agendaProfessores = agendaProfessorPorDiaDaSemana.get(key);

                CelulaExcel disciplinasHoras = new CelulaExcel("");
                CelulaExcel nomeProfessores = new CelulaExcel("");
                CelulaExcel corDisciplina1 = new CelulaExcel("");
                CelulaExcel corDisciplina2 = new CelulaExcel("");

                if (agendaProfessores.size() == 1) {
                    AgendaProfessorModel agendaProfessor = agendaProfessores.get(0);

                    disciplinasHoras.setConteudo(agendaProfessor.getDisciplina().getNome() +
                            " (" + agendaProfessor.getDisciplina().getCargaHoraria() + "hr)");

                    disciplinasHoras.setCodigoHexadecimalCorFundo(agendaProfessor.getDisciplina().getCodigoCor());

                    nomeProfessores.setConteudo(agendaProfessor.getProfessor().getNomeCompleto());

                    nomeProfessores.setCodigoHexadecimalCorFundo(agendaProfessor.getDisciplina().getCodigoCor());

                    corDisciplina1.setCodigoHexadecimalCorFundo(agendaProfessor.getDisciplina().getCodigoCor());

                    corDisciplina2.setConteudo("###");

                    int quantidadePreenchivel = 0;
                    if (disciplinasRepetidas.containsKey(agendaProfessor.getDisciplina().getNome())) {

                        int qtdeRepeticoes = disciplinasRepetidas.get(agendaProfessor.getDisciplina().getNome()).intValue();
                        quantidadePreenchivel = ((agendaProfessor.getDisciplina().getCargaHoraria() / 4) / qtdeRepeticoes);
                    } else {
                        quantidadePreenchivel = agendaProfessor.getDisciplina().getCargaHoraria() / 4;
                    }
                    dadoCelulaDiaAulas.add(new DadoCelulaDiaAula(quantidadePreenchivel, agendaProfessor.getDisciplina().getCodigoCor()));

                } else if (agendaProfessores.size() > 1) {

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

                            corDisciplina1.setCodigoHexadecimalCorFundo(agProf.getDisciplina().getCodigoCor());

                            disciplinasHoras.setHexCorFonte(agProf.getDisciplina().getCodigoCor());
                            nomeProfessores.setHexCorFonte(agProf.getDisciplina().getCodigoCor());
                        } else {

                            corDisciplina2.setCodigoHexadecimalCorFundo(agProf.getDisciplina().getCodigoCor());

                            disciplinasHoras.setHexCorFonte(agProf.getDisciplina().getCodigoCor());
                            nomeProfessores.setHexCorFonte(agProf.getDisciplina().getCodigoCor());
                        }


                        int quantidadePreenchivel = 0;
                        if (disciplinasRepetidas.containsKey(agProf.getDisciplina().getNome())) {

                            int qtdeRepeticoes = disciplinasRepetidas.get(agProf.getDisciplina().getNome()).intValue();
                            quantidadePreenchivel = ((agProf.getDisciplina().getCargaHoraria() / 4) / qtdeRepeticoes);
                        } else {
                            quantidadePreenchivel = agProf.getDisciplina().getCargaHoraria() / 4;
                        }
                        dadoCelulaDiaAulas.add(new DadoCelulaDiaAula(quantidadePreenchivel, agProf.getDisciplina().getCodigoCor()));
                    }

                }

                conteudos.add(disciplinasHoras);
                conteudos.add(nomeProfessores);
                conteudos.add(corDisciplina1);
                conteudos.add(corDisciplina2);

                workbook = ManipularExcel.preencherCelulas(workbook, linha, 2, conteudos);
                workbook = ManipularExcel.preencherDias(workbook, linha, 7, meses, dadoCelulaDiaAulas, diaExcecaoService.findAll(), requestGerarCronograma.getDataInicio(), requestGerarCronograma.getDataFim());

                linha += 1;
            }

            linha += 3;


        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        byte[] excelbytes = outputStream.toByteArray();
        String retornoErros = "";
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);


        ZipEntry excelEntry = new ZipEntry("cronograma.xlsx");
        zipOutputStream.putNextEntry(excelEntry);
        zipOutputStream.write(excelbytes);
        zipOutputStream.closeEntry();


        if (erros.isEmpty()) {
            retornoErros = "Sem erro. Cronograma gerado com sucesso";
        } else {
            retornoErros = erros;
        }

        ZipEntry textEntry = new ZipEntry("mensagem.txt");
        zipOutputStream.putNextEntry(textEntry);
        zipOutputStream.write(retornoErros.getBytes());
        zipOutputStream.closeEntry();
        zipOutputStream.close();


        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=cronograma.zip");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/zip");

        return new ResponseEntity<>(byteArrayOutputStream.toByteArray(), headers, HttpStatus.OK);
    }
}
