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

import Data.Serialisierung;
import Personen.Person;

/**
 *
 * @author Lechl Bernhard
 */
public class Freunde extends HttpServlet {

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
            out.println("<title></title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Das findest du unter processRequest, Freunde Servlet"+ "</h1>");
           
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request - freundname der hinzu oder entfernt werden soll, aktueller User, addelete = loeschen oder hinzufuegen
     * @param response servlet response - nichts (eventuelle textausgaben)
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	response.setContentType("text/html");
    	String freundname = request.getParameter("freundname");
    	HttpSession session = request.getSession();
    	PrintWriter out = response.getWriter();
    	String Ziel = "Freunde.jsp";
    	//out.println("ich bin vor der Auswahl");
    	
    	String wunsch = request.getParameter("wunsch");
    	if(wunsch.equals("zufuegen")){
    		freundzufuegen(request, response);
    		out.println("Ich bin in zufuegen");
    		Ziel = "Freunde.jsp";
    	}
    	else if(wunsch.equals("loeschen")){
    		freundloeschen(request,response);
    		Ziel = "Freunde.jsp";
    	}
    	else if(wunsch.equals("adden")){
    		freundanfragen(request,response);
    		//out.println("ich bin in adden");
    		Ziel = "Freunde.jsp";
    	}
    	
    	//out.println("ich bin wieder draussen");
    	
    	response.sendRedirect(Ziel);
        //processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request - aktueller User und dessen username
     * @param response servlet response - liste der Freunde, die User hat
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	
    	HttpSession session = request.getSession();
    	Serialisierung a = new Serialisierung();
		String username = (String) session.getAttribute("username");
		Person getlistuser = a.getPersonbyid(username);
		session.setAttribute("geffreunde", getlistuser.getFreunde());
    	
		response.sendRedirect("Freunde.jsp"); 
		
        
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
    
    
    public void freundanfragen(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	response.setContentType("text/html");
    	PrintWriter out = response.getWriter();
    	String freundname = request.getParameter("freundname");
    	HttpSession session = request.getSession();
    	String username =(String) session.getAttribute("username");
    	Serialisierung a = new Serialisierung();
    	Person ich = a.getPersonbyid(username);
    	Person freund = a.getPersonbyid(freundname);
    	out.println("Anfragen wird aufgerufen"+freund.getID());
    	if((!(request.getParameter("freundname").isEmpty()))){
    		ich.addgesendeteAnfragen(freund);
    		freund.addeingehendeAnfragen(ich);
    		//if(ich.getgesendeteAnfragen()==null) out.println("Leere Liste");
    		for(Person test: ich.getgesendeteAnfragen()) out.println(test.getID());
    		Person ichneu = ich;
    		Person freundneu = freund;
    		
    		a.loeschePerson(ich);
    		a.speicherePerson(ichneu);
    		a.loeschePerson(freund);
    		a.speicherePerson(freundneu);
    		
    	}
    }
    
    public void freundloeschen(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	response.setContentType("text/html");
    	String freundname = request.getParameter("freundname");
    	HttpSession session = request.getSession();
    	
    	
    	if(!(request.getParameter("freundname").isEmpty())){
    		String adddelete = (String) request.getParameter("adddelete");
    		String zufuegen = "1";
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
    		out.println("Ich bin vor der Auswahl");
    		
    		if(adddelete.equals(zufuegen)){
    			out.println("ich bin in adddelete==1");
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