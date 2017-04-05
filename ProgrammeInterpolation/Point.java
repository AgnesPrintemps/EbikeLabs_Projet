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
	
	/** retourne les coordonées cartésiennes du point courant en coordonées polaires
	 * @return les coordonées cartésiennes du point courant en coordonées polaires
	 */
	public Point polToCart(){
		return new Point(x*Math.cos(y),x*Math.sin(y));
	}
		
	private double x;
	private double y;
}
