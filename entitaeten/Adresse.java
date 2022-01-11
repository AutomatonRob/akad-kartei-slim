package entitaeten;

public class Adresse {
    private String plz;
    private String ort;
    private String strasse;

    public Adresse() {
        this.plz = "";
        this.ort = "";
        this.strasse = "";
    }

    public Adresse(String plz, String ort, String strasse) {
        this.plz = plz;
        this.ort = ort;
        this.strasse = strasse;
    }

    public String getPlz() {
        return this.plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return this.ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getStrasse() {
        return this.strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public boolean validatePostleitzahl(String plz) {
        return plz.matches("^\\d{5}$");
    }
}
