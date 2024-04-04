package geometries;

import java.util.List;
import primitives.*;

/**
 * The interface that deals with calculating all of the intersections
 */
public abstract class Intersectable extends BoundingRegion {
	/**
	 * A method that receives a ray and returns a list of intersection points
	 * between the ray and the geometry shape
	 * 
	 * @param ray - a given ray
	 * @return a list of all the intersection points
	 */
	public final List<Point> findIntersections(Ray ray) {
		var geoList = findGeoIntersections(ray);
		return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
	}



	/**
	 * A method that receives a ray and returns a list of intersection GeoPoints
	 * between the ray and the geometry shape
	 * 
	 * @param ray - a given ray
	 * @return a list of all the intersection GeoPoints
	 */
	public final List<GeoPoint> findGeoIntersections(Ray ray) {
		if (!boundaryRegionIntersection(ray))
			return null;
		return findGeoIntersectionsHelper(ray);
	}

	/**
	 * A method to help the findGeoIntersections method
	 * 
	 * @param ray - a given ray
	 * @return a list of all the intersection GeoPoints
	 */
	protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);

	/**
	 * A class representing a GeoPoint, which includes the geometry shape and the
	 * intersection point
	 */
	public static class GeoPoint {
		/** The geometry shape */
		public Geometry geometry;

		/** The point on the geometry */
		public Point point;

		/**
		 * Constructor to initialize GeoPoint based object
		 * 
		 * @param geometry - the geometry shape
		 * @param point    - the point on the geometry
		 */
		public GeoPoint(Geometry geometry, Point point) {
			this.geometry = geometry;
			this.point = point;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			return (obj instanceof GeoPoint other) && geometry.equals(other.geometry) && point.equals(other.point);
		}

		@Override
		public String toString() {
			return "Geometry:" + geometry + ", Point:" + point;
		}
	}

}