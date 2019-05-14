package me.frikk.oblig7;
import java.util.List;

import javafx.scene.paint.Color;

class SortRute extends Rute {
    public SortRute(int rad, int kolonne) {
        super(rad, kolonne, Color.BLACK);
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

    @Override
    public void klikk() {
        return;
    }
}