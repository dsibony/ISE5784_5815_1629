package geometries;

import java.util.*;
import primitives.*;

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
	 * Constructor which adds the given list of geometries into the geometries list above
	 * 
	 * @param cluster - the list of the geometries
	 */
	public Geometries(List<Intersectable> cluster) {
		for (Intersectable geom : cluster) {
			add(geom);
		}
	}

	/**
	 * Add geometries to the list of "geometries"
	 * 
	 * @param geometries - a list of intersectable geometries
	 */
	public void add(Intersectable... geometries) {
		for (Intersectable geom : geometries) {
			this.geometries.add(geom);
			updateRegion(geom.getMinPoint(), geom.getMaxPoint());
		}
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

	/**
	 * A method to switch on & off the acceleration
	 * 
	 * @param flag - true or false, to determine if we want the acceleration to be
	 *             used or not
	 */
	public void boundaryRegionSwitchAll(boolean flag) {
		for (Intersectable geom : geometries) {
			geom.setBoundaryRegionSwitch(flag);
		}
	}

	/**
	 * Getter for geometries
	 * 
	 * @return the list of geometries
	 */
	public List<Intersectable> getGeometries() {
		return geometries;
	}
	
	/**
	 * the method to use the k means algorithm(for the acceleration)
	 * 
	 * @param k - the number of clusters
	 */
	public void KMeansClustering(int k) {
		KMeansClustering clustering = new KMeansClustering(k, this.geometries);
		clustering.cluster();
		geometries.clear();
		
		for (int i = 0; i < k; i++) {
			Geometries geo = new Geometries(clustering.getCluster(i));
			geometries.add(geo);
		}
	}
}