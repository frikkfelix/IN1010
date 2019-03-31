package me.frikk.oblig2;

class MilitaerResept extends HvitResept {
    public MilitaerResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientID, int reit) {
        super(legemiddel, utskrivendeLege, pasientID, reit);
        prisAaBetale = 0;
    }
}