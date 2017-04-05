import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ApplicationEbike extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final double g = 9.81;
	private JTextField T1;
	private JTextField T2;	
	private JTextField T3;
	private JTextField T4;
	private JTextField T5;
	private JTextField T6;
	private JTextField T7;
	private JTextField T8;
	private JTextField T9;
	private JTextField T10;
	private JLabel L1;
	private JLabel L2;
	private JLabel L3;
	private JLabel L4;
	private JLabel L5;
	private JLabel L6;
	private JLabel L7;
	private JLabel L8;
	private JLabel L9;
	private JLabel L10;
	private static String S1;
	private static String S2;
	private static String S3;
	private static String S4;
	private static String S5;
	private static String S6;
	private static String S7;
	private static String S8;
	private static String S9;
	private static String S10;
	
	private final static String newline = "\n";
	
	public ApplicationEbike() {
		

		JFrame Fen = new JFrame();
		Fen.setTitle("Project Ebike");
		//Fen.setSize(1700, 1000);
		Fen.setSize(1000, 600);
	    Fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Fen.setLocationRelativeTo(null);	
	    Fen.setLayout(new GridLayout(20, 4));
		T1 = new JTextField(30);		
		T2 = new JTextField(30);
		T3 = new JTextField(10);
		T4 = new JTextField(10);
		T5 = new JTextField(10);
		T6 = new JTextField(10);
		T7 = new JTextField(10);
		T8 = new JTextField(10);
		T9 = new JTextField(10);
		T10 = new JTextField(10);
		T1.setBackground(Color.cyan);
		T2.setBackground(Color.cyan);
		T3.setBackground(Color.cyan);
		T4.setBackground(Color.cyan);
		T5.setBackground(Color.cyan);
		T6.setBackground(Color.cyan);
		T7.setBackground(Color.cyan);
		T8.setBackground(Color.cyan);
		T9.setBackground(Color.cyan);
		T10.setBackground(Color.cyan);
		
		L1 = new JLabel("Quel est votre point de départ? ");		
		L2 = new JLabel("Quel est votre point d'arrivée? ");
		L3 = new JLabel("Quelle est votre taille en cm? ");
		L4 = new JLabel("Quel est votre poids total en comptant le poids du vélo en kg? ");
		L5 = new JLabel("Quel est le coeff de frottement en pourcentage? ");
		L6 = new JLabel("Quelle est la pente de la route en pourcentage? ");
		L7 = new JLabel("Quelle est la vitesse limite du velo? ");
		L8 = new JLabel("Quelle est la vitesse du velo? ");
		L9 = new JLabel("Quelle est la température en degrés Celsius? ");
		L10 = new JLabel("Entrez l'aire frontale en m² ");
	    Fen.getContentPane().add(L1);
	    Fen.getContentPane().add(T1);
	    Fen.getContentPane().add(L2);
	    Fen.getContentPane().add(T2);
	    Fen.getContentPane().add(L3);
	    Fen.getContentPane().add(T3);
	    Fen.getContentPane().add(L4);
	    Fen.getContentPane().add(T4);
	    Fen.getContentPane().add(L5);
	    Fen.getContentPane().add(T5);
	    Fen.getContentPane().add(L6);
	    Fen.getContentPane().add(T6);
	    Fen.getContentPane().add(L7);
	    Fen.getContentPane().add(T7);
	    Fen.getContentPane().add(L8);
	    Fen.getContentPane().add(T8);
	    Fen.getContentPane().add(L9);
	    Fen.getContentPane().add(T9);
	    Fen.getContentPane().add(L10);
	    Fen.getContentPane().add(T10);

		T1.addActionListener(this);
		T2.addActionListener(this);
		T3.addActionListener(this);
		T4.addActionListener(this);
		T5.addActionListener(this);
		T6.addActionListener(this);
		T7.addActionListener(this);
		T8.addActionListener(this);
		T9.addActionListener(this);
		T10.addActionListener(this);
		
		Fen.setVisible(true);
			
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		   
		    S1 = T1.getText();
		    S2 = T2.getText();
		    S3 = T3.getText();
		    S4 = T4.getText();
		    S5 = T5.getText();
		    S6 = T6.getText();
		    S7 = T7.getText();
		    S8 = T8.getText();
		    S9 = T9.getText();
		    S10 = T10.getText();
		    double P = Puissance();
		    System.out.println("La puissance nécessaire est de : " + P);
		}
	
	
	public static double CalculDeCx(){
		double Cx;
		double f = Double.parseDouble(S5);
		double p = Double.parseDouble(S6);
		double W = Double.parseDouble(S4);
		double Vs = Double.parseDouble(S7);
		Cx = 324*(f+p)*W/250/(Vs*Vs);
		System.out.println("Le coeff de Cx vaut  : " + Cx);
		return Cx;
	}	
	
	public static double ResDuPesanteur(){
		double m = Double.parseDouble(S4);
		double N = m*g;
		double p = Double.parseDouble(S6);
		System.out.println("La résistance de pesanteur vaut : " + N*p);
		return N*p;
	}	
		
	public static double ResDeLair(){
		double T= Double.parseDouble(S9);
		double massevolu = 1.292*273.15/(273.15+T);
		double airefron = Double.parseDouble(S10);
		double Cx = CalculDeCx();
		System.out.println("La résistance de l'air vaut : " + 0.5*massevolu*airefron*Cx);
		return 0.5*massevolu*airefron*Cx;
	}
		
	public static double ResDeFrottement(){
		double m = Double.parseDouble(S4);
		double N = m*g;
		double f = Double.parseDouble(S5); 
		System.out.println("La résistance de frottement vaut : " + N*f);
		return N*f;
	}	

	public double Puissance(){
		double RDP =ResDuPesanteur();
		double RDL = ResDeLair();
		double RDF = ResDeFrottement();
		double Fr = RDF+RDL+RDP;
		double vitesse = 12;
		double P = Fr*vitesse;
		return P;
		
	}
}

