import java.util.LinkedList;
import java.io.File;
import java.io.PrintWriter;

class Chemin{
	
	// constructeur
	public Chemin(String nom){

		DonneesGPS d=new DonneesGPS(nom); // on recupere les données GPS
		LinkedList<Point> l = new LinkedList<Point>(); // les distances à l'origine
		t = new LinkedList<Point>(); // les paramètres a et b
		double ltot=0.0;
		t.add(new Point(ltot,getaltitude(0))); // on commence au point 0.0, à son altitude donnée
		for(int i=1; i<getAltitude().length;i++){ // puis, pour chaque point
			ltot+=distance(new Point(getLongitude(i), getLatitude(i)), new Point(getLongitude(i-1), getLatitude(i-1))); // on ajoute la distance entre le point courant et le dernier point pour trouver la distance à l'origine du point
			l.add(new Point(ltot,getAltitude(i))); // on rajoute à la liste des points un nouveau point indiquant sa distance à l'origine et son altitude
			t.add(getConditions(new Point(getLongitude(i-1), getLatitude(i-1)),new Point(getLongitude(i), getLatitude(i))) // puis, on récupère les conditions entre les 2 points
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
