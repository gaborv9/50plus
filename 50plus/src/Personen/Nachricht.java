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
	 * @param sender - Sender der Nachricht
	 * @param empfaenger - Empfaenger der Nachricht
	 * @param inhalt - Inhalt der Nachricht
	 * @param zeit - Zeit des Versendens
	 */
	public Nachricht(String sender, String empfaenger, String inhalt, String zeit) {
		setNachrichtSender(sender);
		setNachrichtEmpfaenger(empfaenger);
		setNachrichtInhalt(inhalt);
		setNachrichtZeit(zeit);
	}

	
	/**
	 * Methode setNachrichtSender setzt den Username als Sender, der die Nachricht erstellt hat.
	 * @param s - Usernamen der gesendet hat
	 */
	public void setNachrichtSender(String s) {
		sender=s;
	}
	/**
	 * Methode getNachrichtSender gibt den Sender einer Nachricht zurueck.
	 * @return - Usernamen der gesendet hat
	 */
	public String getNachrichtSender(){
		return sender;
	}
	/**
	 * Methode setNachrichtEmpfaenger setzt den Username, der die Nachricht bekommt.
	 * @param e - Empfaenger der Nachricht
	 */
	public void setNachrichtEmpfaenger(String e) {
		empfaenger=e;
	}
	/**
	 * Methode getNachrichtEmpfaenger gibt den Username des Empfaengers zurueck.
	 * @return - Empfaenger der Nachricht
	 */
	public String getNachrichtEmpfaenger(){
		return empfaenger;
	}
	/**
	 * Methode setNachrichtInhalt setzt den Inahlt einer Nachricht.
	 * @param i - Inhalt der Nachricht
	 */
	public void setNachrichtInhalt(String i) {
		inhalt=i;
	}
	/**
	 * Methode getNachrichtInhalt gibt den Inhalt einer Nachricht zurueck.
	 * @return - Inhalt der Nachricht
	 */
	public String getNachrichtInhalt(){
		return inhalt;
	}
	/**
	 * Methode setNachrichtZeit setzt die Zeit wann eine Nachricht geschrieben wurde.
	 * @param z - Zeit wann Nachricht geschrieben wurde
	 */
	public void setNachrichtZeit(String z){
		zeit=z;
	}
	/**
	 * Methode getNachrichtZeit gibt die Zeit einer Nachricht zurueck.
	 * @return - Zeit wann Nachricht geschrieben wurde
	 */
	public String getNachrichtZeit(){
		return zeit;
	}
	
}
