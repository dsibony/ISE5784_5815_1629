/**
 * 
 */
package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * A class representing a point light source
 */
public class PointLight extends Light implements LightSource {
	/** The position of the light */
	protected final Point position;

	private double kC = 1;
	private double kL = 0;
	private double kQ = 0;
	
	private double radius = 0;

	/**
	 * Constructor to initialize PointLight based object
	 * 
	 * @param intensity - the intensity of the PointLight
	 * @param position  - the position of the PointLight
	 */
	public PointLight(Color intensity, Point position) {
		super(intensity);
		this.position = position;
	}

	/**
	 * setter for kC
	 * 
	 * @param kC - the kC to set
	 * @return PointLight after its been updated
	 */
	public PointLight setKc(double kC) {
		this.kC = kC;
		return this;
	}

	/**
	 * setter for kL
	 * 
	 * @param kL - the kL to set
	 * @return PointLight after its been updated
	 */
	public PointLight setKl(double kL) {
		this.kL = kL;
		return this;
	}

	/**
	 * setter for kQ
	 * 
	 * @param kQ - the kQ to set
	 * @return PointLight after its been updated
	 */
	public PointLight setKq(double kQ) {
		this.kQ = kQ;
		return this;
	}

	@Override
	public Color getIntensity(Point p) {
		double d = p.distance(this.position);
		return this.intensity.scale(1 / (this.kC + this.kL * d + this.kQ * d * d));
	}

	@Override
	public Vector getL(Point p) {
		return p.subtract(this.position).normalize();
	}

	@Override
	public double getDistance(Point p) {
		return this.position.distance(p);
	}

	@Override
	public double getRadius() {
		return radius;
	}

	/**
	 * setter for radius
	 * 
	 * @param radius - the new radius value
	 * @return the pointLight after its radius has been updated
	 */
	public PointLight setRadius(int radius) {
		this.radius = radius;
		return this;
	}

	@Override
	public Point getPosition() {
		return position;
	}

}
