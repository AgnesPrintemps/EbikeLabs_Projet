class Point3 extends Point{
		
	// constructeur
	public Point3(double x, double y, double yprime){
		super(x,y);
		this.yprime=yprime;
	}
	
	/** retourne la pente du point
	 * @return la pente du point
	 */
	public double getyprime(){
		return yprime;
	}
	
	private double yprime;
}
