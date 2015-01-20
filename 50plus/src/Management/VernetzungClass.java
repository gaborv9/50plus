package Management;
 
import java.util.List;

import org.jgrapht.alg.*;
import org.jgrapht.*;
import org.jgrapht.graph.*;

import Data.Serialisierung;
import Personen.Person;

 
public class VernetzungClass {
    public List calc(String a, String b) {
    	    PersonDAO ser = new Serialisierung();
    
    		if (ser.getPersonbyid(a)==null || ser.getPersonbyid(b)==null){
    		 
    			return null;
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
    			 return null;
    		 }
    		 else{
    			return path;
    		 }
    	}
    	
    }
 
