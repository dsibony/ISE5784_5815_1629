/**
 * 
 */
package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

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
		var intersections = this.scene.geometries.findIntersections(ray);
		if (intersections == null)
			return this.scene.background;
		return this.calcColor(ray.findClosestPoint(intersections));
		
	}
	
	/**
	 * Calculates the color of a point based on the current scene
	 * 
	 * @param point - the point 
	 * @return the color of the point
	 */
	public Color calcColor(Point point) {
		return this.scene.ambientLight.getIntensity();
	}
	
}
