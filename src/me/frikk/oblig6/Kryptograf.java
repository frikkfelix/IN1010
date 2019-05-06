package me.frikk.oblig6;

import java.util.concurrent.CountDownLatch;

class Kryptograf implements Runnable {
    private Monitor kryptertMonitor;
    private Monitor dekryptertMonitor;
    private CountDownLatch kryptografer;

    public Kryptograf(Monitor kryptertMonitor, Monitor dekryptertMonitor, CountDownLatch kryptografer) {
        this.kryptertMonitor = kryptertMonitor;
        this.dekryptertMonitor = dekryptertMonitor;
        this.kryptografer = kryptografer;
    }

    /**
     * Thread-metode
     * Henter meldinger fra kryptert monitor, dekrypterer og setter inn i 
     * dekryptert monitor
     */
    @Override
    public void run() {
        try {
            Melding kryptertMelding = kryptertMonitor.hentMelding();
            while (!kryptertMonitor.erFerdig()) {
                String dekryptertInnhold = Kryptografi.dekrypter(kryptertMelding.hentInnhold());
                dekryptertMonitor.settInnMelding(new Melding(dekryptertInnhold, kryptertMelding.hentSekvensNummer(), kryptertMelding.hentKanalID()));
                kryptertMelding = kryptertMonitor.hentMelding();
            }

            kryptografer.countDown();
        } catch (InterruptedException e) {
            System.out.println("Kryptograf avbrutt");
        }
    }

}