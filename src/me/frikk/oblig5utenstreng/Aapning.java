package me.frikk.oblig5utenstreng;

import java.util.List;

class Aapning extends HvitRute {
    public Aapning(int rad, int kolonne) {
        super(rad, kolonne);
    }

    @Override
    public void gaa(List<Rute> besoekteRuter) {
        Rute startRute;
        if (besoekteRuter.isEmpty()){
            startRute = this;
        } else {
            startRute = besoekteRuter.get(0);
            besoekteRuter.add(this);
        }
        startRute.losninger.add(besoekteRuter);
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", kolonne, rad);
    }
}