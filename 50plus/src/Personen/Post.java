package Personen;

import java.io.Serializable;



public class Post implements Serializable
{

	/**
	 * Die Klasse Post represaentiert einen Post mit dessen allen Variablen und Methoden
	 */
	private static final long serialVersionUID = 5232046163819389300L;
	
	private int ownPostcounter;
	private String username;
	private String inhalt;
	private String zeitpunkt;
	private boolean flagged;
	private String pinnwandOwner;
	
	/**
	 * Default Konstruktor der Klassen Post
	 * @param username User, der den Post gepostet hat
	 * @param inhalt Inhalt des Posts
	 * @param zeitpunkt Zeitpunkt, wann der Post gepostet wurde
	 */
	public Post (String username, String inhalt, String zeitpunkt, String pinnwandOwner)
	{
	
		this.ownPostcounter = 0; //spaeter wird es in Post_Serialisierung gesetzt werden
		this.username = username;
		this.inhalt = inhalt;
		this.zeitpunkt = zeitpunkt;
		this.pinnwandOwner = pinnwandOwner;
		
		this.flagged = false; 
	}
	
	/**
	 * Die Methode getOwnPostcounter gibt den User zurueck, auf dessen Pinnwand der Post gepostet wurde
	 * @return der Name des Users, auf dessen Pinnwand der Post gepostet wurde
	 */
	public String getPinnwandOwner()
	{
		return pinnwandOwner;
	}
	
	/**
	 * Die Methode setPinnwandOwner setzt den Namen des Users, auf dessen Pinnwand der Post gepostet wurde
	 * @param pinnwandOwner User, auf dessen Pinnwand der Post gepostet wurde
	 */
	public void setPinnwandOwner(String pinnwandOwner)
	{
		this.pinnwandOwner = pinnwandOwner;
	} 
	
	/**
	 * Die Methode getOwnPostcounter gibt die Nummer eines Posts zurueck
	 * @return die Nummer des Posts 
	 */
	public int getOwnPostcounter()
	{
		return ownPostcounter;
	}
	
	/**
	 * Die Methode setOwnPostcounter setzt die Nummer eines Posts
	 * @param nummer die Nummer eines Posts
	 */
	public void setOwnPostcounter(int nummer)
	{
		ownPostcounter = nummer;
	} 
	
	/**
	 * Die Methode getFlagged gibt den flagged oder gemeldet Status des Posts, 0 heisst ungemeldet oder unflagged, 1 heisst gemeldet oder flagged
	 * @return Status des Posts
	 */
	public boolean getFlagged()
	{
		return flagged;
	}
	
	/**
	 * Die Methode setFlagged setzt den flagged oder gemeldet Status des Posts, 0 heisst ungemeldet oder unflagged, 1 heisst gemeldet oder flagged
	 * @param flagged Status des Posts
	 */	
	public void setFlagged(boolean flagged)
	{
		this.flagged = flagged;
	} 
	
	/**
	 * Die Methode getUsername gibt den Username zurueck
	 * @return Username eines Users
	 */
	public String getUsername()
	{
		return username;
	}
	/**
	 * Die Methode setUsername setzt den Username des Users, der gepostet hat
	 * @param username Username des Users
	 */	
	public void setUsername(String username)
	{
		this.username = username;
	} 
	/**
	 * Die Methode getInhalt gibt den Inhalt eines Posts zurueck
	 * @return Inhalt eines Posts
	 */	
	public String getInhalt()
	{
		return inhalt;
	}
	/**
	 * Die Methode setInhalt setzt den Inhalt eines Posts
	 * @param inhalt Inhalt eines Posts
	 */		
	public void setInhalt(String inhalt)
	{
		this.inhalt = inhalt;
	}
	/**
	 * Die Methode getZeitpunkt gibt den Zeitpunkt eines Posts zurueck
	 * @return Zeitpunkt eines Posts
	 */		
	public String getZeitpunkt()
	{
		return zeitpunkt;
	}
	/**
	 * Die Methode setZeitpunkt setzt den Zeitpunkt eines Posts
	 * @param zeitpunkt  Zeitpunkt eines Posts
	 */	
	public void setZeitpunkt(String zeitpunkt)
	{
		this.zeitpunkt = zeitpunkt;
	}
	
	
	

	
}


