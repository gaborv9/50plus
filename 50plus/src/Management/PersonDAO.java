package Management;
import java.util.ArrayList;
import Personen.Person;
/**PersonDAO ist ein Interface und stellt Methoden zur Verwaltung von Personen zur Verfuegung.
 * 
 * @author master
 *
 */
public interface PersonDAO{
	 public ArrayList<Person> getPersonList();
	 public Person getPersonbyid(String ID);
	 public int speicherePerson(Person a);
	 public int loeschePerson(Person a);
}
