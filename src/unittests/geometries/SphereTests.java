/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Comparator;
import org.junit.jupiter.api.Test;
import geometries.Sphere;
import primitives.*;

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
		Point center = new Point(0, 0, 0);

		// Create a sphere with a center at (0, 0, 0) and a radius of 1
		Sphere sphere = new Sphere(center, 1.0);

		// Choose a point on the surface of the sphere
		Point surfacePoint = new Point(1, 0, 0);

		// Calculate the expected normal vector at the surface point
		Vector expectedNormal = new Vector(1, 0, 0);

		// Get the actual normal vector from the getNormal method
		Vector actualNormal = sphere.getNormal(surfacePoint);

		// Assert that the actual normal vector is approximately equal to the expected
		// normal
		assertEquals(expectedNormal, actualNormal, "getNormal should return the correct normal vector");

	}

	private final Point p001 = new Point(0, 0, 1);
	private final Point p101 = new Point(1, 0, 1);
	private final Point p100 = new Point(1, 0, 0);
	private final Point p200 = new Point(2, 0, 0);
	private final Vector v001 = new Vector(0, 0, 1);
	private final Vector v100 = new Vector(1, 0, 0);

	/**
	 * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}
	 */
	@Test
	public void testFindIntersections() {
		Sphere sphere = new Sphere(p100, 1d);
		final Point gp1 = new Point(0.0651530771650466, 0.355051025721682, 0);
		final Point gp2 = new Point(1.53484692283495, 0.844948974278318, 0);
		final var exp = List.of(gp1, gp2);
		final Vector v310 = new Vector(3, 1, 0);
		final Vector v110 = new Vector(1, 1, 0);
		final Point p01 = new Point(-1, 0, 0);
		
		// ============ Equivalence Partitions Tests ==============
		// TC01: Ray's line is outside the sphere (0 points)
		assertNull(sphere.findIntersections(new Ray(p01, v110)), "Ray's line out of sphere");

		// TC02: Ray starts before and crosses the sphere (2 points)
		final var result02 = sphere.findIntersections(new Ray(p01, v310)).stream()
				.sorted(Comparator.comparingDouble(p -> p.distance(p01))).toList();
		assertEquals(2, result02.size(), "Wrong number of points");
		assertEquals(exp, result02, "Ray crosses sphere");

		// TC03: Ray starts inside the sphere (1 point)
		final var result03 = sphere.findIntersections(new Ray(new Point(0.5, 0.5, 0), v110)).stream().toList();
		final var exp03 = List.of(new Point(1, 1, 0));
		assertEquals(1, result03.size(), "Wrong number of points");
		assertEquals(exp03, result03, "Ray crosses sphere");

		// TC04: Ray starts after the sphere (0 points)
		assertNull(sphere.findIntersections(new Ray(new Point(5, 5, 0), v110)), "Ray doesn't cross sphere");

		// =============== Boundary Values Tests ==================
		// **** Group: Ray's line crosses the sphere (but not the center)
		// TC11: Ray starts at sphere and goes inside (1 points)
		final var result11 = sphere.findIntersections(new Ray(p101, new Vector(0,-1,-1))).stream().toList();
		final var exp11 = List.of(new Point(1, -1, 0));
		assertEquals(1, result11.size(), "Wrong number of points");
		assertEquals(exp11, result11, "Ray crosses sphere");

		// TC12: Ray starts at sphere and goes outside (0 points)
		assertNull(sphere.findIntersections(new Ray(p101, new Vector(0,1,1))), "Ray doesn't cross sphere");
		// **** Group: Ray's line goes through the center
		// TC13: Ray starts before the sphere (2 points)
		final var result13 = sphere.findIntersections(new Ray(new Point(1,0,2), v001.scale(-1))).stream()
				.sorted(Comparator.comparingDouble(p -> p.distance(p01))).toList();
		final var exp13 = List.of(p101, new Point(1,0,-1));
		assertEquals(2, result13.size(), "Wrong number of points");
		assertEquals(exp13, result13, "Ray crosses sphere");
		// TC14: Ray starts at sphere and goes inside (1 points)
		final var result14 = sphere.findIntersections(new Ray(p101, v001.scale(-1))).stream().toList();
		final var exp14 = List.of(p200);
		assertEquals(1, result14.size(), "Wrong number of points");
		assertEquals(exp14, result14, "Ray crosses sphere");

		// TC15: Ray starts inside (1 points)
		final var result15 = sphere.findIntersections(new Ray(new Point(0.5, 0, 0), v100)).stream().toList();
		final var exp15 = List.of(p200);
		assertEquals(1, result15.size(), "Wrong number of points");
		assertEquals(exp15, result15, "Ray crosses sphere");

		// TC16: Ray starts at the center (1 points)
		final var result16 = sphere.findIntersections(new Ray(p100, v100)).stream().toList();
		final var exp16 = List.of(p200);
		assertEquals(1, result16.size(), "Wrong number of points");
		assertEquals(exp16, result16, "Ray crosses sphere");

		// TC17: Ray starts at sphere and goes outside (0 points)
		assertNull(sphere.findIntersections(new Ray(p200, v100)), //
				"Ray doesn't cross sphere");

		// TC18: Ray starts after sphere (0 points)
		assertNull(sphere.findIntersections(new Ray(new Point(3, 0, 0), v100)), //
				"Ray doesn't cross sphere");

		// **** Group: Ray's line is tangent to the sphere (all tests 0 points)
		// TC19: Ray starts before the tangent point
		assertNull(sphere.findIntersections(new Ray(p001, v100)), //
				"Ray is tangent to the sphere");

		// TC20: Ray starts at the tangent point
		assertNull(sphere.findIntersections(new Ray(new Point(1, 0, 1), v100)), //
				"Ray is tangent to the sphere");

		// TC21: Ray starts after the tangent point
		assertNull(sphere.findIntersections(new Ray(new Point(2, 0, 1), v100)), //
				"Ray is tangent to the sphere");

		// **** Group: Special cases
		// TC22: Ray's line is outside, ray is orthogonal to ray start to sphere's
		// center line
		assertNull(sphere.findIntersections(new Ray(p01, v001)), //
				"ray is orthogonal to ray start to sphere's center line");
	}
}
