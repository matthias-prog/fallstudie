package backend;

import daten.CIRecord;
import daten.CITyp;
import daten.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Testprogramm {

    private static int currentUser;
    private static int currentCITyp;
    private static Connection con;
    private static Statement stmt;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        hauptprogramm.dbVerbindungAufbauen();
        System.out.println("Was tun? Bitte auswählen: \n");
        System.out.println("Anmelden(a)");
        System.out.println("Benutzer anlegen (b)");
        System.out.println("alle CITypen holen (c)");
        System.out.println("einen CITyp holen (d)");
        System.out.println("alle CIRecords holen (e)");
        System.out.println("neuen CITyp erstellen (f)");
        System.out.println("CITyp löschen (g)");
        System.out.println("neuen CIRecord erstellen (h)");
        System.out.println("CIRecord löschen (i)");
        System.out.println("Aktualisiere CIRecord (j)");
        System.out.println("Benutzer löschen (l)");
        System.out.println("Benutzerrecht verändern (m)");
        System.out.println("Passwort ändern (n)");


        String was = scanner.next();
        char wasTun = was.charAt(0);


        while (wasTun != 'q') {


            switch (wasTun) {
                case 'a': //funktioniert

                    System.out.println("Benutzernamen eingeben: ");
                    String benutzername = scanner.next();

                    System.out.println("Passwort eingeben: ");
                    String passwort = scanner.next();

                    daten.Message message = hauptprogramm.versucheLogin(benutzername, passwort);
                    System.out.println("Ergebnis des Logins: " + message.getNachricht());

                    break;

                case 'b': //funktioniert

                    System.out.println("Neuen Benutzer anlegen: Benutzername");
                    String benutzernameNeu = scanner.next();

                    System.out.println("Neuen Benutzer anlegen: Passwort");
                    String passwortNeu = scanner.next();

                    System.out.println("Neuen Benutzer anlegen: Admin? (True/False)");
                    Boolean IstAdmin = scanner.nextBoolean();

                    daten.Message message0 = hauptprogramm.benutzerAnlegen(benutzernameNeu, passwortNeu, IstAdmin);

                    System.out.println(message0.getNachricht());

                    break;

                case 'c': //funktioniert

                    ArrayList<CITyp> listeCITypen = hauptprogramm.holeAlleCITypen();
                    System.out.println(listeCITypen.get(0).getCItypName() + " CITyp an der Stelle 0 ");

                    break;

                case 'd': //funktioniert

                    System.out.println("CITYP eingeben: ");
                    String citypEingabe = scanner.next();

                    CITyp cityp = hauptprogramm.holeCITyp(citypEingabe);

                    System.out.println("Cityp: " + citypEingabe + " " + cityp.getCItypID());

                    break;

                case 'e': //funktioniert
                    System.out.println("CITYP eingeben: ");
                    String citypEingabe2 = scanner.next();

                    ArrayList<CIRecord> cirecord = hauptprogramm.holeAlleRecordsVonCITyp(citypEingabe2);

                    System.out.println("CIRecord an der Stelle 1 von CItyp " + cirecord.get(1).getAttribute().get(0));

                    break;

                case 'f': //funktioniert

                    System.out.println("Neuen CITyp erstellen: Name eingeben ");
                    String citypName = scanner.next();

                    System.out.println("Neuen CITyp erstellen: Attribute in verschiedene Zeilen eingeben ");

                    ArrayList<String> attribute = new ArrayList<>();
                    String line = "";
                    while (scanner.hasNextLine() && !line.equals("q")) {

                        line = scanner.next();
                        if (!line.equals("q")) {
                            attribute.add(line);
                        }

                    }

                    daten.Message message2 = hauptprogramm.erstelleCITyp(citypName, attribute);

                    System.out.println("Erstellen des CITypen: " + message2.getNachricht());

                    break;

                case 'g': //funktioniert
                    System.out.println("CITyp löschen: Name eingeben ");
                    String citypName2 = scanner.next();

                    daten.Message message4 = hauptprogramm.loescheCITyp(citypName2);

                    System.out.println(message4.getNachricht());

                    break;

                case 'h': //funktioniert

                    System.out.println("CiRecords für welchen CITypen erstellen? Name eingeben:");
                    String citypName3 = scanner.next();

                    System.out.println("Attribute in verschiedene Zeilen eingeben ");
                    ArrayList<String> attributeCIRecord = new ArrayList<>();

                    String line2 = "";
                    while (scanner.hasNextLine() && !line2.equals("q")) {

                        line2 = scanner.next();
                        if (!line2.equals("q")) {
                            attributeCIRecord.add(line2);
                        }

                    }
                    daten.Message message3 = hauptprogramm.erstelleCIRecord(citypName3, attributeCIRecord);

                    System.out.println("Erstellen des CIRecords: " + message3.getNachricht());

                    break;

                case ('i'): //funktioniert
                    System.out.println("CIRecord löschen... CITyp des CIRecords eingeben: ");
                    String citypName4 = scanner.next();

                    System.out.println("CIRecord löschen... CI RecordID eingeben: ");

                    int recordID = scanner.nextInt();

                    daten.Message message5 = hauptprogramm.loescheCIRecord(citypName4, recordID);

                    System.out.println(message5.getNachricht());

                    break;

                case 'j': //funktioniert

                    System.out.println("CIRecord aktualisieren: CITyp eingeben");
                    String citypName5 = scanner.next();

                    System.out.println("CIRecord aktualisieren: CIRecordID eingeben");
                    int ciRecordID2 = scanner.nextInt();

                    System.out.println("CIRecord aktualisieren: Attribute in verschiedene Zeilen eingeben ");
                    ArrayList<String> attributeCIRecord2 = new ArrayList<>();

                    String line3 = "";
                    while (scanner.hasNextLine() && !line3.equals("q")) {

                        line3 = scanner.next();
                        if (!line3.equals("q")) {
                            attributeCIRecord2.add(line3);
                        }

                    }
                    daten.Message message6 = hauptprogramm.aktualisiereCIRecord(citypName5, ciRecordID2, attributeCIRecord2);

                    System.out.println("Aktualisieren des CIRecords: " + message6.getNachricht());

                    break;

                case 'l': //funktioniert

                    System.out.println("Benutzer löschen: CITypID eingeben");
                    int benutzerID = scanner.nextInt();

                    daten.Message message7 = hauptprogramm.benutzerLoeschen(benutzerID);

                    System.out.println("Löschen des Benutzers: " + message7.getNachricht());

                    break;

                case 'm': //funktioniert

                    System.out.println("Benutzerrecht verändern: CITypID eingeben");
                    int benutzerID2 = scanner.nextInt();

                    System.out.println("Admin? (True/False)");
                    Boolean IsAdmin = scanner.nextBoolean();

                    daten.Message message8 = hauptprogramm.benutzerRechtVeraendern(benutzerID2, IsAdmin);

                    System.out.println("Löschen des Benutzers: " + message8.getNachricht());

                    break;

                case 'n': //funktioniert
                    System.out.println("Benutzerpasswort verändern: CITypID eingeben");
                    int benutzerID3 = scanner.nextInt();

                    System.out.println("Neues Passwort: ");
                    String neuesPasswort = scanner.next();

                    daten.Message message9 = hauptprogramm.benutzerPasswortVeraendern(benutzerID3, neuesPasswort);

                    System.out.println("Löschen des Benutzers: " + message9.getNachricht());

                    break;

            }
            String was2 = scanner.next();
            wasTun = was2.charAt(0);

        }
        scanner.close();

    }
}