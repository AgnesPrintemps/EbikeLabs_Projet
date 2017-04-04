package ProgrammeInterpolation;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;


public class DonneesGPS {

	private double[][] coordonnees;
	private double[] altitude;
	private double distancecumulee;
	
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
				double rlat1 = Math.PI * coordonnees[0][j]/180;
			    double rlat2 = Math.PI * coordonnees[0][j+1]/180;
			    double rlon1 = Math.PI * coordonnees[1][j]/180;
			    double rlon2 = Math.PI * coordonnees[1][j+1]/180;
			 
			    double theta = coordonnees[1][j]-coordonnees[1][j+1];
			    double rtheta = Math.PI * theta/180;
			 
			    double dist = Math.sin(rlat1) * Math.sin(rlat2) + Math.cos(rlat1) * Math.cos(rlat2) * Math.cos(rtheta);
			    dist = Math.acos(dist);
			    dist = dist * 180/Math.PI;
			    dist = dist * 60 * 1.1515;
			 
			    dist = dist * 1.609344;
			    distancecumulee +=dist;
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
}
