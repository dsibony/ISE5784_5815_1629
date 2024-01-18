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
		if (intersections != null) {
			if (vertices.get(0) != intersections.get(0)) {
				Vector u = vertices.get(1).subtract(vertices.get(0));
				Vector v = vertices.get(2).subtract(vertices.get(0));
				Vector n = u.crossProduct(v);
				Vector w = intersections.get(0).subtract(vertices.get(0));
				if (!(u.equals(w) || w.equals(v))) {
					double gamma = (u.crossProduct(w)).dotProduct(n) / n.lengthSquared();
					double beta = (w.crossProduct(v)).dotProduct(n) / n.lengthSquared();
					double alpha = 1 - gamma - beta;
					if (alpha > 0 && beta > 0 && gamma > 0)
						return intersections;
				}
			}
		}
		return null;
	}
}
