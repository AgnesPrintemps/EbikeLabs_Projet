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
	
	/** retourne les coordonées cartésiennes du point courant en coordonées sphériques
	 * @return les coordonées cartésiennes du point courant en coordonées sphériques
	 */
	public Point3 spherToCart(){
		return new Point3(getx()*getz(),gety()*getz(),getz()-DonneesGPS.getRayonTerre());
	}
	
	private double yprime; // pente
}
