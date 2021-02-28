package engineering.purdue.tracsat.simulation;

import javax.swing.*;

public class CoreSimulator {

    private SimulationThread simulationThread;
    private SimulationView simView;
    private SimulationTimeInspector timeInspector;
    private SimulationCanvas simulationCanvas;


    public CoreSimulator() {
        simulationThread = new SimulationThread("TestSimulation", 25, this);
        simulationCanvas = new SimulationCanvas();

        simView = new SimulationView(this, simulationCanvas);
        timeInspector = new SimulationTimeInspector(this);

    }

    public SimulationThread getSimulationThread() {
        return simulationThread;
    }

    public SimulationView getSimView() {
        return simView;
    }

    public SimulationTimeInspector getTimeInspector() {
        return timeInspector;
    }

    public void render() {
        JFrame frame = new JFrame(); // Create the Main Application JFrame

        frame.setBounds(0, 0, 889, 703); // Set the Width and Height

        frame.setTitle("Table Simulator"); // Title the Main Application JFrame

        frame.setLocationRelativeTo(null); // center on the screen

        frame.setResizable(false);

        frame.setContentPane(simView.getContentPanel());

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }

    public void onSimulationUpdate() {

        getTimeInspector().update();
        getSimView().refreshSimulatorMonitorTable();

        simulationCanvas.update();


    }

}
