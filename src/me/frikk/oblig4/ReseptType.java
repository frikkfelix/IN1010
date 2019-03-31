package me.frikk.oblig4;

public enum ReseptType {
    MILITAER("militaer"),
    BLAA("blaa"),
    HVIT("hvit"),
    PREVENSJON("prevensjon");

    private String tekst;
    
    ReseptType(String tekst) {
        this.tekst = tekst;
    }

    public String hentTekst() {
        return tekst;
    }

    public static ReseptType fraTekst(String tekst) {
        for (ReseptType reseptType : ReseptType.values()) {
            if (reseptType.tekst.equals(tekst)) {
                return reseptType;
            }
        }
        return null;
    }
}
