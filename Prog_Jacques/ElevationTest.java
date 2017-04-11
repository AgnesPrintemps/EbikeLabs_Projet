
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.ElevationApi;
import com.google.maps.model.LatLng;
import com.google.maps.model.ElevationResult;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.EncodedPolyline;
import com.google.maps.DirectionsApi;


import java.io.*;

public class ElevationTest {
public static void main (String [] args) {
	String key = "AIzaSyC4L5u34eRDmx15WBWSd1GdcBW8LmGuMsQ";
	GeoApiContext context = new GeoApiContext().setApiKey(key);

	try {

		//cree un premier point
		double lat1=45.152914;
		double lng1=5.776557;
		LatLng p1 = new LatLng(lat1,lng1);

		//cree un second point
		double lat2=45.165987;
		double lng2=5.779868;
		LatLng p2 = new LatLng(lat2,lng2);


		//requete pour la distance sur un trajet par defaut entre les points p1 et p2
		DistanceMatrixApiRequest requestDist= new DistanceMatrixApiRequest(context);
		requestDist.origins(p1);
		requestDist.destinations(p2); 
		DistanceMatrix m=requestDist.await();
		long distance=m.rows[0].elements[0].distance.inMeters;
		System.out.println("distance: "+distance);
		
		//intervalle de distance en metres entre deux points sur le trajet
		int intervalle=10;

		int sample=(int) distance/intervalle;
		
		//cree une requete de direction entre les deux points p1 et p2
		DirectionsApiRequest dReq=DirectionsApi.getDirections(context,""+lat1+","+lng1,""+lat2+","+lng2);
		//resDir est le resultat de la requete
		DirectionsResult resDir=dReq.await();
	
		//on recupere dans p le chemin sous forme d'un polyline
		EncodedPolyline p=resDir.routes[0].overviewPolyline;

		//res contient entre autre l'altitude d'un nombre de points donné par "sample" equidistant le long du chemin. 
		ElevationResult[] res = new ElevationResult[sample];
		res=ElevationApi.getByPath(context,sample,p).await();
		

		//for(int i=0;i<sample;i++)
			//System.out.println(res[i].elevation);
		
		//la base de l'url qu'on va envoyer en requete
		String url = "https://maps.googleapis.com/maps/api/staticmap?size=800x800&path=color:0x0000ff";
		
		//on construit l'url en ajoutant les coordonnées des points récupérés dans res
		for(int i=0;i<sample;i++)
			url=url+"|"+res[i].location.lat+","+res[i].location.lng;

		//on termine l'url ici
		url=url+"&"+"key="+key;

		//System.out.println(url);

	FileReader filereader = null;
      	FileWriter filewriter = null;
	
	
	filereader= new FileReader("./donnees/nombreDonnees.txt");
	BufferedReader bufferedReader = new BufferedReader(filereader);
	String ligne;
	ligne = bufferedReader.readLine();
	filereader.close();

	int numFichier=Integer.parseInt(ligne);

	java.io.File fichier = new java.io.File("./donnees/donnees"+numFichier+".txt");
	fichier.createNewFile();
	

	filewriter = new FileWriter("./donnees/nombreDonnees.txt");
	filewriter.flush();
	filewriter.write(""+(numFichier+1));
	filewriter.close();

	filewriter = new FileWriter("./donnees/donnees"+(numFichier+1)+".txt");

	for(int i=0;i<sample;i++){
		filewriter.write(""+res[i].location.lat+","+res[i].location.lng+":"+res[i].elevation+"\n");
		
	}
		
	filewriter.close();
	
	

	}catch(Exception e){
		System.out.println("erreur a la creation du fichier"+e);	
	}


}
}
