/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Personen.Person;
import Personen.User;

/**
 * 
 * @author master
 */
public class Register extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
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
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			/* TODO output your page here. You may use following sample code. */
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet Register</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Servlet Register at " + request.getContextPath()
					+ "</h1>");

			out.println("</body>");
			out.println("</html>");
		}
	}

	// <editor-fold defaultstate="collapsed"
	// desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
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
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method. Registers and saves a new User.
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
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String username = request.getParameter("username");
		String password = request.getParameter("passwort");
		String vorname = request.getParameter("vorname");
		String nachname = request.getParameter("nachname");
		String year = request.getParameter("datum3");
		String month = request.getParameter("datum2");
		String day = request.getParameter("datum1");
	

		PersonManagement a = new PersonManagement();
		Person p = new User();
		ArrayList<Person> list = new ArrayList<>();
		p.setArrayList(list);
		
		int fc = 0; // failcheck
		if (p.setID(username) == 0) {
			out.println("Username darf nicht mehr als 25 Zeichen haben und darf nicht leer sein");
			out.println("<a href=\"/50plus/index.jsp\">Hier klicken um zur Startseite zurückzukommen</a>");
			fc = 1;
		}
		if (p.setVorname(vorname) == 0) {
			out.println("Vorname darf nicht mehr als 25 Zeichen haben und darf nicht leer sein");
			out.println("<a href=\"/50plus/index.jsp\">Hier klicken um zur Startseite zurückzukommen</a>");
			fc = 1;

		}
		if (p.setNachname(nachname) == 0) {
			out.println("Nachname darf nicht mehr als 25 Zeichen haben und darf nicht leer sein");
			out.println("<a href=\"/50plus/index.jsp\">Hier klicken um zur Startseite zurückzukommen</a>");
			fc = 1;
		}
		if (p.setPW(password) == 0) {
			out.println("Passwort darf nicht mehr als 25 oder weniger als 6 Zeichen haben");
			out.println("<a href=\"/50plus/index.jsp\">Hier klicken um zur Startseite zurückzukommen</a>");
			fc = 1;
		}
		if (p.setDatum(year, month, day) == 0) {
			out.println("Sie müssen alt genug sein und ein korrektes Datum eingeben!");
			out.println("<a href=\"/50plus/index.jsp\">Hier klicken um zur Startseite zurückzukommen</a>");
			fc = 1;
		}
		if (username.equals("admin1")){
			p.setRole(1);
		}

		if (a.add(p) == 0) {
			out.println("username schon vorhanden");
			out.println("<a href=\"/50plus/index.jsp\">Hier klicken um zur Startseite zurückzukommen</a>");
			fc = 1;
		} else {
			out.println("<a href=\"/50plus/index.jsp\"> Hier klicken um zur Startseite zurückzukommen</a>");
		}

		if (fc == 1) {
			a.delete(username);
		}

		// Bessere Darstellung wäre gut, funktionieren tut es mal. Mit einem
		// direkten Forward könnte man gleich auf die Index-Seite verweisen.

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
