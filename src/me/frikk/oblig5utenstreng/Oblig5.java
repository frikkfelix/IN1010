package me.frikk.oblig5utenstreng;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

class Oblig5 {
    public static void main(String[] args) {
        String filnavn = null;

        if (args.length > 0) {
            filnavn = args[0];
        } else {
            System.out.println("FEIL! Riktig bruk av programmet: "
                               +"java Oblig5 <labyrintfil>");
            return;
        }
        File fil = new File(filnavn);
        Labyrint l = null;
        try {
            l = Labyrint.lesFraFil(fil);
        } catch (FileNotFoundException e) {
            System.out.printf("FEIL: Kunne ikke lese fra '%s'\n", filnavn);
            System.exit(1);
        }
        System.out.println(l);
        // les start-koordinater fra standard input
        Scanner inn = new Scanner(System.in);
        System.out.println("Skriv inn koordinater <kolonne> <rad> ('a' for aa avslutte)");
        String[] ord = inn.nextLine().split(" ");
        while (!ord[0].equals("a")) {

            try {
                int startKol = Integer.parseInt(ord[0]);
                int startRad = Integer.parseInt(ord[1]);
                long start = System.nanoTime();
                
                List<List<Rute>> utveier = l.finnUtveiFra(startKol, startRad);
                
                if (utveier.size() != 0) {
                    for (List<Rute> s : utveier) {
                        for (Rute r : s) {
                            System.out.print(r);
                        }
                        System.out.println("\n\n");
                    }
                    System.out.println("Antall utveier: " + utveier.size());
                } else {
                    System.out.println("Ingen utveier.");
                }
                long slutt = System.nanoTime();
                // Måler hvor lang tid det tok å finne og skrive ut utveiene
                long tidsforbruk = slutt - start;
                System.out.println("Tidsforbruk: " + tidsforbruk/1000000 + " ms");
                System.out.println();
            } catch (NumberFormatException e) {
                System.out.println("Ugyldig input!");
            }
            
            System.out.println("Skriv inn nye koordinater ('a' for aa avslutte)");
            ord = inn.nextLine().split(" ");
        }
        inn.close();
    }
}
