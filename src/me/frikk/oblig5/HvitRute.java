package me.frikk.oblig5;

class HvitRute extends Rute {
    public HvitRute(int rad, int kolonne) {
        super(rad, kolonne);
        sort = false;
    }

    @Override
    char tilTegn() {
        return '.';
    }

}