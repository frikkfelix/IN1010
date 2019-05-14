package me.frikk.oblig5utenstreng;

import java.util.ArrayList;
import java.util.List;

class HvitRute extends Rute {
    public HvitRute(int rad, int kolonne) {
        super(rad, kolonne);
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
}