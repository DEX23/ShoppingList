package com.workshop.execom.shoppinglist.model;



/**
 * Created by user on 13.4.2017.
 */
public class Articles {

    public static final String FIELD_NAME_ID = "id";
    public static final String FIELD_NAME_NAME = "ime artikla";
    public static final String FIELD_NAME_KOLICINA = "kolicina artikla";

    private int idArtikla;
    private String imeArtikla;
    private String kolicinaArtikla;

    public Articles(){}

    public int getIdArtikla() {
        return idArtikla;
    }

    public String getImeArtikla() {
        return imeArtikla;
    }

    public String getKolicinaArtikla() {
        return kolicinaArtikla;
    }

    public void setIdArtikla(int idArtikla) {
        this.idArtikla = idArtikla;
    }

    public void setImeArtikla(String imeArtikla) {
        this.imeArtikla = imeArtikla;
    }

    public void setKolicinaArtikla(String kolicinaArtikla) {
        this.kolicinaArtikla = kolicinaArtikla;
    }

    public Articles(int idArtikla, String imeArtikla, String kolicinaArtikla) {
        this.idArtikla = idArtikla;
        this.imeArtikla = imeArtikla;
        this.kolicinaArtikla = kolicinaArtikla;
    }
}