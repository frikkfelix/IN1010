package me.frikk.oblig5;

import java.util.ArrayList;

abstract class Rute {
    protected int kolonne;
    protected int rad;
    protected Labyrint labyrint;
    protected boolean erUtvei = false;
    protected boolean sort;
    protected boolean besokt;

    protected boolean fraSyd = false;
    protected boolean fraNord = false;
    protected boolean fraOest = false;
    protected boolean fraVest = false;
    
    protected Rute nord;
    protected Rute syd;
    protected Rute vest;
    protected Rute oest;
    protected Rute forrige;



    public Rute(int rad, int kolonne) {
        this.rad = rad;   
        this.kolonne = kolonne;
    }

    abstract char tilTegn();

    public void settNord(Rute nord) {
        this.nord = nord;
        return;
    }

    public void settSyd(Rute syd) {
        this.syd = syd;
        return;
    }

    public void settVest(Rute vest) {
        this.vest = vest;
        return;
    }

    public void settOest(Rute oest) {
        this.oest = oest;
        return;
    }

    public void settLabyrint(Labyrint labyrint) {
        this.labyrint = labyrint;
        return;
    }

    public ArrayList<Rute> hentNaboer() {
        ArrayList<Rute> naboer = new ArrayList<Rute>();
        if (nord != null) {naboer.add(nord);}
        if (syd != null) {naboer.add(syd);}
        if (oest != null) {naboer.add(oest);}
        if (vest != null) {naboer.add(vest);}
        return naboer;
    }

    @Override
    public String toString() {
        return String.format("rad: %d, kolonne: %d", rad + 1, kolonne + 1);
    }

    public void gaa() {
        besokt = true;
        if (erUtvei) {
            System.out.println("Fant en utvei!");
            return;
        }
        for (Rute nabo : hentNaboer()) {
            if (!nabo.sort && !nabo.besokt) {
                System.out.println(nabo);
                nabo.gaa();
            }
        }        
    }


    public void finnUtvei() {
        gaa();
    }
}
