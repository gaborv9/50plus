<%-- 
    Document   : Pinnwand
    Created on : 22.11.2014, 15:37:13
    Author     : master
    test
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
		<%@ page import="java.util.ArrayList, Personen.Post" %> 
		
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
					<h1>Hier ist deine tolle Pinnwand</h1>
					
					<br>
					
					bei der Ausgabe statt Scriplets sollten wir eher Taglibs and EL nutzen:
					http://stackoverflow.com/questions/3177733/how-to-avoid-java-code-in-jsp-files

					<br>
					
					
					<form action="Pinnwand" method="post"> 
					
					
					<textarea name="inhalt" rows="7" cols="90"></textarea> 
					<br>
						
					
					<input type="submit" name="posten" value="posten"> 
					<br>
					
					<%
					String username = (String) session.getAttribute("username");
					ArrayList<Post> postlist = 	(ArrayList<Post>) session.getAttribute("postlist");
				     %>
					 
					eingeloggt als <%= username %> 
					<br>
					
					<% 
					if(postlist.size() != 0)
					{
						for (int i=0; i < postlist.size(); i++)
					    {
							out.println(postlist.get(i).getPostcounter() + ":    "); 
							out.println(postlist.get(i).getZeitpunkt() + ":    "); 
		               		out.println(postlist.get(i).getInhalt() + "<br>"); 
					    }
					}

					
		             %>
				
					
					
					</form> 
							
					
					
					
					<%
				        	if (session.getAttribute("username")==null){
						 
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
