package entitaeten;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 */
public class Kartei {
    private ArrayList<Freund> freunde = new ArrayList<Freund>();
    private int schluessel = 1;
    private int anzahlFreunde = 0;
    private Scanner scan = new Scanner(System.in);

    /*
     * PRIVATE METHODS
     */

    private String formatAusgabeAdressen(ArrayList<Adresse> adressen) {
        int index = 0;
        String[] arrStr = new String[adressen.size()];

        for (Adresse adresse : adressen) {
            arrStr[index] = "(" + ++index + ") " + adresse.getStrasse() + ", " + adresse.getPlz() + " " + adresse.getOrt();
        }

        return String.join(" ", arrStr);
    }

    private String inputValidatedGeburtsdatum(Freund freund) {
        String geburtsdatum = "";

        while(!freund.validateGeburtsdatum(geburtsdatum)) {
            System.out.println("\nGeburtsdatum eingeben (DD.MM.YYYY):");
            geburtsdatum = scan.nextLine();
        }

        return geburtsdatum;
    }

    private String inputValidatePostleitzahl(Adresse adresse) {
        String plz = "";

        while(!adresse.validatePostleitzahl(plz)) {
            System.out.println("\nFünfstellige Postleitzahl eingeben:");
            plz = scan.nextLine();
        }

        return plz;
    }

    private Adresse freundSetAdresseDialog(Adresse addresse) {
        System.out.println("Strasse und Hausnummer eingeben:");
        addresse.setStrasse(scan.nextLine());
        
        addresse.setPlz(inputValidatePostleitzahl(addresse));
        
        System.out.println("Ort eingeben:");
        addresse.setOrt(scan.nextLine());

        return addresse;
    }

    private ArrayList<Adresse> freundAdresseAendern(ArrayList<Adresse> adressen) {
        int adressId = -1;

        while (adressId < 1 || adressId > adressen.size()) {
            System.out.println("\nWelche Adress-ID soll geändert werden:");
            adressId = inputInt();
        }

        freundSetAdresseDialog(adressen.get(adressId - 1));

        return adressen;
    }

    private ArrayList<Adresse> freundAdressenAnlegen(ArrayList<Adresse> adressen) {
        int weitereAdresseAnlegen = 1;

        while (weitereAdresseAnlegen == 1) {
            Adresse addAdresse = new Adresse();
            System.out.println("\n### Adresse anlegen");

            freundSetAdresseDialog(addAdresse);

            adressen.add(addAdresse);

            System.out.println("\nWeitere Adresse für diesen Freund anlegen?");
            System.out.println("(1) Ja");
            System.out.println("(*) Abbrechen");
            weitereAdresseAnlegen = inputInt();
        }

        return adressen;
    }

    private int inputInt() {
        boolean isValid = false;
        int value = -1;
        
        while (!isValid) {
            if (scan.hasNextInt()) {
                value = scan.nextInt();
                scan.nextLine();

                if (value >= 0) {
                    isValid = true;
                }
            } else {
                System.out.println("Ungültige Eingabe. Bitte nur positive ganze Zahlen eingeben.");
                scan.nextLine();
            }
        }

        return value;
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

    /*
     * PUBLIC METHODS
     */

    public void freundeAuflisten() {
        System.out.println("\n### FREUNDE");
        System.out.println("Schlüssel | Vorname | Nachname | Geburtsdatum | Adressen");

        for (Freund freund : freunde) {
            StringBuilder str = new StringBuilder();

            str.append(freund.getSchluessel() + " | " + freund.getVorname() + " | " + freund.getNachname() + " | " + freund.getGeburtsdatum() + " | ");
            
            if (!freund.getAdressen().isEmpty()) {
                ArrayList<Adresse> adressen = freund.getAdressen();
                str.append(formatAusgabeAdressen(adressen));
            } 
            else {
                str.append("n/a");
            }

            System.out.println(str.toString());
        }
    }

    public void freundHinzufuegen(Freund addFreund) {
        freunde.add(addFreund);
    }

    public void freundHinzufuegen(int addSchluessel) {
        Freund addFreund = new Freund(addSchluessel); 
        System.out.println("\n### Einen neuen Freundeeintrag anlegen");

        System.out.println("\nVorname eingeben:");
        addFreund.setVorname(scan.nextLine());

        System.out.println("\nNachname eingeben:");
        addFreund.setNachname(scan.nextLine());

        inputValidatedGeburtsdatum(addFreund);
    }

    public void freundAendern() {
        System.out.println("\nBitte Schlüssel des Freundes eingeben, der bearbeitet werden soll:");
        int editSchluessel = inputInt();
        int freundIndex = freundIndexViaSchluessel(editSchluessel);

        if (freundIndex <= 0) {
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
            System.out.println("(4) Adressen ändern: " + formatAusgabeAdressen(adressen));
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
                    System.out.println("Vorname eingeben");
                    freund.setVorname(scan.nextLine());
                    break;
                case 2:
                    System.out.println("Nachname eingeben");
                    freund.setNachname(scan.nextLine());
                    break;
                case 3:
                    freund.setGeburtsdatum(inputValidatedGeburtsdatum(freund));
                    break;
                case 4:
                    freund.setAdressen(adressen.isEmpty() ? freundAdressenAnlegen(adressen) : freundAdresseAendern(adressen));
                    break;
                case 5:
                    freund.setAdressen(freundAdressenAnlegen(adressen));
                    break;
            }
        }
    }

    public void freundLoeschen() {
        System.out.println("\nSchlüssel des Freundes eingeben, der gelöscht werden soll:");
        int removeSchluessel = inputInt();
        freunde.removeIf(freund -> (freund.getSchluessel() == removeSchluessel));
    }

    public void freundSuchen() {
        System.out.println("\nBitte Schlüssel, Vorname oder Nachname eines Freundes eingeben, nachdem gesucht werden soll:");
        String value = scan.nextLine();
        
        freunde.forEach(freund -> {
            if(freund.getVorname().contains(value) || freund.getNachname().contains(value) || Integer.toString(freund.getSchluessel()).equals(value)) {
                System.out.println("Freund gefunden: (" + freund.getSchluessel() + ") " + freund.getVorname() + " " + freund.getNachname());
            }
        });
    }

    public int erzeugeSchluessel() {
        return schluessel++;
    }

    public int zeigeMenue() {
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
}