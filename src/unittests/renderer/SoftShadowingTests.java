/**
 * 
 */
package unittests.renderer;

import static java.awt.Color.*;

import org.junit.jupiter.api.Test;

import geometries.*;
import lighting.*;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * 
 */
class SoftShadowingTests {
	/** Scene of the tests */
	private final Scene scene = new Scene("Test scene");
	/** Camera builder of the tests */
	private final Camera.Builder camera = Camera.getBuilder().setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
			.setP0(new Point(0, 0, 1000)).setVpDistance(1000).setVpSize(200, 200)
			.setRayTracer(new SuperSampleRayTracer(scene, 81));

	/**
	 * Produce a picture of a two triangles lighted by a spot light with a Sphere
	 * producing a shading
	 */
	@Test
	public void trianglesSphere() {
		scene.geometries.add(
				new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135), new Point(75, 75, -150)) //
						.setMaterial(new Material().setKs(0.8).setShininess(60)), //
				new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
						.setMaterial(new Material().setKs(0.8).setShininess(60)), //
				new Sphere(new Point(0, 0, -11), 30d) //
						.setEmission(new Color(BLUE)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)) //
		);
		scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));
		scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(40, 40, 115), new Vector(-1, -1, -4)) //
				.setKl(4E-4).setKq(2E-5).setRadius(10));

		camera.setImageWriter(new ImageWriter("softShadowingTest", 600, 600)).build().renderImage().writeToImage();
	}

}
