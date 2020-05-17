package hr.fer.zemris.lsystems.impl.commands;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;

/**
 * Class represents command that can rotate direction in which turtle moves.
 * 
 * @author Ante Gazibarić
 * @version 1.0
 *
 */
public class RotateCommand implements Command {

	/**
	 * represents angle that adjusts turtle direction angle
	 */
	private double angle;
	
	/**
	 * Constructor for creating new <code>RotateCommand</code> object.
	 * 
	 * @param angle <code>double</code> represents angle that adjusts turtle direction angle
	 */
	public RotateCommand(double angle) {
		this.angle = angle;
	}
	
	@Override
	public void execute(Context ctx, Painter painter) {
		ctx.getCurrentState().getDirection().rotate(angle);
	}

}
