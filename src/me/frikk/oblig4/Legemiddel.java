package me.frikk.oblig4;

abstract class Legemiddel {
    protected String navn;
    protected double pris;
    protected double virkestoff;

    protected int legemiddelID;
    private static int idTeller = 0;

    public Legemiddel(String navn, double pris, double virkestoff) {
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;

        this.legemiddelID = idTeller;
        idTeller++;
    }

    /**
     * @return string-representasjon for alle Legemiddel-objekter
     */
    @Override
    public String toString() {
        return (navn 
        + "\nLegemiddel-ID: " + legemiddelID
        + "\nPris: " + pris
        + "\nVirkestoff: " + virkestoff + " mg");
    }

    public int hentLegemiddelID() {
        return legemiddelID;
    }

    public String hentNavn() {
        return navn;
    }

    public double hentPris() {
        return pris;
    }

    public double hentVirkestoff() {
        return virkestoff;
    }

    public void settNyPris(double nyPris) {
        System.out.println("Prisen på " + navn + " er oppdatert til kr " + nyPris + ",-");
        pris = nyPris;
    }

}