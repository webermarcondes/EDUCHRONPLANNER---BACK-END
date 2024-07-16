package com.projetotcs.tcsbackend.utilitarios;

public class DadoCelulaDiaAula {

    private Integer quantidadeCelulaPreenchivel;
    private String codigoHexadecimalCorFundo;

    public DadoCelulaDiaAula(Integer quantidadeCelulaPreenchivel, String codigoHexadecimalCorFundo) {
        this.quantidadeCelulaPreenchivel = quantidadeCelulaPreenchivel;
        this.codigoHexadecimalCorFundo = codigoHexadecimalCorFundo;
    }

    public Integer getQuantidadeCelulaPreenchivel() {
        return quantidadeCelulaPreenchivel;
    }

    public void setQuantidadeCelulaPreenchivel(Integer quantidadeCelulaPreenchivel) {
        this.quantidadeCelulaPreenchivel = quantidadeCelulaPreenchivel;
    }

    public String getCodigoHexadecimalCorFundo() {
        return codigoHexadecimalCorFundo;
    }

    public void setCodigoHexadecimalCorFundo(String codigoHexadecimalCorFundo) {
        this.codigoHexadecimalCorFundo = codigoHexadecimalCorFundo;
    }
}
