package com.projetotcs.tcsbackend.utilitarios;

import com.projetotcs.tcsbackend.model.DiaExcecaoModel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class ManipularExcel {

    private static final int ESPACO_BARRA_SEPARADORA = 3;
    private static final SimpleDateFormat FORMATO_DATA = new SimpleDateFormat("dd/MM/yyyy");
    private static final String CODIGO_COR_DIA_INICIO = "#272b00";
    private static final String CODIGO_COR_DIA_FIM = "#Ff4040";
    private static final String CODIGO_COR_DIA_EXCECAO = "#FFFF00";

    public static XSSFWorkbook preencherCelula(XSSFWorkbook workbook,
                                               int linha,
                                               int celula,
                                               CelulaExcel conteudoCelula) {

        XSSFRichTextString texto = new XSSFRichTextString(conteudoCelula.getConteudo());


        XSSFSheet sheet = workbook.getSheetAt(0);

        XSSFRow row = sheet.getRow(linha);
        if (row == null) {
            row = sheet.createRow(linha);
        }
        XSSFCell cell = row.getCell(celula);
        if (cell == null) {
            cell = row.createCell(celula);
        }

        CellStyle style = workbook.createCellStyle();

        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);

        style.setAlignment(HorizontalAlignment.CENTER);

        if (!conteudoCelula.getCodigoHexadecimalCorFundo().isEmpty()) {

            XSSFColor color = CorHexToRgb(workbook, conteudoCelula.getCodigoHexadecimalCorFundo());

            ((XSSFCellStyle) style).setFillForegroundColor(color);
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        } else if (conteudoCelula.getCodigosHexadecimaisCorFonte().size() > 0) {


            String[] conteudos = conteudoCelula.getConteudo().split(" / ");

            int inicioString = 0;
            int fimString = 0;

            for (int i = 0; i < conteudos.length; i++) {
                XSSFFont fonte = workbook.createFont();

                String hexCorFonte = conteudoCelula.getCodigosHexadecimaisCorFonte().get(i);

                fonte.setColor(CorHexToRgb(workbook, hexCorFonte));

                fimString = (i == 0 ? conteudos[i].length() : conteudoCelula.getConteudo().length());

                texto.applyFont(inicioString, fimString, fonte);

                inicioString = fimString + ESPACO_BARRA_SEPARADORA;
            }

        }

        cell.setCellValue(texto);
        cell.setCellStyle(style);

        return workbook;
    }

    public static XSSFWorkbook preencherCelulas(XSSFWorkbook workbook,
                                                Integer linha,
                                                Integer primeiraCelula,
                                                List<CelulaExcel> conteudos) {


        int numeroCelula = primeiraCelula;
        for (CelulaExcel conteudoCelula : conteudos) {
            preencherCelula(workbook, linha, numeroCelula, conteudoCelula);

            numeroCelula += 1;
        }

        return workbook;
    }

    public static XSSFColor CorHexToRgb(XSSFWorkbook workbook, String codCorHexadecimal) {

        int red = Integer.valueOf(codCorHexadecimal.substring(1, 3), 16);
        int green = Integer.valueOf(codCorHexadecimal.substring(3, 5), 16);
        int blue = Integer.valueOf(codCorHexadecimal.substring(5, 7), 16);

        return new XSSFColor(new java.awt.Color(red, green, blue), null);

    }

    public static XSSFWorkbook preencherDias(XSSFWorkbook workbook,
                                             int linha,
                                             int primeiraCelula,
                                             List<String> meses,
                                             List<DadoCelulaDiaAula> dadosCelulasDiasAulas,
                                             List<DiaExcecaoModel> diasExcecao,
                                             String diaInicio,
                                             String diaFim) {

        int valorDiaLido = 0;
        int numeroMes = 0;
        int quantidadeDiasAulaPreenchidos = 0;

        try {

            XSSFSheet sheet = workbook.getSheetAt(0);

            XSSFRow row = sheet.getRow(linha);

            int celula = primeiraCelula;
            int diaAnteriorLido = 0;

            XSSFFont font = workbook.createFont();
            font.setBold(true);

            for (DadoCelulaDiaAula dadoCelulaDiaAula : dadosCelulasDiasAulas) {
                while (quantidadeDiasAulaPreenchidos < dadoCelulaDiaAula.getQuantidadeCelulaPreenchivel()) {

                    boolean isDiaInicio = false;
                    boolean isDiaFinal = false;
                    boolean isdiaExcecao = false;
                    XSSFCell cell = row.getCell(celula);

                    if (cell.getCellType() == CellType.BLANK) {
                        celula += 1;


                    } else if (cell.getCellType() == CellType.NUMERIC) {
                        valorDiaLido = ((Double) cell.getNumericCellValue()).intValue();

                        CellStyle style = workbook.createCellStyle();
                        XSSFColor color = null;

                        if (valorDiaLido < diaAnteriorLido) {
                            numeroMes += 1;
                        }


                        String dia = valorDiaLido + "/" + MesStringToNumeral.getNumMes(meses.get(numeroMes)) + "/2024";


                        if (FORMATO_DATA.parse(dia).before(FORMATO_DATA.parse(diaInicio))) {
                            celula += 1;
                            continue;
                        } else if (FORMATO_DATA.parse(dia).equals(FORMATO_DATA.parse(diaInicio))) {
                            quantidadeDiasAulaPreenchidos += 1;
                            color = CorHexToRgb(workbook, CODIGO_COR_DIA_INICIO);
                            isDiaInicio = true;

                            style.setBorderTop(BorderStyle.THICK);
                            style.setBorderBottom(BorderStyle.THICK);
                            style.setBorderLeft(BorderStyle.THICK);
                            style.setBorderRight(BorderStyle.THICK);

                        }


                        if (FORMATO_DATA.parse(dia).after(FORMATO_DATA.parse(diaFim))) {
                            break;
                        } else if (FORMATO_DATA.parse(dia).equals(FORMATO_DATA.parse(diaFim))) {
                            quantidadeDiasAulaPreenchidos += 1;
                            color = CorHexToRgb(workbook, CODIGO_COR_DIA_FIM);
                            isDiaFinal = true;

                            style.setBorderTop(BorderStyle.THICK);
                            style.setBorderBottom(BorderStyle.THICK);
                            style.setBorderLeft(BorderStyle.THICK);
                            style.setBorderRight(BorderStyle.THICK);

                        }

                        for (DiaExcecaoModel diaExcecao : diasExcecao) {

                            if (FORMATO_DATA.parse(diaExcecao.getData()).equals(FORMATO_DATA.parse(dia))) {
                                color = CorHexToRgb(workbook, CODIGO_COR_DIA_EXCECAO);
                                isdiaExcecao = true;

                                style.setBorderTop(BorderStyle.THICK);
                                style.setBorderBottom(BorderStyle.THICK);
                                style.setBorderLeft(BorderStyle.THICK);
                                style.setBorderRight(BorderStyle.THICK);
                                break;
                            }
                        }


                        if (!isdiaExcecao && !isDiaInicio && !isDiaFinal) {
                            quantidadeDiasAulaPreenchidos += 1;

                            style.setBorderTop(BorderStyle.THIN);
                            style.setBorderBottom(BorderStyle.THIN);
                            style.setBorderLeft(BorderStyle.THIN);
                            style.setBorderRight(BorderStyle.THIN);

                            color = CorHexToRgb(workbook, dadoCelulaDiaAula.getCodigoHexadecimalCorFundo());

                        }
                        celula += 1;

                        ((XSSFCellStyle) style).setFillForegroundColor(color);
                        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                        style.setAlignment(HorizontalAlignment.CENTER);
                        cell.setCellStyle(style);

                    }

                    diaAnteriorLido = valorDiaLido;

                }
                quantidadeDiasAulaPreenchidos = 0;
            }


        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return workbook;
    }

    public static List<String> lerCelulasMescladas(XSSFWorkbook workbook,
                                                   int linha,
                                                   int celulaInicio,
                                                   int quantidadeCelulasALer) {


        List<String> conteudoCelulas = new ArrayList<>();
        int celula = celulaInicio;
        int quantidadeCelulaslidas = 0;
        XSSFSheet sheet = workbook.getSheetAt(0);

        while (true) {
            for (int i = 0; i < sheet.getNumMergedRegions(); i++) {

                CellRangeAddress region = sheet.getMergedRegion(i);

                if (region.isInRange(linha, celula)) {
                    conteudoCelulas.add(sheet.getRow(region.getFirstRow()).getCell(region.getFirstColumn()).getStringCellValue());
                    quantidadeCelulaslidas += 1;
                    celula += region.getNumberOfCells();

                    break;
                }

            }

            if (quantidadeCelulaslidas == quantidadeCelulasALer) {
                break;
            }
        }


        return conteudoCelulas;

    }
}

