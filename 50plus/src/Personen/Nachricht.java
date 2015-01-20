package Personen;

import java.io.Serializable;

/**
 * Klasse Nachricht erzeugt private Nachrichten Objekte.
 *
 */
public class Nachricht implements Serializable{
	
	private static final long serialVersionUID = -2309114533598099141L;
	private String sender;
	private String empfaenger;
	private String inhalt;
	private String zeit;
	

	/**
	 * Konstruktor ruft Methoden auf, welche die Werte setzen.
	 * @param sender
	 * @param empfaenger
	 * @param inhalt
	 * @param zeit
	 */
	public Nachricht(String sender, String empfaenger, String inhalt, String zeit) {
		setNachrichtSender(sender);
		setNachrichtEmpfaenger(empfaenger);
		setNachrichtInhalt(inhalt);
		setNachrichtZeit(zeit);
	}

	
	/**
	 * Methode setNachrichtSender setzt den Username als Sender, der die Nachricht erstellt hat.
	 * @param s
	 */
	public void setNachrichtSender(String s) {
		sender=s;
	}
	/**
	 * Methode getNachrichtSender gibt den Sender einer Nachricht zurueck.
	 * @return
	 */
	public String getNachrichtSender(){
		return sender;
	}
	/**
	 * Methode setNachrichtEmpfaenger setzt den Username, der die Nachricht bekommt.
	 * @param e
	 */
	public void setNachrichtEmpfaenger(String e) {
		empfaenger=e;
	}
	/**
	 * Methode getNachrichtEmpfaenger gibt den Username des Empfaengers zurueck.
	 * @return
	 */
	public String getNachrichtEmpfaenger(){
		return empfaenger;
	}
	/**
	 * Methode setNachrichtInhalt setzt den Inahlt einer Nachricht.
	 * @param i
	 */
	public void setNachrichtInhalt(String i) {
		inhalt=i;
	}
	/**
	 * Methode getNachrichtInhalt gibt den Inhalt einer Nachricht zurueck.
	 * @return
	 */
	public String getNachrichtInhalt(){
		return inhalt;
	}
	/**
	 * Methode setNachrichtZeit setzt die Zeit wann eine Nachricht geschrieben wurde.
	 * @param z
	 */
	public void setNachrichtZeit(String z){
		zeit=z;
	}
	/**
	 * Methode getNachrichtZeit gibt die Zeit einer Nachricht zurueck.
	 * @return
	 */
	public String getNachrichtZeit(){
		return zeit;
	}
	
}
