package Personen;

import java.io.Serializable;
import java.util.GregorianCalendar;

public abstract class Person implements Serializable {
	public int role;
	public int alter;
	public String vorname;
	public String nachname;
	public String id; // username eindeutig
	public String pw;
	public GregorianCalendar datum;

	/**
	 * @param role
	 *            the role to set
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
	 * @param alter
	 *            the alter to set
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
	 * @param vorname
	 *            the vorname to set
	 */
	public int setVorname(String vorname) {
		if ((vorname.length() > 25) || (vorname.isEmpty() == true)) {
			return 0;
		}
		this.vorname = vorname;
		return 1;
	}

	/**
	 * @return the nachname
	 */
	public String getNachname() {
		return nachname;
	}

	/**
	 * @param nachname
	 *            the nachname to set
	 */
	public int setNachname(String nachname) {
		if ((nachname.length() > 25) || (nachname.isEmpty() == true)) {
			return 0;
		}

		this.nachname = nachname;
		return 1;
	}

	/**
	 * @return the id
	 */
	public String getID() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public int setID(String id) {
		if ((id.length() > 25) || (id.isEmpty() == true)) {
			return 0;
		}
		this.id = id;
		return 1;
	}

	/**
	 * @return the datum
	 */
	public GregorianCalendar getDatum() {
		return datum;
	}

	/**
	 * @param datum
	 *            the datum to set
	 */
	public int setDatum(String year, String month, String day) {
		try {
			int dayi = Integer.parseInt(day);
			int monthi = Integer.parseInt(month);
			int yeari = Integer.parseInt(year);
			
			GregorianCalendar now = new GregorianCalendar();
			GregorianCalendar gebdate = new GregorianCalendar();
			gebdate.setLenient(false);
			gebdate.set(GregorianCalendar.YEAR, yeari);
			gebdate.set(GregorianCalendar.MONTH, monthi);
			gebdate.set(GregorianCalendar.DAY_OF_MONTH, dayi);
			if (now.get(GregorianCalendar.YEAR)
					- gebdate.get(GregorianCalendar.YEAR) < 49) {
				return 0;
			}
			else{

			this.datum = gebdate;
			return 1;
			}

		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * @return the pw
	 */
	public String getPW() {
		return pw;
	}

	/**
	 * @param pw
	 *            the pw to set
	 */
	public int setPW(String pw) {
    	if ((id.length() >25) || (id.isEmpty()==true) || (pw.length() < 6)){
    		return 0;
    	}
        this.pw = pw;
        return 1;
    }

}
