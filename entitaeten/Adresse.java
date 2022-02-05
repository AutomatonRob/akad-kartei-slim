package entitaeten;

/**
 * Adresse als Basis-Objekte zur Speicherung von Adressdaten eines Freundes,
 * inklusive Straße (inkl. Hausnummer), Postleitzahl und Ort.
 */
public class Adresse {

    /**
     * Postleitzahl des Adress-Objektes.
     */
    private String plz;
    
    /**
     * Ort des Adress-Objektes.
     */
    private String ort;
    
    /**
     * Straße (und Hausnummer) des Adress-Objektes.
     */
    private String strasse;

    /**
     * Konstruktor zur Erzeugung eines leeren Adress-Objektes.
     */
    public Adresse() {
        this("", "", "");
    }

    /**
     * Konstruktor zur Erzeugung eines Adress-Objektes mit den übergebenen Werten.
     * @param plz Postleitzahl
     * @param ort Ort
     * @param strasse Straße (und Hausnummer)
     */
    public Adresse(String plz, String ort, String strasse) {
        this.plz = plz;
        this.ort = ort;
        this.strasse = strasse;
    }

    /**
     * Rückgabe der Postleitzahl eines Adress-Objektes.
     * @return Postleitzahl
     */
    public String getPlz() {
        return this.plz;
    }

    /**
     * Änderung der Postleitzahl Rückgabe eines Adress-Objektes.
     * @param plz Neue Postleitzahl
     */
    public void setPlz(String plz) {
        this.plz = plz;
    }

    /**
     * Rückgabe des Ortes eines Adress-Objektes.
     * @return Ort
     */
    public String getOrt() {
        return this.ort;
    }

    /**
     * Änderung des Ortes eines Adress-Objektes.
     * @param ort Neuer Ort
     */
    public void setOrt(String ort) {
        this.ort = ort;
    }

    /**
     * Rückgabe der Straße (und Hausnummer) eines Adress-Objektes.
     * @return Straße (und Hausnummer)
     */
    public String getStrasse() {
        return this.strasse;
    }

    /**
     * Änderung der Straße (und Hausnummer) eines Adress-Objektes.
     * @param strasse Neue Straße (und Hausnummer)
     */
    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    /**
     * Überprüfung der eingegebenen Postleitzahl auf das Format: NNNNN
     * @param plz Zu prüfende Postleitzahl
     * @return Wahr, wenn die eingegebene Postleitzahl dem Muster entspricht
     */
    public static boolean validatePostleitzahl(String plz) {
        return plz.matches("^\\d{5}$");
    }
}
