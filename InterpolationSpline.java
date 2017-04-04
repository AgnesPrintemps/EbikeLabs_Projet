import java.lang.Math;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.LinkedList;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

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
		if(x0<x1){
			int s=0;
			for(int i=0; i<3; i++){
				s+=(Math.pow(x1,i+1)-Math.pow(x0,i+1))*param(i)/(i+1);
			}
			return s;
		}
		else{
			throw new RuntimeException("Intervalle incorrect");
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
			if(param(2)==0){ // si la fonction ets linéaire
				return (-param(0)/param(1)) // on renvoie sa solution
			}
			else{ // si la fonction est quadratique
				switch (i){ // en fonction de i
					case 1:	return (-param(1) - Math.sqrt(delta()))/(2*param(2)); // on renvoie la première solution
					case 2: return (-param(1) + Math.sqrt(delta()))/(2*param(2)); // ou la deuxième
					default:	return -param(1)/(2*param(2)); // par défaut, on renvoie la solution d'une equation quadratiqueique a une solution
				}
			}
		}
		else{ // si la fonction ets cubique
			throw new RuntimeException("Impossible de resoudre l'equation"); // on ne peut pas trouver ses zéros
		}
	}
	
	private double a; // coefficient de degré 3
	private double b; // coefficient de degré 2
	private double c; // coefficient de degré 1
	private double d; // coefficient de degré 0
}
	
class Restriction{ // restriction d'un polynome de degré 3 sur un intervalle [a,b]
	
	//constructeur
	public Restriction(Polynome3 p, double a, double b){
		if(a<b){
			this.p=p;
			this.a=a;
			this.b=b;
		}
		else{ // si a>=b, la restriction est invalide
			throw new RuntimeException("Intervalle invalide");
		}
	}
	/** retourne le debut de la restriction
	  * @return le début de la restriction
	  */
	public double debut(){
		return a;
	}
	/** retourne la fin de la restriction courante
	  * @return la fin de la restriction courante
	  */
	public double fin(){
		return b;
	}
	
	/** indique si un réel est dans la restriction courant
	  * @param un reel c
	  * @return vrai si c est dans l'intervalle de définition de la restriction courante, faux sinon
	  */
	public boolean dans(double c){
		return(a<=c && c<=b);
	}
	
	/** derive la restriction courante
	  * @return la derivée de la restriction courante
	  */
	public Restriction derive(){
		return new Restriction(p.derive(),a,b);
	}
	
	/** fait la somme de la restriction courant avec un reel
	 * @param un reel
	 * @return la somme de la restriction courante avec un réel
	 */
	public Restriction add(double d){
		return new Restriction(p.add(d),a,b);
	}
	
	/** fait le produit de la restriction courant avec un reel
	 * @param un reel
	 * @return le produit de la restriction courante avec un réel
	 */
	public Restriction mult(double d){
		return new Restriction(p.mult(d),a,b);
	}
	
	/** calcule l'intégrale de la restriction sur ses valeurs positives
	 * en supposant que le polynome de la restrction est de degré 2
	 * @return l'intégrale de la restriction sur ses valeurs positives
	 */
	public double integralPos(){
		if(p.param(3)==0){
			if(p.param(2)==0){
				if(param(1)==0){ // si le polynome est de degré 1
					if (p.apply(a)>0){ // si la valeur initiale est positive
						return p.integral(a,b); // on fait l'intégrale de a à b
					}
					else{ // si la valeur initiale est negative
						return 0; // on retourne 0
					}
				}
				else{ // si le polynome est de degré 2
					if (p.apply(a)>0){ // si la valeur initiale est positive
						if(dans(p.sol(0))){ // si la solution est dans l'intervalle
							return p.integral(a,p.sol(0)); // on fait l'intégrale de a à la solution
						}
						else{ // si la solution n'est pas dans l'intervalle
							return p.integral(a,b); // on fait l'intégrale de a à b
						}
					}
					else{ // si la valeur initiale est negative
						if(dans(p.sol(0))){ // si la solution est dans l'intervalle
							return p.integral(p.sol(0),b); // on fait l'intégrale de la solution à b
						}
						else{ // si la solution n'est pas dans l'intervalle
							return 0; // onb retourne 0
						}
					}
				}
			}
			else{ // si le polynome est de degré 2
				if(p.nsol()==2){ // si le polynome a 2 solutions
					if(p.apply(a)>0){ // si sa valeur initiale est positive
						if(dans(p.sol(1))){ // si la solution 1 est dans la restriction
							if(dans(p.sol(2))){ // si la solution 2 est dans la restriction
								return (p.integral(a,p.sol(1)) + p.integral(p.sol(2),b)); // on fait la somme des intégrales entra a et la première solution et entre la deuxième solution et b
							}
							else{ // si la solution 2 n'est pas dans la restriction
								return p.integral(a,p.sol(1)); // on fait l'intégrale de a à la solution 1
							}
						}
						else{ // si la solution 1 n'est pas dans la restriction
							if(dans(p.sol(2))){ // si la solution 2 est dans la restriction
								return p.integral(a,p.sol(2)); // on fait l'intégrale de a à la solution 2
							}
							else{ // si la solution 2 n'est pas dans la restriction
								return p.integral(a,b); // on fait l'intégrale de a à b
							}
						}
					}
					else{ // si la valeur initiale est négative
						if(dans(p.sol(1))){ // si la solution 1 est dans la restriction
							if(dans(p.sol(2))){ // si la solution 2 est dans la restriction
								return p.integral(p.sol(1),p.sol(2)); // on fait l'intégrale de la solution 1 à la solution 2
							}
							else{ // si la solution 2 n'est pas dans la restriction
								return p.integral(p.sol(1),b); // on fait l'intégrale de la solution 1 à b
							}
						}
						else{ // si la solution 1 n'est pas dans la restriction
							if(dans(p.sol(2))){ // si la solution 2 est dans la restriction
								return p.integral(p.sol(2),b); // on fait l'intégrale de la solution 2 à b
							}
							else{ // si la solution 2 n'est pas dans la restriction
								return 0; // on retourne 0
							}
						}
					 }
				}
				else{ // si le polynome a un ou deux solutions
					if (p.apply(a)>0){ // si la valeur initiale est positive
						return p.integral(a,b); // on fait l'intégrale de a à b
					}
					else{ // si la valeur initiale est negative
						return 0; // on retourne 0
					}
				}
			}
		}
		else{ // si le polynome est de degré 3
			throw new RuntimeException("Impossible de trouver l'intégrale positive"); // on ne peut pas trouver l'intégrale positive
		}
	}
	
	private Polynome3 p; // polynome
	private double a; // début de l'intervalle
	private double b; // fin de l'intervalle
}

class Spline{
		// constructeur vide
		public Spline(){
			l=new LinkedList<Restriction>();
		}
	
		// constructeur à partir d'un chemin
		public Spline(Chemin c){
			l=new LinkedList<Restriction>(); // on initialise la liste vide
			LinkedList<Point3> p=c.get(); // on calcule la liste des points à partir du chemin
			for(int i=0; i<p.size()-1;i++){ // puis, entre chaque couple de points
				add(Hermite(p.get(i),p.get(i+1))); // on calcule le polynome interpolant de Hermite
			}
		}
	
		/** calcule l'intégrale de la spline sur ses valeurs positives
	 	 * @return l'intégrale de la restriction sur ses valeurs positives
	 	 */
		public double integralPos(){
			double s=0;
			for (Restriction i:l){
				s+=i.integralPos(); // on fait la somme des intégrales de toutes les restrictions de la spline
			}
			return s;
		}
		
		// ajoute une restriction a la spline courante
		private void add(Restriction r){
			l.add(r);
		}
		
		/** calcule la restriction correspondant au polynome de hermite entre deux points
		 * @param deux points a et b tel que a<b
	 	 * @return le polynome de hermite entre a et b
	 	 */
		public static Restriction Hermite(Point3 a, Point3 b){
			if(a<b){
				Polynome3 H0=new Polynome3(2,-3,0,1); // on cree le polynome de hermite 0
				H0=H0.mult(a.gety()); // et on le multiplie par y_a
				Polynome3 H1=new Polynome3(3,-2,1,0); // on cree le polynome de hermite 1
				H1=H1.mult(a.getyprime()).mult(b.getx()-a.getx()); // et on le multiplie par yprime_a, puis x_b-x_a
				Polynome3 H2=new Polynome3(-2,3,0,0); // on cree le polynome de hermite 2
				H2=H2.mult(b.gety()); // et on le multiplie par y_b
				Polynome3 H3=new Polynome3(1,-1,0,0); // on cree le polynome de hermite 3
				H3=H3.mult(b.getyprime()).mult(b.getx()-a.getx()); // et on le multiplie par yprime_b, puis x_b-x_a
				Polynome3 p=H0.add(H1).add(H2).add(H3); // on fait la somme des 4 polynomes
				p=p.transAff(1/(b.getx()-a.getx()),-a.getx()/(b.getx()-a.getx())); // et on l'applique à (x-x_0)/(x_1-x_0), soit x * 1/(x_1-x_0) - x_0/(x_1-x_0) 
				return (new Restriction(p,a.getx(),b.getx())); // et on retourne ce polynome restreint à [x_0, x_1]
			}
			else{ // si a>=b, la restriction est invalide
				throw new RuntimeException("Intervalle invalide");
			}
		}
		
		/** derive la spline courante
	  	* @return la derivée de la spline courante
	  	*/
		public Spline derive(){
			Spline d=new Spline();
			for(Restriction i:l){
				d.add(i.derive()); // on derive chaque restriction de la spline
			}
			return d;
		}
		
		/** fait la somme de la restriction courant avec un reel
	 	* @param un reel
	 	* @return la somme de la restriction courante avec un réel
	 	*/
		public Spline add(double d){
			Spline s=new Spline();
			for(Restriction i:l){
				s.add(i.add(d)); // on applique la somme à chaque restriction de la spline
			}
			return s;
		}
		
		/** fait le produit de la restriction courant avec un reel
	 	* @param un reel
		* @return le produit de la restriction courante avec un réel
	 	*/
		public Spline mult(double d){
			Spline s=new Spline();
			for(Restriction i:l){
				s.add(i.mult(d)); // on applique le produit à chaque restriction de la spline
			}
			return s;
		}
		
		private LinkedList<Restriction> l; // liste de restrictions correspondant à la spline
}

class Point{
	
	// contructeur
	public Point(double x, double y){
		this.x=x;
		this.y=y;
	}
	
	/** retourne la distance du point à l'origine
	 * @return la la distance du point à l'origine
	 */
	public double getx(){
		return x;
	}
	
	/** retourne l'altitude du point
	 * @return l'altitude du point
	 */
	public double gety(){
		return y;
	}
		
	private double x; // distance à l'origine
	private double y; // altitude
}

class Point3 extends Point{
		
	// constructeur
	public Point3(double x, double y, double yprime){
		super(x,y);
		this.yprime=yprime;
	}
	
	/** retourne la pente du point
	 * @return la pente du point
	 */
	public double getyprime(){
		return yprime;
	}
	
	private double yprime; // pente
}

class Chemin{
	
	// constructeur
	public Chemin(String nom){

		File f = new File(nom); // on prend le fichier correspondant à la chaine de caractères
		LinkedList<Point> l = new LinkedList<Point>();
		try{
			Scanner sc=new Scanner(f);
			double dis;
			double al;
			while(sc.hasNextDouble()){ // tant qu'il reste encore des réels dans le fichier
				dis=sc.nextDouble(); // on prend la distance à l'origine
				al=sc.nextDouble(); // on prend son altitude
				l.add(new Point(dis,al)); // et on les stocke dans un point qu'on rajoute au chemin
			}
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		p = new LinkedList<Point3>()
		p.add(new Point3(l.getFirst().getx(),l.getFirst().gety(),0)); // on met la première pente à 0
		for(int i=1; i<l.size()-1;i++){ // pour chaque point, on trouve sa pente en fonction des points d'avant et d'après
			p.add(new Point3(l.get(i).getx(),l.get(i).gety(),(l.get(i+1).gety()-l.get(i-1).gety())/(l.get(i+1).getx()-l.get(i-1).getx())));
		}
		p.add(new Point3(l.getLast().getx(),l.getLast().gety(),0)); // et on met la dernière pente à 0
	}
	
	/** calcule l'énergie nécessaire pour un chemin donné
	 * @param les paramètres a et b tel que E=a*p+b ou E est l'énergie instantanée et p la pente
	 * @return l'energie totale pour le trajet
	 */
	public double Energie(double a, double b){ 
		Spline altitude=new Spline(this); // on crée une nouvelle spline à partir du chemin courant
		Spline pente=altitude.derive(); // puis on determine sa derivée pour trouver la pente à chaque point
		Spline EInst=pente.mult(a).add(b); // et on determine l'energie instantanée à chaque point
		return EInst.integralPos(); // et enfine, on retourne son intégrale positive
	}
	
	/** affiche le chemin courat ainsi que l'energie requise dans un fichier
	 * @param le nom du fichier
	 * @param les paramètres a et b tel que E=a*p+b ou E est l'énergie instantanée et p la pente
	 */
	public void put(String nom, double a, double b){
		try{
			PrintWriter f = new PrintWriter(nom);
			for(Point3 i:p){
				f.println(i.getx() + " " + i.gety() + " " + i.getyprime()); // on place chaque élément du chemin dans le fichier
			}
			f.println();
			f.println(Energie(a,b)); // puis on place léenergie requise dans le fichier
			f.close();
		}
		catch(FileNotFoundException e){}
	}
	
	public LinkedList<Point3> get(){
		return p;
	}
	
	private LinkedList<Point3> p; // liste de points correspondant au chemin
}

class InterpolationSpline{
	
	// argument 1: le fichier d'entree
	// argument 2: le fichier de sortie (pas forcément nécessaire mais utile pour les tests
	// arguements 3 et 4: respectivement, a et b tel que E=a*p+b ou E est l'énergie instantanée et p la pente
	
	public static void main(String[] args){
		Chemin c=new Chemin(args[0]);
		c.put(args[1], Double.parseDouble(args[2]), Double.parseDouble(args[3]));
	}
}
