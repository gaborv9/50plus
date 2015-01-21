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

/**Die Klasse Gruppe_Serialisierung ist fuer die Serialisierung und Deserialisierung von 
 * GruppeClass-Objekten(Gruppen) zustaendig
 */
public class Gruppe_Serialisierung implements GruppeDAO
{
	private String pfad;
	private ArrayList<GruppeClass> gruppelist;

	
	/**Im Konstruktor wird der Pfad zur Datei wird gesetzt.
     */
    public Gruppe_Serialisierung()
    {
        String dir = System.getProperty("user.dir"); //Gibt Pfad von Eclipse
        this.pfad= dir + "/gruppen.ser"; 
        gruppelist = new ArrayList<GruppeClass>();
    }
	   
    
	/**Die Methode speichereGruppe speichert ein GruppeClass Objekt in Gruppen.ser
	   * 
	   * @param gr GruppeClass-Objekt
	   */	
    public void speichereGruppe(GruppeClass gr) 
    {
        
    	gruppelist = getGruppenlist();
    	
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
      
    
	/**Die Methode getGruppenlist gibt eine Liste aller Gruppen aus Gruppen.ser zurueck.
	   * 
	   * @return ArrayList mit Gruppen
	   */	
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
    				//os.close();
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
    
	/**Die Methode getOwnGruppenlist gibt eine Liste von allen Gruppen einer Person aus Gruppen.ser zurueck.
	   * 
	   * @param username Username eines Person-Objektes
	   * @return ArrayList mit Gruppen
	   */	
    public ArrayList<GruppeClass> getGruppenbyAdmin(String username)
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

		return ownGruppen;
   	}
    
    /**
     * Der Methode wird ein Gruppenname uebergeben und sie liefert das Gruppen-Objekt zurueck.
     * @param gn - gruppenname der uebergeben wird
     * @return das Gruppenobjekt
     */
    
    public GruppeClass getGruppebyName(String gn){
    	
    	gruppelist = getGruppenlist();
    	    	  	
    
    	for(GruppeClass temp:gruppelist){
    		if(temp.getName().equals(gn)){
    			return temp;
    		}
    	}
    	return null;
   	}
    

    /**
     * Der Methode wird ein Gruppenobjekt uebergeben und loescht dieses Objekt aus der Datei.
     * @param gr das Gruppenobjekt, welches geloescht werden soll
     */
    public void loescheGruppe(GruppeClass gr) 
    {
        
    	gruppelist = getGruppenlist();
    	gruppelist.remove(gr);

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
            	System.out.println("Die Streams konnten beim loeschen nicht geschlossen werden. " + e);
			}
         }
     }
    
    /**
     * Der Methode wird ein Username uebergeben und sie liefert alle Gruppen des Users zurueck.
     * @param username 
     * @return ownGruppen
     */
    public ArrayList<GruppeClass> getOwnGruppenlist(String username)
   	{
    	gruppelist = getGruppenlist();
    	ArrayList<GruppeClass> ownGruppen = new ArrayList<GruppeClass>();
    	    

    	for(GruppeClass temp:gruppelist){
    		for(Person p:temp.getMitglied()){
    			if(p.getID().equals(username)){
    				ownGruppen.add(temp);
    			}
    		}
    	}

		return ownGruppen;
   	}
    
}
