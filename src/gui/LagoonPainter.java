package gui;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import ecosystem.*;

/**
 * LagoonPainter
 */
public class LagoonPainter {

    private static ArrayList<Cell> cellList = new ArrayList<Cell>();
    private static JFrame frame = new JFrame("Fakarava");

    public static void createGrid() {

        int lagoonSize = Lagoon.getLength();

        fillGrid(lagoonSize);

        frame.setLayout(new GridLayout(lagoonSize,lagoonSize));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500,500);  
        frame.setVisible(true); 
        frame.validate();
    }

    public static Cell getCellByPosition(int x, int y) {
        int i = 0;
        boolean trouve = false;
        Cell res = null;
        while (i < LagoonPainter.cellList.size() && trouve == false){
            if ((LagoonPainter.cellList.get(i).getPosition()[0] == x) && 
            (LagoonPainter.cellList.get(i).getPosition()[1] == y)) {
                res = LagoonPainter.cellList.get(i);
                trouve = true;
            }
            i++;
        }
        return res;
    }

    public static void resetGrid() {
        for (int i = 0; i < Lagoon.getLength() * Lagoon.getLength(); i++){

            Component[] componentList = cellList.get(i).getComponents();

            for(Component c : componentList){
                if(c instanceof JLabel){
                    cellList.get(i).remove(c);
                }
            }
            cellList.get(i).revalidate();
            cellList.get(i).repaint();
        }
    }

    public static void fillGrid(int lagoonSize) {
        for (int i = 0; i < lagoonSize; i++) {
            for (int j = 0; j < lagoonSize; j++) {
                Cell cell = new Cell(i, j);
                LagoonPainter.cellList.add(cell);
                frame.add(cell);
            }
        }
        frame.validate();
    }

    public static void updateFrame() {
        frame.invalidate();
        frame.validate();
        frame.repaint();
    }

    public static void exit() {
        frame.setVisible(false);
        frame.dispose();
    }
}