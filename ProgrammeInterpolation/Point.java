class Point{
	
	// contructeur
	public Point(double x, double y){
		this.x=x;
		this.y=y;
	}
	
	/** retourne la coordonee x du point
	 * @return la coordonee x du point
	 */
	public double getx(){
		return x;
	}
	
	/** retourne la coordonée y du point
	 * @return la coordonee x du point
	 */
	public double gety(){
		return y;
	}
	
	/** retourne la cdistance entre 2 points
	 * @param les deux points
	 * @return la distance entre les deux points
	 */
	public static double distance(Point a, Point b){
		return (Math.sqrt(Math.pow(a.getx()-b.getx(),2)+Math.pow(a.gety()-b.gety(),2)));
	}
	
	/** retourne les conditions d'une route
	 * @param les deux de la route
	 * @return un point p correspondant aux conditions de la route tel que
	 *	-p.gety() est l'énergie instantanée nécessaire sur une route d'exactement 45°
	 * 	-p.getx() + p.gety() est l'énergie instantanée nécessaire sur une route d'exactement 45°
	 */
	public static Point conditions(Point a, Point b){
		return new Point(50,0.5); // valeur générique pour l'instant
	}
	private double x;
	private double y;
}
