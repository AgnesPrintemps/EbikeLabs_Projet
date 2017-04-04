import java.awt.Color;
import java.awt.Dimension;
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
	private JLabel L1;
	private JLabel L2;
	private JLabel L3;
	private JLabel L4;
	
	private final static String newline = "\n";
	
	public ApplicationEbike() {
		this.setTitle("GridBagLayout");
	    this.setSize(1700, 1000);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
		//JFrame Fen = new JFrame();
//	    Fen.setLayout(new GridLayout(10, 4));
//		T1 = new JTextField(30);
//		T2 = new JTextField(30);
//		T3 = new JTextField(10);
//		T4 = new JTextField(10);
//		L1 = new JLabel("Quel est votre point de départ? ");
//		L2 = new JLabel("Quel est votre point d'arrivée? ");
//		L3 = new JLabel("Quelle est votre taille en cm? ");
//		L4 = new JLabel("Quel est votre poids en kg? ");
//	    Fen.getContentPane().add(T1);
//	    Fen.getContentPane().add(T2);
//	    Fen.getContentPane().add(T3);
//	    Fen.getContentPane().add(T4);
//	    Fen.getContentPane().add(L1);
//	    Fen.getContentPane().add(L2);
//	    Fen.getContentPane().add(L3);
//	    Fen.getContentPane().add(L4);
		//fen.getContentPane().add(new JButton("3"));
		//this.getContentPane().add(new JButton("4"));
		//this.getContentPane().add(new JButton("5"));
	  //On crée nos différents conteneurs de couleur différente
	    JPanel cell1 = new JPanel();
	    cell1.setBackground(Color.YELLOW);
	    cell1.setPreferredSize(new Dimension(150, 40));		
	    JPanel cell2 = new JPanel();
	    cell2.setBackground(Color.red);
	    cell2.setPreferredSize(new Dimension(60, 40));
	    JPanel cell3 = new JPanel();
	    cell3.setBackground(Color.green);
	    cell3.setPreferredSize(new Dimension(60, 40));
	    JPanel cell4 = new JPanel();
	    cell4.setBackground(Color.black);
	    cell4.setPreferredSize(new Dimension(60, 40));
	    JPanel cell5 = new JPanel();
	    cell5.setBackground(Color.cyan);
	    cell5.setPreferredSize(new Dimension(60, 40));
	    JPanel cell6 = new JPanel();
	    cell6.setBackground(Color.BLUE);
	    cell6.setPreferredSize(new Dimension(60, 40));
	    JPanel cell7 = new JPanel();
	    cell7.setBackground(Color.orange);
	    cell7.setPreferredSize(new Dimension(60, 40));
	    JPanel cell8 = new JPanel();
	    cell8.setBackground(Color.DARK_GRAY);
	    cell8.setPreferredSize(new Dimension(60, 40));
	    //Le conteneur principal
	    JPanel content = new JPanel();
	    content.setPreferredSize(new Dimension(1700, 1000));
	    content.setBackground(Color.WHITE);
	    //On définit le layout manager
	    content.setLayout(new GridBagLayout());
		
	    //L'objet servant à positionner les composants
	    GridBagConstraints gbc = new GridBagConstraints();
			
	    //On positionne la case de départ du composant
	    gbc.gridx = -00;
	    gbc.gridy = -10;
	    //La taille en hauteur et en largeur
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    content.add(cell1, gbc);
	    //---------------------------------------------
	    gbc.gridx = 1;
	    content.add(cell2, gbc);
	    //---------------------------------------------
	    gbc.gridx = 2;		
	    content.add(cell3, gbc);		
	    //---------------------------------------------
	    //Cette instruction informe le layout que c'est une fin de ligne
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    gbc.gridx = 3;	
	    content.add(cell4, gbc);
	    //---------------------------------------------
	    gbc.gridx = 0;
	    gbc.gridy = 1;
	    gbc.gridwidth = 1;
	    gbc.gridheight = 2;
	    //Celle-ci indique que la cellule se réplique de façon verticale
	    gbc.fill = GridBagConstraints.VERTICAL;
	    content.add(cell5, gbc);
	    //---------------------------------------------
	    gbc.gridx = 1;
	    gbc.gridheight = 1;
	    //Celle-ci indique que la cellule se réplique de façon horizontale
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    content.add(cell6, gbc);
	    //---------------------------------------------
	    gbc.gridx = 1;
	    gbc.gridy = 2;
	    gbc.gridwidth = 2;
	    content.add(cell7, gbc);
	    //---------------------------------------------
	    gbc.gridx = 3;
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    content.add(cell8, gbc);
	    //---------------------------------------------
	    //On ajoute le conteneur
	    this.setContentPane(content);

	    this.setVisible(true);
	//	Fen.add(T1); Fen.add(T2);
//		Fen.setTitle("Project Ebike");
//		Fen.setSize(1700, 1000);
//		Fen.setVisible(true);
	//	T1.addActionListener(this);
	//	T2.addActionListener(this);
//		T3.addActionListener(this);
//		T4.addActionListener(this);
//		
	}
	
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		    String text = T1.getText();
		    T1.selectAll();
		}
	}

