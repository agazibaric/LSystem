package hr.fer.zemris.lsystems.impl;

import java.awt.Color;
import java.util.Objects;

import hr.fer.zemris.math.Vector2D;

/**
 * Class represents model of turtle that is used for moving on the screen </br>
 * and drawing some pattern that can be modified with given current position, </br>
 * direction, color and length of the line that is drawn.
 * 
 * @author Ante Gazibarić
 * @version 1.0
 *
 */
public class TurtleState {

	/**
	 * position from which turtle starts its path
	 */
	private Vector2D currentPosition;
	/**
	 * direction in which turtle moves
	 */
	private Vector2D direction;
	/**
	 * color in which turtle draws line
	 */
	private Color color;
	/**
	 * length of the line that is drawn
	 */
	private double effectiveLength;
	
	/**
	 * Constructor for creating new <code>TurtleState</code> object.
	 * 
	 * @param currentPosition <code>Vector2D</code> that represents position from which turtle starts moving
	 * @param direction		  <code>Vector2D</code> that represents direction in which turtle moves
	 * @param color           <code>Color</code> in which turtle draws line
	 * @param effectiveLength <code>double</code> that represents length of the line
	 * @throws 	    		  <code>NullPointerException</code> if any of the arguments is <code>null</code>
	 */
	public TurtleState(Vector2D currentPosition, Vector2D direction, Color color, double effectiveLength) {
		this.currentPosition = Objects.requireNonNull(currentPosition);
		this.direction = Objects.requireNonNull(getUnitLengthDirection(direction));
		this.color = Objects.requireNonNull(color);
		this.effectiveLength = effectiveLength;
	}

	/**
	 * Method that returns copy of this <code>TurtleState</code> as a new object.
	 * 
	 * @return new <code>TurtleState</code> object that represents copy of this object
	 */
	public TurtleState copy() {
		return new TurtleState(currentPosition.copy(), direction.copy(), color, effectiveLength);
	}
	
	/**
	 * Helper method for getting unit length vector with the same direction as given vector.
	 * 
	 * @param direction <code>Vector2D</code> whose unit vector is returned
	 * @return          <code>Vector2D</code> with unit length with the same direction as given vector
	 */
	private Vector2D getUnitLengthDirection(Vector2D direction) {
		double x = direction.getX();
		double y = direction.getY();
		double vectorLength = Math.sqrt(x * x + y * y);
		
		return direction.scaled(1.0 / vectorLength);
	}

	/**
	 * Method returns current position of the turtle.
	 * 
	 * @return <code>Vector2D</code> that represents current position of the turtle 
	 */
	public Vector2D getCurrentPosition() {
		return currentPosition;
	}

	/**
	 * Method used for setting current position of the turtle.
	 * 
	 * @param currentPosition <code>Vector2D</code> that represents new current position of the turtle
	 */
	public void setCurrentPosition(Vector2D currentPosition) {
		this.currentPosition = currentPosition;
	}

	/**
	 * Method returns current direction of the turtle.
	 * 
	 * @return <code>Vector2D</code> that represents current direction of the turtle
	 */
	public Vector2D getDirection() {
		return direction;
	}

	/**
	 * Method sets direction of the turtle.
	 * 
	 * @param direction <code>Vector2D</code> that represents new direction of the turtle
	 */
	public void setDirection(Vector2D direction) {
		this.direction = direction;
	}

	/**
	 * Method returns color in which turtle draws lines.
	 * 
	 * @return <code>Color</code> that represents current color in which turtle draws lines
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Method sets color in which turtle draws lines.
	 * 
	 * @param color <code>Color</code> that represents new color in which turtle draws lines.
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Method returns current length of the line that turtle draws.
	 * 
	 * @return <code>double</code> that represents effective length of the line that turtle draws
	 */
	public double getEffectiveLength() {
		return effectiveLength;
	}

	/**
	 * Method sets effective length of the line that turtle draws
	 * 
	 * @param effectiveLength <code>double</code> that represents effective length of the line that turtle draws
	 */
	public void setEffectiveLength(double effectiveLength) {
		this.effectiveLength = effectiveLength;
	}
	
	
}
