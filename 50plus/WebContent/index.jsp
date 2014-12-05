
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="dist/docs/favicon.ico">
        <title>Login</title>

        <!-- Bootstrap core CSS -->
        <link href="dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="dist/css/signin.css" rel="stylesheet">

        <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
        <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
        <script src="dist/docs/assets/js/ie-emulation-modes-warning.js"></script>

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>

    <body>

        <!--   <div class="container"> -->
        <form class="form-signin" method="post" action="Login" role="form">
            <h2 class="form-signin-heading">Bitte einloggen:</h2>


            <input type="text" class="form-control" name="username" placeholder="Nutzername" required autofocus>
            <input type="password" class="form-control" name="password" placeholder="Passwort" required>
            <!-- <div class="checkbox"> -->
            <label>
                <input type="checkbox" value="remember"> Eingabe speichern
            </label>
            <!-- </div> -->

            <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
        </form>

        <form class="form-signin" method="post" action="Register" role="form">
            <h2 class="form-signin-heading">Registrieren:</h2>

            <div class="form-group">
                <label for="Nutzername">Nutzername</label>
                <input type="nutzername" class="form-control" name="username" placeholder="Nutzername" required>
            </div>

            <div class="form-group">
                <label for="Passwort">Password</label>
                <input type="passwort" class="form-control" name="passwort" placeholder="Passwort"required>
            </div>

            <div class="form-group">
                <label for="Vorname">Vorname</label>
                <input type="vorname" class="form-control" name="vorname" placeholder="Vorname" required>
            </div>

            <div class="form-group">
                <label for="Nachname">Nachname</label>
                <input type="nachname" class="form-control" name="nachname" placeholder="Nachname"required>
            </div>

            <div class="form-group">
                <label for="Geburtsdatum">Geburtsdatum</label>
                 <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                 <ul class="nav navbar-nav">
                    <li>
                       <input type="geburtsdatum" class="form-control" name="datum1" placeholder="DD"required>
                    </li>
                    <li>
                        <input type="geburtsdatum" class="form-control" name="datum2" placeholder="MM"required>
                    </li>
                    <li>
                       <input type="geburtsdatum" class="form-control" name="datum3" placeholder="YYYY"required>
                    </li>
 				</ul>
            </div>

            <div class="checkbox">
                <label>
         <input type="checkbox" required> Sie erklären sich hiermit einverstanden, dass alle ihre Daten zu Forschungszwecken weitergeleitet werden, have fun. 
                </label>
            </div>
            <button type="register" class="btn btn-default">Registrieren</button>
        </form>

        <!--  </div> <!-- /container --> 

        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <script src="dist/docs/assets/js/ie10-viewport-bug-workaround.js"></script>
    </body>
</html>
