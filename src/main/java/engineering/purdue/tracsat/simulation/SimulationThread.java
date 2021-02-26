package engineering.purdue.tracsat.simulation;

public class SimulationThread implements Runnable {
	
	private double simulation_time = 0.0D; // A count of how many MILLISECONDS have passed since the simulation started.
	
	private long simulation_delay = 1; // How fast the simulation should update in Hz (i.e. 1 Hz = 1 simulation update per second)
	
	private boolean simulation_active = false;
	private boolean simulation_running = false;
	
	private String simulation_name;

	/**
	 * Create a new simulation timing thread.
	 * @param simulationName
	 * @param simulation_frequency
	 */
	public SimulationThread(String simulationName, long simulation_delay) {
		this.simulation_name = simulationName;
		
		if (simulation_delay < 1) {
			throw new IllegalArgumentException("Simulation Delay must be greater than or equal to 1");
		}
		
		this.simulation_delay = simulation_delay;
		
	}
	
	@Override
	public void run() {
		
		System.out.println("[SimulationThread] Starting a new simulation");
		
		while (simulation_active) {
			
			if (simulation_running) {
				
				simulation_time += simulation_delay;
				
			}
			
			try {
				Thread.sleep(simulation_delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		
	}
	
	public void startSimulation() {
		
		simulation_active = true;
		
	}
	
	public void stopSimulation() {
		
		simulation_active = false;
		
	}
	
	public void pauseSimulation() {
		
		simulation_running = false;
		
	}
	
	public void resumeSimulation() {
		
		simulation_running = true;
		
	}
	
	public void setSimulationTime(double simulation_time_ms) throws IllegalArgumentException {
		
		if (simulation_time_ms < 0) {
			throw new IllegalArgumentException("Tried to set simulation time less than 0.");
		}
		
		this.simulation_time = simulation_time_ms;
	}
	
	

}
