package me.frikk.oblig4;

class PResept extends HvitResept {
    /**
     * Konstruktøren setter reit til 3, og setter prisen
    */    
    public PResept(ReseptType reseptType, Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient) {
        super(reseptType, legemiddel, utskrivendeLege, pasient, 3);
        
        if (legemiddel.hentPris() <= 108) {
            prisAaBetale = 0;
        }
        else {
            prisAaBetale = legemiddel.hentPris() - 108;
        }
    }
}