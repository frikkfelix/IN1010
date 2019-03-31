package me.frikk.oblig2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Main {
    static ArrayList<Legemiddel> legemidler = new ArrayList<Legemiddel>();
    static HashMap<String, Lege> leger = new HashMap<String, Lege>();
    static ArrayList<Resept> resepter = new ArrayList<Resept>();
    public static void main(String[] args) {
        lesFil("/Users/frikk/Documents/2.semester/IN1010/JavaProjects/me.frikk.obliger/src/me/frikk/oblig2/data.txt");

        System.out.println("\n#####\nLEGEMIDLER OPPRETTET\n####");
        for (Legemiddel l : legemidler) {
            System.out.println(l);
        }

        System.out.println("####\nRESEPTER OPPRETTET\n####");
        for (Resept r : resepter) {
            System.out.println(r);
        }

        System.out.println("\n#####\nLEGER OPPRETTET\n####");
        for (Lege l : leger.values()) {
            System.out.println(l);
        }

        System.out.println("\n########\nPROVER Å SKRIVE UT ULOVLIG RESEPT\n########");
        PreparatA oramorph = new PreparatA("Oramorph", 199, 20, 10);
        Lege wilson = leger.get("Dr. Wilson");

        try {
            wilson.skrivResept(oramorph, 1, 3);
        } catch (UlovligUtskrift e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metoden leser filen etter spesifisert filformat,
     * oppretter objekter, legger Legemiddel- og Resept-objekter 
     * inn i ArrayList-er, og Lege-objekter i en HashMap
     * 
     * @param filnavn navn på filen som skal leses
     */
    public static void lesFil(String filnavn) {
        Scanner fil = null;

        try {
            fil = new Scanner(new File(filnavn));

            while(fil.hasNextLine()) {
                String header = fil.nextLine();

                if (header.startsWith("# Legemidler")) {
                    /**
                     * Løkken avsluttes når en linje starter med #
                     */
                    while (true) {
                        String linje = fil.nextLine();
                        if (linje.startsWith("#")) {
                            header = linje;
                            break;}

                        String[] lmInfo = linje.split(", ");
                        String navn = lmInfo[0].trim();
                        String type = lmInfo[1].trim();
                        double pris = Double.parseDouble(lmInfo[2].trim());
                        double virkestoff = Double.parseDouble(lmInfo[3].trim());

                        if (type.equals("a")) {
                            int styrke = Integer.parseInt(lmInfo[4].trim());
                            legemidler.add(new PreparatA(navn, pris, virkestoff, styrke));
                        }

                        if (type.equals("b")) {
                            int styrke = Integer.parseInt(lmInfo[4].trim());
                            legemidler.add(new PreparatB(navn, pris, virkestoff, styrke));
                        }

                        if (type.equals("c")) {
                            legemidler.add(new PreparatC(navn, pris, virkestoff));
                        }
                    }
                }

                if (header.startsWith("# Leger")) {
                    while(true) {
                        String linje = fil.nextLine();
                        if (linje.startsWith("#")) {
                            header = linje;
                            break;}

                        String[] legeInfo = linje.split(", ");
                        String navn = legeInfo[0].trim();
                        int kontrollID = Integer.parseInt(legeInfo[1].trim());

                        if (kontrollID == 0) {
                            leger.put(navn, new Lege(navn));
                            System.out.println("Lege registrert: " + navn);
                        }

                        else {
                            leger.put(navn, new Spesialist(navn, kontrollID));
                            System.out.println("Spesialist registrert: " + navn);
                        }
                    }
                }

                if (header.startsWith("# Resepter")) {
                    Legemiddel lm = null;
                    int reit = 0;

                    /**
                     * Går ut av løkken dersom det ikke er fler linjer i filen
                     */
                    while(true) {
                        if (!(fil.hasNextLine())) {break;}
                        String linje = fil.nextLine();

                        String[] reseptInfo = linje.split(", ");
                        String type = reseptInfo[0].trim();
                        int legemiddelID = Integer.parseInt(reseptInfo[1].trim());
                        String legeNavn = reseptInfo[2].trim();
                        int pasientID = Integer.parseInt(reseptInfo[3].trim());

                        if (!(type.equals("prevensjon"))) {
                            reit = Integer.parseInt(reseptInfo[4].trim());
                        }

                        else {reit = 3;}

                        for (Legemiddel l : legemidler) {
                            if (l.hentId() == legemiddelID) {lm = l;}
                        }

                        Lege utskrivendeLege = leger.get(legeNavn);
                        try {
                            resepter.add(utskrivendeLege.skrivResept(lm, pasientID, reit));
                        } catch (UlovligUtskrift e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        fil.close();
    }

}
