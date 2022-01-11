package entitaeten;

import java.util.ArrayList;

public class Freund {
    private int schluessel;
    private String vorname;
    private String nachname;
    private String geburtsdatum;
    private ArrayList<Adresse> adressen;

    public Freund(int schluessel) {
        this.schluessel = schluessel;
        this.vorname = "";
        this.nachname = "";
        this.geburtsdatum = "";
        this.adressen = new ArrayList<Adresse>();
    }

    public Freund(String vorname, String nachname, String geburtsdatum, ArrayList<Adresse> adressen, int schluessel) {
        this.schluessel = schluessel;
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtsdatum = geburtsdatum;
        this.adressen = adressen;
    }

    public String getVorname() {
        return this.vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return this.nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getGeburtsdatum() {
        return this.geburtsdatum;
    }

    public void setGeburtsdatum(String geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public ArrayList<Adresse> getAdressen() {
        return this.adressen;
    }

    public void setAdressen(ArrayList<Adresse> adressen) {
        this.adressen = adressen;
    }

    public int getSchluessel() {
        return this.schluessel;
    }

    public boolean validateGeburtsdatum(String geburtsdatum) {
        return geburtsdatum.matches("^(0?[1-9]|[12][0-9]|3[01])[\\.](0?[1-9]|1[012])[\\.]\\d{4}$");
    }
}
