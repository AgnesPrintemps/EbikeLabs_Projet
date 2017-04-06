class Segment{
	
	
	// constructeur du segment à partir des point de départ et d'arivée
	public Segment(Point a, Point b, Vent v, Cycliste c, Velo b, double t){
		debut=a;
		fin=b;
		vent=v;
		cycliste=c;
		velo=b;
		temperature=t;
	}
	
	/** retourne les paramètres
	 * @param les deux de la route
	 * @return un point p correspondant aux conditions de la route tel que
	 *	-p.gety() est l'énergie instantanée nécessaire sur une route d'exactement 45°
	 * 	-p.getx() + p.gety() est l'énergie instantanée nécessaire sur une route d'exactement 45°
	 */
	public Point parametres(){
		
		double f = getFrottement();
		double m = cycliste.getPoids()+velo.getPoids();
		double Vs = velo.getVLim();
		double Cxa = m/250/(Vs*Vs);
		double Cxb = 324*f*Cxa;
		
		double resPes = m*g;
		
		double massevolu = 1.292*273.15/(273.15+temperature);
		
		double airefron = cycliste.aireFron();
		
		double resAira = 0.5*massevolu*airefron*Cxa;
		double resAirb = 0.5*massevolu*airefron*Cxb;
		
		double resFrot = m*g*f;

		double Fra = resPes+resAira;
		double Frb = resAirb+resFrot;
		double vitesse = 12;
		
		return new Point(Fra*vitesse,Frb*vitesse); // valeur générique pour l'instant, on changera en fonction du vent
	}
	
	public double getFrottement(){
		 return 0.01; // valeur arbitraire
	 }
	 
	 
	
	private Point debut;
	private Point fin;
	private Vent vent;
	private Cycliste cycliste;
	private Velo velo;
	private double temperature;
	private static final double g = 9.81;
	
	// Plus tard, les paramètres de la route dépendront des conditions, que l'on déterminera en fonction des coordonées de départ et d'arivée
}
