/*
    In der Klasse Kartei sollen die Freunde verwaltet werden. Es sollen Methoden zum
    Hinzufügen, zum Ändern, zum Löschen von Freunde geben. Auch möchte man nach
    Freunde in der Kartei suchen können (z.B. nach dem Nachnamen oder dem Schlüssel) und
    die Gesamtanzahl der Freunde, die gespeichert sind, ausgeben können. Berücksichtigen Sie
    auch mögliche Fehler, die auftreten können.
*/
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entitaeten.Freund;
import entitaeten.Adresse;


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

    private static String inputString(String label) {
        switch(label) {
            case "PLZ":
                return inputPostleitzahl();
            case "Geburtsdatum":
                return inputGeburtsdatum();
            default:
                System.out.println("\n" + label + " eingeben: ");
                return scan.nextLine();  
        }
    }

    private static String inputGeburtsdatum() {
        String geburtsdatum = "";

        while (!validiereGeburtsdatum(geburtsdatum)) {
            System.out.println("\nGeburtsdatum eingeben (DD.MM.YYYY):");
            geburtsdatum = scan.nextLine();
        }

        return geburtsdatum;
    }

    private static String inputPostleitzahl() {
        String plz = "";

        while (!validierePostleitzahl(plz)) {
            System.out.println("\nFünfstellige Postleitzahl eingeben:");
            plz = scan.nextLine();
        }

        return plz;
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
        int index = 0;
        String[] arrStr = new String[adressen.size()];

        for (Adresse adresse : adressen) {
            arrStr[index] = "("+ ++index + ") " + adresse.getStrasse() + ", " + adresse.getPlz() + " " + adresse.getOrt();
        }

        return String.join(" ", arrStr);
    }

    private void freundHinzufuegen(int addSchluessel) {
        System.out.println("\n### Einen neuen Freundeeintrag anlegen");
        freunde.add(new Freund(inputString("Vorname"), inputString("Nachname"), inputString("Geburtsdatum"), freundAdressenAnlegen(new ArrayList<Adresse>()), addSchluessel));
    }

    private void freundAendern() {
        System.out.println("\nBitte Schlüssel des Freundes eingeben, der bearbeitet werden soll:");
        int editSchluessel = inputInt();
        int freundIndex = freundIndexViaSchluessel(editSchluessel);

        if (freundIndex < 0) {
            System.out.println("\nEs wurde kein Freund mit dem Schlüssel " + editSchluessel + " gefunden. \nAktion wird abgebrochen.");
        } 
        else {
            Freund freund = freunde.get(freundIndex);
            ArrayList<Adresse> adressen = freund.getAdressen();
            List<Integer> options = Arrays.asList(0, 1, 2, 3, 4, 5);
            int auswahl = -1;

            System.out.println("\n### Änderung");
            System.out.println("(1) Vorname: " + freund.getVorname());
            System.out.println("(2) Nachname: " + freund.getNachname());
            System.out.println("(3) Geburtsdatum: " + freund.getGeburtsdatum());
            System.out.println("(4) Adressen ändern: " + adressenAusgeben(freund.getAdressen()));
            System.out.println("(5) Neue Adresse anlegen");
            System.out.println("(0) Abbrechen");

            while (!options.contains(auswahl)) {
                System.out.println("\nAktion auswählen:");
                auswahl = inputInt();
            }

            switch(auswahl) {
                case 0:
                    return;
                case 1:
                    freund.setVorname(inputString("Vorname"));
                    break;
                case 2:
                    freund.setNachname(inputString("Nachname"));
                    break;
                case 3:
                    freund.setGeburtsdatum(inputString("Geburtsdatum"));
                    break;
                case 4:
                    freund.setAdressen(adressen.isEmpty() ? freundAdressenAnlegen(adressen) : freundAdressenAendern(adressen));
                    break;
                case 5:
                    freund.setAdressen(freundAdressenAnlegen(adressen));
                    break;
            }
        }
    }

    private static ArrayList<Adresse> freundAdressenAendern(ArrayList<Adresse> adressen) {
        int adressId = -1;

        while (adressId < 0 || adressId > adressen.size()) {
            System.out.println("\nWelche Adress-ID soll geändert werden:");
            adressId = inputInt() - 1;
        }

        Adresse editAdresse = adressen.get(adressId);        

        editAdresse.setStrasse(inputString("Straße und Hausnummer"));
        editAdresse.setPlz(inputString("PLZ"));
        editAdresse.setOrt(inputString("Ort"));

        return adressen;
    }

    private static ArrayList<Adresse> freundAdressenAnlegen(ArrayList<Adresse> adressen) {
        String weitereAdresseAnlegen = "y";

        while (weitereAdresseAnlegen.equals("y")) {
            System.out.println("\n### Adresse anlegen");
            adressen.add(new Adresse(inputString("PLZ"), inputString("Ort"), inputString("Straße und Hausnummer")));
            System.out.println("\nWeitere Adresse für diesen Freund anlegen? (y/n)");
            weitereAdresseAnlegen = scan.next();
            scan.nextLine();
        }

        return adressen;
    }

    private static int inputInt() {
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
                System.out.println("\nUngültige Eingabe! Bitte positive ganze Zahl eingeben.");
                scan.nextLine();
            }
        }

        return value;
    }

    private void freundLoeschen() {
        System.out.println("\nSchlüssel des Freundes eingeben, der gelöscht werden soll:");
        int removeSchluessel = inputInt();
        freunde.removeIf(freund -> (freund.getSchluessel() == removeSchluessel));
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

    private int freundIndexViaSchluessel(int findSchluessel) {
        int index = 0;

        for (Freund freund : freunde) {
            if (freund.getSchluessel() == findSchluessel) {
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
        List<Integer> options = Arrays.asList(0, 1, 2, 3, 4, 5);
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
            System.out.println("\nAktion auswählen:");
            auswahl = inputInt();
        }
        
        return auswahl;
    }

    public int getFreundeAnzahl() {
        return freunde.size();
    }

    public static void main(String[] args) {
        Kartei kartei = new Kartei();
        int quit = 0;
        
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

        freunde.add(new Freund("Thomas", "Mann", "06.06.1875", adressen1, erzeugeSchluessel()));
        freunde.add(new Freund("Herrmann", "Hesse", "02.07.1877", adressen2, erzeugeSchluessel()));
        freunde.add(new Freund("Hans", "Fallada", "21.07.1893", adressen3, erzeugeSchluessel()));
        freunde.add(new Freund("Rudolf Wilhelm Friedrich", "Ditzen", "21.07.1893", adressen4, erzeugeSchluessel()));
        freunde.add(new Freund("Franz", "Kafka", "03.07.1983", adressen5, erzeugeSchluessel()));
        freunde.add(new Freund("Heinrich", "Böll", "21.12.1917", new ArrayList<Adresse>(), erzeugeSchluessel()));
        freunde.add(new Freund("Maxi", "Musterfrau", "24.03.1989", adressen7, erzeugeSchluessel()));

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