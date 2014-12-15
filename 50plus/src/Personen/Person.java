package Personen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public abstract class Person implements Serializable {
	public int role;
	public int alter;
	public String vorname;
	public String nachname;
	public String id; // id=username, diese sind eindeutig
	public String pw;
	public GregorianCalendar datum;
	public ArrayList<Person> freunde;
	
	/**
	 * 
	 * @return freunde
	 * Alle Freunde der Person zurueckgeben
	 */
	public ArrayList<Person> getFreunde(){
		return freunde;
	}
	
	/**
	 * 
	 * @param zufuegen
	 * Person, welche als Freund hinzugefuegt werden soll
	 * @return 1 oder 0
	 * 1 = Freund wurde erfolgreich hinzugefuegt
	 * 0 = Freund bereits enthalten
	 */
	
	public void setArrayList(ArrayList<Person>a){
		this.freunde = a;
	}
	
	public int setFreunde(Person zufuegen){
		if(!(freunde==null)){
		for(Person test: freunde){
			if(test.equals(zufuegen)) return 0;
		}
		
		freunde.add(zufuegen);
		return 1;
		}
		ArrayList<Person> test = new ArrayList<Person>();
		test.add(zufuegen);
		freunde = test;
		return 0;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(int role) {
		this.role = role;
	}

	/**
	 * @return the role
	 */
	public int getRole() {
		return role;
	}

	/**
	 * @return the alter
	 */
	public int getAlter() {
		return alter;
	}

	/**
	 * @param alter
	 *            the alter to set
	 */
	public void setAlter(int alter) {
		this.alter = alter;
	}

	/**
	 * @return the vorname
	 */
	public String getVorname() {
		return vorname;
	}

	/**
	 * @param vorname
	 *            the vorname to set
	 */
	public int setVorname(String vorname) {
		if ((vorname.length() > 25) || (vorname.isEmpty() == true)) {
			return 0;
		}
		this.vorname = vorname;
		return 1;
	}

	/**
	 * @return the nachname
	 */
	public String getNachname() {
		return nachname;
	}

	/**
	 * @param nachname
	 *            the nachname to set
	 */
	public int setNachname(String nachname) {
		if ((nachname.length() > 25) || (nachname.isEmpty() == true)) {
			return 0;
		}

		this.nachname = nachname;
		return 1;
	}

	/**
	 * @return the id
	 */
	public String getID() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public int setID(String id) {
		if ((id.length() > 25) || (id.isEmpty() == true)) {
			return 0;
		}
		this.id = id;
		return 1;
	}

	/**
	 * @return the datum
	 */
	public GregorianCalendar getDatum() {
		return datum;
	}

	/**
	 * @param datum
	 *            the datum to set
	 */
	public int setDatum(String year, String month, String day) {
		try {
			int dayi = Integer.parseInt(day);
			int monthi = Integer.parseInt(month);
			int yeari = Integer.parseInt(year);
			
			GregorianCalendar now = new GregorianCalendar();
			GregorianCalendar gebdate = new GregorianCalendar();
			gebdate.setLenient(false);
			gebdate.set(GregorianCalendar.YEAR, yeari);
			gebdate.set(GregorianCalendar.MONTH, monthi-1); //Weil die doofen Programmierer beim Monat eine Ausnahme machen und bei 0 anfangen.. Hier die Ausgabe noch checken.
			gebdate.set(GregorianCalendar.DAY_OF_MONTH, dayi);
			if ((now.get(GregorianCalendar.YEAR) - gebdate.get(GregorianCalendar.YEAR)) < 49) {
				return 0;
			}
			else{

			this.datum = gebdate;
			return 1;
			}

		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * @return the pw
	 */
	public String getPW() {
		return pw;
	}

	/**
	 * @param pw
	 *            the pw to set
	 */
	public int setPW(String pw) {
    	if ((id.length() >25) || (id.isEmpty()==true) || (pw.length() < 6)){
    		return 0;
    	}
        this.pw = pw;
        return 1;
    }

}
