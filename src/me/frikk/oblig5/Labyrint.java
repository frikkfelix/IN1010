package me.frikk.oblig5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
//import java.util.stream.*;
import java.util.stream.Collectors;

class Labyrint {
    protected Rute[][] labyrint;
    protected ArrayList<Rute> besokt = new ArrayList<>();

    protected int kolonner;
    protected int rader;

    private Labyrint(Rute[][] ruter, int rader, int kolonner) {
        labyrint = ruter;
        this.rader = rader;
        this.kolonner = kolonner;
    }

    public static void main(String[] args) {
        Labyrint labyrint;
        try {
            labyrint = Labyrint.lesFraFil(new File("/Users/frikk/Documents/2.semester/IN1010/JavaProjects/me.frikk.obliger/src/me/frikk/oblig5/5.in"));
            System.out.println(labyrint);
            

            Liste<String> los = labyrint.finnUtveiFra(2, 73);

            //los.stream().forEach(l -> System.out.println(l));
            for (String string : los) {
                System.out.println(string);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* public List<String> finnUtveiFra(int kolonne, int rad) {
        Rute startRute = this.labyrint[rad][kolonne];
        startRute.gaa();
        startRute.finnUtvei();

        if(startRute.losninger.size() == 0) {
            return Collections.singletonList("Ingen løsninger");
        }
        return startRute.losninger.stream()
            .map(losning -> losning.stream()
                .map(rute -> String.format("(%s, %s)", rute.kolonne + 1, rute.rad + 1))
                .collect(Collectors.joining(" --> ")))
            .collect(Collectors.toList());
    } */

    public Liste<String> finnUtveiFra(int kolonne, int rad) {
        Rute startRute = this.labyrint[rad][kolonne];
        Liste<String> stringListe = new Lenkeliste<>();

        if (startRute.sort) {
            return stringListe;
        }

        startRute.gaa();
        startRute.finnUtvei();

        startRute.losninger.stream()
            .map(losning -> losning.stream()
                .map(rute -> String.format("(%s, %s)", rute.kolonne, rute.rad))
                .collect(Collectors.joining(" --> ")))
            .forEach(stringListe::leggTil);

        return stringListe;
    }

    public String toString() {
        StringBuilder string = new StringBuilder();
        for (Rute[] ruteArray : labyrint) {
            for (Rute rute : ruteArray) {
                string.append(rute.tilTegn());
            }
            string.append("\n");
        }
        return string.toString();      
    }

    static Labyrint lesFraFil(File fil) throws FileNotFoundException {
        Scanner scanner = new Scanner(fil);
        
        String[] header = scanner.nextLine().split(" ");
        int rader = Integer.parseInt(header[0]);
        int kolonner = Integer.parseInt(header[1]);
        
        Rute[][] ruter = new Rute[rader][kolonner];

        for (int rad = 0; rad < rader; rad++) {
            char[] tegn = scanner.nextLine().toCharArray();
            
            for (int kolonne = 0; kolonne < kolonner; kolonne++) {
                Rute rute = null;
                if (tegn[kolonne] == '#') {
                    rute = new SortRute(rad, kolonne);
                }
                if (tegn[kolonne] == '.') {
                    rute = erAapning(rad, kolonne, rader, kolonner) 
                        ? new Aapning(rad, kolonne) 
                        : new HvitRute(rad, kolonne);
                }

                ruter[rad][kolonne] = rute;
            }
        }
        scanner.close();
        
        
        Labyrint labyrint = new Labyrint(ruter, rader, kolonner);
        labyrint.settNaboer();

        return labyrint;
    }

    public static boolean erAapning(int rad, int kolonne, int rader, int kolonner) {
        return (rad == 0 || kolonne == 0 || rad == rader - 1 || kolonne == kolonner - 1);
    }

    public void settNaboer() {
        for (int rad = 0; rad < rader; rad++) {
            for (int kolonne = 0; kolonne < kolonner; kolonne++) {
                Rute rute = labyrint[rad][kolonne];
                Rute[][] labyrint = this.labyrint;
                rute.settLabyrint(this);
                if (rad < rader - 1) {
                    rute.settSyd(labyrint[rad + 1][kolonne]);
                }
                if (kolonne < kolonner - 1) {
                    rute.settOest(labyrint[rad][kolonne + 1]);
                }
                if (rad > 0) {
                    rute.settNord(labyrint[rad - 1][kolonne]);
                }
                if (kolonne > 0) {
                    rute.settVest(labyrint[rad][kolonne - 1]);
                }
            }  
        }
        return;
    }
}
