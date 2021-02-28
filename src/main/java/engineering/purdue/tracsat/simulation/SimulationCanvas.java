package engineering.purdue.tracsat.simulation;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class SimulationCanvas extends JPanel {

    /**
     * Defines how many horizontal and vertical lines should appear in the simulation canvas
     */
    public static final int num_divisions_x = 20;
    public static final int num_divisions_y = 20;

    private int test_x = 0;


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        setBackground(Color.black);

        g2.setPaint(Color.RED);
        g2.fillOval(300, 300, 60, test_x++);
        g2.setPaint(Color.WHITE);

        int width_division = Math.round((float) getWidth() / num_divisions_x);
        int height_division = Math.round((float) getHeight() / num_divisions_y);

        g2.setFont(new Font("TimesRoman", Font.PLAIN, 10));
        // Vertical Lines for Grid
        for (int i = 0; i < num_divisions_x; i++) {
            g2.drawLine(width_division * (i + 1), 0, width_division * (i + 1), getHeight());
            if (i < num_divisions_x - 1) {
                g2.drawString("" + width_division * (i+1), width_division * (i + 1), getHeight() - 5);
            }
        }

        // Horizontal Lines for Grid
        for (int i = 0; i < num_divisions_y; i++) {
            g2.drawLine(0, height_division * (i + 1), getWidth(), height_division * (i + 1));
            if (i < num_divisions_y - 1) {
                g2.drawString("" + height_division * (i + 1), 0, height_division * (i + 1));
            }
        }

    }

    public void update() {
        repaint();
    }
}

