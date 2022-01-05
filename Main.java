import java.util.ArrayList;

import entitaeten.*;

public class Main {
    private static void createKarteieintraege(Kartei kartei) {
        Adresse adress1 = new Adresse("00001", "Ort1", "Strasse 1");
        Adresse adress2 = new Adresse("00002", "Ort2", "Strasse 2");
        Adresse adress3 = new Adresse("00003", "Ort3", "Strasse 3");
        Adresse adress4 = new Adresse("00004", "Ort4", "Strasse 4");
        Adresse adress5 = new Adresse("00005", "Ort5", "Strasse 5");
        Adresse adress6 = new Adresse("00006", "Ort6", "Strasse 6");
        Adresse adress7 = new Adresse("00007", "Ort7", "Strasse 7");
        Adresse adress8 = new Adresse("00008", "Ort8", "Strasse 8");

        ArrayList<Adresse> adressen1 = new ArrayList<Adresse>();
        adressen1.add(adress1);

        ArrayList<Adresse> adressen2 = new ArrayList<Adresse>();
        adressen2.add(adress2);

        ArrayList<Adresse> adressen3 = new ArrayList<Adresse>();
        adressen3.add(adress3);

        ArrayList<Adresse> adressen4 = new ArrayList<Adresse>();
        adressen4.add(adress4);

        ArrayList<Adresse> adressen5 = new ArrayList<Adresse>();
        adressen5.add(adress5);

        ArrayList<Adresse> adressen6 = new ArrayList<Adresse>();
        adressen6.add(adress6);

        ArrayList<Adresse> adressen7 = new ArrayList<Adresse>();
        adressen7.add(adress7);
        adressen7.add(adress8);

        kartei.freundHinzufuegen(new Freund("Thomas", "Mann", "06.06.1875", adressen1, kartei.erzeugeSchluessel()));
        kartei.freundHinzufuegen(new Freund("Herrmann", "Hesse", "02.07.1877", adressen2, kartei.erzeugeSchluessel()));
        kartei.freundHinzufuegen(new Freund("Hans", "Fallada", "21.07.1893", adressen3, kartei.erzeugeSchluessel()));
        kartei.freundHinzufuegen(new Freund("Rudolf Wilhelm Friedrich", "Ditzen", "21.07.1893", adressen4, kartei.erzeugeSchluessel()));
        kartei.freundHinzufuegen(new Freund("Franz", "Kafka", "03.07.1983", adressen5, kartei.erzeugeSchluessel()));
        kartei.freundHinzufuegen(new Freund("Heinrich", "Böll", "21.12.1917", new ArrayList<Adresse>(), kartei.erzeugeSchluessel()));
        kartei.freundHinzufuegen(new Freund("Maxi", "Musterfrau", "24.03.1989", adressen7, kartei.erzeugeSchluessel()));
    }
    public static void main(String[] args) {
        Kartei kartei = new Kartei();
        int quit = 0;

        createKarteieintraege(kartei);
        
        while (quit == 0) {
            int value = kartei.zeigeMenue();

            switch(value) {
                case 0:
                    quit = 1;
                    break;
                case 1:
                    kartei.freundeAuflisten();
                    break;
                case 2:
                    kartei.freundSuchen();
                    break;
                case 3:
                    kartei.freundAendern();
                    break;
                case 4:
                    kartei.freundHinzufuegen(kartei.erzeugeSchluessel());
                    break;
                case 5:
                    kartei.freundLoeschen();
                    break;
                default:
                    System.out.println("\nAuswahl ungültig!");
            }
        }
    }
}
