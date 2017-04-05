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
		try{
			double dis=0.0;
			double alt=0.0;
			double a=1.0;
			double b=0.0;
			Scanner sc=new Scanner(f).useLocale(Locale.US);;
			while(sc.hasNextLine()){ // tant qu'il reste encore des lignes dans le fichier
				String temp = sc.nextLine();
				String[] nums= temp.split(" ");
				dis=Double.parseDouble(nums[0]); // On récupère la distance à l'origine
				alt=Double.parseDouble(nums[1]); // On récupère l'altitude
				l.add(new Point(dis,alt)); // et on les stocke dans un point qu'on rajoute au chemin
				if(nums.length==4){ // si il y a 4 et non pas 2 paramètres sur la ligne
					a=Double.parseDouble(nums[2]); // on prend le paramètre a
					b=Double.parseDouble(nums[3]); // on prend le paramètre b
				}
				// sinon, on garde ceux d'avant
				t.add(new Point(a,b)); // et on les stocke dans un point qu'on rajoute à la liste des paramètres
			}
			sc.close();
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
