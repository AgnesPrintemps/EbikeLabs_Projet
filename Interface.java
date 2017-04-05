import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ApplicationEbike extends JFrame implements ActionListener{

	private JButton J;
	private JTextField T1;
	private JTextField T2;	
	private JTextField T3;
	private JTextField T4;
	private JTextField T5;
	private JTextField T6;
	private JTextField T7;
	private JTextField T8;
	private JLabel L1;
	private JLabel L2;
	private JLabel L3;
	private JLabel L4;
	private JLabel L5;
	private JLabel L6;
	private JLabel L7;
	private JLabel L8;
	
	private final static String newline = "\n";
	
	public ApplicationEbike() {
		

		JFrame Fen = new JFrame();
		Fen.setTitle("Project Ebike");
		//Fen.setSize(1700, 1000);
		Fen.setSize(1000, 600);
	    Fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Fen.setLocationRelativeTo(null);	
	    Fen.setLayout(new GridLayout(16, 4));
		T1 = new JTextField(30);		
		T2 = new JTextField(30);
		T3 = new JTextField(10);
		T4 = new JTextField(10);
		T5 = new JTextField(10);
		T6 = new JTextField(10);
		T7 = new JTextField(10);
		T8 = new JTextField(10);
		T1.setBackground(Color.cyan);
		T2.setBackground(Color.cyan);
		T3.setBackground(Color.cyan);
		T4.setBackground(Color.cyan);
		T5.setBackground(Color.cyan);
		T6.setBackground(Color.cyan);
		T7.setBackground(Color.cyan);
		T8.setBackground(Color.cyan);
		
		L1 = new JLabel("Quel est votre point de départ? ");		
		L2 = new JLabel("Quel est votre point d'arrivée? ");
		L3 = new JLabel("Quelle est votre taille en cm? ");
		L4 = new JLabel("Quel est votre poids total en comptant le poids du vélo en kg? ");
		L5 = new JLabel("Quel est le coeff de frottement en pourcentage? ");
		L6 = new JLabel("Quelle est la pente de la route? ");
		L7 = new JLabel("Quelle est la vitesse limite du velo? ");
		L8 = new JLabel("Quelle est la vitesse du velo? ");
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

		T1.addActionListener(this);
		T2.addActionListener(this);
		T3.addActionListener(this);
		T4.addActionListener(this);
		T5.addActionListener(this);
		T6.addActionListener(this);
		T7.addActionListener(this);
		T8.addActionListener(this);
		
		Fen.setVisible(true);
			
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		   
		    String S1 = T1.getText();
		    String S2 = T2.getText();
		    String S3 = T3.getText();
		    String S4 = T4.getText();
		    String S5 = T5.getText();
		    String S6 = T6.getText();
		    String S7 = T7.getText();
		    String S8 = T8.getText();
		    System.out.println("S1 = "+S1+" S2 = "+S2+" S3 = "+S3+" S4 = "+S4 +"S5 = " + S5 + "S6 = " + S6);
		}
	}

