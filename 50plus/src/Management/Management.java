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
import Personen.User;

/**
 * 
 * @author master
 */
@SuppressWarnings("serial")
public class Management extends HttpServlet {

	/**
	 * Handles the HTTP <code>GET</code> method. Alters the role
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
		HttpSession session = request.getSession();
		String onlineuser = (String) session.getAttribute("username");
 
		if (request.getParameter("picturelink")!=null) {
			PersonManagement a= new PersonManagement();
			a.changelink(a.getPerson(onlineuser), request.getParameter("picturelink"));
		//	session.setAttribute("picturelink", a.getPerson(onlineuser));
			response.sendRedirect("Profil.jsp");
		} 
		if (request.getParameter("role") != null && request.getParameter("username") !=null) {
			int role = Integer.parseInt(request.getParameter("role"));
			String username = request.getParameter("username");
		
			PersonManagement a = new PersonManagement();
			
	
			if ((a.getPerson(onlineuser)).getRole() == 1) { // Handelt sich um einen Admin, dann fuehre aus
				a.changerole(a.getPerson(username), role);
			}
			else{
				 
			}
			
		response.sendRedirect("Profil.jsp");
		}
	 
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
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
		  
		String password = request.getParameter("password");
		String vorname = request.getParameter("vorname");
		String nachname = request.getParameter("nachname");
		String year = request.getParameter("datum3");
		String month = request.getParameter("datum2");
		String day = request.getParameter("datum1");

		HttpSession session = request.getSession();
		PersonManagement ser = new PersonManagement();
		String usernamenow =(String) session.getAttribute("username");
		Person a= ser.getPerson((String) session.getAttribute("username"));
		Person temp = a;
		Person p = a;
		
		try{
			ser.delete(usernamenow);
			p.setVorname(vorname);
			p.setNachname(nachname);
			p.setPW(password);
			p.setDatum(year, month, day);
			ser.add(p);
			response.sendRedirect("Profil.jsp");
		}
		catch(Exception e){
			ser.add(temp);
			out.println(e.getMessage());
			out.println("<a href=\"/50plus/Profil.jsp\">Hier klicken um zum Profil zurückzukommen</a>");
		}
		
	}
 
}
