/**
 * 
 */
package renderer;

import primitives.*;
import static primitives.Util.*;

import java.util.MissingResourceException;

/**
 * Camera class, used for viewing a scene
 */
public class Camera implements Cloneable {
	private Point p0;
	private Vector vRight;
	private Vector vUp;
	private Vector vTo;
	private double viewPlaneHeight = 0.0;
	private double viewPlaneWidth = 0.0;
	private double viewPlaneDistance = 0.0;
	private ImageWriter imageWriter;
	private RayTracerBase rayTracer;
	private Point viewPlanePC;

	/**
	 * Empty default constructor
	 */
	private Camera() {
	}

	/**
	 * Builder class, used for building cameras
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
		 * @return Builder - after we set the p0 value in the builder we return it
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
		 * @return Builder - after we set the Vector values in the builder we return it
		 */
		public Builder setDirection(Vector vTo, Vector vUp) {
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
		 * @return Builder - after we set the view plane values we return the builder
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
		 * @return Builder - after we set the view plane distance we return the builder
		 */
		public Builder setVpDistance(double distance) {
			if (distance <= 0)
				throw new IllegalArgumentException("illegal distance value");
			this.camera.viewPlaneDistance = distance;
			return this;
		}

		/**
		 * Setter for imageWriter
		 * 
		 * @param imageWriter - the new imageWriter
		 * @return builder after the imageWriter has been updated in the camera
		 */
		public Builder setImageWriter(ImageWriter imageWriter) {
			this.camera.imageWriter = imageWriter;
			return this;
		}

		/**
		 * Setter for rayTracer
		 * 
		 * @param rayTracer - the new rayTracer
		 * @return builder after the rayTracer has been updated in the camera
		 */
		public Builder setRayTracer(RayTracerBase rayTracer) {
			this.camera.rayTracer = rayTracer;
			return this;
		}

		/**
		 * builds a camera while checking if the values are missing or are illegal
		 * 
		 * @return the camera built by the method
		 */
		public Camera build() {
			String problemDesc = "Missing rendering data";
			String problemLoc = "Camera";
			if (camera.p0 == null)
				throw new MissingResourceException(problemDesc, problemLoc, "p0 is missing");
			if (camera.vUp == null)
				throw new MissingResourceException(problemDesc, problemLoc, "vUp is missing");
			if (camera.vTo == null)
				throw new MissingResourceException(problemDesc, problemLoc, "vTo is missing");
			if (camera.imageWriter == null)
				throw new MissingResourceException(problemDesc, problemLoc, "imageWriter is missing");
			if (camera.rayTracer == null)
				throw new MissingResourceException(problemDesc, problemLoc, "rayTracer is missing");
			if (alignZero(camera.viewPlaneWidth) == 0)
				throw new MissingResourceException(problemDesc, problemLoc, "view plane width is missing");
			if (alignZero(camera.viewPlaneWidth) < 0)
				throw new IllegalArgumentException("view plane width has an illegal value");
			if (alignZero(camera.viewPlaneHeight) == 0)
				throw new MissingResourceException(problemDesc, problemLoc, "view plane height is missing");
			if (alignZero(camera.viewPlaneHeight) < 0)
				throw new IllegalArgumentException("view plane height has an illegal value");
			if (alignZero(camera.viewPlaneDistance) == 0)
				throw new MissingResourceException(problemDesc, problemLoc, "view plane height is missing");
			if (alignZero(camera.viewPlaneDistance) < 0)
				throw new IllegalArgumentException("view plane distance has an illegal value");
			if (!isZero(camera.vUp.dotProduct(camera.vTo)))
				throw new IllegalArgumentException("view plane vectors aren't orthogonal to each other");
			camera.vTo.normalize();
			camera.vUp.normalize();
			camera.vRight = camera.vTo.crossProduct(camera.vUp).normalize();
			camera.viewPlanePC = camera.p0.add(camera.vTo.scale(camera.viewPlaneDistance));
			try {
				return (Camera) camera.clone();
			} catch (CloneNotSupportedException ignore) {
				return null;
			}
		}
	}

	/**
	 * p0 getter
	 * 
	 * @return p0
	 */
	public Point getP0() {
		return this.p0;
	}

	/**
	 * vRight getter
	 * 
	 * @return the vRight
	 */
	public Vector getvRight() {
		return this.vRight;
	}

	/**
	 * vUp getter
	 * 
	 * @return the vUp
	 */
	public Vector getvUp() {
		return this.vUp;
	}

	/**
	 * vTo getter
	 * 
	 * @return the vTo
	 */
	public Vector getvTo() {
		return this.vTo;
	}

	/**
	 * height getter
	 * 
	 * @return the height
	 */
	public double getHeight() {
		return this.viewPlaneHeight;
	}

	/**
	 * width getter
	 * 
	 * @return the width
	 */
	public double getWidth() {
		return this.viewPlaneWidth;
	}

	/**
	 * distance getter
	 * 
	 * @return the distance
	 */
	public double getDistance() {
		return this.viewPlaneDistance;
	}

	/**
	 * builder getter
	 * 
	 * @return a new builder object
	 */
	public static Builder getBuilder() {
		return new Builder();
	}

	/**
	 * constructs a ray from the camera to the center of a view plane pixel given
	 * the location of the pixel
	 * 
	 * @param nX - number of pixels in a row
	 * @param nY - number of pixels in a column
	 * @param j  - the x location of the view plane pixel
	 * @param i  - the y location of the view plane pixel
	 * @return the ray from the camera to the center of a view plane pixel
	 */
	public Ray constructRay(int nX, int nY, int j, int i) {
		double rX = this.viewPlaneWidth / (double) nX;
		double rY = this.viewPlaneHeight / (double) nY;
		double xJ = rX * ((double) j - ((double) nX - 1) / 2);
		double yI = rY * -((double) i - ((double) nY - 1) / 2);

		Point pIJ = viewPlanePC;
		if (!isZero(xJ))
			pIJ = pIJ.add(vRight.scale(xJ));
		if (!isZero(yI))
			pIJ = pIJ.add(vUp.scale(yI));
		return (new Ray(p0, pIJ.subtract(p0)));
	}

	/**
	 * renders image by casting rays through the view plane pixels
	 * 
	 * @return camera after the image has been updated
	 */
	public Camera renderImage() {
		int nX = this.imageWriter.getNx();
		int nY = this.imageWriter.getNy();
		for (int i = 0; i < nY; i++) {
			for (int j = 0; j < nX; j++) {
				this.castRay(nX, nY, j, i);
			}
		}
		return this;
	}

	/**
	 * Casts a ray through the middle of the pixel and matches the color to the
	 * intercepted object
	 * 
	 * @param nX     - number of pixels in a row
	 * @param nY     - number of pixels in a column
	 * @param column - current pixel's column
	 * @param row    - current pixel's row
	 */
	private void castRay(int nX, int nY, int column, int row) {
		this.imageWriter.writePixel(column, row, //
				this.rayTracer.traceRay(this.constructRay(nX, nY, column, row)));
	}

	/**
	 * colors the lines according to the interval
	 * 
	 * @param interval - the interval by which the lines are chosen
	 * @param color    - the color used for coloring the lines
	 * 
	 * @return camera after the grid has been printed onto the image
	 */
	public Camera printGrid(int interval, Color color) {
		int nX = this.imageWriter.getNx();
		int nY = this.imageWriter.getNy();
		for (int i = 0; i < nY; i++) {
			for (int j = 0; j < nX; j++) {
				if (i % interval == 0 || j % interval == 0)
					this.imageWriter.writePixel(i, j, color);
			}
		}
		return this;
	}

	/**
	 * Function writeToImage produces unoptimized png file of the image according to
	 * pixel color matrix in the directory of the project
	 */
	public void writeToImage() {
		this.imageWriter.writeToImage();
	}
}
