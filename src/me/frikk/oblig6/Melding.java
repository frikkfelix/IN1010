package me.frikk.oblig6;

class Melding {
    private String innhold;
    private int sekvensNummer;
    private int kanalID;

    public Melding(String innhold, int sekvensNummer, int kanalID) {
        this.innhold = innhold;
        this.sekvensNummer = sekvensNummer;
        this.kanalID = kanalID;
    }

    public String hentInnhold() {
        return innhold;
    }

    public int hentSekvensNummer() {
        return sekvensNummer;
    }

    public int hentKanalID() {
        return kanalID;
    }
}