package com.projetotcs.tcsbackend.utilitarios;

public class DadosCelulaDiaAula {

    private Integer qtdePreenchivel;
    private String codHexCorFundo;

    public DadosCelulaDiaAula(Integer qtdePreenchivel, String codHexCorFundo) {
        this.qtdePreenchivel = qtdePreenchivel;
        this.codHexCorFundo = codHexCorFundo;
    }

    public Integer getQtdePreenchivel() {
        return qtdePreenchivel;
    }

    public void setQtdePreenchivel(Integer qtdePreenchivel) {
        this.qtdePreenchivel = qtdePreenchivel;
    }

    public String getCodHexCorFundo() {
        return codHexCorFundo;
    }

    public void setCodHexCorFundo(String codHexCorFundo) {
        this.codHexCorFundo = codHexCorFundo;
    }
}
