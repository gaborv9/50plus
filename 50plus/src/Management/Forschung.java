package Management;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Data.Gruppe_Serialisierung;
import Data.Post_Serialisierung;
import Data.Serialisierung;
import Personen.Person;
import Personen.Post;

/**Forschung liefert Forschungsergebnisse zurueck.
 * 
 * @author master
 */
public class Forschung extends HttpServlet {

	/**
	 * Handles the HTTP <code>GET</code> method. Gets different research results.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Serialisierung pser = new Serialisierung();
		Gruppe_Serialisierung grpser = new Gruppe_Serialisierung();
		Post_Serialisierung postser = new Post_Serialisierung();
		PinnwandManagement pinman= new PinnwandManagement();
		 
		String searchedperson = request.getParameter("searchperson");
		String vperson1 = request.getParameter("vperson1");
		String vperson2 = request.getParameter("vperson2");
		int check = 0;

		// User Statistik -----
		// Anzahl Personen
		int anzahlpersonen = 0;
		// Anzahl Freunde
		int freundedurchschnitt = 0, gesamtfreunde = 0, freundcounter = 0;
		// Anzahl admin,user,forscher
		int admins = 0, user = 0, forscher = 0;
		// Registrierungszeitpunkte
		// werden bisher nicht gespeichert
		// Alter

		int aeltester = 0, juengster = 1000, altersdurchschnitt = 0, gesamtalter = 0;

		// post
		int postdurchschnitt = 0, gesamtpost = 0;

		for (Person ptemp : pser.getPersonList()) {

			anzahlpersonen++;
			if (ptemp.getRole() == 1) {
				admins++;
			} else if (ptemp.getRole() == 2) {
				forscher++;
			} else {
				user++;
			}

			if (ptemp.getAlter() > aeltester) {
				aeltester = ptemp.getAlter();
			}
			if (ptemp.getAlter() < juengster) {
				juengster = ptemp.getAlter();
			}
			gesamtalter = ptemp.getAlter() + gesamtalter;
			freundcounter=0;
			for (Person freunde : ptemp.getFreunde()) {
				freundcounter++;
			}
			gesamtfreunde = gesamtfreunde + freundcounter;
			if (searchedperson != null && searchedperson.equals(ptemp.getID())) {
				check = 1;
			}

			// gesamtpost =gesamtpost+postser.getPostNumbersInt(ptemp.getID());
			gesamtpost = gesamtpost + postser.getPostNumbers(ptemp.getID()).size();
		}

		// Durchschnitte

		altersdurchschnitt = gesamtalter / anzahlpersonen;
		freundedurchschnitt = gesamtfreunde / anzahlpersonen;
		postdurchschnitt = gesamtpost / anzahlpersonen; //Durch Integer gibt es eine Rundung, kann also auch 0 herauskommen

		// Post-Statistik -----
		// Anzahl aller Posts
	     int anzahlposts = postser.getMaxPostNumber();
 
		 int usergroups = grpser.getOwnGruppenlist(searchedperson).size();
		 int anzahlgruppen= grpser.getGruppenlist().size();

		HttpSession session = request.getSession();

		int role = pser.getPersonbyid((String) session.getAttribute("username")).getRole();
		if (role != 2) {
			session.setAttribute("anfrage", "keinzugriff");
			response.sendRedirect("Forschung.jsp");
		}
		if (role == 2 && searchedperson == null && vperson1==null ) { // Ausgabe fuer allgemeine Informationen
	
			ArrayList<Integer> allgemeinwerte = new ArrayList<>();
			allgemeinwerte.add(anzahlpersonen);
			allgemeinwerte.add(gesamtpost);
			allgemeinwerte.add(juengster);
			allgemeinwerte.add(aeltester);
			allgemeinwerte.add(admins);
			allgemeinwerte.add(user);
			allgemeinwerte.add(forscher);
			allgemeinwerte.add(altersdurchschnitt);
			allgemeinwerte.add(freundedurchschnitt);
			allgemeinwerte.add(postdurchschnitt);
			allgemeinwerte.add(anzahlgruppen);
			session.setAttribute("allgemeinwerte", allgemeinwerte);
			session.setAttribute("anfrage", "info");
			response.sendRedirect("Forschung.jsp");
			
			

		} else if (role == 2 && searchedperson != null && vperson1 ==null && vperson2 ==null) {// Ausgabe fuer Person

			if (check == 0) {
				session.setAttribute("anfrage", "person");
				session.setAttribute("personfound", "false"); // Person wurde nicht gefunden
				response.sendRedirect("Forschung.jsp");
			} 
			else {
				int friendc=0;
				session.setAttribute("personfound", "true");
				session.setAttribute("anfrage", "person");
				ArrayList<Integer> personwerte = new ArrayList<>();
				for (Person freunde : pser.getPersonbyid(searchedperson).getFreunde()) {
					friendc++; // Freundeanzahl
				}

				personwerte.add(postser.getOwnpostlist(searchedperson).size());// postanzahl des users
				
				//String zeitpunkt = new SimpleDateFormat("dd.MM.yyyy HH:mm").format(Calendar.getInstance().getTime());
				ArrayList<Post> postlist = pinman.getOwnpostlist(searchedperson);
				ArrayList<String> monatsposts= new ArrayList<>();
			//	ArrayList<String> tagesposts= new ArrayList<>();
			    
				for (Post post : postlist){
					monatsposts.add(post.getZeitpunkt().substring(3,5));
					//tagesposts.add(post.getZeitpunkt().substring(0, 1));
					
				}
				 
			  ArrayList<Integer> mpostint = new ArrayList<>();
			    for ( String temp : monatsposts){
			    	mpostint.add(Integer.parseInt(temp));
			    }
			    
			  int[] mcount= new int[12];
			    for (int i=1; i <=12; i++){
			    	for (int tempi : mpostint){
			    		if (i == tempi){
			    			mcount[i-1] = mcount[i-1]+1;
			    		}
			    	}
			    	
			    }
				
				personwerte.add(friendc);
				personwerte.add(usergroups);
			//	session.setAttribute("tagesposts", tagesposts); //Monats und Tagesposts werden in Stringform uebergeben.
				session.setAttribute("monatsposts", mcount);
				session.setAttribute("searchedusername", pser.getPersonbyid(searchedperson).getID());
				session.setAttribute("searchedpersonwerte", personwerte);
				response.sendRedirect("Forschung.jsp");
			    

			}

		}else if (role ==2 && searchedperson == null) {
			
			String jspmessage="";
			try{
			
				VernetzungClass g= new VernetzungClass();
				List result = g.calc(vperson1, vperson2);
				ArrayList<String> stringresult = new ArrayList<String>();
				for (int rcount=0; rcount < result.size(); rcount++){
					stringresult.add(result.get(rcount).toString());
				}
				session.setAttribute("anfrage", "vert");
				session.setAttribute("verteilungzahl", Integer.toString(result.size()));
				session.setAttribute("verteilung",   stringresult);
				response.sendRedirect("Forschung.jsp");

			}catch(Exception e){
				jspmessage = e.getMessage();
				ArrayList<String> elist=new ArrayList<String>();
				elist.add(e.getMessage());
				session.setAttribute("anfrage", "vert");
				session.setAttribute("verteilungzahl", "0");
				session.setAttribute("verteilung", elist);
				response.sendRedirect("Forschung.jsp");
			
				
			}
 
		}else{}
	 
	}

}
