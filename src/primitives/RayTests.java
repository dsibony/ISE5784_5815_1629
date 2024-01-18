/**
 * 
 */
package primitives;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * 
 */
class RayTests {

	/**
	 * Test method for {@link primitives.Ray#getPoint(double)}.
	 */
	@Test
	void testGetPoint() {
		Ray ray = new Ray(new Point(1, 0, 0), new Vector(0, 1, 1));
		// ============ Equivalence Partitions Tests ==============
		// TC01: Negative distance
		Point p01 = new Point(1, -1, -1);
		assertEquals(p01, ray.getPoint(-1), "Negative distance");
		
		// TC02: Positive distance
		Point p02 = new Point(1, 1, 1);
		assertEquals(p02, ray.getPoint(1), "Positive distance");

		// =============== Boundary Values Tests ==================
		// TC11: Zero distance
		Point p11 = new Point(1, 0, 0);
		assertEquals(p11, ray.getPoint(0), "Zero distance");
	}

}
