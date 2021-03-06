import java.util.*;

public class CalculDePuissance {
	public static final double g = 9.81;

	public static void main(String args[]){
		double RDP =ResDuPesanteur();
		double RDL =ResDeLair();
		double RDF =ResDeFrottement();
		double Fr = RDF+RDL+RDF;
		double vitesse = LectureClavier.lireDouble("\nEntrez la vitesse du velo en m/s : ");
		double P = Fr*vitesse;
		System.out.printf("La puissance vaut : " + P);
	}

public static double CalculDeCx(){
	double Cx;
	double f = LectureClavier.lireDouble("\nEntrez le coeff du frottement en pourcentage : ");
	double p = LectureClavier.lireDouble("\nEntrez pente de la route en pourcentage : ");
	double W = CalculPoids();
	double Vs = LectureClavier.lireDouble("\nEntrez la vitesse limitee en km/h: ");
	
	Cx = 324*(f+p)*W/250/(Vs*Vs);
	System.out.println("Le coeff de Cx vaut  : " + Cx);
	return Cx;
}	
	
public static double CalculPoids(){
	double W;
	double mv = LectureClavier.lireDouble("\nEntrez le poids du velo en kg : ");
	double mc = LectureClavier.lireDouble("\nEntrez le poids du cycliste en kg : ");
	
	W=mv+mc;
	return W;
}		
	
public static double ResDuPesanteur(){
	double m = CalculPoids();
	double N = m*g;
	double p = LectureClavier.lireDouble("\nEntrez pente de la route en pourcentage : ");
	System.out.println("La résistance du pesanteur vaut : " + N*p);
	return N*p;
}	
	
public static double ResDeLair(){
	double T= LectureClavier.lireDouble("\nEntrez la temperature en Celcius : ");
	double massevolu = 1.292*273.15/(273.15+T);
	double airefron = LectureClavier.lireDouble("\nEntrez l'aire frontale en m2 : ");
	double Cx = CalculDeCx();
	System.out.println("La résistance de l'air vaut : " + 0.5*massevolu*airefron*Cx);
	return 0.5*massevolu*airefron*Cx;
}
	
public static double ResDeFrottement(){
	double m = CalculPoids();
	double N = m*g;
	double f = LectureClavier.lireDouble("\nEntrez le coeff du frottement : ");
	System.out.println("La résistance de frottement vaut : " + N*f);
	return N*f;
}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
}
