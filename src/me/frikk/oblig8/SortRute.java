package me.frikk.oblig8;import java.util.List;

class SortRute extends Rute {
    public SortRute(int rad, int kolonne) {
        super(rad, kolonne);
    }

    @Override
    char tilTegn() {
        return '#';
    }

    @Override
    public void gaa(List<Rute> besoekteRuter) {
        // Kan ikke gå på sorte ruter, returnerer derfor uten å gjøre noe
        return;
    }
}