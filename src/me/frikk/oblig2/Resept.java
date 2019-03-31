package me.frikk.oblig2;

abstract class Resept {
    protected int reseptID;
    protected Legemiddel legemiddel;
    protected Lege utskrivendeLege;
    protected int pasientID;
    protected int reit;
    protected String farge;
    protected double prisAaBetale;
    private static int n = 0;

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientID, int reit) {
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasientID = pasientID;
        this.reit = reit;
        
        reseptID = n;
        n++;
    }

    public int hentID() {
        return reseptID;
    }

    public Legemiddel hentLegemiddel() {
        return legemiddel;
    }

    public Lege hentLege() {
        return utskrivendeLege;
    }

    public int hentPasientID() {
        return pasientID;
    }

    public int hentReit() {
        return reit;
    }

    /**
     * Forsøker å bruke resepten
     * @return true om resepten kunne brukes, ellers false 
     */
    public boolean bruk() {
        if (reit > 0) {
            reit--;
            return true;
        }
        System.out.println("Resepten er brukt opp.");
        return false;
    }
    /**
     * @return String-representasjon for alle Resept-objekter
     */
    @Override
    public String toString() {
        return ("\n" + farge + " resept\n" 
        + legemiddel.hentNavn()
        + "\nUtskrivende lege: " + utskrivendeLege
        + "\nPris: " + prisAaBetale 
        + "\nPasient-ID: " + pasientID
        + "\nResept-ID: " + reseptID
        + "\nReit: " + reit);
    }

    abstract public String farge();
    
    abstract public double prisAaBetale();
}