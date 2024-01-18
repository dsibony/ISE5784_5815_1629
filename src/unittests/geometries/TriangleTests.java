/*
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import geometries.Triangle;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Testing Triangles
 * 
 * @author Dan
 */
public class TriangleTests {

	/**
	 * Delta value for accuracy when comparing the numbers of type 'double' in
	 * assertEquals
	 */
	private final double DELTA = 0.000001;

	/**
	 * Test method for
	 * {@link geometries.Triangle#Triangle(primitives.Point, primitives.Point, primitives.Point)}.
	 */
	@Test
	public void testConstructor() {
		// ============ Equivalence Partitions Tests ==============

		// TC01: Correct triangle with vertices in correct order
		assertDoesNotThrow(() -> new Triangle(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0)),
				"Failed constructing a correct triangle");

		// =============== Boundary Values Tests ==================

		// TC10: Vertex on a side of a triangle
		assertThrows(IllegalArgumentException.class, //
				() -> new Triangle(new Point(0, 0, 1), new Point(0, 0, 2), new Point(0, 0, 1.5)),
				"Constructed a triangle with vertex on a side");

		// TC11: Last point = first point
		assertThrows(IllegalArgumentException.class, //
				() -> new Triangle(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 0, 1)),
				"Constructed a triangle with the same point being the first and the last one");

		// TC12: Co-located points
		assertThrows(IllegalArgumentException.class, //
				() -> new Triangle(new Point(0, 0, 1), new Point(1, 0, 0), new Point(1, 0, 0)),
				"Constructed a triangle with co-located points");

	}

	/** Test method for {@link geometries.Triangle#getNormal(primitives.Point)}. */
	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: There is a simple single test here - using a triangle
		Point[] pts = { new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0) };
		Triangle tri = new Triangle(pts[0], pts[1], pts[2]);
		// ensure there are no exceptions
		assertDoesNotThrow(() -> tri.getNormal(new Point(0, 0, 1)), "");
		// generate the test result
		Vector result = tri.getNormal(new Point(0, 0, 1));
		// ensure |result| = 1
		assertEquals(1, result.length(), DELTA, "Triangle's normal is not a unit vector");
		// ensure the result is orthogonal to the triangle
		for (int i = 0; i < 3; ++i)
			assertEquals(0d, result.dotProduct(pts[i].subtract(pts[i == 0 ? 2 : i - 1])), DELTA,
					"Triangle's normal is not orthogonal to one of the edges");
	}

	/**
	 * Test method for {@link geometries.Triangle#findIntersections(Ray ray)}.
	 */
	@Test
	public void testFindIntersections() {
		Triangle triangle = new Triangle(new Point(-1,-1,0), new Point(2, 0, 0), new Point(0, 2, 0));

		// ============ Equivalence Partitions Tests ==============
		// TC01: Ray intersects the triangle
		final var result01 = triangle.findIntersections(new Ray(new Point(0.5, 0.5, -1), new Vector(0, 0, 1))).stream()
				.toList();
		final var exp01 = List.of(new Point(0.5, 0.5, 0));
		assertEquals(1, result01.size(), "Wrong number of points");
		assertEquals(exp01, result01, "Ray crosses triangle");

		// TC02: Ray intersects with the projection of the triangle
		assertNull(triangle.findIntersections(new Ray(new Point(-2, -2, -1), new Vector(0, 0, 1))),
				"Ray doesn't cross triangle");

		// TC03: Ray does not intersect the triangle
		assertNull(triangle.findIntersections(new Ray(new Point(-1, 1, -1), new Vector(0, 0, 1))),
				"Ray doesn't cross triangle");

		// =============== Boundary Values Tests ==================
		// TC11: Ray intersects with a vertex of the triangle
		assertNull(triangle.findIntersections(new Ray(new Point(2, 0, -1), new Vector(0, 0, 1))),
				"Ray doesn't cross triangle");

		// TC12: Ray intersects with a side of the triangle
		assertNull(triangle.findIntersections(new Ray(new Point(1, 1, -1), new Vector(0, 0, 1))),
				"Ray doesn't cross triangle");

		// TC13: Ray intersects with the projection of a side of the triangle
		assertNull(triangle.findIntersections(new Ray(new Point(-2, 4, -1), new Vector(0, 0, 1))),
				"Ray doesn't cross triangle");
	}
}
