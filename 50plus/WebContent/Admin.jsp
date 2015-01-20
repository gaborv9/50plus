<%-- 
    Document   : Admin
    Created on : 13.01.2015, 15:37:13
    Author     : master
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>50plus</title>
<!-- Bootstrap core CSS -->
<link href="dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="dist/css/signin.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="dist/docs/assets/js/ie-emulation-modes-warning.js"></script>
<!-- Bootstrap Core CSS -->
<link href="dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<!--flush="true"/>;-->
<link href="dist/css/logo-nav.css" rel="stylesheet">

	<!-- jQuery -->
	<script src="dist/js/jquery-1.11.1.js"></script>
<style type="text/css">
#placeholder { width: 600px; height: 250px; }
</style>
 
<!--[if lte IE 8]><script type="text/javascript" language="javascript" src="flot/excanvas.min.js"></script><![endif]-->
<script type="text/javascript" language="javascript" src="flot/jquery.flot.js"></script>
 

</head>

<body>
		<%@ page import="java.util.ArrayList" import="Personen.Person, Personen.Post"%>
	<!-- Navigation -->
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#"> <img
					src="<%out.print(session.getAttribute("picturelink"));%>" alt="">
				</a>

			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->

			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">

				<ul class="nav navbar-nav">
						<li><a href="/50plus/Pinnwand?pinnwandOwner=<%= (String) session.getAttribute("username")%>">Pinnwand</a></li>
						<li><a href="/50plus/Gruppen.jsp">Gruppen</a></li>
						<li><a href="/50plus/Nachrichten.jsp">Nachrichten</a></li>
						<li><a href="/50plus/Freunde.jsp">Freunde</a></li>
						<li><a href="/50plus/Forschung.jsp">Forschung</a></li>
						<li><a href="/50plus/Profil.jsp">Profil</a></li>
						<li><a href="/50plus/Admin.jsp">Admin</a></li>
						<li><a href="/50plus/Login?logout=true">Logout</a></li>
					<form class="form-signin" method="post" action="Suche" role="form">
						<div class="form-group">
							<input type="suche" class="form-control" name="suche"
								placeholder="Freunde/Gruppen finden..">
						</div>
					</form>
				</ul>

			</div>

			<!-- /.navbar-collapse -->
			<span style="color: grey; padding-left: 40px"> <%=(String) session.getAttribute("username")%>
			</span>
		</div>

		<!-- /.container -->
	</nav>


	<!-- Page Content -->


	<div class="container">
		<div class="row">
			<div class="col-lg-12" id="content">
				<br> <br>

				<%
					
					if (session.getAttribute("username") == null) {

						response.sendRedirect("index.jsp");
					}
				%>

				
				<a class="btn btn-default"
					href="/50plus/Meldung?wunsch=liste" role="button">gemeldete Personen anzeigen</a>
				<br />

				<%
					if (session.getAttribute("bitte").equals("nichts")) {
					}

					else if (session.getAttribute("bitte").equals("nichterlaubt")) {
				%>
				Nur fuer Administratoren verfuegbar!

				<%
					}
					else if(session.getAttribute("bitte").equals("personensperren")){
						ArrayList<Person> sperrliste =(ArrayList<Person>) session.getAttribute("sperrliste");
						if(sperrliste.size()==0){
							out.println("Es wurden noch keine Personen gemeldet.");
						}
						else{
							for(Person test:sperrliste){
								out.print("User "+test.getID()+" wurde "+test.getmeldunganz()+" mal gemeldet.");
								session.setAttribute("freundname",test.getID());
									%>
									<br>
									
									Tage befristet sperren:
									<div class="input-group">
										<form class="form-search" method="get" action="Meldung"
											role="form">
											<input type="text" name="zeit" class="form-control"
												placeholder="Anzahl der Tage">
										</form>
									<a class="btn btn-default" href="/50plus/Meldung?wunsch=unbefsperren&freundname=<%=test.getID()%>" role="button">unbefristet sperren</a>
									<a class="btn btn-default" href="/50plus/Meldung?wunsch=meldenbeheben&freundname=<%=test.getID()%>" role="button">aus Liste entfernen</a>
									
									</div>
									<br>
								<% 
								
							}
							
		
							
						}
					}
				%>
				<br>
				<br>
				<br>
				
		
				<a class="btn btn-default" href="/50plus/Pinnwand?getFlaggedPosts=1" role="button">gemeldete Posts anzeigen</a>
			    
			    <br/>

			<%
	
			    Integer role =  (session.getAttribute("role") == null) ? 0 : (Integer)(session.getAttribute("role"));
			    
			
			
				if (role == 1) 
				{
					
					ArrayList<Post> flaggedPostlist = (ArrayList<Post>) session.getAttribute("flaggedPostlist");
					
					
					if(flaggedPostlist == null)
					{
						
					}
					else if(flaggedPostlist.size() == 0)
					{
						out.println("Es wurden noch keine Posts gemeldet.");
					}
					else
					{
						for (int i = 0; i < flaggedPostlist.size(); i++)
						{
							out.println(flaggedPostlist.get(i).getUsername() + ", ");
							//out.println(flaggedPostlist.get(i).getPinnwandOwner() + ", ");
							out.println(flaggedPostlist.get(i).getZeitpunkt()); 
							//out.println(flaggedPostlist.get(i).getOwnPostcounter()); 
							
							%>
							<div class="panel panel-default">
								<div class="panel-body" style="width: 1100px; word-wrap: break-word"><% out.println(flaggedPostlist.get(i).getInhalt()); %></div>
							</div>
				  
						    <a href="/50plus/Pinnwand?postNumber_delete=<%= (flaggedPostlist.get(i).getOwnPostcounter())%>&getFlaggedPosts=1">Delete</a>
						    <br>
						    <br>
						    <%
							
						}
					}
								
				}
				else if(role == 2 || role == 3)
				{
					out.println("Nur fuer Administratoren verfuegbar!");
				}
				else{}
			
			
				
				
			%>
				
			</div>
		</div>
	</div>
	<!-- /.container -->
	<!-- Bootstrap Core JavaScript -->
	<script src="dist/js/bootstrap.min.js"></script>
</body>
</html>