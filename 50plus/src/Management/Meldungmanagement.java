package Management;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Data.Serialisierung;
import Personen.Person;

public class Meldungmanagement {

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
	
	public void unbefsperren(Person person,Person ich){
		Serialisierung a = new Serialisierung();
		ich.setFreunde(person, false);
		ich.remeingehendeAnfragen(person);
		ich.remgesendeteAnfragen(person);
		Person ichneu = ich;
		a.loeschePerson(ich);
		a.speicherePerson(ichneu);
		a.loeschePerson(person);
	}
	
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
