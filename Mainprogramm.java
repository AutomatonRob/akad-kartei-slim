import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entitaeten.*;

/**
 * Main Programm mit Hauptmenü.
 */
public class Mainprogramm {

    /**
     * Hilfsfunktion zum Erzeugen von Testdatensätzen.
     * @param kartei Kartei-Objekt
     */
    private static void createKarteieintraege(Kartei kartei) {
        ArrayList<Adresse> adressen1 = new ArrayList<Adresse>();
        adressen1.add(new Adresse("00001", "Ort1", "Strasse 1"));

        ArrayList<Adresse> adressen2 = new ArrayList<Adresse>();
        adressen2.add(new Adresse("00002", "Ort2", "Strasse 2"));

        ArrayList<Adresse> adressen3 = new ArrayList<Adresse>();
        adressen3.add(new Adresse("00003", "Ort3", "Strasse 3"));

        ArrayList<Adresse> adressen4 = new ArrayList<Adresse>();
        adressen4.add(new Adresse("00004", "Ort4", "Strasse 4"));

        ArrayList<Adresse> adressen5 = new ArrayList<Adresse>();
        adressen5.add(new Adresse("00005", "Ort5", "Strasse 5"));

        ArrayList<Adresse> adressen6 = new ArrayList<Adresse>();
        adressen6.add(new Adresse("00006", "Ort6", "Strasse 6"));
        adressen6.add(new Adresse("00007", "Ort7", "Strasse 7"));

        kartei.freundHinzufuegen(new Freund("Thomas", "Mann", "06.06.1875", adressen1));
        kartei.freundHinzufuegen(new Freund("Herrmann", "Hesse", "02.07.1877", adressen2));
        kartei.freundHinzufuegen(new Freund("Hans", "Fallada", "21.07.1893", adressen3));
        kartei.freundHinzufuegen(new Freund("Rudolf Wilhelm Friedrich", "Ditzen", "21.07.1893", adressen4));
        kartei.freundHinzufuegen(new Freund("Franz", "Kafka", "03.07.1983", adressen5));
        kartei.freundHinzufuegen(new Freund("Heinrich", "Böll", "21.12.1917", new ArrayList<Adresse>()));
        kartei.freundHinzufuegen(new Freund("Maxi", "Musterfrau", "24.03.1989", adressen6));
        kartei.freundHinzufuegen(new Freund());
    }

    /*
     * Die main Methode instanziiert eine Kartei und erzeugt bis zur Beendingung des Programmes das 
     * Hauptmenü mit allen enthaltenen Funktionalitäten. Nach jedem Durchlauf einer Funktionalität
     * erfolgt die Rückkehr in das Hauptmenü.
     */
    public static void main(String[] args) {
        Kartei kartei = new Kartei();
        List<Integer> options = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7);
        
        while (true) {
            System.out.println("\n### Kartei");
            System.out.println("(1) Zeige alle Freunde");
            System.out.println("(2) Freundekartei durchsuchen");
            System.out.println("(3) Daten eines Freundes ändern");
            System.out.println("(4) Einen neuen Freund anlegen");
            System.out.println("(5) Einen Freund löschen");
            System.out.println("(6) Testdatensätze laden");
            System.out.println("(7) Aufgaben lösen");
            System.out.println("(0) Beenden");
            System.out.println("### Anzahl deiner Freunde: " + kartei.getFreundeAnzahl());

            int selection = -1;

            while (!options.contains(selection)) {
                System.out.println("\nAktion auswählen:");
                selection = kartei.inputInt();
            }

            switch(selection) {
                case 0:
                    return;
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
                    kartei.freundAnlegen();
                    break;
                case 5:
                    kartei.freundLoeschen();
                    break;
                case 6:
                    createKarteieintraege(kartei);
                    break;
                case 7:
                    System.out.println("\nAufgabe 1: Verschiedene Freunde mit ihren möglichen Adressen eingegeben und in der Kartei anlegen.");
                    createKarteieintraege(kartei);
                    System.out.println("\nAufgabe 2: Löschen eines Eintrags.");
                    kartei.freundLoeschen(3);
                    System.out.println("\nAufgabe 3: Änderung eines Eintrags.");
                    kartei.getFreunde().get(0).setVorname("Alois");
                    System.out.println("\nAufgabe 4: Adressliste aller Freunde erstellen und ausgegeben.");
                    kartei.freundeAuflisten();
                    break;
                default:
                    System.out.println("\nAuswahl ungültig!");
            }
        }
    }
}
