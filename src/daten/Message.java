package daten;

public class Message {
    private boolean erfolg;
    private String nachricht;

    public Message(boolean erfolg, String nachricht) {
        this.erfolg = erfolg;
        this.nachricht = nachricht;
    }

    public boolean isErfolg() {
        return erfolg;
    }

    public void setErfolg(boolean erfolg) {
        this.erfolg = erfolg;
    }

    public String getNachricht() {
        return nachricht;
    }

    public void setNachricht(String nachricht) {
        this.nachricht = nachricht;
    }
}
