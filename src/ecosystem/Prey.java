package ecosystem;

import javax.swing.ImageIcon;

public class Prey extends Fish {
    /**
     * @attribute
     */
    private static int PREY_CLONE_TIME;

    /**
     * @attribute
     */
    private int vivacity;

    /**
     * @attribute
     */
    private static int MAX_DENSITY;

    private static ImageIcon icone;

    /**
     * Constructeur de la classe Prey
     * @param name nom de la proie
     * @param num id de la proie
     * @param weight poids de la proie
     * @param x position x de la proie
     * @param y position y de la proie
     * @param vivacity vivacite de la proie
     */
    public Prey(String name, int num, Double weight, int x, int y, int vivacity) {
        super(name, num, weight, x, y);
        this.vivacity = vivacity;
        this.icone = new ImageIcon("img\\prey.png");
	}

    /**
     * @return la vivacite
     */
	public int getVivacity() {
        return vivacity;
    }

    /**
     * @param vivacity
     */
    public void setVivacity(int vivacity) {
        this.vivacity = vivacity;
    }

    /**
     * Duplique la proie si elle a l'age adapte
     */
    @Override
    public void duplicate() {
        if ((this.getAge() % Prey.PREY_CLONE_TIME == 0) && (this.getAge() != 0)) {

            int newPrey = Lagoon.addPrey(this.getName(), this.getWeight(), this.getPosition()[0], 
                            this.getPosition()[1], this.getVivacity());

            Lagoon.addToListeActions(this.getNum() + " se clone et cree " + newPrey + ".");                            
        }
    }

    /**
     * @param cloneTime
     */
    public static void setPreyCloneTime(int cloneTime) {
        Prey.PREY_CLONE_TIME = cloneTime;
    }

    /**
     * @return the PREY_CLONE_TIME
     */
    public static int getPreyCloneTime() {
        return PREY_CLONE_TIME;
    }

    /**
     * @return le type de "Prey"
     */
    @Override
    public String getType(){
        return "Prey";
    }

    /**
     * @return the MAX_DENSITY
     */
    public static int getMaxDensity() {
        return MAX_DENSITY;
    }

    /**
     * @param MAX_DENSITY
     */
    public static void setMaxDensity(int maxDensity) {
        Prey.MAX_DENSITY = maxDensity;
    }

    /**
     * @return the icone
     */
    public static ImageIcon getIcone() {
        return icone;
    }

    /**
     * duplique la proie si necessaire et appelle ticktock de Fish.java
     * @param isDay
     */
    @Override
    public void tickTock(Boolean isDay) {       

        this.duplicate();
        super.tickTock(isDay);
    }

    /**
     * Donne un resume rapide de la proie
     */
    @Override
    public String toString() {
        return "Proie no" + this.getNum() +
            " : " + this.getName() +
                ", age : " + this.getAge() +
                    ", x: " + this.getPosition()[0] + " y: " + this.getPosition()[1] +
                        ", " + this.getWeight() + " kg" +
                            ", vivacite : " + this.getVivacity() + ".";
    }
}
