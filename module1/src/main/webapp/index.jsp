<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Module1 | Login</title>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
  </head>
  <body>
    <div class="container" style="max-width:400px; margin-top:15%">
      <form name="login" action="login" method="POST" class="form-signin">
        <h2 class="form-signin-heading">Login</h2>
        <input type="email" class="form-control" name="email" placeholder="E-mail address" required autofocus style="height:50px;">
        <input type="password" class="form-control" id="inputPassword" name="password" placeholder="Password" style="height:50px;margin-bottom:5px;">
        ${errorMsg} <br>
        <input type="submit" class="btn btn-primary btn-lg btn-block" value="Login">
        <a href="register" class="btn centered">Click here if you are not registered</a>
      </form>
    </div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
  </body>
</html>