<%-- 
    Document   : Forschung
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

	<!-- jQuery -->
	<script src="dist/js/jquery-1.11.1.js"></script>
<style type="text/css">
#placeholder { width: 600px; height: 250px; }
</style>
 
<!--[if lte IE 8]><script type="text/javascript" language="javascript" src="flot/excanvas.min.js"></script><![endif]-->
<script type="text/javascript" language="javascript" src="flot/jquery.flot.js"></script>
 

</head>

<body>
		<%@ page import="java.util.ArrayList" import="Personen.Post"%>
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
					<li><a href="/50plus/Pinnwand.jsp">Pinnwand</a></li>
						<li><a href="/50plus/Gruppen.jsp">Gruppen</a></li>
						<li><a href="/50plus/Freunde.jsp">Freunde</a></li>
						<li><a href="/50plus/Forschung.jsp">Forschung</a></li>
						<li><a href="/50plus/Profil.jsp">Profil</a></li>
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
					//	out.println((String) session.getAttribute("username")
					//+ " surft hier gerade");
					if (session.getAttribute("username") == null) {

						response.sendRedirect("index.jsp");
					}
					//<jsp:include page="/Forschung" flush="true" />
				%>

				<div class="input-group">
					<form class="form-search" method="get" action="Forschung"
						role="form">
						<input type="text" name="searchperson" class="form-control"
							placeholder="Person suchen..">

					</form>

				</div>
				<a class="btn btn-default"
					href="/50plus/Forschung?information=information" role="button">Allgemeine
					Informationen</a>
				<br />

				<%
					if (session.getAttribute("anfrage").equals("keine")) {
					}

					else if (session.getAttribute("anfrage").equals("keinzugriff")) {
				%>
				Sie sind kein Forscher - Kein Zugriff.

				<%
					} else if (session.getAttribute("anfrage").equals("info")) {
						ArrayList<Integer> allgemeinwerte = (ArrayList<Integer>) session.getAttribute("allgemeinwerte");
						out.println("<br />");
						out.println("Anzahl aller registrierten Personen: "
								+ allgemeinwerte.get(0));
						out.println("<br />");
						out.println("Anzahler aller Posts: " + allgemeinwerte.get(1));
						out.println("<br />");
						out.println("Anzahler aller Gruppen: " + allgemeinwerte.get(10));
						out.println("<br />");
						out.println("Juengste Person: " + allgemeinwerte.get(2));
						out.println("<br />");
						out.println("Aelteste Person: " + allgemeinwerte.get(3));
						out.println("<br />");
						out.println("Anzahl der Admins: " + allgemeinwerte.get(4));
						out.println("<br />");
						out.println("Anzahl der User: " + allgemeinwerte.get(5));
						out.println("<br />");
						out.println("Anzahler der Forscher: " + allgemeinwerte.get(6));
						out.println("<br />");
						out.println("Durchschnittsalter aller Personen: "
								+ allgemeinwerte.get(7));
						out.println("<br />");
						out.println("Durchschnittliche Freundesanzahl: "
								+ allgemeinwerte.get(8));
						out.println("<br />");
						out.println("Durchschnitt von Posts pro Person: "
								+ allgemeinwerte.get(9));

					} else if (session.getAttribute("anfrage").equals("person")) {

						if (session.getAttribute("personfound").equals("true")) {
							ArrayList<Integer> searchedpersonwerte = (ArrayList<Integer>) session.getAttribute("searchedpersonwerte");

							out.println("<br />");
							out.println("Username:" + session.getAttribute("searchedusername"));
							out.println("<br />");
							out.println("Postanzahl: " + searchedpersonwerte.get(0));
							out.println("<br />");
							out.println("Freundeanzahl: " + searchedpersonwerte.get(1));
							out.println("<br />");
							out.println("Gruppenanzahl: " + searchedpersonwerte.get(2));
							
						 
								
								String username = (String) session.getAttribute("username");
								ArrayList<Post> postlist = 	(ArrayList<Post>) session.getAttribute("postlist");
							   
								String postpermonth[] = new String[12];
 
							
				%>
				<script type="text/javascript">
				
				var werte = [[1, 5], [2, 5], [3, 20], [4, 0], [5, 10], [6, 100], [7, 30], [8, 10], [9, 5], [10, 3], [11, 2], [12, 1]];
 
				$(document).ready(function () {
				    $.plot($("#placeholder"), [werte]);
				});
				</script>
			    <br />
			    <br />
			    <div id="placeholder"></div>
			    x-Achse: Monat
			    <br />
			    y-Achse: Post-Anzahl
				<%
					} else {
							out.println("<br />");
							out.println("Person nicht gefunden!");
						}

					} else {
					}

					session.setAttribute("postDeleteSuccess", "nichts");
				%>
			</div>
		</div>
	</div>
	<!-- /.container -->
	<!-- Bootstrap Core JavaScript -->
	<script src="dist/js/bootstrap.min.js"></script>
</body>
</html>