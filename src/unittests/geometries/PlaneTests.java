/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.Plane;
import primitives.Point;
import primitives.Vector;

/**
 * Unit tests for geometries.Plane class
 */
class PlaneTests {
	/**
	 * Test method for
	 * {@link geometries.Plane#Plane(primitives.Point, primitives.Point, primitives.Point)}.
	 */
	@Test
	public void testConstructor() {
		// =============== Boundary Values Tests ==================
		
		// TC10: The first point and the second point are unified
		Point unifiedPoint = new Point(1, 2, 3);
		
		assertThrows(IllegalArgumentException.class, //
				() -> { new Plane(unifiedPoint, unifiedPoint, new Point(4, 5, 6)); }, 
				"constructed a plane with same 2 points");
		
		// TC11: Co-linear points
		Point point1 = new Point(1, 2, 3);
		Point point2 = new Point(2, 4, 6);
		Point point3 = new Point(3, 6, 9);

        assertThrows(IllegalArgumentException.class, () -> { new Plane(point1, point2, point3); }, 
        "constructed a plane with co linear points");
	}
	
	/**
	 * Test method for {@link geometries.Plane#getNormal()}.
	 */
	@Test
	void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: Get normal of a plane using the constructor with 3 points

        // Define three non-collinear points to represent the plane
		Point point1 = new Point(2, 3, 4);
		Point point2 = new Point(5, 6, 7);
		Point point3 = new Point(1, 8, 9);

        // Create a plane using the constructor that calculates the normal from the points
        Plane plane = new Plane(point1, point2, point3);

        // Calculate the expected normal vector (already normalized in this example)
        Vector expectedNormal = new Vector(0, -0.70711, 0.70711).normalize();

        // Get the actual normal vector from the getNormal method
        Vector actualNormal = plane.getNormal();
        
        // Assert that the actual normal vector is approximately equal to the expected normal
        assertTrue(expectedNormal.equals(actualNormal) || expectedNormal.equals(actualNormal.scale(-1)), "getNormal should return the correct normal vector");
    }
}
