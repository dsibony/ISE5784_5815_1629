/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.Sphere;
import primitives.Point;
import primitives.Vector;

/**
 * Unit tests for geometries.Sphere class
 */
class SphereTests {

	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: Get normal at a specific point on the surface of the sphere
		
		// Center point of the sphere
		Point center = new Point(0,0,0);
        
		// Create a sphere with a center at (0, 0, 0) and a radius of 1
        Sphere sphere = new Sphere(center, 1.0);

        // Choose a point on the surface of the sphere
        Point surfacePoint = new Point(1, 0, 0);

        // Calculate the expected normal vector at the surface point
        Vector expectedNormal = new Vector(1,0,0);

        // Get the actual normal vector from the getNormal method
        Vector actualNormal = sphere.getNormal(surfacePoint);

        // Assert that the actual normal vector is approximately equal to the expected normal
        assertEquals(expectedNormal, actualNormal, "getNormal should return the correct normal vector");

	}

}
