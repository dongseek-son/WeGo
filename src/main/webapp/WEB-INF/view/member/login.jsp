<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/view/common/layout/layout_header.jsp" />

<style>
#title {
	margin-bottom : 30px;
}
</style>
	<div class="container">	
		<div id="title">
			<h1>로그인</h1>
		</div>
		<form id="loginForm" method="post" action="/WeGo/memberlogin" >
			<div id="email-group" class="form-group">
				<div class="input-group">
			  	  <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
			  	  <input type="email" id="email" name="email" placeholder="이메일 입력" class="form-control"/>
			 	</div>
			</div>
			<div id="password-group" class="form-group">
				<div class="input-group">
			  	  <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
			  	  <input type="password" id="password" name="password" placeholder="비밀번호 입력" class="form-control"/>
			 	</div>
			</div>
			<div class="form-group">
				<input type="submit" class="btn btn-sucess btn-block" value="로그인" />
			</div>
		</form>
	</div>
<jsp:include page="/WEB-INF/view/common/layout/layout_footer.jsp" />