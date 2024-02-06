package geometries;

import java.util.List;
import primitives.*;

/**
 * The interface that deals with calculating all of the intersections
 */
public abstract class Intersectable {
	/**
	 * A method that receives a ray and returns a list of intersection points
	 * between the ray and the geometry shape
	 * 
	 * @param ray - a given ray
	 * @return a list of all the intersection points
	 */
	public List<Point> findIntersections(Ray ray) {
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
	public List<GeoPoint> findGeoIntersections(Ray ray) {
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
	 * 
	 */
	public static class GeoPoint {
		/** */
		public Geometry geometry;

		/** */
		public Point point;

		/**
		 * 
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