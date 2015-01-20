<%-- 
    Document   : Gruppen
    Created on : 23.11.2014, 14:42:12
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
		<%@ page import="java.util.ArrayList, Personen.Nachricht, Data.Nachrichten_Serialisierung, java.util.Collections;" %> 

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
		
				<%
					String username = (String) session.getAttribute("username");
					ArrayList<Nachricht> senderliste= (ArrayList<Nachricht>) session.getAttribute("senderliste");//Postausgang
					ArrayList<Nachricht> empfaengerliste= (ArrayList<Nachricht>) session.getAttribute("empfaengerliste");//Posteingang
					String personname = (String) session.getAttribute("personname");
				%>		
		
			<br><br>
			<h2>Neue Nachricht schreiben:</h2>
			<form method="post" action="Nachrichten">
			<div class="form-group">
			<input type="text" class="form-control" name="empf" placeholder="Bitte Nutzername des Empfängers eingeben" required>
			<textarea class="form-control" rows="4" name=inhalt placeholder="Nachricht schreiben..."></textarea>
            	<button type="register" class="btn btn-default">Nachricht senden!</button>
            	</div>
			</form>

	



			
			
		<h3>Nachrichteneingang</h3>
		<%
		Collections.reverse(empfaengerliste);
		for(Nachricht temp: empfaengerliste){%>
			<div class="btn-group">
			<button type="button" data-toggle="dropdown"
					class="btn btn-default dropdown-toggle">
					<%out.println(temp.getNachrichtSender()+", "+temp.getNachrichtZeit()+"<br>"); %><span class="caret"></span>
			</button>
				<ul class="dropdown-menu">
					<li><%out.println(temp.getNachrichtInhalt()); %></li>		 
				</ul>
			
		</div>
		<%} %>





<br><br><br>






		<h3>Nachrichtenausgang</h3>
		<%Collections.reverse(senderliste);
		for(Nachricht temp: senderliste){%>
			<div class="btn-group">
			<button type="button" data-toggle="dropdown"
					class="btn btn-default dropdown-toggle">
					<%out.println(temp.getNachrichtEmpfaenger()+", "+temp.getNachrichtZeit()+"<br>"); %><span class="caret"></span>
			</button>
				<ul class="dropdown-menu">
					<li><%out.println(temp.getNachrichtInhalt()); %></li>		 
				</ul>
			
		</div>
		<%} %>
		
		
		
		
		
		

			<%
				if (session.getAttribute("username") == null) {

					response.sendRedirect("index.jsp");
				}
			%>
			
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
