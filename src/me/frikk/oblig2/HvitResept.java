package me.frikk.oblig2;

class HvitResept extends Resept {
    public HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientID, int reit) {
        super(legemiddel, utskrivendeLege, pasientID, reit);
        farge = "Hvit";
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