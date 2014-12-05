package Personen;

import java.io.Serializable;


public class Post implements Serializable
{

	String username;
	String inhalt;
	String jetzt;
	
	public Post (String username, String inhalt, String jetzt)
	{
		this.username = username;
		this.inhalt = inhalt;
		this.jetzt = jetzt;
	}
	
	
}
