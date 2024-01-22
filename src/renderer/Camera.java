/**
 * 
 */
package renderer;

import primitives.*;
import static primitives.Util.*;

import java.util.MissingResourceException;

/**
 * 
 */
public class Camera implements Cloneable {
	private Point p0;
	private Vector vRight;
	private Vector vUp;
	private Vector vTo;
	private double viewPlaneHeight = 0d;
	private double viewPlaneWidth = 0d;
	private double viewPlaneDistance = 0d;

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
			this.camera.p0 = camera.getP0();
			this.camera.vRight = camera.getvRight();
			this.camera.vUp = camera.getvUp();
			this.camera.vTo = camera.getvTo();
			this.camera.viewPlaneHeight = camera.getHeight();
			this.camera.viewPlaneWidth = camera.getWidth();
			this.camera.viewPlaneDistance = camera.getDistance();
		}

		/**
		 * Sets the p0 value
		 * 
		 * @param p0 - the new p0 of the camera
		 */
		public Builder setP0(Point p0) {
			this.camera.p0 = p0;
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
		 * @param width  - the new view plane width value
		 * @param height - the new view plane height value
		 */
		public Builder setVpSize(double width, double height) {
			if (width <= 0 || height <= 0)
				throw new IllegalArgumentException("illegal view plane values");
			this.camera.viewPlaneWidth = width;
			this.camera.viewPlaneHeight = height;
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
			this.camera.viewPlaneDistance = distance;
			return this;
		}
		
		/**
		 * TODO
		 * @return
		 */
		public Camera build() {
			String problemDesc = "Missing rendering data";
			String problemLoc = "Camera";
//			if (camera.imageWriter == null) throw new MissingResourceException(…);
//			if (camera.rayTracer == null) throw new ... 
			if (camera.p0 == null ) throw new MissingResourceException(problemDesc, problemLoc, "p0 is missing");
			if (camera.vUp == null) throw new MissingResourceException(problemDesc, problemLoc, "vUp is missing"); 
			if (camera.vTo == null) throw new MissingResourceException(problemDesc, problemLoc, "vTo is missing");
			if (alignZero(camera.viewPlaneWidth) == 0) throw new MissingResourceException(problemDesc, problemLoc, "view plane width is missing");
			if (alignZero(camera.viewPlaneWidth) < 0) throw new IllegalArgumentException("view plane width has an illegal value");
			if (alignZero(camera.viewPlaneHeight) == 0) throw new MissingResourceException(problemDesc, problemLoc, "view plane height is missing");
			if (alignZero(camera.viewPlaneHeight) < 0) throw new IllegalArgumentException("view plane height has an illegal value");
			if (alignZero(camera.viewPlaneDistance) == 0) throw new MissingResourceException(problemDesc, problemLoc, "view plane height is missing");
			if (alignZero(camera.viewPlaneDistance) < 0) throw new IllegalArgumentException("view plane distance has an illegal value");
			if (!isZero(camera.vUp.dotProduct(camera.vTo))) throw new IllegalArgumentException("view plane vectors aren't orthogonal to each other");
			camera.vTo.normalize();
			camera.vUp.normalize();
			camera.vRight = camera.vTo.crossProduct(camera.vUp).normalize();
//			camera.viewPlanePC = camera.p0.add(camera.vTo.scale(camera.viewPlaneDistance)); 
			try {
				return (Camera) camera.clone();
			} catch (CloneNotSupportedException ignore) {
				return null;
			}
		}
	}

	/**
	 * Empty default constructor
	 */
	private Camera() {
	}

	/** p0 getter
	 * 
	 * @return p0
	 */
	public Point getP0() {
		return p0;
	}

	/** vRight getter
	 * 
	 * @return the vRight
	 */
	public Vector getvRight() {
		return vRight;
	}

	/** vUp getter
	 * 
	 * @return the vUp
	 */
	public Vector getvUp() {
		return vUp;
	}

	/** vTo getter
	 * 
	 * @return the vTo
	 */
	public Vector getvTo() {
		return vTo;
	}

	/** height getter
	 *  
	 * @return the height
	 */
	public double getHeight() {
		return viewPlaneHeight;
	}

	/** width getter
	 * 
	 * @return the width
	 */
	public double getWidth() {
		return viewPlaneWidth;
	}

	/** distance getter
	 * 
	 * @return the distance
	 */
	public double getDistance() {
		return viewPlaneDistance;
	}

	/** builder getter
	 * 
	 * @return a new builder object
	 */
	public static Builder getBuilder() {
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
