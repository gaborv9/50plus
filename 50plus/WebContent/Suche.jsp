<%-- 
    Document   : Suche
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

<link href="dist/css/logo-nav.css" rel="stylesheet">

</head>

<body>
	<%@ page import="java.util.ArrayList" import="Personen.Person"
		import="Management.GruppeClass"%>

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
					src="http://placehold.it/150x150&text=BILD" alt="">
				</a>

			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->

			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">

				<ul class="nav navbar-nav">
					<li><a href="/50plus/Pinnwand.jsp">Pinnwand</a></li>
					<li><a href="/50plus/Gruppen.jsp">Gruppen</a></li>
					<li><a href="/50plus/Freunde.jsp">Freunde</a></li>
					<li><a href="/50plus/Forschung.jsp">Forschung</a></li>
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
		</div>

		<!-- /.container -->
	</nav>

	<!-- Page Content -->
	<div class="container">
		<div class="row">
			<div class="col-lg-12" id="content">
				<form action="Suche" method="post">


					<%
						ArrayList<Person> gefperliste = (ArrayList<Person>) session.getAttribute("gefperliste");
																ArrayList<GruppeClass> gefgrpliste = (ArrayList<GruppeClass>) session.getAttribute("gefgrpliste");
																String username = (String) session.getAttribute("username");
					%>
					<h2>Freunde:</h2>
					<br>
					<%
						for(Person test: gefperliste){
					%>
					<!-- 			
					<div class="btn-group" role="toolbar" aria-label="">
					
						<button type="button" class="btn btn-default" onclick="<ul method='get' action='Management' href='/50plus/Management?role=1&user='> </ul>">Administrator</button>
					    <button type="button" class="btn btn-default" onclick="<ul method='get' action='Management' href='/50plus/Management?role=2&user='> </ul>">Forscher</button>
					 	<button type="button" class="btn btn-default" onclick="<ul method='get' action='Management' href='/50plus/Management?role=3&user='> </ul>">User</button>
					 	
					</div>
					-->
					<div class="btn-group">
						<button type="button" data-toggle="dropdown"
							class="btn btn-default dropdown-toggle">
							Rollenvergabe <span class="caret"></span>
						</button>
						<ul class="dropdown-menu" method="get" action="Management">
							<li><a
								href="/50plus/Management?role=1&username=<%=test.getID()%>">Administrator</a></li>
							<li><a
								href="/50plus/Management?role=2&username=<%=test.getID()%>">Forscher</a></li>
							<li><a
								href="/50plus/Management?role=3&username=<%=test.getID()%>">User</a></li>
						</ul>


					</div>
					<div class="btn-group">
						<button type="button" data-toggle="dropdown"
							class="btn btn-default dropdown-toggle">
							Add/Remove <span class="caret"></span>
						</button>
						<ul class="dropdown-menu" method="get" action="Freunde">
							<li><a
								href="/50plus/Freunde?freundname=<%=test.getID()%>">ADD</a></li>
							<li><a
								href="/50plus/Freunde?freundname=<%=test.getID()%>">REMOVE</a></li>
							 
						</ul>


					</div>

					<%
						out.println("&nbsp; Username: "+test.getID() + " Vorname: "+test.getVorname()+" Nachname: "+test.getNachname()+" Rolle: "+test.getRole()+"<br>");
															
																}
					%>


					<br> <br>
					<h2>Gruppen:</h2>
					<br>
					<%
						for(GruppeClass test: gefgrpliste){
																	out.println("&nbsp; Gruppenname: "+test.getName() +"<br>");
																}
					%>


					<%
					session.setAttribute("postDeleteSuccess", "nichts");
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