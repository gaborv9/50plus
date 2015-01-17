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
 * Die Klasse Post_Serialisierung ist fuer die Serialisierung und Deserialisierung von 
 * Post-Objekten(Posts) zustaendig
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
	 * Die Methode getGlobalpostlist gibt alle Posts zurueck
	 * @return globalPostList - Liste aller Posts eines Users
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
	 * Die Methode loeschePost loescht einen Post
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
    
   
   public void changeFlag(boolean flag, int postNumber)
   {
	 	   
	   globalPostlist = getGlobalpostlist();
	   

	   
	   
	   for(int i = 0; i < globalPostlist.size(); i++)
	   {
			if(globalPostlist.get(i).getOwnPostcounter() == postNumber)
			{
				/*System.out.println("gefunden");
				System.out.println(globalPostlist.get(i).getOwnPostcounter());
				System.out.println(postNumber);
				*/
				Post p =  getPostbyNumber(postNumber);
				p.setFlagged(flag);
				
				loeschePost(postNumber);			
				speicherePost(p, postNumber);
					
			}
			/*else
			{
				System.out.println("Post nicht gefunden.");
			}
			*/
	   }
	   
	  
   }
   
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
   
   
   
   
   
   
}

