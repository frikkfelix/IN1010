package me.frikk.oblig4;

class HvitResept extends Resept {
    public HvitResept(ReseptType reseptType, Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(reseptType, legemiddel, utskrivendeLege, pasient, reit);
        prisAaBetale = legemiddel.hentPris();
    }

    @Override
    public double prisAaBetale() {
        return prisAaBetale;
    }

    @Override
    public String toString() {
        return "Hvit Resept" + super.toString();
    }
}