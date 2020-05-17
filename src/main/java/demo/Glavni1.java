package demo;

import hr.fer.zemris.lsystems.LSystem;
import hr.fer.zemris.lsystems.LSystemBuilderProvider;
import hr.fer.zemris.lsystems.gui.LSystemViewer;
import hr.fer.zemris.lsystems.impl.LSystemBuilderImpl;

/**
 * Program that creates graphical fractal pattern which is called Koch Curve. </br>
 * User can set degree of recursion (from 0 to 6).
 * 
 * @author Ante Gazibarić
 * @version 1.0
 *
 */
public class Glavni1 {

	/**
	 * Main method. Accepts no arguments.
	 * 
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		
		LSystemViewer.showLSystem(createKochCurve(LSystemBuilderImpl::new));
		
	}
	
	/**
	 * Method that configures and returns LSystem.
	 * 
	 * @param provider <code>LSystemBuilderProvider</code> that is used for creating <code>LSystemBuilder</code>
	 * @return  	   <code>LSystem</code> created by <code>LSystemBuilder</code> that is got from <code>provider</code>
	 */
	private static LSystem createKochCurve(LSystemBuilderProvider provider) {
		return provider.createLSystemBuilder()
				.registerCommand('F', "draw 1")
				.registerCommand('+', "rotate 60")
				.registerCommand('-', "rotate -60")
				.setOrigin(0.05, 0.4)
				.setAngle(0)
				.setUnitLength(0.9)
				.setUnitLengthDegreeScaler(1.0/3.0)
				.registerProduction('F', "F+F--F+F")
				.setAxiom("F")
				.build();
		}
	
}
