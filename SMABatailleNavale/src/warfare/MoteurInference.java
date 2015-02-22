package warfare;


/**
 * Fonctionnalités relatives à la gestion des faits et des règles ainsi que la prise de décision.
 */
public class MoteurInference {

	// Ensemble d'objets règles.... ?
	// Ensemble de faits ?
	
	/**
	 * Constructeur.
	 * Lis le fichier de règles dont le chemin est urlFichier. Puis instancie les règles.
	 * @param urlFichier
	 */
	public MoteurInference(String urlFichier) {
		
	}
	
	/**
	 * @param fait
	 * @return vrai si le fait est solution, cad qu'il ne fait pas partie des faits connus.
	 */
	public boolean estSolution(String fait) {
		return false;
	}
	
	/**
	 * Contient toute la logique de décision du moteur d'inférence.
	 * Il utilise un chainage avant simple pour décider quel sera le coup suivant à réaliser en
	 * fonction des faits de base contenu dans la base de faits du moteur d’inférence. Cette
	 * fonction est aussi capable de revenir un coup en arrière si l’avant dernier coup a été
	 * une réussite et si le dernier coup a été un échec. Le chainage avant simple consiste à
	 * parcourir l’ensemble des faits initiaux et voir si pour un fait donné il existe une règle
	 * dans le cas où le fait n’est déjà pas une solution. Si le fait donné est une solution alors
	 * il est ajouté dans la base de faits courante du moteur. Une fois l’ensemble des faits
	 * parcourus, la base de faits du moteur contient alors le fait lui permettant de prendre la
	 * décision.
	 * @return le prochain coup à jouer.
	 */
	public int[] calculCoup() {
		int[] coup = new int[] {0, 0};
		return coup; 
	}
}
