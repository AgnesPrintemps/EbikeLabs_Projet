class Segment{
	
	private static final double E_1; // energie requise pour un mètre de pente unité avec des conditions de base
	private static final double E_0; // energie requise pour un mètre de pente nulle avec des conditions de base
	
	// constructeur du segment à partir des point de départ et d'arivée
	public Segment(Point a, Point b, Vent v){
		debut=a;
		fin=b;
		vent=v;
	}
	
	/** retourne les paramètres
	 * @param les deux de la route
	 * @return un point p correspondant aux conditions de la route tel que
	 *	-p.gety() est l'énergie instantanée nécessaire sur une route d'exactement 45°
	 * 	-p.getx() + p.gety() est l'énergie instantanée nécessaire sur une route d'exactement 45°
	 */
	public Point parametres(){
		return new Point(E_1-E_0,E_0); // valeur générique pour l'instant, on changera en fonction du vent
	}
	
	private Point debut;
	private Point fin;
	private Vent vent;
	
	// Plus tard, les paramètres de la route dépendront des conditions, que l'on déterminera en fonction des coordonées de départ et d'arivée
}
