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
	
	/** The transparency coefficient */
	public Double3 kT = Double3.ZERO;
	
	/** The reflection coefficient */
	public Double3 kR = Double3.ZERO;
	
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
	 * @param kS the kS to set
	 * @return the material after its been updated
	 */
	public Material setKs(double kS) {
		this.kS = new Double3(kS);
		return this;
	}

	/**
	 * setter for kT (Double3)
	 * 
	 * @param kT - the new kT value
	 * @return the material after its been updated
	 */
	public Material setKt(Double3 kT) {
		this.kT = kT;
		return this;
	}

	/**
	 * setter for kT (double)
	 * 
	 * @param kT - the new kT value
	 * @return the material after its been updated
	 */
	public Material setKt(double kT) {
		this.kT = new Double3(kT);
		return this;
	}
	
	/**
	 * setter for kR (Double3)
	 * 
	 * @param kR - the new kR value
	 * @return the material after its been updated
	 */
	public Material setKr(Double3 kR) {
		this.kR = kR;
		return this;
	}
	
	/**
	 * setter for kR (double)
	 * 
	 * @param kR - the new kR value
	 * @return the material after its been updated
	 */
	public Material setKr(double kR) {
		this.kR = new Double3(kR);
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
