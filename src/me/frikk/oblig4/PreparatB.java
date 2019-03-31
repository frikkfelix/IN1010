package me.frikk.oblig4;

class PreparatB extends Legemiddel {
    protected int styrke;

    public PreparatB(String navn, double pris, double virkestoff, int styrke) {
        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }
    /**
     * Utvider Legemiddel toString med vanedannende styrke
     */
    @Override
    public String toString() {
        return ("B-PREPARAT\n" + super.toString() + "\nVanedannende styrke: " + styrke);
    }

    public int hentVanedannendeStyrke() {
        return styrke;
    }
}