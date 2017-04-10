class CalculEnergie{
	
	public static void RecupererDonneesGPS(File f,Point a, Point b){}// fonction théorique qui place dans le fichier f les données	
	
	// argument 1: le fichier d'entree
	// argument 2: le fichier de sortie (pas forcément nécessaire mais utile pour les tests
	
	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		System.out.println("Entrez les coordonées du point de départ");
		double dx=sc.nextDouble();
		double dy=sc.nextDouble();
		System.out.println("Entrez les coordonées du point de d'arivée");
		double ax=sc.nextDouble();
		double ay=sc.nextDouble();
		RecupererDonneesGPS(new File(args[0]), new Point(dx,dy), new Point(ax,ay));
		System.out.println("Entrez la vitesse du vent");
		double vit=sc.nextDouble();
		System.out.println("Entrez la direction du vent");
		int d=sc.nextInt();
		Vent v=new Vent(d,vit); // le vent
		System.out.println("Entrez la température");
		double t=sc.nextDouble(); // la température
		System.out.println("Entrez le nom du cycliste");
		String nom=sc.nextString();
		System.out.println("Entrez le poids du cycliste");
		double poidsc=sc.nextDouble();
		System.out.println("Entrez la taille du cycliste");
		double taille=sc.nextDouble();
		System.out.println("Entrez l'aire frontale du cycliste");
		double aire=sc.nextDouble();
		System.out.println("Entrez le poids du vélo");
		double poidsv=sc.nextDouble();
		System.out.println("Entrez la vitesse maximale du vélo");
		double vmax=sc.nextDouble();
		System.out.println("Entrez la batterie du vélo");
		double bat=sc.nextDouble();
		Velo velo=new Velo(poidsv,vmax,bat);
		Cycliste cycliste=new Cycliste(nom,taille,poidsc,aire,velo); // le cycliste
		
		Chemin c=new Chemin(args[0], v, cycliste, t);
		c.put(args[1]);
		System.out.println("L'energie requise sur ce chemin est" + c.energie());
		
	}
}
