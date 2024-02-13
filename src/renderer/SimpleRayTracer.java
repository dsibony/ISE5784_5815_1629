/**
 * 
 */
package renderer;

import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import static primitives.Util.alignZero;

import java.util.List;

/**
 * This class is used for tracing rays
 */
public class SimpleRayTracer extends RayTracerBase {
	/** shading coefficient */
	private static final double DELTA = 0.1;

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
		return intersections == null ? this.scene.background
				: this.calcColor(ray.findClosestGeoPoint(intersections), ray);

	}

	/**
	 * Calculates the color of a point based on the Phong model
	 * 
	 * @param point - the point
	 * @param v     - the direction
	 * @return the color of the point
	 */
	private Color calcColor(GeoPoint geoPoint, Ray ray) {
		return this.scene.ambientLight.getIntensity().add(calcLocalEffects(geoPoint, ray));
	}

	/**
	 * TODO
	 * 
	 * @param gp
	 * @param ray
	 * @return
	 */
	private Color calcLocalEffects(GeoPoint gp, Ray ray) {
		Vector n = gp.geometry.getNormal(gp.point);
		Vector v = ray.getDirection();
		double nv = alignZero(n.dotProduct(v));
		if (nv == 0)
			return Color.BLACK;
		Material mat = gp.geometry.getMaterial();
		Color color = gp.geometry.getEmission();
		for (LightSource lightSource : scene.lights) {
			Vector l = lightSource.getL(gp.point);
			double nl = alignZero(n.dotProduct(l));
			if ((nl * nv > 0) && unshaded(gp, lightSource, l, n, nl)) { // sign(nl) == sign(nv)
				Color iL = lightSource.getIntensity(gp.point);
				color = color.add(iL.scale(calcDiffusive(mat, n, l, nl)))//
						.add(iL.scale(calcSpecular(mat, n, l, v, nv)));
			}
		}
		return color;
	}

	/**
	 * TODO
	 * 
	 * @param mat
	 * @param n
	 * @param l
	 * @param nl
	 * @return
	 */
	private Double3 calcDiffusive(Material mat, Vector n, Vector l, double nl) {
		double dp = alignZero(n.dotProduct(l));
		return dp < 0 ? mat.kD.scale(dp * (-1)) : mat.kD.scale(dp);
	}

	/**
	 * TODO
	 * 
	 * @param mat
	 * @param n
	 * @param l
	 * @param v
	 * @param nv
	 * @return
	 */
	private Double3 calcSpecular(Material mat, Vector n, Vector l, Vector v, double nv) {
		double dp = v.scale(-1).dotProduct(calcR(l, n));
		return alignZero(dp) < 0 ? Double3.ZERO : mat.kS.scale(Math.pow(dp, mat.nShininess));
	}

	/**
	 * TODO
	 * 
	 * @param l
	 * @param n
	 * @return
	 */
	private Vector calcR(Vector l, Vector n) {
		return l.subtract(n.scale(l.scale(2).dotProduct(n))).normalize();
	}

	/**
	 * TODO
	 * 
	 * @param gp
	 * @param l
	 * @param n
	 * @return
	 */
	private boolean unshaded(GeoPoint gp, LightSource light, Vector l, Vector n, double nl) {
		Vector lightDirection = l.scale(-1);

		Vector delta = n.scale(nl < 0 ? DELTA : -DELTA);
		Point point = gp.point.add(delta);
		Ray shadowRay = new Ray(point, lightDirection);

		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(shadowRay);
		if (intersections == null)
			return true;
		double distance = light.getDistance(point);
		for (GeoPoint intersection : intersections)
			if (point.distance(intersection.point) < distance)
				return false;
		return true;
	}
}
