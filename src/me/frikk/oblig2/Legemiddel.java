package me.frikk.oblig2;

abstract class Legemiddel {
    protected int legemiddelID;
    protected String navn;
    protected double pris;
    protected double virkestoff;
    private static int n = 0;

    public Legemiddel(String navn, double pris, double virkestoff) {
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;
        this.legemiddelID = n;
        n++;
    }

    /**
     * @return string-representasjon for alle Legemiddel-objekter
     */
    @Override
    public String toString() {
        return ("\n" + navn 
        + "\nLegemiddel-ID: " + legemiddelID
        + "\nPris: " + pris
        + "\nVirkestoff: " + virkestoff + " mg\n");
    }

    public int hentId() {
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