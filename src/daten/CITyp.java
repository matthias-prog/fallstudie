package daten;

import java.util.List;

public class CITyp {
    private int ciTypID;
    private String ciTypName;
    private List<String> attributNamen;

    public CITyp(int ciTypID, String ciTypName, List<String> attributNamen) {
        this.ciTypID = ciTypID;
        this.attributNamen = attributNamen;
        this.ciTypName = ciTypName;
    }

    public int getCItypID() {
        return ciTypID;
    }

    public void setCItypID(int ciTypID) {
        this.ciTypID = ciTypID;
    }

    public List<String> getAttributnamen() {
        return attributNamen;
    }

    public void setAttribute(List<String> attributNamen) {
        this.attributNamen = attributNamen;
    }

    public String getCItypName() {
        return ciTypName;
    }

    public void setCItypName(String ciTypName) {
        this.ciTypName = ciTypName;
    }
}


