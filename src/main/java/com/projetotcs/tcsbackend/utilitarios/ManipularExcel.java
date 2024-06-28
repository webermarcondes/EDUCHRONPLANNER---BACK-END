package com.projetotcs.tcsbackend.utilitarios;

import com.projetotcs.tcsbackend.model.DiaExcecaoModel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ManipularExcel {


    public static String ler(String nomeArquivo, int linha, int celula) {

        String conteudoLido = "";
        try {
            InputStream inputStream = new FileInputStream(nomeArquivo);
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

            //Pega a primeira planilha do arquivo
            XSSFSheet sheet = workbook.getSheetAt(0);

            XSSFRow row = sheet.getRow(linha);

            XSSFCell cell = row.getCell(celula);

            if(cell != null) {
                conteudoLido += cell.toString();
            }


        }
        catch(IOException e) {
            return "Arquivo não encontrado";
        }

        return conteudoLido;

}


    public static String preencherCelula(String nomeArquivo,
                                int linha,
                                int celula,
                                CelulaExcel conteudoCelula) {

        XSSFRichTextString texto = new XSSFRichTextString(conteudoCelula.getConteudo());

        try {
            FileInputStream fis = new FileInputStream(nomeArquivo);

            XSSFWorkbook workbook = new XSSFWorkbook(fis);

            //Pega a primeira planilha do arquivo
            XSSFSheet sheet = workbook.getSheetAt(0);

            XSSFRow row = sheet.getRow(linha);
            if(row == null) {
                row = sheet.createRow(linha);
            }
            XSSFCell cell = row.getCell(celula);
            if(cell == null) {
                cell = row.createCell(celula);
            }

            CellStyle style = workbook.createCellStyle();

            style.setBorderTop(BorderStyle.THIN);
            style.setBorderBottom(BorderStyle.THIN);
            style.setBorderLeft(BorderStyle.THIN);
            style.setBorderRight(BorderStyle.THIN);

            style.setAlignment(HorizontalAlignment.CENTER);
            /*if(conteudoCelula.getAlinhamento().equals("CENTRALIZADO")) {
                style.setAlignment(HorizontalAlignment.CENTER);
            }
            else if(conteudoCelula.getAlinhamento().equals("ESQUERDA")) {
                style.setAlignment(HorizontalAlignment.LEFT);
            }*/


            if (!conteudoCelula.getHexCorFundo().isEmpty()) {

                XSSFColor color = CorHexToRgb(workbook, conteudoCelula.getHexCorFundo());

                ((XSSFCellStyle) style).setFillForegroundColor(color);
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            }

            else if (conteudoCelula.getHexsCoresFonte().size() > 0) {


                String[] conteudos = conteudoCelula.getConteudo().split(" / ");

                int espacoBarraSeparadora = 3;
                int inicioStr = 0;
                int fimStr = 0;

                for(int i = 0; i < conteudos.length; i++) {
                    XSSFFont fonte = workbook.createFont();

                    String hexCorFonte = conteudoCelula.getHexsCoresFonte().get(i);

                    fonte.setColor(CorHexToRgb(workbook, hexCorFonte));


                    fimStr = (i==0 ? conteudos[i].length() : conteudoCelula.getConteudo().length());

                    texto.applyFont(inicioStr, fimStr, fonte);

                    inicioStr = fimStr + espacoBarraSeparadora;
                }

            }

        cell.setCellValue(texto);
        cell.setCellStyle(style);

        fis.close();
        try (FileOutputStream fos = new FileOutputStream(nomeArquivo)) {
            workbook.write(fos);
        }


    }

    catch (FileNotFoundException e) {
        throw new RuntimeException(e);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    return "Conteudo escrito com sucesso";
}

public static String preencherCelulas(String nomeArquivo,
                                       Integer linha,
                                       Integer primeiraCelula,
                                       List<CelulaExcel> conteudos) {


        int numCelula = primeiraCelula;
        for(CelulaExcel conteudoCelula: conteudos) {
            preencherCelula(nomeArquivo, linha, numCelula, conteudoCelula);

            numCelula += 1;
        }

    return "Células preenchidas";
}

    public static XSSFColor CorHexToRgb(XSSFWorkbook workbook, String codCorHexadecimal) {

        int red = Integer.valueOf(codCorHexadecimal.substring(1, 3), 16);
        int green = Integer.valueOf(codCorHexadecimal.substring(3, 5), 16);
        int blue = Integer.valueOf(codCorHexadecimal.substring(5, 7), 16);

        return new XSSFColor(new java.awt.Color(red, green, blue), null);

    }


    public static void preencherDias(String nomeArquivo,
                                             int linha,
                                             int primeiraCelula,
                                             List<String> meses,
                                             List<DadosCelulaDiaAula> dadosCelulasDiasAulas,
                                             List<DiaExcecaoModel> diasExcecao,
                                             String diaInicio,
                                             String diaFim
                                             ) {


        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        int valorDiaLido = 0;
        int numMes = 0;
        int qtdeDiasAulaPreenchidos = 0;



        try {
            FileInputStream fis = new FileInputStream(nomeArquivo);

            XSSFWorkbook workbook = new XSSFWorkbook(fis);

            //Pega a primeira planilha do arquivo
            XSSFSheet sheet = workbook.getSheetAt(0);

            XSSFRow row = sheet.getRow(linha);

            int celula = primeiraCelula;
            int diaAnteriorLido = 0;

            XSSFFont font = workbook.createFont();
            font.setBold(true);

            for(DadosCelulaDiaAula dadoCelulaDiaAula:  dadosCelulasDiasAulas) {
                while (qtdeDiasAulaPreenchidos < dadoCelulaDiaAula.getQtdePreenchivel()) {

                    boolean isDiaInicio = false;
                    boolean isDiaFinal = false;
                    boolean IsdiaExcecao = false;
                    XSSFCell cell = row.getCell(celula);



                    if (cell.getCellType() == CellType.BLANK) {
                        celula += 1;


                    } else if (cell.getCellType() == CellType.NUMERIC) {
                        valorDiaLido = ((Double) cell.getNumericCellValue()).intValue();


                        CellStyle style = workbook.createCellStyle();
                        XSSFColor color = null;

                        if (valorDiaLido < diaAnteriorLido) {
                            numMes += 1;}

                        String dia = valorDiaLido + "/" + MesStringToNumeral.getNumMes(meses.get(numMes)) + "/2024";


                        if(formato.parse(dia).before(formato.parse(diaInicio))) {
                            celula += 1;
                            continue;
                        }

                        else if(formato.parse(dia).equals(formato.parse(diaInicio))) {
                            qtdeDiasAulaPreenchidos += 1;
                            color = CorHexToRgb(workbook, "#272b00");
                            isDiaInicio = true;

                            style.setBorderTop(BorderStyle.THICK);
                            style.setBorderBottom(BorderStyle.THICK);
                            style.setBorderLeft(BorderStyle.THICK);
                            style.setBorderRight(BorderStyle.THICK);

                            //style.setFont(font);
                        }

                        else {

                            if (formato.parse(dia).after(formato.parse(diaFim))) {
                                return;
                            } else if (formato.parse(dia).equals(formato.parse(diaFim))) {
                                qtdeDiasAulaPreenchidos += 1;
                                color = CorHexToRgb(workbook, "#Ff4040");
                                isDiaFinal = true;

                                style.setBorderTop(BorderStyle.THICK);
                                style.setBorderBottom(BorderStyle.THICK);
                                style.setBorderLeft(BorderStyle.THICK);
                                style.setBorderRight(BorderStyle.THICK);

                                //style.setFont(font);
                            }
                        }


                        for(DiaExcecaoModel diaExcecao: diasExcecao) {

                            if(formato.parse(diaExcecao.getData()).equals(formato.parse(dia))) {
                                color = CorHexToRgb(workbook,"#FFFF00");
                                diasExcecao.remove(diaExcecao);
                                IsdiaExcecao = true;
                                //style.setFont(font);

                                style.setBorderTop(BorderStyle.THICK);
                                style.setBorderBottom(BorderStyle.THICK);
                                style.setBorderLeft(BorderStyle.THICK);
                                style.setBorderRight(BorderStyle.THICK);
                                break;
                            }
                        }


                        if(!IsdiaExcecao && !isDiaInicio && !isDiaFinal) {
                            qtdeDiasAulaPreenchidos += 1;

                            style.setBorderTop(BorderStyle.THIN);
                            style.setBorderBottom(BorderStyle.THIN);
                            style.setBorderLeft(BorderStyle.THIN);
                            style.setBorderRight(BorderStyle.THIN);

                            color = CorHexToRgb(workbook, dadoCelulaDiaAula.getCodHexCorFundo());


                        }
                        celula += 1;



                        ((XSSFCellStyle) style).setFillForegroundColor(color);
                        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);



                        style.setAlignment(HorizontalAlignment.CENTER);


                        cell.setCellStyle(style);

                        fis.close();
                        try (FileOutputStream fos = new FileOutputStream(nomeArquivo)) {
                            workbook.write(fos);
                        }
                    }

                    diaAnteriorLido = valorDiaLido;

                }
                qtdeDiasAulaPreenchidos = 0;
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
        public static List<String> lerCelulasMescladas (String nomeArquivo,
                                                        int linha,
                                                        int celulaInicio,
                                                        int qtdeCelulasALer){


            List<String> conteudoCelulas = new ArrayList<>();
            int celula = celulaInicio;
            int qtdeCelulaslidas = 0;

            try {
                InputStream inputStream = new FileInputStream(nomeArquivo);
                XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

                XSSFSheet sheet = workbook.getSheetAt(0);

                while(true) {
                for (int i = 0; i < sheet.getNumMergedRegions(); i++) {

                    CellRangeAddress region = sheet.getMergedRegion(i);

                    if (region.isInRange(linha, celula)) {
                        conteudoCelulas.add(sheet.getRow(region.getFirstRow()).getCell(region.getFirstColumn()).getStringCellValue()); //Conteudo da celula mesclada
                        celula += region.getNumberOfCells(); //Incrementa o valor de celula, com a quantidade das celulas que formam a celula mesclada
                        qtdeCelulaslidas += 1;

                        break;
                    }}

                if(qtdeCelulaslidas == qtdeCelulasALer) {
                    break;
                }
            }


            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return conteudoCelulas;

        }


    }

