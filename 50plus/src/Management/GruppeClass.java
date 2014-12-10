package Management;

import java.util.ArrayList;
import Data.Serialisierung;
import Personen.Person;

public class GruppeClass {
	
	private String name;
	private Person admin;
	private ArrayList<Person> mitglied;	
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String n){
		this.name=n;
	}
	
	public Person getAdmin(){
		return this.admin;
	}
	
	public void setAdmin(Person p){
		this.admin=p;
	}
	
	public ArrayList<Person> getMitglied(){
		return this.mitglied;
	}
	
	public void setMitglied(ArrayList<Person> p){
		this.mitglied=p;
	}
	}
