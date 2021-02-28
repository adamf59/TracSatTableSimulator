package engineering.purdue.tracsat.simulation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class SimulationView {

    private CoreSimulator coreSimulator;

    private JPanel simulationCanvas;

    private JPanel contentPanel;
    private JPanel simulationOps;
    private JButton simulationTimeButton;
    private JButton timeInspectorButton;
    private JPanel simulationCanvasViewPort;
    private JTable stateTable;
    private JButton objectInspectorButton;
    private JButton pathModifierButton;
    private JButton graphButton;
    private JButton resetButton;

    public SimulationView(final CoreSimulator coreSimulator, final JPanel simulationCanvas) {

        this.coreSimulator = coreSimulator;
        this.simulationCanvas = simulationCanvas;

        simulationTimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                switch (coreSimulator.getSimulationThread().getStatus()) {
                    case SIM_WAITING_TO_START:
                        coreSimulator.getSimulationThread().startSimulation();
                        simulationTimeButton.setText("Pause Simulation");
                        break;
                    case SIM_PAUSED:
                        coreSimulator.getSimulationThread().resumeSimulation();
                        simulationTimeButton.setText("Pause Simulation");
                        break;
                    case SIM_ENDED:
                        System.err.println("Attempted to start a simulation that was ended.");
                        simulationTimeButton.setText("---");
                        break;
                    case SIM_RUNNING:
                        coreSimulator.getSimulationThread().pauseSimulation();
                        simulationTimeButton.setText("Resume Simulation");
                        break;
                }

            }
        });
        timeInspectorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                coreSimulator.getTimeInspector().render();
            }
        });
        objectInspectorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimulationObjectInspector.main(null);
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showConfirmDialog(null, "Really reset? You'll lose all data for this simulation.", "Warning", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

            }
        });


//        simulationCanvasViewPort.addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentShown(ComponentEvent e) {
//                System.out.println("testing!");
//            }
//        });

        simulationCanvasViewPort.addHierarchyListener(new HierarchyListener() {

            boolean canvasLoaded = false;

            @Override
            public void hierarchyChanged(HierarchyEvent e) {

                if (!canvasLoaded) {
                    simulationCanvasViewPort.add(simulationCanvas);
                    simulationCanvasViewPort.revalidate();
                    System.out.println("[SimulationView] SimulationCanvas rendered.");

                    canvasLoaded = true;
                }


            }
        });
    }

    private void createUIComponents() {
        simulationCanvasViewPort = new JPanel(null);
        stateTable = new JTable(new DefaultTableModel(new Object[]{"Key", "Value"}, 0));
        DefaultTableModel model = (DefaultTableModel) stateTable.getModel();
        model.addRow(new Object[]{"Simulation Time", "<waiting to start>"});

    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public void refreshSimulatorMonitorTable() {
        DefaultTableModel model = (DefaultTableModel) stateTable.getModel();
        model.setValueAt(String.format("%.3f", coreSimulator.getSimulationThread().getSimulationTime()), 0,1);

    }
}
