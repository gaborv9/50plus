package Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Management.GruppeClass;
import Personen.Person;
import Personen.User;


public class Gruppe_Serialisierung 
{
	private String pfad;
	private ArrayList<GruppeClass> gruppelist;

 
	
    public Gruppe_Serialisierung()
    {
        String dir = System.getProperty("user.dir"); //Gibt Pfad von Eclipse
        this.pfad= dir + "/gruppen.ser"; 
        gruppelist = new ArrayList<GruppeClass>();
    }
	
    
    
    
    
    public void speichereGruppe(GruppeClass gr) 
    {
        
    	gruppelist = getGruppenlist();
    	//gr.setGroupCounter(gruppelist.size() + 1);
    	
    	gruppelist.add(gr);

    	FileOutputStream out= null;
    	ObjectOutputStream output = null;
   
        try 
        {
            //Ausgabestrom der zur Datei fuehrt wird geoeffnet
            out = new FileOutputStream(pfad);
            //Stream wird geoeffnet, um Objekte zu speichern 
            output = new ObjectOutputStream(out);
            //Objekt wird gespeichert
            output.writeObject(gruppelist);
        }
        catch (IOException ex) 
        {
           System.out.println("Die Gruppenliste konnte nicht gespeichert werden. " + ex);
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
            	System.out.println("Die Streams konnten beim speichern nicht geschlossen werden. " + e);
			}
         }
     }
      
    
    
	@SuppressWarnings("unchecked")
	public ArrayList<GruppeClass> getGruppenlist()
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
            return gruppelist;
         }
        
        else
        {
          	InputStream is = null;
    		ObjectInputStream os = null;
    		
    		try
    		{
    			is = new FileInputStream(pfad);
    			os = new ObjectInputStream(is);
    			
    			gruppelist = (ArrayList<GruppeClass>) os.readObject();
    		}
    		
    		catch(IOException e)
    		{
    			System.err.println("IOException" + e);
    		}
    		
    		catch(ClassNotFoundException e)
    		{
    			System.err.println("gruppelist not found" + e);
    		}
    		
    		finally
    		{
    			try
    			{
    				os.close();  //manchmal Exception, warum?
    				is.close();
    			}
    			
    			catch(Exception e)
    			{
    				System.err.println("Die Streams konnten bei getGruppeList nicht geschlossen werden. " + e);
    			}
    		}
    		return gruppelist;
        }
  	}
    
	
	
    public ArrayList<GruppeClass> getOwnGruppenlist(String username)
   	{
    	gruppelist = getGruppenlist();
    	ArrayList<GruppeClass> ownGruppen = new ArrayList<GruppeClass>();
    	    	  	
    	Person admin=new User();
    	String name;
    
    	
    	for(GruppeClass temp:gruppelist){
    		admin=temp.getAdmin();
    		name=admin.getID();
    		if(name.equals(username)){
    			ownGruppen.add(temp);
    		}
    	}

    	/*for(int i = 0; i < gruppelist.size(); i++)
		{
    		
			if(gruppelist.get(i).getAdmin().getID().equals(username))
			{
				tempGroup = gruppelist.get(i);
				ownGruppen.add(j, tempGroup);
				j++;
			}
    		
		}*/
		return ownGruppen;
   	}
    
}
