<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/WeGo/css/bootstrap.css">
<style>
#message-wrapper {
	margin: 30px 40px;
	width: 400px;
	heigth: 300px;
}
</style>
<script src="/WeGo/js/jquery-3.3.1.min.js" type="text/javascript"></script>
<script src="/WeGo/js/bootstrap.js" type="text/javascript"></script>
<script type="text/javascript">
$().ready(function() {
	
	var isRegistedEmail = false;
	
	$("#receiverEmail").blur(function() {
		$.post("/WeGo/member/check/email/registed"
				, {
					"email": $(this).val()			
				}
				, function(response) {
					if ( response.registed ) {
						$("#email-group").attr("class","form-group has-success has-feedback");
						$("#email-icon").attr("class", "glyphicon glyphicon-success form-control-feedback");
						$("#email-check").hide();
						isRegistedEmail = true;
					}
					else {
						$("#email-group").attr("class","form-group has-warning has-feedback");
						$("#email-icon").attr("class", "glyphicon glyphicon-remove form-control-feedback");
						$("#email-check").show();
						isEmailDuplicate = false;
					}
				});
	})
	
	$("#sendBtn").click(function() {
		if ( !isRegistedEmail ) {
			alert("등록되지 않은 이메일 입니다.");
			$("#receiverEmail").focus();
		}
		else {
			$("#messageForm").submit();
		}
	});
});
</script>
</head>
<body>
<ul class="nav nav-wego nav-tabs-wego">
    <li><a href="/WeGo/message/receivelist">받은 메세지</a></li>
    <li><a href="/WeGo/message/sendlist">보낸 메세지</a></li>
    <li class="active"><a href="#">메세지 쓰기</a></li>
</ul>

<div id="message-wrapper">	
	<form action="/WeGo/message/write" method="post" id="messageForm">
		<input type="hidden" name="token" value="${sessionScope._CSRF_ }">
		<input type="hidden" id="senderEmail" name="senderEmail" value="${sessionScope._USER_.email }" >	
		<div id="email-group" class="form-group">
			<div class="input-group">
			  	  <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
			  	  <input type="text" id="receiverEmail" name="receiverEmail" value="${receiverEmail }" placeholder="수신자 ID 입력" class="form-control"/>
			  	  <span id="email-icon"></span>
			 </div>
			 <span id="email-check" class="help-block text-warning" style="display:none">등록되지 않은 이메일 입니다.</span>
		</div>
		<div id="title-group" class="form-group">
			<div class="input-group">
			  	  <span class="input-group-addon"><i class="glyphicon glyphicon-file"></i></span>
			  	  <input type="text" id="title" name="title" placeholder="제목 입력" class="form-control"/>
			  	  <span id="title-icon"></span>
			 </div>
		</div>
		<div class="form-group">
			<textarea class="form-control" rows="6" id="detail" name="detail" placeholder="내용 입력"></textarea>
		</div>
		<div class="form-group">
			<input type="button" id="sendBtn" class="btn btn-warning btn-block disabled" value="보내기" />
		</div>
	</form>
</div>
</body>
</html>