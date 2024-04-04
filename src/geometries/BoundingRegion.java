/**
 * 
 */
package geometries;

import primitives.*;

/**
 * A class to calculate the regions around the geometries (SoC & ISP)
 */
public abstract class BoundingRegion {
	private Point minPoint = new Point(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
	private Point maxPoint = new Point(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
	protected boolean boundaryRegionSwitch;

	/**
	 * @return the minPoint
	 */
	public Point getMinPoint() {
		return minPoint;
	}

	/**
	 * @return the maxPoint
	 */
	public Point getMaxPoint() {
		return maxPoint;
	}

	/**
	 * Set the switch by the flag
	 * 
	 * @param flag - true or false, for turning on & off the acceleration
	 */
	public void setBoundaryRegionSwitch(boolean flag) {
		this.boundaryRegionSwitch = flag;
	}

	/**
	 * Update the region of the geometry
	 * 
	 * @param minPoint - the minimum x, y, z values
	 * @param maxPoint - the maximum x, y, z values
	 */
	protected void updateRegion(Point minPoint, Point maxPoint) {
		this.minPoint = this.minPoint.calcMinimumPoint(minPoint);
		this.maxPoint = this.maxPoint.calcMaximumPoint(maxPoint);
	}

	/**
	 * Create the region of the geometry
	 * 
	 * @param minPoint - the minimum x, y, z values
	 * @param maxPoint - the maximum x, y, z values
	 */
	protected void createRegion(Point minPoint, Point maxPoint) {
		this.minPoint = minPoint;
		this.maxPoint = maxPoint;
	}

	/**
	 * Checks if the ray intersects with the boundary region
	 * 
	 * @param ray - a given ray
	 * @return true if the ray intersects the boundary
	 */
	protected boolean boundaryRegionIntersection(Ray ray) {
		for (int i = 0; i < 3; i++) {
			if (!this.boundaryRegionSwitch)
				return true;

			if (this.minPoint == null && this.maxPoint == null)
				return true; // if Plane

			double tmin = this.minPoint.calcCoordinatesValues(ray, i); // Nearest plane intersection on x-axis
			double tmax = this.maxPoint.calcCoordinatesValues(ray, i); // Farthest plane intersection on x-axis

			// Swap tmin and tmax if necessary (ensure tmin < tmax)
			if (tmin > tmax) {
				double temp = tmin;
				tmin = tmax;
				tmax = temp;
			}

			// Check if the intersection falls outside the ray's parameter range
			if (tmax < 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Calculates the center of the bounding region
	 * 
	 * @return the center values
	 */
	public Point getPosition() {
		if (this.minPoint == null && this.maxPoint == null) // if plane
			return null;

		return this.maxPoint.add(this.minPoint).scale(1 / 2);
	}
}