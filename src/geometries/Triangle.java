/**
 * 
 */
package geometries;

import java.util.List;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

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
		Plane plane = new Plane(vertices.get(0), vertices.get(1), vertices.get(2));
		List<Point> intersections = plane.findIntersections(ray);
		if (((this.vertices.get(1).subtract(this.vertices.get(0))
				.dotProduct(intersections.get(0).subtract(this.vertices.get(0)))) > 0) //
				&& ((this.vertices.get(2).subtract(this.vertices.get(1)) //
						.dotProduct(intersections.get(0).subtract(this.vertices.get(1)))) > 0) //
				&& ((this.vertices.get(0).subtract(this.vertices.get(2)) //
						.dotProduct(intersections.get(0).subtract(this.vertices.get(2)))) > 0)) //
			return intersections;
		return null;
	}
}
