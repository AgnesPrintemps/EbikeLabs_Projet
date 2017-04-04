import java.lang.Math;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.LinkedList;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

class Polynome3{
	
	public Polynome3(){
		this.a=0.0;
		this.b=0.0;
		this.c=0.0;
		this.d=0.0;
	}
	
	public Polynome3(double a, double b, double c, double d){
		this.a=a;
		this.b=b;
		this.c=c;
		this.d=d;
	}
	
	public Polynome3(Polynome3 p){
		this.a=p.a;
		this.b=p.b;
		this.c=p.c;
		this.d=p.d;
	}
	
	public Polynome3(double b, double c, double d){
		new Polynome3(1.0,b,c,d);
	}
	
	public double param(int degre){
		switch (degre) {
            case 0:	return d;
            case 1:	return c;
            case 2:	return b;
            case 3:	return a;
            default:	throw new RuntimeException("Degré incorrect");
        }
	}
	
	public Polynome2 derive(){
		return new Polynome2(this.a*3,this.b*2,this.c);
	}
	
	public Polynome3 add(double d){
		return new Polynome3(this.a,this.b,this.c,this.d+d);
	}
	
	public Polynome3 add(Polynome3 p){
		return new Polynome3(this.a+p.a,this.b+p.b,this.c+p.c,this.d+p.d);
	}
	
	public Polynome3 mult(double d){
		return new Polynome3(this.a*d,this.b*d,this.c*d,this.d*d);
	}
	
	public Polynome3 transAff(double u, double v){
		return new Polynome3(Math.pow(u,3)*a,Math.pow(u,2)*(3*a*v+b),u*(3*a*Math.pow(v,2)+2*b*v+c),a*Math.pow(v,3)+b*Math.pow(v,2)+c*v+d);
	}
	
	public double apply(double x){
		int s=0;
		for(int i=0; i<3; i++){
			s+=Math.pow(x,i)*param(i);
		}
		return s;
	}
	
	public double integral(double x0, double x1){
		int s=0;
		for(int i=0; i<3; i++){
			s+=(Math.pow(x1,i+1)-Math.pow(x0,i+1))*param(i)/(i+1);
		}
		return s;
	}

	public int nsol(){
		return 1;
	}
	
	public double sol(int i){
		throw new RuntimeException("Impossible de resoudre l'equation");
	}
	
	private double a;
	private double b;
	private double c;
	private double d;
}

class Polynome2 extends Polynome3{
	
	public Polynome2(double a, double b, double c){
		super(0.0,a,b,c);
	}
	
	public Polynome2(double b, double c){
		new Polynome2(1.0,b,c);
	}
	
	private double delta(){
		return(Math.pow(param(1),2)-4*param(2)*param(0));
	}
	
	public int nsol(){
		if(delta()>0){
			return 2;
		}
		else if(delta()==0){
			return 1;
		}
		else{
			return 0;
		}
	}
	
	public double sol(int i){
		if(nsol()==0){
			throw new RuntimeException("Aucune solution");
		}
		else{
			switch (i){
				case 1:	return (-param(1) - Math.sqrt(delta()))/(2*param(2));
				case 2: return (-param(1) + Math.sqrt(delta()))/(2*param(2));
				default:	return -param(1)/(2*param(2));
			}
		}
	}
}
	

class Restriction{
	
	public Restriction(Polynome3 p, double a, double b){
		if(a<b){
			this.p=p;
			this.a=a;
			this.b=b;
		}
		else{
			throw new RuntimeException("Spline incorrecte");
		}
	}
	
	public double debut(){
		return a;
	}
	
	public double fin(){
		return b;
	}
	
	public boolean dans(double c){
		return(a<=c && c<=b);
	}
	
	public Restriction derive(){
		return new Restriction(p.derive(),a,b);
	}
	
	public Restriction add(double d){
		return new Restriction(p.add(d),a,b);
	}
	
	public Restriction mult(double d){
		return new Restriction(p.mult(d),a,b);
	}
	
	public double integralPos(){
		if(p.param(3)==0){
			if(p.nsol()==2){
				if(p.apply(a)>0){
					if(a>p.sol(2) || b<p.sol(1) || (a>p.sol(1) && b<p.sol(2))){
						return p.integral(a,b);
					}
					else if(a>p.sol(1) && b>p.sol(2)){
						return p.integral(a,p.sol(2));
					}
					else if(a<p.sol(1) && b<p.sol(2)){
						return p.integral(a,p.sol(1));
					}
					else{
						return p.integral(a,p.sol(1))+p.integral(p.sol(2),b);
					}
				}
				else{
					if(a>p.sol(2) || b<p.sol(1) || (a>p.sol(1) && b<p.sol(2))){
						return 0;
					}
					else if(a>p.sol(1) && b>p.sol(2)){
						return p.integral(p.sol(2),b);
					}
					else if(a<p.sol(1) && b<p.sol(2)){
						return p.integral(p.sol(1),b);
					}
					else{
						return p.integral(p.sol(1),p.sol(2));
					}
				 }
			}
			else{
				if (p.apply(a)>0){
					return p.integral(a,b);
				}
				else{
					return 0;
				}
			}
		}
		else{
			throw new RuntimeException("Impossible de resoudre l'equation");
		}
	}
	
	private Polynome3 p;
	private double a;
	private double b;
}

class Spline{
	
		public Spline(){
			l=new LinkedList<Restriction>();
		}
	
		public Spline(Chemin c){
			l=new LinkedList<Restriction>();
			LinkedList<Point3> p=c.get();
			for(int i=0; i<p.size()-1;i++){
				add(Hermite(p.get(i),p.get(i+1)));
			}
		}
		
		public double integralPos(){
			double s=0;
			for (Restriction i:l){
				s+=i.integralPos();
			}
			return s;
		}
		
		public void add(Restriction r){
			l.add(r);
		}
		
		public static Restriction Hermite(Point3 a, Point3 b){
			Polynome3 H0=new Polynome3(2,-3,0,1);
			H0=H0.mult(a.gety());
			Polynome3 H1=new Polynome3(3,-2,1,0);
			H1=H1.mult(a.getyprime()).mult(a.getx()-a.gety());
			Polynome3 H2=new Polynome3(-2,3,0,0);
			H2=H2.mult(b.gety());
			Polynome3 H3=new Polynome3(1,-1,0,0);
			H3=H3.mult(b.getyprime()).mult(a.getx()-a.gety());
			Polynome3 p=H0.add(H1).add(H2).add(H3);;
			p=p.transAff(1/(b.getx()-a.getx()),-a.getx()/(b.getx()-a.getx()));
			return (new Restriction(p,a.getx(),b.getx()));
		}
		
		public Spline derive(){
			Spline d=new Spline();
			for(Restriction i:l){
				d.add(i.derive());
			}
			return d;
		}
		
		public Spline add(double d){
			Spline s=new Spline();
			for(Restriction i:l){
				s.add(i.add(d));
			}
			return s;
		}
		
		public Spline mult(double d){
			Spline s=new Spline();
			for(Restriction i:l){
				s.add(i.mult(d));
			}
			return s;
		}
		
		private LinkedList<Restriction> l;
}

class Point{
	
	public Point(double x, double y){
		this.x=x;
		this.y=y;
	}
		
	public double getx(){
		return x;
	}
		
	public double gety(){
		return y;
	}
		
	private double x;
	private double y;
}

class Point3 extends Point{
		
	public Point3(double x, double y, double yprime){
		super(x,y);
		this.yprime=yprime;
	}
	
	public double getyprime(){
		return yprime;
	}
	
	private double yprime;
}

class Chemin{
	
	public Chemin(String nom){

		File f = new File(nom);
		LinkedList<Point> l = new LinkedList<Point>();
		try{
			Scanner sc=new Scanner(f);
			double dis;
			double al;
			while(sc.hasNext()){
				dis=sc.nextDouble();
				al=sc.nextDouble();
				l.add(new Point(dis,al));
			}
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		p = new LinkedList<Point3>();
		p.add(new Point3(l.getFirst().getx(),l.getFirst().gety(),0));
		for(int i=1; i<l.size()-1;i++){
			p.add(new Point3(l.get(i).getx(),l.get(i).gety(),(l.get(i+1).gety()-l.get(i-1).gety())/(l.get(i+1).getx()-l.get(i-1).getx())));
		}
		p.add(new Point3(l.getLast().getx(),l.getLast().gety(),0));
	}
	
	public double Energie(double a, double b){
		Spline altitude=new Spline(this);
		Spline pente=altitude.derive();
		Spline EInst=pente.mult(a).add(b);
		return EInst.integralPos();
	}
	
	public void put(String nom, double a, double b){
		try{
			PrintWriter f = new PrintWriter(nom);
			for(Point3 i:p){
				f.println(i.getx() + " " + i.gety() + " " + i.getyprime());
			}
			f.println();
			f.println(Energie(a,b));
			f.close();
		}
		catch(FileNotFoundException e){}
	}
	
	public LinkedList<Point3> get(){
		return p;
	}
	
	private LinkedList<Point3> p;
}

class InterpolationSpline{
	
	public static void main(String[] args){
		Chemin c=new Chemin(args[0]);
		c.put(args[1], Double.parseDouble(args[2]), Double.parseDouble(args[3]));
	}
}
