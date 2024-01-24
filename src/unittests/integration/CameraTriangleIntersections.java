/**
 * 
 */
package unittests.integration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.*;
import primitives.*;
import renderer.Camera;

/**
 * 
 */
class CameraTriangleIntersections {
	/** Camera builder for the tests */
	private final Camera.Builder cameraBuilder = Camera.getBuilder()
			// .setRayTracer(new SimpleRayTracer(new Scene("Test")))
			// .setImageWriter(new ImageWriter("Test", 1, 1))
			.setP0(Point.ZERO).setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0)).setVpDistance(1);

	/**
	 * Test method for the integration of findIntersections (Triangle) and
	 * constructRay (Camera)
	 */
	@Test
	void test() {
		// TC01: The triangle is in front of only one pixel from the view plane
		Camera camera1 = cameraBuilder.setVpSize(3, 3).build();
		assertEquals(1,
				camera1.numOfIntersections(
						new Geometries(new Triangle(new Point(0, 1, -2), new Point(1, -1, 2), new Point(1, -1, -2))), 3,
						3),
				"Triangle is in front of one view plane pixel");

		// TC02: The triangle is in front of multiple pixels from the view plane
		assertEquals(2,
				camera1.numOfIntersections(
						new Geometries(new Triangle(new Point(0, 20, -2), new Point(1, -1, -2), new Point(-1, -1, -2))),
						3, 3),
				"Triangle is in front of two view plane pixels");
	}

}
