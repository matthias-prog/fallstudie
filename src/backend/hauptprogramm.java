package backend;

import daten.Benutzer;
import daten.CIRecord;
import daten.CITyp;
import daten.Message;

import java.sql.*;
import java.util.ArrayList;

public class hauptprogramm {
    private static int currentUser;
    private static int currentCITyp;
    private static Connection con;
    private static Statement stmt;
    private static Statement stmt2;

    /**
     * stellt eine Verbindung zur Datenbank her
     */
    public static void dbVerbindungAufbauen() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fallstudie", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            stmt = con.createStatement();
            stmt2 = con.createStatement();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        try // Treiber für mySQL laden
        {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Treiber gefunden\n--------------------------------");
        } //try
        catch (ClassNotFoundException e) {
            System.out.println("Treiber NICHT gefunden\n--------------------------------");
        } //catch
    }

    /**
     * baut die DB-Verbindung vorzeitig ab
     */
    public static void dbVerbindungAbbauen() {
        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Nutzer wird eingeloggt indem überprüft wird,
     * ob die Benutzername-Passwort Kombination in der Datenbank vorhanden ist
     *
     * @param benutzername zu überprüfender Nutzername
     * @param passwort     zu überprüfendes Passwort
     * @return Message-Objekt mit Fehlermeldung und Erfolg
     */
    public static Message versucheLogin(String benutzername, String passwort) {
        String abfrage = "SELECT * from Benutzer where Benutzername='" + benutzername + "' and Passwort='" + passwort + "'";
        ResultSet rs;

        //SQL-Abfrage wird abgeschickt
        try {
            rs = stmt.executeQuery(abfrage);
        } catch (SQLException e) {
            return new Message(false, "Datenbank-Abfrage fehlgeschlagen");
        }

        //hier wird überprüft ob das ResultSet min. 1 Zeile besitzt (sie sollte nicht mehr als eine haben,
        // da benutzername unique ist). Hat das ResultSet keine Zeile (es gibt also die Benutzername-Passwort Kombination
        // nicht in der Datenbank) kommt man in den Catch-Block
        try {
            rs.next();
            currentUser = rs.getInt(1);
            return new Message(true, "Richtig! Erfolgreich angemeldet");
        } catch (SQLException e) {
            return new Message(false, "Benutzer oder Passwort falsch");
        }
    }

    /**
     * Nutzer wird auf Adminstatus ueberprueft
     *
     * @param benutzername zu ueberpruefender Benutzername
     * @return boolean Wert (true = Admin, false = kein Admin)
     */
    public static boolean pruefeAdmin (String benutzername) {
    	 String abfrage = "SELECT IsAdmin from Benutzer where Benutzername='" + benutzername + "'";
         ResultSet rs = null;
         boolean istAdmin = false;

         //SQL-Abfrage wird abgeschickt
         try {
             rs = stmt.executeQuery(abfrage);
         } catch (SQLException e) {
        	 	System.out.println(e);
         }
			try {
				 rs.next();
				istAdmin = rs.getBoolean("IsAdmin");
			} catch (SQLException e) {
				e.printStackTrace();
			}
          return istAdmin;
    }
    
    /**
     * momentan angemeldeter Nutzer wird ausgeloggt, indem seine BenutzerID vergessen wird
     */
    public static void logout() {
        currentUser = 0;
    }


    /**
     * Holt alle existierenden CITypen und gibt sie in einer Liste aus
     *
     * @return eine Liste mit CITypen
     */
    public static ArrayList<CITyp> holeAlleCITypen() {
        String abfrage = "SELECT * from cityp;";
        ResultSet rs;
        ResultSetMetaData rsmd;
        ArrayList<CITyp> listeCITypen = new ArrayList<CITyp>();


        //SQL-Abfrage wird abgeschickt
        try {
            rs = stmt.executeQuery(abfrage);
            rsmd =rs.getMetaData();
        } catch (SQLException e) {
            return listeCITypen;
        }

        try {
            while (rs.next()) {
                int id = rs.getInt("TypId");
                String name = rs.getString("Typname");
                int index = 3;
                ArrayList<String> attribute = new ArrayList<String>();

                while (index <= rsmd.getColumnCount()) {
                    attribute.add(rs.getString(index));
                    index++;
                }
                CITyp cityp = new CITyp(id, name, attribute);
                listeCITypen.add(cityp);
            }

        } catch (SQLException e) {
        	e.printStackTrace();
            return listeCITypen;
        }
        return listeCITypen;
    }

    /**
     * Holt einen bestimmten CITyp
     *
     * @param CITyp CITyp, der abgefragt wird
     * @return eine Instanz von CITyp
     */
    public static CITyp holeCITyp(String CITyp) {
        String abfrage = "SELECT * from cityp where typname = '" + CITyp + "';";
        ResultSet rs;
        ResultSetMetaData rsmd;
        ArrayList<String> attribute = new ArrayList<String>();

        //SQL-Abfrage wird abgeschickt
        try {
            rs = stmt.executeQuery(abfrage);
            rsmd = rs.getMetaData();
        } catch (SQLException e) {
            return null;
        }

        try {
            rs.next();
            int id = rs.getInt("TypId");
            String name = rs.getString("Typname");
            int index = 3;

            while (index <= rsmd.getColumnCount()) {
                attribute.add(rs.getString(index));
                index++;
            }

            CITyp cityp = new CITyp(id, name, attribute);

            return cityp;
        } catch (SQLException e) {
        	e.printStackTrace();
            return null;
        }

    }

    /**
     * Holt alle CIRecords eines bereits bestehenden CITyps
     *
     * @param CITyp CITyp zu dem die CIRecords gehören
     * @return eine Liste aller CIRecords des CITyps
     */
    public static ArrayList<CIRecord> holeAlleRecordsVonCITyp(String CITyp) {
        String abfrage = "SELECT * from " + CITyp;
        ResultSet rs;
        ResultSetMetaData rsmd;
        ArrayList<CIRecord> listeCIRecords = new ArrayList<CIRecord>();

        //SQL-Abfrage wird abgeschickt
        try {
            rs = stmt.executeQuery(abfrage);
            rsmd = rs.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
            return listeCIRecords;
        }

        try {
            while (rs.next()) {
                int id = rs.getInt("RecordID");
                ArrayList<String> attribute = new ArrayList<String>();
                int index = 2;
                while (index <= rsmd.getColumnCount()) {
                    attribute.add(rs.getString(index));
                    index++;
                }

                CIRecord cirecord = new CIRecord(id, attribute);
                listeCIRecords.add(cirecord);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return listeCIRecords;
        }
        return listeCIRecords;
    }

    /**
     * Erstellt einen neuen CITyp und eine DB Tabelle für die zugehörigen CIRecords des CITypen
     *
     * @param CITypName CITypName des neuen CITyps
     * @param Attribute Alle Attribute, die der neue CITyp beinhaltet
     * @return ein Message-Objekt mit Fehlermeldung und Erfolg
     */

    public static Message erstelleCITyp(String CITypName, ArrayList<String> Attribute) {

        PreparedStatement neuerCITyp;
        String abfrage = "SELECT * from cityp where typname = '" + CITypName + "';";
        ResultSet rs;
        
        //SQL-Abfrage wird abgeschickt
        try {
            rs = stmt.executeQuery(abfrage);
            if (rs.next()) {
            	return new Message(false, "Es existiert bereits ein CITyp mit diesem Namen.");
            }
        } catch (SQLException e) {
        }

        try {
            neuerCITyp = con.prepareStatement("INSERT into cityp (TypName, Attribut1, Attribut2, Attribut3, Attribut4, Attribut5, Attribut6,Attribut7,Attribut8,Attribut9,Attribut10,Attribut11,Attribut12,Attribut13,Attribut14,Attribut15)" +
                    "values ('" + CITypName + "', ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
        } catch (SQLException e) {
            return new Message(false, "Fehler beim Erstellen des neuen CITypen.");
        }

        //alle Strings aus der Liste werden dem SQL-Statement angehängt
        try {
            for (int i = 0; i < Attribute.size(); i++) {
                neuerCITyp.setString(i + 1, Attribute.get(i));
            }
            for (int i = Attribute.size(); i < 15; i++) {
                neuerCITyp.setString(i + 1, null);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Message(false, "Fehler beim Hinzufügen der Attribute des CITypen.");
        }

        //SQL-Insert wird ausgeführt
        try {
            neuerCITyp.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return new Message(false, "Fehler beim Hinzufügen des CITypen aufgetreten!");
        }

        StringBuilder neueTabelle = new StringBuilder("Create table " + CITypName + "(RecordID integer not null AUTO_INCREMENT, ");

        //alle Strings aus der Liste werden dem SQL-Statement angehängt
        for (String s : Attribute) {
            neueTabelle.append(s);
            neueTabelle.append(" char(20)");
            neueTabelle.append(","); //Komma um die Attribute zu trennen
        }

        neueTabelle.append("CONSTRAINT rule1 PRIMARY KEY(RecordID)");
        neueTabelle.append(");");

        //SQL-Insert wird ausgeführt
        try {
            stmt.executeUpdate(neueTabelle.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            return new Message(false, "Fehler beim Erstellen der Tabelle der CIRecords aufgetreten!");
        }
        return new Message(true, "CITyp erfolgreich hinzugefügt und Tabelle für die CIRecords erfolgreich erstellt.");
    }


    /**
     * Erstellt einen neuen CIRecord eines bereits bestehenden CITyps
     *
     * @param CITyp     CITyp zu dem der CIRecord gehört
     * @param Attribute Alle Attribute die der neue CIRecord belegt
     * @return ein Message-Objekt mit Fehlermeldung und Erfolg
     */
    public static Message erstelleCIRecord(String CITyp, ArrayList<String> Attribute) {

        //Spaltennamen werden aus der CI Typen Tabelle geladen
        String citypabfrage = "Select * from cityp where TypName='" + CITyp + "';";
        ResultSet rs;
        try {
            rs = stmt.executeQuery(citypabfrage);
        } catch (SQLException e) {
            e.printStackTrace();
            return new Message(false, "Fehler bei der Abfrage des CITyps aufgetreten!");
        }

        ArrayList<String> attributeVonCITyp = new ArrayList<String>();
        try {
            rs.next();
            for (int i = 3; i < 18; i++) {
                if (rs.getString(i) != null) {
                    attributeVonCITyp.add(rs.getString(i));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        StringBuilder columns = new StringBuilder("(");
        for (String s : attributeVonCITyp) {
            columns.append(s);
            columns.append(",");
        }
        columns = new StringBuilder(columns.toString().replaceAll(".$", ""));
        columns.append(")");

        //Hier wird statt einem normalen String StringBuilder verwendet, da dies schneller sein sollte
        StringBuilder insert = new StringBuilder("INSERT into " + CITyp + columns + " values (");

        //alle Strings aus der Liste werden dem SQL-Statement angehängt
        for (String s : Attribute) {
            insert.append("'");
            insert.append(s);
            insert.append("'");
            insert.append(","); //Komma um die Attribute zu trennen
        }

        //ACHTUNG: Regular Expression ersetzt hier das letzte Zeichen das mit der foreach-Schleife zu viel hinzugefügt
        // wurde mit nichts. "." steht für alle Zeichen "$" für das Ende (war die einfachste Möglichkeit dies zu umzusetzen)
        insert = new StringBuilder(insert.toString().replaceAll(".$", ""));
        insert.append(");");

        //SQL-Insert wird ausgeführt
        try {
            stmt.executeUpdate(insert.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            return new Message(false, "Fehler beim Erstellen aufgetreten!");
        }

        return new Message(true, "CIRecord erfolgreich hinzugefügt.");
    }

    /**
     * löscht den spezifizierten CI Typ. Dabei wird überprüft ob die entsprechende CI Record Tabelle wirklich leer ist.
     *
     * @param CITyp der zu löschende CI Typ
     * @return Message-Objekt mit Fehlermeldung und Erfolg
     * @
     */
    public static Message loescheCITyp(String CITyp) {

        String delete = "DELETE from cityp where Typname='" + CITyp + "'";
        String dropTable = "drop table " + CITyp;
        String ueberpruefung = "Select * from " + CITyp; //dient der Überprüfung, ob die CI Record Tabelle leer ist
        ResultSet rs;

        //TODO: brauchen wir eine Überprüfung ob der CITyp überhaupt existiert?
        //hier wird zuerst überprüft ob die dem CITyp zugehörige CIRecord-Tabelle wirklich leer ist. Im Fehlerfall ist sie leer.
        try {

            rs = stmt.executeQuery(ueberpruefung);
            rs.next();
            rs.getString(2); //hier tritt die SQLException auf wenn die Anfrage keine Datensätze geliefert hat --> wollen wir haben
            return new Message(false, "Loeschen blockiert: Es existieren noch CIRecords von diesem Typ");

        } catch (SQLException e1) {

            //SQL-Delete wird ausgeführt und die dem CITyp zugehörige CIRecord-Tabelle wird gedroppt
            try {
                stmt.executeUpdate(delete);
                stmt.execute(dropTable);
                return new Message(true, "Erfolgreich geloescht!");

            } catch (SQLException e2) {
                return new Message(false, "Fehler beim Loeschen aufgetreten!");
            }
        }
    }

    /**
     * löscht einen spezifizierten CI Record
     *
     * @param CITyp    CITyp zu dem der CI Record gehört
     * @param RecordID ID des zu löschenden Records
     * @return Message-Objekt mit Fehlermeldung und Erfolg
     */
    public static Message loescheCIRecord(String CITyp, int RecordID) {
        String delete = "DELETE from " + CITyp + " where RecordID=" + RecordID;

        //SQL-Delete wird ausgeführt
        try {
            stmt.executeUpdate(delete);
            return new Message(true, "Erfolgreich gelöscht");
        } catch (SQLException e) {
            e.printStackTrace();
            return new Message(false, "Fehler beim Löschen aufgetreten!");
        }
    }

    /**
     * aktualisiert den angegeben CI Record mit den angegebenen Daten
     *
     * @param CITyp         zum Record passender CI Typ
     * @param RecordID      ID des zu ändernden Records
     * @param neueAttribute Liste, die angibt wie der Record nach dem Update aussehen soll
     * @return Message-Objekt mit Fehlermeldung und Erfolg
     */
    public static Message aktualisiereCIRecord(String CITyp, int RecordID, ArrayList<String> neueAttribute) {
        String abfrageRecord = "Select * from " + CITyp + " where RecordID=" + RecordID;
        String abfrageCITyp = "Select * from CITyp where Typname='" + CITyp+"'";
        ResultSet rs1;
        ResultSet rs2;
        ResultSetMetaData rsmd1;
        ResultSetMetaData rsmd2;
        CIRecord alterSatz;
        daten.CITyp passenderTyp;

        //SQL-Abfragen werden abgeschickt
        try {
            rs1 = stmt.executeQuery(abfrageRecord);
            rsmd1 = rs1.getMetaData();
            rs2 = stmt2.executeQuery(abfrageCITyp);
            rsmd2 = rs2.getMetaData();
        } catch (SQLException e) {
        	e.printStackTrace();
            return new Message(false, "Datenbank-Abfrage fehlgeschlagen");
        }

        //ruft den momentanen Stand des Records ab
        try {
            rs1.next();
            ArrayList<String> alteAttribute = new ArrayList<String>();
            int momRecordID = rs1.getInt(1);
            int index = 2;
            while (index <= rsmd1.getColumnCount()) {
                alteAttribute.add(rs1.getString(index));
                index++;
            }
            alterSatz = new CIRecord(momRecordID, alteAttribute);
        } catch (SQLException e) {
        	e.printStackTrace();
            return new Message(false, "Fehler bei DB-Abruf");
        }

        //ruft den passenden CITypen ab
        try {
            rs2.next();
            ArrayList<String> spaltennamen = new ArrayList<String>();
            int momRecordID = rs1.getInt(1);
            String typname = rs1.getString(2);
            int index = 3;
            while (index <= rsmd2.getColumnCount()) {
                spaltennamen.add(rs2.getString(index));
                index++;
            }
            passenderTyp = new CITyp(momRecordID, typname, spaltennamen);
        } catch (SQLException e) {
        	e.printStackTrace();
            return new Message(false, "Fehler bei DB-Abruf");
        }

        //alle Spalten des Records werden überprüft
        for (int i = 0; i < alterSatz.getAttribute().size(); i++) {

            //Änderung entdeckt: entsprechende Attribute des geladenen Records stimmen nicht mit denen der Parameter überein
            if (!alterSatz.getAttribute().get(i).equals(neueAttribute.get(i))) {

                //entsprechende Felder werden in das SQL-Update eingesetzt
                String update = "update " + CITyp + " set " + passenderTyp.getAttributnamen().get(i) + "='" + neueAttribute.get(i) + "' where RecordID=" + RecordID;
                try {
                    stmt.executeUpdate(update);
                } catch (SQLException e) {
                	e.printStackTrace();
                    return new Message(false, "SQL-Update fehlgeschlagen");
                }
            }
        }

        return new Message(true, "Update erfolgreich erledigt");
    }

    /**
     * Aktualisiert den angegebenen CI Record indem der Record gelöscht wird und ein neuer mit den gelieferten Attributen erstellt wird
     *
     * @param CITyp     CITyp zu dem der zu aktualisierende Record gehört
     * @param RecordID  ID des zu aktualisierenden Records
     * @param Attribute Die Liste der Attribute des Records mit denen er aktualisiert werden soll
     * @return Message-Objekt mit Fehlermeldung und Erfolg
     */
    public static Message aktualisiereCIRecordHolzhammer(String CITyp, int RecordID, ArrayList<String> Attribute) {
        //zur Datensicherheit wird erst der neue Record erstellt bevor er gelöscht wird
        Message fromInsert = erstelleCIRecord(CITyp, Attribute);
        if (!fromInsert.isErfolg()) {
            return fromInsert; //vorzeitiger Return falls das Erstellen nicht funktioniert
        } else {
            return loescheCIRecord(CITyp, RecordID);
        }
    }


    /**
     * Erstellt einen neuen Benutzer
     *
     * @param benutzername benutzername des Users
     * @param passwort     passwort des Users
     * @param istAdmin     Rechtelevel des Users
     * @return ein Message-Objekt mit Fehlermeldung und Erfolg
     */
    public static Message benutzerAnlegen(String benutzername, String passwort, boolean istAdmin) {

        String insert = ("insert into Benutzer (Benutzername, Passwort, IsAdmin) values ('" + benutzername + "', '" + passwort + "', " + istAdmin + ");");

        //SQL-Insert wird ausgeführt
        try {
            stmt.executeUpdate(insert.toString());
        } catch (SQLException e) {
            return new Message(false, "Fehler beim Erstellen de Benutzers aufgetreten!");
        }

        return new Message(true, "Benutzer erfolgreich hinzugefügt.");
    }

    /* hole alle Benutzer
     * 
     */
    public static ArrayList<daten.Benutzer> holeAlleBenutzer() {
        String abfrage = "SELECT * from Benutzer;";
        ResultSet rs;
        ResultSetMetaData rsmd;
        ArrayList<daten.Benutzer> listeBenutzer = new ArrayList<daten.Benutzer>();


        //SQL-Abfrage wird abgeschickt
        try {
            rs = stmt.executeQuery(abfrage);
            rsmd =rs.getMetaData();
        } catch (SQLException e) {
            return listeBenutzer;
        }

        try {
            while (rs.next()) {
                int id = rs.getInt("benutzerId");
                String name = rs.getString("Benutzername");
                String passwort = rs.getString("Passwort");
                boolean isAdmin = rs.getBoolean("isAdmin");
                int index = 3;
                ArrayList<String> attribute = new ArrayList<String>();

                while (index <= rsmd.getColumnCount()) {
                    attribute.add(rs.getString(index));
                    index++;
                }
                Benutzer benutzer = new Benutzer(id, name, passwort, isAdmin);
                listeBenutzer.add(benutzer);
            }

        } catch (SQLException e) {
        	e.printStackTrace();
            return listeBenutzer;
        }
        return listeBenutzer;
    }
    
    
    
    /**
     * löscht einen benutzer
     *
     * @param benutzerID BenutzerID des Users
     * @return Message-Objekt mit Fehlermeldung und Erfolg
     */
    public static Message benutzerLoeschen(int benutzerID) {

        String delete = ("delete from Benutzer where BenutzerID =" + benutzerID + ";");

        //SQL-Delete wird ausgeführt
        try {
            stmt.executeUpdate(delete);
            return new Message(true, "Erfolgreich gelöscht");
        } catch (SQLException e) {
            return new Message(false, "Fehler beim Löschen aufgetreten!");
        }
    }

    /**
     * aktualisiert das Passwort eines Users
     *
     * @param benutzerID BenutzerID des Users
     * @param passwort   neues Passwort des Users
     * @return Message-Objekt mit Fehlermeldung und Erfolg
     */
    public static Message benutzerPasswortVeraendern(int benutzerID, String passwort) {

        String update = "update Benutzer set Passwort ='" + passwort + "' where BenutzerID=" + benutzerID;

        //SQL-Update wird ausgeführt
        try {
            stmt.executeUpdate(update);
        } catch (SQLException e) {
            return new Message(false, "SQL-Update fehlgeschlagen");
        }

        return new Message(true, "Update erfolgreich erledigt");
    }

    /**
     * aktualisiert das Rechtelevel eines Users
     *
     * @param benutzerID BenutzerID des Users
     * @param istAdmin   neues Rechtelevel des Users
     * @return Message-Objekt mit Fehlermeldung und Erfolg
     */
    public static Message benutzerRechtVeraendern(int benutzerID, boolean istAdmin) {

        String update = "update Benutzer set isAdmin =" + istAdmin + " where BenutzerID=" + benutzerID;

        //SQL-Update wird ausgeführt
        try {
            stmt.executeUpdate(update);
        } catch (SQLException e) {
            return new Message(false, "SQL-Update fehlgeschlagen");
        }

        return new Message(true, "Update erfolgreich erledigt");
    }
    
    /**
     * Holt Anzahl der CIRecords eines bestimmten CITyps
     *
     * @param CITyp CITyp zu dem die CIRecords gehören
     * @return anzahl der Records eines Typs
     */
    public static int zeigeAnzahlRecords(String CITyp) {
        String abfrage = "SELECT * from " + CITyp;
        ResultSet rs;
        int numberOfRows = 0;
        
        //SQL-Abfrage wird abgeschickt
        try {
            rs = stmt.executeQuery(abfrage);
            rs.last();
            numberOfRows = rs.getRow();
        } catch (SQLException e) {
            e.printStackTrace();
            return numberOfRows;
        }
    
        return numberOfRows;
    }
    
    /**
     * Holt Anzahl der CITypen
     *
     * @return anzahl der CITypen
     */
    public static int zeigeAnzahlTypen() {
        String abfrage = "SELECT * from cityp";
        ResultSet rs;
        int numberOfRows = 0;
        
        //SQL-Abfrage wird abgeschickt
        try {
            rs = stmt.executeQuery(abfrage);
            rs.last();
            numberOfRows = rs.getRow();
        } catch (SQLException e) {
            e.printStackTrace();
            return numberOfRows;
        }
        return numberOfRows;
    }
}