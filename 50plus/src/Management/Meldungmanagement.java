package Management;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Data.Serialisierung;
import Personen.Person;
/**
 * 
 * @author Lechl
 *
 */
public class Meldungmanagement {
	/**
	 * 
	 * @param person - dies ist die Person, die gemeldet werden soll
	 * @param ich - dies ist die Person die jene Person melden will
	 */
	public void meldungmachen(Person person, Person ich){
		Serialisierung a = new Serialisierung();
		int anzmeldung = 0;
		ArrayList<Person> meldung = person.getgemeldetvon();
		boolean pruef = false;
		for(Person test: meldung){
			if(test.getID().equals(ich.getID())) pruef=true;
		}
		if(pruef==false){
			meldung.add(ich);
		}
		person.setgemeldetvon(meldung);
		anzmeldung=person.getgemeldetvon().size();
		if(pruef==false){
			anzmeldung++;
		}
		person.setmeldunganz(anzmeldung);
		Person personneu = person;
		a.loeschePerson(person);
		a.speicherePerson(personneu);
	}
	/**
	 * 
	 * @param person - dies ist jene Person die befristet gesperrt werden soll
	 * @param zeit - dies ist jene Anzahl von tagen, die jene Person gesperrt werden soll
	 */
	public void befsperren(Person person, int zeit){
		person.setsperrdatum(zeit);
		PersonDAO a = new Serialisierung();
		Person personneu = person;
		ArrayList<Person> meld = new ArrayList<Person>();
		personneu.setgemeldetvon(meld);
		personneu.setmeldunganz(0);
		a.loeschePerson(person);
		a.speicherePerson(personneu);
	}
	
	/**
	 * 
	 * @param person - Person die geloescht werden soll
	 * @param list - Personen, deren Freundschaftsanfragen auch geloescht werden sollen
	 */
	public void unbefsperren(Person person,ArrayList<Person> list){
		Serialisierung a = new Serialisierung();
		Person testneu;
		for(Person test: list){
			if(!(test.getID().equals(person.getID()))){
				test.setFreunde(person, false);
				test.remeingehendeAnfragen(person);
				test.remgesendeteAnfragen(person);
				testneu = test;
				a.loeschePerson(test);
				a.speicherePerson(testneu);
			}
		}
		a.loeschePerson(person);
	}
	
	/**
	 * 
	 * @return sperrlisteneu - liste welche alle gemeldeten Personen und deren Melder anzeigt (hier groesser 0)
	 */
	public ArrayList<Person> sperrliste(){
		Serialisierung a = new Serialisierung();
		ArrayList<Person> sperrlisteall = new ArrayList<Person>();
		sperrlisteall=a.getPersonList();
		ArrayList<Person> sperrlisteneu = new ArrayList<Person>();
		for(Person test:sperrlisteall){
			if(test.getmeldunganz()>0) sperrlisteneu.add(test);
		}
		return sperrlisteneu;
	}
	
}
