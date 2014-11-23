package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta charset=\"utf-8\">\n");
      out.write("        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("        <meta name=\"description\" content=\"\">\n");
      out.write("        <meta name=\"author\" content=\"\">\n");
      out.write("        <link rel=\"icon\" href=\"dist/docs/favicon.ico\">\n");
      out.write("        <title>Login</title>\n");
      out.write("\n");
      out.write("        <!-- Bootstrap core CSS -->\n");
      out.write("        <link href=\"dist/css/bootstrap.min.css\" rel=\"stylesheet\">\n");
      out.write("\n");
      out.write("        <!-- Custom styles for this template -->\n");
      out.write("        <link href=\"dist/css/signin.css\" rel=\"stylesheet\">\n");
      out.write("\n");
      out.write("        <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->\n");
      out.write("        <!--[if lt IE 9]><script src=\"../../assets/js/ie8-responsive-file-warning.js\"></script><![endif]-->\n");
      out.write("        <script src=\"dist/docs/assets/js/ie-emulation-modes-warning.js\"></script>\n");
      out.write("\n");
      out.write("        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->\n");
      out.write("        <!--[if lt IE 9]>\n");
      out.write("          <script src=\"https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js\"></script>\n");
      out.write("          <script src=\"https://oss.maxcdn.com/respond/1.4.2/respond.min.js\"></script>\n");
      out.write("        <![endif]-->\n");
      out.write("    </head>\n");
      out.write("\n");
      out.write("    <body>\n");
      out.write("\n");
      out.write("        <!--   <div class=\"container\"> -->\n");
      out.write("        <form class=\"form-signin\" method=\"post\" action=\"Login\" role=\"form\">\n");
      out.write("            <h2 class=\"form-signin-heading\">Bitte einloggen:</h2>\n");
      out.write("\n");
      out.write("\n");
      out.write("            <input type=\"text\" class=\"form-control\" name=\"username\" placeholder=\"Nutzername\" required autofocus>\n");
      out.write("            <input type=\"password\" class=\"form-control\" name=\"password\" placeholder=\"Passwort\" required>\n");
      out.write("            <!-- <div class=\"checkbox\"> -->\n");
      out.write("            <label>\n");
      out.write("                <input type=\"checkbox\" value=\"remember\"> Eingabe speichern\n");
      out.write("            </label>\n");
      out.write("            <!-- </div> -->\n");
      out.write("\n");
      out.write("            <button class=\"btn btn-lg btn-primary btn-block\" type=\"submit\">Login</button>\n");
      out.write("        </form>\n");
      out.write("\n");
      out.write("        <form class=\"form-signin\" method=\"post\" action=\"Register\" role=\"form\">\n");
      out.write("            <h2 class=\"form-signin-heading\">Registrieren:</h2>\n");
      out.write("\n");
      out.write("            <div class=\"form-group\">\n");
      out.write("                <label for=\"Nutzername\">Nutzername</label>\n");
      out.write("                <input type=\"nutzername\" class=\"form-control\" name=\"nutzername\" placeholder=\"Nutzername\" required>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <div class=\"form-group\">\n");
      out.write("                <label for=\"Passwort\">Password</label>\n");
      out.write("                <input type=\"passwort\" class=\"form-control\" name=\"passwort\" placeholder=\"Passwort\"required>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <div class=\"form-group\">\n");
      out.write("                <label for=\"Vorname\">Vorname</label>\n");
      out.write("                <input type=\"vorname\" class=\"form-control\" name=\"vorname\" placeholder=\"Vorname\" required>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <div class=\"form-group\">\n");
      out.write("                <label for=\"Nachname\">Nachname</label>\n");
      out.write("                <input type=\"nachname\" class=\"form-control\" name=\"nachname\" placeholder=\"Nachname\"required>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <div class=\"form-group\">\n");
      out.write("                <label for=\"Geburtsdatum\">Geburtsdatum</label>\n");
      out.write("                <input type=\"geburtsdatum\" class=\"form-control\" name=\"datum\" placeholder=\"Geburtsdatum\"required>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <div class=\"checkbox\">\n");
      out.write("                <label>\n");
      out.write("                    <input type=\"checkbox\"> Sie erklären sich hiermit einverstanden, dass alle ihre Daten zu Forschungszwecken weitergeleitet werden, have fun.\n");
      out.write("                </label>\n");
      out.write("            </div>\n");
      out.write("            <button type=\"register\" class=\"btn btn-default\">Registrieren</button>\n");
      out.write("        </form>\n");
      out.write("\n");
      out.write("        <!--  </div> <!-- /container --> \n");
      out.write("\n");
      out.write("        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->\n");
      out.write("        <script src=\"dist/docs/assets/js/ie10-viewport-bug-workaround.js\"></script>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
