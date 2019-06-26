package ecosystem;

import javax.swing.ImageIcon;

public class Transmitter {
    /**
     * @attribute
     */
    private Integer num;
    
    /**
     * @attribute
     */
    Diver diver;
    
    /**
     * @attribute
     */
    Predator predator;

    /**
     * Constructeur de la classe Transmitter
     * @param num numero du transmetteur
     * @param p predator sur lequel est pose le transmetteur
     * @param diver plongeur qui pose le transmetteur
     */
    public Transmitter(Integer num, Predator p, Diver diver) {
        this.num = num;
        this.predator = p;
        this.diver = diver;
        p.setIcon(new ImageIcon("predwtransmitter.png"));
    }

    /**
     * @return the diver
     */
    public Diver getDiver() {
        return diver;
    }

    /**
     * @return le predator sur lequel est pose le transmetteur
     */
    public Predator getPredator() {
        return predator;
    }

    /**
     * @return l'id du transmetteur
     */
    public Integer getNum() {
        return num;
    }
}
