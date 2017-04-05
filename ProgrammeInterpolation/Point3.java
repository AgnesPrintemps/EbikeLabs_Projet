class Point3 extends Point{
		
	// constructeur
	public Point3(double x, double y, double z){
		super(x,y);
		this.z=z;
	}
	
	/** retourne la pente du point
	 * @return la pente du point
	 */
	public double getz(){
		return z;
	}
	
	/** retourne les coordonées cartésiennes du point courant en coordonées polaires
	 * @return les coordonées cartésiennes du point courant en coordonées polaires
	 */
	public Point3 polToCart(){
		return new Point3(getx()*getz(),gety()*getz(),getz()-DonneesGPS.getRayonTerre());
	}
	
	private double z;
}
