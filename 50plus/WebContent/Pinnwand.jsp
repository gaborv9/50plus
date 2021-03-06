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
					 


  					<br>
 
					<br>
					
					
				    <%
					String username = (String) session.getAttribute("username");
					String pinnwandOwner = (String) session.getAttribute("pinnwandOwner");
					ArrayList<Post> postlist = 	(ArrayList<Post>) session.getAttribute("postlist");
				     %>	
					
					
					<div class="well">
					<form class="form-horizontal" action="Pinnwand" method="post"
						role="form">
						<h4>Neue Nachricht</h4>
						<div class="form-group">
							<textarea class="form-control" rows="4" name=inhalt
								placeholder="Neue Nachricht eingeben.."></textarea>
							<button class="btn btn-primary" input type="submit" name="posten" value="posten" type="button">Post</button>
							<input type="hidden" name="pinnwandOwner" value = ${pinnwandOwner}>
						</div>
					</form>
				</div>
					
					<!--  
					<form action="Pinnwand" method="post"> 
					
					
					<textarea name="inhalt" rows="7" cols="90"></textarea> 
					<br>
						
					<button type="register" class="btn btn-default">posten</button>
				
					
					
					
				 
					
					</form> 
					<!--<input type="submit" name="posten" value="posten"> -->
						               		
		            
		           
					
	
					 
					<h4>Pinnwand von <%=pinnwandOwner%></h4>
					<br>
					
					<% 
					if(postlist.size() != 0)
					{
						int i = postlist.size()-1;
						for ( ; i >=0; i--)
							 
										//for (Post test: postlist )
					    {
							//out.println(test.getOwnPostcounter() + ":    "); 
							out.println(postlist.get(i).getUsername() + ", ");
							//out.println(postlist.get(i).getPinnwandOwner() + ", ");
							out.println(postlist.get(i).getZeitpunkt()); 
							//out.println(postlist.get(i).getOwnPostcounter()); 
							
							
							if(!username.equals(postlist.get(i).getPinnwandOwner()))
							{
						       	if (postlist.get(i).getFlagged())
								{
								%> 
									<span style="float:right">gemeldet </span>
								<% 
								}
						       	else
						       	{
						       		%>
						       		<span style="float:right">
						       		<a href="/50plus/Pinnwand?postNumber_melden=<%= (postlist.get(i).getOwnPostcounter())%>&pinnwandOwner=<%=pinnwandOwner%>">Melden</a>
						       		</span>
						       		<% 
						       	}
							}


							
							
							
							
					%>
					<div class="panel panel-default">
						<div class="panel-body" style="width: 1100px; word-wrap: break-word"><% out.println(postlist.get(i).getInhalt()); %></div>
					</div>

				   <% 
				   
				   Integer role =  (session.getAttribute("role") == null) ? 0 : (Integer)(session.getAttribute("role"));
				   
				   if(username.equals(postlist.get(i).getPinnwandOwner()) || role == 1)
				   {
				    %>   
				    <a href="/50plus/Pinnwand?postNumber_delete=<%= (postlist.get(i).getOwnPostcounter())%>&pinnwandOwner=<%=pinnwandOwner%>">Delete</a>
				    <%
				   }
				   
				   %>
	               
	      	             	
	             	
	               <br> 	
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
