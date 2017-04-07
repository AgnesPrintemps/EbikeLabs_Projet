public class Cycliste {
	
	public Cycliste(String n, double p, Velo v, double t, double a){
    		nom=n;
    		poids=p;
		velo=v;
    		talle=t;
		aireFrontale=a;
  	}
	
	public double getPoids(){
		return poids+velo.getPoids();
	}
	
	public double getVLim(){
		return velo.getVLim();
	}
	
	public double getAireFron(){
		return aireFrontale;
	}

	private String nom;
	private double poids; // en kg
	private Velo velo;
	private double taille; // en cm
	private double aireFrontale;

}
