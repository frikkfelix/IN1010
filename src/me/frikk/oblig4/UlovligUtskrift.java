package me.frikk.oblig4;

public class UlovligUtskrift extends Exception {

    private static final long serialVersionUID = 1L;

    UlovligUtskrift(Lege l, Legemiddel lm) {
        super("Legen " + l.hentNavn() + " har ikke lov til aa skrive ut " + lm.hentNavn());
    }
}