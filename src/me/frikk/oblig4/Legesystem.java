package me.frikk.oblig4;

import java.util.*;
import java.io.*;

public class Legesystem{
    private SortertLenkeliste<Lege> leger = new SortertLenkeliste<>();
    private Lenkeliste<Pasient> pasienter = new Lenkeliste<>();
    private Lenkeliste<Legemiddel> legemidler = new Lenkeliste<>();
    private Lenkeliste<Resept> resepter = new Lenkeliste<>();
    
    public static void main(String[] args){
        File innFil = new File("/Users/frikk/Documents/2.semester/IN1010/JavaProjects/me.frikk.obliger/src/me/frikk/oblig4/skrivefil.txt");
        Legesystem legesystem = new Legesystem();
        legesystem.lesFraFil(innFil);
        Meny meny = new Meny(legesystem, innFil);
        meny.hovedmeny();
    }

    public SortertLenkeliste<Lege> hentLegeliste() {
        return leger; 
    }

    public Lenkeliste<Pasient> hentPasientListe() {
        return pasienter;
    }

    public Lenkeliste<Legemiddel> hentLegemiddelListe() {
        return legemidler;
    }

    public Lenkeliste<Resept> hentReseptListe() {
        return resepter;
    }

    private void lesFraFil(File filnavn){
        Scanner fil = null;
        try{
            fil = new Scanner(filnavn);
        }catch(FileNotFoundException e){
            System.out.println("Fant ikke filen, starter opp som et tomt Legesystem");
            return;
        }

        String linje = fil.nextLine();


        while(fil.hasNextLine()){

            String[] info = linje.split(" ");

            // Legger til alle pasientene i filen
            if(info[1].compareTo("Pasienter") == 0){
                while(fil.hasNextLine()) {
                    linje= fil.nextLine();

                    if(linje.charAt(0) == '#'){
                        break;
                    }

                    String[] pasientInfo = linje.split(", ");
                    String navn = pasientInfo[0].trim();
                    String fodselsNummer = pasientInfo[1].trim();

                    pasienter.leggTil(new Pasient(navn, fodselsNummer));
                }

            }
            //Legger inn Legemidlene
            else if(info[1].compareTo("Legemidler") == 0){
                while(fil.hasNextLine()){
                    linje= fil.nextLine();

                    if(linje.charAt(0) == '#'){
                        break;
                    }
                    String[] legemiddelInfo = linje.split(", ");
                    String navn = legemiddelInfo[0].trim();
                    String type = legemiddelInfo[1].trim();
                    double pris = Double.parseDouble(legemiddelInfo[2].trim());
                    double virkestoff = Double.parseDouble(legemiddelInfo[3].trim());

                    if (type.equals("a")) {
                        int styrke = Integer.parseInt(legemiddelInfo[4].trim());
                        legemidler.leggTil(new PreparatA(navn, pris, virkestoff, styrke));
                    }

                    if (type.equals("b")) {
                        int styrke = Integer.parseInt(legemiddelInfo[4].trim());
                        legemidler.leggTil(new PreparatB(navn, pris, virkestoff, styrke));
                    }

                    if (type.equals("c")) {
                        legemidler.leggTil(new PreparatC(navn, pris, virkestoff));
                    }

                }
            }
            //Legger inn leger
            else if(info[1].compareTo("Leger") == 0){
                while(fil.hasNextLine()){
                    linje= fil.nextLine();

                    if(linje.charAt(0) == '#'){
                        break;
                    }

                    String[] legeInfo = linje.split(", ");
                    String navn = legeInfo[0].trim();
                    int kontrollID = Integer.parseInt(legeInfo[1].trim());

                    if (kontrollID == 0){
                        leger.leggTil(new Lege(navn));

                    } else {
                        leger.leggTil(new Spesialist(navn, kontrollID));

                    }
                }

            }
            //Legger inn Resepter
            else if(info[1].compareTo("Resepter") == 0){
                while(fil.hasNextLine()){
                    linje = fil.nextLine();
                    String[] reseptInfo = linje.split(", ");

                    String reseptType = reseptInfo[0].trim();
                    int legemiddelID = Integer.parseInt(reseptInfo[1].trim());
                    String legeNavn = reseptInfo[2].trim();
                    int pasientID = Integer.parseInt(reseptInfo[3].trim());
                    int reit = 0;

                    if (reseptInfo.length == 5) {
                        reit = Integer.parseInt(reseptInfo[4].trim());
                    }

                    Legemiddel legemiddel = finnLegemiddel(legemiddelID);
                    Lege lege = finnLege(legeNavn);
                    Pasient pasient = finnPasient(pasientID);

                    try {
                        resepter.leggTil(lege.skrivResept(ReseptType.fraTekst(reseptType), legemiddel, pasient, reit));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
        fil.close();
    }
    
    private Legemiddel finnLegemiddel(int legemiddelID) {
        for (Legemiddel legemiddel : legemidler) {
            if (legemiddel.hentLegemiddelID() == legemiddelID) {
                return legemiddel;
            }
        }
        return null;
    }

    private Lege finnLege(String navn) {
        for (Lege lege : leger) {
            if (lege.hentNavn().equals(navn)) {
                return lege;
            }
        }
        return null;
    }

    private Pasient finnPasient(int pasientID) {
        for (Pasient pasient : pasienter) {
            if (pasient.hentPasientID() == pasientID) {
                return pasient;
            }
        }
        return null;
    }
}

