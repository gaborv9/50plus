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
    	System.out.println("nem talaltam a postot");
		return null;
   	}   
    */
}

