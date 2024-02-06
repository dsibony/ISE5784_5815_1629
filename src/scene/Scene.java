/**
 * 
 */
package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;

/**
 * Scene class - helps to organize the objects and geometries in the scene
 */
public class Scene {
	/** The name of the scene */
	public String name;
	
	/** The background color of the scene */
	public Color background;
	
	/** The ambient light of the scene (initialized to none) */
	public AmbientLight ambientLight = AmbientLight.NONE;
	
	/** The geometries in the scene */
	public Geometries geometries = new Geometries();
	
	/**
	 * Constructor with the name of the scene as the only parameter
	 * 
	 * @param name - name of the scene
	 */
	public Scene(String name) {
		this.name = name;
	}

	/**
	 * setter for background
	 * 
	 * @param background the background to set
	 * @return the scene after its been updated
	 */
	public Scene setBackground(Color background) {
		this.background = background;
		return this;
	}

	/**
	 * setter for ambientLight
	 * 
	 * @param ambientLight the ambientLight to set
	 * @return the scene after its been updated
	 */
	public Scene setAmbientLight(AmbientLight ambientLight) {
		this.ambientLight = ambientLight;
		return this;
	}

	/**
	 * setter for geometries
	 * 
	 * @param geometries the geometries to set
	 * @return the scene after its been updated
	 */
	public Scene setGeometries(Geometries geometries) {
		this.geometries = geometries;
		return this;
	}
	
}
