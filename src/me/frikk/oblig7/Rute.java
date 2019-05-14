package me.frikk.oblig7;import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;


abstract class Rute {
    protected int kolonne;
    protected int rad;
    protected Labyrint labyrint;
    protected List<List<Rute>> losninger;

    private Rute nord;
    private Rute syd;
    private Rute vest;
    private Rute oest;

    public Rute(int rad, int kolonne) {
        this.rad = rad;
        this.kolonne = kolonne;
    }

    abstract char tilTegn();

    public void settNord(Rute nord) {
        this.nord = nord;
    }

    public void settSyd(Rute syd) {
        this.syd = syd;
    }

    public void settVest(Rute vest) {
        this.vest = vest;
    }

    public void settOest(Rute oest) {
        this.oest = oest;
    }

    public void settLabyrint(Labyrint labyrint) {
        this.labyrint = labyrint;
    }

    /**
     * Filtrerer ut naboruter som er null, og samler i en liste 
     * @return liste av naboruter
     */
    public List<Rute> hentNaboer() {
        return Stream.of(oest, syd, vest, nord).filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * Metoden finner alle utveier fra ruten
     * @param besoekteRuter liste over ruter ruten har besøkt
     * @param sti string som inneholder koordinatene til rutene på stien
     */
    public abstract void gaa(List<Rute> besoekteRuter);

    public void finnUtvei() {
        gaa(Collections.emptyList());
    }

    @Override
    public String toString() {
        return String.format("(%d, %d) --> ", kolonne, rad);
    }

}
