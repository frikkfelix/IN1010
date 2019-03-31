package me.frikk.oblig4;

class BlaaResept extends Resept {    
    public BlaaResept(ReseptType reseptType, Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(reseptType, legemiddel, utskrivendeLege, pasient, reit);
        prisAaBetale = legemiddel.hentPris() * 0.25;
    }

    @Override
    public double prisAaBetale() {
        return prisAaBetale;
    }

    @Override
    public String toString() {
        return "Blå Resept" + super.toString();
    }

}