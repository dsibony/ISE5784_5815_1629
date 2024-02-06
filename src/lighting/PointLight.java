/**
 * 
 */
package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * 
 */
public class PointLight extends Light implements LightSource {
	/** */
	protected Point position;

	private double kC = 1;
	private double kL = 0;
	private double kQ = 0;
	
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
	public PointLight setkC(double kC) {
		this.kC = kC;
		return this;
	}

	/**
	 * setter for kL
	 * 
	 * @param kL - the kL to set
	 * @return PointLight after its been updated
	 */
	public PointLight setkL(double kL) {
		this.kL = kL;
		return this;
	}

	/**
	 * setter for kQ
	 * 
	 * @param kQ the kQ to set
	 * @return PointLight after its been updated
	 */
	public PointLight setkQ(double kQ) {
		this.kQ = kQ;
		return this;
	}

	@Override
	public Color getIntensity(Point p) {
		return this.intensity;
	}

	@Override
	public Vector getL(Point p) {
		return p.subtract(this.position);
	}

}
