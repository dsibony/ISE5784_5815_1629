/**
 * 
 */
package geometries;

import java.util.List;

import primitives.Double3;
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
		double p1[] = vertices.get(0).getCoordinates();
		double p2[] = vertices.get(1).getCoordinates();
		double p3[] = vertices.get(2).getCoordinates();
		Plane plane = new Plane(vertices.get(0), vertices.get(1), vertices.get(2));
		List<Point> intersections = plane.findIntersections(ray);
		double point[] = intersections.get(0).getCoordinates();
		double detT = (p2[1] - p1[1]) * (p3[0] - p1[0]) - (p2[0] - p1[0]) * (p3[1] - p1[1]);
		double alpha = ((p2[1] - p3[1]) * (point[0] - p3[0]) + (p3[0] - p2[0]) * (point[1] - p3[1])) / detT;
		double beta = ((p3[1] - p1[1]) * (point[0] - p3[0]) + (p1[0] - p3[0]) * (point[1] - p3[1])) / detT;
		double gamma = 1 - alpha - beta;

		// Check if the point is inside the 3D triangle
		if( alpha >= 0 && beta >= 0 && gamma >= 0)
			return intersections;
		return null;
	}
}
