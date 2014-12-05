package Data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Personen.Post;

public class Post_Serialisierung 
{
	public String pfad;
	
    public Post_Serialisierung()
    {
        String dir = System.getProperty("user.dir"); //Gibt Pfad von Eclipse
        this.pfad= dir + "/post.ser"; //das veraendern wir noch
    }
	
    
    public int speicherePost(Post p) {
        
    	ArrayList<Post> liste = new ArrayList<Post>(); //spaeter aendern
        
    	liste.add(p);

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
            output.writeObject(liste);
                 
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
        return 1;
    }
    
    
    
}

