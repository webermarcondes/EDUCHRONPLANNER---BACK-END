package com.projetotcs.tcsbackend.utilitarios;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
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




        if(cell.getStringCellValue().isEmpty()) {
            cell.setCellValue(texto);
            cell.setCellStyle(style);
        }
        else {
            String conteudoC = cell.getStringCellValue();
            cell.setCellValue(conteudoC + texto);
            cell.setCellStyle(style);
        }

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
                                       Integer ultimaCelula,
                                       List<CelulaExcel> conteudos) {


        /*for (int i = primeiraCelula; i < ultimaCelula; i++) {
                preencherCelula(nomeArquivo, linha, i, conteudos.get(i));
            }*/
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

        return new XSSFColor(new java.awt.Color(red, green, blue), null);}



}



