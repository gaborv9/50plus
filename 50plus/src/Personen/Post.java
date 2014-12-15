package Personen;

import java.io.Serializable;


public class Post implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5232046163819389300L;
	
	private int Postcounter;
	private String username;
	private String inhalt;
	private String zeitpunkt;
	
	public Post (String username, String inhalt, String zeitpunkt)
	{
		this.Postcounter = 0; //spaeter wird es in Post_Serialisierung gesetzt werden
		this.username = username;
		this.inhalt = inhalt;
		this.zeitpunkt = zeitpunkt;
	}
	

	public int getPostcounter()
	{
		return Postcounter;
	}

	public void setOwnPostcounter(int zahl)
	{
		Postcounter = zahl;
	} 
	
	public String getUsername()
	{
		return username;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	} 
	
	public String getInhalt()
	{
		return inhalt;
	}
	
	public void setInhalt(String inhalt)
	{
		this.inhalt = inhalt;
	}
	
	public String getZeitpunkt()
	{
		return zeitpunkt;
	}
	
	public void setZeitpunkt(String zeitpunkt)
	{
		this.zeitpunkt = zeitpunkt;
	}
	
	
	
	
}
