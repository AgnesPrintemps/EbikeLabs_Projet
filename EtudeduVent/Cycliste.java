public class Cycliste {
	
	public Cycliste(File f, Velo v){
		velo=v;
    		try{
			Scanner sc=new Scanner(f);
			nom = sc.nextLine();
			String temp = sc.nextLine();
			taille=Double.parseDouble(temp);
			temp = sc.nextLine();
			poids=Double.parseDouble(temp);
			temp = sc.nextLine();
			aireFrontale=Double.parseDouble(temp);
		}
		catch(FileNotFoundException e){}
  	}
	
	public Cycliste(String n, double t, double p, double a, Velo v){
    		nom=n;
		talle=t;
    		poids=p;
		aireFrontale=a;
		velo=v;
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
	private double poids; // en k
	private double taille; // en cm
	private double aireFrontale;
	private Velo velo;

}
