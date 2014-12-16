package Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Personen.Post;

/**
 * Die Klasse Post_Serialisierung ist für die Serialisierung und Deserialisierung von 
 * Post-Objekten(Posts) zuständig
 */
public class Post_Serialisierung 
{
	private String pfad;
	private ArrayList<Post> globalPostlist;

	/**
	 * default Konstruktor der Klasse Post_Serialisierung
	 * der Pfad zur Datei wird gesetzt, wohin die Posts gespeichert werden
	 */
	
    public Post_Serialisierung()
    {
        String dir = System.getProperty("user.dir"); //Gibt Pfad von Eclipse
        this.pfad= dir + "/post.ser";
        globalPostlist = new ArrayList<Post>();
    }
	
	/**
	 * Die Methode speicherePost speichert ein Post Objekt in post.ser
	 * @param p Post, der gespeichert wird
	 * @throws IOException ex wenn globalPostlist nicht gespeichert werden kann
	 * @throws IOException e wenn Streams nicht geschlossen werden können
	 */  
    public void speicherePost(Post p) 
    {
        
    	globalPostlist = getGlobalpostlist();
    	
    	p.setOwnPostcounter(getMaxPostNumber() + 1);
 
    	/*
    	System.out.println("Alle Counter");
    	for(int i = 0; i < globalPostlist.size(); i++)
		{
    		System.out.println(globalPostlist.get(i).getOwnPostcounter());
		}
    	*/
    	
    	globalPostlist.add(p);

    	FileOutputStream out = null;
    	ObjectOutputStream output = null;
    	    	
        try 
        {
            //Ausgabestrom der zur Datei fÃ¼hrt wird geÃ¶ffnet
            out = new FileOutputStream(pfad);
            //Stream wird geoeffnet, um Objekte zu speichern 
            output = new ObjectOutputStream(out);
            //Objekt wird gespeichert
            output.writeObject(globalPostlist);
        }
        catch (IOException ex) 
        {
           System.out.println("Konnte globalPostlist nicht speichern. " + ex);
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
            	System.out.println("Konnte Streams nicht schliessen. " + e);
			}
         }
     }
    
	/**
	 * Die Methode getGlobalpostlist gibt alle Posts zurueck
	 * @throws IOException ex die Daten konnte nicht erstellt werden
	 * @throws IOException e Streams konnten nicht geschlossen werden 
	 * @throws ClassNotFoundException Klasse Postlist wurden nicht gefunden
	 * @throws IOException e Streams konnten nicht geschlossen werden 
	 * @return Liste aller Posts
	 */         
	public ArrayList<Post> getGlobalpostlist()
	{
    	File file = new File(pfad);
    	
        if (!file.exists())
        {
            try 
            {
                file.createNewFile();
            } 
            catch (IOException ex) 
            {
            	System.out.println("IO Exception" + ex);
            }
            return globalPostlist;
         }
        
        else
        {
          	InputStream is = null;
    		ObjectInputStream os = null;
    		
    		try
    		{
    			is = new FileInputStream(pfad);
    			os = new ObjectInputStream(is);
    			
    			globalPostlist = (ArrayList<Post>) os.readObject();
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
    				//os.close();  manchmal Exception, warum?
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
	
    public ArrayList<Post> getOwnpostlist(String username)
   	{
    	globalPostlist = getGlobalpostlist();
    	ArrayList<Post> ownPostlist = new ArrayList<Post>();
    	    	  	
    	int j = 0;
    	Post temp_post;
    	
    	/*Gabor: Notiz fuer mich -  warum ist das so nicht richtig?
    	
    	if (globalPostlist.size() == 0)
    	{
        	return null;
        }
        */
    	    	
    	for(int i = 0; i < globalPostlist.size(); i++)
		{
			if(globalPostlist.get(i).getUsername().equals(username))
			{
				temp_post = globalPostlist.get(i);
				ownPostlist.add(j, temp_post);
				j++;
			}
		}
		return ownPostlist;
   	}
    
	/**
	 * Die Methode loeschePost loescht einen Post
	 * @param posNumber Post mit dieser Nummer wird geloescht
	 * @throws IOException ex globalPostlist konnte nicht gespeichert werden
	 * @throws IOException e Streams konnten nicht geschlossen werden 
	 */    
    public void loeschePost(int postNumber) 
    {
        
    	globalPostlist = getGlobalpostlist();
    	//p.setOwnPostcounter(globalPostlist.size() + 1);

    	int i;
    	
    	for(i = 0; i < globalPostlist.size(); i++)
		{
			if(globalPostlist.get(i).getOwnPostcounter() == postNumber)
			{
				globalPostlist.remove(globalPostlist.get(i));
			}
	
		}
    	    	
    	FileOutputStream out = null;
    	ObjectOutputStream output = null;
    	    	
        try 
        {
            //Ausgabestrom der zur Datei fÃ¼hrt wird geÃ¶ffnet
            out = new FileOutputStream(pfad);
            //Stream wird geoeffnet, um Objekte zu speichern 
            output = new ObjectOutputStream(out);
            //Objekt wird gespeichert
            output.writeObject(globalPostlist);
        }
        catch (IOException ex) 
        {
           System.out.println("Konnte globalPostlist nicht speichern. " + ex);
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
            	System.out.println("Konnte Streams nicht schliessen. " + e);
			}
         }
     }
    
	/**
	 * Die Methode getPostNumbers gibt die Anzahl der Posts eines Users zurueck
	 * @param username User, dessen Posts zurueckgegeben werden
	 * @return die Anzahl der Posts eines Users
	 */      
    public ArrayList<Integer> getPostNumbers(String username)
   	{
 
    	ArrayList<Post> ownPostlist = getOwnpostlist(username);
    	ArrayList<Integer> ownPostNumbers = new ArrayList<Integer>();
    
    	int tempCounter;
    	int j = 0;
    	
    	
    	for(int i = 0; i < globalPostlist.size(); i++)
		{
			if(globalPostlist.get(i).getUsername().equals(username))
			{
				tempCounter = globalPostlist.get(i).getOwnPostcounter();
				ownPostNumbers.add(j, tempCounter);
				j++;
			}
		}
		return ownPostNumbers;
   	}
	/**
	 * Die Methode getMaxPostNumber gibt die hoechste Nummer der Posts, die je gepostet wurden
	 * @return die hoechste Nummer der Posts
	 */     
    private int getMaxPostNumber()
   	{
    	int max = 0;
    	
    	for(int i = 0; i < globalPostlist.size(); i++)
		{
			if(globalPostlist.get(i).getOwnPostcounter() > max)
			{
				max = globalPostlist.get(i).getOwnPostcounter();
			}
		}
		return max;
   	}    
    
    
    
   /* public Post getPost(int postNumber)
   	{
    	globalPostlist = getGlobalpostlist();
      	    	  	
   
    	Post p;
        	    	
    	for(int i = 0; i < globalPostlist.size(); i++)
		{
			if(globalPostlist.get(i).getOwnPostcounter() == postNumber)
			{
				p = globalPostlist.get(i);
				return p;
			}
		}
    	System.out.println("ich habe den Post nicht gefunden");
		return null;
   	}   
    */
}

