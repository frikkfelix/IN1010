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

    public List<List<Rute>> losninger = new ArrayList<>();
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
    }

    public void settSyd(Rute syd) {
        this.syd = syd;
    }

    public void settVest(Rute vest) {
        this.vest = vest;
    }

    public void settOest(Rute oest) {
        this.oest = oest;
    }

    public void settLabyrint(Labyrint labyrint) {
        this.labyrint = labyrint;
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
     * @param sti Liste med ruter man har vært innom før nåværende rute
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
            this.naboTilUtvei = hentNaboer().stream()               // henter en stream av denne rutens naboer
                .filter(nabo -> !sti.contains(nabo) && !nabo.sort)  // filtrerer ut svarte og tidligere besøkte naboer 
                .filter(nabo -> nabo.gaa(nySti))                    // filtrerer ut naboene som ikke leder til en utvei
                .collect(Collectors.toList());                      // samler alle naboene som leder til en utvei i listen
        }
        //System.out.println(this);
        //System.out.println(naboTilUtvei);
        return this.naboTilUtvei.size() > 0;                        // returnerer true hvis ruten har naboer som fører til en utvei
    }

    public void finnUtvei() {
        gaa();
        if (this.erUtvei) {
            this.losninger.add(Collections.singletonList(this));    // hvis vi allerede er på en utvei legges denne ruten til i løsninger
        } else {
            this.naboTilUtvei.stream().forEach(nabo -> {            // henter en stream av rutens naboer som leder til en utvei
                nabo.finnUtvei();                                   // rekursivt kall på alle naboer som leder til utvei
                this.losninger.addAll(nabo.losninger.stream().map(loesning -> { // Gjør løsning om til nyLøsning som defineres i lambda-funksjonen
                    List<Rute> nyLoesning = new ArrayList<>(loesning);          // oppretter ny løsning 
                    nyLoesning.add(0, this);                                    // legger til denne ruten på begynnelsen av listen
                    return nyLoesning;
                }).collect(Collectors.toList()));                               // legger til alle løsninger i denne rutens liste av løsninger
            });
        }
    }
}
