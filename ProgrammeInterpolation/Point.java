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
