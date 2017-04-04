import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

class Chemin{
	
	// constructeur
	public Chemin(String nom){

		File f = new File(nom); // on prend le fichier correspondant à la chaine de caractères
		LinkedList<Point> l = new LinkedList<Point>();
		try{
			Scanner sc=new Scanner(f);
			double dis;
			double al;
			while(sc.hasNextDouble()){ // tant qu'il reste encore des réels dans le fichier
				dis=sc.nextDouble(); // on prend la distance à l'origine
				al=sc.nextDouble(); // on prend son altitude
				l.add(new Point(dis,al)); // et on les stocke dans un point qu'on rajoute au chemin
			}
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		p = new LinkedList<Point3>();
		p.add(new Point3(l.getFirst().getx(),l.getFirst().gety(),0)); // on met la première pente à 0
		for(int i=1; i<l.size()-1;i++){ // pour chaque point, on trouve sa pente en fonction des points d'avant et d'après
			p.add(new Point3(l.get(i).getx(),l.get(i).gety(),(l.get(i+1).gety()-l.get(i-1).gety())/(l.get(i+1).getx()-l.get(i-1).getx())));
		}
		p.add(new Point3(l.getLast().getx(),l.getLast().gety(),0)); // et on met la dernière pente à 0
	}
	
	/** calcule l'énergie nécessaire pour un chemin donné
	 * @param les paramètres a et b tel que E=a*p+b ou E est l'énergie instantanée et p la pente
	 * @return l'energie totale pour le trajet
	 */
	public double Energie(double a, double b){ 
		Spline altitude=new Spline(this); // on crée une nouvelle spline à partir du chemin courant
		Spline pente=altitude.derive(); // puis on determine sa derivée pour trouver la pente à chaque point
		Spline EInst=pente.mult(a).add(b); // et on determine l'energie instantanée à chaque point
		return EInst.integralPos(); // et enfine, on retourne son intégrale positive
	}
	
	/** affiche le chemin courat ainsi que l'energie requise dans un fichier
	 * @param le nom du fichier
	 * @param les paramètres a et b tel que E=a*p+b ou E est l'énergie instantanée et p la pente
	 */
	public void put(String nom, double a, double b){
		try{
			PrintWriter f = new PrintWriter(nom);
			for(Point3 i:p){
				f.println(i.getx() + " " + i.gety() + " " + i.getyprime()); // on place chaque élément du chemin dans le fichier
			}
			f.println();
			f.println(Energie(a,b)); // puis on place l'énergie requise dans le fichier
			f.close();
		}
		catch(FileNotFoundException e){}
	}
	
	public LinkedList<Point3> get(){
		return p;
	}
	
	private LinkedList<Point3> p; // liste de points correspondant au chemin
}
