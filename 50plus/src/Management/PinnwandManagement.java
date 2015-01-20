package Management;

import java.util.ArrayList;

import Data.Post_Serialisierung;
import Data.Serialisierung;
import Personen.Post;

/**
 * die Klasse Pinnwandmanagement verwaltet die Methoden von Post_Serialisierung
 * 
 */

public class PinnwandManagement implements PinnwandDAO
{
	PinnwandDAO ps = new Post_Serialisierung();
	
	/**
	 * Die Methode addPost ruft die Methode speicherePost auf, mit Hilfe derer man einen Post speichern kann
	 * @param p Post, der hinzugefuegt wird
     */

    public void speicherePost(Post p, int postNumber) 
    {
    	ps.speicherePost(p, postNumber);
    }
	
    /**
     * Die Methode getOwnpostlist ruft die Methode getOwnpostlist auf, mit Hilfe derer man eine Postliste eines User bekommt
	 * @param username die Posts dieses Users werden zurueckgegeben 
	 * @return gibt die Posts des Users zurueck
	 */
    
    public ArrayList<Post> getOwnpostlist(String username) 
    {
    	return ps.getOwnpostlist(username);
    } 
    
    /**
     * Die Methode deletePost ruft die Methode loeschePost auf, mit Hilfe derer man einen Post loeschen kann
	 * @param postNumber Post mit dieser Number wird geloescht
	 */   
    public void loeschePost(int postNumber) 
    {
    	ps.loeschePost(postNumber);
    }
    
    /**
     * Die Methode getPostNumbers ruft die Methode getPostNumbers auf, mit Hilfe derer man die Anzahl der Posts eines User bekommt
	 * @param username Post mit dieser Number wird geloescht
	 * @return gibt die Anzahl des Posts eines Users zurueck
	 */       
    public ArrayList<Integer> getPostNumbers(String username)
    {
    	return ps.getPostNumbers(username);
    } 
   
    //du bekommst den Post nach postNumber
    public Post getPostbyNumber (int postNumber) 
    {
    	return ps.getPostbyNumber(postNumber);
    
    }    
    
  //du kannst den Post auf flagged/not flagged setzen
    public void changeFlag(boolean flag, int postNumber)
    {
    	ps.changeFlag(flag, postNumber);
    }

  //du bekommst die Liste der flagged Posts zurueck
    public  ArrayList<Post> getFlaggedPostlist()
    {
    	return ps.getFlaggedPostlist();
    }
    
    public void loescheAllPosts(String username)
    {
    	ps.loescheAllPosts(username);
    }
    
}
