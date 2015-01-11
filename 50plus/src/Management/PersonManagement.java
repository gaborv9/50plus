package Management;

import Data.Serialisierung;
import Personen.Person;

public class PersonManagement {
	private PersonDAO ser = new Serialisierung();

	// 1 2 oder 3..

	/**
	 * Gibt eine Person mithilfe der ID zurueck.
	 * 
	 * @param username
	 *            ID der Person
	 * @return Personen-Objekt
	 */
	public Person getPerson(String username) {
		return ser.getPersonbyid(username);
	}
	
	/**Setzt den Bilderlink einer Person
	 * 
	 * @param a Person-Objekt
	 * @param link Link der zum Bild fuehrt
	 * @return 1, falls erfolgreich
	 */

	public int changelink(Person a, String link) {
		Person temp = a;
		if (temp.setPicturelink(link) == 0) {
			return 0;
		} else {
			ser.loeschePerson(a);
			ser.speicherePerson(temp);
			return 1;
		}
	}
    /**Setzt die Rolle einer Person
     * 
     * @param a Person-Objekt
     * @param role die zu setzende Rolle
     * @return int 1, falls erfolgreich
     */
	public int changerole(Person a, int role) {
		Person temp = a;
		if (temp.setRole(role) == 0) {
			return 0;
		} else {
			ser.loeschePerson(a);
			ser.speicherePerson(temp);
			return 1;
		}
	}
	/**
	 * Person wird persistent angelegt.
	 * 
	 * @param a Personen-Objekt
	 * @return int 1, falls erfolgreich
	 */


	public int add(Person a) {
		if (ser.speicherePerson(a) == 0) {
			return 0; // username existiert bereits
		} else {
			return 1; // Hat geklappt
		}
	}

	/**
	 * Loescht eine Person
	 * 
	 * @param username ID der Person
	 * @return int 1, falls erfolgreich
	 */
	public int delete(String username) {
		if (ser.loeschePerson(ser.getPersonbyid(username)) == 0) {
			return 0; // username existiert nicht
		} else {
			return 1; // Hat geklappt
		}
	}

}
