package com.projetotcs.tcsbackend.utilitarios;

import java.util.LinkedHashMap;
import java.util.Map;


public class MesStringToNumeral {

    public static String getNumMes(String mes) {

        Map<String, String> meses = new LinkedHashMap<>();

        meses.put("janeiro", "01");
        meses.put("fevereiro", "02");
        meses.put("março", "03");
        meses.put("abril", "04");
        meses.put("maio", "05");
        meses.put("junho", "06");
        meses.put("julho", "07");
        meses.put("agosto", "08");
        meses.put("setembro", "09");
        meses.put("outubro", "10");
        meses.put("novembro", "11");
        meses.put("dezembro", "12");

        return meses.get(mes.toLowerCase());
    }
}