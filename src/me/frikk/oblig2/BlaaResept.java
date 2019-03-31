package me.frikk.oblig2;

class BlaaResept extends Resept {    
    public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientID, int reit) {
        super(legemiddel, utskrivendeLege, pasientID, reit);
        farge = "Blaa";
        prisAaBetale = legemiddel.hentPris() * 0.25;
    }

    @Override
    public String farge() {
        return farge;
    }

    @Override
    public double prisAaBetale() {
        return prisAaBetale;
    }

}