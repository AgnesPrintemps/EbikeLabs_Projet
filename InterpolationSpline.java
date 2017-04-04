class InterpolationSpline{
	
	// argument 1: le fichier d'entree
	// argument 2: le fichier de sortie (pas forcément nécessaire mais utile pour les tests
	// arguements 3 et 4: respectivement, a et b tel que E=a*p+b ou E est l'énergie instantanée et p la pente
	
	public static void main(String[] args){
		Chemin c=new Chemin(args[0]);
		c.put(args[1], Double.parseDouble(args[2]), Double.parseDouble(args[3]));
	}
}
