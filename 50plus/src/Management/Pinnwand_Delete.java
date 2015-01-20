package Management;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Personen.Post;

/**
 * Mit Servlet Pinnwand_Delete kann erfolgt das Loeschen von Posts
 */

@WebServlet("/Pinnwand_Post_Delete")
public class Pinnwand_Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * Default Konstruktor
     */
    public Pinnwand_Delete() 
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
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");	
		
		int postNumber =  Integer.parseInt(request.getParameter("postNumber"));

		PinnwandManagement pm = new PinnwandManagement();
		
		if (pm.getPostNumbers(username).contains(postNumber))
		{
			pm.deletePost(postNumber);
			session.setAttribute("postDeleteSuccess", "success");
		}
		else
		{
			session.setAttribute("postDeleteSuccess", "noSuccess");
		}

		ArrayList<Post> postlist = pm.getOwnpostlist(username);
		session.setAttribute("postlist", postlist);
		response.sendRedirect("Pinnwand.jsp");

	}

    /**
     * auskommentiert, eine zweite Loesung fuer Loeschen mit Eingeben der Nummer des Posts
     * Handles the HTTP POST method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		/*behalten nur fuer mich (Gabor)/zweite Loesung fuer Loeschen mit Eingeben der Nummer des Posts
		 
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");	
		
		try
		{
			int postNumber =  Integer.parseInt(request.getParameter("postNumber"));
		
			
			if (pm.getPostNumbers(username).contains(postNumber))
			{
				pm.deletePost(postNumber);
				session.setAttribute("postDeleteSuccess", "success");
			}
			else
			{
				session.setAttribute("postDeleteSuccess", "noSuccess");
			}
	
		
		}
		catch (NumberFormatException ex)
		{
			session.setAttribute("postDeleteSuccess", "notNumber");
		}
		finally
		{
			ArrayList<Post> postlist = pm.getOwnpostlist(username);
			session.setAttribute("postlist", postlist);
			response.sendRedirect("Pinnwand.jsp");
		}


		in Pinnwand.jsp kopieren
		
				    <form action="Pinnwand_Delete" method="post"> 
		            
		           	<input type="text" name="postNumber">
		            
		            <input type="submit" name="delete" value="delete"> 
					<%
						if (postDeleteSuccess.equals("noSuccess"))
						{
							out.println("Sie haben kein Post mit dieser Nummer.");
						}
						else if(postDeleteSuccess.equals("success"))
						{
							out.println("Post geloescht.");
						}
						else if(postDeleteSuccess.equals("notNumber"))
						{
							out.println("Sie haben keine Nummer eingegeben!");
						}						
							
			
					
					%>			            
					</form> 

		*/
	}

}