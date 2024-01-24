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
class CameraPlaneIntersections {
	/** Camera builder for the tests */
	private final Camera.Builder cameraBuilder = Camera.getBuilder()
			// .setRayTracer(new SimpleRayTracer(new Scene("Test")))
			// .setImageWriter(new ImageWriter("Test", 1, 1))
			.setP0(Point.ZERO).setDirection(new Vector(0, 0, -1), new Vector(0, -1, 0)).setVpDistance(1);

	/**
	 * Test method for the integration of findIntersections (Plane) and constructRay
	 * (Camera)
	 */
	@Test
	void test() {
		// TC01: The plane is orthogonal to the view plane rays
		Camera camera1 = cameraBuilder.setVpSize(3, 3).build();
		assertEquals(9,
				camera1.numOfIntersections(new Geometries(new Plane(new Point(0, 0, -3), new Vector(0, 0, 1))), 3, 3),
				"Plane is orthogonal to the view plane rays");

		// TC02: The plane is in front of the view plane but is not orthogonal to the
		// rays coming from it
		assertEquals(9,
				camera1.numOfIntersections(new Geometries(new Plane(new Point(0, 0, -3), new Vector(0, 1, -3))), 3, 3),
				"Plane is not orthogonal to the view plane rays");

		// TC03: The plane is parallel
		assertEquals(6,
				camera1.numOfIntersections(new Geometries(new Plane(new Point(0, 0, -4), new Vector(0, 1, -1))), 3, 3),
				"Plane is in an angle towards the view plane");
	}

}
