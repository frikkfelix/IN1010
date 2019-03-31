package me.frikk.oblig1;

public class Node {

    private int minne;
    private int prosessorer;

    // Konstruktør
    public Node(int m, int p) {
        minne = m;
        prosessorer = p;
    }

    public int getProsessorer() {
        return prosessorer;
    }

    public int getMinne() {
        return minne;
    }

    public boolean nokMinne(int reqMinne) {
        return minne >= reqMinne;
    }
}
