package me.frikk.oblig4;

class Lege implements Comparable<Lege> {
    protected String navn;
    protected Lenkeliste<Resept> utskrevedeResepter;

    public static int vanedannendeResepter = 0;
    public static int narkotiskeResepter = 0;
    public int dennesNarkotiske = 0;

    public Lege(String navn) {
        this.navn = navn;
        utskrevedeResepter = new Lenkeliste<Resept>();
    }

    public String hentNavn() {
        return navn;
    }

    @Override
    public String toString() {
        return navn;
    }

    public Lenkeliste<Resept> hentResepter() {
        return utskrevedeResepter;
    }

    /**
     * Forsøker å skrive en resept
     * Dersom legemiddel er et objekt av typen PreparatA,
     * og legen ikke er spesialist, kastes unntaket Ulovligutskrift
     * 
     * Oppretter resept-objekt av den oppgitte typen, og legger til i Legens
     * og Pasientens lister over resepter
     * 
     * @return resept-objekt av den oppgitte typen
     */

    public Resept skrivResept(ReseptType reseptType, Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (legemiddel instanceof PreparatA && !(this instanceof Spesialist)) {
            throw new UlovligUtskrift(this, legemiddel);
        }

        if (legemiddel instanceof PreparatA) {
            narkotiskeResepter++;
            dennesNarkotiske++;
        }
        if (legemiddel instanceof PreparatB) {
            vanedannendeResepter++;
        }

        Resept resept = null;

        if (reseptType == ReseptType.PREVENSJON) {
            resept = new PResept(reseptType, legemiddel, this, pasient);
        }

        if (reseptType == ReseptType.BLAA) {
            resept = new BlaaResept(reseptType, legemiddel, this, pasient, reit);
        }

        if (reseptType == ReseptType.HVIT) {
            resept = new HvitResept(reseptType, legemiddel, this, pasient, reit);
        }

        if (reseptType == ReseptType.MILITAER) {
            resept = new MilitaerResept(reseptType, legemiddel, this, pasient, reit);
        } 

        utskrevedeResepter.leggTil(resept);
        pasient.hentReseptListe().leggPaa(resept);
        return resept;
    }
    
    /**
     * Sørger for at legene sammenlignes etter navn
     */
    @Override
    public int compareTo(Lege annenLege) {
        return this.hentNavn().compareTo(annenLege.hentNavn());
    }
}