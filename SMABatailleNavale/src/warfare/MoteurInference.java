package warfare;


/**
 * Fonctionnalit�s relatives � la gestion des faits et des r�gles ainsi que la prise de d�cision.
 */
public class MoteurInference {

	// Ensemble d'objets r�gles.... ?
	// Ensemble de faits ?
	
	/**
	 * Constructeur.
	 * Lis le fichier de r�gles dont le chemin est urlFichier. Puis instancie les r�gles.
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
	 * Contient toute la logique de d�cision du moteur d'inf�rence.
	 * Il utilise un chainage avant simple pour d�cider quel sera le coup suivant � r�aliser en
	 * fonction des faits de base contenu dans la base de faits du moteur d�inf�rence. Cette
	 * fonction est aussi capable de revenir un coup en arri�re si l�avant dernier coup a �t�
	 * une r�ussite et si le dernier coup a �t� un �chec. Le chainage avant simple consiste �
	 * parcourir l�ensemble des faits initiaux et voir si pour un fait donn� il existe une r�gle
	 * dans le cas o� le fait n�est d�j� pas une solution. Si le fait donn� est une solution alors
	 * il est ajout� dans la base de faits courante du moteur. Une fois l�ensemble des faits
	 * parcourus, la base de faits du moteur contient alors le fait lui permettant de prendre la
	 * d�cision.
	 * @return le prochain coup � jouer.
	 */
	public int[] calculCoup() {
		int[] coup = new int[] {0, 0};
		return coup; 
	}
}
