package me.frikk.oblig5utenstreng;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Labyrint {
    private Rute[][] labyrint;
    private int kolonner;
    private int rader;

    private Labyrint(Rute[][] labyrint, int rader, int kolonner) {
        this.labyrint = labyrint;
        this.rader = rader;
        this.kolonner = kolonner;
    }

    /**
     * Finner alle utveiene fra en gitt rute vha. kall på finnUtvei
     * @param kolonne
     * @param rad
     * @return liste av liste med ruter til utveier
     */
    public List<List<Rute>> finnUtveiFra(int kolonne, int rad) {
        // Returnerer tom liste dersom koordinatene er utenfor rekkevidde
        if (kolonne >= kolonner || rad >= rader) {
            return new ArrayList<>();
        }
        Rute startRute = this.labyrint[rad][kolonne];
        startRute.losninger = new ArrayList<>();
        startRute.finnUtvei();
        return startRute.losninger;
    }

    /**
     * @return string-representasjon av 2d-arrayet til labyrinten
     */
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

    /**
     * Static factory-metode som leser en fil og oppretter et labyrintobjekt
     * Setter alle ruter sine naboer
     * @param fil
     * @return labyrint-objekt
     * @throws FileNotFoundException
     */
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

    /**
     * Setter alle rutene i labyrinten sine naboer
     */
    public void settNaboer() {
        for (int rad = 0; rad < rader; rad++) {
            for (int kolonne = 0; kolonne < kolonner; kolonne++) {
                Rute rute = labyrint[rad][kolonne];
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
