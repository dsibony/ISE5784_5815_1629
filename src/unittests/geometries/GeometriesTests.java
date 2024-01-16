/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.*;
import primitives.*;

/**
 * 
 */
class GeometriesTests {
	/**
	 * Test method for
	 * {@link geometries.Geometries#findIntersections(primitives.Ray)}.
	 */
	@Test
	void testFindIntersections() {
		Ray ray = new Ray(new Point(0, 0, -1), new Vector(0, 0, 1));
		Point p200 = new Point(2, 0, 0);
		Point p020 = new Point(0, 2, 0);
		Point pM1M10 = new Point(-1, -1, 0);
		Sphere sphere = new Sphere(new Point(1, 0, 0), 1d);
		Plane plane = new Plane(p200, p020, pM1M10);
		Triangle triangle = new Triangle(p200, p020, pM1M10);
		Geometries emptyGeo = new Geometries();
		Geometries someGeoIntersected = new Geometries(sphere);
		Geometries noGeoIntersected = new Geometries();
		Geometries oneGeoIntersected = new Geometries();
		Geometries allGeoIntersected = new Geometries();

		// ============ Equivalence Partitions Tests ==============
		// TC01: Some geometries get intersected by the ray
		
		// =============== Boundary Values Tests ==================
	}

}
