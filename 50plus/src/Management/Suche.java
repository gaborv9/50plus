/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

import Data.Gruppe_Serialisierung;
import Data.Serialisierung;
import Personen.Person;
import Personen.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lechl Bernhard
 */
public class Suche extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request - Beschreibung was Servlet macht
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
            out.println("<title>Servlet Suche</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Suche at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request - aktueller User wird abgefragt, sowie Freund der gesucht wird
     * @param response servlet response - zurueckgegeben wird: true = sind befreundet, false = nicht befreundet
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        processRequest(request, response);
        response.setContentType("text/html");
    	HttpSession session = request.getSession();
    	if(!(request.getParameter("freundname").isEmpty())){
    		
    		String freundname = request.getParameter("freundname");
    		String username2 = (String) session.getAttribute("username");
    		Serialisierung a = new Serialisierung();
    		ArrayList<Person> perliste2 = new ArrayList<Person>();
    		perliste2 = a.getPersonList();
    		for(Person test: perliste2){
    			if(test.getID().equals(freundname)){
    				ArrayList<Person> freundlist = test.getFreunde();
    				for(Person test2: freundlist){
    					if(username2==test2.getID()){
    						session.setAttribute("ist_befreundet", true);
    						break;
    					}
    				}
    			}
    			session.setAttribute("ist_befreundet",false);
    		}
		
    	}
    	response.sendRedirect("Suche.jsp");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request - aktueller User, Suchbegriff
     * @param response servlet response - Liste der gefundenen Gruppen, Liste der gefunden Personen, aktueller User
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		 response.setContentType("text/html");
		 HttpSession session = request.getSession();
		 String username = (String) session.getAttribute("username");
		 
		 
         String suche= request.getParameter("suche");
         Serialisierung a = new Serialisierung();
         Gruppe_Serialisierung b = new Gruppe_Serialisierung();
         ArrayList<Person> perliste = new ArrayList<Person>();
         ArrayList<GruppeClass> grpliste = new ArrayList<GruppeClass>();
         perliste = a.getPersonList();
         grpliste = b.getGruppenlist();
         ArrayList<Person> gefperliste = new ArrayList<Person>();
         ArrayList<GruppeClass> gefgrpliste = new ArrayList<GruppeClass>();
         
         for(Person test: perliste){
        	 if((test.getID().equals(suche)||test.getVorname().equals(suche)||test.getNachname().equals(suche))&&(!(suche.equals(username)))) gefperliste.add(test); 
         }
        for(GruppeClass test: grpliste){
        	 if(test.getName().equals(suche)) gefgrpliste.add(test);
         }
         
         session.setAttribute("gefperliste",gefperliste);
         session.setAttribute("gefgrpliste",gefgrpliste);
         session.setAttribute("username", username);
         
         response.sendRedirect("Suche.jsp");
         
        /*
         out.println("Personen: ");
       	 if(gefperliste.isEmpty()) out.println("Keine Treffer!");
       	 else{
       		 for(Person test: gefperliste){
       			 out.println(test.getID()+" "+test.getVorname()+" "+test.getNachname());
       		 }
       	 }
       		 out.println("Gruppen: ");
       	 
         for(GruppeClass test: gefgrpliste){
        	 out.println(test.getName());
         }*/
       // processRequest(request, response);
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
