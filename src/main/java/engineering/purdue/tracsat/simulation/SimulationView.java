package engineering.purdue.tracsat.simulation;

import com.github.weisj.darklaf.LafManager;
import com.github.weisj.darklaf.theme.DarculaTheme;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationView {
    private JPanel contentPanel;
    private JPanel simulationOps;
    private JButton startSimulationButton;
    private JButton timeInspectorButton;
    private JPanel simulationCanvas;
    private JTable stateTable;
    private JButton objectInspectorButton;
    private JButton pathModifierButton;
    private JButton graphButton;
    private JButton exportButton;


    String[] columnNames = { "Key", "Value" };
    String[][] current_state = {
            { "TS_SIM_INTERVAL", "50" },
            { "TS_SIM_TIME", "103" },
            { "LIDAR Distance", "13.4" },
            { "Controller State", "ACQUIRE_OBJECT" }
    };

    public SimulationView() {
        startSimulationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel diagram = new SimulationCanvas();
                diagram.setVisible(true);
                simulationCanvas.add(diagram);
                simulationCanvas.revalidate();

                System.out.println("Added Simulation Canvas");
            }
        });
        timeInspectorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimulationTimeInspector.main(null);
            }
        });
        objectInspectorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimulationObjectInspector.main(null);
            }
        });
    }

    public static void main(String[] args) {
        LafManager.install(new DarculaTheme());

        JFrame frame = new JFrame();
        frame.setBounds(0, 0, 875, 485); // setup the width and height

        frame.setTitle("Table Simulator");

        frame.setLocationRelativeTo(null); // center on the screen

        SimulationView simView = new SimulationView();

        frame.setContentPane(simView.contentPanel);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }

    private void createUIComponents() {
        simulationCanvas = new JPanel();
        stateTable = new JTable(current_state, columnNames);

    }
}
