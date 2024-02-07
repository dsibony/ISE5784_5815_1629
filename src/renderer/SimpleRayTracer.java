/**
 * 
 */
package renderer;

import primitives.Color;
import primitives.Material;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;
import geometries.Intersectable.GeoPoint;
import lighting.LightSource;

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
	 * @param direction - the direction
	 * @return the color of the point
	 */
	private Color calcColor(GeoPoint geoPoint, Vector direction) {
		Color color = this.scene.ambientLight.getIntensity().add(geoPoint.geometry.getEmission());
		for (LightSource light : this.scene.lights) {
			Vector l = light.getL(geoPoint.point);
			Vector n = geoPoint.geometry.getNormal(geoPoint.point);
			Vector r = l.subtract(n.scale(l.scale(2).dotProduct(n))).normalize();
			Color i = light.getIntensity(geoPoint.point);
			Material m = geoPoint.geometry.getMaterial();
			
			color = color.add(i.scale(Math.abs(n.dotProduct(l))).scale(m.kD));
			color = color.add(i.scale(Math.pow(Math.max(0, direction.scale(-1).dotProduct(r)), m.nShininess)).scale(m.kS));
		}
		return color;
	}

}
