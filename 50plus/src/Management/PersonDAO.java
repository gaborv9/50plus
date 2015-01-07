package Management;
import java.util.ArrayList;
import Personen.Person;

public interface PersonDAO{
	 public ArrayList<Person> getPersonList();
	 public Person getPersonbyid(String ID);
	 public int speicherePerson(Person a);
	 public int loeschePerson(Person a);
}
