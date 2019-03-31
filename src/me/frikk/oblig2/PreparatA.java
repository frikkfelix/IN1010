package me.frikk.oblig2;

class PreparatA extends Legemiddel {
    protected int styrke;

    public PreparatA(String navn, double pris, double virkestoff, int styrke) {
        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }
    /**
     * Utvider Legemiddel toString med narkotisk styrke
     */
    @Override
    public String toString() {
        return ("A-PPREPARAT\n" + super.toString() + "Narkotisk styrke: " + styrke);
    }

    public int hentNarkotiskStyrke() {
        return styrke;
    }
}