package demo;

import hr.fer.zemris.lsystems.gui.LSystemViewer;
import hr.fer.zemris.lsystems.impl.LSystemBuilderImpl;

/**
 * Program that creates graphical fractal pattern. </br>
 * It allows user to input configuration text file of fractal pattern. </br>
 * User can set degree of recursion (from 0 to 6).
 * 
 * @author Ante Gazibarić
 * @version 1.0
 *
 */
public class Glavni3 {

	/**
	 * Main method. Accepts no arguments.
	 * 
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		
		LSystemViewer.showLSystem(LSystemBuilderImpl::new);
		
	}
	
	
}
