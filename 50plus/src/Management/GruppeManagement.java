package Management;

import Data.Gruppe_Serialisierung;
import Management.GruppeClass;

import java.util.ArrayList;


public class GruppeManagement{

	Gruppe_Serialisierung group = new Gruppe_Serialisierung();
 

	/**speichert eine Gruppe in der Gruppe_Serialisierung.
     * 
     * @param gr Gruppe (GruppeClass-Objekt)
     */
    public void addGruppe(GruppeClass gr) 
    {
    	group.speichereGruppe(gr);	
    }

    /**Gibt alle eigenen Gruppen-Objekte einer Person zurueck.
     * 
     * @param username Username eines Person-Objekts
     * @return ArrayList mit Gruppen-Objekten
     */
    public ArrayList<GruppeClass> getOwnGruppelist(String username) 
    {
    	return group.getOwnGruppenlist(username);
    } 
    
}
