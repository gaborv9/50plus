package Personen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import Data.Serialisierung;
import Management.PersonDAO;


public abstract class Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int role;
	//private int alter; Alter wird derzeit nicht gesetzt, da man sonst immer auf die Aktualisierung achten muesste, also wird immer neu berechnet mit getAlter
	private String vorname;
	private String nachname;
	private String id; // id=username, diese sind eindeutig
	private String pw;
	private GregorianCalendar datum;
	private ArrayList<Person> freunde;
	private ArrayList<Person> eingehendeAnfragen;
	private ArrayList<Person> gesendeteAnfragen;
	private ArrayList<Person> gemeldetvon;
	private int meldunganz;
	private String picturelink;
	private GregorianCalendar sperrdatum;
	/**
	 * 
	 * @return sperrdatum - wichtig  um Datum bis erneutem Login festzulegen
	 */
	public GregorianCalendar getsperrdatum(){
		if(sperrdatum == null ) sperrdatum = new GregorianCalendar();
		return sperrdatum;
	}
	/**
	 * 
	 * @param day - anzahl der Tage, die Benutzer gesperrt werden soll
	 */
	public void setsperrdatum(int day){
		sperrdatum = new GregorianCalendar();
		sperrdatum.add(GregorianCalendar.DAY_OF_MONTH, day);
	}
	
	/**
	 * 
	 * @param zahl - Anzahl der Meldungen
	 */
	public void setmeldunganz(int zahl){
		meldunganz = zahl;
	}
	/**
	 * 
	 * @return gemeldetvon - Liste von Personen, die jene betroffene Person gemeldet haben
	 */
	public ArrayList<Person> getgemeldetvon(){
		if(gemeldetvon==null) gemeldetvon = new ArrayList<Person>();
		return gemeldetvon;
	}
	/**
	 * 
	 * @return meldunganz - gibt anzahl an meldungen zurueck
	 */
	public int getmeldunganz(){
		meldunganz = getgemeldetvon().size();
		return meldunganz;
	}
	/**
	 * 
	 * @param melder - editiert Liste von meldenden Personen
	 */
	public void setgemeldetvon(ArrayList<Person> melder){
		if(melder!=null) gemeldetvon=melder;
	}
	/**
	 * 
	 * @return picturelink wichtig fuer das Bild
	 */
	public String getPicturelink() {
		return picturelink;
	}
	public int setPicturelink(String picturelink) {
		if ( picturelink.isEmpty()==true || picturelink.length() > 1000){
			return 0;
		}
		else{
		this.picturelink = picturelink;
			return 1;
		}
	}
	/**
	 * 
	 * @return gesendeteAnfragen - alle Freundschaftsanfragen von der Person
	 */
	public ArrayList<Person> getgesendeteAnfragen(){
		if(gesendeteAnfragen==null) gesendeteAnfragen = new ArrayList<Person>();
		return gesendeteAnfragen;
	}
	/**
	 * 
	 * @return eingehendeAnfragen - alle Freundschaftsanfragen an die Person
	 */
	public ArrayList<Person> geteingehendeAnfragen(){
		if(eingehendeAnfragen==null) eingehendeAnfragen = new ArrayList<Person>();
		return eingehendeAnfragen;
	}
	/**
	 * 
	 * @param zufuegen - Anfrage zu gesendeteAnfragen adden
	 */
	public void addgesendeteAnfragen(Person zufuegen){
		ArrayList<Person> altlist = getgesendeteAnfragen();
			if((altlist!=null)){
				boolean doppelt = false;
				for(Person test : altlist){
					if(test.getID().equals(zufuegen.getID())) doppelt = true;
				}
				if(doppelt==false){
					altlist.add(zufuegen);
					gesendeteAnfragen = altlist;
				}
			}
	}
	/**
	 * 
	 * @param loeschen - Anfrage von gesendeteAnfragen loeschen
	 */
	public void remgesendeteAnfragen(Person loeschen){
		ArrayList<Person> altlist = getgesendeteAnfragen();
			if(altlist.size()!=0){
				ArrayList<Person> neulist = new ArrayList<Person>();
				for(Person test: altlist){
					if(test.getID().equals(loeschen.getID())){}
					else neulist.add(test);
				}
					gesendeteAnfragen = neulist;
				}
	}
	/**
	 * 
	 * @param zufuegen - Anfrage von eingehende Anfragen adden
	 */
	public void addeingehendeAnfragen(Person zufuegen){
		ArrayList<Person> altlist = geteingehendeAnfragen();
		if(altlist!=null){
			boolean doppelt = false;
			for(Person test : altlist){
				if(test.getID().equals(zufuegen.getID())) doppelt = true;
			}
			if(doppelt==false){
				altlist.add(zufuegen);
				eingehendeAnfragen = altlist;
			}
		
		}
	}
	/**
	 * 
	 * @param loeschen - Anfrage von eingehendeAnfragen loeschen
	 */
	public void remeingehendeAnfragen(Person loeschen){
		ArrayList<Person> altlist = geteingehendeAnfragen();
			if(altlist.size()!=0){
				ArrayList<Person> neulist = new ArrayList<Person>();
				for(Person test: altlist){
					if(test.getID().equals(loeschen.getID())){}
					else neulist.add(test);
				}
					eingehendeAnfragen = neulist;
				
			}
	}
	
	/**
	 * 
	 * @return freunde
	 * Alle Freunde der Person zurueckgeben
	 */
	public ArrayList<Person> getFreunde(){
		if(freunde==null) freunde= new ArrayList<Person>();
		PersonDAO p = new Serialisierung();
		ArrayList<Person> alleuser = p.getPersonList();
		ArrayList<Person> freundeneu = new ArrayList<Person>();
		for(Person test: freunde){
			for(Person test2: alleuser){
				if(test.getID().equals(test2.getID()))freundeneu.add(test);
			}
		}
		this.freunde = freundeneu;

		return this.freunde;
	}
	
	
	/**
	 * 
	 * @param a - Parameter fuer Geaenderte Freund-Liste
	 */
	public void setArrayList(ArrayList<Person>a){
		this.freunde = a;
	}
	/**
	 * 
	 * @param addrem Person die den Freunden zugefuegt werden soll
	 * @param user aktueller User
	 * 
	 */
	public void setFreunde(Person user, boolean addrem){
		if(getFreunde()!=null){
			if(addrem == true){
				for(Person test: freunde){
					if(test.equals(user)) return;
				}	
				freunde.add(user);
				return;
			}
		else{
			ArrayList<Person> test = new ArrayList<Person>();
			for(Person test2: freunde){
				if(!(test2.getID().equals(user.getID()))) test.add(test2);
				}
			freunde = test;			
			}
		}
	}
	
	/**
	 * @param role
	 *            the role to set
	 * @return rolle
	 */
	public int setRole(int role) {
		if (role > 3 || role < 1){
			return 0;
		}
		else{
			this.role = role;
			return 1;
		}
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

		GregorianCalendar now = new GregorianCalendar();
		
		int alter= now.get(GregorianCalendar.YEAR) - this.datum.get(GregorianCalendar.YEAR);
	    return alter;
		
	}

	/**
	 * @param alter
	 *            the alter to set
	 */
	/*public void setAlter(int alter) {
		this.alter = alter;
	}*/

	/**
	 * @return the vorname
	 */
	public String getVorname() {
		return vorname;
	}

	/**
	 * @param vorname
	 *            the vorname to set
	 * @return 1 wenn Vorname zugefuegt wurde
	 */
	public int setVorname(String vorname) {
		if ((vorname.length() > 25) || (vorname.isEmpty() == true)) {
			throw new IllegalArgumentException("Vorname darf nicht leer sein und muss weniger als 25 Zeichen haben.");
			//return 0;
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
	 * @return 1 wenn nachname gesetzt
	 */
	public int setNachname(String nachname) {
		if ((nachname.length() > 25) || (nachname.isEmpty() == true)) {
			throw new IllegalArgumentException("Nachname darf nicht leer sein und muss weniger als 25 Zeichen haben.");
			//return 0;
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
	 * @return 1 wenn id gesetzt
	 */
	public int setID(String id) {
		if ((id.length() > 25) || (id.isEmpty() == true)) {
			return 0;
		}
		this.id = id;
		return 1;
	}

	/**
	 * @return datum
	 */
	public GregorianCalendar getDatum() {
		return datum;
	}
	public String getDatumString(){
		int year=datum.get(GregorianCalendar.YEAR);
		int month=datum.get(GregorianCalendar.MONTH)+1;
		int day=datum.get(GregorianCalendar.DAY_OF_MONTH);
 
		return  (Integer.toString(day)+"-"+Integer.toString(month)+"-"+Integer.toString(year));
	}

	/**
	 * @param year - das jahr
	 * @param month - das Monat
	 * @param day - der tag
	 *            the datum to set
	 * @return 1 wenn datum gesetzt
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
			gebdate.set(GregorianCalendar.MONTH, monthi-1); //Beim Monat gibt es eine Ausnahme, man muss bei 0 anfangen.., Profil.jsp hat bei der Ausgabe+1 zur korrekten Darstellung.
			gebdate.set(GregorianCalendar.DAY_OF_MONTH, dayi);
			if ((now.get(GregorianCalendar.YEAR) - gebdate.get(GregorianCalendar.YEAR)) < 49) {
				return 0;
			}
			else{
			this.datum = gebdate;
			this.sperrdatum = gebdate;
			return 1;
			}

		} catch (Exception e) {
			throw new IllegalArgumentException("Sie müssen über 50 sein und ein korrektes Datum eingeben.");
			//return 0;
		}
	}

	/**
	 * @return pw - Passwort
	 */
	public String getPW() {
		return pw;
	}

	/**
	 * @param pw
	 *            the pw to set
	 * @return 1 wenn gesetzt
	 */
	public int setPW(String pw) {
    	if ((pw.length() >25) || (pw.isEmpty()==true) || (pw.length() < 6)){
    		throw new IllegalArgumentException("Passwort darf nicht leer oder weniger als 6 Zeichen haben und muss weniger als 25 Zeichen haben.");
    		//return 0;
    	}
        this.pw = pw;
        return 1;
    }

}
