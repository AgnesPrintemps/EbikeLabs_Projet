import java.util.LinkedList;

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
			if(a.getx()<b.getx()){
				Polynome3 H0=new Polynome3(2,-3,0,1); // on cree le polynome de hermite 0
				H0=H0.mult(a.gety()); // et on le multiplie par y_a
				Polynome3 H1=new Polynome3(1,-2,1,0); // on cree le polynome de hermite 1
				H1=H1.mult(a.getyprime()).mult(b.getx()-a.getx()); // et on le multiplie par yprime_a, puis x_b-x_a
				Polynome3 H2=new Polynome3(-2,3,0,0); // on cree le polynome de hermite 2
				H2=H2.mult(b.gety()); // et on le multiplie par y_b
				Polynome3 H3=new Polynome3(1,-1,0,0); // on cree le polynome de hermite 3
				H3=H3.mult(b.getyprime()).mult(b.getx()-a.getx()); // et on le multiplie par yprime_b, puis x_b-x_a
				Polynome3 p=H0.add(H1).add(H2).add(H3); // on fait la somme des 4 polynomes
				p=p.transAff(1/(b.getx()-a.getx()),-a.getx()/(b.getx()-a.getx())); // et on l'applique à (x-x_0)/(x_1-x_0), soit x * 1/(x_1-x_0) - x_0/(x_1-x_0) 
				return (new Restriction(p,a.getx(),b.getx())); // et on retourne ce polynome restreint à [x_0, x_1]
			}
			else{ // si x_a<=x_b, la restriction est invalide
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
		
		public void affiche(){
			for(Restriction i:l){
				i.affiche();
			}
		}
		
		public Spline app(LinkedList<Point> p){
			if(l.size()==p.size()){
				Spline s=new Spline();
				for(int i=0;i<l.size();i++){
					s.add(l.get(i).mult(p.get(i).getx()).add(p.get(i).gety()));
				}
				return s;
			}
			else{
				throw new RuntimeException("Nombre de paramètres incorrects");
			}
		}
		
		private LinkedList<Restriction> l; // liste de restrictions correspondant à la spline
}
