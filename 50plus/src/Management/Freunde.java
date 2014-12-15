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
 * @author master
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        	
    	response.setContentType("text/html");
    	String freundname = request.getParameter("freundname");
    	HttpSession session = request.getSession();
    	if(!(request.getParameter("freundname").isEmpty())){
    		
    		String username = (String) session.getAttribute("username");
    		Serialisierung a = new Serialisierung();
    		Person test_1,test_2;
    		test_1 = a.getPersonbyid(freundname);
    		test_2 = a.getPersonbyid(username);
    		
    		test_2.setFreunde(test_1);
    		
    	}
    	response.sendRedirect("Suche.jsp");
        
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
    	
    	response.setContentType("text/html");
    	HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		Serialisierung a = new Serialisierung();
		ArrayList<Person> perliste = new ArrayList<Person>();
		perliste = a.getPersonList();
		
		Person test = a.getPersonbyid(username);
		perliste = test.getFreunde();
		ArrayList<Person> geffreunde = new ArrayList<Person>();
		for(Person test2: perliste){
			geffreunde.add(test2);
		}
		session.setAttribute("geffreunde", geffreunde);
		
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
