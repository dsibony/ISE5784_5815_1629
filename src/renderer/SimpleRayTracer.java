/**
 * 
 */
package renderer;

import primitives.Color;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;
import geometries.Intersectable.GeoPoint;


/**
 * This class is used for tracing rays
 */
public class SimpleRayTracer extends RayTracerBase {

	/**
	 * Constructor for SimpleRayTracer based object
	 * 
	 * @param scene - the scene object used to initialize the scene field
	 */
	public SimpleRayTracer(Scene scene) {
		super(scene);
	}

	@Override
	public Color traceRay(Ray ray) {
		var intersections = this.scene.geometries.findGeoIntersections(ray);
		if (intersections == null)
			return this.scene.background;
		return this.calcColor(ray.findClosestGeoPoint(intersections), ray.getDirection());
		
	}
	
	/**
	 * Calculates the color of a point based on the current scene
	 * 
	 * @param point - the point 
	 * @return the color of the point
	 */
	private Color calcColor(GeoPoint geoPoint, Vector direction) {
		return this.scene.ambientLight.getIntensity().add(geoPoint.geometry.getEmission());
	}
	
}
