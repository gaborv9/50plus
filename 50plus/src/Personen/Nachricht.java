package Personen;

import java.io.Serializable;


public class Nachricht implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2309114533598099141L;
	private String sender;
	private String empfaenger;
	private String inhalt;
	private String zeit;
	

	
	public Nachricht(String sender, String empfaenger, String inhalt, String zeit) {
		setNachrichtSender(sender);
		setNachrichtEmpfaenger(empfaenger);
		setNachrichtInhalt(inhalt);
		setNachrichtZeit(zeit);
	}

	
	
	public void setNachrichtSender(String s) {
		sender=s;
	}
	
	public String getNachrichtSender(){
		return sender;
	}
	
	public void setNachrichtEmpfaenger(String e) {
		empfaenger=e;
	}
	
	public String getNachrichtEmpfaenger(){
		return empfaenger;
	}
	
	public void setNachrichtInhalt(String i) {
		inhalt=i;
	}
	
	public String getNachrichtInhalt(){
		return inhalt;
	}
	
	public void setNachrichtZeit(String z){
		zeit=z;
	}
	
	public String getNachrichtZeit(){
		return zeit;
	}
	
}
