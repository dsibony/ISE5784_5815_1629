/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import geometries.*;
import primitives.*;

/**
 * Unit tests for geometries.Geometries class
 */
class GeometriesTests {
	/**
	 * Test method for
	 * {@link geometries.Geometries#findIntersections(primitives.Ray)}.
	 */
	@Test
	void testFindIntersections() {
		// Ray ray = new Ray(new Point(0, 0, -1), new Vector(0, 0, 1));
		Point p200 = new Point(2, 0, 0);
		Point p020 = new Point(0, 2, 0);
		Point pM1M10 = new Point(-1, -1, 0);
		Sphere sphere = new Sphere(new Point(1, 0, 0), 1d);
		Plane plane = new Plane(p200, p020, pM1M10);
		Triangle triangle = new Triangle(p200, p020, pM1M10);
		Geometries emptyGeo = new Geometries();
		Geometries geoIntersected = new Geometries(plane, sphere, triangle);
//		Geometries noGeoIntersected = new Geometries(sphere, plane, triangle);
//		Geometries oneGeoIntersected = new Geometries(sphere, plane, triangle);
//		Geometries allGeoIntersected = new Geometries(sphere, plane, triangle);

		// ============ Equivalence Partitions Tests ==============
		// TC01: Some geometries get intersected by the ray
		final var result01 = geoIntersected.findIntersections(new Ray(new Point(0, 0, -10), new Vector(0, 1, 1)));
		assertEquals(1, result01.size(), "Some geometries get intersected by the ray");

		// =============== Boundary Values Tests ==================
		// TC11: Empty geometries array
		assertNull(emptyGeo.findIntersections(new Ray(new Point(-5, -4, -3), new Vector(1, 1, 0))),
				"No geometries are in the array");

		// TC12: No geometries get intersected by the ray
		assertNull(geoIntersected.findIntersections(new Ray(new Point(0, 0, -3), new Vector(1, 0, 0))),
				"No geometries get intersected by the ray");

		// TC13: Only one geometry gets intersected by the ray
		final var result13 = geoIntersected.findIntersections(new Ray(new Point(0, 0, -5), new Vector(0, 1, 1)));
		assertEquals(1, result13.size(), "Only one geometry gets intersected by the ray");

		// TC14: All of the geometries get intersected by the ray
		final var result14 = geoIntersected.findIntersections(new Ray(new Point(0.5, 0.5, -10), new Vector(0, 0, 1)));
		assertEquals(3, result14.size(), "All of the geometries get intersected by the ray");
	}

}
