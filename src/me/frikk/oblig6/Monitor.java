package me.frikk.oblig6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Monitor implements MonitorI {
    private List<Melding> mottatteMeldinger = new ArrayList<>();
    private Lock lock = new ReentrantLock();
    private Condition meldingerKanHentes = lock.newCondition();
    private boolean erFerdig = false;

    @Override
    public void settInnMelding(Melding melding) throws InterruptedException {
        lock.lock();
        try {
            mottatteMeldinger.add(melding);
            if ("INGEN FLERE MELDINGER".equals(melding.hentInnhold())) {
                erFerdig = true;
            }
            meldingerKanHentes.signalAll();
        } 
        finally {
            lock.unlock();
        }  
    }

    /**
     * @return null hvis monitor er ferdig og tom
     */
    @Override
    public Melding hentMelding() throws InterruptedException {
        lock.lock();
        try {
            while(erTom() && !erFerdig()) {
                meldingerKanHentes.await();
            }
            return mottatteMeldinger.isEmpty() && erFerdig() ? null : mottatteMeldinger.remove(0);
        }
        finally {
            lock.unlock();
        }
    }

    @Override
    public boolean erFerdig() {
        return this.erFerdig;
    }

    @Override
    public boolean erTom() {
        return mottatteMeldinger.isEmpty();
    }
}