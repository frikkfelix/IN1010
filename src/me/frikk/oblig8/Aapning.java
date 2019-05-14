package me.frikk.oblig8;import java.util.List;

class Aapning extends HvitRute {
    public Aapning(int rad, int kolonne) {
        super(rad, kolonne);
    }

    @Override
    public void gaa(List<Rute> besoekteRuter) {
        Rute startRute;
        besoekteRuter.add(this);
        if (besoekteRuter.size() == 1){
            startRute = this;
        } else {
            startRute = besoekteRuter.get(0);
        }
        startRute.losninger.add(besoekteRuter);
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", kolonne, rad);
    }
}