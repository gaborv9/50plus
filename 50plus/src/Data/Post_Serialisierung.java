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
	ArrayList<Post> postlist;
	
    public Post_Serialisierung()
    {
        String dir = System.getProperty("user.dir"); //Gibt Pfad von Eclipse
        this.pfad= dir + "/post.ser"; //das veraendern wir noch
    }
	
    
    public void speicherePost(Post p) {
        
    
        
    	
    	postlist = getPostlist();
    	
    	postlist.add(p);

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
    	
    	FileOutputStream out = null;
    	ObjectOutputStream output = null;
    	    	
        try 
        {
            //Ausgabestrom der zur Datei führt wird geöffnet
            out = new FileOutputStream(pfad);
            //Stream wird geoeffnet, um Objekte zu speichern 
            output = new ObjectOutputStream(out);
            //Objekt wird gespeichert
            output.writeObject(postlist);
        }
        catch (IOException ex) 
        {
           System.out.println("Konnte liste nicht speichern. " + ex);
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
    
    public ArrayList<Post> getPostlist()
	{

		InputStream is = null;
		ObjectInputStream os = null;
		
		try
		{
			is = new FileInputStream(pfad);
			os = new ObjectInputStream(is);
			
			postlist = (ArrayList<Post>) os.readObject();
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
		return postlist;
	}
    
    
    
}

