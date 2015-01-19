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
import Personen.Nachricht;


public class Nachrichten_Serialisierung implements NachrichtenDAO{
	private String pfad;
	private ArrayList<Nachricht> globallist;


	
    public Nachrichten_Serialisierung()
    {
        String dir = System.getProperty("user.dir");
        this.pfad= dir + "/nachricht.ser";
        globallist = new ArrayList<Nachricht>();
    }

    
    
    public void speichereNachricht(Nachricht n) 
    {
 
    	globallist = getAlleNachrichten();
    	globallist.add(n);

    	
    	FileOutputStream out = null;
    	ObjectOutputStream output = null;
    	    	
        try 
        {
            //Ausgabestrom der zur Datei fuehrt wird geoeffnet
            out = new FileOutputStream(pfad);
            //Stream wird geoeffnet, um Objekte zu speichern 
            output = new ObjectOutputStream(out);
            //Objekt wird gespeichert
            output.writeObject(globallist);
        }
        catch (IOException ex) 
        {
           System.out.println("Nachricht konnte nicht gespeichert werden. " + ex);
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
    
    
    public ArrayList<Nachricht> getAlleNachrichten(){
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
            return globallist;
         }
        
        else
        {
          	InputStream is = null;
    		ObjectInputStream ois = null;
    		
    		try
    		{
    			is = new FileInputStream(pfad);
    			ois = new ObjectInputStream(is);
    			
    			globallist = (ArrayList<Nachricht>) ois.readObject();
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
    				ois.close();
    				is.close();
    			}
    			
    			catch(IOException e)
    			{
    				System.err.println("Konnte Streams nicht schliessen. " + e);
    			}
    		}
    		return globallist;
        }
  	}
        

    
}
