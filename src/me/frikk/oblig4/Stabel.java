package me.frikk.oblig4;

class Stabel<T> extends Lenkeliste<T> {
    public void leggPaa(T data) {
        leggTil(data);
    }
    
    public T taAv() {
        return fjern(stoerrelse() - 1);
    }
}