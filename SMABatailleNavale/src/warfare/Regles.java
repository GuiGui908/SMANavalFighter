package warfare;


/**
 * Repr�sentation en objet d�une r�gle d�inf�rence
 */
public class Regles {
	
	/**
	 * Constructeur.
	 */
	public Regles() {
		
	}

	/**
	 * @param fait
	 * @return vrai si le fait correspond � un pr�misse d'une r�gle
	 */
	public boolean satisfaitCondition(String fait) {
		return false;
	}
	
	/**
	 * @param faitS
	 * @return vrai si tous les faitS satisfont les pr�misses d'une r�gle
	 */
	public boolean satisfaitConditionS(String faitS) {
		return false;
	}
	
	
}
