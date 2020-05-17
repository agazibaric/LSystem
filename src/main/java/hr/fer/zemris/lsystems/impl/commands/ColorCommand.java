package hr.fer.zemris.lsystems.impl.commands;

import java.awt.Color;
import java.util.Objects;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;

/**
 * Class represents command that can change color of <code>TurtleState</code>.
 * 
 * @author Ante Gazibarić
 * @version 1.0
 *
 */
public class ColorCommand implements Command {

	/**
	 * color used for setting <code>TurtleState</code> color
	 */
	private Color color;
	
	/**
	 * Constructor for creating new <code>ColorCommand</code>.
	 * 
	 * @param color <code>Color</code> that represents color that command uses
	 * @throws      <code>NullPointerException</code> if given color is <code>null</code>
	 */
	public ColorCommand(Color color) {
		this.color = Objects.requireNonNull(color);
	}
	
	@Override
	public void execute(Context ctx, Painter painter) {
		ctx.getCurrentState().setColor(color);
	}

}
