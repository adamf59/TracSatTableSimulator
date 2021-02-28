package engineering.purdue.tracsat;

import com.github.weisj.darklaf.LafManager;
import com.github.weisj.darklaf.theme.DarculaTheme;
import engineering.purdue.tracsat.simulation.CoreSimulator;
import engineering.purdue.tracsat.simulation.SimulationView;

import javax.swing.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        System.out.println("Starting Table Simulator");
        System.out.println("Version 1.0 Development");

        LafManager.install(new DarculaTheme()); // Setup Dark Theme

        CoreSimulator simulator = new CoreSimulator();
        simulator.render();

    }
}
