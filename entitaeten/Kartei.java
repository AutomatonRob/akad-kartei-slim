package entitaeten;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Kartei ist die Hauptklasse welche für die Verwaltung von Freunden dient.
 */
public class Kartei {
    /**
     * Ein dynamisches Feld, welches die Datensätze aller in der Kartei gespeicherten Freunde enthält.  
     */
    private ArrayList<Freund> freunde = new ArrayList<Freund>();

    /**
     * Einzigartiger, unveränderlicher Schlüssel, mit dem eine Datensatz eindeutig identifiziert werden kann.
     */
    private static int schluessel = 1;

    /**
     * Gesamtzahl der Datensätze in der Kartei.
     */
    private int anzahlFreunde = 0;

    /**
     * Instanz von java.util.Scanner zum Einlesen von Benutereingaben.
     */
    private Scanner scan = new Scanner(System.in);

    /**
      * Hilfsfunktion, um eine Liste aus Adressen zu einer Zeichenkette zusammenzusetzen. 
      * @param adressen Dynamisches Feld von Adress-Objekten
      * @return Eine Zeichenkette aller Adressen
      */
    private String formatAusgabeAdressen(ArrayList<Adresse> adressen) {
        int index = 0;
        String[] arrStr = new String[adressen.size()];

        for (Adresse adresse : adressen) {
            arrStr[index] = "(" + ++index + ") " + adresse.getStrasse() + ", " + adresse.getPlz() + " " + adresse.getOrt();
        }

        return String.join(" ", arrStr);
    }

    /**
     * Eingabedialog zum Erfassen des Geburtsdatums im Format 'DD-MM-YYYY'. 
     * Die Eingabe wird auf konsistenz geprüft und ggf. wiederholt abgefragt.
     * @return Ein Geburtsdatum im Format: DD.MM.YYYY
     */
    private String inputValidatedGeburtsdatum() {
        String geburtsdatum = "";

        while(!Freund.validateGeburtsdatum(geburtsdatum)) {
            System.out.println("\nGeburtsdatum eingeben (DD.MM.YYYY):");
            geburtsdatum = scan.nextLine();
        }

        return geburtsdatum;
    }

    /**
     * Eingabedialog zum Erfassen einer Postleitzahl im Format 'NNNNN'. 
     * Die Eingabe wird auf konsistenz geprüft und ggf. wiederholt abgefragt.
     * @return Eine Postleitzahl im Format: NNNNN
     */
    private String inputValidatePostleitzahl() {
        String plz = "";

        while(!Adresse.validatePostleitzahl(plz)) {
            System.out.println("\nFünfstellige Postleitzahl eingeben:");
            plz = scan.nextLine();
        }

        return plz;
    }

    /**
     * Eingabedialog zum Erfassen oder Verändern einer Adresse. 
     * @param addresse Adress-Objekt, welches verändert oder befüllt werden soll
     * @return Adress-Objekt, welches die neuen Daten enhält
     */
    private Adresse freundSetAdresseDialog(Adresse addresse) {
        System.out.println("Strasse und Hausnummer eingeben:");
        addresse.setStrasse(scan.nextLine());
        
        addresse.setPlz(inputValidatePostleitzahl());
        
        System.out.println("Ort eingeben:");
        addresse.setOrt(scan.nextLine());

        return addresse;
    }

    /**
     * Eingabedialog zur Auswahl eines Adressdatensatzes, der geändert werden soll.
     * @param adressen Dynamisches Feld von Adress-Objekten, das bearbeitet werden soll
     * @return Dynamisches Feld von Adress-Objekten
     */
    private ArrayList<Adresse> freundAdresseAendern(ArrayList<Adresse> adressen) {
        int adressId = -1;

        while (adressId < 1 || adressId > adressen.size()) {
            System.out.println("\nWelche Adress-ID soll geändert werden:");
            adressId = inputInt();
        }

        freundSetAdresseDialog(adressen.get(adressId - 1));

        return adressen;
    }

    /**
     * Eingabedialog zur Erzeugung von Adress-Objekten, die zu einer dynamischen Liste von Adressen hinzugefügt werden.
     * @param adressen Leeres oder bereits befülltes Feld von Adress-Objekten
     * @return Dynamisches Feld von Adress-Objekten
     */
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

    /**
     * Eingabedialog für positive natürliche Ganzzahlen mit Konsistenzprüfung.
     * @return Positive natürliche Ganzzahl
     */
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
    
    /**
     * Hilfsfunktion zum Auffinden des Indices eines Freund-Datensatzes anhand von dessen Schlüssel.
     * @param findSchluessel Eingegebener Schlüssel
     * @return Index des Freund-Datensatzes oder -1, falls für den Schlüssel kein Datensatz vorhanden ist
     */
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

    /**
     * Ausgabedialog zur Auflistung aller in der Kartei gespeicherten Freund-Datensätze.
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

    /**
     * Hinzufügen eines Freund-Objektes zur Kartei.
     * @param addFreund Freund-Objekt
     */
    public void freundHinzufuegen(Freund addFreund) {
        freunde.add(addFreund);
    }

    /**
     * Dialog zum Anlegen eines neuen Freund-Objektes.
     */
    public void freundAnlegen() {
        Freund addFreund = new Freund(); 
        System.out.println("\n### Einen neuen Freundeeintrag anlegen");

        System.out.println("\nVorname eingeben:");
        addFreund.setVorname(scan.nextLine());

        System.out.println("\nNachname eingeben:");
        addFreund.setNachname(scan.nextLine());

        addFreund.setGeburtsdatum(inputValidatedGeburtsdatum());

        freunde.add(addFreund);
    }

    /**
     * Interaktiver Dialog zum Editieren eines vorhandenen Freund-Datensatzes.
     */
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
                    freund.setGeburtsdatum(inputValidatedGeburtsdatum());
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

    /**
     * Eingabedialog zum Löschen eines Freund-Datensatzes anhand des Schlüssel.
     */
    public void freundLoeschen() {
        System.out.println("\nSchlüssel des Freundes eingeben, der gelöscht werden soll:");
        int removeSchluessel = inputInt();
        freunde.removeIf(freund -> (freund.getSchluessel() == removeSchluessel));
    }

    /**
     * Eingabedialog zur Suche von Freunden anhand von Schlüssel, Vorname oder Nachname.
     * Die Ausgabe der Treffer erfolgt bei exakter Übereinstimmung des Schlüssel oder teilweiser Übereinstimmung der 
     * eingegebenen Zeichenkette mit Vor- oder Nachname. 
     */
    public void freundSuchen() {
        System.out.println("\nBitte Schlüssel, Vorname oder Nachname eines Freundes eingeben, nachdem gesucht werden soll:");
        String value = scan.nextLine();
        
        freunde.forEach(freund -> {
            if(freund.getVorname().contains(value) || freund.getNachname().contains(value) || Integer.toString(freund.getSchluessel()).equals(value)) {
                System.out.println("Freund gefunden: (" + freund.getSchluessel() + ") " + freund.getVorname() + " " + freund.getNachname());
            }
        });
    }

    /**
     * Basis-Menü. Interaktiver Dialog zur Auswahl einer Aktion.
     * @return Ganzzahl des ausgewählten Menüpunktes
     */
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

    /**
     * Gibt die Anzahl der Freund-Objekte in der Kartei zurück.
     * @return Gesamtzahl der Freunde
     */
    public int getFreundeAnzahl() {
        return freunde.size();
    }
}