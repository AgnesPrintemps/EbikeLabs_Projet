import java.util.Scanner;

public class Vent {

	private String direction;
	private double vitesse;
	
	public Vent(String d, double v){
		direction=d;
		vitesse=v;
	}
	
	public Vent (int d, double v){
		switch(d){
			case 1 : direction = "Nord"; break;
			case 2 : direction = "Nord-Nord-Ouest"; break;
			case 3 : direction = "Nord-Ouest"; break;
			case 4 : direction = "Ouest-Nord-Ouest"; break; // par conevntion, on dit comme ça
			case 5 : direction = "Ouest"; break;
			case 6 : direction = "Ouest-Sud-Ouest"; break;
			case 7 : direction = "Sud-Ouest"; break;
			case 8 : direction = "Sud-Sud-Ouest"; break;
			case 9 : direction = "Sud"; break;
			case 10 : direction = "Sud-Sud-Est"; break;
			case 11 : direction = "Sud-Est"; break;
			case 12 : direction = "Est-Sud-Est"; break;
			case 13 : direction = "Est"; break;
			case 14 : direction = "Est-Nord-Est"; break;
			case 15 : direction = "Nord-Est"; break;
			case 16 : direction = "Nord-Nord-Est"; break;
			default : direction = "Erreur"; break;
		}
		vitesse = v;
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
			default: return 0;
		}
				
	}
}
