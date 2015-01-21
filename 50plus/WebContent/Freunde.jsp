<%-- 
    Document   : Freunde
    Created on : 22.11.2014, 15:37:13
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

</head>
<body>
	<%@ page import="java.util.ArrayList" import="Personen.Person"
		import="Management.Freunde" import="Management.PersonManagement"%>
		
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
			<span style="color:grey; padding-left:40px"> <%=(String) session.getAttribute("username") %> </span>	
			<!-- /.navbar-collapse -->
		</div>

		<!-- /.container -->
	</nav>

	<!-- Page Content -->
	<div class="container">
		<div class="row">
			<div class="col-lg-12" id="content">
				<form action="Freunde" method="post">
					<%
					//response.sendRedirect("Freunde.java");
					String username = (String) session.getAttribute("username");
 					PersonManagement a = new PersonManagement();
 					ArrayList<Person> geffreunde = a.getPerson(username).getFreunde();
 					ArrayList<Person> ausstehendeAnfragen = a.getPerson(username).getgesendeteAnfragen();
 					ArrayList<Person> eingehendeAnfragen = a.getPerson(username).geteingehendeAnfragen();
 					boolean prufaus = false;		//puefung auf eingehende und gesendete Anfragen, sowie ob Freund schon existiert
 					boolean prufein = false;
 					boolean freund = false;
 					
 					
 					
 					if(ausstehendeAnfragen==null) prufaus=true;
 					if(ausstehendeAnfragen.size()==0) prufaus=true;
 					
 					if(eingehendeAnfragen==null) prufein=true;
 					if(eingehendeAnfragen.size()==0) prufein=true;

 					if(ausstehendeAnfragen.size()==0) prufaus=true;

 					
 					if(geffreunde.size()==0) freund = true;
 					
					%>
					<h2>Meine ausstehenden Anfragen:</h2>
					
					<% 
					
					
					if(!prufaus){
						for(Person test: ausstehendeAnfragen){
							out.println(test.getID() +" hat noch nicht geantwortet");
						}
					}
					else { out.println("Du hast noch keine Anfragen verschickt.");}
						
					
					%>
					
					<h2>Meine Freundschaftsanfragen:</h2>
					  
				 	<%
				 	
				 	
					if(!prufein){
						for(Person test: eingehendeAnfragen){
							
							%>
							<div class="btn-group">
						<button type="button" data-toggle="dropdown"
							class="btn btn-default dropdown-toggle">
							Akzeptieren <span class="caret"></span>
						</button>
						<ul class="dropdown-menu" method="get" action="Freunde">
							<li><a
								href="/50plus/Freunde?adddelete=1&freundname=<%=test.getID()%>&wunsch=zufuegen">Akzeptieren</a></li>
							 <li><a
								href="/50plus/Freunde?adddelete=0&freundname=<%=test.getID()%>&wunsch=zufuegen">Nicht Akzeptieren</a></li>
							 
						</ul>
		</div>
							<%
							
							out.println(test.getID() +" will dich als Freund. <br>");
						}
					}
					else out.println("Du hast zurzeit keine Freundschaftsanfragen.");
					
				 	%>
				 	
					<h2>Meine Freunde:</h2>
					<%
				
					
					
							if(freund) out.println("Du hast noch keine Freunde");
							else{
								for(Person test: geffreunde){
									%>
						<div class="btn-group">
						<button type="button" data-toggle="dropdown"
							class="btn btn-default dropdown-toggle">
							Entfernen <span class="caret"></span>
						</button>
						<ul class="dropdown-menu" method="get" action="Freunde">
							<li><a
								href="/50plus/Freunde?adddelete=0&freundname=<%=test.getID()%>&wunsch=loeschen">Entfernen</a></li>
						</ul>
						</div>
					<% 
										out.println("&nbsp; Username: "+test.getID() + " Vorname: "+test.getVorname()+" Nachname: "+test.getNachname());	
					%>
										<a href="/50plus/Pinnwand?pinnwandOwner=<%= test.getID()%>">&nbsp&nbsp&nbsp&nbspZur Pinnwand</a> <br>
										<a href="/50plus/Nachrichten?empf=<%= test.getID()%>">&nbsp&nbsp&nbsp&nbspFreund anschreiben</a> <br>
										
										
										
					
					<%					
									}
								}
								if (session.getAttribute("username")==null){
									response.sendRedirect("index.jsp");
							  	}
					%>

				</form> 
			</div>
		</div>
	</div>
	<!-- /.container -->

	<!-- jQuery -->
		<script src="dist/js/jquery-1.11.1.js"></script>
		
		<!-- Bootstrap Core JavaScript -->
		<script src="dist/js/bootstrap.min.js"></script>

</body>
</html>
