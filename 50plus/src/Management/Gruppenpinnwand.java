package Management;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Personen.GruppePost;
import Management.GruppePostManagement;
import Data.GruppenPost_Serialisierung;

/**
 * Mit Servlet Gruppenpinnwand erfolgt das Posten von Posts in einer Gruppe
 */

public class Gruppenpinnwand extends HttpServlet 
{
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
    	
    	response.setContentType("text/html");
    	PrintWriter out = response.getWriter();
    	HttpSession session = request.getSession();
    	String Ziel = "Gruppenpinnwand.jsp";
    	
    	String wunsch = request.getParameter("wunsch");
    	String gruppenname=request.getParameter("gn");
    	session.setAttribute("groupname", gruppenname);
    	
    	if(wunsch.equals("pinnwand")){
    		Ziel = "Gruppenpinnwand.jsp";
    	}
    	
    	GruppePostManagement gm = new GruppePostManagement();
    	ArrayList<GruppePost> gplist = new ArrayList<GruppePost>();
		gplist=gm.getGroupPostlist(gruppenname);
		session.setAttribute("gplist", gplist);
    	
    	session.setAttribute("gn", gruppenname);
    	
    	response.sendRedirect(Ziel);
        //processRequest(request, response);
    }

    /**
     * Handles the HTTP POST method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
	
		HttpSession session = request.getSession();
		
		String username = (String) session.getAttribute("username");
		String gn = (String) session.getAttribute("gn");
		String inhalt = request.getParameter("inhalt");
		String zeitpunkt = new SimpleDateFormat("dd.MM.yyyy HH:mm").format(Calendar.getInstance().getTime());

		
		if(!inhalt.isEmpty())
		{
			//neuer GruppePost wird erstellt
			GruppePost p = new GruppePost(username, inhalt, zeitpunkt, gn);
			
			//Post wird gespeichert
		    GruppePostManagement gpm = new GruppePostManagement();
			gpm.addGruppenPost(p);
		    
			ArrayList<GruppePost> gplist = new ArrayList<GruppePost>();
			gplist=gpm.getGroupPostlist(gn);
			
			session.setAttribute("gplist", gplist);
 
		}
		  response.sendRedirect("Gruppenpinnwand.jsp");
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
