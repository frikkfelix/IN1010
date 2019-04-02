package me.frikk.oblig5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

abstract class Rute {
    protected int kolonne;
    protected int rad;
    protected Labyrint labyrint;
    protected boolean erUtvei = false;
    protected boolean sort;
    protected boolean besokt;
    public List<List<Rute>> losninger = new ArrayList<>();
    // public Liste<String> losninger  = new Lenkeliste<String>();

    private List<Rute> naboTilUtvei;
    
    protected Rute nord;
    protected Rute syd;
    protected Rute vest;
    protected Rute oest;

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

    public List<Rute> hentNaboer() {
        return Stream.of(nord, syd, oest, vest)
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.format("rad: %d, kolonne: %d", rad, kolonne);
    }

    /* public boolean gaa(Rute forrige) {
        if (erUtvei) {
            System.out.println("Fant en utvei!");
            return true;
        }
        for (Rute nabo : hentNaboer()) {
            if (!nabo.sort && !nabo.equals(forrige)) {
                System.out.println(nabo);
                nabo.gaa(this);
            }
        }
        return false;        
    } */

    public boolean gaa() {
        return gaa(Collections.emptyList());
    }

    /**
     * Hver rute har en nabo til utvei liste
     * Metoden returnerer true hvis den kommer til en utvei
     * 
     * 
     * @param path
     * @return
     */
    public boolean gaa(List<Rute> sti) {
        if (this.erUtvei) {
            //System.out.println(this);
            //System.out.println("Fant en utvei");
            return true;
        }

        if (this.naboTilUtvei == null) {
            ArrayList<Rute> nySti = new ArrayList<Rute>(sti);
            nySti.add(this);
            this.naboTilUtvei = hentNaboer().stream()
                .filter(nabo -> !sti.contains(nabo) && !nabo.sort)
                .filter(nabo -> nabo.gaa(nySti))
                .collect(Collectors.toList());
        }
        //System.out.println(this);
        //System.out.println(naboTilUtvei);
        return this.naboTilUtvei.size() > 0;
    }

    public void finnUtvei() {
        if (this.erUtvei) {
            this.losninger.add(Collections.singletonList(this));
        } else {
            this.naboTilUtvei.stream().forEach(nabo -> {
                nabo.finnUtvei();
                this.losninger.addAll(nabo.losninger.stream().map(loesning -> {
                    List<Rute> nyLoesning = new ArrayList<>(loesning);
                    nyLoesning.add(0, this);
                    return nyLoesning;
                }).collect(Collectors.toList()));
            });
        }
    }
}
