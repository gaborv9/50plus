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
import Data.Serialisierung;


/**
 *
 * @author master
 */
public class Gruppen extends HttpServlet {
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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

    	response.setContentType("text/html");
	
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String groupname = (String) request.getParameter("gruppenname");
		
		//holt Person aus Serialisierung und setzt Administrator und Mitglieder
		Serialisierung se= new Serialisierung();
		Person administrator=new User();
		ArrayList<Person> mitgl= new ArrayList<Person>();
		administrator=se.getPersonbyid(username);
		mitgl.add(administrator);
		
		
		//erstellt neue Gruppe
		GruppeClass g = new GruppeClass(groupname, administrator, mitgl);
		
		//speichert Gruppe in Serialisierung
		GruppeManagement gr = new GruppeManagement();
		gr.addGruppe(g);			
		
		//erstellt neue Liste mit den eigenen Gruppen
		ArrayList<GruppeClass> gruppelist = new ArrayList<GruppeClass>();
		gruppelist = gr.getOwnGruppelist(username);
		
		
		
		//Nachname von Administrator
		String admin=administrator.getNachname();
		
		session.setAttribute("admin", admin);
		session.setAttribute("grouplist", gruppelist);
		session.setAttribute("gruppenname", groupname);

		
		
		response.sendRedirect("Gruppen.jsp");
    	
    	
        //processRequest(request, response);
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
