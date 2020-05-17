package hr.fer.zemris.math;

import org.junit.Assert;
import org.junit.Test;

public class Vector2DTest {

	private static double THRESHOLD = 1E-5;
	
	@Test
	public void testTranslation() {
		Vector2D v1 = new Vector2D(2, 4);
		v1.translate(new Vector2D(1, 1));
		double expectedX = 3;
		double expectedY = 5;
		double actualX = v1.getX();
		double actualY = v1.getY();
		Assert.assertEquals(expectedX, actualX, THRESHOLD);
		Assert.assertEquals(expectedY, actualY, THRESHOLD);
	}
	
	@Test
	public void testRotation1() {
		Vector2D v1 = new Vector2D(2, 0);
		v1.rotate(90);
		double expectedX = 0;
		double expectedY = 2;
		double actualX = v1.getX();
		double actualY = v1.getY();
		Assert.assertEquals(expectedX, actualX, THRESHOLD);
		Assert.assertEquals(expectedY, actualY, THRESHOLD);
	}
	
	@Test
	public void testRotation2() {
		Vector2D v1 = new Vector2D(2, 0);
		v1.rotate(270);
		double expectedX = 0;
		double expectedY = -2;
		double actualX = v1.getX();
		double actualY = v1.getY();
		Assert.assertEquals(expectedX, actualX, THRESHOLD);
		Assert.assertEquals(expectedY, actualY, THRESHOLD);
	}
	
	@Test
	public void testRotation3() {
		Vector2D v1 = new Vector2D(2, 0);
		v1.rotate(-90);
		double expectedX = 0;
		double expectedY = -2;
		double actualX = v1.getX();
		double actualY = v1.getY();
		Assert.assertEquals(expectedX, actualX, THRESHOLD);
		Assert.assertEquals(expectedY, actualY, THRESHOLD);
	}
	
	@Test
	public void testScalling() {
		Vector2D v1 = new Vector2D(2, 1);
		double scaler = 2.0;
		v1.scale(scaler);
		double expectedX = 4;
		double expectedY = 2;
		double actualX = v1.getX();
		double actualY = v1.getY();
		Assert.assertEquals(expectedX, actualX, THRESHOLD);
		Assert.assertEquals(expectedY, actualY, THRESHOLD);
	}
	
	@Test
	public void testCopy() {
		Vector2D v1 = new Vector2D(2, 0);
		Vector2D v2 = v1.copy();
		double expectedX = 2;
		double expectedY = 0;
		double actualX = v2.getX();
		double actualY = v2.getY();
		Assert.assertEquals(expectedX, actualX, THRESHOLD);
		Assert.assertEquals(expectedY, actualY, THRESHOLD);
	}
	
}
