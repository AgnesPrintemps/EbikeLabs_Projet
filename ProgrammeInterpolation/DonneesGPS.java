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
				distancecumulee += Math.sqrt(Math.pow((coordonnees[0][j+1] - coordonnees[0][j]),2.0) + Math.pow((coordonnees[1][j+1] - coordonnees[1][j]),2.0));
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
