package Management;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Data.Serialisierung;
import Personen.Person;
/**
 * 
 * @author Author
 *
 */
public class FreundeManagement {
	/**
	 * 
	 * @param request - Aktuelle Daten der eingeloggten Person
	 * @param response - sendet Daten zurück an das Objekt
	 * @throws IOException - bei fehler wird Exception geworfen
	 * 
	 * in dieser Methode wird eine Freundschaftsanfrage an den Freund gesendet
	 */
	 public void freundanfragen(HttpServletRequest request, HttpServletResponse response) throws IOException{
	    	response.setContentType("text/html");
	    	PrintWriter out = response.getWriter();
	    	String freundname = request.getParameter("freundname");
	    	HttpSession session = request.getSession();
	    	String username =(String) session.getAttribute("username");
	    	Serialisierung a = new Serialisierung();
	    	Person ich = a.getPersonbyid(username);
	    	Person freund = a.getPersonbyid(freundname);
	    	boolean bef = false;
	    	boolean befreundet = false;
	    	if((!(request.getParameter("freundname").isEmpty()))){
	    		for(Person test: ich.geteingehendeAnfragen()) if(test.getID().equals(freund.getID())) bef=true;
	    		if(bef == false){
	    			for(Person test: ich.getFreunde()) if(test.getID().equals(freund.getID())) befreundet = true;
	    			if(!befreundet){
	    				ich.addgesendeteAnfragen(freund);
	    				freund.addeingehendeAnfragen(ich);
	    			}
	    			Person ichneu = ich;
	    			Person freundneu = freund;
	    			
	    			a.loeschePerson(ich);
	    			a.speicherePerson(ichneu);
	    			a.loeschePerson(freund);
	    			a.speicherePerson(freundneu);
	    		}
	    		else{	    			
	    			boolean check=false;
	    			
	    			for (Person per : ich.getFreunde()){
	    				if (freundname.equals(per.getID())){
	    					check=true;
		    				}
	    				}
	    			if (check==false){
	    					ich.setFreunde(freund,true);
	    					freund.setFreunde(ich, true);
	    					
	    	    			freund.remgesendeteAnfragen(ich);
	    	    			freund.remeingehendeAnfragen(ich);
	    	    			ich.remeingehendeAnfragen(freund);
	    	    			Person userneu = ich;
	    	        		Person freundneu = freund;
	    	        		a.loeschePerson(freund);
	    	        		a.speicherePerson(freundneu);
	    	    			a.loeschePerson(ich);
	    					a.speicherePerson(userneu);
	    				}
		 	 
	    		}
	    	}
	    }
	 /**
	  * 
	  * @param request - Aktuelle Daten der eingeloggten Person
	  * @param response - sendet Daten zurück an das Objekt
	  * @throws IOException - bei fehler wird Exception geworfen
	  * 
	  * in dieser Methode wird ein Freund entfernt
	  */
	 public void freundloeschen(HttpServletRequest request, HttpServletResponse response) throws IOException{
	    	response.setContentType("text/html");
	    	String freundname = request.getParameter("freundname");
	    	HttpSession session = request.getSession();
	    	
	    	
	    	if(!(request.getParameter("freundname").isEmpty())){
	    		String adddelete = (String) request.getParameter("adddelete");
	    		String loeschen = "0";
	    		
	    		if(adddelete.equals(loeschen)){
	        		//out.println("Ich loesche jetzt!");
	        		String username = (String) session.getAttribute("username");
	    			Serialisierung a = new Serialisierung();
	    			Person freund,useralt;
	    			freund = a.getPersonbyid(freundname);
	    			useralt = a.getPersonbyid(username);
	    			ArrayList<Person> freundlist = new ArrayList<Person>();
	    			freundlist = useralt.getFreunde();
	    			
	    			boolean check=false;
	    			
	    			for (Person per : freundlist){
	    				if (freundname.equals(per.getID())){
	    					check=true;
	        				}
	    				}
	    			if (check==true){
	    					
	    					
	    					useralt.setFreunde(freund,false);
	    					freund.setFreunde(freund,false);
	    					Person freundneu = freund;
	    					Person userneu = useralt;
	    					a.loeschePerson(freund);
	    					a.speicherePerson(freundneu);
	    					a.loeschePerson(useralt);
	    					a.speicherePerson(userneu);
	    					
	    					
	    				
	    				}
	        		}
	        	
	        	}
	    }
	 
	 /**
	  * 
	  * @param request - Aktuelle Daten der eingeloggten Person
	  * @param response - sendet Daten zurück an das Objekt
	  * @throws IOException - bei fehler wird Exception geworfen
	  * 
	  * in dieser methode wird entschieden, ob eine Person als Freund hinzugefuegt wird oder nicht
	  */
	 public void freundzufuegen(HttpServletRequest request, HttpServletResponse response) throws IOException{
	    	PrintWriter out = response.getWriter();
	    	response.setContentType("text/html");
	    	String freundname =(String) request.getParameter("freundname");
	    	HttpSession session = request.getSession();   
	    	String username = (String) session.getAttribute("username");
		    
	    	if(!(request.getParameter("freundname").isEmpty())){
	    		String adddelete = (String) request.getParameter("adddelete");
	    		String zufuegen = "1";
	    		String loeschen = "0";
	    		
	    		if(adddelete.equals(zufuegen)){
	    			Serialisierung a = new Serialisierung();
	    			Person freund,useralt;
	    			freund = a.getPersonbyid(freundname);
	    			useralt = a.getPersonbyid(username);
	    			
	    			boolean check=false;
	    			
	    			for (Person per : useralt.getFreunde()){
	    				if (freundname.equals(per.getID())){
	    					check=true;
		    				}
	    				}
	    			if (check==false){
	    					useralt.setFreunde(freund,true);
	    					freund.setFreunde(useralt, true);
	    					
	    	    			freund.remgesendeteAnfragen(useralt);
	    	    			useralt.remeingehendeAnfragen(freund);
	    	    			Person userneu = useralt;
	    	        		Person freundneu = freund;
	    	        		a.loeschePerson(freund);
	    	        		a.speicherePerson(freundneu);
	    	    			a.loeschePerson(useralt);
	    					a.speicherePerson(userneu);
	    				}
		 	 
		    	
	    			}
	    		else if(adddelete.equals(loeschen)){
	    			Serialisierung a = new Serialisierung();
	    			Person freund,user;
	    			freund = a.getPersonbyid(freundname);
	    			user = a.getPersonbyid(username);
	    			
	    			freund.remgesendeteAnfragen(user);
	    			user.remeingehendeAnfragen(freund);
	    			
	    			Person ichneu = user;
	        		Person freundneu = freund;
	        		
	        		a.loeschePerson(user);
	        		a.speicherePerson(ichneu);
	        		a.loeschePerson(freund);
	        		a.speicherePerson(freundneu);
	    			
	    		}
	    		
	    	}
	    }

}
