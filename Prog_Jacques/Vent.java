import java.util.Scanner;

public class Vent {

	private String direction;
	private double vitesse;
	
	public Vent(String d, double v){
		direction=d;
		vitesse=v;
	}
	
	public double getVitesse(){
		return vitesse;
	}
	
	public double getAngle(){
		double pi = Math.PI;
		switch(direction){
			case "Nord" : return pi/2;
			case "Nord-Nord-Ouest" : return 5*pi/8;
			case "Nord-Ouest" : return 3*pi/4;
			case "Ouest-Nord-Ouest" : return 7*pi/8;
			case "Ouest" : return pi;
			case "Ouest-Sud-Ouest" : return -7*pi/8;
			case "Sud-Ouest" : return -3*pi/4;
			case "Sud-Sud-Ouest" : return -5*pi/8;
			case "Sud": return -pi/2;
			case "Sud-Sud-Est": return -3*pi/8;
			case "Sud-Est": return -pi/4;
			case "Est-Sud-Est": return -pi/8;
			case "Est": return 0;
			case "Est-Nord-Est": return pi/8;
			case "Nord-Est": return pi/4;
			case "Nord-Nord-Est": return 3*pi/8;
			default: throw new RuntimeException("Direction du vent incorrecte");
		}
				
	}
}
