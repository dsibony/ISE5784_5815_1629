/**
 * 
 */
package renderer;

import primitives.*;
import static primitives.Util.isZero;

/**
 * 
 */
public class Camera implements Cloneable {
	private Point location;
	private Vector vRight;
	private Vector vUp;
	private Vector vTo;
	private double height = 0d;
	private double width = 0d;
	private double distance = 0d;

	/**
	 * 
	 */
	public static class Builder {
		private final Camera camera = new Camera();

		/**
		 * Default constructor
		 */
		public Builder() {

		}

		/**
		 * Constructor to initialize Camera object based with all its values
		 * 
		 * @param camera - Used for the Camera values
		 */
		public Builder(Camera camera) {
			this.camera.location = camera.getLocation();
			this.camera.vRight = camera.getvRight();
			this.camera.vUp = camera.getvUp();
			this.camera.vTo = camera.getvTo();
			this.camera.height = camera.getHeight();
			this.camera.width = camera.getWidth();
			this.camera.distance = camera.getDistance();
		}

		/**
		 * Sets the location value
		 * 
		 * @param location - the new location of the camera
		 */
		public Builder setLocation(Point location) {
			this.camera.location = location;
			return this;
		}

		/**
		 * Sets the direction Vector values
		 * 
		 * @param vUp - the new up vector
		 * @param vTo - the new to vector
		 */
		public Builder setDirection(Vector vUp, Vector vTo) {
			if (!isZero(vUp.dotProduct(vTo)))
				throw new IllegalArgumentException("Vectors are not orthogonal to each other");
			this.camera.vUp = vUp.normalize();
			this.camera.vTo = vTo.normalize();
			return this;
		}

		/**
		 * Sets the view plane
		 * 
		 * @param width  - the new width value
		 * @param height - the new height value
		 */
		public Builder setVpSize(double width, double height) {
			if (width <= 0 || height <= 0)
				throw new IllegalArgumentException("illegal view plane values");
			this.camera.width = width;
			this.camera.height = height;
			return this;
		}

		/**
		 * Sets the distance of the view plane
		 * 
		 * @param distance - the new distance value
		 */
		public Builder setVpDistance(double distance) {
			if (distance <= 0)
				throw new IllegalArgumentException("illegal distance value");
			this.camera.distance = distance;
			return this;
		}

	}

	/**
	 * Empty default constructor
	 */
	private Camera() {
	}

	/**
	 * @return the location
	 */
	public Point getLocation() {
		return location;
	}

	/**
	 * @return the vRight
	 */
	public Vector getvRight() {
		return vRight;
	}

	/**
	 * @return the vUp
	 */
	public Vector getvUp() {
		return vUp;
	}

	/**
	 * @return the vTo
	 */
	public Vector getvTo() {
		return vTo;
	}

	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * @return the width
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * @return the distance
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * 
	 * @return
	 */
	public Builder getBuilder() {
		return new Builder();
	}

	/**
	 * 
	 * @param nX
	 * @param nY
	 * @param j
	 * @param i
	 * @return
	 */
	public Ray constructRay(int nX, int nY, int j, int i) {
		return null;
	}
}
