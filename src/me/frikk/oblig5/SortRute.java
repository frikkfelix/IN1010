package me.frikk.oblig5;

class SortRute extends Rute {
    public SortRute(int rad, int kolonne) {
        super(rad, kolonne);
        sort = true;
    }

    @Override
    char tilTegn() {
        return '#';
    }
}