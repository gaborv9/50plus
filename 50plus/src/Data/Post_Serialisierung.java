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


public class Post_Serialisierung 
{
	private String pfad;
	private ArrayList<Post> globalPostlist;

 
	
    public Post_Serialisierung()
    {
        String dir = System.getProperty("user.dir"); //Gibt Pfad von Eclipse
        this.pfad= dir + "/post.ser"; //das veraendern wir noch
        globalPostlist = new ArrayList<Post>();
    }
	
    
    public void speicherePost(Post p) 
    {
        
    	globalPostlist = getGlobalpostlist();
    	
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
           System.out.println("Konnte postlist nicht speichern. " + ex);
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
    			
    			catch(Exception e)
    			{
    				System.err.println("Konnte Streams nicht schliessen. " + e);
    			}
    		}
    		return globalPostlist;
        }
  	}
    
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
    
}

