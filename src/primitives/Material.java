/**
 * 
 */
package primitives;

/**
 * 
 */
public class Material {
	public Double3 kD = Double3.ZERO;
	public Double3 kS = Double3.ZERO;
	public int nShininess = 0;

	/**
	 * @param kD the kD to set
	 */
	public Material setkD(Double3 kD) {
		this.kD = kD;
		return this;
	}

	/**
	 * @param kS the kS to set
	 */
	public Material setkS(Double3 kS) {
		this.kS = kS;
		return this;
	}

	/**
	 * @param kD the kD to set
	 */
	public Material setkD(double kD) {
		this.kD = new Double3(kD);
		return this;
	}

	/**
	 * @param kS the kS to set
	 */
	public Material setkS(double kS) {
		this.kS = new Double3(kS);
		return this;
	}

	/**
	 * @param nShininess the nShininess to set
	 */
	public Material setnShininess(int nShininess) {
		this.nShininess = nShininess;
		return this;
	}
}
