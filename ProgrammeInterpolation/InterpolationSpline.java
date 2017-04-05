class InterpolationSpline{
	
	// argument 1: le fichier d'entree
	// argument 2: le fichier de sortie (pas forcément nécessaire mais utile pour les tests
	
	public static void main(String[] args){
		new DonneesGPS("donnees5.txt");
		Chemin c=new Chemin(args[0]);
		c.put(args[1]);
	}
}
