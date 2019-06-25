package ecosystem;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import gui.LagoonPainter;

/**
 * Classe des prédateurs. Ce sont les poissons qui chassent les proies.
 */
public class Predator extends Fish {
    /**
     * @attribute
     */
    private static int BITE_FACTOR;

    /**
     * @attribute
     */
    private static int PREDATOR_CLONE_TIME;

    /**
     * @attribute
     */
    private static ImageIcon icone= new ImageIcon("\\img\\predator.png");
    
    /**
     * Constructeur de la classe Predator
     * @param name nom du predateur
     * @param num id du predateur
     * @param weight poids du predateur
     * @param x position x du predateur
     * @param y position y du predateur
     */
    public Predator(String name, int num, Double weight, int x, int y) {
        super(name, num, weight, x, y);
    }

    /**
     * @return le type "Predateur"
     */
    @Override
    public String getType(){
        return "Predator";
    }

    /**
     * @param cloneTime
     */
    public static void setPredatorCloneTime(int cloneTime) {
        Predator.PREDATOR_CLONE_TIME = cloneTime;
    }

    /**
     * @param biteFactor
     */
    public static void setBiteFactor(int biteFactor) {
        Predator.BITE_FACTOR = biteFactor;
    }

    /**
     * @return the bite factor
     */
    public static int getBiteFactor() {
        return BITE_FACTOR;
    }

    /**
     * @return the predator clone time
     */
    public static int getPredatorCloneTime() {
        return PREDATOR_CLONE_TIME;
    }

    /**
     * Duplique le predateur si il a l'age adapte
     */
    @Override
    public void duplicate() {
        if ((this.getAge() % Predator.PREDATOR_CLONE_TIME == 0) && (this.getAge() != 0)) {
            int newPredator = Lagoon.addPredator(this.getName(), this.getWeight(), this.getPosition()[0], 
                            this.getPosition()[1]);
        
            Lagoon.addToListeActions(this.getNum() + " se clone et cree " + newPredator + ".");
        }
    }

    /**
     * @return the icone
     */
    public static ImageIcon getIcone() {
        return icone;
    }

    /**
     * Appelle le ticktock de Fish.java, Reduit le poids du predateur, le duplique si c'est le moment et verifie si il est mort de faim
     * @param isDay
     */
    @Override
    public void tickTock(Boolean isDay) {
        super.tickTock(isDay);
        this.setWeight(this.getWeight() - 1);
        this.duplicate();
        // LagoonPainter.getCellByPosition(this.getPosition()[0], this.getPosition()[1]).add(new JLabel("predator"));
        LagoonPainter.getCellByPosition(this.getPosition()[0], this.getPosition()[1]).addToCell(icone);

        if (this.getWeight() == 0) {
            this.die();
        }
    }
    
    /**
     * Donne un resume rapide du predateur
     */
    @Override
    public String toString() {
        return "Predateur no " + this.getNum() +
            " : " + this.getName() +
                ", age : " + this.getAge() +
                    ", x: " + this.getPosition()[0] + " y:" + this.getPosition()[1] +
                        ", " + this.getWeight() + " kg.";
    }
}
