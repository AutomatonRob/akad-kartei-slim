package entitaeten;

import java.util.ArrayList;

/**
 * Freund als Basis-Objekte zur Speicherung von persönlichen Daten eines Freundes, 
 * inklusive Vorname, Nachname, Geburtstag und Adressen sowie einem Schlüssel zur 
 * eindeutigen Identifizierung.
 */
public class Freund {
    private int schluesselSeed = 1;

    /**
     * Einzigartiger, ganzzahliger Schlüssel, der ein Freund-Objekt eindeutig identifiziert.
     */
    private int schluessel;
    
    /**
     * Vorname des Freund-Objektes.
     */
    private String vorname;
    
    /**
     * Nachname des Freund-Objektes.
     */
    private String nachname;
    
    /**
     * Geburtsdatum des Freund-Objektes.
     */
    private String geburtsdatum;
    
    /**
     * Liste von Adressen des Freund-Objektes.
     */
    private ArrayList<Adresse> adressen;

    /**
     * Konstruktor zur Erzeugung eines leeren Freund-Objektes mit einzigartigem Schlüssel. 
     */
    public Freund() {
        this("", "", "", new ArrayList<Adresse>());
    }

    /**
     * Konstruktor zur Erzeugung eines Freund-Objektes mit den übergebenen Werten und 
     * einzigartigem Schlüssel.
     * @param vorname Vorname
     * @param nachname Nachname
     * @param geburtsdatum Geburtsdatum im Format: DD.MM.YYYY
     * @param adressen Liste von Adressen des Freund-Objektes
     */
    public Freund(String vorname, String nachname, String geburtsdatum, ArrayList<Adresse> adressen) {
        this.schluessel = erzeugeSchluessel();
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtsdatum = geburtsdatum;
        this.adressen = adressen;
    }

    /**
     * Rückgabe des Vornamens eines Freund-Objektes.
     * @return Vorname
     */
    public String getVorname() {
        return this.vorname;
    }

    /**
     * Änderung des Vornamens eines Freund-Objektes.
     * @param vorname Neuer Vorname
     */
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    /**
     * Rückgabe des Nachnames eines Freund-Objektes.
     * @return Nachname
     */
    public String getNachname() {
        return this.nachname;
    }

    /**
     * Änderung des Nachnames eines Freund-Objektes.
     * @param nachname Neuer Nachname
     */
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    /**
     * Rückgabe des Geburtsdatums eines Freund-Objektes.
     * @return Geburtsdatum
     */
    public String getGeburtsdatum() {
        return this.geburtsdatum;
    }

    /**
     * Änderung des Geburtsdatums eines Freund-Objektes.
     * @param nachname Neues Geburtsdatum
     */
    public void setGeburtsdatum(String geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    /**
     * Rückgabe der Adressen eines Freund-Objektes.
     * @return Liste von Adressen
     */
    public ArrayList<Adresse> getAdressen() {
        return this.adressen;
    }

    /**
     * Änderung der Adressen eines Freund-Objektes.
     * @param nachname Neue Liste von Adressen
     */
    public void setAdressen(ArrayList<Adresse> adressen) {
        this.adressen = adressen;
    }

    /**
     * Rückgabe des Schlüssels eines Freund-Objektes.
     * @return Einzigartiger Schlüssel
     */
    public int getSchluessel() {
        return this.schluessel;
    }

    /**
     * Überprüfung des eingegebenen Geburtsdatums auf das Format: DD.MM.YYYY
     * @param geburtsdatum Zu prüfendes Geburtsdatum
     * @return Wahr, wenn das eingegebenen Geburtsdatum dem Muster enspricht
     */
    public static boolean validateGeburtsdatum(String geburtsdatum) {
        return geburtsdatum.matches("^(0?[1-9]|[12][0-9]|3[01])[\\.](0?[1-9]|1[012])[\\.]\\d{4}$");
    }

    /**
     * Erzeugung eines einzigartigen, ganzzahligen Schlüssels.
     * @return Einzigartiger, ganzzahliger Schlüssel
     */
    private int erzeugeSchluessel() {
        return schluesselSeed++;
    }
}
