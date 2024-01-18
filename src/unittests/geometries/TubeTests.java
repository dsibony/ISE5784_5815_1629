/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.Tube;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Unit tests for geometries.Tube class
 */
class TubeTests {

	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormal() {
		// Create a tube with a mainRay and radius
		Ray mainRay = new Ray(new Point(1, 0, 0), new Vector(1, 0, 0));
		double radius = 1.0;
		Tube tube = new Tube(mainRay, radius);

		// ============ Equivalence Partitions Tests ==============
		// TC01: Get normal at a specific point on the surface of the tube

		// Create a tube with a mainRay and radius

		// Choose a point on the surface of the tube
		Point surfacePoint = new Point(2, 0, 1);

		// Calculate the expected normal vector at the surface point
		Vector expectedNormal = new Vector(0, 0, 1);

		// Get the actual normal vector from the getNormal method
		Vector actualNormal = tube.getNormal(surfacePoint);

		// Assert that the actual normal vector is equal to the expected normal
		assertEquals(expectedNormal, actualNormal, "getNormal should return the correct normal vector");

		// =============== Boundary Values Tests ==================
		// TC11: Get normal at a point exactly on the axis of the tube

		// Choose a point exactly on the axis of the tube
		Point axisPoint = new Point(0, 0, 0);

		expectedNormal = new Vector(1, 0, 0);
		// Get the actual normal vector from the getNormal method
		actualNormal = tube.getNormal(axisPoint);

		// Assert that the actual normal vector is approximately equal to the
		// expected normal
		assertEquals(expectedNormal, actualNormal,
				"getNormal should return the correct normal vector on the tube axis");
	}

}
