package ecosystem;

public abstract class Fish implements Clock {
    /**
     * @attribute
     */
    private int num;

    /**
     * @attribute
     */
    private Double weight;

    /**
     * @attribute
     */
    private int[] position = new int[2];

    /**
     * @attribute
     */
    private String name;

    /**
     * @attribute
     */
    private int age;

    /**
     * @param name nom du poisson
     * @param num id du poisson
     * @param weight poids du poisson
     * @param x position x du poisson
     * @param y position y du poisson
     */
    public Fish(String name, int num, Double weight, int x, int y) {
        this.name = name;
        this.weight = Math.abs(weight);
        this.age = 0;
        this.num = num;

        if (x < 0) {
            this.position[0] = 0;
        }
        if (y < 0) {
            this.position[1] = 0;
        }
        //Si la position x est en dehors des limites de la lagune, 
        //on place le poisson a la position maximum x.
        if (x > Lagoon.getLength()) {
            this.position[0] = Lagoon.getLength();
        } 
        else {
            this.position[0] = x;
        }

        //Si la position y est en dehors des limites de la lagune, 
        //on place le poisson a la position maximum y.
        if (y > Lagoon.getLength()) {
            this.position[1] = Lagoon.getLength();
        }
        else {
            this.position[1] = y;
        }
    }

    /**
     * @return Le poids du poisson
     */
    public Double getWeight() {
        return weight;
    }

    /**
     * @return L'age du poisson
     */
    public int getAge() {
        return age;
    }

    /**
     * @return Le numero du poisson
     */
    public int getNum() {
        return num;
    }

    /**
     * @return Le nom du poisson
     */
    public String getName() {
        return name;
    }

    /**
     * @return La position du poisson
     */
    public int[] getPosition() {
        int[] pos = new int[2];
        pos[0] = position[0];
        pos[1] = position[1];
        return pos;
    }

    /**
     * Modifie la position du poisson.
     * @param x coordonnes x du poisson
     * @param y coordonnes y du poisson
     */
    public void setPosition(int x, int y) {
        if (x < 0) {
            this.position[0] = 0;
        }
        if (y < 0) {
            this.position[1] = 0;
        }
        //Si la position x est en dehors des limites de la lagune, 
        //on place le poisson a la position maximum x.
        if (x > Lagoon.getLength()) {
            this.position[0] = Lagoon.getLength();
        } 
        else {
            this.position[0] = x;
        }

        //Si la position y est en dehors des limites de la lagune, 
        //on place le poisson a la position maximum y.
        if (y > Lagoon.getLength()) {
            this.position[1] = Lagoon.getLength();
        }
        else {
            this.position[1] = y;
        }
    }

    /**
     * Modifie le poids du poisson.
     * @param weight le nouveau poids du poisson
     */
    public void setWeight(Double weight) {
        if (weight <= 0) {
            this.weight = -weight + 1;
        }   
        else {
            this.weight = weight;
        }
    }

    /**
     * Cree un clone du poisson
     * Elle a ce nom car la fonction clone() existe deja dans Java.Lang.Object
     */
    public abstract void duplicate();

    /**
     * Determine une nouvelle position aleatoire du poisson et l'applique.
     */
    public void move() {
        Random rnd = Random.getARandom();
        int[] newPos = rnd.move(position[0], position[1], Lagoon.getLength());
        this.setPosition(newPos[0], newPos[1]);

        Lagoon.addToListeActions(this.toString());
    }

    /**
     * Tue le poisson en l'enlevant de la fishesList de la lagune.
     */
    public void die() {
        Lagoon.deleteFish(num);
        Lagoon.addToListeActions(num + " est mort.");
    }

    /**
     * @return le type du poisson.
     */
    public abstract String getType();

    /**
     * Fait bouger le poisson et augmente son age.
     * @param isDay
     */
    @Override
    public void tickTock(Boolean isDay) {
        this.move();
        this.age++;
    }
}
