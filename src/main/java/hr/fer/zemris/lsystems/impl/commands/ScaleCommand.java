package hr.fer.zemris.lsystems.impl.commands;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;

/**
 * Class represents command that can scale unit length of turtle.
 * 
 * @author Ante Gazibarić
 * @version 1.0
 *
 */
public class ScaleCommand implements Command {

	/**
	 * factor used for scaling
	 */
	private double factor;
	
	/**
	 * Constructor that creates new <code>ScaleCommand</code> object
	 * 
	 * @param factor <code>double</code> that command uses for scaling
	 * @throws <code>IllegalArgumentException</code> if given factor is not positive
	 */
	public ScaleCommand(double factor) {
		if (factor <= 0)
			throw new IllegalArgumentException("Factor has to be positive. You entered: " + factor);
		
		this.factor = factor;
	}
	
	@Override
	public void execute(Context ctx, Painter painter) {
		double newEffectiveLength = ctx.getCurrentState().getEffectiveLength() * factor;
		ctx.getCurrentState().setEffectiveLength(newEffectiveLength);
	}

}
