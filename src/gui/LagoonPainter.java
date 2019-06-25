package gui;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import ecosystem.Lagoon;

/**
 * LagoonPainter
 */
public class LagoonPainter {

    public static void init() {

        JFrame frame = new JFrame("Fakarava");
        int lagoonSize = Lagoon.getLength();

        for (int i = 0; i < lagoonSize; i++) {
            for (int j = 0; j < lagoonSize; j++) {
                JButton btn = new JButton("x");
                frame.add(btn);
            }
        }

        frame.setLayout(new GridLayout(lagoonSize,lagoonSize));
        frame.setSize(300,300);  
        frame.setVisible(true); 
    }
}