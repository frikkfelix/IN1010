package me.frikk.oblig7;
import java.util.ArrayList;
import java.util.List;

class Aapning extends HvitRute {
    public Aapning(int rad, int kolonne) {
        super(rad, kolonne);
    }

    @Override
    public void gaa(List<Rute> besoekteRuter) {
        Rute startRute;
        ArrayList<Rute> oppdatertBesoekteRuter = new ArrayList<Rute>(besoekteRuter);
        oppdatertBesoekteRuter.add(this);

        if (oppdatertBesoekteRuter.size() == 1){
            startRute = this;
        } else {
            startRute = oppdatertBesoekteRuter.get(0);
        }
        startRute.losninger.add(oppdatertBesoekteRuter);
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", kolonne, rad);
    }
}