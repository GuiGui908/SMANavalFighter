package warfare;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


/**
 * Fonctionnalités relatives à la gestion des faits et des règles ainsi que la prise de décision.
 */
public class MoteurInference {

	// Ensemble d'objets règles.... ?
	// Ensemble de faits ?
	private ArrayList<Regles> myRules = new ArrayList<Regles>();
	private ArrayList<String> myFacts = new ArrayList<String>();
	protected int[] dernierCoup;
	protected String derniereConsequence;
	protected int[] dernierCoupTouche;
	protected char bateauCible;
	protected ArrayList<char[]> bateauDecouvert = new ArrayList<char[]>(); 
	
	/**
	 * Constructeur.
	 * Lis le fichier de règles dont le chemin est urlFichier. Puis instancie les règles.
	 * @param urlFichier
	 */
	public MoteurInference(String urlFichier) {
		try {
			InputStream inps = new FileInputStream(urlFichier); 
			InputStreamReader inpsr = new InputStreamReader(inps);
			BufferedReader br = new BufferedReader(inpsr);
			String regle;
			while ((regle = br.readLine()) != null) {
				myRules.add(new Regles(regle));
			}
			br.close();
		} catch(FileNotFoundException e) {
			System.err.println("Fichier de règles absent !!!!!" + e.getMessage());
		} catch(IOException e) {
			e.printStackTrace();
		}
/*		myRules.add(new Regles("PasDeFaits=>JouerRandom"));
		myRules.add(new Regles("Couler=>DeleteFaits"));
		myRules.add(new Regles("JouerACoteHautReussite,JouerACoteHautFail=>JouerACoteBas"));
		myRules.add(new Regles("JouerACoteHautReussite=>JouerACoteHaut"));
		myRules.add(new Regles("JouerACoteBasReussite,JouerACoteBasFail=>JouerACoteHaut"));
		myRules.add(new Regles("JouerACoteBasReussite=>JouerACoteBas"));
		myRules.add(new Regles("JouerADroiteReussite,JouerADroiteFail=>JouerAGauche"));
		myRules.add(new Regles("JouerADroiteReussite=>JouerADroite"));
		myRules.add(new Regles("JouerAGaucheReussite,JouerAGaucheFail=>JouerADroite"));
		myRules.add(new Regles("JouerAGaucheReussite=>JouerAGauche"));
		myRules.add(new Regles("JouerADroiteFail=>JouerAGauche"));
		myRules.add(new Regles("JouerACoteBasFail=>JouerADroite"));
		myRules.add(new Regles("JouerACoteHautFail=>JouerACoteBas"));
		myRules.add(new Regles("Toucher=>JouerACoteHaut"));*/
		this.bateauCible='0';
		dernierCoup = new int[2];
		dernierCoupTouche = new int[2];
		dernierCoup[0]=0;dernierCoup[1]=0;
		dernierCoupTouche[0]=0;dernierCoupTouche[1]=0;
	}
	
	/**
	 * @param fait
	 * @return vrai si le fait est solution, cad qu'il ne fait pas partie des faits connus.
	 */
	public boolean estSolution(String fait) {
		boolean b = false;
		for(int i=0;i<myFacts.size();i++){
			if(myFacts.equals(fait))
				b=true;
		}
		return b;
	}
	
	public void addFact(String unFait){
		myFacts.add(unFait);
	}
	
	public void clearFact(){
		myFacts.clear();
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
		int[] coup = new int[2];
		coup[0]=dernierCoup[0];
		coup[1]=dernierCoup[1];
		int i =0;
		String consequence="";
		boolean b= false;
		while(i<myRules.size()&&!b){
			if(myRules.get(i).satisfaitConditionS(myFacts)){
				consequence = (myRules.get(i)).getConsequence();
				b=true;
			}
			i++;
		}
		if(myFacts.size()==0)
			consequence="JouerRandom";
		switch(consequence){
		case "JouerRandom": coup = myRandom(); break;
		case "JouerACoteHaut": coup[0]=coup[0]-1; break;
		case "JouerACoteBas": coup[0]=coup[0]+1; break;
		case "JouerADroite": coup[1]=coup[1]+1; break;
		case "JouerAGauche": coup[1]=coup[1]-1; break;
		case "DeleteFaits": this.clearFact();
				if(bateauDecouvert.isEmpty()) {
					coup = myRandom();
				}
				else {
					addFact("Toucher");
					dernierCoupTouche[0] = (int)bateauDecouvert.get(0)[0];
					dernierCoupTouche[1] = (int)bateauDecouvert.get(0)[1];
					coup[0] = dernierCoupTouche[0];
					coup[1] = dernierCoupTouche[1];
					bateauCible = bateauDecouvert.get(0)[2];
					this.delBateauDecouvert();
				}
				break;
		default: System.out.println("Problème rencontré dans le switchCase conséquence :"+consequence); break;
		}
		this.derniereConsequence=consequence;
		System.out.println("Conséquence: "+consequence);
		return coup; 
	}
	
	protected void miseAjourFaits(boolean consequenceSucces){
		switch(derniereConsequence){
		case "JouerRandom": if(consequenceSucces) this.addFact("Toucher"); break;
		case "DeleteFaits" : break;
		default: 
			if(consequenceSucces) 
				this.addFact(derniereConsequence+"Reussite");
			else  
				this.addFact(derniereConsequence+"Fail");
		}
	}

	protected int[] getDernierCoup() {
		return dernierCoup;
	}

	protected void setDernierCoup(int[] dernierCoup) {
		this.dernierCoup[0] = dernierCoup[0];
		this.dernierCoup[1] = dernierCoup[1];
	}

	protected String getDerniereConsequence() {
		return derniereConsequence;
	}

	protected void setDerniereConsequence(String derniereConsequence) {
		this.derniereConsequence = derniereConsequence;
	}

	protected int[] getDernierCoupTouche() {
		return dernierCoupTouche;
	}

	protected void setDernierCoupTouche(int[] dernierCoupTouche) {
		this.dernierCoupTouche[0] = dernierCoupTouche[0];
		this.dernierCoupTouche[1] = dernierCoupTouche[1];
	}

	protected char getBateauCible() {
		return bateauCible;
	}

	protected void setBateauCible(char bateauCible) {
		this.bateauCible = bateauCible;
	}

	protected ArrayList<String> getMyFacts() {
		return myFacts;
	}

	protected void setMyFacts(ArrayList<String> myFacts) {
		this.myFacts = myFacts;
	}
	
	protected void addBateauDecouvert(char[] bateau) {
		bateauDecouvert.add(bateau);
	}
	
	protected void delBateauDecouvert() {
		if(!bateauDecouvert.isEmpty()) {
			bateauDecouvert.remove(0);
		}
	}
	
	private int[] myRandom() {
		int[] nb = new int[2];
		do {
			 nb[0] = (int) (Math.random()*10);
			 nb[1] = (int) (Math.random()*10);
		}
		while((nb[0]+nb[1])%2 != 0);
		return nb;
	}
	
}
