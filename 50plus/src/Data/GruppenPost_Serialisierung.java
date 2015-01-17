package Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Personen.GruppePost;

/**
 * Die Klasse GruppenPost_Serialisierung ist fuer die Serialisierung und Deserialisierung von 
 * GruppenPost-Objekten zustaendig
 */
public class GruppenPost_Serialisierung implements GruppePostDAO{
	private String pfad;
	private ArrayList<GruppePost> globalPostlist;

	/**
	 * default Konstruktor der Klasse Post_Serialisierung
	 * der Pfad zur Datei wird gesetzt, wohin die Posts gespeichert werden
	 */
	
    public GruppenPost_Serialisierung()
    {
        String dir = System.getProperty("user.dir");
        this.pfad= dir + "/gruppenpost.ser";
        globalPostlist = new ArrayList<GruppePost>();
    }
	
	/**
	 * Die Methode speicherePost speichert ein Post Objekt in post.ser
	 * @param p Post, der gespeichert wird
	 */  
    public void speichereGruppenPost(GruppePost p) 
    {
 
    	globalPostlist = getAlleGruppen();
    	globalPostlist.add(p);

    	
    	FileOutputStream out = null;
    	ObjectOutputStream output = null;
    	    	
        try 
        {
            //Ausgabestrom der zur Datei fuehrt wird geoeffnet
            out = new FileOutputStream(pfad);
            //Stream wird geoeffnet, um Objekte zu speichern 
            output = new ObjectOutputStream(out);
            //Objekt wird gespeichert
            output.writeObject(globalPostlist);
        }
        catch (IOException ex) 
        {
           System.out.println("Post konnte nicht gespeichert werden. " + ex);
        }
        finally
        {
            try 
            {
				output.close();
				out.close();
			} 
            catch (IOException e) 
            {
            	System.out.println("Streams konnten nicht geschlossen werden. " + e);
			}
         }
     }
    
	/**
	 * Die Methode getGlobalpostlist gibt alle Posts zurueck
	 * @return globalPostList - Liste aller Posts eines Users
	 */         
	public ArrayList<GruppePost> getAlleGruppen(){
    	File file = new File(pfad);
    	
        if (!file.exists())
        {
            try 
            {
                file.createNewFile();
                System.out.println("getAlleGruppen1");
            } 
            catch (IOException ex) 
            {
            	System.out.println("IO Exception" + ex);
            	System.out.println("getAlleGruppen2");
            }
            return globalPostlist;
         }
        
        else
        {
          	InputStream is = null;
    		ObjectInputStream ois = null;
    		
    		try
    		{
    			is = new FileInputStream(pfad);
    			ois = new ObjectInputStream(is);
    			
    			globalPostlist = (ArrayList<GruppePost>) ois.readObject();
    		}
    		
    		catch(IOException e)
    		{
    			System.err.println("IOException" + e);
    		}
    		
    		catch(ClassNotFoundException e)
    		{
    			System.err.println("globalPostlist not found" + e);
    		}
    		
    		finally
    		{
    			try
    			{
    				//ois.close();
    				is.close();
    			}
    			
    			catch(IOException e)
    			{
    				System.err.println("Konnte Streams nicht schliessen. " + e);
    			}
    		}
    		return globalPostlist;
        }
  	}
    
	/**
	 * Die Methode getOwnpostlist gibt alle Posts eines Users zurueck
	 * @param username User, dessen Posts zurueckgegeben werden
	 * @return alle Posts eines Users
	 */ 	
	
    public ArrayList<GruppePost> getPostsbyGroupname(String gruppenname){
    	
    	globalPostlist = getAlleGruppen();
    	ArrayList<GruppePost> groupPostlist = new ArrayList<GruppePost>();
    	    	  	
    	for(GruppePost objekt: globalPostlist){
    		if(objekt.getPostGruppenname().equals(gruppenname)){
    			groupPostlist.add(objekt);
    		}
    	}
    	return groupPostlist;
   	}   
}
