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
import Personen.Post;
import Management.PinnwandManagement;

/**
 * 
 * @author master
 */
public class Login extends HttpServlet {

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
			out.println("<title>Servlet Login</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Servlet Login at " + request.getContextPath()
					+ "</h1>");
			out.println("</body>");
			out.println("</html>");
		}
	}

	// <editor-fold defaultstate="collapsed"
	// desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method. Invalidates session.
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

		if (request.getParameter("logout")!= null) {

			request.getRequestDispatcher("index.jsp").include(request, response);

			HttpSession session = request.getSession();
			session.invalidate();

			// response.sendRedirect("index.jsp");
		}

		// processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method. Sets attributes to the session.
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

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//String groupname = request.getParameter("gruppenname");
				
		PinnwandManagement pm = new PinnwandManagement();
		GruppeManagement gr = new GruppeManagement();
		
		ArrayList<Post> postlist = pm.getOwnpostlist(username);
		ArrayList<GruppeClass> grouplist = gr.getOwnGruppelist(username);
		
		
		
		Serialisierung a = new Serialisierung();
		if ((a.getPersonbyid(username) != null) && password.equals(a.getPersonbyid(username).getPW())) 
		{
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
		 	session.setAttribute("postlist", postlist);
		 	session.setAttribute("grouplist", grouplist);
		 	session.setAttribute("grouplist", grouplist);
		 	session.setAttribute("postDeleteSuccess", "nichts");
		 	
			response.sendRedirect("Pinnwand.jsp");
		} 
		else 
		{
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println(username);
			out.println(password);
			out.println("falsche Eingabe");

		}
	}

	// processRequest(request, response);

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
