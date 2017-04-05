package ProgrammeInterpolation;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;


public class DonneesGPS {

	private double[][] coordonnees;
	private double[] altitude;
	private double distancecumulee;
	private final int RayonTerre = 6378000; // en mètres
	
	public DonneesGPS(String nom){
		File f = new File(nom);
		try{
			Scanner sc=new Scanner(f);
			int i= 0;
			while(sc.hasNextDouble()){ // tant qu'il reste encore des réels dans le fichier
				coordonnees[0][i]=sc.nextDouble(); // On récupère la latitude
				coordonnees[1][i]=sc.nextDouble(); // On récupère la longitude
				altitude[i]=sc.nextDouble(); // On récupère l'altitude
				i++;
			}
			distancecumulee = 0;
			for(int j = 0; j <i; j++){
				// calcul de la distance cumulée a partir des latitudes et longitudes de deux points successifs
				distancecumulee += Distance(coordonnees[0][j],coordonnees[1][j],coordonnees[0][j+1],coordonnees[1][j+1]);
			}
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
	}
	
	public double[] getAltitude() {
		return altitude;
	}
	
	public double[][] getCoordonnees(){
		return coordonnees;
	}
	
	public double getDistance() {
		return distancecumulee;
	}
	//Conversion des degrés en radian
	public double convertRad(double angle){
	        return (Math.PI * angle)/180;
	}

	// Calcul de distance (en mètres) entre deux points en coordonnées GPS
	public double Distance(double lat_1, double long_1, double lat_2, double long_2){	 
	    double latitude1 = convertRad(lat_1);
	    double longitude1 = convertRad(long_1);
	    double latitude2 = convertRad(lat_2);
	    double longitude2 = convertRad(long_2);
	     
	    return RayonTerre * (Math.PI/2 - Math.asin( Math.sin(latitude2) * Math.sin(latitude1) + Math.cos(longitude2 - longitude1) * Math.cos(latitude2) * Math.cos(latitude1)));

	}
}
