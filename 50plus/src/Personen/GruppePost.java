package Personen;

import java.io.Serializable;


public class GruppePost implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2649217202219074182L;
	private String username;
	private String inhalt;
	private String zeit;
	private String gruppenname;

	
	public GruppePost(String username, String inhalt, String zeit, String gruppenname) {
		setPostGruppenUsername(username);
		setPostGruppenInhalt(inhalt);
		setPostGruppenZeit(zeit);
		setPostGruppenname(gruppenname);	
	}

	
	
	public void setPostGruppenUsername(String un) {
		username=un;
	}
	
	public String getPostGruppenUsername(){
		return username;
	}
	
	public void setPostGruppenInhalt(String in){
		inhalt=in;
	}
	
	public String getPostGruppenInhalt(){
		return inhalt;
	}
	
	public void setPostGruppenZeit(String z){
		zeit=z;
	}
	
	public String getPostGruppenZeit(){
		return zeit;
	}
	
	public void setPostGruppenname(String gr) {
		gruppenname=gr;
	}

	public String getPostGruppenname(){
		return gruppenname;
	}
}
