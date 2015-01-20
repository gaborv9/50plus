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

import Management.GruppeClass;
import Personen.Person;
import Personen.User;
import Data.Gruppe_Serialisierung;
import Data.Serialisierung;

/**
 * 
 * Mit Servlet Gruppenmitglieder erfolgt das Anzeigen und Hinzufuegen von Gruppenmitgliedern
 *
 */
public class Gruppenmitglieder extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
            out.println("<title>Servlet Gruppen</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Gruppen at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    	response.setContentType("text/html");
    	PrintWriter out = response.getWriter();
			
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String gn = (String) session.getAttribute("gn");
		String wunsch = request.getParameter("wunsch");
		
		Gruppe_Serialisierung se = new Gruppe_Serialisierung();
		ArrayList<GruppeClass> gruppelist = new ArrayList<GruppeClass>();
		ArrayList<Person> mitgl = new ArrayList<Person>();
		
		
		if(wunsch.equals("anzeigen")){
			gruppelist=se.getGruppenlist();
			for(GruppeClass temp:gruppelist){
				if(temp.getName().equals(gn)){
					mitgl=temp.getMitglied();
				}
			}
			session.setAttribute("mitgl", mitgl);
			response.sendRedirect("Gruppenmitglieder.jsp");
  
    	}
		
    	//processRequest(request, response);
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

    	PrintWriter out = response.getWriter();
    	HttpSession session = request.getSession();
		//String username = (String) session.getAttribute("username");
		String gn = (String) session.getAttribute("gn");
		String nutzername = request.getParameter("nutzername");
		
		
		Gruppe_Serialisierung se = new Gruppe_Serialisierung();//Gruppen Serialisierung
		Serialisierung ser = new Serialisierung();	//Personen Seriailsierung
		ArrayList<GruppeClass> gruppelist = new ArrayList<GruppeClass>();
		ArrayList<Person> mitgl = (ArrayList<Person>) session.getAttribute("mitgl"); //Liste mit Mitglieder einer Gruppe
		Person p = new User();
		GruppeClass gc;
		
		
		p=ser.getPersonbyid(nutzername);	//holt Person Objekt aus Serialisierung
		gc=se.getGruppebyName(gn);			//holt Gruppen Objekt aus Gruppe_Serialisierung
		
		
    	if(gc!=null){
    		se.loescheGruppe(gc);
    		mitgl.add(p);
    		gc.setMitglied(mitgl);
    		se.speichereGruppe(gc);
    	}
    	else{
    	}
    	response.sendRedirect("Gruppenmitglieder.jsp");
		

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
