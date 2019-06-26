package gui;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Cell
 */
public class Cell extends JPanel {

    private static final long serialVersionUID = -5193774407319038614L;
    private int[] position = new int[2];

    Cell(int x, int y) {
        super();
        this.position[0] = x;
        this.position[1] = y;
        this.setBackground(Color.WHITE);
    }

    /**
     * @return the position
     */
    public int[] getPosition() {
        return position;
    }

    public void addToCell(ImageIcon icone) {
        this.add(new JLabel(icone));
    }   
}