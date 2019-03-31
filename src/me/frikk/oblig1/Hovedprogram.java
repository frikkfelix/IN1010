package me.frikk.oblig1;

public class Hovedprogram {
    public static void main(String[] args) {
        Regneklynge abel = new Regneklynge("/Users/frikk/Documents/2.semester/IN1010/JavaProjects/me.frikk.obliger/src/me/frikk/oblig1/regneklynge.txt");

        System.out.println(String.format("Noder med minst 32 GB: %d", abel.noderMedNokMinne(32)));
        System.out.println(String.format("Noder med minst 64 GB: %d", abel.noderMedNokMinne(64)));
        System.out.println(String.format("Noder med minst 128 GB: %d", abel.noderMedNokMinne(128)));

        System.out.println(String.format("\nAntall prosessorer: %d",abel.sumProsessorer()));
        System.out.println(String.format("Antall rack: %d",abel.antRacks()));
    }
}
