package Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Management.GruppeClass;
 
public class Gruppe_Serialisierung {
	
    public String pfad;
 
    
    public Gruppe_Serialisierung(){
        String dir = System.getProperty("user.dir");
        this.pfad= dir+"/gruppen.ser";
    }

    //Deserialisierung
   @SuppressWarnings("unchecked")
     public ArrayList<GruppeClass> getGruppenList() {
        ArrayList<GruppeClass> list = new ArrayList<GruppeClass>();
        File file = new File(pfad);
        if (!file.exists()){
            try {
                file.createNewFile();
                return list;
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

       

        if (file.length() ==0){

           return list;
        }
        try {
            //Eingabestrom der zur Datei fuehrt wird geoeffnet
            FileInputStream in = new FileInputStream(pfad);
            //Stream wird geoeffnet, um Objekte auszulesen
            ObjectInputStream input = new ObjectInputStream(in);
            //Einlesen
            list = (ArrayList<GruppeClass>) input.readObject();
            //Kanaele werden geschlossen
            input.close();
            in.close();
            return list;
        }
        catch (ClassNotFoundException ex) {
            System.out.println("Class not found: " +ex);
        }
        catch (IOException ex) {
           System.out.println("IOException " + ex.getMessage()); 
        }
        return list;
        
    }
   
   
  /**Gibt ein Person-Objekt anhand der ID zurueck.
   * 
   * @param ID ID eines Person-Objekts
   * @return Person-Objekt
   */
    //Die Methode getPersonbyId(… Id) soll ein Person-Objekt zurueckgeben (anhand von ID ). 
    //Falls das Person nicht gefunden wird, soll die Methode den Wert null zurueckgeben. 
 @SuppressWarnings("unchecked")  
    public GruppeClass getGruppebyName(String name) {
        for(GruppeClass objekt : getGruppenList()){
            if (objekt.getName().equals(name)){
                return objekt;
            }  
        }        
        return null; 
    }
 
 
    /**Person-Objekt wird persistent gespeichert
     * 
     * @param a Person-Objekt welches gespeichert werden soll
     */
    //Serialisierung  
   @SuppressWarnings("unchecked")
    public int speichereGruppe(GruppeClass a) {
        ArrayList<GruppeClass> list = getGruppenList();
        
        if (!list.isEmpty()){
            for(GruppeClass objekt : list){
                if (objekt.getName().equals(a.getName())){
                    return 0;
                }
            }
        }
        list.add(a);
       
        try {
            //Ausgabestrom der zur Datei führt wird geöffnet
            FileOutputStream out = new FileOutputStream(pfad);
            //Stream wird geöffnet, um Objekte zu speichern 
            ObjectOutputStream output = new ObjectOutputStream(out);
            //Objekt wird gespeichert
            output.writeObject(list);
            //Kanäle werden geschlossen
            output.close();
            out.close();
            return 1;
        }
        catch (IOException ex) {
           System.out.println("Konnte Datei nicht erstellen oder existiert bereits " + ex);
        }
        return 1;
    }
    /**Person-Objekt wird gelöscht.
     * 
     * @param a Zu löschendes Person-Objekt
     */
    /*Die Methode loeschePerson(Person) soll ein bestehendes Person von der persistenten Datenquelle löschen. 
    Falls es kein solches Person gibt, soll IllegalArgumentException (mit entsprechender Fehlermeldung) geworfen werden.*/
 @SuppressWarnings("unchecked")  
    public int loescheGruppe(GruppeClass a) {
        ArrayList<GruppeClass> list= getGruppenList();
        for(GruppeClass objekt : list){
            if (objekt.getName().equals(a.getName())){
                list.remove(objekt); 
                try {
                    FileOutputStream out = new FileOutputStream(pfad);
                    ObjectOutputStream output = new ObjectOutputStream(out);
               
                    output.writeObject(list);
                    output.close();
                    out.close();
                }
                catch (IOException ex) {
                   System.out.println("Konnte Datei nicht erstellen oder existiert bereits " + ex);
                } 
            return 1;
            }
        }  
        return 0;
    }
}
