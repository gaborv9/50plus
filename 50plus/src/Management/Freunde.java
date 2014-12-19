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
    	String Ziel = "Suche.jsp";
	    
    	if(!(request.getParameter("freundname").isEmpty())){
    		String adddelete = (String) request.getParameter("adddelete");
    		String zufuegen = "1";
    		String loeschen = "0";
    		
    		if(adddelete.equals(zufuegen)){
    			String username = (String) session.getAttribute("username");
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
    					Person userneu = useralt;
    					a.loeschePerson(useralt);
    					a.speicherePerson(userneu);
    				}
	 	 
	    	
    			}
    		
    	else if(adddelete.equals(loeschen)){
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
					Person userneu = useralt;
					a.loeschePerson(useralt);
					a.speicherePerson(userneu);
					Ziel = "Freunde.jsp";
					
				
				}
    		}
    	
    	}
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

}
