package warfare;


/**
 * Représentation en objet d’une règle d’inférence
 */
public class Regles {
	
	/**
	 * Constructeur.
	 */
	public Regles() {
		
	}

	/**
	 * @param fait
	 * @return vrai si le fait correspond à un prémisse d'une règle
	 */
	public boolean satisfaitCondition(String fait) {
		return false;
	}
	
	/**
	 * @param faitS
	 * @return vrai si tous les faitS satisfont les prémisses d'une règle
	 */
	public boolean satisfaitConditionS(String faitS) {
		return false;
	}
	
	
}
