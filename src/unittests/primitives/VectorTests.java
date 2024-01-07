/**
 * 
 */
package unittests.primitives;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.Vector;

/**
 * Unit tests for primitives.Vector class
 */
class VectorTests {
/**
 * Vector values for testing the operations
 */
	Vector v1 = new Vector(1, 2, 3);
	Vector v1Opposite = new Vector(-1, -2, -3);
	Vector v2 = new Vector(-2, -4, -6);
	Vector v3 = new Vector(0, 3, -2);
	Vector v4 = new Vector(1, 2, 2);	
	
	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	void testAddVector() {
		// Test case 1: Check if adding opposite vector to vector throws an exception
        assertThrows(Exception.class, () -> v1.add(v1Opposite),
                "ERROR: Vector + -itself does not throw an exception");

        // Test case 2: Check if subtracting vector from itself throws an exception
        assertThrows(Exception.class, () -> v1.subtract(v1),
                "ERROR: Vector - itself does not throw an exception");

        // Test case 3: Check if adding vectors produces the correct result
        assertEquals(v1Opposite, v1.add(v2), "ERROR: Vector + Vector does not work correctly");

        // Test case 4: Check if subtracting vectors produces the correct result
        assertEquals(new Vector(3, 6, 9), v1.subtract(v2), "ERROR: Vector - Vector does not work correctly");
    }

	/**
	 * Test method for {@link primitives.Vector#scale(double)}.
	 */
	@Test
	void testScale() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	void testDotProduct() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	@Test
	void testCrossProduct() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link primitives.Vector#lengthSquared()}.
	 */
	@Test
	void testLengthSquared() {
		// TC01: Check if lengthSquared() returns the correct value (9)
        assertEquals(9, v4.lengthSquared(), "ERROR: lengthSquared() wrong value");
	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	void testLength() {
        // TC01: Check if length() returns the correct value (3)
        assertEquals(3, v4.length(), "ERROR: length() wrong value");
	}

	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	void testNormalize() {
		fail("Not yet implemented");
	}

}
