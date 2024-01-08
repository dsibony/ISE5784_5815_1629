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
	 * Test method for
	 * {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	 @Test
	 void testCrossProduct() {
	 // ============ Equivalence Partitions Tests ==============
	 Vector vr = v1.crossProduct(v2);
	 // TC01: Test that length of cross-product is proper (orthogonal vectors taken
	 // for simplicity)
	 assertEquals(v1.length() * v3.length(), vr.length(), DELTA, "crossProduct() wrong result length");
	 // TC02: Test cross-product result orthogonality to its operands
	 assertEquals(0, vr.dotProduct(v1), "crossProduct() result is not orthogonal to 1st operand");
	 assertEquals(0, vr.dotProduct(v3), "crossProduct() result is not orthogonal to 2nd operand");
	 // =============== Boundary Values Tests ==================
	 // TC11: test zero vector from cross-product of parallel vectors
	 assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v2), //
	 "crossProduct() for parallel vectors does not throw an exception");
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
		Vector u = v1.normalize();
		
		 // TC01: Check if the normalized vector is a unit vector
        assertEquals(1, u.length(), DELTA, "ERROR: the normalized vector is not a unit vector");

        // TC02: Check if vectors are co-lined (cross product should throw an exception)
        assertThrows(Exception.class, () -> v1.crossProduct(u),
                "ERROR: the normalized vector is not parallel to the original one");

        // TC03: Check if the dot product indicates opposite direction
        assertEquals(-1, v1.dotProduct(u), DELTA, "ERROR: the normalized vector is opposite to the original one");
	}

}
