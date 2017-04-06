
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.features2d.*;
import org.opencv.core.*;
import org.opencv.highgui.Highgui;
import org.opencv.features2d.FeatureDetector;

public class Test {
	public static void main(String[] args){
		//charge la librairie
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Features2d t= new Features2d();
		
		// image1 et image2 doivent etre des images du meme objet.
		Mat image1=Highgui.imread("fleurRose.jpg");
		Mat image2=Highgui.imread("fleurRose.jpg");
		
		//matrices contenant les points d'interets des deux images
		MatOfKeyPoint keypoints1=new MatOfKeyPoint();
		MatOfKeyPoint keypoints2=new MatOfKeyPoint();
		
		Mat outImage=new Mat();
		
		//cree les detecteurs de points d'interets avec l'algo FAST
		FeatureDetector fastFeatureDetector1 = FeatureDetector.create(FeatureDetector.FAST);
		FeatureDetector fastFeatureDetector2 = FeatureDetector.create(FeatureDetector.FAST);
		
		DescriptorExtractor surfDescriptorExtractor = DescriptorExtractor.create(DescriptorExtractor.SIFT); 
		
		//detecte les points d'interets avec l'algo FAST
		fastFeatureDetector1.detect(image1,keypoints1);
		
		//cree une nouvelle fenetre
		JFrame jframe = new JFrame("TEST");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel vidpanel = new JLabel();
        jframe.setContentPane(vidpanel);
        jframe.setSize(640, 480);
        jframe.setVisible(true);
        
       
        
        java.util.List<KeyPoint> listkey=keypoints1.toList();
        java.util.ListIterator<KeyPoint> it=listkey.listIterator();
       
        KeyPoint suiv;
        Point p1;
        Point p2;
        
        //affiche des petits rectangles autour des points d'interet.
        while(it.hasNext()){
        	suiv=it.next();
        	p1=suiv.pt;
        	p2=new Point(p1.x+5,p1.y+5);
        	Core.rectangle(image1,p1,p2, new Scalar(0, 255, 0),1);
        
        }
        
        ImageIcon im = new ImageIcon(Mat2bufferedImage(image1));
        vidpanel.setIcon(im);
        vidpanel.repaint();
        
 
		
		//FeatureDetector c=new FeatureDetector("FAST");
	 	//detect(image,keypoints);
		
		//t.drawKeypoints(Mat image,MatOfKeyPoint keypoints,Mat outImage);
		
	}
	
    public static BufferedImage Mat2bufferedImage(Mat image) {
        MatOfByte bytemat = new MatOfByte();
        Highgui.imencode(".jpg", image, bytemat);
        byte[] bytes = bytemat.toArray();
        InputStream in = new ByteArrayInputStream(bytes);
        BufferedImage img = null;
        try {
            img = ImageIO.read(in);
        } catch (IOException e) {
           
            e.printStackTrace();
        }
        return img;
    }
    
    
}
