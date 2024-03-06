package geometries;

import java.util.List;
import primitives.*;

/**
 * The interface that deals with calculating all of the intersections
 */
public abstract class Intersectable {
	protected Point minPoint = new Point(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
	protected Point maxPoint = new Point(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);

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
	 * Checks if the ray intersects with the boundary region
	 * 
	 * @param ray - a given ray
	 * @return true if the ray intersects the boundary
	 */
	protected boolean boundaryRegionIntersection(Ray ray) {
		if (this.minPoint == null && this.maxPoint == null) return true; // if Plane
		
		double tMin = 0;
		double tMax = Double.POSITIVE_INFINITY;

		List<Double> min = (this.minPoint.subtract(ray.getHead())).reduce(ray.getDirection());
		List<Double> max = (this.maxPoint.subtract(ray.getHead())).reduce(ray.getDirection());

		for (int i = 0; i < 3; i++) {
			double t1 = min.get(i);
			double t2 = max.get(i);
			
			if (t1 > t2) {
				double tmp = t1;
				t1 = t2;
				t2 = tmp;
			}
			
			tMin = tMin > t1 ? tMin : t1;
			tMax = tMax < t2 ? tMax : t2;
			
			if (tMax < tMin)
				return false;
		}
		return tMax >= 0;
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