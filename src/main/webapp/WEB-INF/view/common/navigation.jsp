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
    padding: 15px;
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
  } */
</style>
<nav class="navbar navbar-wego">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">WeGo</a>
		</div>
		<ul class="nav navbar-nav">
			<li><a href="/WeGo/mygoal/write">My Goal</a></li>
			<li><a href="#">Our Goal</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li class="dropdown">
				<a class="dropdown-toggle" data-toggle="dropdown" href="#">
					<img src="/WeGo/member/profiledownload/${sessionScope._USER_.profileFilename }" class="img-circle" width="20px" height="20px"> 
					${sessionScope._USER_.name }
					<span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li><a href="/WeGo/message/write">Message</a></li>
					<li><a href="#">MyPage</a></li>
					<li><a href="#">회원정보수정</a></li>
					<li><a href="/WeGo/memberlogout">Logout</a></li>
				</ul></li>
		</ul>
		<form class="navbar-form navbar-right" action="#">
			<div class="input-group">
				<input type="text" class="form-control" placeholder="Search">
				<div class="input-group-btn">
					<button class="btn btn-default" type="submit">
						<i class="glyphicon glyphicon-search"></i>
					</button>
				</div>
			</div>
		</form>
	</div>
</nav>
