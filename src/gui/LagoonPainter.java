package gui;

import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

import ecosystem.*;

/**
 * LagoonPainter
 */
public class LagoonPainter {

    private static ArrayList<Cell> cellList = new ArrayList<Cell>();

    public static void createGrid() {

        JFrame frame = new JFrame("Fakarava");
        int lagoonSize = Lagoon.getLength();

        for (int i = 0; i < lagoonSize; i++) {
            for (int j = 0; j < lagoonSize; j++) {
                Cell cell = new Cell(i, j);
                LagoonPainter.cellList.add(cell);
                frame.add(cell);
            }
        }

        frame.setLayout(new GridLayout(lagoonSize,lagoonSize));
        frame.setSize(300,300);  
        frame.setVisible(true); 
    }

    public static void updateGrid() {

        for (int i = 0; i < Lagoon.getPredatorList().size(); i++) {
            if (Lagoon.getPredatorList().get(i).getPosition() == LagoonPainter.cellList.get(i).getPosition()) {
                LagoonPainter.cellList.get(i).add(new JLabel(Predator.getIcone()));
            }
        }
        for (int i = 0; i < Lagoon.getPreyList().size(); i++) {
            if (Lagoon.getPreyList().get(i).getPosition() == LagoonPainter.cellList.get(i).getPosition()) {
                LagoonPainter.cellList.get(i).add(new JLabel(Prey.getIcone()));
            }
        }
    }
}