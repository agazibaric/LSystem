package hr.fer.zemris.math;

import java.util.Objects;
import static java.lang.Math.sin;
import static java.lang.Math.cos;

/**
 * Class represents vector in two dimensions. </br>
 * It offers methods for vector translation, rotation, scaling, copying.
 * 
 * @author Ante Gazibarić
 * @version 1.0
 *
 */
public class Vector2D {
	
	/**
	 * x component of vector
	 */
	private double x;
	/**
	 * y component of vector
	 */
	private double y;
	
	/**
	 * Constructor that creates new <code>Vector2D</code> object.
	 * 
	 * @param x <code>double</code> x component of vector
	 * @param y <code>double</code> y component of vector
	 */
	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Method that translates this vector by given <code>offset</code>.
	 * 
	 * @param offset <code>Vector2D</code> offset by which this vector is translated
	 * @throws 		 <code>NullPointerException</code> if given offset is <code>null</code>
	 */
	public void translate(Vector2D offset) {
		Objects.requireNonNull(offset);
		
		this.x += offset.x;
		this.y += offset.y;
	}
	
	/**
	 * Method returns new <code>Vector2D</code> object that represents translation of this vector by given <code>offset</code>.
	 * 
	 * @param offset <code>Vector2D</code> that represents offset by which this vector is translated
	 * @return       new <code>Vector2D</code> object that represents translation of this vector by given <code>offset</code>.
	 * @throws 		 <code>NullPointerException</code> if given offset is <code>null</code>
	 */
	public Vector2D translated(Vector2D offset) {
		Objects.requireNonNull(offset);
		return new Vector2D(this.x + offset.x, this.y + offset.y);
	}
	
	/**
	 * Method that rotates this vector by given <code>angle</code>.
	 * 
	 * @param angle <code>double</code> angle by which this vector is rotated.
	 */
	public void rotate(double angle) {
		double angleInRad = angle * Math.PI / 180;
		double newX = getRotatedX(angleInRad);
		double newY = getRotatedY(angleInRad);
		setX(newX);
		setY(newY);
	}
	
	/**
	 * Method returns new <code>Vector2D</code> object that represents rotation of this vector by given <code>angle</code>.
	 * 
	 * @param angle <code>Vector2D</code> that represents angle by which this vector is rotated
	 * @return       new <code>Vector2D</code> object that represents rotation of this vector by given <code>angle</code>.
	 */
	public Vector2D rotated(double angle) {
		return new Vector2D(getRotatedX(angle), getRotatedY(angle));
	}
	
	/**
	 * Method that scales this vector by given <code>scaler</code>.
	 * 
	 * @param scaler <code>double</code> scaler by which this vector is scaled.
	 */
	public void scale(double scaler) {
		x *= scaler;
		y *= scaler;
	}
	
	/**
	 * Method returns new <code>Vector2D</code> object that represents this vector scaled by given <code>scaler</code>.
	 * 
	 * @param scaler <code>Vector2D</code> that represents scaler by which this vector is scaled
	 * @return       new <code>Vector2D</code> object that represents this vector scaled by given <code>scaler</code>.
	 */
	public Vector2D scaled(double scaler) {
		return new Vector2D(x * scaler, y * scaler);
	}
	
	/**
	 * Method returns copy of this <code>Vector2D</code>
	 * 
	 * @return <code>Vector2D</code> that represents copy of this vector
	 */
	public Vector2D copy() {
		return new Vector2D(x, y);
	}
	
	/**
	 * Method returns x component of vector.
	 * 
	 * @return <code>double</code> x component of vector.
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * Method returns y component of vector.
	 * 
	 * @return <code>double</code> y component of vector.
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * Method sets x component of vector.
	 * 
	 * @return <code>double</code> x component of vector.
	 */
	private void setX(double x) {
		this.x = x;
	}
	
	/**
	 * Method sets y component of vector.
	 * 
	 * @return <code>double</code> y component of vector.
	 */
	private void setY(double y) {
		this.y = y;
	}
	
	/**
	 * Helper method for calculating x component of this vector that is rotated by given <code>angle</code>.
	 * 
	 * @param angle <code>double</code> angle by which vector is rotated
	 * @return      <code>double</code> x component of rotated vector
	 */
	private double getRotatedX(double angle) {
		return x * cos(angle) - y * sin(angle);
	}
	
	/**
	 * Helper method for calculating y component of this vector that is rotated by given <code>angle</code>.
	 * 
	 * @param angle <code>double</code> angle by which vector is rotated
	 * @return      <code>double</code> y component of rotated vector
	 */
	private double getRotatedY(double angle) {
		return y * cos(angle) + x * sin(angle);
	}
	
	
}
