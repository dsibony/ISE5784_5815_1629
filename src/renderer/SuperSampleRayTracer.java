/**
 * 
 */
package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.Double3;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

/**
 * 
 */
public class SuperSampleRayTracer extends SimpleRayTracer {
	final int shadowRaysNum;
	
	public SuperSampleRayTracer(Scene scene, int shadowRaysNum) {
		super(scene);
		this.shadowRaysNum = shadowRaysNum; 
	}
	
	@Override
	protected Double3 calcKtr(GeoPoint gp, LightSource light, Vector l, Vector n) {
		Vector lightDirection = l.scale(-1);
		Double3 avgKtr = Double3.ZERO;
		
		for ()
		avgKtr = avgKtr.add(transparency(gp, light, new Ray));
		return avgKtr/
	}
}
