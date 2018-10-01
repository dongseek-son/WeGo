<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style type="text/css">
  body {
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
  }
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
<nav>
	<div id="nav_left">
		<h1>WeGo</h1>
	</div>
	<div id="nav_right">
		<div>
			<form action="/WeGo/memberlogin" method="post">
			  	<input type="email" name="email" placeholder="Email">
			    <input type="password" name="password" placeholder="Password">
		    	<input type="submit" value="로그인">
  			</form>
		</div>
		<div>
			<a href="/WeGo/member/regist">WeGo는 처음이신가요?</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="#">이메일/패스워드를 잊으셨나요?</a>
		</div>
	</div>
</nav>
