package me.frikk.oblig6;

public interface MonitorI {
    public void settInnMelding(Melding melding) throws InterruptedException;
    public Melding hentMelding() throws InterruptedException;
    public boolean erFerdig();
    public boolean erTom();
}
    
