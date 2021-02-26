package engineering.purdue.tracsat.simulation;

import javax.swing.*;

public class SimulationObjectInspector {
    private JPanel contentPanel;
    private JTable objectDetails;

    String[] columnNames = { "Key", "Value" };
    String[][] current_state = {
            { "POS_X", "50" },
            { "POS_Y", "103" },
            { "ROT", "13.4" },
            { "Thruster_0", "40%" },
            { "Thruster_1", "20%" },
            { "Thruster_2", "63%" }
    };

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setTitle("Table Simulator");

        frame.setLocationRelativeTo(null); // center on the screen

        SimulationObjectInspector objectInspector = new SimulationObjectInspector();

        frame.setContentPane(objectInspector.contentPanel);

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();

        frame.setVisible(true);
    }

    private void createUIComponents() {
        objectDetails = new JTable(current_state, columnNames);

    }
}
