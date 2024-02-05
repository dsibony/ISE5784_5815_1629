/**
 * 
 */
package unittests.primitives;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Unit tests for primitives.Ray class
 */
class RayTests {

	/**
	 * Test method for {@link primitives.Ray#getPoint(double)}.
	 */
	@Test
	void testGetPoint() {
		Ray ray = new Ray(new Point(1, 0, 0), new Vector(0, 0, 1));
		// ============ Equivalence Partitions Tests ==============
		// TC01: Negative distance
		Point p01 = new Point(1, 0, -1);
		assertEquals(p01, ray.getPoint(-1), "Negative distance");

		// TC02: Positive distance
		Point p02 = new Point(1, 0, 1);
		assertEquals(p02, ray.getPoint(1), "Positive distance");

		// =============== Boundary Values Tests ==================
		// TC11: Zero distance
		Point p11 = new Point(1, 0, 0);
		assertEquals(p11, ray.getPoint(0), "Zero distance");
	}

	/**
	 * Test method for {@link primitives.Ray#findClosestPoint(List<Point>)}.
	 */
	@Test
	void testFindClosestPoint() {
		Ray ray = new Ray(new Point(0, 0, 1), new Vector(0, 1, 0));
		Point p1 = new Point(0, 0, 2);
		Point p2 = new Point(0, 0, 1.5);
		Point p3 = new Point(2, 0, 1.5);
		List<Point> list = new LinkedList<Point>();
		

		// ============ Equivalence Partitions Tests ==============
		// TC01: A point in the middle of the list is the closest one to the ray's head
		list.add(p1);
		list.add(p2);
		list.add(p3);
		Point expectedPoint = p2;
		assertEquals(expectedPoint, ray.findClosestPoint(list), "The point is in the middle of the list");
		
		// =============== Boundary Values Tests ==================
		// TC10: The list is empty
		list.clear();
		assertNull(ray.findClosestPoint(list), "The list is empty");
		
		// TC11: The first point is the closest one to the ray's head
		list.add(p2);
		list.add(p1);
		list.add(p3);
		assertEquals(expectedPoint, ray.findClosestPoint(list), "The point is in the first index of the list");
		
		// TC12: The last point is the closest one to the ray's head
		list.clear();
		list.add(p3);
		list.add(p1);
		list.add(p2);
		assertEquals(expectedPoint, ray.findClosestPoint(list), "The point is in the last index of the list");

	}
}
