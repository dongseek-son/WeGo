<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style type="text/css">
/*   body {
    padding: 0px;
    margin: 0px;
    font-size: 9pt;
  }
  nav {
    background-color: #ddd;
    height: 46px;
  }

  nav ul {
    padding: 0px;
    margin: 0px;
  }

  nav ul > li {
    display: inline-block;
    margin-left: 30px;
  }

  nav ul > li:first-child {
    margin-left: 0px;
  }

  a, a:visited {
    text-decoration: none;
    color: #333;
  }

  a:active {
    text-decoration: underline;
  }

  a:hover {
    color: #999;
  }
  
  #nav_left {
  	float: left;
  	margin-left: 100px;
  }
  
  #nav_right {
  	float: right;
  	margin-right: 100px;
  } */
</style>
<script type="text/javascript">
	var message = "${param.message}";
	if ( message != "" ) {
		alert(message);
	}
	var message = "${message}";
	if ( message != "" ) {
		alert(message);
	}
</script>
<nav class="navbar navbar-wego">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="/WeGo/">WeGo</a>
    </div>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="#" data-toggle="modal" data-target="#registModal"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
      <li><a href="#" data-toggle="modal" data-target="#loginModal"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
    </ul>
  </div>
</nav>
