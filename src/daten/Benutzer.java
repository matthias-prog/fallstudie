package daten;

/**
 * Hier werden die Benutzer und ihre Rechte gespeichert
 */
public class Benutzer {
    private int benutzerID;
    private String benutzername;
    private String passwort;
    private boolean istAdmin;

    public Benutzer(int benutzerID, String benutzername, String passwort, boolean istAdmin) {
        this.benutzerID = benutzerID;
        this.benutzername = benutzername;
        this.passwort = passwort;
        this.istAdmin = istAdmin;
    }

    public int getBenutzerID() {
        return benutzerID;
    }

    public void setBenutzerID(int benutzerID) {
        this.benutzerID = benutzerID;
    }

    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public String getIstAdmin() {
    	String istAdminStr = Boolean.toString(istAdmin);
        return istAdminStr;
    }

    public void setIstAdmin(boolean istAdmin) {
        this.istAdmin = istAdmin;
    }
 
}
