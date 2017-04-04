import java.lang.Math;

class Polynome3{ // polynome de degré 3
	
	// constructeur vide
	public Polynome3(){
		this.a=0.0;
		this.b=0.0;
		this.c=0.0;
		this.d=0.0;
	}
	
	// constructeur complet
	public Polynome3(double a, double b, double c, double d){
		this.a=a;
		this.b=b;
		this.c=c;
		this.d=d;
	}
	
	//constructeur copie
	public Polynome3(Polynome3 p){
		this.a=p.a;
		this.b=p.b;
		this.c=p.c;
		this.d=p.d;
	}
	
	//constructeur simplifié
	public Polynome3(double b, double c, double d){
		new Polynome3(1.0,b,c,d);
	}
	
	/** renvoie le coefficient de degré demandé du polynome courant
	 * @param un entier i de 0 à 3
	 * @return le coefficient i du polynome courant
	 */
	public double param(int degre){
		switch (degre) {
            		case 0:	return d;
            		case 1:	return c;
            		case 2:	return b;
            		case 3:	return a;
            		default:	throw new RuntimeException("Degré incorrect");
        	}
	}
	
	/** derive le polynome courant
	 * @return la derivée du polynome courant
	 */
	public Polynome2 derive(){
		return new Polynome2(this.a*3,this.b*2,this.c);
	}
	
	/** fait la somme du polynome courant avec un réel
	 * @param un reel
	 * @return la somme du polynome courant avec le réel
	 */
	public Polynome3 add(double d){
		return new Polynome3(this.a,this.b,this.c,this.d+d);
	}
	
	/** fait la somme du polynome courant avec un autre polynome
	 * @param un polynome p
	 * @return la somme du polynome courant avec le polynome p
	 */
	public Polynome3 add(Polynome3 p){
		return new Polynome3(this.a+p.a,this.b+p.b,this.c+p.c,this.d+p.d);
	}
	
	/** fait le produit du polynome courant avec un réel
	 * @param un reel
	 * @return le produit du polynome courant avec le réel
	 */
	public Polynome3 mult(double d){
		return new Polynome3(this.a*d,this.b*d,this.c*d,this.d*d);
	}
	
	/** fait une transformation affine du polynome courant
	 * c'est à dire, si le polynome courant est f, transAff(u,v) donne le polynome f(ux+v)
	 * @param deux reels u et v
	 * @return le polynome courant appliqué à ux+v
	 */
	public Polynome3 transAff(double u, double v){
		return new Polynome3(Math.pow(u,3)*a,Math.pow(u,2)*(3*a*v+b),u*(3*a*Math.pow(v,2)+2*b*v+c),a*Math.pow(v,3)+b*Math.pow(v,2)+c*v+d);
	}
	
	/** applique un reel au polynome
	 * c'est à dire, si le polynome courant est f, apply(x) donne la valeur de f(x)
	 * @param un réel
	 * @return l'image de x par le polynome courant
	 */
	public double apply(double x){
		int s=0;
		for(int i=0; i<3; i++){
			s+=Math.pow(x,i)*param(i);
		}
		return s;
	}
	
	/** calcule une intégrale du polynome courant
	 * @param deux réels x_0 eyt x_1 tel que x_0<x_1
	 * @return l'intégrale du polynome courant entre x_0 et x_1
	 */
	public double integral(double x0, double x1){
		if(x0<x1){ // si x0<x1
			int s=0;
			 // on calcule l'intégrale au point x1 et on soustrait l'intégrale au point x0
			for(int i=0; i<3; i++){
				s+=(Math.pow(x1,i+1)-Math.pow(x0,i+1))*param(i)/(i+1);
			}
			return s;
		}
		else{
			if(x0==x1){ // si x0=x1
				return 0; // l'intégrale est vide
			}
			else{ // sinon
				throw new RuntimeException("Intervalle incorrect"); // on ne peut pas intégrer
			}
		}
	}
	
	// calcule de delta du polynome courant si c'est un polynome de degré 2
	private double delta(){
		if(param(3)==0 && param(2)!=0){
			return(Math.pow(param(1),2)-4*param(2)*param(0));
		}
		else{
			throw new RuntimeException("Ne peut que traiter les polynomes de degré 2");
		}
	}
	
	/** calcule le nombre de solutions du polynome
	  * c'est à dire, si le polynome courant est f, calcule le nombre de réels x tel que f(x)=0
	  * @return le nombre de zéros du polynome courant
	  */
	public int nsol(){
		if(param(3)==0){
			if(param(2)==0){
				if(param(1)==0){ // si la fonction est constante
					return 0; // elle a aucun zéro
				}
				else{ // sinon, si elle est affine
					return 1; // elle a un zéro
				}
			}
			else{ // si elle est quadratique
				if(delta()>0){ // si son delta est positif
					return 2; // elle a deux zéros
				}
				else if(delta()==0){ // si son delta est nul
					return 1; // elle a un zéro
				}
				else{ // si son delta est negatif
					return 0; // elle a aucun zéro
				}
			}
		}
		else{ // si elle est cubique
			return 1; // on suppose qu'elle a un zéro (pas forcément vrai, mais il est difficile et pas nécessaire de le déterminer
		}
	}
	/** calcule les solutons d'un polynome
	  * c'est à dire, si le polynome courant est f, calcule le nombre de réels x tel que f(x)=0
	  * @param un entier entre 1 et 2 représentant le zéro recherché
	  * @return le nombre de zéros du polynome courant
	  */
	public double sol(int i){
		if(nsol()==0){ // si la fonction n'a pas de zeros
			throw new RuntimeException("Aucune solution"); // on ne peut rien renvoyer
		}
		else{
			if(param(3)==0){
				if(param(2)==0){ // si la fonction est linéaire
					return (-param(0)/param(1)); // on renvoie sa solution
				}
				else{ // si la fonction est quadratique
					switch (i){ // en fonction de i
						case 1:	// on renvoie la solution inférieure
								if(param(2)>0){ // si a est positif
									return (-param(1) - Math.sqrt(delta()))/(2*param(2)); // c'est celle ou le delta est negatif
								}
								else{ // si a est négatif
									return (-param(1) + Math.sqrt(delta()))/(2*param(2)); // c'est celle ou le delta est positif
								}
						case 2: // ou la solution supérieure
								if(param(2)>0){ // si a est positif
									return (-param(1) + Math.sqrt(delta()))/(2*param(2)); // c'est celle ou le delta est positif
								}
								else{ // si a est négatif
									return (-param(1) - Math.sqrt(delta()))/(2*param(2)); // c'est celle ou le delta est negatif
								}
								
						default:	return -param(1)/(2*param(2)); // par défaut, on renvoie la solution d'une equation quadratiqueique a une solution
					}
				}
			}
			else{ // si la fonction est cubique
				throw new RuntimeException("Impossible de resoudre l'equation"); // on ne peut pas trouver ses zéros
			}
		}
	}
	
	private double a; // coefficient de degré 3
	private double b; // coefficient de degré 2
	private double c; // coefficient de degré 1
	private double d; // coefficient de degré 0
}
