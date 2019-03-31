package me.frikk.oblig4;

abstract class Resept {
    protected Legemiddel legemiddel;
    protected Lege utskrivendeLege;
    protected Pasient pasient;
    protected int pasientID;
    protected int reit;
    protected ReseptType reseptType;
    protected double prisAaBetale;

    protected int reseptID;
    private static int n = 0;

    public Resept(ReseptType reseptType, Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        this.reseptType = reseptType;
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasient = pasient;
        this.pasientID = pasient.hentPasientID();
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
        System.out.println("Kunne ikke bruke resept paa " + legemiddel.hentNavn() + " (ingen gjenvaerende reit).");
        return false;
    }
    /**
     * @return String-representasjon for alle Resept-objekter
     */
    @Override
    public String toString() {
        return ("\nLegemiddel: " + legemiddel.hentNavn()
        + "\nUtskrivende lege: " + utskrivendeLege.hentNavn()
        + "\nPris: " + prisAaBetale 
        + "\nPasient-ID: " + pasientID
        + "\nResept-ID: " + reseptID
        + "\nReit: " + reit);
    }
    
    abstract public double prisAaBetale();
}