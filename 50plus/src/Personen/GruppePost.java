package Personen;

import java.io.Serializable;

/**
 * Klasse GruppePost erzeugt Posts einer Gruppe
 *
 */
public class GruppePost implements Serializable{
	
	private static final long serialVersionUID = 2649217202219074182L;
	private String username;
	private String inhalt;
	private String zeit;
	private String gruppenname;

/**
 * Konstruktor ruft Methoden auf, welche die Werte setzen
 * @param username
 * @param inhalt
 * @param zeit
 * @param gruppenname
 */
	public GruppePost(String username, String inhalt, String zeit, String gruppenname) {
		setPostGruppenUsername(username);
		setPostGruppenInhalt(inhalt);
		setPostGruppenZeit(zeit);
		setPostGruppenname(gruppenname);	
	}

	
	/**
	 * Methode setPostGruppenUsername setzt den Username der Person die den Post erstellt.
	 * @param un
	 */
	public void setPostGruppenUsername(String un) {
		username=un;
	}
	/**
	 * Methode getPostGruppenUsername gibt den username zurueck.
	 * @return
	 */
	public String getPostGruppenUsername(){
		return username;
	}
	/**
	 * Methode setPostGruppenInhalt setzt den Inhalt des Posts.
	 * @param in
	 */
	public void setPostGruppenInhalt(String in){
		inhalt=in;
	}
	/**
	 * Methode getPostGruppenInhalt gibt den inhalt zurueck.
	 * @return
	 */
	public String getPostGruppenInhalt(){
		return inhalt;
	}
	/**
	 * Methode setPostGruppenZeit setzt die Zeit des Posts.
	 * @param z
	 */
	public void setPostGruppenZeit(String z){
		zeit=z;
	}
	/**
	 * Methode getPostGruppenZeit gibt die Zeit des Posts zurueck.
	 * @return
	 */
	public String getPostGruppenZeit(){
		return zeit;
	}
	/**
	 * Methode setPostGruppenname setzt den Namen der Gruppe, in der der Post erstellt wurde.
	 * @param gr
	 */
	public void setPostGruppenname(String gr) {
		gruppenname=gr;
	}
	/**
	 * Methode getPostGruppenname gibt den Name der Gruppe zurueck, in der der Post erstellt wurde.
	 * @return
	 */
	public String getPostGruppenname(){
		return gruppenname;
	}
}
