/*
    In der Klasse Kartei sollen die Freunde verwaltet werden. Es sollen Methoden zum
    Hinzufügen, zum Ändern, zum Löschen von Freunde geben. Auch möchte man nach
    Freunde in der Kartei suchen können (z.B. nach dem Nachnamen oder dem Schlüssel) und
    die Gesamtanzahl der Freunde, die gespeichert sind, ausgeben können. Berücksichtigen Sie
    auch mögliche Fehler, die auftreten können.
*/

import entitaeten.Freund;
import entitaeten.Adresse;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Kartei {
    private static final ArrayList<Freund> freunde = new ArrayList<Freund>();
    private static int schluessel = 1;
    private static int anzahlFreunde = 0;
    private static Scanner input = new Scanner(System.in);

    private static String getInputString() {
        return input.next();
    }

    private void freundeAuflisten() {
        System.out.println("\n### FREUNDE"); 
        System.out.println("Schlüssel | Vorname | Name | Geburtsdatum | Anz. Adressen"); 
        for(Freund freund : freunde) {
            System.out.println(freund.getSchluessel() + " | " + freund.getVorname() + " | " + freund.getNachname() + " | " + freund.getGeburtsdatum() + " | " + freund.getAddressen().size());
        }
    }

    private void freundHinzufuegen(String vorname, String nachname, String geburtsdatum, Vector<Adresse> addressen, String schluessel) {
        Freund freund = new Freund(vorname, nachname, geburtsdatum, addressen, schluessel);
        freunde.add(freund);
    }

    private void freundAendern(int schluessel) {
        System.out.println("Bitte Schlüssel des Freundes eingeben, der bearbeitet werden soll:");
        getInputString();
        
    }

    private void freundLoeschen() {
        System.out.println("Schlüssel des Freundes eingeben, der gelöscht werden soll:");
        String inputSchluessel = getInputString();
        freunde.removeIf(freund -> (freund.getSchluessel().equals(inputSchluessel)));
    }

    private void freundSuchen() {
        System.out.println("\nBitte Schlüssel, Vorname oder Nachname eines Freundes eingeben, nachdem gesucht werden soll:");
        String value = getInputString();
        
        freunde.forEach(freund -> {
            if(freund.getVorname().contains(value) || freund.getNachname().contains(value) || freund.getSchluessel().equals(value)) {
                System.out.println("Freund gefunden: (" + freund.getSchluessel() + ") " + freund.getVorname() + " " + freund.getNachname());
            }
        });

    }

    private String erzeugeSchluessel() {
        return Integer.toString(this.schluessel++);

    }

    private String zeigeMenue() {
        System.out.println("\n### Kartei");
        System.out.println("(1) Zeige alle Freunde");
        System.out.println("(2) Freundekartei durchsuchen");
        System.out.println("(3) Ändere die Daten eines Freundes");
        System.out.println("(4) Lösche einen Freund");
        System.out.println("(0) Beenden");
        System.out.println("### Anzahl deiner Freunde: " + anzahlFreunde);
        System.out.println("Was möchtest Du tun:");

        return getInputString();
    }

    public int getFreundeAnzahl() {
        return freunde.size();
    }

    public static void main(String[] args) {
        Kartei kartei = new Kartei();

        int quit = 0;

        Vector<Adresse> adressen1 = new Vector<Adresse>();
        Adresse adresse1 = new Adresse("PLZ", "Ort", "Strasse");
        adressen1.add(adresse1);

        kartei.freundHinzufuegen("Thomas", "Mann", "06.06.1875", adressen1, kartei.erzeugeSchluessel());
        kartei.freundHinzufuegen("Herrmann", "Hesse", "02.07.1877", adressen1, kartei.erzeugeSchluessel());
        kartei.freundHinzufuegen("Hans", "Fallada", "21.07.1893", adressen1, kartei.erzeugeSchluessel());
        kartei.freundHinzufuegen("Rudolf Wilhelm Friedrich", "Ditzen", "21.07.1893", adressen1, kartei.erzeugeSchluessel());
        kartei.freundHinzufuegen("Franz", "Kafka", "03.07.1983", adressen1, kartei.erzeugeSchluessel());
        kartei.freundHinzufuegen("Heinrich", "Böll", "21.12.1917", adressen1, kartei.erzeugeSchluessel());

        while (quit == 0) {
            anzahlFreunde = kartei.getFreundeAnzahl();
            String value = kartei.zeigeMenue();

            switch(value) {
                case "0":
                    quit = 1;
                    break;
                case "1":
                    kartei.freundeAuflisten();
                    break;
                case "2":
                    kartei.freundSuchen();
                    break;
                case "3":
                    System.out.println("Funktion noch nicht implementiert");
                    break;
                case "4":
                    kartei.freundLoeschen();
                    break;
                default:
                    System.out.println("Auswahl ungültig!");
            }
        }
                
    }

}