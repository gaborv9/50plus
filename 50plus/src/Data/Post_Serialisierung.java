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
import Management.PinnwandDAO;

/**
 * Die Klasse Post_Serialisierung ist fuer die Serialisierung und Deserialisierung von 
 * Post-Objekten(Posts) zustaendig
 */
public class Post_Serialisierung implements PinnwandDAO
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
	 * Die Methode speicherePost speichert ein Post Objekt in ArrayList globalPostlist, die in der Methode setGlobalpostlist in post.ser ausschrieben werden soll
	 * @param p Post, der gespeichert wird
	 * @param postNumber als wievielte der Post gepostet wurde, entweder neue Nummber oder bei vorhandenem Post seine eigene Nummer
	 */  
    public void speicherePost(Post p, int postNumber) 
    {
              	
    	globalPostlist = getGlobalpostlist();
    	
    	if (postNumber == 0)
    	{
    		p.setOwnPostcounter(getMaxPostNumber() + 1);
    	}
    	else
    	{
    		p.setOwnPostcounter(postNumber);
    	}
     	
    	globalPostlist.add(p);
    	setGlobalpostlist(globalPostlist);
    	
     }
    
	/**
	 * Die Methode getGlobalpostlist gibt alle Posts zurueck, die in der Datei post.ser gespeichert werden
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
	 * Die Methode setGlobalpostlist speichert persistent die Liste aller Posts in post.ser
	 * @param globalPostlist Liste aller Posts, die gespeichert werden soll
	 */   
	private void setGlobalpostlist(ArrayList<Post> globalPostlist)
	{
				
		FileOutputStream out = null;
    	ObjectOutputStream output = null;
    	    	
        try 
        {
            //Ausgabestrom der zur Datei führt wird geöffnet
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
	 * Die Methode getOwnpostlist gibt alle Posts eines Users zurueck
	 * @param username User, dessen Posts zurueckgegeben werden sollen
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
			if(globalPostlist.get(i).getPinnwandOwner().equals(username))
			{
				temp_post = globalPostlist.get(i);
				ownPostlist.add(j, temp_post);
				j++;
			}
		}
		return ownPostlist;
   	}
    
	/**
	 * Die Methode loeschePost loescht einen Post aus einer List, ArrayList globalPostlist, die in der Methode setGlobalpostlist in post.ser ausschrieben werden soll
	 * @param postNumber Post mit dieser Nummer wird geloescht
	 *
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
    	    	
    	setGlobalpostlist(globalPostlist);
     }
    
	/**
	 * Die Methode getPostNumbers gibt alle Postnummern eines Users zurueck
	 * @param username User, dessen Postnummern zurueckgegeben werden sollen
	 * @return die Postnummern des Users
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
    public int getMaxPostNumber()
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
    
    
	/**
	 * Die Methode getPostbyNumber gibt einen Post nach dessen Nummer zurueck
	 * @param postNumber Postnummer, nach derer den Post zurueckgegeben wird
	 * @return den Post mit der angegebenen Postnummer
	 */ 
   public Post getPostbyNumber(int postNumber)
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
    
	/**
	 * Die Methode changeFlag setzt den Status eines Posts auf flagged oder unflagged nach Postnummer
	 * @param flag Zustand des Posts, 0 heisst unflagged oder ungemeldet, 1 heisst flagged oder gemeldet
	 * @param postNumber Postnummer, nach derer den flagged oder gemeldeten Status eines Posts gesetzt werden soll
	 */   
   public void changeFlag(boolean flag, int postNumber)
   {
	   globalPostlist = getGlobalpostlist();
		   
	   for(int i = 0; i < globalPostlist.size(); i++)
	   {
			if(globalPostlist.get(i).getOwnPostcounter() == postNumber)
			{
				Post p =  getPostbyNumber(postNumber);
				p.setFlagged(flag);
				
				loeschePost(postNumber);			
				speicherePost(p, postNumber);
			}
	   }
   }
 
	/**
	 * Die Methode getFlaggedPostlist gibt alle Posts mit flagged oder gemeldet Status
	 * @return Liste von Posts mit flagged Status
	 */ 
   
   public ArrayList<Post> getFlaggedPostlist()
  	{
	   
	   	globalPostlist = getGlobalpostlist();
	   	ArrayList<Post> flaggedPostlist = new ArrayList<Post>();
	   	    	  	
	   	int j = 0;
	   	Post temp_post;
	   	    	
	   	for(int i = 0; i < globalPostlist.size(); i++)
			{
				if(globalPostlist.get(i).getFlagged() == true) //works because they are primitives (boolean) and not objects (Boolean)
				{
					temp_post = globalPostlist.get(i);
					flaggedPostlist.add(j, temp_post);
					j++;
				}
			}
		return flaggedPostlist;
  	}
   
	/**
	 * Die Methode loescheAllPosts loescht alle Posts eines Users
	 * @param username User, dessen Posts geloescht werden sollen
	 */ 
   public void loescheAllPosts(String username)
  	{
	   	globalPostlist = getGlobalpostlist();
		
	   	for(int i = 0; i < globalPostlist.size(); i++)
		{
	   		
			if(globalPostlist.get(i).getUsername().equals(username) || globalPostlist.get(i).getPinnwandOwner().equals(username))
			{
			
				
				globalPostlist.remove(globalPostlist.get(i));
				i--; //wenn if true ist, dann globalPostlist.remove(globalPostlist.get(i), danach globalPostlist.size()=size von vorigeren globalPostlist - 1, so waere nur jeden 2. Post geloescht 
				
			}
		}
	   	setGlobalpostlist(globalPostlist);
 	}
  
}

