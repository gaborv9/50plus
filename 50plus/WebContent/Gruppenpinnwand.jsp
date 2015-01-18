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
		<%@ page import="java.util.ArrayList, Data.GruppenPost_Serialisierung, Personen.GruppePost, Management.Gruppenpinnwand" %> 
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
		
			<br><br>
			
			
			
			<%
					String username = (String) session.getAttribute("username");
					String gn = (String) session.getAttribute("gn");
					ArrayList<GruppePost> gplist = (ArrayList<GruppePost>) session.getAttribute("gplist");
			%>
					
			<h2><% out.println("Gruppe: "+gn);%></h2>
			
			<div class="btn-group">
			<button type="button" data-toggle="dropdown"
					class="btn btn-default dropdown-toggle">
					Mitglieder<span class="caret"></span>
			</button>
				<ul class="dropdown-menu" method="post" action="Gruppenmitglieder">
					<li><a
						href="/50plus/Gruppenmitglieder?wunsch=anzeigen">Mitglieder anzeigen</a></li>		 
				</ul>
			</div>
			<br>
			
			<br>
					<div class="well">
					<form class="form-horizontal" action="Gruppenpinnwand" method="post"
						role="form">
						<h4>Neue Nachricht</h4>
						<div class="form-group">
							<textarea class="form-control" rows="4" name=inhalt
								placeholder="Neue Nachricht eingeben..."></textarea>
							<button class="btn btn-primary" input type="submit" name="posten"
								value="posten" type="button">Post</button>
						</div>
					</form>
				</div>
			
						
			<% 
					if(gplist.size() != 0)
					{
						int i = gplist.size()-1;
						for ( ; i >=0; i--)
							 
					    {
							out.println(gplist.get(i).getPostGruppenUsername() + ", ");
							out.println(gplist.get(i).getPostGruppenZeit() + "<br />"); 
							%>
							<div class="panel panel-default">
								<div class="panel-body" style="width: 1100px; word-wrap: break-word"><% out.println(gplist.get(i).getPostGruppenInhalt()); %></div>
							</div>
			<%
					    }
					}
			%>
			
			

			
			
			
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
