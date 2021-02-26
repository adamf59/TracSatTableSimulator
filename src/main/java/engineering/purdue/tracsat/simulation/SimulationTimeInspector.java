package engineering.purdue.tracsat.simulation;

import javax.swing.*;

public class SimulationTimeInspector {
    private JPanel contentPanel;
    private JLabel currentSimulationTime;
    private JPanel timeOps;
    private JButton timeToZeroButton;
    private JButton timeToButton;
    private JTextField textField1;
    private JButton frequencyToButton;
    private JSpinner frequencySpinna;

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setTitle("Table Simulator");

        frame.setLocationRelativeTo(null); // center on the screen

        SimulationTimeInspector timeInspector = new SimulationTimeInspector();

        frame.setContentPane(timeInspector.contentPanel);

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();

        frame.setVisible(true);
    }
}
