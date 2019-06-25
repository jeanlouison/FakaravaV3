package ecosystem;

import java.util.ArrayList;

public class Fishway implements Clock {
    /**
     * @attribute
     */
    private static int MAX_CURRENT_STRENGTH;

    /**
     * @attribute
     */
    private int num;

    /**
     * @attribute
     */
    private int[] position = new int[2];

    /**
     * @attribute
     * La force du courant de la passe
     */
    private Double strength;

    /**
     * @attribute
     */
    private ArrayList<Prey> preyList = new ArrayList<Prey>();

    /**
     * @attribute
     */
    private ArrayList<Predator> predatorList = new ArrayList<Predator>();

    private Camera camera = null;
    
    public Fishway(int num, int x, int y) {
        this.num = num;
        this.position[0] = x;
        this.position[1] = y;
        this.strength = MAX_CURRENT_STRENGTH / (double)(Lagoon.getFishwaysList().size());
    }

    /**
     * Ajoute la proie a la passe
     * @param num numero du poisson a ajouter
     */
    public void addPrey(int num) {
        this.preyList.add((Prey)Lagoon.getFish(num));
    }    

    /**
     * Ajoute le predateur a la passe
     * @param num numero du poisson a ajouter
     */
    public void addPredator(int num) {
        this.predatorList.add((Predator)Lagoon.getFish(num));
    }

    /**
     * Supprime le poisson de la passe
     * @param num numero du poisson a ajouter
     */
    public void deletePrey(int num) {
        if (! this.preyList.isEmpty()) {
            int i = 0;
            boolean found = false;
            while (i < this.preyList.size() && found == false) {
                if (this.preyList.get(i).getNum() == num) {
                    this.preyList.remove(i);
                    found = true;
                }
                i++;
            }
        } 
        else {
            throw new NullPointerException("La liste des proies est vide, impossible de supprimer une proie");
        }
        
    }

    /**
     * Supprime le poisson de la passe
     * @param num numero du poisson a ajouter
     */
    public void deletePredator(int num) {
        if (! this.predatorList.isEmpty()) {
            int i = 0;
            boolean found = false;
            while (i < this.predatorList.size() && found == false) {
                if (this.predatorList.get(i).getNum() == num) {
                    this.predatorList.remove(i);
                    found = true;
                }
                i++;
            }
        }
        else {
            throw new NullPointerException("La liste des proies est vide, impossible de supprimer un predateur");
        }
    }

    /**
     * Chasse la proie avec le moins de vivacite, avec une reussite aleatoire
     * Si l'attaque reussit, on supprime la proie de la passe
     * Si l'attaque echoue, on attaque on autre predateur
     * @param predator
     * @param prey
     * @return l'issue de l'attaque
     */
    public boolean hunt(Predator predator, Prey attackedPrey, Boolean isDay) {
        boolean haveCam = false;

        if(camera != null)
        {
            haveCam = true;
        }
        int attackedPreyVivacity = attackedPrey.getVivacity();
        if (!isDay) {
            attackedPreyVivacity = attackedPreyVivacity / 2;
        }
        Random rnd = Random.getARandom();
        Double chancesOfSurvival = (attackedPrey.getVivacity()/predator.getWeight() - this.getStrength()/100);
        Boolean isAlive = rnd.selection(chancesOfSurvival);

        if (isAlive) { //Si l'attaque rate et que la proie s'enfuit
            int indexOfAttackedPredator = rnd.who(getPredatorList().size());
            Predator attackedPredator = this.predatorList.get(indexOfAttackedPredator);
            attackedPredator.setWeight(attackedPredator.getWeight() - (predator.getWeight() / Predator.getBiteFactor()));

            if(haveCam)
            {
                camera.getDiver().addToLog(predator.getNum() + " attaque " + attackedPrey.getNum() + " mais rate et attaque " + attackedPredator.getNum());
            }

            Lagoon.addToListeActions(predator.getNum() + " attaque " + attackedPrey.getNum() + " mais rate et attaque " + attackedPredator.getNum() + ".");

            if (attackedPredator.getWeight() <= 0) {
                if (haveCam) {
                camera.getDiver().addToLog(attackedPredator.getNum() + " est mort");
                }
                attackedPredator.die();
            }
        }
        else { //Si l'attaque reussit
            predator.setWeight(predator.getWeight() + attackedPrey.getWeight());
            if (haveCam) {
                camera.getDiver().addToLog(predator.getNum() + " attaque " + attackedPrey.getNum());
                camera.getDiver().addToLog(attackedPrey.getNum() + " est mort");
            }

            Lagoon.addToListeActions(predator.getNum() + " attaque " + attackedPrey.getNum());

            attackedPrey.die();
        }
        return isAlive;
    }

    /**
     * Trie de maniere croissante la liste des proies de la lagune par leur vivacite
     */
    public void sortPreyListByVivacity() {

        if (!this.getPreyList().isEmpty()) {
            int j = 0;
            Prey temp;
            Boolean trie = false;
    
            while (j < getPreyList().size() - 1 && !trie) {
                trie = true;
                for (int i = 1; i < getPreyList().size() - j; i++) {
                    if (getPreyList().get(i - 1).getVivacity() >= getPreyList().get(i).getVivacity()) {
                        temp = getPreyList().get(i - 1);
                        preyList.set(i - 1, getPreyList().get(i));
                        preyList.set(i, temp);
                        trie = false;
                    }
                }
            j++;
            }
        }
        else {
            throw new NullPointerException("La liste des proies est vide et ne peut pas etre triee");
        }
    }

    /**
     * Trie de maniere croissante la liste des predateurs de la lagune par leur poids
     */
    public void sortPredatorListByWeight() {

        if (!this.getPredatorList().isEmpty()) {
            int j = 0;
            Predator temp;
            Boolean trie = false;

            while (j < getPredatorList().size() - 1 && !trie) {
                trie = true;
                
                for (int i = 1; i < getPredatorList().size() - j; i++) {
                    if (getPredatorList().get(i - 1).getWeight() >= getPredatorList().get(i).getWeight()) {
                        temp = getPredatorList().get(i - 1);
                        predatorList.set(i - 1, getPredatorList().get(i));
                        predatorList.set(i, temp);
                        trie = false;
                    }
                }
            j++;
            
            }
        }
        else {
            throw new NullPointerException("La liste des predateurs est vide et ne peut pas etre triee");
        }
    }

    /**
     * @return predatorList
     */
    public ArrayList<Predator> getPredatorList() {
        return predatorList;
    }

    /**
     * @return preyList
     */
    public ArrayList<Prey> getPreyList() {
        return preyList;
    }

    /**
     * Permet de recuperer la force du courant des passes
     * @return MAX_CURRENT_STRENGTH
     */
    public static int getMaxCurrentStrength() {
        return Fishway.MAX_CURRENT_STRENGTH;
    }
    
    /**
     * @param max MAX_CURRENT_STRENGTH
     */
    public static void setMaxCurrentStrength(int max) {
        Fishway.MAX_CURRENT_STRENGTH = max;
    }

    /**
     * @param camera the camera to set
     */
    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    /** 
     * Permet de recuperer l'id de la passe
     * @return num
     */
    public int getNum() {
        return num;
    }

    /**
     * @return
     */
    public Double getStrength() {
        return strength;
    }

    /**
     * @param strength
     */
    public void setStrength(Double strength) {
        this.strength = strength;
    }

    /**
     * @return the position
     */
    public int[] getPosition() {
        return position;
    }

    /**
     * Met a jour les passes et s'occupe de la chasse dans celles-ci
     * @param isDay     
     */
    public void tickTock(Boolean isDay) {  
        int taille = getPredatorList().size();
        int i = 0;
        while(i < taille && !(getPreyList().isEmpty()))
        {
            sortPredatorListByWeight();
            sortPreyListByVivacity();

            hunt(getPredatorList().get(i), getPreyList().get(0), isDay);
            if (taille != getPredatorList().size()) {
                taille = getPredatorList().size();
            }
            i++;
        }
    }
}