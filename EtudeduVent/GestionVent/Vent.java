package GestionVent;
import java.util.Scanner;

public class Vent {

	private String direction;
	private double force;
	
	public Vent (int d, double f){
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
			case 12 : direction "Est-Sud-Est"; break;
			case 13 : direction = "Est"; break;
			case 13 : direction = "Est-Nord-Est"; break;
			case 14 : direction = "Nord-Est"; break;
			case 15 : direction = "Nord-Nord-Est"; break;
		}
		force = f;
	}
	
	
	
}
