package geometries;

import java.util.LinkedList;
import java.util.List;
import primitives.Ray;

/**
 * Geometries class which is used for handling multiple geometries in the same
 * class with the same methods
 */
public class Geometries extends Intersectable {
	private final List<Intersectable> geometries = new LinkedList<Intersectable>();

	/**
	 * Empty default constructor
	 */
	public Geometries() {
	}

	/**
	 * Constructor which adds the given geometries into the geometries list above
	 * 
	 * @param geometries, given geometries for the list
	 */
	public Geometries(Intersectable... geometries) {
		add(geometries);
	}

	/**
	 * Add geometries to the list of "geometries"
	 * 
	 * @param geometries - a list of intersectable geometries
	 */
	public void add(Intersectable... geometries) {
		for (Intersectable geom : geometries)
			this.geometries.add(geom);
	}

	@Override
	protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
		List<GeoPoint> intersections = null;
		for (Intersectable geom : geometries) {
			var geoIntersections = geom.findGeoIntersections(ray);
			if (geoIntersections != null) {
				if (intersections == null)
					intersections = new LinkedList<GeoPoint>(geoIntersections);
				else
					intersections.addAll(geoIntersections);
			}
		}
		return intersections;
	}

}
