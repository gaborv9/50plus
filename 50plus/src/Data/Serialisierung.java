
package Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Personen.Person;
 
public class Serialisierung {
    public String pfad;
    /**Pfad zur Datei wird gesetzt.
     * 
     * @param pfad Pfad wird übergeben
     */
 
    public Serialisierung(){
        String dir = System.getProperty("user.dir"); //Gibt Pfad von tomcat/bin zurueck
        this.pfad= dir+"/db.ser";
    }
    /**Gibt eine ArrayListe mit allen gespeicherten Personen zurueck.
     * 
     * @return ArrayListe mit Personen
     */
    //Deserialisierung
   @SuppressWarnings("unchecked")
     public ArrayList<Person> getPersonList() {
        ArrayList<Person> liste = new ArrayList<Person>();
        File file = new File(pfad);
        if (!file.exists()){
            try {
                file.createNewFile();
                return liste;
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        if (file.length() ==0){
           return liste;
        }
        try {
            //Eingabestrom der zur Datei führt wird geöffnet
            FileInputStream in = new FileInputStream(pfad);
            //Stream wird geöffnet, um Objekte auszulesen
            ObjectInputStream input = new ObjectInputStream(in);
            //Einlesen
            liste = (ArrayList<Person>) input.readObject();
            //Kanäle werden geschlossen
            input.close();
            in.close();
            return liste;
        }
        catch (ClassNotFoundException ex) {
            System.out.println("Class not found: " +ex);
        }
        catch (IOException ex) {
           System.out.println("IOException " + ex.getMessage()); 
        }
        return liste;
       
    }
  /**Gibt ein Person-Objekt anhand der ID zurueck.
   * 
   * @param ID ID eines Person-Objekts
   * @return Person-Objekt
   */
    //Die Methode getPersonbyId(… Id) soll ein Person-Objekt zurueckgeben (anhand von ID ). 
    //Falls das Person nicht gefunden wird, soll die Methode den Wert null zurueckgeben. 
 @SuppressWarnings("unchecked")  
    public Person getPersonbyid(String ID) {
        for(Person objekt : getPersonList()){
            if (objekt.getID().equals(ID)){
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
    public int speicherePerson(Person a) {
        ArrayList<Person> liste = getPersonList();
        
        if (!liste.isEmpty()){
            for(Person objekt : liste){
                if (objekt.getID().equals(a.getID())){
                    return 0;
                }
            }
        }
        liste.add(a);
       
        try {
            //Ausgabestrom der zur Datei führt wird geöffnet
            FileOutputStream out = new FileOutputStream(pfad);
            //Stream wird geöffnet, um Objekte zu speichern 
            ObjectOutputStream output = new ObjectOutputStream(out);
            //Objekt wird gespeichert
            output.writeObject(liste);
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
    public int loeschePerson(Person a) {
        ArrayList<Person> liste= getPersonList();
        for(Person objekt : liste){
            if (objekt.getID().equals(a.getID())){
                liste.remove(objekt); 
                try {
                    FileOutputStream out = new FileOutputStream(pfad);
                    ObjectOutputStream output = new ObjectOutputStream(out);
               
                    output.writeObject(liste);
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
 