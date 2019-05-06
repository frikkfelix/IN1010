package me.frikk.oblig6;

import java.util.concurrent.CountDownLatch;

class Hovedprogram {
    public static void main(String[] args) throws InterruptedException {
        int ANTALL_TELEGRAFISTER = 3;
        int ANTALL_KRYPTOGRAFER = 50;
        
        CountDownLatch telegrafister = new CountDownLatch(ANTALL_TELEGRAFISTER);
        CountDownLatch kryptografer = new CountDownLatch(ANTALL_KRYPTOGRAFER);

        Operasjonssentral ops = new Operasjonssentral(ANTALL_TELEGRAFISTER);
        Kanal[] kanaler = ops.hentKanalArray();

        Monitor kryptertMonitor = new Monitor();
        Monitor dekryptertMonitor = new Monitor();

        for (Kanal kanal : kanaler) {
            Runnable telegrafist = new Telegrafist(kanal, kryptertMonitor, telegrafister);
            new Thread(telegrafist).start();
        }

        for (int i = 0; i < ANTALL_KRYPTOGRAFER; i++) {
            Runnable kryptograf = new Kryptograf(kryptertMonitor, dekryptertMonitor, kryptografer);
            new Thread(kryptograf).start();
        }

        Runnable operasjonsleder = new Operasjonsleder(dekryptertMonitor, kanaler.length);
        new Thread(operasjonsleder).start();
        
        telegrafister.await();
        Melding stoppSignal = new Melding("INGEN FLERE MELDINGER", 0, 0);
        kryptertMonitor.settInnMelding(stoppSignal);

        kryptografer.await();
        dekryptertMonitor.settInnMelding(stoppSignal);
    }
}