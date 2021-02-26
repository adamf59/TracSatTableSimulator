package engineering.purdue.tracsat.simulation;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;


public class SimulationCanvas extends JPanel{
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        System.out.println("Renderer: " + getWidth() + " x " + getHeight());

        setBackground(Color.black);

        int num_divisions_x = 20;
        int num_divisions_y = 20;

        int width_division = Math.round((float) getWidth() / num_divisions_x);
        int height_division = Math.round((float) getHeight() / num_divisions_y);

        // Vertical Lines for Grid
        for (int i = 0; i < num_divisions_x; i++) {
            g2.drawLine(width_division * (i + 1), 0, width_division * (i + 1), getHeight());
        }

        // Horizontal Lines for Grid
        for (int i = 0; i < num_divisions_y; i++) {
            g2.drawLine(0, height_division * (i + 1), getWidth(), height_division * (i + 1));
        }

        g2.setPaint(Color.RED);

    }
}

