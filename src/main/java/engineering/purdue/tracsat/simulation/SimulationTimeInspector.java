package engineering.purdue.tracsat.simulation;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationTimeInspector {

    private JPanel contentPanel;
    private JLabel currentSimulationTime;
    private JPanel timeOps;
    private JButton timeToZeroButton;
    private JButton timeToButton;
    private JTextField textField1;
    private JButton frequencyToButton;
    private JSpinner frequencySpinna;
    private JButton stepBackwardButton;
    private JButton stepForwardButton;
    private JSlider timeScaler;
    private JLabel timeScaleIndicator;
    private JLabel currentFreq;

    private CoreSimulator coreSimulator;

    public SimulationTimeInspector(final CoreSimulator coreSimulator) {
        this.coreSimulator = coreSimulator;
        stepBackwardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                coreSimulator.getSimulationThread().stepBackward();
            }
        });
        stepForwardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                coreSimulator.getSimulationThread().stepForward();
            }
        });
        timeToZeroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                coreSimulator.getSimulationThread().zero();
            }
        });
        timeScaler.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                timeScaleIndicator.setText(timeScaler.getValue() + "%");
                coreSimulator.getSimulationThread().setTimeScale(timeScaler.getValue());
            }
        });
    }

    public void render() {
        final JFrame frame = new JFrame();

        frame.setTitle("Table Simulator");

        frame.setLocationRelativeTo(null); // center on the screen

        frame.setContentPane(coreSimulator.getTimeInspector().contentPanel);

        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.pack();

        frame.setVisible(true);
    }

    public void update() {
        currentSimulationTime.setText(String.format("%.3f", coreSimulator.getSimulationThread().getSimulationTime()));
        currentFreq.setText(coreSimulator.getSimulationThread().getSimulationDelay() + " ms");
    }


}
