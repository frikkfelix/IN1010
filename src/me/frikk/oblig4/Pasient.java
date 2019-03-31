package me.frikk.oblig4;

class Pasient {
    protected String navn;
    protected String fodselsNummer;
    protected Stabel<Resept> resepter;

    protected int pasientID;
    private static int idTeller = 0;

    public Pasient(String navn, String fodselsNummer) {
        this.navn = navn;
        this.fodselsNummer = fodselsNummer;
        resepter = new Stabel<Resept>();

        pasientID = idTeller;
        idTeller++;
    }

    public int hentPasientID() {
        return pasientID;
    }

    public String hentNavn() {
        return navn;
    }

    public String hentFodselsnummer() {
        return fodselsNummer;
    }

    public void leggTilResept(Resept resept) {
        resepter.leggPaa(resept);
    }

    public Stabel<Resept> hentReseptListe() {
        return resepter;
    }

    @Override
    public String toString() {
        return ("Navn: " + navn
        + "\nFodselsnummer: " + fodselsNummer
        + "\nPasient-ID: " + pasientID);
    }
}