class Segment{
	
	// constructeur du segment à partir des point de départ et d'arivée
	public Segment(Point a, Point b){
		debut=a;
		fin=b;
	}
	
	/** retourne les paramètres
	 * @param les deux de la route
	 * @return un point p correspondant aux conditions de la route tel que
	 *	-p.gety() est l'énergie instantanée nécessaire sur une route d'exactement 45°
	 * 	-p.getx() + p.gety() est l'énergie instantanée nécessaire sur une route d'exactement 45°
	 */
	public Point parametres(){
		return new Point(50,0.5); // valeur générique pour l'instant
	}
	
	private Point debut;
	private Point fin;
	
	// Plus tard, les paramètres de la route dépendront des conditions, que l'on déterminera en fonction des coordonées de départ et d'arivée
}
