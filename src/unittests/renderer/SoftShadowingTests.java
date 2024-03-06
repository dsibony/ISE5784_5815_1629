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
 * Testing soft shadow
 */
class SoftShadowingTests {
	/** Scene of the tests */
	private final Scene scene = new Scene("Test scene");
	/** Camera builder of the tests */
	private final Camera.Builder camera = Camera.getBuilder().setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
			.setP0(new Point(0, 0, 1000)).setVpDistance(1000).setVpSize(200, 200)
			.setRayTracer(new SuperSampleRayTracer(scene, 81)).setMultithreading(3).setDebugPrint(0.1);

//	/**
//	 * Produce a picture of a two triangles lighted by a spot light with a Sphere
//	 * producing a shading
//	 */
//	@Test
//	public void trianglesSphere() {
//		scene.geometries.add(
//				new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135), new Point(75, 75, -150)) //
//						.setMaterial(new Material().setKs(0.8).setShininess(60)), //
//				new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
//						.setMaterial(new Material().setKs(0.8).setShininess(60)), //
//				new Sphere(new Point(0, 0, -11), 30d) //
//						.setEmission(new Color(BLUE)) //
//						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)) //
//		);
//		scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));
//		scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(40, 40, 115), new Vector(-1, -1, -4)) //
//				.setKl(4E-4).setKq(2E-5).setRadius(10));
//
//		camera.setImageWriter(new ImageWriter("softShadowingTest", 600, 600)).build().renderImage().writeToImage();
//	}

	/**
	 * Produce a complicated picture that consists of ten shapes
	 */
	@Test
	public void complicatedPicture() {
		scene.geometries.add(
				new Sphere(new Point(0, 0, -150), 50d).setEmission(new Color(BLUE))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKt(0.3)),
				new Sphere(new Point(0, -12, -50), 10d).setEmission(new Color(RED))
						.setMaterial(new Material().setKd(0.6).setKs(0.8).setShininess(50).setKt(0.3)),
				new Sphere(new Point(10, 15, 10), 15d).setEmission(new Color(BLACK))
						.setMaterial(new Material().setKd(0.1).setKs(0.3).setShininess(50).setKt(0.3)),
				new Sphere(new Point(-10, 15, 10), 15d).setEmission(new Color(BLACK))
						.setMaterial(new Material().setKd(0.1).setKs(0.3).setShininess(50).setKt(0.3)),
				new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135), new Point(75, 75, -150)) //
						.setMaterial(new Material().setKs(0.8).setShininess(60)), //
				new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
						.setMaterial(new Material().setKs(0.8).setShininess(60)),
				new Triangle(new Point(-30, -37, -40), new Point(30, -37, -50), new Point(0, -25, -45))
						.setEmission(new Color(ORANGE))
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(50).setKt(0.3)),
				new Triangle(new Point(22, 28, 10), new Point(4, 32, 10), new Point(12, 37, 10))
						.setEmission(new Color(165, 42, 42))
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(50).setKt(0.3)),
				new Triangle(new Point(-22, 28, 10), new Point(-4, 32, 10), new Point(-12, 37, 10))
						.setEmission(new Color(165, 42, 42))
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(50).setKt(0.3)));

		scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));
		scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(40, 40, 115), new Vector(-1, -1, -4)) //
				.setKl(4E-4).setKq(2E-5).setRadius(10));
		scene.lights.add(new SpotLight(new Color(YELLOW), new Point(50, 50, -25), new Vector(1, 1, -0.5).scale(-1))
				.setKl(0.0001).setKq(0.0000001).setRadius(15));
		scene.lights.add(new DirectionalLight(new Color(GREEN), new Vector(2, 1, -0.5)));
		camera.setImageWriter(new ImageWriter("faceObjectsPicture", 600, 600)).build().renderImage().writeToImage();
	}

	/**
	 * Produce a complicated picture that consists of ten shapes
	 */
	@Test
	public void complicatedPictureNoSoft() {
		scene.geometries.add(
				new Sphere(new Point(0, 0, -150), 50d).setEmission(new Color(BLUE))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKt(0.3)),
				new Sphere(new Point(0, -12, -50), 10d).setEmission(new Color(RED))
						.setMaterial(new Material().setKd(0.6).setKs(0.8).setShininess(50).setKt(0.3)),
				new Sphere(new Point(10, 15, 10), 15d).setEmission(new Color(BLACK))
						.setMaterial(new Material().setKd(0.1).setKs(0.3).setShininess(50).setKt(0.3)),
				new Sphere(new Point(-10, 15, 10), 15d).setEmission(new Color(BLACK))
						.setMaterial(new Material().setKd(0.1).setKs(0.3).setShininess(50).setKt(0.3)),
				new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135), new Point(75, 75, -150)) //
						.setMaterial(new Material().setKs(0.8).setShininess(60)), //
				new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
						.setMaterial(new Material().setKs(0.8).setShininess(60)),
				new Triangle(new Point(-30, -37, -40), new Point(30, -37, -50), new Point(0, -25, -45))
						.setEmission(new Color(ORANGE))
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(50).setKt(0.3)),
				new Triangle(new Point(22, 28, 10), new Point(4, 32, 10), new Point(12, 37, 10))
						.setEmission(new Color(165, 42, 42))
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(50).setKt(0.3)),
				new Triangle(new Point(-22, 28, 10), new Point(-4, 32, 10), new Point(-12, 37, 10))
						.setEmission(new Color(165, 42, 42))
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(50).setKt(0.3)));

		scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));
		scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(40, 40, 115), new Vector(-1, -1, -4)) //
				.setKl(4E-4).setKq(2E-5));
		scene.lights.add(new SpotLight(new Color(YELLOW), new Point(50, 50, -25), new Vector(1, 1, -0.5).scale(-1))
				.setKl(0.0001).setKq(0.0000001));
		scene.lights.add(new DirectionalLight(new Color(GREEN), new Vector(2, 1, -0.5)));
		camera.setImageWriter(new ImageWriter("faceObjectsPictureNoSoft", 600, 600)).build().renderImage()
				.writeToImage();
	}
}
