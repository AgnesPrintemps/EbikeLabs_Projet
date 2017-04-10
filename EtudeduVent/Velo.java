public class Velo {
	
	public Velo(file f){
   	 	try{
			Scanner sc=new Scanner(f);
			String temp = sc.nextLine();
			poids=Double.parseDouble(temp);
			temp = sc.nextLine();
			vitessemax=Double.parseDouble(temp);
			temp = sc.nextLine();
			batterie=Double.parseDouble(temp);
		}
		catch(FileNotFoundException e){}
		sc.close()
  	}
  
	public Velo(double p, double v, double t){
    		poids=p;
    		vitessemax=v;
   		batterie=b;
 	}
  
  	public double getPoids(){
		return poids;
	}
	
	public double getVLim(){
		return vitessemax;
	}

  	private final double poids; //en kg
  	private final double vitessemax; // en km/h
  	private final double batterie; // en Watts
}
