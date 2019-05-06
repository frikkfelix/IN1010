package me.frikk.oblig6;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Operasjonsleder implements Runnable {
    private Monitor dekryptertMonitor;
    private List<Melding> dekrypterteMeldinger = new ArrayList<>();
    private final int ANTALL_KANALER;

    public Operasjonsleder(Monitor dekryptertMonitor, int antallKanaler) {
        this.dekryptertMonitor = dekryptertMonitor;
        ANTALL_KANALER = antallKanaler;
    }

    @Override
    public void run() {
        try {
            Melding dekryptertMelding = dekryptertMonitor.hentMelding();
            while (dekryptertMelding != null) {
                dekrypterteMeldinger.add(dekryptertMelding);
                dekryptertMelding = dekryptertMonitor.hentMelding();
            }
            dekrypterteMeldinger.sort(Comparator.comparing(Melding::hentKanalID).thenComparing(Melding::hentSekvensNummer));
            skrivTilFil(dekrypterteMeldinger);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * @param meldinger dekrypterte meldinger sortert etter sekvens- og kanalnummer 
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    private void skrivTilFil(List<Melding> meldinger) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter skriver = null;

        for (int i = 1; i <= ANTALL_KANALER; i++) {
            String filnavn = "kanal" + i + ".txt";
            skriver = new PrintWriter(filnavn, "utf-8");
            for (Melding m : meldinger) {
                if (m.hentKanalID() == i) {
                    skriver.write(m.hentInnhold() + "\n\n");
                }
            }
            skriver.close();
        }
    }
}