/**
 * 
 */
package renderer;

import java.util.List;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import scene.Scene;
import static primitives.Util.*;

/**
 * A class to implement super sampling in the ray tracer
 */
public class SuperSampleRayTracer extends SimpleRayTracer {
	private Blackboard board;
	private final int sqrtShadowRaysNum;

	/**
	 * Constructor to initialize the ray tracer
	 * 
	 * @param scene - the scene object used to initialize the scene field
	 * @param shadowRaysNum - the amount of shadow rays (for soft shadowing)
	 */
	public SuperSampleRayTracer(Scene scene, int shadowRaysNum) {
		super(scene);
		this.sqrtShadowRaysNum = (int) Math.sqrt(shadowRaysNum);
	}

	@Override
	protected Double3 calcKtr(GeoPoint gp, LightSource light, Vector l, Vector n) {
		Double3 avgKtr = Double3.ZERO;

		if (isZero(light.getRadius())) {
			return transparency(gp, light, new Ray(gp.point, l.scale(-1)));
		}

		board = new Blackboard(light, l, this.sqrtShadowRaysNum);
		List<Point> shadowPoints = board.createGrid();

		for (Point shadowPoint : shadowPoints) {
			avgKtr = avgKtr.add(transparency(gp, light, new Ray(gp.point, shadowPoint.subtract(gp.point), n)));
		}

		return avgKtr.scale(1d / shadowPoints.size());
	}
}