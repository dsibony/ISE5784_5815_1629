/**
 * 
 */
package unittests.primitives;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

/**
 * Unit tests for primitives.Point class
 */
class PointTests {
	/**
	* Point and Vector values used for testing the operations
	*/
	Point p1 = new Point(1, 2, 3);
	Point p2 = new Point(2, 4, 6);
	Point p3 = new Point(2, 4, 5);

	Vector v1 = new Vector(1, 2, 3);
	Vector v1Opposite = new Vector(-1, -2, -3);
	
	/**
	* Test method for {@link primitives.Point#subtract(primitives.Point)}.
	*/
	@Test
	void testSubtract() {
	    // ============ Equivalence Partitions Tests ==============
		// TC01: Check if subtract method works correctly
        assertEquals(v1, p2.subtract(p1), "ERROR: (point2 - point1) does not work correctly");
        
        // =============== Boundary Values Tests ==================
        // TC10: Check if the correct exception is thrown when subtracting a point from itself
        assertThrows(IllegalArgumentException.class, () -> {
        	p1.subtract(p1);
			out.println("ERROR: (point - itself) does not throw an exception");}, 
                "ERROR: (point - itself) throws wrong exception");
            }
     /*
	 * Test method for {@link primitives.Point#add(primitives.Vector)}.
	 */
	@Test
	void testAdd() {
	    // ============ Equivalence Partitions Tests ==============
		// TC01: Check if adding vector to point works correctly
        assertEquals(p2, p1.add(v1), "ERROR: (point + vector) = other point does not work correctly");

        // TC02: Check if adding opposite vector to point results in the center of coordinates
        assertEquals(Point.ZERO, p1.add(v1Opposite),
                "ERROR: (point + vector) = center of coordinates does not work correctly");
    }
	

	/**
	 * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}.
	 */
	@Test
	void testDistanceSquared() {
		// TC01: Point squared distance to itself is zero
        assertEquals(0,(p1.distanceSquared(p1)), "ERROR: point squared distance to itself is not zero");

        // TC02: Squared distance between points is 9
        assertEquals(9, (p1.distanceSquared(p3)), "ERROR: squared distance between points is wrong");

        // TC03: Squared distance between points is 9 (reversed order)
        assertEquals(9, (p3.distanceSquared(p1)), "ERROR: squared distance between points is wrong");

    }

	/**
	 * Test method for {@link primitives.Point#distance(primitives.Point)}.
	 */
	@Test
	void testDistance() {
        // TC01: Point distance to itself is zero
        assertEquals(0, (p1.distance(p1)), "ERROR: point distance to itself is not zero");

        // TC02: Distance between points is 3
        assertEquals(3, (p1.distance(p3)), "ERROR: distance between points is wrong");

        // TC03: Distance between points is 3 (reversed order)
        assertEquals(3, (p3.distance(p1)), "ERROR: distance between points is wrong");
	}

}
