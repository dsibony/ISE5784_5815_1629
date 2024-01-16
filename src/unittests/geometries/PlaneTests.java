/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import geometries.Plane;
import primitives.Point;
import primitives.Ray;
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
				() -> {
					new Plane(unifiedPoint, unifiedPoint, new Point(4, 5, 6));
				}, "constructed a plane with same 2 points");

		// TC11: Co-linear points
		Point point1 = new Point(1, 2, 3);
		Point point2 = new Point(2, 4, 6);
		Point point3 = new Point(3, 6, 9);

		assertThrows(IllegalArgumentException.class, () -> {
			new Plane(point1, point2, point3);
		}, "constructed a plane with co linear points");
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

		// Create a plane using the constructor that calculates the normal from the
		// points
		Plane plane = new Plane(point1, point2, point3);

		// Calculate the expected normal vector (already normalized in this example)
		Vector expectedNormal = new Vector(0, -0.70711, 0.70711).normalize();

		// Get the actual normal vector from the getNormal method
		Vector actualNormal = plane.getNormal();

		// Assert that the actual normal vector is approximately equal to the expected
		// normal
		assertTrue(expectedNormal.equals(actualNormal) || expectedNormal.equals(actualNormal.scale(-1)),
				"getNormal should return the correct normal vector");
	}

	/**
	 * Test method for {@link geometries.Plane#findIntsersections(Ray ray)}.
	 */
	@Test
	public void testFindIntersections() {
		final Vector v001 = new Vector(0, 0, 1);
		Plane plane = new Plane(Point.ZERO, v001);
		final Vector v011 = new Vector(0, 1, 1);
		final Vector v100 = new Vector(1, 0, 0);
		final Point p01 = new Point(0, 0, -1);
		final Point p100 = new Point(1, 0, 0);
		final Point p001 = new Point(0, 0, 1);
		// ============ Equivalence Partitions Tests ==============
		// TC01: Ray intersects the plane
		final var result01 = plane.findIntersections(new Ray(p01, v011)).stream().toList();
		final var exp01 = List.of(new Point(0, 1, 0));
		assertEquals(1, result01.size(), "Wrong number of points");
		assertEquals(exp01, result01, "Ray crosses plane");

		// TC02: Ray does not intersect the plane
		assertNull(plane.findIntersections(new Ray(p01, v011.scale(-1))), //
				"Ray doesn't cross plane");

		// =============== Boundary Values Tests ==================
		// **** Group: Ray is parallel to the plane
		// TC10: The ray is included in the plane
		assertNull(plane.findIntersections(new Ray(p100, v100)), //
				"The ray is included in the plane");
		
		// TC11: The ray is not included in the plane
		assertNull(plane.findIntersections(new Ray(p001, v100)), //
				"The ray is not included in the plane");
		
		// **** Group: Ray is orthogonal to the plane
		// TC12: Ray starts before the plane
		final var result12 = plane.findIntersections(new Ray(new Point(0, 0, -2), v001)).stream().toList();
		final var exp12 = List.of(Point.ZERO);
		assertEquals(1, result12.size(), "Wrong number of points");
		assertEquals(exp12, result12, "Ray crosses plane");

		// TC13: Ray starts at the plane
		assertNull(plane.findIntersections(new Ray(Point.ZERO, v001)), //
				"Ray doesn't cross plane");
		
		// TC14: Ray starts after the plane
		assertNull(plane.findIntersections(new Ray(p001, v001)), //
				"Ray doesn't cross plane");

		// **** Group: Special cases
		// TC15: Ray is neither orthogonal nor parallel to the plane and begins at the plane
		assertNull(plane.findIntersections(new Ray(p100, v011)), //
				"Ray doesn't cross plane");

		// TC16: Ray is neither orthogonal nor parallel to the plane and begins in the
		// same point which appears as reference point in the plane
		assertNull(plane.findIntersections(new Ray(Point.ZERO, v011)), //
				"Ray doesn't cross plane");
	}
}
