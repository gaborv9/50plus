package Management;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Data.Post_Serialisierung;
import Personen.Person;
import Personen.Post;

/**
 * Mit Servlet Pinnwand erfolgt das Posten, Loeschen, Melden der Posts und das Zurueckgeben des gemeldeten Posts
 */

public class Pinnwand extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * Default Konstruktor von Pinnwand
     */
    public Pinnwand() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Handles the HTTP GET method. Hier erfolgt das Loeschen, Melden der Posts und das Zurueckgeben des gemeldeten Posts
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");	
		String pinnwandOwner = request.getParameter("pinnwandOwner");
		
				
		PinnwandManagement pm = new PinnwandManagement();
		
		//Delete
		int postNumber_delete = (request.getParameter("postNumber_delete") == null) ? 0 : Integer.parseInt(request.getParameter("postNumber_delete"));
		if (postNumber_delete != 0)
		{
			pm.loeschePost(postNumber_delete);
		}
		
		//Melden
		int postNumber_melden = (request.getParameter("postNumber_melden") == null) ? 0 : Integer.parseInt(request.getParameter("postNumber_melden"));
		
		if (postNumber_melden != 0)
		{
			pm.changeFlag(true, postNumber_melden);
		}
		
		
		ArrayList<Post> postlist = pm.getOwnpostlist(pinnwandOwner);
		
		//wenn Post gemeldet ist, dann sortieren
		if (postNumber_melden != 0)
		{
						
			//sortieren nach postNumber=ownPostCounter, weil ich die Attribute eines Posts geaendert habe, den alten Post geloescht habe, dann habe ich den neuen, geanderten Post gespeichert,
			//so wird die Reihenfolge in der Datei geaendert
			//http://stackoverflow.com/questions/2784514/sort-arraylist-of-custom-objects-by-property
			class counterComparator implements java.util.Comparator<Post> 
			{
			    public int compare(Post p1, Post p2) {
			        return p1.getOwnPostcounter() - p2.getOwnPostcounter() ;
			    }
			}	
			
			Collections.sort(postlist, new counterComparator());
			
		}
				
		PersonManagement perman = new PersonManagement();
		Person p = perman.getPerson(username);
		int role = p.getRole();

		session.setAttribute("role", role);
		
		//Postmeldung
		int getFlaggedPosts = (request.getParameter("getFlaggedPosts") == null) ? 0 : Integer.parseInt(request.getParameter("getFlaggedPosts"));
		
		if(getFlaggedPosts != 0)
		{
			ArrayList<Post> flaggedPostlist = pm.getFlaggedPostlist();
			
			session.setAttribute("flaggedPostlist", flaggedPostlist);
			response.sendRedirect("Admin.jsp");
		}	
		else
		{
			session.setAttribute("pinnwandOwner", pinnwandOwner);
			session.setAttribute("postlist", postlist);
			response.sendRedirect("Pinnwand.jsp");
		}
   }

    /**
     * Handles the HTTP POST method. Hier erfolgt das Erstellen des Posts.
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
		String pinnwandOwner = request.getParameter("pinnwandOwner");
		
		//requests von Pinnwand.jsp
		String inhalt = request.getParameter("inhalt");
		String zeitpunkt = new SimpleDateFormat("dd.MM.yyyy HH:mm").format(Calendar.getInstance().getTime());
		
		if(!inhalt.isEmpty())
		{
			//neues Post wird erstellt
			Post p = new Post(username, inhalt, zeitpunkt, pinnwandOwner);
			int postNumber = 0;
			
			//Post wird spreichert
		    PinnwandManagement pm = new PinnwandManagement();
			pm.speicherePost(p, postNumber);
		    
			//neue postlist wird erstellt und aktualisiert und in session gesetzt
		    ArrayList<Post> postlist = pm.getOwnpostlist(pinnwandOwner);
 		    
		    class counterComparator implements java.util.Comparator<Post> 
			{
			    public int compare(Post p1, Post p2) {
			        return p1.getOwnPostcounter() - p2.getOwnPostcounter() ;
			    }
			}	
			
			Collections.sort(postlist, new counterComparator());
			    
		    session.setAttribute("postlist", postlist);
 
		}
		response.sendRedirect("Pinnwand.jsp");
 	}

}
