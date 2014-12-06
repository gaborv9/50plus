package Personen;

import java.io.Serializable;


public class Post implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5232046163819389300L;
	
	private String username;
	private String inhalt;
	private String zeitpunkt;
	
	public Post (String username, String inhalt, String zeitpunkt)
	{
		this.username = username;
		this.inhalt = inhalt;
		this.zeitpunkt = zeitpunkt;
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
