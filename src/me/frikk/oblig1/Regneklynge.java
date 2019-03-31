package me.frikk.oblig1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Regneklynge {

    private ArrayList<Rack> racks;
    private int noderPrRack;

    public Regneklynge(int maxNoder) {
        racks = new ArrayList<>();
        noderPrRack = maxNoder;
    }
    /**
     * Konstruktør
     * Leser tekstfilen, setter max noder pr. rack
     * Oppretter node-objekter og setter inn i regneklyngen
     * @param filnavn
     */
    public Regneklynge(String filnavn) {
        racks = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filnavn));
            noderPrRack = Integer.parseInt(br.readLine().trim());
            String line;
            while ((line = br.readLine()) != null) {
                String[] specs = line.split(" ");
                Integer antallNoder = Integer.parseInt(specs[0].trim());
                Integer minnePrNode = Integer.parseInt(specs[1].trim());
                Integer prosessorer = Integer.parseInt(specs[2].trim());
                for(int i=0; i < antallNoder; i++) {
                    settInnNode(new Node(minnePrNode, prosessorer));
                }
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Setter inn en node i racket hvis det er ledig plass, eller oppretter et nytt
     * dersom racket er fullt (eller det ikke finnes noen racks enda)
     * @param node
     */
    public void settInnNode(Node node) {
        if (racks.isEmpty() || racks.get(racks.size() - 1).getAntNoder() >= noderPrRack) {
            racks.add(new Rack());
        }
        Rack rack = racks.get(racks.size() - 1);

        rack.settInn(node);
    }

    /**
     * @return  sum av alle prosessorer i regneklyngen
     */
    public int sumProsessorer() {
        return racks.stream()
            .mapToInt(rack -> rack.getProsessorer())
            .sum();
    }

    /**
     * Returnerer antall noder i regneklyngen med tilstrekkelig minne
     * @param reqMinne
     * @return
     */
    public int noderMedNokMinne(int reqMinne) {
        return racks.stream()
            .mapToInt(rack -> rack.noderMedNokMinne(reqMinne))
            .sum();
    }

    public int antRacks() {
        return racks.size();
    }
}
