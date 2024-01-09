/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.Cylinder;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Unit tests for geometries.Cylinder class
 */
class CylinderTests {

	/**
	 * Test method for {@link geometries.Cylinder#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormal() {// Create a cylinder
		double radius = 2.0;
		double height = 5.0;
		Cylinder cylinder = new Cylinder(new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)), radius, height);

		// ============ Equivalence Partitions Tests ==============
		// TC01: Point on the side of the cylinder
		Point onSidePoint = new Point(1, 2, 3);
		Vector sideNormal = cylinder.getNormal(onSidePoint);
		Vector expectedSideNormal = new Vector(1, 2, 0).normalize();
		assertEquals(expectedSideNormal, sideNormal, "Equivalence Test: Side of the cylinder");

		// TC02: Point on the bottom base of the cylinder
		Point onBottomBasePoint = new Point(2, 2, 0);
		Vector bottomBaseNormal = cylinder.getNormal(onBottomBasePoint);
		Vector expectedBottomBaseNormal = new Vector(0, 0, 1);
		assertEquals(expectedBottomBaseNormal, bottomBaseNormal, "Equivalence Test: Bottom base of the cylinder");

		// TC03: Point on the top base of the cylinder
		Point onTopBasePoint = new Point(2, 2, 5);
		Vector topBaseNormal = cylinder.getNormal(onTopBasePoint);
		Vector expectedTopBaseNormal = new Vector(0, 0, 1);
		assertEquals(expectedTopBaseNormal, topBaseNormal, "Equivalence Test: Top base of the cylinder");

		// =============== Boundary Values Tests ==================
		// TC10: Point in the center of the bottom base
		Point inBottomBaseCenter = new Point(0, 0, 0);
		Vector centerBottomBaseNormal = cylinder.getNormal(inBottomBaseCenter);
		Vector expectedCenterBottomBaseNormal = new Vector(0, 0, 1);
		assertEquals(expectedCenterBottomBaseNormal, centerBottomBaseNormal,
				"Boundary Test: Center of the bottom base of the cylinder");

		// TC11: Point in the center of the top base
		Point inTopBaseCenter = new Point(0, 0, 5);
		Vector centerTopBaseNormal = cylinder.getNormal(inTopBaseCenter);
		Vector expectedCenterTopBaseNormal = new Vector(0, 0, 1);
		assertEquals(expectedCenterTopBaseNormal, centerTopBaseNormal,
				"Boundary Test: Center of the top base of the cylinder");
	}
}
