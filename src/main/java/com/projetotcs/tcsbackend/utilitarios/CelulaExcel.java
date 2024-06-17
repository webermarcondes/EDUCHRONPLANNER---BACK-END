package com.projetotcs.tcsbackend.utilitarios;

import java.util.ArrayList;
import java.util.List;

public class CelulaExcel {

    private String conteudo;
    private String hexCorFundo;
    private List<String> hexsCoresFonte;

    public CelulaExcel(String conteudo, String hexCorFundo) {
        this.conteudo = conteudo;
        this.hexCorFundo = hexCorFundo;
        this.hexsCoresFonte = new ArrayList<>();
    }

    public CelulaExcel(String conteudo) {
        this.conteudo = conteudo;
        this.hexsCoresFonte = new ArrayList<String>();
        this.hexCorFundo = "";
    }



    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getHexCorFundo() {
        return hexCorFundo;
    }

    public void setHexCorFundo(String hexCorFundo) {
        this.hexCorFundo = hexCorFundo;
    }

    public List<String> getHexsCoresFonte() {
        return hexsCoresFonte;
    }

    public void setHexsCoresFonte(List<String> hexsCoresFonte) {
        this.hexsCoresFonte = hexsCoresFonte;
    }

    public void setHexCorFonte(String hexCorFonte) {
        this.hexsCoresFonte.add(hexCorFonte);
    }
}
