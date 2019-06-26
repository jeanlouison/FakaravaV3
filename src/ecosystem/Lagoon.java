package ecosystem;

import java.awt.Color;
import java.util.ArrayList;

import gui.LagoonPainter;

/**
 * Classe gestionnaire des instances des poissons et passes a poissons.
 */
public class Lagoon {
    /**
     * @attribute
     */
    private static int[][] lagoonTable;
    
    /**
     * @attribute
     */
    static ArrayList<Fish> fishesList = new ArrayList<Fish>();

    /**
     * @attribute
     */
    static ArrayList<Fishway> fishwaysList = new ArrayList<Fishway>();

    /**
     * @attribute
     */
    static ArrayList<Diver> diversList = new ArrayList<Diver>();

    /**
     * @attribute
     */
    private static int nextFishNum = 0;

    /**
     * @attribute
     */
    private static int nextFishwayNum = 0;

    /**
     * @attribute
     */
    private static int nextDiverNum = 0;

    private static ArrayList<String> listeActions = new ArrayList<String>();

    /**
     * Instancie une proie, lui attribue un id et l'ajoute a la lagune.
     * @param name Nom du poisson
     * @param weight Poids du poisson
     * @param x Coordonnes x du poisson
     * @param y Coordonnes y du poisson
     * @param vivacity Vivacite du poisson
     * @return L'id du poisson nouvellement cree.
     */
    public static int addPrey(String name, Double weight, int x, int y, int vivacity) {
        Prey newPrey = new Prey(name, nextFishNum, weight, x, y, vivacity);
        
        fishesList.add(newPrey);
        nextFishNum++;
        return newPrey.getNum();
    }

    /**
     * Instancie un predateur, lui attribue un id et l'ajoute a la lagune.
     * @param name Nom du poisson
     * @param weight Poids du poisson
     * @param x Coordonnes x du poisson
     * @param y Coordonnes y du poisson
     * @return L'id du poisson nouvellement cree.
     */
    public static int addPredator(String name, Double weight, int x, int y) {
        Predator newPredator = new Predator(name, nextFishNum, weight, x, y);
        fishesList.add(newPredator);
        nextFishNum++;
        return newPredator.getNum();
    }

    /**
     * Instancie un plongeur, lui attribue un id et l'ajoute a la lagune.
     * @param name nom du plongeur
     * @param labo nom du laboratoire
     * @return L'id du plongeur nouvellement cree.
     */
    public static int addDiver(String name, String lab) {
        Diver newDiver = new Diver(name, nextDiverNum, lab);
        diversList.add(newDiver);
        nextDiverNum++;
        return newDiver.getId();
    }

    /**
     * Supprime le poisson d'id donne en parametre
     * @param num id du poisson a supprimer
     */
    public static void deleteFish(int num) {
        int i = 0;
        boolean found = false;
        while (i < fishesList.size() && found == false) {
            if (Lagoon.getFishesList().get(i).getNum() == num) {

                for (Fishway f : fishwaysList) { //On supprime d'abord le poisson de sa passe, si il est dans une telle passe
                    switch (Lagoon.getFishesList().get(i).getType()) {
                        case "Prey" :
                            if (f.getPreyList().contains(Lagoon.getFishesList().get(i))) {
                                f.deletePrey(num);
                            }
                        case "Predator" :
                            if (f.getPredatorList().contains(Lagoon.getFishesList().get(i))) {
                                f.deletePredator(num);
                            }
                    } 
                }
                
                Lagoon.fishesList.remove(i);
                found = true;
            }
            i++;
        }
    }

    /**
     * Supprime le plongeur d'id donne en parametre
     * @param num id du plongeur a supprimer
     */
    public static void deleteDiver(int num) {
        if (getDiversList().contains(Lagoon.getDiver(num))){    
            Lagoon.diversList.remove(Lagoon.getDiver(num));
        }
    }
    
    
    /**
     * Cherche dans la liste des plongeurs de la lagune et renvoie celui
     * dont le numero correspond a celui passe en parametre
     * @param num
     * @return le plongeur dont le numero correspond a celui passe en parametre
     */
    public static Diver getDiver(int num) {
        int i = 0;
        boolean found = false;
        Diver d = null;
        while (i < diversList.size() && found == false) {
            if (Lagoon.getDiversList().get(i).getId() == num) {
                d = Lagoon.getDiversList().get(i);
                found = true;
            }
            i++;
        }
        return d;
    }

    /**
     * @return the diversList
     */
    public static ArrayList<Diver> getDiversList() {
        return new ArrayList<Diver>(diversList);
    }

    /**
     * Donne la liste complete de tous les poissons de la lagune.
     * @return La liste des poissons de la lagune.
     */
    public static ArrayList<Fish> getFishesList() {
        return new ArrayList<Fish>(fishesList);
    }
    
    /**
     * Cherche les proies dans fishesList et renvoie une nouvelle ArrayList<Prey>
     * @return La liste des proies de la lagune
     */
    public static ArrayList<Prey> getPreyList() {
        ArrayList<Prey> preyList = new ArrayList<Prey>();
        for (Fish i : fishesList) {
            if (i.getType() == "Prey") {
                preyList.add((Prey)i);
            }
        }
        return preyList;
    }
    
    /**
     * Cherche les predateurs dans fishesList et renvoie une nouvelle ArrayList<Predateur>
     * @return La liste des predateurs de la lagune
     */
    public static ArrayList<Predator> getPredatorList() {
        ArrayList<Predator> predatorList = new ArrayList<Predator>();
        for (Fish i : fishesList) {
            if (i.getType() == "Predator") {
                predatorList.add((Predator)i);
            }
        }
        return predatorList;
    }

    /**
     * Donne la liste complete de toutes les passes de la lagune.
     * @return La liste des passes de la lagune.
     */
    public static ArrayList<Fishway> getFishwaysList() {
        return new ArrayList<Fishway>(fishwaysList);
    }

    /**
     * Modifie la taille de la lagune
     * @param n taille n de la lagune
     */
    public static void setLagoonTable(int n) {
        Lagoon.lagoonTable =  new int[n][n];
    }

    /**
     * Retourne la taille n de la lagune
     * @return la taille de la lagune
     */
    public static int getLength() {
        return Lagoon.lagoonTable.length;
    }

    /**
     * Cherche dans la liste des poissons de la lagune celui correspondant
     * au numero passe en parametre
     * @param num
     * @return Le poisson correspondant au numero passe en parametre, null sinon
     */
    public static Fish getFish(int num) {
        int i = 0;
        boolean found = false;
        Fish f = null;
        while (i < fishesList.size() && found == false) {
            if (Lagoon.getFishesList().get(i).getNum() == num) {
                f = Lagoon.getFishesList().get(i);
                found = true;
            }
            i++;
        }
        return f;
    }

    /**
     * @return the nextFishNum
     */
    public static int getNextFishNum() {
        return nextFishNum;
    }

    /**
     * @return the nextFishwayNum
     */
    public static int getNextFishwayNum() {
        return nextFishwayNum;
    }

    /**
     * @return the nextDiverNum
     */
    public static int getNextDiverNum() {
        return nextDiverNum;
    }

    /**
     * Ajoute une passe dans la lagune et renvoie son id.
     * Si la passe n'a pas pu etre ajoutee renvoie -1.
     * @param x
     * @param y
     * @return id de la nouvelle passe, -1 si l'ajout n'a pas ete fait
     */
    public static int addFishway(int x, int y) {
        int res = -1;

        //Si la position x ou y est en dehors des limites de la lagune, 
        //on empeche l'ajout de la passe a la lagune
        if (x > Lagoon.getLength()) {
            System.out.println("Passe en dehors des limites de la lagune ! Elle n'a pas ete ajoutee.");
        } 
        else if (y > Lagoon.getLength()) {
            System.out.println("Passe en dehors des limites de la lagune ! Elle n'a pas ete ajoutee.");
        }
        else {

            //Si la liste des passes est vide, alors on peut l'ajouter sans faire de verifications
            //sur les eventuelles passes adjacentes
            if (fishwaysList.isEmpty()) {
                Fishway newFishway = new Fishway(Lagoon.nextFishwayNum, x, y);
                res = nextFishwayNum;
                Lagoon.fishwaysList.add(newFishway);
                nextFishwayNum ++;
            }
            else {
                
                //Sinon, on parcourt la liste des passes de la lagune et on verifie
                //si il existe des passes adjacentes a celle qu'on veut creer
                for (Fishway f : Lagoon.fishwaysList) {
                    if (f.getPosition()[0] == x && f.getPosition()[1] == y) {
                        System.out.println("La nouvelle passe est placee sur une passe deja existante ! Elle n'a pas ete ajoutee.");
                    }
                    else if (f.getPosition()[0] == x && f.getPosition()[1] == y + 1) {
                        System.out.println("La nouvelle passe est placee sur une case adjacente d'une passe deja existante ! Elle n'a pas ete ajoutee.");
                    }
                    else if (f.getPosition()[0] == x && f.getPosition()[1] == y - 1) {
                        System.out.println("La nouvelle passe est placee sur une case adjacente d'une passe deja existante ! Elle n'a pas ete ajoutee.");
                    }
                    else if (f.getPosition()[0] == x + 1 && f.getPosition()[1] == y) {
                        System.out.println("La nouvelle passe est placee sur une case adjacente d'une passe deja existante ! Elle n'a pas ete ajoutee.");
                    }
                    else if (f.getPosition()[0] == x - 1 && f.getPosition()[1] == y) {
                        System.out.println("La nouvelle passe est placee sur une case adjacente d'une passe deja existante ! Elle n'a pas ete ajoutee.");
                    }
                    else { //positionnement correct
                        Fishway newFishway = new Fishway(nextFishwayNum, x, y);
                        res = nextFishwayNum;
                        Lagoon.fishwaysList.add(newFishway);
                        LagoonPainter.getCellByPosition(x, y).setBackground(Color.CYAN);
                        nextFishwayNum ++;
                    }
                }
            }
        }
        return res;
    }

    /**
     * Trouve la passe correspondant au numero passe en parametre dans la liste des passes existantes
     * @param num
     * @return La passe dont le numero a ete passe en parametre
     */
    public static Fishway getFishway(int num) {
        Fishway res = null;
        int i = 0;
        boolean found = false;
        while (i < Lagoon.fishwaysList.size() && found == false) {
            if (Lagoon.fishwaysList.get(i).getNum() == num) {
                res = Lagoon.fishwaysList.get(i);
                found = true;
            }
            i++;
        }
        return res;
    }

    /**
     * @return the listeActions
     */
    public static ArrayList<String> getListeActions() {
        return new ArrayList<String>(listeActions);
    }

    /**
     * @param listeActions
     */
    public static void setListeActions(ArrayList<String> listeActions) {
        Lagoon.listeActions = listeActions;
    }

    /**
     * Ajoute une action dans la liste d'actions
     * @param action
     */
    public static void addToListeActions(String action) {
        Lagoon.listeActions.add(action + "\n");
    }

    /**
     * Vide la liste des actions
     */
    public static void clearListeActions() {
        Lagoon.listeActions.clear();
    }
}
