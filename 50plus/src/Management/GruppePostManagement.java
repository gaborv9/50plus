package Management;
import Data.GruppenPost_Serialisierung;
import Personen.GruppePost;
import java.util.ArrayList;

public class GruppePostManagement{

	GruppenPost_Serialisierung post = new GruppenPost_Serialisierung();
 

	/**speichert einen GruppenPost in der GruppenPost_Serialisierung.
     * 
     * @param p GruppeClass Objekt
     */
    public void addGruppenPost(GruppePost p) {
    	post.speichereGruppenPost(p);
    }

    /**Gibt alle eigenen Gruppen-Objekte einer Person zurueck.
     * 
     * @param gn - Username eines Person-Objekts
     * @return ArrayList mit Gruppen-Objekten
     */
    public ArrayList<GruppePost> getGroupPostlist(String gn) {
    	return post.getPostsbyGroupname(gn);
    }    
}
