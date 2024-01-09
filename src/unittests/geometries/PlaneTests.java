/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.Plane;
import primitives.Point;

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
		// ============ Equivalence Partitions Tests ==============
		
        // TC01: Correct plane with vertices in correct order
		assertDoesNotThrow(() -> { new Plane(new Point(1, 0, 0), new Point(0, 0, 1), new Point(0, 1, 0)); }, 
        "Failed constructing a correct plane");
		
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
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormalPoint() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link geometries.Plane#getNormal()}.
	 */
	@Test
	void testGetNormal() {
		fail("Not yet implemented");
	}

}
