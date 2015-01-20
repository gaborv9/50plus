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

/**Register ist fuer die Aufnahme der Daten von Personen zustaendig.
 * 
 * @author master
 */
public class Register extends HttpServlet {

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
		
		try{
			p.setID(username);
			p.setVorname(vorname);
			p.setNachname(nachname);
			p.setPW(password);
			p.setDatum(year, month, day);
			p.setRole(3);
			a.add(p);
			out.println("Registierung erfolgreich.");
			out.println("<a href=\"/50plus/index.jsp\">Hier klicken um zur Startseite zurückzukommen</a>");
		}
		catch(Exception e){
			out.println(e.getMessage());
			out.println("<a href=\"/50plus/index.jsp\">Hier klicken um zur Startseite zurückzukommen</a>");
		
		}
		
	}
}
