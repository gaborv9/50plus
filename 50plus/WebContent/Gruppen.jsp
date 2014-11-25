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
    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">
                    <img src="http://placehold.it/150x150&text=BILD" alt="">
                </a>

            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

                <ul class="nav navbar-nav">
                        <li>
                        <a href="/50plus/Pinnwand.jsp">Pinnwand</a>
                    </li>
                    <li>
                        <a href="/50plus/Gruppen.jsp">Gruppen</a>
                    </li>
                    <li>
                        <a href="/50plus/Freunde.jsp">Freunde</a>
                    </li>
                    <li>
                        <a href="/50plus/Forschung.jsp">Forschung</a>
                    </li>

                    <form class="form-signin" method="post" action="Suche" role="form">
                        <div class="form-group">
                            <input type="suche" class="form-control" name="suche" placeholder="Freunde/Gruppen finden..">
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
                
              <jsp:include page="/Gruppen" flush="true"/>
            </div>
        </div>
    </div>
    <!-- /.container -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>
</html>