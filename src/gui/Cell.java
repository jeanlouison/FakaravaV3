package gui;

import java.awt.Container;

/**
 * Cell
 */
public class Cell extends Container {

    private int[] position = new int[2];

    Cell(int x, int y) {
        super();
        this.position[0] = x;
        this.position[1] = y;
    }

    /**
     * @return the position
     */
    public int[] getPosition() {
        return position;
    }
}