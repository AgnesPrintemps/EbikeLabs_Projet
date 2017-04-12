import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;


public class ApplicationEbike{
	
	public static void RecupererDonneesGPS(File f, Point a, Point b){}// fonction théorique qui place dans le fichier f les données	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField T1;
	private JTextField T2;	
	private JTextField T3;
	private JTextField T4;
	private JTextField T5;
	private JLabel L1;						
	private JLabel L2;
	private JLabel L3;
	private JLabel L4;
	private JLabel L5;
	private JButton bouton;
	private static String S1;
	private static String S2;
	private static String S3;
	private static String S4;
	private static String S5;
	private final static String newline = "\n";
	private final static File donnees = new File("donnees.txt");
	private static int nfen=1;
	private boolean attente= true;
	
	private static File nom;
	private static Velo velo;
	private static Cycliste cycliste;
	
	public ApplicationEbike() {
		
		JFrame Fen = new JFrame();
		Fen.setTitle("Project Ebike");
		bouton=new JButton("Suite");
		bouton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				suite();
				Fen.dispose();
			} 
		} );
		
		Fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		switch (nfen){
			case 1:
				Fen.setSize(500, 150);
				Fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Fen.setLocationRelativeTo(null);
				Fen.setLayout(new GridLayout(5, 4));
				
				T1 = new JTextField(30);		
				T2 = new JTextField(30);
		
				T1.setBackground(Color.cyan);
				T2.setBackground(Color.cyan);
		
				L1 = new JLabel("Rentrez votre nom ");
				L2 = new JLabel("Rentrez votre vélo ");
		
				Fen.getContentPane().add(L1);
				Fen.getContentPane().add(T1);
				Fen.getContentPane().add(L2);
				Fen.getContentPane().add(T2);
				break;
			case 2:
				Fen.setSize(500, 210);
				Fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Fen.setLocationRelativeTo(null);	
				Fen.setLayout(new GridLayout(7, 4));
				
				T1 = new JTextField(10);		
				T2 = new JTextField(10);
				T3 = new JTextField(10);
				
				T1.setBackground(Color.cyan);
				T2.setBackground(Color.cyan);
				T3.setBackground(Color.cyan);
				
				L1 = new JLabel("Quelle est votre taille en cm? ");
				L2 = new JLabel("Quel est votre poids total en kg? ");
				L3 = new JLabel("Quelle est votre aire frontale en m²?");
				
				Fen.getContentPane().add(L1);
				Fen.getContentPane().add(T1);
				Fen.getContentPane().add(L2);
				Fen.getContentPane().add(T2);
				Fen.getContentPane().add(L3);
				Fen.getContentPane().add(T3);
				
				break;
			case 3:
				Fen.setSize(500, 330);
				Fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Fen.setLocationRelativeTo(null);	
				Fen.setLayout(new GridLayout(11, 4));
				
				T1 = new JTextField(10);		
				T2 = new JTextField(10);
				T3 = new JTextField(10);
				T4 = new JTextField(10);
				T5 = new JTextField(10);
				
				T1.setBackground(Color.cyan);
				T2.setBackground(Color.cyan);
				T3.setBackground(Color.cyan);
				T4.setBackground(Color.cyan);
				T5.setBackground(Color.cyan);
				
				L1 = new JLabel("Quelles sont les coordonées de votre point de depart? ");
				L2 = new JLabel("Quelles sont les coordonées de votre point d'arivée? ");
				L3 = new JLabel("Quelle est la vitesse du vent?");
				L4 = new JLabel("Quelle est la direction du vent?");
				L5 = new JLabel("Quelle est la température?");
				
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

				break;
		}
		Fen.getContentPane().add(bouton);
		Fen.setVisible(true);
	}	
	
	public void suite() {
		// TODO Auto-generated method stub
		switch (nfen){
			case 1:
				S1 = T1.getText();
				S1 = S1 + ".txt";
				S2 = T2.getText();
				S2 = S2 + ".txt";
				nom = new File(S1);
				File nomVelo= new File(S2);
				velo=new Velo(nomVelo);
				if(nom.exists()){
					cycliste=new Cycliste(nom,velo);
					nfen=3;
				}
				else{
					nfen=2;
				}			
				new ApplicationEbike();
				break;		
			case 2:
				S1 = T1.getText();
				S2 = T2.getText();
				S3 = T3.getText();
				try{
					BufferedWriter b= new BufferedWriter(new FileWriter(nom));
					b.write(S1);
					b.newLine();
					b.write(S2);
					b.newLine();
					b.write(S3);						
					b.close();
				}	
				catch(Exception e){}
				cycliste=new Cycliste(nom,velo);
				nfen=3;
				new ApplicationEbike();
				break;
			case 3:
				S1 = T1.getText();
				S2 = T2.getText();
				S3 = T3.getText();
				S4 = T4.getText();
				S5 = T5.getText();
				
				String [] coord = S1.split(" ");
				String [] coora = S2.split(" ");
				Point dep = new Point(Double.parseDouble(coord[0]),Double.parseDouble(coord[1]));
				Point arr = new Point(Double.parseDouble(coora[0]),Double.parseDouble(coora[1]));
				
				RecupererDonneesGPS(donnees,dep,arr);
				Vent vent=new Vent(S4,Double.parseDouble(S3));
				Chemin c=new Chemin(donnees, vent, cycliste, Double.parseDouble(S5));
				System.out.println("L'energie requise sur ce chemin est " + c.energie()/36000000 + " kWh");
		}
	}
}
