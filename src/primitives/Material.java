/**
 * 
 */
package primitives;

/**
 * A class representing the material properties of an object
 */
public class Material {
	/** The diffuse reflection coefficient */
	public Double3 kD = Double3.ZERO;
	
	/** The specular reflection coefficient */
	public Double3 kS = Double3.ZERO;
	
	/** The shininess of the material */
	public int nShininess = 0;

	/**
	 * setter for kD (Double3)
	 * 
	 * @param kD the kD to set
	 * @return the material after its been updated
	 */
	public Material setKd(Double3 kD) {
		this.kD = kD;
		return this;
	}

	/**
	 * setter for kS (Double3)
	 * 
	 * @param kS the kS to set
	 * @return the material after its been updated
	 */
	public Material setKs(Double3 kS) {
		this.kS = kS;
		return this;
	}

	/**
	 * setter for kS (double)
	 * 
	 * @param kD the kD to set
	 * @return the material after its been updated
	 */
	public Material setKd(double kD) {
		this.kD = new Double3(kD);
		return this;
	}

	/**
	 * setter for kS (double)
	 * 
	 * @param kS the kS to set
	 * @return the material after its been updated
	 */
	public Material setKs(double kS) {
		this.kS = new Double3(kS);
		return this;
	}

	/**
	 * setter for nShininess
	 * 
	 * @param nShininess the nShininess to set
	 * @return the material after its been updated
	 */
	public Material setShininess(int nShininess) {
		this.nShininess = nShininess;
		return this;
	}
}
