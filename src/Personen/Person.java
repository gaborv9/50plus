 
package Personen;

import java.io.Serializable;
import java.util.GregorianCalendar;

 
public abstract class Person implements Serializable{
    public int role;
    public int alter;
    public String vorname;
    public String nachname;
    public String id; //username eindeutig
    public String pw;
    public GregorianCalendar datum;
     
    /**
     * @param role the role to set
     */
    public void setRole(int role) {
        this.role = role;
    }

    /**
     * @return the role
     */
    public int getRole() {
        return role;
    }

    /**
     * @return the alter
     */
    public int getAlter() {
        return alter;
    }

    /**
     * @param alter the alter to set
     */
    public void setAlter(int alter) {
        this.alter = alter;
    }

    /**
     * @return the vorname
     */
    public String getVorname() {
        return vorname;
    }

    /**
     * @param vorname the vorname to set
     */
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    /**
     * @return the nachname
     */
    public String getNachname() {
        return nachname;
    }

    /**
     * @param nachname the nachname to set
     */
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    /**
     * @return the id
     */
    public String getID() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setID(String id) {
        this.id = id;
    }

    /**
     * @return the datum
     */
    public GregorianCalendar getDatum() {
        return datum;
    }

    /**
     * @param datum the datum to set
     */
    public void setDatum(GregorianCalendar datum) {
        this.datum = datum;
    }

    /**
     * @return the pw
     */
    public String getPW() {
        return pw;
    }

    /**
     * @param pw the pw to set
     */
    public void setPW(String pw) {
        this.pw = pw;
    }

    
    
    
}
