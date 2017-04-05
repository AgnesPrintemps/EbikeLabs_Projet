import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Locale;

class Chemin{
	
	// constructeur
	public Chemin(String nom){

		File f = new File(nom); // on prend le fichier correspondant à la chaine de caractères
		LinkedList<Point> l = new LinkedList<Point>(); // les distances à l'origine
		t = new LinkedList<Point>(); // les paramètres a et b
		//try{
			Scanner sc=new Scanner(System.in).useLocale(Locale.US);
			while(sc.hasNextDouble()){ // tant qu'il reste encore des réels dans le fichier
				double dis=sc.nextDouble(); // on prend la distance à l'origine
				double al=sc.nextDouble(); // on prend son altitude
				l.add(new Point(dis,al)); // et on les stocke dans un point qu'on rajoute au chemin
				if(sc.hasNextDouble()){
					double a=sc.nextDouble(); // on prend le paramètre a
					double b=sc.nextDouble(); // on prend le paramètre b
					t.add(new Point(a-b,b)); // et on les stocke dans un point qu'on rajoute à la liste des paramètres
				}
			}
		/*}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}*/
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
	public double Energie(){ 
		Spline altitude=new Spline(this); // on crée une nouvelle spline à partir du chemin courant
		Spline pente=altitude.derive(); // puis on determine sa derivée pour trouver la pente à chaque point
		Spline EInst=pente.app(t); // et on determine l'energie instantanée à chaque point
		return EInst.integralPos(); // et enfine, on retourne son intégrale positive
	}
	
	/** affiche le chemin courat ainsi que l'energie requise dans un fichier
	 * @param le nom du fichier
	 * @param les paramètres a et b tel que E=a*p+b ou E est l'énergie instantanée et p la pente
	 */
	public void put(String nom){
		try{
			PrintWriter f = new PrintWriter(nom);
			for(Point3 i:p){
				f.println(i.getx() + " " + i.gety() + " " + i.getyprime()); // on place chaque élément du chemin dans le fichier
			}
			f.close();
			System.out.println(Energie()); // puis on affiche l'énergie requise
		}
		catch(FileNotFoundException e){}
	}
	
	public LinkedList<Point3> get(){
		return p;
	}
	
	private LinkedList<Point3> p; // liste de points correspondant à l'altitude et à la pente
	private LinkedList<Point> t; // liste des points correspondant aux paramètres sur chaque intervalle
}
