package me.frikk.oblig4;

import java.util.*;
import java.io.*;

/**
 * Klassen meny brukes til å håndtere brukers interaksjon med programmet
 */
public class Meny{
    private SortertLenkeliste<Lege> leger;
    private Lenkeliste<Pasient> pasienter;
    private Lenkeliste<Legemiddel> legemidler;
    private Lenkeliste<Resept> resepter;
    private File innFil;

    public Meny(Legesystem legesystem, File innFil) {
        this.leger = legesystem.hentLegeliste();
        this.pasienter = legesystem.hentPasientListe();
        this.legemidler = legesystem.hentLegemiddelListe();
        this.resepter = legesystem.hentReseptListe();
        this.innFil = innFil;
    }
    
    /**
     * METODER SOM HÅNDTERER HOVEDMENYEN
     */
    public void hovedmeny() {
        Scanner tastatur = new Scanner(System.in);
        boolean fortsett = true;

        while (fortsett) {
            skrivHovedmeny();
            try {
                int input = Integer.parseInt(tastatur.nextLine());
                switch (input) {
                    case 0:
                        fortsett = false;
                        break;
                    case 1: 
                        skrivOversikt();
                        break;
                    case 2: 
                        leggTilMeny(tastatur);
                        break;
                    case 3:
                        brukResept(tastatur);
                        break;
                    case 4:
                        skrivStatistikk();
                        break;
                    case 5:
                        skrivTilFil(innFil);
                        break;
                    default:
                        System.out.println("Ugyldig input");
                }
            } catch (NumberFormatException e) {
                System.out.println("Inntastet verdi må være et heltall");
            }

        } 

        tastatur.close();
        System.exit(0);
    }

    private void skrivHovedmeny() {
        System.out.println("------------------------------");
        System.out.println("|       VELKOMMEN TIL        |");
        System.out.println("|      IFIs LEGESYSTEM       |");
        System.out.println("------------------------------");
        System.out.println("\nVennligst velg et alternativ:");
        System.out.println("1: Skriv ut fullstendig oversikt");
        System.out.println("2: Legg til i systemet");
        System.out.println("3: Bruk resept");
        System.out.println("4: Statistikk");
        System.out.println("5: Lagre endringer");
        System.out.println("\n0: Avslutt");
    }

    private void skrivLeggTilMeny() {
        System.out.println("\nVennligst velg et alternativ:");
        System.out.println("1: Legg til lege");
        System.out.println("2: Legg til spesialist");
        System.out.println("3: Legg til pasient");
        System.out.println("4: Legg til resept");
        System.out.println("5: Legg til legemiddel");
        System.out.println("\n0: Gå tilbake til hovedmenyen");
    }

    private void leggTilMeny(Scanner tastatur) {
        boolean fortsett = true;

        while (fortsett) {
            skrivLeggTilMeny();
            try {
                int input = Integer.parseInt(tastatur.nextLine());
                switch (input) {
                    case 0:
                    fortsett = false; 
                    return;
                    case 1: leggTilLege(tastatur);
                    break;
                    case 2: leggTilSpesialist(tastatur);
                    break;
                    case 3: leggTilPasient(tastatur);
                    break;
                    case 4: leggTilResept(tastatur);
                    break;
                    case 5: leggTilLegemiddel(tastatur);
                    break;
                    default: System.out.println("Ugyldig input");
                }
            } catch (NumberFormatException e) {
                System.out.println("Inntastet verdi må være et heltall");
            }
        }
    }

    /**
     * Metode som skriver ut oversikt over alle objektene i systemet
     */
    private void skrivOversikt() {
        System.out.println("\n-   Leger   -\n");
        for (Lege lege : leger){
            System.out.println(lege);
        }

        System.out.println("\n-   Pasienter   -");
        for (Pasient pasient : pasienter){
            System.out.println("\n" + pasient);
        }

        System.out.println("\n-   Legemidler   -");
        for (Legemiddel legemiddel : legemidler){
            System.out.println("\n" + legemiddel);
        }

        System.out.println("\n-   Resepter   -");
        for (Resept resept : resepter){
            System.out.println("\n" + resept);
        }
    }

    /**
     * METODER FOR Å LEGGE TIL LEGER, PASIENTER OG LEGEMIDLER
     */
    private void leggTilLege(Scanner tastatur) {
        System.out.println("Vennligst oppgi legens navn:");
        String navn = tastatur.nextLine();
        leger.leggTil(new Lege(navn));
        return;
    }

    private void leggTilSpesialist(Scanner tastatur) {
        boolean fortsett = true;
        System.out.println("Vennligst oppgi spesialistens navn:");
        String navn = tastatur.nextLine();

        while (fortsett) {
            try {
                System.out.println("Vennligst oppgi spesialistens kontroll-ID:");
                int kontrollID = Integer.parseInt(tastatur.nextLine());
                leger.leggTil(new Spesialist(navn, kontrollID));
                fortsett = false;
                return;
            } catch (NumberFormatException e) {
                System.out.println("Ugyldig input");
            }
        }
    }

    private void leggTilPasient(Scanner tastatur) {
        System.out.println("Vennligst oppgi pasientens navn:");
        String navn = tastatur.nextLine();
        System.out.println("Vennligst oppgi pasientens fødselsnummer:");
        String fodselsNummer = tastatur.nextLine();

        pasienter.leggTil(new Pasient(navn, fodselsNummer));
        System.out.println("Pasienten " + navn + " er lagt til i systemet.");
        return;
    }

    /**
     * METODER FOR Å LEGGE TIL LEGEMIDLER
     */
    private void skrivPreparatMeny() {
        System.out.println("\nVelg type preparat som skal opprettes:");
        System.out.println("1: A-Preparat");
        System.out.println("2: B-Preparat");
        System.out.println("3: C-Preparat");
    }

    private void leggTilLegemiddel(Scanner tastatur) {
        String preparat = "";
        boolean fortsett = true;
        double pris;
        double virkestoff;
        int styrke;

        while (fortsett) {
            skrivPreparatMeny();
            try {
                int input = Integer.parseInt(tastatur.nextLine());
                switch (input) {
                    case 1: preparat = "A";
                    fortsett = false; 
                    break;
                    case 2: preparat = "B";
                    fortsett = false; 
                    break;
                    case 3: preparat = "C";
                    fortsett = false; 
                    break;
                    default: System.out.println("Ugyldig input");
                }
            } catch (NumberFormatException e) {
                System.out.println("Inntastet verdi må være et heltall");
            }
        }

        System.out.println("Oppgi navnet på legemiddelet:");
        String navn = tastatur.nextLine();

        while (true) {
            System.out.println("Oppgi pris på legemiddelet:");
            try {
                pris = Double.parseDouble(tastatur.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Inntastet verdi må være et tall");
            }
        }

        while (true) {
            System.out.println("Oppgi mengde virkestoff:");
            try {
                virkestoff = Double.parseDouble(tastatur.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Inntastet verdi må være et tall");
            }
        }

        if (preparat.equals("A") || preparat.equals("B")) {
            while (true) {
                System.out.println("Oppgi styrke på legemiddelet:");
                try {
                    styrke = Integer.parseInt(tastatur.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Inntastet verdi må være et tall");
                }
            }
            if (preparat.equals("A")) {
                legemidler.leggTil(new PreparatA(navn, pris, virkestoff, styrke));
            }
            if (preparat.equals("B")) {
                legemidler.leggTil(new PreparatB(navn, pris, virkestoff, styrke));
            }
        } else {
            legemidler.leggTil(new PreparatC(navn, pris, virkestoff));
        }
    }

    /**
     * METODER FOR Å LEGGE TIL RESEPTER
     */
    private void skrivReseptMeny() {
        System.out.println("\nVelg type resept som skal skrives:");
        System.out.println("1: Hvit Resept");
        System.out.println("2: Blå Resept");
        System.out.println("3: Militærresept");
        System.out.println("4: P-Resept");
    }

    private ReseptType velgReseptType(Scanner tastatur) {
        ReseptType reseptType = null;

        boolean fortsett = true;

        while (fortsett) {
            skrivReseptMeny();
            try {
                int input = Integer.parseInt(tastatur.nextLine());
                switch (input) {
                    case 1: reseptType = ReseptType.HVIT;
                    fortsett = false;
                    break;
                    case 2: reseptType = ReseptType.BLAA;
                    fortsett = false; 
                    break;
                    case 3: reseptType = ReseptType.MILITAER;
                    fortsett = false; 
                    break;
                    case 4: reseptType = ReseptType.PREVENSJON;
                    fortsett = false;
                    default: System.out.println("Ugyldig input");
                }
            } catch (NumberFormatException e) {
                System.out.println("Inntastet verdi må være et heltall");
            }
        }
        System.out.println(reseptType.hentTekst());
        return reseptType;
    }

    // Metoder for å velge lege som skal skrive ut resept
    private void skrivLegeMeny() {
        int teller = 1;
        System.out.println("Velg en lege:");
        for (Lege lege : leger) {
            System.out.println(teller + ": " + lege.hentNavn());
            teller++;
        }
    }
    
    private Lege velgLege(Scanner tastatur) {
        boolean fortsett = true;

        while (fortsett) {
            skrivLegeMeny();
            try {
                int input = Integer.parseInt(tastatur.nextLine());
                try {
                    Lege lege = leger.hent(input - 1);
                    System.out.println(lege);
                    return lege;
                } catch (UgyldigListeIndeks e){
                    System.out.println("Ugyldig input");
                }
            } catch (NumberFormatException e) {
                System.out.println("Inntastet verdi må være et heltall");
            }
        }
        return null;
    }

    // Metoder for å velge hvilken pasient resepten skal skrives ut til
    private void skrivPasientMeny() {
        int teller = 1;
        System.out.println("Velg en pasient:");
        for (Pasient pasient : pasienter) {
            System.out.println(teller + ": " + pasient.hentNavn());
            teller++;
        }
    }

    private Pasient velgPasient(Scanner tastatur) {
        boolean fortsett = true;

        while (fortsett) {
            skrivPasientMeny();
            try {
                int input = Integer.parseInt(tastatur.nextLine());
                try {
                    Pasient pasient = pasienter.hent(input - 1);
                    System.out.println(pasient);
                    return pasient;
                } catch (UgyldigListeIndeks e){
                    System.out.println("Ugyldig input");
                }
            } catch (NumberFormatException e) {
                System.out.println("Inntastet verdi må være et heltall");
            }
        }
        return null;
    }

    // Metoder for å velge legemiddel
    private void skrivLegemiddelMeny() {
        int teller = 1;
        System.out.println("Velg et legemiddel:");
        for (Legemiddel legemiddel : legemidler) {
            System.out.println(teller + ": " + legemiddel.hentNavn());
            teller++;
        }
    }

    private Legemiddel velgLegemiddel(Scanner tastatur) {
        boolean fortsett = true;

        while (fortsett) {
            skrivLegemiddelMeny();
            try {
                int input = Integer.parseInt(tastatur.nextLine());
                try {
                    Legemiddel legemiddel = legemidler.hent(input - 1);
                    return legemiddel;
                } catch (UgyldigListeIndeks e){
                    System.out.println("Ugyldig input");
                }
            } catch (NumberFormatException e) {
                System.out.println("Inntastet verdi må være et heltall");
            }
        }
        return null;
    }

    // Metode for å legge til og skrive resepten
    private void leggTilResept(Scanner tastatur) {
        int reit = 0;
        boolean fortsett = true;

        Lege lege = velgLege(tastatur);
        Pasient pasient = velgPasient(tastatur);
        ReseptType reseptType = velgReseptType(tastatur);
        Legemiddel legemiddel = velgLegemiddel(tastatur);

        if (!(reseptType == ReseptType.PREVENSJON)) {
            while (fortsett) {
                System.out.println("Oppgi antall reit:");
                try {
                    reit = Integer.parseInt(tastatur.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Inntastet verdi må være et heltall");
                }
            }
        }
        try {
            resepter.leggTil(lege.skrivResept(reseptType, legemiddel, pasient, reit));
            System.out.println("Resept lagt til");
        } catch (UlovligUtskrift e) {
            System.out.println(e.getMessage());
        }
        
    }

    /**
     *  METODER FOR Å BRUKE RESEPTER
     */
    private void skrivPasientensResepter(Pasient pasient) {
        int teller = 0;

        System.out.println("Valgt pasient: " + pasient.hentNavn());
        System.out.println("Hvilken resept vil du bruke?");
        for (Resept resept : pasient.hentReseptListe()) {
            System.out.println(String.format("%d: %s (%d reit)", teller, resept.hentLegemiddel().hentNavn(), resept.hentReit()));
            teller++;
        }
    }
    
    private Resept velgResept(Scanner tastatur, Pasient pasient) {
        boolean fortsett = true;

        while (fortsett) {
            skrivPasientensResepter(pasient);
            try {
                int input = Integer.parseInt(tastatur.nextLine());
                try {
                    Resept resept = pasient.hentReseptListe().hent(input);
                    System.out.println(resept);
                    return resept;
                } catch (UgyldigListeIndeks e){
                    System.out.println("Ugyldig input");
                }
            } catch (NumberFormatException e) {
                System.out.println("Inntastet verdi må være et heltall");
            }
        }
        return null;
    }

    private void brukResept(Scanner tastatur) {
        Pasient pasient = velgPasient(tastatur);
        if (pasient.hentReseptListe().stoerrelse() > 0) {
            Resept resept = velgResept(tastatur, pasient);
            resept.bruk();
            System.out.println(String.format("Brukte resept på %s. Antall gjenværende reit: %d", resept.hentLegemiddel().hentNavn(), resept.hentReit()));    
        } else {
            System.out.println("Pasienten har ingen resepter!");
        }
       
    }

    /**
     *  SKRIV STATISTIKK
     */
    private void skrivStatistikk() {
        System.out.println("------------------------------");
        System.out.println("          STATISTIKK          ");
        System.out.println("------------------------------\n");
        System.out.println("Antall utsktrevne resepter på"); 
        System.out.println("Narkotiske legemidler: " + Lege.narkotiskeResepter);
        System.out.println("Vanedannende legemidler: " + Lege.vanedannendeResepter);
        narkotikaStatistikk();
    }

    private void narkotikaStatistikk() {
        System.out.println("\nLeger som har skrevet ut resepter\npå narkotiske legemidler:");
        for (Lege lege : leger) {
            if (lege.dennesNarkotiske > 0) {
                System.out.println(lege.hentNavn() + ": " + lege.dennesNarkotiske);
            } 
        }
        
        System.out.println("\nPasienter som har resepter\npå narkotiske legemidler:");
        for (Pasient pasient : pasienter) {
            int narkotiskeResepterPasient = 0;
            for (Resept resept : pasient.hentReseptListe()) {
                if (resept.hentLegemiddel() instanceof PreparatA) {
                    narkotiskeResepterPasient++;
                }
            }
            if (narkotiskeResepterPasient > 0) {
                System.out.println(pasient.hentNavn() + ": " + narkotiskeResepterPasient);
            }
        }
    }

    /**
     * SKRIV TIL FIL
     */
    private void skrivTilFil(File fil) {
        try {
            PrintWriter skriver = new PrintWriter(fil);
            skrivPasienterTilFil(skriver);
            skrivLegemidlerTilFil(skriver);
            skrivLegerTilFil(skriver);
            skrivResepterTilFil(skriver);
            System.out.println("Endringer lagret");
            skriver.close();
        } catch (FileNotFoundException e) {
            System.out.println("Finner ikke filen");
        }
    }

    private void skrivPasienterTilFil(PrintWriter skriver) {
        skriver.append("# Pasienter\n");

        for (Pasient pasient : pasienter) {
            skriver.append(String.format("%s, %s\n", pasient.hentNavn(), pasient.hentFodselsnummer()));
        }
    }

    private void skrivLegemidlerTilFil(PrintWriter skriver) {
        skriver.append("# Legemidler (navn, type, pris, virkestoff [, styrke])\n");

        for (Legemiddel legemiddel : legemidler) {
            String navn = legemiddel.hentNavn();
            double pris = legemiddel.hentPris();
            double virkestoff = legemiddel.hentVirkestoff();

            if (legemiddel instanceof PreparatA) {
                PreparatA aPreparat = (PreparatA) legemiddel;
                skriver.append(String.format(Locale.ROOT, "%s, a, %.2f, %.2f, %d\n", navn, pris, virkestoff, aPreparat.hentNarkotiskStyrke()));
            }

            if (legemiddel instanceof PreparatB) {
                PreparatB bPreparat = (PreparatB) legemiddel;
                skriver.append(String.format(Locale.ROOT, "%s, b, %.2f, %.2f, %d\n", navn, pris, virkestoff, bPreparat.hentVanedannendeStyrke()));
            }

            if (legemiddel instanceof PreparatC) {
                skriver.append(String.format(Locale.ROOT, "%s, c, %.2f, %.2f\n", navn, pris, virkestoff));
            }
        }

    }

    private void skrivLegerTilFil(PrintWriter skriver) {
        skriver.append("# Leger (navn, kontrollid / 0 hvis vanlig lege)\n");
        int kontrollID;

        for (Lege lege : leger) {
            if (lege instanceof Spesialist) {
                Spesialist spesialist = (Spesialist) lege;
                kontrollID = spesialist.hentKontrollID();
            } else {
                kontrollID = 0;
            }
            skriver.append(String.format("%s, %d\n", lege.hentNavn(), kontrollID));
        } 
    }
    
    private void skrivResepterTilFil(PrintWriter skriver) {
        skriver.append("# Resepter (reseptType, legemiddelNummer, legeNavn, pasientID, reit)\n");

        for (Resept resept : resepter) {
            String reseptType = resept.reseptType.hentTekst();
            int legemiddelNummer = resept.hentLegemiddel().hentLegemiddelID();
            String legeNavn = resept.hentLege().hentNavn();
            int pasientID = resept.hentPasientID();
            int reit = resept.hentReit();
            if (resept instanceof PResept) {
                skriver.append(String.format("%s, %d, %s, %d\n", reseptType, legemiddelNummer, legeNavn, pasientID));
            } else {
                skriver.append(String.format("%s, %d, %s, %d, %d\n", reseptType, legemiddelNummer, legeNavn, pasientID, reit));
            }
        }
    }
}   

