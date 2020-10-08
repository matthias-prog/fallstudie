package daten;

import java.util.List;

public class CIRecord {
    private int ciRecordID;
    private List<String> attribute;

    public CIRecord(int ciRecordID, List<String> attribute) {
        this.ciRecordID = ciRecordID;
        this.attribute = attribute;
    }

    public int getCIRecordID() {
        return ciRecordID;
    }

    public void setCIRecordID(int ciRecordID) {
        this.ciRecordID = ciRecordID;
    }


    public List<String> getAttribute() {
        return attribute;
    }

    public void setAttribute(List<String> attribute) {
        this.attribute = attribute;
    }
}


