/*
    In der Klasse Kartei sollen die Freunde verwaltet werden. Es sollen Methoden zum
    Hinzufügen, zum Ändern, zum Löschen von Freunde geben. Auch möchte man nach
    Freunde in der Kartei suchen können (z.B. nach dem Nachnamen oder dem Schlüssel) und
    die Gesamtanzahl der Freunde, die gespeichert sind, ausgeben können. Berücksichtigen Sie
    auch mögliche Fehler, die auftreten können.
*/

import entitaeten.Freund;
import entitaeten.Adresse;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class Kartei {
    private static final ArrayList<Freund> freunde = new ArrayList<Freund>();
    private static int schluessel = 1;
    private static int anzahlFreunde = 0;
    private static Scanner input = new Scanner(System.in);

    private static boolean validiereGeburtsdatum(String geburtsdatum) {
        return geburtsdatum.matches("^(0?[1-9]|[12][0-9]|3[01])[\\.](0?[1-9]|1[012])[\\.]\\d{4}$");
    }

    private static boolean validierePostleitzahl(String plz) {
        return plz.matches("^\\d{5}$");
    }

    private void freundeAuflisten() {
        System.out.println("\n### FREUNDE"); 
        System.out.println("Schlüssel | Vorname | Name | Geburtsdatum | Anz. Adressen"); 
        for(Freund freund : freunde) {
            System.out.println(freund.getSchluessel() + " | " + freund.getVorname() + " | " + freund.getNachname() + " | " + freund.getGeburtsdatum() + " | " + freund.getAddressen().size());
        }
    }

    private void freundHinzufuegen(String schluessel) {
        input.nextLine();
        
        System.out.println("\n### Einen neuen Freundeeintrag anlegen");
        System.out.println("Vorname eingeben: ");
        String vorname = input.nextLine();
        System.out.println("\nNachname eingeben: ");
        String nachname = input.nextLine();
        System.out.println("\nGeburtsdatum eingeben (DD.MM.YYYY): ");
        String geburtsdatum = input.next();
        while(!validiereGeburtsdatum(geburtsdatum)) {
            System.out.println("\nEingabeformat Fehlerhaft! Geburtsdatum eingeben (DD.MM.YYYY): ");
            geburtsdatum = input.next();
        }

        ArrayList<Adresse> adressen = freundAdressenAnlegen();

        freunde.add(new Freund(vorname, nachname, geburtsdatum, adressen, schluessel));
    }

    private static ArrayList<Adresse> freundAdressenAnlegen() {
        ArrayList<Adresse> adressen = new ArrayList<Adresse>();
        String weitereAdresseAnlegen = "y";

        while(weitereAdresseAnlegen.equals("y")) {
            input.nextLine();

            System.out.println("### Adresse anlegen");
            System.out.println("\nStraße und Hausnummer eingeben: ");
            String strasse = input.nextLine();
            System.out.println("\nPostleitzahl eingeben: ");
            String plz = input.next();
            while (!validierePostleitzahl(plz)) {
                System.out.println("\nFehlerhafte Eingabe! Bitte fünfstellige PLZ eingeben: ");
                plz = input.next();
            };
            System.out.println("\nOrt eingeben: ");
            input.nextLine();
            String ort = input.nextLine();

            adressen.add(new Adresse(plz, ort, strasse));

            System.out.println("\nWeitere Adresse für diesen Freund anlegen? (y/n)");
            weitereAdresseAnlegen = input.next();
        }

        return adressen;
    }

    private void freundAendern(int schluessel) {
        System.out.println("Bitte Schlüssel des Freundes eingeben, der bearbeitet werden soll:");
        input.next();

        // TODO
    }

    private void freundLoeschen() {
        System.out.println("\nSchlüssel des Freundes eingeben, der gelöscht werden soll:");
        String inputSchluessel = input.next();
        freunde.removeIf(freund -> (freund.getSchluessel().equals(inputSchluessel)));
    }

    private void freundSuchen() {
        System.out.println("\nBitte Schlüssel, Vorname oder Nachname eines Freundes eingeben, nachdem gesucht werden soll:");
        input.nextLine();
        String value = input.nextLine();
        
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
        System.out.println("(4) Einen neuen Eintrag anlegen");
        System.out.println("(5) Lösche einen Freund");
        System.out.println("(0) Beenden");
        System.out.println("### Anzahl deiner Freunde: " + anzahlFreunde);
        System.out.println("\nWas möchtest Du tun:");

        return input.next().strip();
    }

    public int getFreundeAnzahl() {
        return freunde.size();
    }

    public static void main(String[] args) {
        Kartei kartei = new Kartei();

        int quit = 0;

        ArrayList<Adresse> adressen1 = new ArrayList<Adresse>();
        Adresse adresse1 = new Adresse("PLZ", "Ort", "Strasse");
        adressen1.add(adresse1);

        freunde.add(new Freund("Thomas", "Mann", "06.06.1875", adressen1, kartei.erzeugeSchluessel()));
        freunde.add(new Freund("Herrmann", "Hesse", "02.07.1877", adressen1, kartei.erzeugeSchluessel()));
        freunde.add(new Freund("Hans", "Fallada", "21.07.1893", adressen1, kartei.erzeugeSchluessel()));
        freunde.add(new Freund("Rudolf Wilhelm Friedrich", "Ditzen", "21.07.1893", adressen1, kartei.erzeugeSchluessel()));
        freunde.add(new Freund("Franz", "Kafka", "03.07.1983", adressen1, kartei.erzeugeSchluessel()));
        freunde.add(new Freund("Heinrich", "Böll", "21.12.1917", adressen1, kartei.erzeugeSchluessel()));

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
                    kartei.freundHinzufuegen(kartei.erzeugeSchluessel());
                    break;
                case "5":
                    kartei.freundLoeschen();
                    break;
                default:
                    System.out.println("\nAuswahl ungültig!");
            }
        }
    }
}