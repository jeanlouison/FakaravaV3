package control;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import ecosystem.*;
import gui.*;

public class Fakarava {
    /**
     * @attribute
     */
    public static Boolean end = false;

    /**
     * @attribute
     */
    private static Boolean isDay = false;

    public static void clockForward() {
        if(Fakarava.isDay)
        {
            Fakarava.isDay = false;
        }
        else {
            Fakarava.isDay = true;
        }

    
        //Verificatinon du game over
        if (Lagoon.getPreyList().size() > (Prey.getMaxDensity() * (Lagoon.getLength() * Lagoon.getLength()))
                || Lagoon.getPreyList().isEmpty()) {

            //creation des fichiers a la fin de la simulation
            for (Diver d : Lagoon.getDiversList()){
                String nomFichierLog = "log-"+d.getName()+".txt";
                
                try {
                    File file = new File(nomFichierLog);
                    file.createNewFile();
                    FileWriter fw = new FileWriter(file);
                    BufferedWriter bw = new BufferedWriter(fw);
                    
                    for (String line : d.getLog()) {
                        bw.write(line+"\r\n");
                    }
                    
                    bw.flush();
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            
            end = true;
        }

        

        int tailleFishesList = Lagoon.getFishesList().size();

        //ticktock des poissons
        for (int i = 0; i < tailleFishesList; i++) {
            Fish f = Lagoon.getFishesList().get(i);
            // System.out.println("nbr prey"+Lagoon.getPreyList().size());
            if (f != null) {
                f.tickTock(isDay);
            }
            if (tailleFishesList != Lagoon.getFishesList().size()) {
                tailleFishesList = Lagoon.getFishesList().size();
            }
        }

        //Verification des poissons qui passent dans les passes
        for (Fishway f : Lagoon.getFishwaysList()) {
            if (f != null) {
                f.getPredatorList().clear();
                f.getPreyList().clear();
                
                for (Predator pr : Lagoon.getPredatorList()) {
                    
                    if (pr.getPosition()[0] == f.getPosition()[0] && pr.getPosition()[1] == f.getPosition()[1]) {
                        f.addPredator(pr.getNum());
                    }
                }
                for (Prey p : Lagoon.getPreyList()) {
                    
                    if (p.getPosition()[0] == f.getPosition()[0] && p.getPosition()[1] == f.getPosition()[1]) {
                        f.addPrey(p.getNum());
                    }
                }
                //ticktock des passes
                f.tickTock(isDay);
            }
        }

        int tailleDiversList = Lagoon.getDiversList().size();

        //ticktock des plongeurs
        for (int i = 0; i < tailleDiversList; i++) {
            Diver d = Lagoon.getDiversList().get(i);
            if (d != null) {
                d.tickTock(isDay);
            }
            if (tailleDiversList != Lagoon.getDiversList().size()) {
                tailleDiversList = Lagoon.getDiversList().size();
            }
        }

        LagoonPainter.resetGrid();
        LagoonPainter.updateFrame();

        try
        {
            Thread.sleep(1000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Cree la simulation
     * @param biteFactor 
     * @param maxCurrentStrength 
     * @param maxDensity 
     * @param n Taille de la lagune
     * @param predatorCloneTime
     * @param preyCloneTime
     * @param seed Graine pour l'aleatoire
     */
    public static void init(int biteFactor, int maxCurrentStrength, int maxDensity, int n, int predatorCloneTime, int preyCloneTime, Long seed) {
        Prey.setMaxDensity(maxDensity);
        Lagoon.setLagoonTable(n);
        Fishway.setMaxCurrentStrength(maxCurrentStrength);
        Prey.setPreyCloneTime(preyCloneTime);
        Predator.setPredatorCloneTime(predatorCloneTime);
        Predator.setBiteFactor(biteFactor);
        Random.mySeed = seed;

        LagoonPainter.createGrid();
    }

    /**
     * Cree une proie et l'ajoute a la lagune.
     * @param name Nom du poisson
     * @param weight Poids du poisson
     * @param x Coordonnes x du poisson
     * @param y Coordonnes y du poisson
     * @param vivacity Vivacite du poisson
     * @return L'id du poisson nouvellement cree.
     */
    public static int createPrey(String name, Double weight, int x, int y, int vivacity) {
        return Lagoon.addPrey(name, weight, x, y, vivacity);
    }

    /**
     * Cree un predateur et l'ajoute a la lagune.
     * @param name Nom du poisson
     * @param weight Poids du poisson
     * @param x Coordonnes x du poisson
     * @param y Coordonnes y du poisson
     * @return L'id du poisson nouvellement cree.
     */
    public static int createPredator(String name, Double weight, int x, int y) {
        return Lagoon.addPredator(name, weight, x, y);
    }

    /**
     * Cree une passe et l'ajoute a la lagune.
     * @param x Coordonnes x de la passe
     * @param y Coordonnes y de la passe
     * @return L'id de la passe nouvellement creee.
     */
    public static int createFishway(int x, int y) {
        return Lagoon.addFishway(x, y);
    }
    
    /**
     * Cree un plongeur et l'ajoute a la lagune.
     * @param name Nom du poisson
     * @param labo Ville de son laboratoire
     * @return L'id du plongeur nouvellement cree.
     */
    public static int createDiver(String name, String labo) {
        return Lagoon.addDiver(name, labo);
    }

    /**
     * Supprime le plongeur d'id passe en parametre
     * @param id id du plongeur a supprimer
     */
    public static void deleteDiver(int id) {
        Lagoon.deleteDiver(id);
    }

    /**
     * Pose une camera dans une passe (ajoute l'action dans la TODOlist du plongeur)
     * @param diver plongeur qui pose la camera
     * @param fishway passe ou mettre la camera
     */
    public static void putCamera(int diver, int fishway) {
        Lagoon.getDiver(diver).addTODO(fishway + " Camera");
    }

    /**
     * Pose un transmetteur sur les predateurs d'une passe (ajoute l'action dans la TODOlist du plongeur)
     * @param diver plongeur qui pose le transmetteur
     * @param fishway passe ou mettre le transmetteur
     */
    public static void putTransmitters(int diver, int fishway) {
        Lagoon.getDiver(diver).addTODO(fishway + " Transmitter");
    }

    /**
     * pour la reproduction : a chaque tour on regarde l'age des poissons, et on fait le calcul avec le modulo pour voir si ils se reproduisent.
     * Pour la chasse : on ajoute les infos dans une arraylist statique de Fishway.java, et spyReport() vient la chercher a chaque tour.
     * @return la liste des actions pour ce tour
     */
    public static String[] spyReport() {
        
        String[] spyReport = new String[Lagoon.getListeActions().size()];

        for (int i = 0; i < Lagoon.getListeActions().size(); i++){
            spyReport[i] = Lagoon.getListeActions().get(i);
        }
        //On vide la liste des actions
        Lagoon.clearListeActions();
        
        return spyReport;
    }
}
