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
	 * Delta value for accuracy when comparing the numbers of type 'double' in
	 * assertEquals
	 */
	private final double DELTA = 0.000001;
	
	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	void testAddVector() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: Check if adding opposite vector to vector throws an exception
        assertThrows(Exception.class, () -> v1.add(v1Opposite),
                "ERROR: Vector + -itself does not throw an exception");
        
        // TC02: Check if adding vectors produces the correct result
        assertEquals(v1Opposite, v1.add(v2), "ERROR: Vector + Vector does not work correctly");
    }
	
	/**
	 * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
	 */
	@Test
	void testSubtractVector() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: Check if subtracting vector from itself throws an exception
        assertThrows(Exception.class, () -> v1.subtract(v1),
                "ERROR: Vector - itself does not throw an exception");
        
        // TC02: Check if subtracting vectors produces the correct result
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
		// ============ Equivalence Partitions Tests ==============
		// TC01: Check dotProduct() for orthogonal vectors
        assertEquals(0, v1.dotProduct(v3) ,"ERROR: dotProduct() for orthogonal vectors is not zero");

        // TC02: Check dotProduct() for wrong value
        assertEquals(0, v1.dotProduct(v3), "ERROR: dotProduct() wrong value");
	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	@Test
	void testCrossProduct() {
		// ============ Equivalence Partitions Tests ==============
		try {
            // TC01: Test that crossProduct() for parallel vectors throws an exception
            v1.crossProduct(v2);
            fail("ERROR: crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {
            // Expected exception
        }

        // TC02: Test crossProduct() result length
        Vector vr = v1.crossProduct(v3);
        assertEquals(0, vr.length() - v1.length() * v3.length(), DELTA, "ERROR: crossProduct() wrong result length");

        // TC03: Test crossProduct() result orthogonality
        assertEquals(0, vr.dotProduct(v1), DELTA, "ERROR: crossProduct() result is not orthogonal to its operands");
        assertEquals(0, vr.dotProduct(v3), DELTA, "ERROR: crossProduct() result is not orthogonal to its operands");
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
