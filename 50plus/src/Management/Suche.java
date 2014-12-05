/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

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

/**
 *
 * @author master
 */
public class Suche extends HttpServlet {

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
    @SuppressWarnings({ "unused", "null" })
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         String suche= request.getParameter("suche");
         Serialisierung a = new Serialisierung();
         ArrayList<Person> perliste = new ArrayList<Person>();
         //ArrayList<Gruppe> grpliste = new ArrayList<Gruppe>();
         perliste = a.getPersonList();
         //grpliste = a.getGruppenList();
         //int zaehler = perliste.size();
         ArrayList<Person> gefperliste = null;
         //ArrayList<Gruppe> gefgrpliste;
         
         for(Person test: perliste){
        	 if(test.getID().equals(suche)) gefperliste.add(test); 
         }
        /* for(Gruppe test: grpliste){
        	 if(test.getName().equals(suche)) gefgrpliste.add(test);
         }*/
         
         
         response.setContentType("text/html");
         PrintWriter out = response.getWriter();
         out.println("Personen: ");
       	 if(gefperliste==null) out.println("Keine Treffer!");
       	 else{
       		 for(Person test: gefperliste){
       			 out.println(test.getID()+" "+test.getVorname()+" "+test.getNachname());
       		 }
       	 }
       		 out.println("Gruppen: ");
       	 
         /*for(Gruppe test: gefgrpliste){
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
