package me.frikk.oblig2;

class TestResepter {
    public static void main(String[] args) {
        PreparatA a = new PreparatA("Paralgin Forte", 299.99, 70, 3);
        PreparatB b = new PreparatB("Valium Roche", 200, 5, 9);
        PreparatC c = new PreparatC("Ibux", 100, 400);

        Lege l = new Lege("Dr. Jekyll");

        MilitaerResept mr = new MilitaerResept(a, l, 1234, 5);
        PResept pr = new PResept(b, l, 1234);
        BlaaResept br = new BlaaResept(c, l, 1234, 0);

        testResepter("Militaerresept", mr);
        testResepter("P-Resept", pr);
        testResepter("Blaa Resept", br);
        /**
         * Tester toString
         */
        System.out.println(mr);
        System.out.println(pr);
        System.out.println(br);
    }

    /**
     * Tester  bruk og prisAaBetale
     */
    public static void testResepter(String type, Resept r) {
        System.out.println("\n####Test av " + type + "####");
        Legemiddel lm = r.hentLegemiddel();

        System.out.println(r.farge() + " resept");
        System.out.println(lm.hentNavn());
        System.out.println("Pris for fradrag: " + lm.hentPris() + ",-");
        System.out.println("Pris etter fradrag: " + r.prisAaBetale() + ",-");
        System.out.println("Reit: " + r.hentReit());

        r.bruk();

        System.out.println("Gjenstående reit: " + r.hentReit());
    }
}