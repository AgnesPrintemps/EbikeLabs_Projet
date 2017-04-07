public class Velo {
  
  public Velo(double p, double v, double t){
    poids=p;
    vitessemax=v;
    batterie=b;
  }
  
  public double getPoids(){
		return poids;
	}
	
	public double getVLim(){
		return vitessemax;
	}

  private final double poids; //en kg
  private final double vitessemax; // en km/h
  private final double batterie; // en Watts
}
