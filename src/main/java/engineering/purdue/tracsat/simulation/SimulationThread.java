package engineering.purdue.tracsat.simulation;

import java.math.BigDecimal;

public class SimulationThread implements Runnable {

	enum SimulationStatus {
		SIM_PAUSED, SIM_RUNNING, SIM_ENDED, SIM_WAITING_TO_START
	}
	
	private BigDecimal simulation_time = new BigDecimal("0.000");

	private int timeScale = 100;
	
	private long simulation_delay; // How fast the simulation should update in Hz (i.e. 1 Hz = 1 simulation update per second)

	private SimulationStatus status = SimulationStatus.SIM_WAITING_TO_START;

	private boolean simulation_active = true;
	private boolean simulation_running = false;
	
	private String simulation_name;

	private CoreSimulator coreSimulator;

	private Thread simThread;

	/**
	 * Create a new simulation timing thread.
	 * @param simulationName
	 * @param simulation_delay
	 */
	public SimulationThread(String simulationName, long simulation_delay, CoreSimulator coreSimulator) {
		this.simulation_name = simulationName;
		this.coreSimulator = coreSimulator;

		if (simulation_delay < 1) {
			throw new IllegalArgumentException("Simulation Delay must be greater than or equal to 1");
		}
		
		this.simulation_delay = simulation_delay;

		simThread = new Thread(this);
		simThread.start();
	}
	
	@Override
	public void run() {
		
		System.out.println("[SimulationThread] Starting a new simulation");
		
		while (simulation_active) {
			
			if (simulation_running) {
				stepForward();
			}
			
			try {
				if (timeScale < 100) {
					// slow down
					Thread.sleep((long)(simulation_delay * ((100 + (100 - timeScale))/ 100.0)));
				} else if (timeScale == 100) {
					Thread.sleep(simulation_delay);
				} else {
					// speed up
					Thread.sleep((long) (simulation_delay * ((100 - (timeScale - 100)) / 100.0)));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		System.out.println("[SimulationThread] Simulation ended");



	}
	
	public void startSimulation() {
		
		simulation_active = true;
		simulation_running = true;
		status = SimulationStatus.SIM_RUNNING;
		
	}
	
	public void stopSimulation() {
		
		simulation_active = false;
		status = SimulationStatus.SIM_ENDED;
		
	}
	
	public void pauseSimulation() {
		
		simulation_running = false;
		status = SimulationStatus.SIM_PAUSED;
		
	}
	
	public void resumeSimulation() {
		
		simulation_running = true;
		status = SimulationStatus.SIM_RUNNING;

	}

	public void stepForward() {
		simulation_time = simulation_time.add(new BigDecimal((Double.toString(simulation_delay / 1000.0D))));
		coreSimulator.onSimulationUpdate();
	}

	public void stepBackward() {
		simulation_time = simulation_time.subtract(new BigDecimal((Double.toString(simulation_delay / 1000.0D))));
		coreSimulator.onSimulationUpdate();
	}

	public void zero() {
		simulation_time = new BigDecimal("0.00");
		coreSimulator.onSimulationUpdate();
	}

	public void setTimeScale(int timeScale) {
		this.timeScale = timeScale;
	}

	public long getSimulationDelay() {
		return simulation_delay;
	}

	public void setSimulationTime(double simulation_time_ms) throws IllegalArgumentException {
		
		if (simulation_time_ms < 0) {
			throw new IllegalArgumentException("Tried to set simulation time less than 0.");
		}
		
//		this.simulation_time = simulation_time_ms;
	}

	public double getSimulationTime() {
		return simulation_time.doubleValue();
	}

	public SimulationStatus getStatus() {
		return status;
	}
}
