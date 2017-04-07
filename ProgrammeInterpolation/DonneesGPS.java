import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;


public class DonneesGPS {

	private double[][] coordonnees;
	private double[] altitude;
	private double distancecumulee;
	private int TailleEchantillon;
	private static final int RayonTerre = 6378000; // en mètres
	
	public DonneesGPS(String path){
		File f = new File(path);
		try{
			coordonnees = new double[2][100];
			altitude = new double[100];
			Scanner sc=new Scanner(f);
			int i=0;
			while(sc.hasNextLine()){ // tant qu'il reste encore des réels dans le fichier
				String temp = sc.nextLine();
				String [] alt = temp.split(":");
				String [] coor = alt[0].split(",");
				coordonnees[0][i]=Double.parseDouble(coor[0]); // On récupère la latitude
				coordonnees[1][i]=Double.parseDouble(coor[1]); // On récupère la longitude
				altitude[i]=Double.parseDouble(alt[1]); // On récupère l'altitude
				i++;
			}
			TailleEchantillon = i+1;
			sc.close();
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
	public double getAltitude(int i){
		return altitude[i];
	}
	
	public double[][] getCoordonnees(){
		return coordonnees;
	}
	public double getLatitude(int i){
		return coordonnees[0][i];
	}
	public double getLongitude(int i){
		return coordonnees[1][i];
	}
	
	public double getDistance() {
		return distancecumulee;
	}
	public int getTailleEchantillon() {
		return TailleEchantillon;
	}
	//Conversion des degrés en radian
	public double convertToRad(double angle){
	        return (Math.PI * angle)/180;
	}

	// Calcul de distance (en mètres) entre deux points en coordonnées GPS
	public double Distance(double lat_1, double long_1, double lat_2, double long_2){	 
	    double latitude1 = convertToRad(lat_1);
	    double longitude1 = convertToRad(long_1);
	    double latitude2 = convertToRad(lat_2);
	    double longitude2 = convertToRad(long_2);
	     
	    return RayonTerre * (Math.PI/2 - Math.asin( Math.sin(latitude2) * Math.sin(latitude1) + Math.cos(longitude2 - longitude1) * Math.cos(latitude2) * Math.cos(latitude1)));

	}
	
	public static int getRayonTerre(){
		return RayonTerre;
	}
	
	// a partir de la liste de coordonées sphériques, courante, retourne une liste de coordonées cartésienne
	public static double[][] spherToCart(double[][] s){
		double[][] c=new double[3][100];
		for (int i=0; i<100; i++){
			c[1][i]=s[1][i]*RayonTerre;
			c[2][i]=s[2][i]*RayonTerre;
			c[3][i]=c[3][i]-RayonTerre;
		}
		
	}
	
}
