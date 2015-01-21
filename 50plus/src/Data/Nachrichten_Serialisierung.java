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

/**
 * Die Klasse Nachrichten_Serialisierung ist fuer die Serialisierung und Deserialisierung von 
 * Nachricht-Objekten(Gruppen) zustaendig
 *  *
 */
public class Nachrichten_Serialisierung implements NachrichtenDAO{
	private String pfad;
	private ArrayList<Nachricht> globallist;


	/**
	 * Konstruktor setzt Pfad zur Datei
	 */
    public Nachrichten_Serialisierung()
    {
        String dir = System.getProperty("user.dir");
        this.pfad= dir + "/nachricht.ser";
        globallist = new ArrayList<Nachricht>();
    }

    
    /**
     * Die Methode speichere Nachricht speichert Nachricht Objekte in der Datei nachricht.ser
     * @param n - Nachricht die gespeichert wird
     */
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
    
    /**
     * Die Methode getAlleNachrichten liefert eine Liste mit allen Nachrichten, die in der Datei gespeichert sind.
     * @return - alle Nachrichten
     */
    public ArrayList<Nachricht> getAlleNachrichten(){
    	File file = new File(pfad);
    	
        if (!file.exists()){
            try{
                file.createNewFile();
            } 
            catch (IOException ex){
            	System.out.println("IO Exception" + ex);
            }
            return globallist;
         }
        
        else{
          	InputStream is = null;
    		ObjectInputStream ois = null;
    		
    		try{
    			is = new FileInputStream(pfad);
    			ois = new ObjectInputStream(is);
    			globallist = (ArrayList<Nachricht>) ois.readObject();
    		}
    		catch(IOException e){
    			System.err.println("IOException" + e);
    		}
    		catch(ClassNotFoundException e){
    			System.err.println("globalPostlist not found" + e);
    		}
    		finally{
    			try{
    				//ois.close();
    				is.close();
    			}
    			
    			catch(IOException e){
    				System.err.println("Konnte Streams nicht schliessen. " + e);
    			}
    		}
    		return globallist;
        }
  	}
    /**
     * Die Methode getNachrichtenbySender gibt alle Nachrichten zurueck, die den angegebenen Username als Sender aufweisen.    
     * @param sender - der Sender der Nachricht
     * @return - Nachricht des Senders
     */
    public ArrayList<Nachricht> getNachrichtenbySender(String sender){
    	
    	globallist=getAlleNachrichten();
    	ArrayList<Nachricht> senderlist=new ArrayList<Nachricht>();
    	
    	for(Nachricht temp: globallist){
    		if(temp.getNachrichtSender().equals(sender)){
    			senderlist.add(temp);
    		}
    	}
    	return senderlist;
    	
    }
    
 /**
  * Die Methode getNachrichtenbyEmpfaeinger gibt alle Nachrichten zurueck, die den angegebenen Username als Empfaenger aufweisen.   
  * @param empfaenger - Empfaenger der Nachricht
  * @return Nachricht des Empfaengers
  */
public ArrayList<Nachricht> getNachrichtenbyEmpfaenger(String empfaenger){
    	
    	globallist=getAlleNachrichten();
    	ArrayList<Nachricht> empflist=new ArrayList<Nachricht>();
    	
    	for(Nachricht temp: globallist){
    		if(temp.getNachrichtEmpfaenger().equals(empfaenger)){
    			empflist.add(temp);
    		}
    	}
    	return empflist;
    	
    }



}
