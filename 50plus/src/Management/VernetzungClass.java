package Management;
 
import java.util.List;

import org.jgrapht.alg.*;
import org.jgrapht.*;
import org.jgrapht.graph.*;

import Data.Serialisierung;
import Personen.Person;
/**VernetzungClass berechnet die Vernetzung von 2 Personen.
 * 
 * @author master
 *
 */ 
public class VernetzungClass {
	
	/**Gibt eine Liste mit den Daten der Vernetzung zurück.
	 * 
	 * @param a Person(String), von der die Vernetzung mit einer anderen Person gefragt ist
	 * @param b Person(String), von der die Vernetzung mit einer anderen Person gefragt ist
	 * @return Liste
	 */
    public List calc(String a, String b) {
    	    PersonDAO ser = new Serialisierung();
    
    		if (ser.getPersonbyid(a)==null || ser.getPersonbyid(b)==null){
    		 
    			throw new IllegalArgumentException("Personen nicht gefunden");
    		}
    		
    		DirectedGraph<String, DefaultEdge> directedGraph = new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
    		
    		for(Person temp: ser.getPersonList()){
    			directedGraph.addVertex(temp.getID());
    			 
    		}
    		for(Person temp: ser.getPersonList()){
    			for (Person temp2 : temp.getFreunde()){
    				directedGraph.addEdge(temp.getID(), temp2.getID());
    			}
    		}
    		 List path = DijkstraShortestPath.findPathBetween(directedGraph, a, b);
    		 if (path==null){
    			throw new IllegalArgumentException("Personen nicht gefunden");
    		 }
    		 else{
    			return path;
    		 }
    	}
    	
    }
 
