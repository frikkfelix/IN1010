package me.frikk.oblig4;

class Spesialist extends Lege implements GodkjenningsFritak {
    protected int kontrollID;

    public Spesialist(String navn, int kontrollID) {
        super(navn);
        this.kontrollID = kontrollID;
    }

    @Override
    public String toString() {
        return (super.toString() + " | Kontroll-ID: " + kontrollID);
    }

    public int hentKontrollID() {
        return kontrollID;
    }
}