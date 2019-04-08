package me.frikk.oblig5;

import java.util.List;

class Aapning extends HvitRute {
    public Aapning(int rad, int kolonne) {
        super(rad, kolonne);
    }

    @Override
    public void gaa(List<Rute> besoekteRuter, String sti) {
        // Dersom vi starter på en åpning er besoekteRuter tom
        // Ellers settes startruten til å være den første besøkte ruten
        Rute startRute;
        if (besoekteRuter.isEmpty()){
            startRute = this;
        } else {
            startRute = besoekteRuter.get(0);
            besoekteRuter.add(this);
        }
        sti += String.format("(%d, %d)", kolonne, rad);

        // Legger til stien i startrutens liste av løsninger
        startRute.losninger.leggTil(sti);
    }
}