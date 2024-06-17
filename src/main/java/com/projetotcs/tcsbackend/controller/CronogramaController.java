package com.projetotcs.tcsbackend.controller;


import com.projetotcs.tcsbackend.utilitarios.CelulaExcel;
import com.projetotcs.tcsbackend.utilitarios.ManipularExcel;
import com.projetotcs.tcsbackend.model.*;
import com.projetotcs.tcsbackend.services.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/cronograma")
public class CronogramaController {

    /*
    Montar estrutura

        Get no Curso  - ADS  OK
        Get de todas as fases do curso - fases, de id 1 a 5 OK
        Get na agenda professor de acordo com o ID da fase do curso - OK;

        Retornar a o conteúdo da estrutura que será adicionado ao EXCEL


        Dia com uma disciplina:
        Disciplina(ch)    | Nome Professor  | CorDC1  | CorDC2 == ### | Dia Da Semana |

        Dia da semana com duas disciplinas:

        Disciplina1(ch) / Disciplina2(ch)    | Nome Prof1 / Nome Prof2  | CorDC1  | CorDC2| Dia Da Semana |
    */

    @Autowired
    AgendaProfessorService agendaProfessorService;

    @Autowired
    CursoService cursoService;

    @Autowired
    FaseService faseService;

    @Autowired
    DiaDaSemanaService diaDaSemanaService;

    @GetMapping(value="/gerarcronograma/")
    public String gerarCronograma(@RequestBody String nomeCurso) {

        JSONObject jsonNomeCurso = new JSONObject(nomeCurso);
        //Pega o curso pelo nome do Curso
        CursoModel curso = cursoService.findByNome(jsonNomeCurso.getString("nomeCurso"));

        //Pega as fases do Curso selecionado
        List<FaseModel> fases = faseService.getFasesByCurso(curso);

        int linha = 9;
        //Iteração de fase a fase do curso para pegar os registros de agenda do professor
        //de cada fase, e adicionar ao mapper da agenda de professores separando por dia da semana
        for (FaseModel fase : fases) {

            //Mapper que ira organizar os registros da agenda de professor da fase por
            // dia da Semana separadamente
            Map<String, List<AgendaProfessorModel>> agProfPorDiaSemana = new LinkedHashMap<>();

            //Criação das Keys e listas para a agProfPorDiaSemana
            for (DiaDaSemanaModel diaDaSemana : diaDaSemanaService.findAll()) {

                agProfPorDiaSemana.put(diaDaSemana.getDescricao(), new ArrayList<AgendaProfessorModel>());
            }



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
                } else {
                    agProfPorDiaSemana.get("SÁBADO").add(agProf);
                }
            }


                for (String key : agProfPorDiaSemana.keySet()) {
                    List<CelulaExcel> conteudos = new ArrayList<>();
                    List<AgendaProfessorModel> agendaProfessores = agProfPorDiaSemana.get(key);

                    if (agendaProfessores.size() == 0) {
                        break;
                    }

                    else if (agendaProfessores.size() == 1) {
                        AgendaProfessorModel agProf = agendaProfessores.get(0);


                        CelulaExcel disciplinaHoras = new CelulaExcel(agProf.getDisciplina().getNome() + " (" +
                                agProf.getDisciplina().getCargaHoraria() + "hr)",
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

                    } else if (agendaProfessores.size() > 1) {


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
                        }

                        conteudos.add(disciplinasHoras);
                        conteudos.add(nomeProfessores);
                        conteudos.add(corDC1);
                        conteudos.add(corDC2);

                    }

                    String nomeArquivo = "C:/Users/weber/Downloads/modelo_cronograma1.xlsx";
                    ManipularExcel.preencherCelulas(nomeArquivo, linha, 2, 5, conteudos);


                    linha += 1;
                }

                linha += 3;

            }


        return "Teste";
    }


}
