package me.frikk.oblig2;

class TestPreparat {
    public static void main(String[] args) {
        PreparatA a = new PreparatA("Paralgin Forte", 299.99, 70, 3);
        PreparatB b = new PreparatB("Valium Roche", 249.99, 5, 9);
        PreparatC c = new PreparatC("Ibux", 199, 400);

        testLegemidler(a);
        testAB(a);

        testLegemidler(b);
        testAB(b);

        testLegemidler(c);

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }

    /**
     * Tester metodene felles for alle Legemidler
     */
    public static void testLegemidler(Legemiddel lm) {
        System.out.println("\nNavn: " + lm.hentNavn());
        System.out.println("Legemiddel-ID: " + lm.hentId());
        System.out.println("Virkestoff: " + lm.hentVirkestoff());
        System.out.println("Pris: " + lm.hentPris());
        lm.settNyPris(666);
        System.out.println("Pris: " + lm.hentPris());
    }

    /**
     * Tester metodene hentNarkotiskStyrke og hentVanedannendeStyrke
     */
    public static void testAB(Legemiddel lm) {
        if (lm instanceof PreparatA) {
            PreparatA a = (PreparatA) lm;
            System.out.println("Narkotisk styrke: " + a.hentNarkotiskStyrke());
        }

        if (lm instanceof PreparatB) {
            PreparatB b = (PreparatB) lm;
            System.out.println("Vanedannende styrke: " + b.hentVanedannendeStyrke());
        }
    }
}