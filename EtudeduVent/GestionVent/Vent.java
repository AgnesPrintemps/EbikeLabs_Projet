package GestionVent;
import java.util.Scanner;

public class Vent {

	private String direction;
	private int force;
	
	public Vent (int d, int f){
		switch(d){
		case 1 : direction = "Nord"; break;
		case 2 : direction = "Nord-Ouest"; break;
		case 3 : direction = "Ouest"; break;
		case 4 : direction = "Sud_Ouest"; break;
		case 5 : direction = "Sud"; break;
		case 6 : direction = "Sud-Est"; break;
		case 7 : direction = "Est"; break;
		case 8 : direction = "Nord-Est"; break;
		}
		force =f;
	}
	
	
	
}
