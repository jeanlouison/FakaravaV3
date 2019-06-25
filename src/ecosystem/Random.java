package ecosystem;

import java.awt.Point;
import static java.lang.Math.*;

public class Random extends java.util.Random {
    private static final long serialVersionUID = 1L;

    private static Random myRandom;

    /**
     * Graine du générateur aléatoire.
     * Lui donner une valeur permet de faire des tests reproductibles.
     */
    public static Long mySeed; 


    private Random() {
        super();
    }

    private Random(long l) {
        super(l);
    }

    /**
     * Retourne un générateur pseudo-aléatoire.
     * @return L'unique objet Random de la simulation.
     */
    public static Random getARandom() {
        if (myRandom == null)
            if (mySeed == null)
                myRandom = new Random();
            else
                myRandom = new Random(mySeed);
        return myRandom;
    }

    /**
     * Sélectionne un individu parmi n.
     * @param n nombre d'individus
     * @return L'indice de l'individu sélectionné (entre 0 et n-1).
     */
    public int who(int n) {
        return abs(myRandom.nextInt()) % n;
    }

    /**
     * Modélise le déplacement d'un poisson dans le lagon.
     * @param p position du poisson
     * @param gridSize taille du lagon (côté)
     * @return la nouvelle position du poisson
     */
    public int[] move(int x, int y, int gridSize) {
        switch (myRandom.nextInt(5)) {
        case 0:
            // East
            return new int[] {min(x + 1, gridSize - 1), y};
        case 1:
            // North
            return new int[] {x, min(y + 1, gridSize - 1)};
        case 2:
            // West
            return new int[] {max(0, x - 1), y};
        case 3:
            // South
            return new int[] {x, max(0, y - 1)};
       default:
            // Stay
            return new int[] {x, y};
        }
    }

    /**
     * Tire au sort l'apparition d'un événement de probabilité p.
     * @param p: probabilité ( 0 <= p <= 1 )
     * @return true si l'événement doit se produire, false sinon.
     */
    public boolean selection(double p) {
        return myRandom.nextDouble() < p;
    }
}
