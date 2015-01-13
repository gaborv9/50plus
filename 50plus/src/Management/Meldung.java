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

import Data.Serialisierung;
import Personen.Person;

/**
 * Servlet implementation class Meldung
 */
@WebServlet("/Meldung")
public class Meldung extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Meldung() {
        super();
        // TODO Auto-generated constructor stub
    }
    
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
            out.println("<h1>Das findest du unter processRequest, Meldung Servlet"+ "</h1>");
           
            out.println("</body>");
            out.println("</html>");
        }
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		Serialisierung a = new Serialisierung();
		HttpSession session = request.getSession();
		String username =(String) session.getAttribute("username");
    	String personname = request.getParameter("personname");
    	String wunsch = request.getParameter("wunsch");
    	PrintWriter out = response.getWriter();
    	String Ziel = "Admin.jsp";
    	String bitte = "nichterlaubt";
    	Person ich = a.getPersonbyid(username);
    	Person person = a.getPersonbyid(personname);
    	
    	if(wunsch=="meldung"){
    		meldungmachen(person,ich);
    		out.println("ich komme bis zur meldung");
    		Ziel="Suche.jsp";
    	}
    	
    	if(wunsch=="befsperren"){
    		int zeit = Integer.parseInt(request.getParameter("zeit"));
    		befsperren(person,zeit);
    		Ziel = "Admin.jsp";
    	}
    	if(wunsch=="unbefsperren"){
    		unbefsperren(person);
    		Ziel = "Admin.jsp";
    	}
    	if(wunsch=="liste"){
    		ArrayList<Person> sperrliste = sperrliste();
    		session.setAttribute("sperrliste",sperrliste);
    		if(ich.getRole()==1) bitte = "personensperren";
    		Ziel = "Admin.jsp";
    	}
    	
    	session.setAttribute("bitte", bitte);
    	response.sendRedirect(Ziel);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	public void meldungmachen(Person person, Person ich){
		Serialisierung a = new Serialisierung();
		int anzmeldung = 0;
		ArrayList<Person> meldung = person.getgemeldetvon();
		boolean pruef = false;
		for(Person test: meldung){
			if(test.getID()==ich.getID()) pruef=true;
		}
		if(pruef==false){
			meldung.add(ich);
		}
		ich.setgemeldetvon(meldung);
		anzmeldung=ich.getmeldunganz();
		if(pruef==false){
			anzmeldung++;
		}
		ich.setmeldunganz(anzmeldung);
		Person ichneu = ich;
		a.loeschePerson(ich);
		a.speicherePerson(ichneu);
	}
	
	public void befsperren(Person person, int zeit){
		person.setsperrdatum(zeit);
	}
	
	public void unbefsperren(Person person){
		Serialisierung a = new Serialisierung();
		a.loeschePerson(person);
	}
	public ArrayList<Person> sperrliste(){
		Serialisierung a = new Serialisierung();
		ArrayList<Person> sperrlisteall = new ArrayList<Person>();
		sperrlisteall=a.getPersonList();
		ArrayList<Person> sperrlisteneu = new ArrayList<Person>();
		for(Person test:sperrlisteall){
			if(test.getmeldunganz()>0) sperrlisteneu.add(test);
		}
		return sperrlisteneu;
	}
	

}
