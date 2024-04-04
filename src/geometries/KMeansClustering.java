/**
 * 
 */
package geometries;

import java.util.*;
import primitives.Point;

/**
 * KMeansClustering class, used to cluster geometries optimally
 */
public class KMeansClustering {
	private int k; // Number of clusters
	private List<Intersectable> geometries; // List of geometries to be clustered
	private List<Point> centroids; // List of centroids for clusters
	private List<List<Intersectable>> clusters; // List of clusters

	/**
	 * Constructor to initialize KMeansClustering object based with all its values
	 * 
	 * @param k - the number of clusters
	 * @param geometries - geometries to cluster
	 */
	public KMeansClustering(int k, List<Intersectable> geometries) {
		this.k = k;
		for (Intersectable geom : geometries) {
			if (geom.getMinPoint() == null && geom.getMaxPoint() == null) {
				geometries.remove(geom);
			}
		}
		this.geometries = geometries;
		this.centroids = initializeCentroids();
		this.clusters = new LinkedList<>();
		for (int i = 0; i < k; i++) {
			clusters.add(new LinkedList<>());
		}
	}

	/**
	 * Performs the clustering
	 */
	public void cluster() {
		boolean converged = false;
		while (!converged) {
			assignToClusters();
			List<Point> oldCentroids = new LinkedList<>(centroids);
			calculateNewCentroids();
			if (oldCentroids.equals(centroids)) {
				converged = true;
			}
		}
	}

	/**
	 * Initializes centroids randomly from the positions of geometries
	 * 
	 * @return the random centroid values
	 */
	private List<Point> initializeCentroids() {
		Random random = new Random();
		List<Point> centroids = new LinkedList<>();
		for (int i = 0; i < k; i++) {
			centroids.add(geometries.get(random.nextInt(geometries.size())).getPosition());
		}
		return centroids;
	}

	/**
	 * Assigns each geometry to the nearest centroid
	 */
	private void assignToClusters() {
		clusters.forEach(List::clear);
		for (Intersectable geometry : geometries) {
			int nearestCentroidIndex = findNearestCentroid(geometry.getPosition());
			clusters.get(nearestCentroidIndex).add(geometry);
		}
	}

	/**
	 * Finds the nearest centroid for a given point
	 * 
	 * @param point - the point we want to relate to the closest cluster
	 * @return the nearest centroid
	 */
	private int findNearestCentroid(Point point) {
		int nearestCentroidIndex = 0;
		double minDistance = Double.MAX_VALUE;
		for (int i = 0; i < centroids.size(); i++) {
			double distance = point.distanceSquared(centroids.get(i));
			if (distance < minDistance) {
				minDistance = distance;
				nearestCentroidIndex = i;
			}
		}
		return nearestCentroidIndex;
	}

	/**
	 * Calculates new centroids for each cluster
	 */
	private void calculateNewCentroids() {
		for (int i = 0; i < k; i++) {
			if (!clusters.get(i).isEmpty()) {
				Point newCentroid = calculateCentroid(clusters.get(i));
				centroids.set(i, newCentroid);
			}
		}
	}

	/**
	 * Calculates the centroid of a cluster
	 * 
	 * @param list - the list of geometries
	 * @return the centroid
	 */
	private Point calculateCentroid(List<Intersectable> list) {
		Point sum = Point.ZERO;
		for (Intersectable geometry : list) {
			sum.add(geometry.getPosition());
		}
		int size = list.size();
		return sum.scale(1 / size);
	}

	/**
	 * Getter for cluster in index i
	 * 
	 * @param i - the index
	 * @return the matching cluster
	 */
	public List<Intersectable> getCluster(int i) {
		return clusters.get(i);
	}
}