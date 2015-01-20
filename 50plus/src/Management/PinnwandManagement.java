package Management;

import java.util.ArrayList;

import Data.Post_Serialisierung;
import Data.Serialisierung;
import Personen.Post;

/**
 * die Klasse Pinnwandmanagement implementiert die Methoden von PinnwandDAO
 * 
 */

public class PinnwandManagement implements PinnwandDAO
{
	PinnwandDAO ps = new Post_Serialisierung();
	
	/**
	 * Die Methode speicherePost ruft die Methode speicherePost auf, mit Hilfe derer man einen Post speichert
	 * @param p Post, der gespeichert werden soll
	 * @param postNumber Postnummer, mit derer ein Post gespeichert werden soll
     */

    public void speicherePost(Post p, int postNumber) 
    {
    	ps.speicherePost(p, postNumber);
    }
	
    /**
     * Die Methode getOwnpostlist ruft die Methode getOwnpostlist auf, mit Hilfe derer man eine Postliste eines User bekommt
	 * @param username die Posts dieses Users sollen zurueckgegeben werden 
	 * @return gibt die Posts des Users zurueck
	 */
    
    public ArrayList<Post> getOwnpostlist(String username) 
    {
    	return ps.getOwnpostlist(username);
    } 
    
    /**
     * Die Methode loeschePost ruft die Methode loeschePost auf, mit Hilfe derer man einen Post loescht
	 * @param postNumber Postnummer, mit dieser der Post soll geloescht werden
	 */   
    public void loeschePost(int postNumber) 
    {
    	ps.loeschePost(postNumber);
    }
    
    /**
     * Die Methode getPostNumbers ruft die Methode getPostNumbers auf, mit Hilfe derer man alle Postnummern des User bekommt
	 * @param username Name des Users, dessen Posts zurueckgegeben werden sollen
	 * @return gibt alle Nummern des Posts des Users zurueck
	 */       
    public ArrayList<Integer> getPostNumbers(String username)
    {
    	return ps.getPostNumbers(username);
    } 
   
    /**
     * Die Methode getPostbyNumber ruft die Methode getPostbyNumber auf, mit Hilfe derer man einen Post nach Postnummer bekommt
	 * @param postNumber Postnummer, Post mit dieser Nummer soll zurueckgegeben werden
	 * @return einen Post mit der angegebenen Postnummer
	 */ 
    public Post getPostbyNumber (int postNumber) 
    {
    	return ps.getPostbyNumber(postNumber);
    
    }    
    
    /**
     * Die Methode changeFlag ruft die Methode changeFlag auf, mit Hilfe derer man den flagged oder gemeldet Status eines Posts nach Postnummer setzt
	 * @param postNumber Postnummer, Status des Posts mit dieser Nummer wird gesetzt
	 * @param flag Status des Posts, 0 heisst unflagged oder ungemeldet, 1 heisst flagged oder gemeldet
	 */ 
    public void changeFlag(boolean flag, int postNumber)
    {
    	ps.changeFlag(flag, postNumber);
    }

    /**
     * Die Methode getFlaggedPostlist ruft die Methode getFlaggedPostlist auf, mit Hilfe derer man die flagged oder gemeldeten Posts zurueckbekommt
	 * @return die Liste der flagged oder gemeldeten Posts
	 */ 
    public ArrayList<Post> getFlaggedPostlist()
    {
    	return ps.getFlaggedPostlist();
    }
    
    /**
     * Die Methode loescheAllPosts ruft die Methode loescheAllPosts auf, mit Hilfe derer man alle Posts eines Users loescht
	 * @param username User, dessen Posts geloescht werden
	 */ 
    public void loescheAllPosts(String username)
    {
    	ps.loescheAllPosts(username);
    }
    
}
