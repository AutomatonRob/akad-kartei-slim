/*
    In der Klasse Freund werden die Vornamen, Nachname, Geburtsdatum und Adressen der
    Freunde gespeichert. Außerdem benötigen Sie noch eine Variable, welche jedes Objekt
    „Freund“ eindeutig identifiziert (Schlüssel). Es gibt Methoden zum Anlegen von neuen
    Freunden, sowie zum Auslesen und zu den Änderungen einzelner wesentlicher Attribute.
    Außerdem sollen die Freunde auch mehrere Adressen haben können. Stellen Sie für diese
    Problemstellung nur die wichtigen Methoden zum Auslesen und Ändern der Attribute zur
    Verfügung. Berücksichtigen Sie auch mögliche Fehler, die auftreten können.
*/

package entitaeten;

import java.util.ArrayList;

public class Freund {
    private int schluessel;
    private String vorname;
    private String nachname;
    private String geburtsdatum;
    private ArrayList<Adresse> adressen;

    // Konstruktor zum Anlegen eines neuen Freundes
    public Freund(String vorname, String nachname, String geburtsdatum, ArrayList<Adresse> adressen, int schluessel) {
        this.schluessel = schluessel;
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtsdatum = geburtsdatum;
        this.adressen = adressen;
    }

    // Auslesen und Setzen von Attributen
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
}
