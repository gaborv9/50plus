<%-- 
    Document   : Profil
    Created on : 6.1.2015, 19:22:10
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
#placeholder {
	width: 600px;
	height: 250px;
}
</style>

<!--[if lte IE 8]><script type="text/javascript" language="javascript" src="flot/excanvas.min.js"></script><![endif]-->
<script type="text/javascript" language="javascript"
	src="flot/jquery.flot.js"></script>


</head>

<body>
	<%@ page import="java.util.ArrayList" import="Personen.Person"
		import="Management.PersonManagement" import="Data.Serialisierung"
		import="java.util.GregorianCalendar"%>
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

			<!-- /.navbar-collapse -->
			<span style="color: grey; padding-left: 40px"> <%=(String) session.getAttribute("username")%>
			</span>
		</div>

		<!-- /.container -->
	</nav>


	<!-- Page Content -->


	<div class="container">
		<div class="row">
					<div class="col-md-4">
						<br />
						<%
							//	out.println((String) session.getAttribute("username")
							//+ " surft hier gerade");
							if (session.getAttribute("username") == null) {

								response.sendRedirect("index.jsp");
							}
						%>

						<h2>
							<small>Profil von</small>
							<%
								out.print(session.getAttribute("username"));
							%>
						</h2>
						<br />
						<%
							Serialisierung ser = new Serialisierung();
							String username = (String) session.getAttribute("username");
							Person a = ser.getPersonbyid((String) session
									.getAttribute("username"));
						%>
						<h4>
							<small>Vorname:</small>
							<%
								out.print(a.getVorname());
							%>
						</h4>
						<h4>
							<small>Nachname:</small>
							<%
								out.print(a.getNachname());
							%>
						</h4>
						<h4>
							<small>Alter:</small>
							<%
								out.print(a.getAlter());
							%>
						</h4>
						<h4>
							<small>Geburtsdatum:</small>
							<%
								out.print(a.getDatumString());
							%>
						</h4>
					</div>
					<div class="col-md-4">
						<br /> <br />
						<h4>Daten aendern:</h4>
						<form class="form form-horizontal" role="form" method="post" action="Management">

							<div class="form-group">
								<div class="col-md-7">
									<label for="Vorname">Vorname</label> <input type="text"
										class="form-control" name="vorname" placeholder="Vorname">
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-7">
									<label for="Nachname">Nachname</label> <input type="text"
										class="form-control" name="nachname" placeholder="Nachname">
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-7">
									<label for="password">Password</label> <input type="password"
										class="form-control" name="password" placeholder="Password">
								</div>
							</div>

							<div class="form-group">
								<div class="col-md-7">
									<label for="Geburtsdatum">Geburtsdatum</label>
									<div class="collapse navbar-collapse"
										id="bs-example-navbar-collapse-1">
										<ul class="nav navbar-nav">
											<li><input type="geburtsdatum" class="form-control"
												name="datum1" placeholder="DD"></li>
											<li><input type="geburtsdatum" class="form-control"
												name="datum2" placeholder="MM"></li>
											<li><input type="geburtsdatum" class="form-control"
												name="datum3" placeholder="YYYY"></li>
										</ul>
									</div>
								</div>
							</div>
 
							<button type="submit" class="btn btn-default">Submit</button>

						</form>

					</div>
					<div class="col-md-4">
					<br />
					<br />
					<form class="form form-horizontal" role="form" method="get" action="Management?value=1">
						<div class="form-group">
								<div class="col-md-10">
									<h4>Link zum Bild (max 150x150)</h4>
									<input type="text" class="form-control" name="picturelink" placeholder="Link">
									<button type="submit" class="btn btn-default">Submit</button>
								</div>
						</div>
					</form>
					
					
					</div>

				</div>
			</div>
 
		<!-- /.container -->
		<!-- Bootstrap Core JavaScript -->
		<script src="dist/js/bootstrap.min.js"></script>
</body>
</html>