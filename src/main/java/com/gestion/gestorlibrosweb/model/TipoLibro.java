package com.gestion.gestorlibrosweb.model;

public enum TipoLibro {

    // Solo toma el valor Fisico o Digital

    FISICO, DIGItAL;

    public static  TipoLibro getTipoLibro(String tipo) {

        switch (tipo) {
            case "1":
                return FISICO;
                case "2":
                    return DIGItAL;

        }
        return null;

    }

    public String getTipo() {

        switch (this) {
            case FISICO:
                return "1";
                case DIGItAL:
                    return "2";

        }
        return "";
    }




}
