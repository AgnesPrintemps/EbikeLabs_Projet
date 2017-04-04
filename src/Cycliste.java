import ProgrammeInterpolation.DonneesGPS;


public class Cycliste {
	private double[][] parcours;
	private double direction;
	
	
	public Cycliste(DonneesGPS d){
		parcours = d.getCoordonnees();
		
		
	}
	
	
	public double getDirection() {
		return 0;
	}
	
	
}
