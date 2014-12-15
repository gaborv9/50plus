package Management;

import java.io.Serializable;
import java.util.ArrayList;

import Personen.Person;
import Personen.User;

/**Die Klasse GruppeClass erstellt Gruppen Objekte
 */
public class GruppeClass implements Serializable{
	
	
	private static final long serialVersionUID = -1110898186195512752L;
	
	private String name;
	private Person admin=new User();
	private ArrayList<Person> mitglied=new ArrayList<Person>();
	private int groupcounter;
	
	/**Der Konstruktor ruft die setter Methoden auf um Werte zu setzen.
	 * @param name, admin, mitglied  Name der Gruppe, ein Person-Objekt, ArrayList mit PersonObjekten
	 */
	public GruppeClass (String name, Person admin, ArrayList<Person> mitglied)
	{
		setName(name);
		setAdmin(admin);
		setMitglied(mitglied);	
	}
	
	/**Gibt den Namen der Gruppe zurueck
	 * 
	 * @return String
	 */
	public String getName(){
		return this.name;
	}
	
	/**setzt name mit übergebenen String
	 * 
	 * @param n String mit dem Namen der Gruppe
	 */
	public void setName(String n){
		this.name=n;
	}
	
	/**Gibt ein Person-Objekt zurueck
	 * 
	 * @return Person
	 */
	public Person getAdmin(){
		return this.admin;
	}
	
	/**setzt admin mit Person-Objekt
	 * 
	 * @param p Person-Objekte
	 */
	public void setAdmin(Person p){
		this.admin=p;
	}
	
	/**Gibt eine ArrayList mit PersonObjekten zurueck
	 * 
	 * @return String
	 */
	public ArrayList<Person> getMitglied(){
		return this.mitglied;
	}
	
	/**setzt mitglied mit ArrayList von Person-Objekten
	 * 
	 * @param p ArrayList mit Person-Objekten
	 */
	public void setMitglied(ArrayList<Person> p){
		this.mitglied=p;
	}
	}
