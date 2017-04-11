import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Cycliste {
	
	public Cycliste(File f, Velo v){
		velo=v;
    	try{
			Scanner sc=new Scanner(f);
			String temp = sc.nextLine();
			taille = Double.parseDouble(temp);
			temp = sc.nextLine();
			poids = Double.parseDouble(temp);
			temp = sc.nextLine();
			aireFrontale=Double.parseDouble(temp);
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}
	
	public Cycliste(double p, Velo v, double t, double a){
    	poids=p;
		velo=v;
    	taille=t;
		aireFrontale=a;
  	}
	
	public double getPoids(){
		return poids+velo.getPoids();
	}
	
	public double getVLim(){
		return velo.getVLim();
	}
	
	public double getAireFron(){
		return aireFrontale;
	}

	private double poids; // en kg
	private Velo velo;
	private double taille; // en cm
	private double aireFrontale;

}
