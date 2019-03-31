package me.frikk.oblig3;

class TestSort {
    public static void main(String[] args) {
        SortertLenkeliste<Integer> liste = new SortertLenkeliste<Integer>();
        liste.leggTil(2);
        liste.leggTil(1);
        liste.leggTil(4);
        liste.leggTil(3);
        /* liste.leggTil("C");
        liste.leggTil("A");
        liste.leggTil("D");
        liste.leggTil("B"); */
        System.out.println(liste.hent(0));
        System.out.println(liste.hent(1));
        System.out.println(liste.hent(2));
        System.out.println(liste.hent(3));
        liste.fjern();
        System.out.println(liste.hent(0));
        System.out.println(liste.hent(1));
        System.out.println(liste.hent(2));
    }
}