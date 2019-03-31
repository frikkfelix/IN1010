package me.frikk.oblig4;

class MilitaerResept extends HvitResept {
    public MilitaerResept(ReseptType reseptType, Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(reseptType, legemiddel, utskrivendeLege, pasient, reit);
        prisAaBetale = 0;
    }
}