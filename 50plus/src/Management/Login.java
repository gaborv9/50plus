
package Management;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Data.Nachrichten_Serialisierung;
import Data.Serialisierung;
import Personen.Nachricht;
import Personen.Person;
import Personen.Post;
import Personen.User;
import Management.PinnwandManagement;

/**Kuemmert sich um das Einloggen von Personen.
 * 
 * @author master
 */
@SuppressWarnings("serial")
public class Login extends HttpServlet {

	
 /**
  * Registers admin.
  */
	public void init(){
	 
		PersonManagement a = new PersonManagement();
		if (a.getPerson("admin")==null){
			Person p = new User();
			 
			p.setID("admin");
			p.setVorname("adminv");
			p.setNachname("adminn");
			p.setPW("123456");
			p.setDatum("1", "1", "1");
			p.setRole(1);
			a.add(p);
		}
	}
	
 
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
		Nachrichten_Serialisierung nser=new Nachrichten_Serialisierung();
		
		ArrayList<Post> postlist = pm.getOwnpostlist(username);
		ArrayList<GruppeClass> grouplist = gr.getOwnGruppelist(username);
		ArrayList<Nachricht> senderliste = nser.getNachrichtenbySender(username);
		ArrayList<Nachricht> empfaengerliste = nser.getNachrichtenbyEmpfaenger(username);
		
		
		PersonDAO a = new Serialisierung();
		GregorianCalendar test = new GregorianCalendar();
		if ((a.getPersonbyid(username) != null) && password.equals(a.getPersonbyid(username).getPW()) && ((!(test.before(a.getPersonbyid(username).getsperrdatum()))||(test.get(GregorianCalendar.DAY_OF_MONTH)==a.getPersonbyid(username).getsperrdatum().get(GregorianCalendar.DAY_OF_MONTH)&&test.get(GregorianCalendar.MONTH)==a.getPersonbyid(username).getsperrdatum().get(GregorianCalendar.MONTH)&&test.get(GregorianCalendar.YEAR)==a.getPersonbyid(username).getsperrdatum().get(GregorianCalendar.YEAR))))) // &&(a.getPersonbyid(username).getsperrdatum()<=test) 
		{
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			session.setAttribute("pinnwandOwner", username);
		 	session.setAttribute("postlist", postlist);
		 	session.setAttribute("grouplist", grouplist);
		 	session.setAttribute("senderliste", senderliste);
		 	session.setAttribute("empfaengerliste", empfaengerliste);
		 	session.setAttribute("postDeleteSuccess", "nichts");
		 	session.setAttribute("anfrage", "keine");
		 	session.setAttribute("bitte", "keine");
		 	
		 	if (a.getPersonbyid(username).getPicturelink() ==null){
		 		session.setAttribute("picturelink","http://placehold.it/150x150&text=BILD");
		 	}else{		
		 	session.setAttribute("picturelink", a.getPersonbyid(username).getPicturelink());
		 	}
			response.sendRedirect("Pinnwand.jsp");
		} 
		else 
		{
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println(username);
			out.println(password);
			
			if((test.before(a.getPersonbyid(username).getsperrdatum()))){ //fuer Gesperrtdatum ausgeben
				GregorianCalendar test2 = a.getPersonbyid(username).getsperrdatum();
				out.println(" Sie sind noch bis "+GregTag(test2)+". "+GregMon(test2)+1+". "+GregJahr(test2)+" gesperrt!");
			}
			else{
			
				out.println("falsche Eingabe");
			}
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
	
	public int GregTag(GregorianCalendar test3){
        return test3.get(GregorianCalendar.DAY_OF_MONTH);
    }
   
    public int GregMon(GregorianCalendar test3){
        return test3.get(GregorianCalendar.MONTH);
    }
       
    public int GregJahr(GregorianCalendar test3){
        return test3.get(GregorianCalendar.YEAR);
    }

}
