package hr.fer.zemris.lsystems.impl.commands;

import java.awt.Color;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;
import hr.fer.zemris.math.Vector2D;

/**
 * Class represents command that moves turtle and draws a line
 * 
 * @author Ante Gazibarić
 * @version 1.0
 *
 */
public class DrawCommand implements Command {

	/**
	 * number that represents how many of turtle's effective length turtle moves
	 */
	private double step;
	
	/**
	 * Constructor for creating new <code>DrawCommand</code> object.
	 * 
	 * @param step <code>double</code> that represents how many times of turtle's effective length turtle moves
	 * @throws     <code>IllegalArgumentException</code> if given step is not positive
	 */
	public DrawCommand(double step) {
		if (step <= 0)
			throw new IllegalArgumentException("Step has to be positive. You entered: " + step);
		
		this.step = step;
	}
	
	@Override
	public void execute(Context ctx, Painter painter) {
		Vector2D oldPosition = ctx.getCurrentState().getCurrentPosition();
		double scaler = ctx.getCurrentState().getEffectiveLength() * step;
		Vector2D translator = ctx.getCurrentState().getDirection().scaled(scaler);
		
		Vector2D newPosition = oldPosition.translated(translator);
		Color color = ctx.getCurrentState().getColor();
		float size = 1.0f;
		
		painter.drawLine(oldPosition.getX(), oldPosition.getY(), 
						 newPosition.getX(), newPosition.getY(), 
						 color, size); 
		
		ctx.getCurrentState().getCurrentPosition().translate(translator);
	}
	
}
