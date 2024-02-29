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
 * 
 */
public class SuperSampleRayTracer extends SimpleRayTracer {
	private Blackboard board;
	final private int shadowRaysNum;

	public SuperSampleRayTracer(Scene scene, int shadowRaysNum) {
		super(scene);
		this.shadowRaysNum = shadowRaysNum;
	}

	@Override
	protected Double3 calcKtr(GeoPoint gp, LightSource light, Vector l, Vector n) {
		Double3 avgKtr = Double3.ZERO;

		if (isZero(light.getRadius())) {
			return transparency(gp, light, new Ray(gp.point, l.scale(-1)));
		}

		board = new Blackboard(light, l);
		List<Point> shadowPoints = board.getShadowPoints(light, l, shadowRaysNum);

		for (Point shadowPoint : shadowPoints) {
			avgKtr = avgKtr.add(transparency(gp, light, new Ray(gp.point, shadowPoint.subtract(gp.point), n)));
		}

		return avgKtr.scale(1d / shadowPoints.size());
	}
}