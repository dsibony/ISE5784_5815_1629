package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * Abstract class that is the base for tracing rays
 */
public abstract class RayTracerBase {
	/** the main scene */
	protected Scene scene;
	
	/**
	 * Constructor for RayTracerBase based object
	 * 
	 * @param scene - the scene object used to initialize the scene field
	 */
	public RayTracerBase(Scene scene) {
		this.scene = scene;
	}
	
	/**
	 * traceRay method, used for tracing the ray and returning the color of the object hit by the ray
	 * 
	 * @param ray - the ray being traced
	 * @return the color based on the ray
	 */
	public abstract Color traceRay(Ray ray);
}
