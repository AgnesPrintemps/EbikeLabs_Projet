
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

import org.opencv.calib3d.Calib3d;

import java.util.*;

public class Test {
	public static void main(String[] args){
		//charge la librairie
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Features2d t= new Features2d();
		
		// image1 et image2 doivent etre des images du meme objet.
		Mat image1=Highgui.imread("agnes1.jpg");
		Mat image2=Highgui.imread("agnes2.jpg");
		
		//matrices contenant les points d'interets des deux images
		MatOfKeyPoint keypoints1=new MatOfKeyPoint();
		MatOfKeyPoint keypoints2=new MatOfKeyPoint();
		
		Mat outImage=new Mat();
		
		//cree les detecteurs de points d'interets avec l'algo FAST
		FeatureDetector fastFeatureDetector1 = FeatureDetector.create(FeatureDetector.FAST);
		FeatureDetector fastFeatureDetector2 = FeatureDetector.create(FeatureDetector.FAST);
		
		//detecte les points d'interets avec l'algo FAST
		fastFeatureDetector1.detect(image1,keypoints1);
		fastFeatureDetector2.detect(image2,keypoints2);
		
		DescriptorExtractor siftDescriptor1 = DescriptorExtractor.create(DescriptorExtractor.SIFT); 
		MatOfKeyPoint descriptors1=new MatOfKeyPoint();
		siftDescriptor1.compute(image1,keypoints1,descriptors1);
		
		DescriptorExtractor siftDescriptor2 = DescriptorExtractor.create(DescriptorExtractor.SIFT); 
		MatOfKeyPoint descriptors2=new MatOfKeyPoint();
		siftDescriptor2.compute(image2,keypoints2,descriptors2);
		
		
		
		
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
        
  
        Mat matchoutput = new Mat(image1.rows() * 2, image1.cols() * 2, Highgui.CV_LOAD_IMAGE_COLOR);
        Scalar matchestColor = new Scalar(0, 255, 0);

        List<MatOfDMatch> matches = new LinkedList<MatOfDMatch>();
        DescriptorMatcher descriptorMatcher = DescriptorMatcher.create(DescriptorMatcher.FLANNBASED);
        System.out.println("Matching object and scene images...");
        descriptorMatcher.knnMatch(descriptors1,descriptors2, matches,2);

        System.out.println("Calculating good match list...");
        LinkedList<DMatch> goodMatchesList = new LinkedList<DMatch>();

        float nndrRatio = 0.9f;

        for (int i = 0; i < matches.size(); i++) {
            MatOfDMatch matofDMatch = matches.get(i);
            DMatch[] dmatcharray = matofDMatch.toArray();
            DMatch m1 = dmatcharray[0];
            DMatch m2 = dmatcharray[1];

            if (m1.distance <= m2.distance * nndrRatio) {
                goodMatchesList.addLast(m1);

            }
        }
        
        if (goodMatchesList.size() >= 7) {
            System.out.println("Object Found!!!");

            List<KeyPoint> objKeypointlist = keypoints1.toList();
            List<KeyPoint> scnKeypointlist = keypoints2.toList();

            LinkedList<Point> objectPoints = new LinkedList<>();
            LinkedList<Point> scenePoints = new LinkedList<>();

            for (int i = 0; i < goodMatchesList.size(); i++) {
                objectPoints.addLast(objKeypointlist.get(goodMatchesList.get(i).queryIdx).pt);
                scenePoints.addLast(scnKeypointlist.get(goodMatchesList.get(i).trainIdx).pt);
            }

            MatOfPoint2f objMatOfPoint2f = new MatOfPoint2f();
            objMatOfPoint2f.fromList(objectPoints);
            MatOfPoint2f scnMatOfPoint2f = new MatOfPoint2f();
            scnMatOfPoint2f.fromList(scenePoints);

            Mat homography = Calib3d.findHomography(objMatOfPoint2f, scnMatOfPoint2f, Calib3d.RANSAC, 3);

            Mat obj_corners = new Mat(4, 1, CvType.CV_32FC2);
            Mat scene_corners = new Mat(4, 1, CvType.CV_32FC2);

            obj_corners.put(0, 0, new double[]{0, 0});
            obj_corners.put(1, 0, new double[]{image1.cols(), 0});
            obj_corners.put(2, 0, new double[]{image1.cols(), image1.rows()});
            obj_corners.put(3, 0, new double[]{0, image1.rows()});

            System.out.println("Transforming object corners to scene corners...");
            Core.perspectiveTransform(obj_corners, scene_corners, homography);

            //Mat img = Highgui.imread(bookScene, Highgui.CV_LOAD_IMAGE_COLOR);

            Core.line(image2, new Point(scene_corners.get(0, 0)), new Point(scene_corners.get(1, 0)), new Scalar(0, 255, 0), 4);
            Core.line(image2, new Point(scene_corners.get(1, 0)), new Point(scene_corners.get(2, 0)), new Scalar(0, 255, 0), 4);
            Core.line(image2, new Point(scene_corners.get(2, 0)), new Point(scene_corners.get(3, 0)), new Scalar(0, 255, 0), 4);
            Core.line(image2, new Point(scene_corners.get(3, 0)), new Point(scene_corners.get(0, 0)), new Scalar(0, 255, 0), 4);

            System.out.println("Drawing matches image...");
            MatOfDMatch goodMatches = new MatOfDMatch();
            goodMatches.fromList(goodMatchesList);

            Features2d.drawMatches(image1, keypoints1 , image2, keypoints2, goodMatches, matchoutput, matchestColor, new Scalar(0,255,0), new MatOfByte(), 2);

            Highgui.imwrite("output//outputImage.jpg", image2);
            Highgui.imwrite("output//matchoutput.jpg", matchoutput);
            Highgui.imwrite("output//img.jpg", image1);
        } else {
            System.out.println("Object Not Found");
        }
        

        
       
        
 
		
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
    
  /*  public class SURFDetector {

       
            for (int i = 0; i < matches.size(); i++) {
                MatOfDMatch matofDMatch = matches.get(i);
                DMatch[] dmatcharray = matofDMatch.toArray();
                DMatch m1 = dmatcharray[0];
                DMatch m2 = dmatcharray[1];

                if (m1.distance <= m2.distance * nndrRatio) {
                    goodMatchesList.addLast(m1);

                }
            }

            if (goodMatchesList.size() >= 7) {
                System.out.println("Object Found!!!");

                List<KeyPoint> objKeypointlist = objectKeyPoints.toList();
                List<KeyPoint> scnKeypointlist = sceneKeyPoints.toList();

                LinkedList<Point> objectPoints = new LinkedList<>();
                LinkedList<Point> scenePoints = new LinkedList<>();

                for (int i = 0; i < goodMatchesList.size(); i++) {
                    objectPoints.addLast(objKeypointlist.get(goodMatchesList.get(i).queryIdx).pt);
                    scenePoints.addLast(scnKeypointlist.get(goodMatchesList.get(i).trainIdx).pt);
                }

                MatOfPoint2f objMatOfPoint2f = new MatOfPoint2f();
                objMatOfPoint2f.fromList(objectPoints);
                MatOfPoint2f scnMatOfPoint2f = new MatOfPoint2f();
                scnMatOfPoint2f.fromList(scenePoints);

                Mat homography = Calib3d.findHomography(objMatOfPoint2f, scnMatOfPoint2f, Calib3d.RANSAC, 3);

                Mat obj_corners = new Mat(4, 1, CvType.CV_32FC2);
                Mat scene_corners = new Mat(4, 1, CvType.CV_32FC2);

                obj_corners.put(0, 0, new double[]{0, 0});
                obj_corners.put(1, 0, new double[]{objectImage.cols(), 0});
                obj_corners.put(2, 0, new double[]{objectImage.cols(), objectImage.rows()});
                obj_corners.put(3, 0, new double[]{0, objectImage.rows()});

                System.out.println("Transforming object corners to scene corners...");
                Core.perspectiveTransform(obj_corners, scene_corners, homography);

                Mat img = Highgui.imread(bookScene, Highgui.CV_LOAD_IMAGE_COLOR);

                Core.line(img, new Point(scene_corners.get(0, 0)), new Point(scene_corners.get(1, 0)), new Scalar(0, 255, 0), 4);
                Core.line(img, new Point(scene_corners.get(1, 0)), new Point(scene_corners.get(2, 0)), new Scalar(0, 255, 0), 4);
                Core.line(img, new Point(scene_corners.get(2, 0)), new Point(scene_corners.get(3, 0)), new Scalar(0, 255, 0), 4);
                Core.line(img, new Point(scene_corners.get(3, 0)), new Point(scene_corners.get(0, 0)), new Scalar(0, 255, 0), 4);

                System.out.println("Drawing matches image...");
                MatOfDMatch goodMatches = new MatOfDMatch();
                goodMatches.fromList(goodMatchesList);

                Features2d.drawMatches(objectImage, objectKeyPoints, sceneImage, sceneKeyPoints, goodMatches, matchoutput, matchestColor, newKeypointColor, new MatOfByte(), 2);

                Highgui.imwrite("output//outputImage.jpg", outputImage);
                Highgui.imwrite("output//matchoutput.jpg", matchoutput);
                Highgui.imwrite("output//img.jpg", img);
            } else {
                System.out.println("Object Not Found");
            }

            System.out.println("Ended....");
        }
    }
    
    
}*/
