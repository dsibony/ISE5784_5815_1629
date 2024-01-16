/**
 * 
 */
package geometries;

import java.util.List;

import primitives.Point;
import primitives.Ray;

/**
 * This class will be used as a Triangle, a geometric object
 */
public class Triangle extends Polygon {

	/**
	 * Constructor to initialize Triangle based object with its three Point values
	 * 
	 * @param p1 - first point value
	 * @param p2 - second point value
	 * @param p3 - third point value
	 */
	public Triangle(Point p1, Point p2, Point p3) {
		super(p1, p2, p3);
	}

	@Override
	public List<Point> findIntersections(Ray ray) {
		return null;
	}
}
