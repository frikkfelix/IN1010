package me.frikk.oblig2;

class PResept extends HvitResept {
    /**
     * Konstruktøren setter reit til 3, og setter prisen
    */    
    public PResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientID) {
        super(legemiddel, utskrivendeLege, pasientID, 3);
        
        if (legemiddel.hentPris() <= 108) {
            prisAaBetale = 0;
        }
        else {
            prisAaBetale = legemiddel.hentPris() - 108;
        }
    }
}