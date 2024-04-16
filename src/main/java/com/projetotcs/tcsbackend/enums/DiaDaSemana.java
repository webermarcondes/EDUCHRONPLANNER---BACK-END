package com.projetotcs.tcsbackend.enums;

public enum DiaDaSemana {
    SEG(0, "SEG"),
    TER(1, "TER"),
    QUA(2, "QUA"),
    QUI(3, "QUI"),
    SEX(4, "SEX"),
    SAB(5, "SAB");

    final String descricao;
    final int id;

    private DiaDaSemana(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
    

    public static DiaDaSemana getById(int id) {
        for(DiaDaSemana diaDaSemana: values()) {
            if (diaDaSemana.id == id) {
                return diaDaSemana;
            }
        }

        return null;
    }

    public static DiaDaSemana getByDescricao(String descricao) {
        for(DiaDaSemana diaDaSemana: values()) {
            if (diaDaSemana.descricao.equals(descricao)) {
                return diaDaSemana;
            }
        }

        
        return null;
    }


}
