import java.net.URL;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Image;
import java.awt.Container;

public class GrayImage extends JPanel {
	private static final int TYPE_INT_ARGB=2;
	private BufferedImage A;
	private final int BLANC=230;
	private int nbpixelsnonblancs;
	
	  protected void paintComponent(Graphics g) {
		    int x = (getWidth() - A.getWidth()) / 2;
		    int y = (getHeight() - A.getHeight()) / 2;
		    g.drawImage(A, x, y, this);
		  }
	  
	  
	
	public GrayImage(){
		//URL url = this.getClass().getResource("/res/marguerite.png");
		//ImageIcon im = new ImageIcon(url);
		
		//charge une image 
		try {
		    A = ImageIO.read(new File("fleurRose.jpg"));
		}catch (IOException e) {
			System.out.println("erreur de chargement du fichier");
		}
		
		//stocke les valeur RGB d'un pixel
		int alpha;
		int rouge;
		int vert;
		int bleu;
		int rgb;
		
		
		//compteur de pixel non "blanc"
		int compteur=0;
		//parcourir tous les pixels de la photo
    		for(int i=0;i<A.getWidth();i++){
    			for(int j=0;j<A.getHeight();j++){
    				rgb=A.getRGB(i,j);
    			
    				//pour extraire les couleurs du pixel
    				alpha = (rgb >> 24) & 0xFF;
    				rouge =   (rgb >> 16) & 0xFF;
    				vert = (rgb >>  8) & 0xFF;
    				bleu =  (rgb >> 0) & 0xFF;
    				if(rouge<BLANC || vert<BLANC || bleu<BLANC){
    					nbpixelsnonblancs++;
    				}else{
    					A.setRGB(i, j, 0);
    				}
    				//System.out.println(A.getRGB(i, j));
    			}
    		}
		
    		//pourcentage de pixel non blanc
    		//double pourcentage=((double)compteur/(double)((A.getWidth()*A.getHeight())))*100;
    		//System.out.println(pourcentage);
	}
	
	public double Aire (){
		return ((double)compteur/(double)((A.getWidth()*A.getHeight())))*100;
	}
    	 // cree une fenetre contenant l'image
    	 JFrame f = new JFrame();
    	 f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	 f.setContentPane(this);
    	 f.setSize(A.getWidth(), A.getHeight());
    	 f.setLocation(0,0);
    	 f.setVisible(true);
    	 
    	 f.repaint();
    	
	}
}
