<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script>
$().ready(function() {
	$("#message-a").click(function() {
		window.open("/WeGo/message/write", "Message", "titlebar=no,menubar=no,status=no,toolbar=no,top=150,left=700,width=500,height=500");
	});
});
</script>
<nav class="navbar navbar-wego">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">WeGo</a>
		</div>
		<ul class="nav navbar-nav">
			<li><a href="/WeGo/mygoal/detail">My Goal</a></li>
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
					<li><a id="message-a" href="#">Message</a></li>
					<li><a href="#">MyPage</a></li>
					<li><a href="#">회원정보수정</a></li>
					<li><a href="/WeGo/memberlogout">Logout</a></li>
				</ul>
			</li>
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
