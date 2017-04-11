import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Velo {
	
	public Velo(File f){
   	 	try{
			Scanner sc=new Scanner(f);
			String temp = sc.nextLine();
			poids = Double.parseDouble(temp);
			temp = sc.nextLine();
			vitessemax = Double.parseDouble(temp);
			temp = sc.nextLine();
			batterie = Double.parseDouble(temp);
			sc.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}
  
  public Velo(double p, double v, double b){
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

  private double poids; //en kg
  private double vitessemax; // en km/h
  private double batterie; // en Watts
}
