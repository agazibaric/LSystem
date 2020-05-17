package hr.fer.zemris.lsystems.impl.commands;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;
import hr.fer.zemris.math.Vector2D;

/**
 * Class represents command that is used for moving turtle without drawing line
 * 
 * @author Ante Gazibarić
 * @version 1.0
 *
 */
public class SkipCommand implements Command {

	/**
	 * number that represents how many of turtle's effective length turtle moves
	 */
	private double step;
	
	/**
	 * Constructor that creates new <code>SkipCommand</code> object.
	 * 
	 * @param step <code>double</code> number that represents how many of turtle's effective length turtle moves
	 * @throws     <code>IllegalArgumentException</code> if given step is not positive
	 */
	public SkipCommand(double step) {
		if (step <= 0)
			throw new IllegalArgumentException("Step has to be positive. You entered: " + step);
		
		this.step = step;
	}
	
	@Override
	public void execute(Context ctx, Painter painter) {
		double scaler = ctx.getCurrentState().getEffectiveLength() * step;
		Vector2D translator = ctx.getCurrentState().getDirection().scaled(scaler);
		ctx.getCurrentState().getCurrentPosition().translate(translator);
	}

	
	
}
