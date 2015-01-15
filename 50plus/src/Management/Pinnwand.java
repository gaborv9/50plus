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

import Data.Post_Serialisierung;
import Personen.Post;

/**
 * Mit Servlet Pinnwand erfolgt das Posten von Posts
 */

public class Pinnwand extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * Default Konstruktor
     */
    public Pinnwand() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Handles the HTTP GET method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
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
		//String username = request.getParameter("user");
		
		//requests von Pinnwand.jsp
		String inhalt = request.getParameter("inhalt");
		String zeitpunkt = new SimpleDateFormat("dd.MM.yyyy HH:mm").format(Calendar.getInstance().getTime());
		
		if(!inhalt.isEmpty())
		{
			//neues Post wird erstellt
			Post p = new Post(username, inhalt, zeitpunkt);
			int postNumber = 0;
			
			//Post wird spreichert
		    PinnwandManagement pm = new PinnwandManagement();
			pm.addPost(p, postNumber);
		    
			//neue postlist wird erstellt und aktualisiert und in session gesetzt
		    ArrayList<Post> postlist = pm.getOwnpostlist(username);
 		    
		    session.setAttribute("postlist", postlist);
 
		}
		  response.sendRedirect("Pinnwand.jsp");
 	}

}
