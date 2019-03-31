package me.frikk.oblig1;

import java.util.ArrayList;

public class Rack {

    private ArrayList<Node> noder;

    public Rack() {
        noder = new ArrayList<>();
    }

    public void settInn(Node node) {
        noder.add(node);
    }

    public int getAntNoder() {
        return noder.size();
    }
    /**
     * @return sum av alle prosessorer i racket
     */
    public int getProsessorer() {
        return noder.stream()
            .mapToInt(node -> node.getProsessorer())
            .sum();
    }
    /**
     * @param reqMinne  påkrevd minne
     * @return          returnerer alle noder med nok minne
     */
    public int noderMedNokMinne(int reqMinne) {
        return (int) noder.stream()
            .filter(node -> node.nokMinne(reqMinne))
            .count();
    }
}
