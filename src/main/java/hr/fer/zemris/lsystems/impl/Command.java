package hr.fer.zemris.lsystems.impl;

import hr.fer.zemris.lsystems.Painter;

/**
 * Interface represents general command that can be executed.
 * 
 * @author Ante Gazibarić
 * @version 1.0
 *
 */
public interface Command {

	/**
	 * Method used for executing command.
	 * 
	 * @param ctx     <code>Context</code> that can be used in this method
	 * @param painter <code>Painter</code> that can be used in this method
	 */
	void execute(Context ctx, Painter painter);
	
}
