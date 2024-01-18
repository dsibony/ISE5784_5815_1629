package geometries;

import java.util.LinkedList;
import java.util.List;
import primitives.Point;
import primitives.Ray;

/**
 * Geometries class which is used for handling multiple geometries in the same class with the same methods
 */
public class Geometries implements Intersectable {
	private final List<Intersectable> geometries = new LinkedList<Intersectable>();

	/**
	 * Empty default constructor
	 */
	public Geometries() {}

	/** Constructor which adds the given geometries into the geometries list above
	 * 
	 * @param geometries, given geometries for the list
	 */
	public Geometries(Intersectable... geometries) {
		add(geometries);
	}

	public void add(Intersectable... geometries) {
		for (Intersectable geom : geometries)
			this.geometries.add(geom);
	}

	@Override
	public List<Point> findIntersections(Ray ray) {
		List<Point> intersections = null;
		for (Intersectable geom : geometries) {
			var geoIntersections = geom.findIntersections(ray);
			if (geoIntersections != null) {
				if (intersections == null)
					intersections = new LinkedList<Point>(geoIntersections);
				else
					for (Point p : geoIntersections) {
						if (!intersections.contains(p))
							intersections.add(p);
					}
			}
		}
		return intersections;
	}

}
