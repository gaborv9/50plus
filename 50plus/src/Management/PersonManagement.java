package Management;

import Data.Serialisierung;
import Personen.Person;

public class PersonManagement {

    Serialisierung ser = new Serialisierung();

    //1 2 oder 3..
    
    /**Gibt eine Person mithilfe der ID zurueck.
     * 
     * @param username ID der Person
     * @return Personen-Objekt
     */
    public Person getPerson(String username){
    	return ser.getPersonbyid(username);
    }
    
    /**Person wird persistent angelegt.
     * 
     * @param a Personen-Objekt
     * @return int
     */

    public int add(Person a) {
        if (ser.speicherePerson(a) == 0) {
            return 0; //username existiert bereits
        } else {
            return 1; //Hat geklappt
        }
    }
/**Loescht eine Person
 * 
 * @param username ID der Person
 * @return int 
 */
    public int delete(String username) {
        if (ser.loeschePerson(ser.getPersonbyid(username)) == 0) {
            return 0; //username existiert bereits
        } else {
            return 1; //Hat geklappt
        }
    }
    

}
