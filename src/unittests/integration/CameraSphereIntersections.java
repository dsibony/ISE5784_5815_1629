/**
 * 
 */
package unittests.integration;

import static unittests.integration.IntegrationUtil.*;
import org.junit.jupiter.api.Test;

import geometries.*;
import primitives.*;
import renderer.Camera;

/**
 * 
 */
class CameraSphereIntersections {
	/** Camera builder for the tests */
	private final Camera.Builder cameraBuilder = Camera.getBuilder()
			// .setRayTracer(new SimpleRayTracer(new Scene("Test")))
			// .setImageWriter(new ImageWriter("Test", 1, 1))
			.setP0(Point.ZERO).setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0)).setVpDistance(1);

	/**
	 * Test method for the integration of findIntersections (Sphere) and
	 * constructRay (Camera)
	 */
	@Test
	void test() {
		// TC01: Sphere is in front of the camera
		Camera camera1 = cameraBuilder.setVpSize(3, 3).build();
		assertIntersections(2, camera1, new Geometries(new Sphere(new Point(0, 0, -3), 1)), 3, 3,
				"Sphere is in front of the camera");

		// TC02: Sphere goes out of the frame of the camera and goes up to the view
		// plane
		Camera camera2 = cameraBuilder.setP0(new Point(0, 0, 0.5)).build();
		assertIntersections(18, camera2, new Geometries(new Sphere(new Point(0, 0, -2.5), 2.5)), 3, 3,
				"Sphere goes out of the frame");

		// TC03: Sphere is in front of the camera but goes up to the view plane
		assertIntersections(10, camera2, new Geometries(new Sphere(new Point(0, 0, -2), 2)), 3, 3,
				"Sphere goes up to the view plane");

		// TC04: The camera and the view plane are inside the Sphere
		assertIntersections(9, camera2, new Geometries(new Sphere(new Point(0, 0, -2), 4)), 3, 3,
				"The camera and the view plane are inside the sphere");

		// TC05: The Sphere is behind the camera
		assertIntersections(0, camera1, new Geometries(new Sphere(new Point(0, 0, 1), 0.5)), 3, 3,
				"The sphere is behind the camera");
	}

}
