/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Management.GruppeClass;
import Personen.Nachricht;
import Personen.Person;
import Personen.User;
import Data.Gruppe_Serialisierung;
import Data.Nachrichten_Serialisierung;
import Data.Serialisierung;


/**
 *
 * @author master
 */
public class Nachrichten extends HttpServlet {
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
    	PrintWriter out = response.getWriter();
    	HttpSession session = request.getSession();
    	
    	String sender = (String) session.getAttribute("username");
		String empfaenger = request.getParameter("empf");
		session.setAttribute("empf", empfaenger);
		
		
    	response.sendRedirect("Nachrichtenneu.jsp");
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
    	
		String sender = (String) session.getAttribute("username");
		String empfaenger = request.getParameter("empf");
		String empf=(String) session.getAttribute("empf");
		String inhalt = request.getParameter("inhalt");
		String zeitpunkt = new SimpleDateFormat("dd.MM.yyyy HH:mm").format(Calendar.getInstance().getTime());
		
		if(empfaenger==null){
			empfaenger=empf;
		}
		
		Nachrichten_Serialisierung nser=new Nachrichten_Serialisierung();

		//holt sich Empfänger Person aus Serialisierung
		Person p=new User();
		Serialisierung ser=new Serialisierung();
		p=ser.getPersonbyid(empfaenger);
		
		if(p==null){
			out.println("Dieser Nutzer ist uns leider nicht bekannt! Bitte überprüfen Sie Ihre Eingabedaten!");
		}
		else{
			//erstellt neue Nachricht
			Nachricht nachricht=new Nachricht(sender, empfaenger, inhalt, zeitpunkt);
		
			//Nachricht speichern in Nachrichten_Serialisierung
			nser.speichereNachricht(nachricht);

			System.out.println("Nachricht gespeichert!");
		}

		
		//holt Nachrichten des Senders (Postausgang)
		String username=(String) session.getAttribute("username");//aktuell angemeldeter user
		ArrayList<Nachricht> senderliste = new ArrayList<Nachricht>();
		senderliste = nser.getNachrichtenbySender(username);
		
		//holt Nachrichten des Empfaengers (Posteingang)
		ArrayList<Nachricht> empfaengerliste = new ArrayList<Nachricht>();
		empfaengerliste = nser.getNachrichtenbyEmpfaenger(username);
		
		
		//in Session speichern
		session.setAttribute("senderliste", senderliste);
		session.setAttribute("empfaengerliste", empfaengerliste);
		
		
    	response.sendRedirect("Nachrichten.jsp");
		

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
