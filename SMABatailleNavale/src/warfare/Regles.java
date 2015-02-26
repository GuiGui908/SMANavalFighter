package warfare;

import java.util.ArrayList;


/**
 * Représentation en objet d’une règle d’inférence
 */
public class Regles {
	
	/**
	 * Constructeur.
	 */
	protected String regle;
	protected ArrayList<String> faitS;
	protected String consequence;

	public Regles(String regle) {
		this.faitS = new ArrayList<String>();
				this.regle=regle;
				String fait="";
				boolean consequence = false;
				for(int i=0;i<regle.length();i++){
					
					if(regle.substring(i,i+1).equals("=")){
						consequence=true;
						this.faitS.add(fait);
					}

					if(!consequence){
						if(regle.substring(i,i+1).equals(",")){
							this.faitS.add(fait);
							fait="";
						}
						else
						fait=fait+regle.substring(i,i+1);
					}
				}
				this.consequence = regle.substring(regle.indexOf('>')+1);
	}

	/**
	 * @param fait
	 * @return vrai si le fait correspond à un prémisse d'une règle
	 */
	public boolean satisfaitCondition(String fait) {	
		boolean b=false;
		for(int i=0;i<faitS.size();i++){
			if(fait.equals(faitS.get(i)))
				b=true;
		}
		return (b);
	}
	
	/**
	 * @param faitS
	 * @return vrai si tous les faitS satisfont les prémisses d'une règle
	 */
	public boolean satisfaitConditionS(ArrayList<String> T_faitS) {
		boolean b=false;
		boolean r=true;
		for(int j=0;j<faitS.size();j++){
			b=false;
			for(int i=0;i<T_faitS.size();i++){
				if(faitS.get(j).equals(T_faitS.get(i))){
					b=true;
				}
			}
			if(!b)
				r=false;
		}
		return(r);
	}

	protected String getRegle() {
		return regle;
	}

	protected void setRegle(String regle) {
		this.regle = regle;
	}

	protected ArrayList<String> getFaitS() {
		return faitS;
	}

	protected void setFaitS(ArrayList<String> faitS) {
		this.faitS = faitS;
	}

	protected String getConsequence() {
		return consequence;
	}

	protected void setConsequence(String consequence) {
		this.consequence = consequence;
	}

	/*
	 * // Ensemble des règles utilisées par le moteur d’inférence sous un format spécifique.
		// prémisse1, ..., prémisseN  => conséquence
		
		PasDeFaits=>JouerRandom
		Couler=>DeleteFaits
		JouerACoteHautReussite,JouerACoteHautFail=>JouerACoteBas
		JouerACoteHautReussite=>JouerACoteHaut
		JouerACoteBasReussite,JouerACoteBasFail=>JouerACoteHaut
		JouerACoteBasReussite=>JouerACoteBas
		JouerADroiteReussite,JouerADroiteFail=>JouerAGauche
		JouerADroiteReussite=>JouerADroite
		JouerAGaucheReussite,JouerAGaucheFail=>JouerADroite
		JouerAGaucheReussite=>JouerAGauche
		JouerACoteHautFail=>JouerACoteBas
		JouerACoteBasFail=>JouerADroite
		JouerADroiteFail=>JouerAGauche
		Toucher=>JouerACoteHaut
*/


}
