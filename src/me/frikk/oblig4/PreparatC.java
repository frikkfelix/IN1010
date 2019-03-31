package me.frikk.oblig4;

class PreparatC extends Legemiddel {
    protected int styrke;

    public PreparatC(String navn, double pris, double virkestoff) {
        super(navn, pris, virkestoff);
    }

    @Override
    public String toString() {
        return ("C-PREPARAT\n" + super.toString());
    }
}