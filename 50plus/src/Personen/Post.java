package Personen;

import java.io.Serializable;


public class Post implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5232046163819389300L;
	
	private static int globalPostcounter;
	private int ownPostcounter;
	private String username;
	private String inhalt;
	private String zeitpunkt;
	
	public Post (String username, String inhalt, String zeitpunkt)
	{
		globalPostcounter++;
		this.ownPostcounter = globalPostcounter;
		this.username = username;
		this.inhalt = inhalt;
		this.zeitpunkt = zeitpunkt;
	}
	
	public int getGlobalPostcounter()
	{
		return globalPostcounter;
	}
	
	public void setGlobalPostcounter(int zahl)
	{
		globalPostcounter = zahl;
	}
	
	public int getOwnPostcounter()
	{
		return ownPostcounter;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public String getInhalt()
	{
		return inhalt;
	}
	
	public String getZeitpunkt()
	{
		return zeitpunkt;
	}
	
	
	
	
}
