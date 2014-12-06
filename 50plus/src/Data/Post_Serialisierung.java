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
	public String pfad;
	ArrayList<Post> globalpostlist= new ArrayList<Post>();

	
    public Post_Serialisierung()
    {
        String dir = System.getProperty("user.dir"); //Gibt Pfad von Eclipse
        this.pfad= dir + "/post.ser"; //das veraendern wir noch
    }
	
    
    public void speicherePost(Post p) {
        
    

    	
    	globalpostlist = getGlobalpostlist();
    	
    	globalpostlist.add(p);


    	
    	FileOutputStream out = null;
    	ObjectOutputStream output = null;
    	    	
        try 
        {
            //Ausgabestrom der zur Datei führt wird geöffnet
            out = new FileOutputStream(pfad);
            //Stream wird geoeffnet, um Objekte zu speichern 
            output = new ObjectOutputStream(out);
            //Objekt wird gespeichert
            output.writeObject(globalpostlist);
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
                System.out.println(ex.getMessage());
            }
        }
    	
    	
    	
		InputStream is = null;
		ObjectInputStream os = null;
		
		try
		{
			is = new FileInputStream(pfad);
			os = new ObjectInputStream(is);
			
			globalpostlist = (ArrayList<Post>) os.readObject();
		}
		
		catch(IOException e)
		{
			System.err.println(e);
		}
		
		catch(ClassNotFoundException e)
		{
			System.err.println(e);
		}
		
		finally
		{
			try
			{
				is.close();
				os.close();
				
			
				

				
				
			}
			
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return globalpostlist;
	}
    
    public ArrayList<Post> getOwnpostlist(String username)
   	{
    	ArrayList<Post> ownpostlist = new ArrayList<Post>();
    	
    	int j = 0;
    	Post temp_post;
    	
    	for(int i = 0; i < globalpostlist.size(); i++)
		{
			
			/*System.out.println(postlist.get(i).getUsername());
			System.out.println(postlist.get(i).getInhalt());
			System.out.println(postlist.get(i).getZeitpunkt());
			*/
			
			
			if(globalpostlist.get(i).getUsername().equals(username))
			{
				temp_post = globalpostlist.get(i);
				ownpostlist.add(j, temp_post);
				j++;
			}
		}
		
    	return ownpostlist;
   	}
    
}

