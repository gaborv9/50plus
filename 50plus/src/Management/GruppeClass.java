package Management;

import java.io.Serializable;
import java.util.ArrayList;

import Personen.Person;
import Personen.User;

public class GruppeClass implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1110898186195512752L;
	
	private String name;
	private Person admin=new User();
	private ArrayList<Person> mitglied=new ArrayList<Person>();
	private int groupcounter;
	
	
	public GruppeClass (String name, Person admin, ArrayList<Person> mitglied)
	{
		this.groupcounter = 0; //spaeter wird er in Gruppe_Serialisierung gesetzt
		setName(name);
		setAdmin(admin);
		setMitglied(mitglied);
		
	}
	
	public int getGroupCounter()
	{
		return groupcounter;
	}

	public void setGroupCounter(int zahl)
	{
		groupcounter = zahl;
	} 
	
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
