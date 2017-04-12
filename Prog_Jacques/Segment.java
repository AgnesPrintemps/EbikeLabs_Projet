class Segment{
	
	
	// constructeur du segment à partir des point de départ et d'arivée
	public Segment(Point a, Point b, Vent v, Cycliste c, double t){
		debut=a;
		fin=b;
		vent=v;
		cycliste=c;
		temperature=t;
	}
	
	/** retourne les paramètres de la route
	 * @param les deux de la route
	 * @return un point p correspondant aux conditions de la route tel que
	 *	-p.gety() est l'énergie instantanée nécessaire sur une route d'exactement 45°
	 * 	-p.getx() + p.gety() est l'énergie instantanée nécessaire sur une route d'exactement 45°
	 */
	public Point parametres(){
		
		// o,n m'assure que ces calculs sont corrects
		
		double f = getFrottement();
		double m = cycliste.getPoids();
		double Vs = cycliste.getVLim();
		double Cxa = 324*m/250/(Vs*Vs);
		double Cxb = f*Cxa;
		
		double resPes = m*g;
		
		double massevolu = 1.292*273.15/(273.15+temperature);
		
		double airefron = cycliste.getAireFron();
		
		double vVent = getVitesseVent();
		
		double resAira = 0.5*massevolu*airefron*Cxa*vVent*Math.abs(vVent);
		double resAirb = 0.5*massevolu*airefron*Cxb*vVent*Math.abs(vVent);
		
		double resFrot = m*g*f;

		double Fra = resPes+resAira;
		double Frb = resAirb+resFrot;
		
		return new Point(Fra*Vs,Frb*Vs);
	}
	
	/** retourne le coefficient de frottement de l'air de la route en fonction du vent
	 * @return le coefficient de frottement de l'air
	 */
	public static double getFrottement(){
		 return 0.013; // valeur arbitraire, a determiner experimentalement
	}
	
	/** retourne la vitesse du vent par rapport au cycliste
	 * @return la vitesse du vent par rapport au cycliste
	 */
	public double getVitesseVent(){
		// calcul trigonometrique ennuyeux
		double a1=Math.atan((fin.gety()-debut.gety())/(fin.getx()-debut.getx()));
		double a2=vent.getAngle();
		return (cycliste.getVLim()-Math.cos((a1+a2) % (2*Math.PI))*vent.getVitesse());
	}
			
	/** retourne la longeur du chemin
	 * @return la longeur du chemin
	 */
	public double longeur(){
		return Point.distance(debut,fin);
	}
	
	private Point debut;
	private Point fin;
	private Vent vent;
	private Cycliste cycliste;
	private double temperature;
	private static final double g = 9.81;
	
	// Plus tard, les paramètres de la route dépendront des conditions, que l'on déterminera en fonction des coordonées de départ et d'arivée
}
