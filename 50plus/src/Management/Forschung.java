/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Data.Gruppe_Serialisierung;
import Data.Post_Serialisierung;
import Data.Serialisierung;
import Personen.Person;

/**
 *
 * @author master
 */
public class Forschung extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Forschung</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Forschung at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	Serialisierung pser = new Serialisierung();
    	Gruppe_Serialisierung grpser = new Gruppe_Serialisierung();
    	Post_Serialisierung postser= new Post_Serialisierung();
    	
    	  //	String information =request.getParameter("information");
    	String searchedperson = request.getParameter("searchperson");
    	int check=0;
    	
    	
    	
    	//User Statistik -----
    	//Anzahl Personen
    	int anzahlpersonen=0;
    	//Anzahl Freunde
    	int freundedurchschnitt=0, gesamtfreunde=0, freundcounter=0;
    	//Anzahl admin,user,forscher
    	int admins=0,user =0,forscher =0;
    	//Registrierungszeitpunkte
    	//werden bisher nicht gespeichert
    	//Alter 
    	
       int aeltester=1000, juengster=0, altersdurchschnitt=0, gesamtalter=0;
       
       //post
       int postdurchschnitt=0, gesamtpost=0;
       
    	for(Person ptemp:pser.getPersonList()){
    		
    		anzahlpersonen++;
    		if (ptemp.getRole()==1){
    			admins++;
    		}
    		else if(ptemp.getRole()==2){
    			forscher++;
    		}
    		else{
    			user++;
    		}
    		
    		if (ptemp.getAlter() > aeltester){
    			aeltester=ptemp.getAlter();
    		}
    		if (ptemp.getAlter() < juengster){
    			juengster=ptemp.getAlter();
    		}
    		gesamtalter=ptemp.getAlter()+gesamtalter;
    		
    		for (Person freunde : ptemp.getFreunde()){
    			freundcounter++;
    		}
    		gesamtfreunde = gesamtfreunde+freundcounter;
    		if (searchedperson != null && searchedperson.equals(ptemp.getID())){
    			check=1;
    		}
    		
    		//gesamtpost =gesamtpost+postser.getPostNumbersInt(ptemp.getID());
    		gesamtpost=gesamtpost+ postser.getPostNumbers(ptemp.getID()).size();
      	}
    	//Durchschnitte
    
 
    	altersdurchschnitt= gesamtalter/anzahlpersonen;
    	freundedurchschnitt=gesamtfreunde/anzahlpersonen;
    	postdurchschnitt= gesamtpost/anzahlpersonen;
    	
    	//Post-Statistik -----
    	//Anzahl aller Posts
    	int anzahlposts = postser.getMaxPostNumber();
 
    	//Gruppen-Statistik -----
    	 /*for (Person ptemp: pser.getPersonList()){
    		
    		
    	}
    	
    	//grpclass.getName() gibt den Namen der Gruppe zurueck
    	// public ArrayList<GruppeClass> getOwnGruppenlist(String username)   gibt gruppen eines nutzers zurueck
    	int grpcount=0; //Anzahl aller Gruppen
    	
   		for (GruppeClass grpclass : grpser.getGruppenlist()){
    		grpclass.getName();
    		grpser.getOwnGruppenlist(username);
    		grpcount++;
    	}*/
    	
  
    	HttpSession session = request.getSession();
    	
    	int role = pser.getPersonbyid((String) session.getAttribute("username")).getRole();
    	if (role !=2){
    		session.setAttribute("anfrage", "keinzugriff");		
    		response.sendRedirect("Forschung.jsp");
    	}
    	if (role == 2 && searchedperson==null){ //Ausgabe fuer allgemeine Informationen
    		session.setAttribute("anfrage", "info");
    		
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
    		
    		session.setAttribute("allgemeinwerte", allgemeinwerte);
    		response.sendRedirect("Forschung.jsp");
    		
    	}
    	else if (role ==2 && searchedperson!=null){//Ausgabe fuer Person
    	
    		if (check==0){
    			session.setAttribute("anfrage", "person");
    			session.setAttribute("personfound", "false"); //Person wurde nicht gefunden
    			response.sendRedirect("Forschung.jsp");
    		}
    		else{
    			session.setAttribute("personfound", "true");
	    		session.setAttribute("anfrage", "person");
	    		ArrayList<Integer> personwerte = new ArrayList<>();
		    	for (Person freunde : pser.getPersonbyid(searchedperson).getFreunde()){
		    		freundcounter++;	//Freundeanzahl
		    	}
	    		
	    		personwerte.add(postser.getOwnpostlist(searchedperson).size());//postanzahl des users
	    		personwerte.add(freundcounter);
	    		
	    		session.setAttribute("searchedpersonwerte", personwerte);
	    		
	    		response.sendRedirect("Forschung.jsp");
    		}
    	}
    	
      //  processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
