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
import java.util.Arrays;

/**
 * 
 */
public class Kartei {
    private static final ArrayList<Freund> freunde = new ArrayList<Freund>();
    private static int schluessel = 1;
    private static int anzahlFreunde = 0;
    private static Scanner scan = new Scanner(System.in);

    private static boolean validiereGeburtsdatum(String geburtsdatum) {
        return geburtsdatum.matches("^(0?[1-9]|[12][0-9]|3[01])[\\.](0?[1-9]|1[012])[\\.]\\d{4}$");
    }

    private static boolean validierePostleitzahl(String plz) {
        return plz.matches("^\\d{5}$");
    }

    private void freundeAuflisten() {
        System.out.println("\n### FREUNDE");
        System.out.println("Schlüssel | Vorname | Name | Geburtsdatum | Adressen");

        for (Freund freund : freunde) {
            StringBuilder str = new StringBuilder();

            str.append(freund.getSchluessel() + " | " + freund.getVorname() + " | " + freund.getNachname() + " | " + freund.getGeburtsdatum() + " | ");
            
            if (freund.getAdressen().size() > 0) {
                ArrayList<Adresse> adressen = freund.getAdressen();
                str.append(adressenAusgeben(adressen));
            } 
            else {
                str.append("n/a");
            }

            System.out.println(str.toString());
        }
    }

    private String adressenAusgeben(ArrayList<Adresse> adressen) {
        StringBuilder str = new StringBuilder();
        int index = 1;

        for (Adresse adresse : adressen) {
            str.append("("+ index++ + ") " + adresse.getStrasse() + ", " + adresse.getPlz() + " " + adresse.getOrt());
        }

        return str.toString();
    }

    private void freundHinzufuegen(int schluessel) {
        System.out.println("\n### Einen neuen Freundeeintrag anlegen");
        System.out.println("Vorname eingeben: ");
        String vorname = scan.nextLine();
        System.out.println("\nNachname eingeben: ");
        String nachname = scan.nextLine();
        System.out.println("\nGeburtsdatum eingeben (DD.MM.YYYY): ");
        String geburtsdatum = scan.next();
        scan.nextLine();

        while (!validiereGeburtsdatum(geburtsdatum)) {
            System.out.println("\nEingabeformat Fehlerhaft! Geburtsdatum eingeben (DD.MM.YYYY): ");
            geburtsdatum = scan.next();
            scan.nextLine();
        }
    
        ArrayList<Adresse> adressen = freundAdressenAnlegen();

        freunde.add(new Freund(vorname, nachname, geburtsdatum, adressen, schluessel));
    }

    

    private void freundAendern() {
        System.out.println("\nBitte Schlüssel des Freundes eingeben, der bearbeitet werden soll:");
        int schluessel = getInputInt();
        int freundIndex = freundIndexViaSchluesselSuchen(schluessel);

        if (freundIndex < 0) {
            System.out.println("\nEs wurde kein Freund mit dem Schlüssel " + schluessel + " gefunden.");
        } 
        else {
            Freund freund = freunde.get(freundIndex);
            ArrayList<Adresse> adressen = freund.getAdressen();
            ArrayList<Integer> options = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5));
            int auswahl = -1;

            System.out.println("### Änderung");
            System.out.println("(1) Vorname: " + freund.getVorname());
            System.out.println("(2) Nachname: " + freund.getNachname());
            System.out.println("(3) Geburtsdatum: " + freund.getGeburtsdatum());
            System.out.println("(4) Adressen ändern: " + adressenAusgeben(freund.getAdressen()));
            System.out.println("(5) Neue Adresse anlegen.");
            System.out.println("(0) Abbrechen");
            
            while (!options.contains(auswahl)) {
                auswahl = getInputInt();
            }

            switch(auswahl) {
                case 0:
                    return;
                case 1:
                    System.out.println("Neuer Vorname: ");
                    freund.setVorname(scan.nextLine());
                    break;
                case 2:
                    System.out.println("Neuer Nachname: ");
                    freund.setNachname(scan.nextLine());
                    break;
                case 3:
                    System.out.println("Neues Geburtsdatum: ");
                    freund.setGeburtsdatum(scan.nextLine());
                    break;
                case 4:
                    freund.setAdressen(adressen.isEmpty() ? freundAdressenAnlegen() : freundAdressenAendern(adressen));
                    break;
                case 5:
                    freund.setAdressen(freundAdressenAnlegen());
                    break;
            }
        }
    }

    // TODO: Adressänderungen werden auf ALLE Instanzen von Adresse in allen Freunden geschrieben!
    private static ArrayList<Adresse> freundAdressenAendern(ArrayList<Adresse> adressen) {
        int adressId = -1;

        while (adressId < 1 || adressId > adressen.size()) {
            System.out.println("Welche Adress-ID soll geändert werden:");
            adressId = getInputInt() - 1;
        }

        Adresse editAdresse = adressen.get(adressId);        

        System.out.println("\nNeue Straßen und Hausnummer:");
        editAdresse.setStrasse(scan.nextLine());
        System.out.println("\nNeue Postleitzahl:");
        editAdresse.setPlz(scan.nextLine());
        System.out.println("\nNeuer Ort:");
        editAdresse.setOrt(scan.nextLine());

        return adressen;
    }

    private static ArrayList<Adresse> freundAdressenAnlegen() {
        ArrayList<Adresse> adressen = new ArrayList<Adresse>();
        String weitereAdresseAnlegen = "y";

        while (weitereAdresseAnlegen.equals("y")) {
            System.out.println("\n### Adresse anlegen");
            System.out.println("\nStraße und Hausnummer eingeben: ");
            String strasse = scan.nextLine();
            System.out.println("\nPostleitzahl eingeben: ");
            String plz = scan.next();
            scan.nextLine();

            while (!validierePostleitzahl(plz)) {
                System.out.println("\nFehlerhafte Eingabe! Bitte fünfstellige PLZ eingeben: ");
                plz = scan.next();
                scan.nextLine();
            }

            System.out.println("\nOrt eingeben: ");
            String ort = scan.nextLine();

            adressen.add(new Adresse(plz, ort, strasse));

            System.out.println("\nWeitere Adresse für diesen Freund anlegen? (y/n)");
            weitereAdresseAnlegen = scan.next();
            scan.nextLine();
        }

        return adressen;
    }

    private static int getInputInt() {
        boolean isValid = false;
        int value = -1;
        
        while (!isValid) {
            if (scan.hasNextInt()) {
                value = scan.nextInt();
                scan.nextLine();

                if (value >= 0) {
                    isValid = true;
                }
            }
            else {
                System.out.println("\nUngültige Eingabe. Bitte positive ganze Zahl eingeben:");
            }
        }

        return value;
    }


    private void freundLoeschen() {
        System.out.println("\nSchlüssel des Freundes eingeben, der gelöscht werden soll:");
        int schluessel = scan.nextInt();
        scan.nextLine();
        freunde.removeIf(freund -> (freund.getSchluessel() == schluessel));
    }

    private void freundSuchen() {
        System.out.println("\nBitte Schlüssel, Vorname oder Nachname eines Freundes eingeben, nachdem gesucht werden soll:");
        String value = scan.nextLine();
        
        freunde.forEach(freund -> {
            if(freund.getVorname().contains(value) || freund.getNachname().contains(value) || Integer.toString(freund.getSchluessel()).equals(value)) {
                System.out.println("Freund gefunden: (" + freund.getSchluessel() + ") " + freund.getVorname() + " " + freund.getNachname());
            }
        });
    }

    private int freundIndexViaSchluesselSuchen(int schluessel) {
        int index = 0;

        for (Freund freund : freunde) {
            if (freund.getSchluessel() == schluessel) {
                return index;
            }
            index++;
        }

        return -1;
    }

    private static int erzeugeSchluessel() {
        return schluessel++;
    }

    private int zeigeMenue() {
        ArrayList<Integer> options = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5));
        int auswahl = -1;

        System.out.println("\n### Kartei");
        System.out.println("(1) Zeige alle Freunde");
        System.out.println("(2) Freundekartei durchsuchen");
        System.out.println("(3) Ändere die Daten eines Freundes");
        System.out.println("(4) Einen neuen Eintrag anlegen");
        System.out.println("(5) Lösche einen Freund");
        System.out.println("(0) Beenden");
        System.out.println("### Anzahl deiner Freunde: " + anzahlFreunde);

        while (!options.contains(auswahl)) {
            System.out.println("\nWas möchtest Du tun:");

            if (scan.hasNextInt()) {
                auswahl = scan.nextInt();
                scan.nextLine();
            }
        }
        
        return auswahl;
    }

    public int getFreundeAnzahl() {
        return freunde.size();
    }

    public static void main(String[] args) {
        Kartei kartei = new Kartei();
        int quit = 0;

        
        Adresse adressDummy = new Adresse("PLZ", "Ort", "Strasse");

        ArrayList<Adresse> adressen1 = new ArrayList<Adresse>();
        adressen1.add(adressDummy);
        
        ArrayList<Adresse> adressen2 = new ArrayList<Adresse>();
        adressen2.add(adressDummy);
        adressen2.add(adressDummy);

        freunde.add(new Freund("Thomas", "Mann", "06.06.1875", adressen1, erzeugeSchluessel()));
        freunde.add(new Freund("Herrmann", "Hesse", "02.07.1877", adressen1, erzeugeSchluessel()));
        freunde.add(new Freund("Hans", "Fallada", "21.07.1893", adressen1, erzeugeSchluessel()));
        freunde.add(new Freund("Rudolf Wilhelm Friedrich", "Ditzen", "21.07.1893", adressen1, erzeugeSchluessel()));
        freunde.add(new Freund("Franz", "Kafka", "03.07.1983", adressen1, erzeugeSchluessel()));
        freunde.add(new Freund("Heinrich", "Böll", "21.12.1917", new ArrayList<Adresse>(), erzeugeSchluessel()));
        freunde.add(new Freund("Maxi", "Musterfrau", "24.03.1989", adressen2, erzeugeSchluessel()));

        while (quit == 0) {
            anzahlFreunde = kartei.getFreundeAnzahl();
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
                    kartei.freundHinzufuegen(erzeugeSchluessel());
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