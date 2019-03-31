package me.frikk.oblig3;

class Lenkeliste<T> implements Liste<T> {
    private Node start = null;
    private int stoerrelse = 0;

    class Node {
        Node neste = null;
        T data;

        Node(T data) {
            this.data = data;
        }
    }

    public void leggTil(T data) {
        if (start == null) {
            start = new Node(data);
        } else {
            Node siste = finnSiste();
            siste.neste = new Node(data);
        }
        stoerrelse++;
    }

    /**
     * @return data til noden som fjernes hvis den ikke er null, og null ellers
     */
    public T fjern() throws UgyldigListeIndeks {
        if (start == null) {
            throw new UgyldigListeIndeks(0);
        }

        Node gammelStart = start;
        if (gammelStart.neste == null) {
            start = null;
            stoerrelse = 0;
        } else {
            Node nyStart = gammelStart.neste;
            start = nyStart;
            stoerrelse--;
        }

        return gammelStart != null ? gammelStart.data : null;
    }

    public void sett(int pos, T data) throws UgyldigListeIndeks {
        if (pos < 0 || pos >= stoerrelse || stoerrelse == 0) {
            throw new UgyldigListeIndeks(pos);
        }

        Node node = finnIndex(pos);
        node.data = data;
    }

    public void leggTil(int pos, T data) throws UgyldigListeIndeks {
        if (pos < 0 || pos > stoerrelse) {
            throw new UgyldigListeIndeks(pos);
        }

        if (start == null) {
            leggTil(data);
            return;
        }

        Node nyNode = new Node(data);

        if (pos == 0) {
            Node gammelStart = start;
            nyNode.neste = gammelStart;
            start = nyNode;

        } else {
            Node forrige = finnIndex(pos - 1);
            Node neste = forrige.neste;
            forrige.neste = nyNode;
            nyNode.neste = neste;
        }
        stoerrelse++;
    }

    /**
     * @return data til node som fjernes
     */
    public T fjern(int pos) throws UgyldigListeIndeks {
        if (pos < 0 || pos >= stoerrelse || stoerrelse == 0) {
            throw new UgyldigListeIndeks(pos);
        }

        Node skalFjernes;

        if (pos == 0) {
            skalFjernes = start;
            Node nyStart = skalFjernes.neste;
            start = nyStart;

        } else {
            Node forrige = finnIndex(pos - 1);
            skalFjernes = forrige.neste;
            forrige.neste = skalFjernes.neste;     
        }
        stoerrelse--;
        return skalFjernes.data;

    }

    /**
     * Setter noden til å være start hvis pos == 0, ellers finnIndex på pos
     */
    public T hent(int pos) throws UgyldigListeIndeks {
        if (pos < 0 || pos >= stoerrelse || stoerrelse == 0) {
            throw new UgyldigListeIndeks(pos);
        }

        Node node = pos == 0 ? start : finnIndex(pos);

        return node.data;
    }

    public int stoerrelse() {
        return stoerrelse;
    }

    /**
     * @return den siste noden i listen
     */
    public Node finnSiste() {
        return stoerrelse == 0 ? start : finnIndex(stoerrelse - 1);
    }

    /**
     * @return node på en gitt posisjon
     */
    public Node finnIndex(int index) {
        Node node = start;
        for (int i = 0; i < index; i++) {
            node = node.neste;
        }
        return node;
    }
}