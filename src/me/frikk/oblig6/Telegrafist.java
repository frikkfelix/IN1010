package me.frikk.oblig6;

import java.util.concurrent.CountDownLatch;

class Telegrafist implements Runnable {
    private Kanal kanal;
    private Monitor monitor;
    private CountDownLatch telegrafister;
    private int kanalID;
    private int sekvensNummer = 0;

    public Telegrafist(Kanal kanal, Monitor monitor, CountDownLatch telegrafister) {
        this.kanal = kanal;
        kanalID = kanal.hentId();
        this.monitor = monitor;
        this.telegrafister = telegrafister;
    }

    @Override
    public void run() {
        try {
            String kryptertInnhold = kanal.lytt();
            while(kryptertInnhold != null) {
                Melding kryptertMelding = new Melding(kryptertInnhold, sekvensNummer++, kanalID);
                monitor.settInnMelding(kryptertMelding);
                kryptertInnhold = kanal.lytt();
            }
            telegrafister.countDown();
            
        } catch (InterruptedException e) {
            System.out.println("Telegrafist avbrutt");
        }
        
    }
}