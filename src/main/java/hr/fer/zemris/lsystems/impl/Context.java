package hr.fer.zemris.lsystems.impl;

import hr.fer.zemris.java.custom.collections.ObjectStack;

/**
 * <code>Context</code> is stack-like data storage for storing <code>TurtleState</code> objects. </br>
 * It offers methods for pushing or popping objects from <code>Context</code>.
 * 
 * @author Ante Gazibarić
 * @version 1.0
 *
 */
public class Context {

	/**
	 * private stack for storing data
	 */
	private ObjectStack stack;
	
	/**
	 * Constructor for creating new <code>Context</code>.
	 * It accepts no arguments.
	 */
	public Context() {
		stack = new ObjectStack();
	}
	
	/**
	 * Method returns object that is last pushed to the context.
	 * 
	 * @return <code>Object</code> that is last pushed to the context
	 */
	public TurtleState getCurrentState() {
		return (TurtleState) stack.peek();
	}
	
	/**
	 * Method used for storing object to the context.
	 * 
	 * @param state <code>TurtleState</code> object that is stored
	 */
	public void pushState(TurtleState state) {
		stack.push(state);
	}
	
	/**
	 * Method that removes last <code>TurtleState</code> object </br>
	 * that is pushed to the context.
	 */
	public void popState() {
		stack.pop();
	}
	
}
