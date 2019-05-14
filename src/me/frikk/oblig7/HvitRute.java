package me.frikk.oblig7;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

class HvitRute extends Rute {
    public HvitRute(int rad, int kolonne) {
        super(rad, kolonne, Color.WHITE);
    }

    @Override
    char tilTegn() {
        return '.';
    }

    @Override
    public void gaa(List<Rute> besoekteRuter) {        
        ArrayList<Rute> oppdatertBesoekteRuter = new ArrayList<Rute>(besoekteRuter);
        oppdatertBesoekteRuter.add(this);

        // Rekursivt kall på rutens naboruter som ikke er besøkt
        for (Rute nabo : hentNaboer()) {
            if (!besoekteRuter.contains(nabo)) {
                nabo.gaa(oppdatertBesoekteRuter);
            }
        }
    }

    @Override
    public void klikk() {
        labyrint.nullstillBrett();
        List<List<Rute>> losninger = labyrint.finnUtveiFra(kolonne, rad);
        MazeSolver.losninger = losninger;
        List<Rute> enVei = losninger.size() > 0 ? losninger.get(0) : null;
        if(enVei != null) {
            setFill(Color.GREEN);
            MazeSolver.tegnLoesning(enVei);
        } else {
            setFill(Color.RED);
        } 
    }

    public void markerRuter(List<Rute> ruter) {
        for (Rute rute : ruter) {
            rute.setFill(Color.GREEN);
        }
    }
}