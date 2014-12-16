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
				<span style="color:grey; padding-left:40px"> <%=(String) session.getAttribute("username") %> </span>	
				<!-- /.navbar-collapse -->
				
				
			</div>
		
			<!-- /.container -->
		</nav>
		
		
		
		<!-- Page Content -->
		<div class="container">
			<div class="row">
				<div class="col-lg-12" id="content">
					 
				<!--	bei der Ausgabe statt Scriplets sollten wir eher Taglibs and EL nutzen:
					http://stackoverflow.com/questions/3177733/how-to-avoid-java-code-in-jsp-files
  -->
  <br>
 
					<br>
					<div class="well">
					<form class="form-horizontal" action="Pinnwand" method="post"
						role="form">
						<h4>Neue Nachricht</h4>
						<div class="form-group">
							<textarea class="form-control" rows="4" name=inhalt
								placeholder="Neue Nachricht eingeben.."></textarea>
							<button class="btn btn-primary" input type="submit" name="posten"
								value="posten" type="button">Post</button>
						</div>
					</form>
				</div>
					
					<!--  
					<form action="Pinnwand" method="post"> 
					
					
					<textarea name="inhalt" rows="7" cols="90"></textarea> 
					<br>
						
					<button type="register" class="btn btn-default">posten</button>
				
					
					
					<!--<input type="submit" name="posten" value="posten"> -->
				 
					
					</form> 
						               		
					<%
						String postDeleteSuccess = (String)session.getAttribute("postDeleteSuccess");
					
					%>		            
		           
					
					<%
					String username = (String) session.getAttribute("username");
					ArrayList<Post> postlist = 	(ArrayList<Post>) session.getAttribute("postlist");
				     %>
					 
					<h4>Pinnwand von <%=username%></h4>
					<br>
					
					<% 
					if(postlist.size() != 0)
					{
						//for (int i=0; i < postlist.size(); i++)
							int i=postlist.size()-1;
										for (; i >0; --i)
					    {
							//out.println(postlist.get(i).getOwnPostcounter() + ":    "); 
							out.println(username + ", ");
							out.println(postlist.get(i).getZeitpunkt() + "<br />"); 
							%>
							<div class="panel panel-default">
								<div class="panel-body" style="width: 1100px; word-wrap: break-word"><% out.println(postlist.get(i).getInhalt()); %></div>
							</div>

							<%
		               //		out.println(postlist.get(i).getInhalt() + "<br />"); 
		             %>
		               	
		               <a href="/50plus/Pinnwand_Delete?postNumber=<%= (postlist.get(i).getOwnPostcounter())%>">Delete</a><br> 	
		               <br>
		               		
		               		
		            <% 
		            	}
					}

					
		             %>
					
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
