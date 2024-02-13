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
	/** stopping condition (maximum) */
	private static final int MAX_CALC_COLOR_LEVEL = 10;

	/** stopping condition (minimum) */
	private static final double MIN_CALC_COLOR_K = 0.001;

	/** inital k */
	private static final Double3 INITIAL_K = Double3.ONE;

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
		GeoPoint closestPoint = findClosestIntersection(ray);
		return closestPoint == null ? scene.background : calcColor(closestPoint, ray);
	}

	/**
	 * Calculates the color of a point based on the Phong model
	 * 
	 * @param geoPoint - the geopoint
	 * @param ray      - the ray
	 * @return the color of the point
	 */
	private Color calcColor(GeoPoint geoPoint, Ray ray) {
		return calcColor(geoPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(scene.ambientLight.getIntensity());
	}

	private Color calcColor(GeoPoint geoPoint, Ray ray, int level, Double3 k) {
		Color color = calcLocalEffects(geoPoint, ray, k);
		return 1 == level ? color : color.add(calcGlobalEffects(geoPoint, ray, level, k));
	}

	private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
		Vector n = gp.geometry.getNormal(gp.point);
		Material material = gp.geometry.getMaterial();
		return (calcGlobalEffect(constructRefractedRay(gp, ray, n), material.kT, level, k))
				.add(calcGlobalEffect(constructReflectedRay(gp, ray, n), material.kR, level, k));
	}

	private Color calcGlobalEffect(Ray ray, Double3 kx, int level, Double3 k) {
		Double3 kkx = kx.product(k);
		if (kkx.lowerThan(MIN_CALC_COLOR_K))
			return Color.BLACK;
		GeoPoint gp = findClosestIntersection(ray);
		return gp == null ? scene.background : (calcColor(gp, ray, level - 1, kkx)).scale(kx);
	}

	private Ray constructRefractedRay(GeoPoint gp, Ray ray, Vector n) {
		return new Ray(gp.point, ray.getDirection(), n);
	}

	private Ray constructReflectedRay(GeoPoint gp, Ray ray, Vector n) {
		Vector v = ray.getDirection();
		return new Ray(gp.point, v.subtract(n.scale(2 * v.dotProduct(n))).normalize(), n);
	}

	private GeoPoint findClosestIntersection(Ray ray) {
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
		return ray.findClosestGeoPoint(intersections);
	}

	/**
	 * this method calculates the local effects
	 * 
	 * @param gp  - the geopoint
	 * @param ray - the ray
	 * @return the local effects
	 */
	private Color calcLocalEffects(GeoPoint gp, Ray ray, Double3 k) {
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
			if ((nl * nv > 0)) { // sign(nl) == sign(nv)
				Double3 ktr = transparency(gp, lightSource, l, n);
				if (!ktr.product(k).lowerThan(MIN_CALC_COLOR_K)) {
					Color iL = lightSource.getIntensity(new Point(gp.point.getCoordinates().product(ktr)));
					color = color.add(iL.scale(calcDiffusive(mat, n, l, nl)))//
							.add(iL.scale(calcSpecular(mat, n, l, v, nv)));
				}
			}
		}
		return color;
	}

	/**
	 * this method calculates the diffusive effect
	 * 
	 * @param mat - the material
	 * @param n   - the normal to the object
	 * @param l   - the vector from the light pointing to the object
	 * @param nl  - n dot product l
	 * @return the diffusive effects
	 */
	private Double3 calcDiffusive(Material mat, Vector n, Vector l, double nl) {
		double dp = alignZero(n.dotProduct(l));
		return dp < 0 ? mat.kD.scale(dp * (-1)) : mat.kD.scale(dp);
	}

	/**
	 * this method calculates the specular effects
	 * 
	 * @param mat - the material
	 * @param n   - the normal to the object
	 * @param l   - the vector from the light pointing to the object
	 * @param v   - the direction of the ray
	 * @param nv  - n dot product v
	 * @return the specular effects
	 */
	private Double3 calcSpecular(Material mat, Vector n, Vector l, Vector v, double nv) {
		double dp = v.scale(-1).dotProduct(calcR(l, n));
		return alignZero(dp) < 0 ? Double3.ZERO : mat.kS.scale(Math.pow(dp, mat.nShininess));
	}

	/**
	 * this method calculates the reflected vector
	 * 
	 * @param l - the vector from the light pointing to the object
	 * @param n - the normal to the object
	 * @return the reflection vector
	 */
	private Vector calcR(Vector l, Vector n) {
		return l.subtract(n.scale(l.scale(2).dotProduct(n))).normalize();
	}

	/**
	 * 
	 * @param gp    - the geo point
	 * @param light - the light source
	 * @param l     - the vector from the light pointing to the object
	 * @param n     - the normal to the object
	 * @return
	 */
	private Double3 transparency(GeoPoint gp, LightSource light, Vector l, Vector n) {
		Vector lightDirection = l.scale(-1);

		Point point = gp.point;
		Ray shadowRay = new Ray(point, lightDirection, n);
		Double3 ktr = Double3.ONE;

		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(shadowRay);
		if (intersections == null)
			return ktr;

		double distance = light.getDistance(point);
		for (GeoPoint intersection : intersections)
			if (point.distance(intersection.point) < distance)
				ktr = ktr.product(intersection.geometry.getMaterial().kT);
		return ktr;
	}
}
