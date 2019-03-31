package me.frikk.oblig2;

class Lege {
    protected String navn;

    public Lege(String navn) {
        this.navn = navn;
    }

    public String hentNavn() {
        return navn;
    }

    @Override
    public String toString() {
        return ("Navn: " + navn);
    }

    /**
     * Forsøker å skrive en resept
     * Dersom legemiddel er et objekt av typen PreparatA,
     * kastes unntaket Ulovligutskrift
     * @return objekt av typen BlaaResept
     */
    public Resept skrivResept(Legemiddel legemiddel, int pasientID, int reit) throws UlovligUtskrift {
        if (legemiddel instanceof PreparatA && !(this instanceof Spesialist)) {
            throw new UlovligUtskrift(this, legemiddel);
        }
        return new BlaaResept(legemiddel, this, pasientID, reit);
    }
}