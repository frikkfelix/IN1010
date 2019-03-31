package me.frikk.oblig2;

class Hovedprogram {
    public static void main(String[] args) {
        Spesialist house = new Spesialist("Dr. House", 666);
        Lege wilson = new Lege("Dr. Wilson");
        PreparatA vicodin = new PreparatA("Vicodin", 299, 500, 8);

        
        System.out.println(vicodin);
        System.out.println(house);

        try {
            wilson.skrivResept(vicodin, 123, 2);            
        } catch (UlovligUtskrift e) {
            System.out.println(e.getMessage());
        }
        
    }
}