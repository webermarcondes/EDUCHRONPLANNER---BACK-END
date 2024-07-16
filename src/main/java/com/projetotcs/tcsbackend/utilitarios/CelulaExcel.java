package com.projetotcs.tcsbackend.utilitarios;

import java.util.ArrayList;
import java.util.List;

public class CelulaExcel {

    private String conteudo;
    private String codigoHexadecimalCorFundo;
    private List<String> codigosHexadecimaisCorFonte;
    private String alinhamento;

    public CelulaExcel(String conteudo, String codigoHexadecimalCorFundo) {
        this.conteudo = conteudo;
        this.codigoHexadecimalCorFundo = codigoHexadecimalCorFundo;
        this.codigosHexadecimaisCorFonte = new ArrayList<>();
    }

    public CelulaExcel(String conteudo) {
        this.conteudo = conteudo;
        this.codigosHexadecimaisCorFonte = new ArrayList<String>();
        this.codigoHexadecimalCorFundo = "";
    }

    public CelulaExcel(String conteudo, String codigoHexadecimalCorFundo, String alinhamento) {
        this.conteudo = conteudo;
        this.codigoHexadecimalCorFundo = codigoHexadecimalCorFundo;
        this.alinhamento = alinhamento;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getCodigoHexadecimalCorFundo() {
        return codigoHexadecimalCorFundo;
    }

    public void setCodigoHexadecimalCorFundo(String codigoHexadecimalCorFundo) {
        this.codigoHexadecimalCorFundo = codigoHexadecimalCorFundo;
    }

    public List<String> getCodigosHexadecimaisCorFonte() {
        return codigosHexadecimaisCorFonte;
    }

    public void setCodigosHexadecimaisCorFonte(List<String> codigosHexadecimaisCorFonte) {
        this.codigosHexadecimaisCorFonte = codigosHexadecimaisCorFonte;
    }

    public void setHexCorFonte(String hexCorFonte) {
        this.codigosHexadecimaisCorFonte.add(hexCorFonte);
    }

    public String getAlinhamento() {
        return alinhamento;
    }

    public void setAlinhamento(String alinhamento) {
        this.alinhamento = alinhamento;
    }
}
