<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WeGo - 쪽지</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/WeGo/css/bootstrap.css">
<style type="text/css">
#message-wrapper {
	margin: 30px 40px;
	width: 400px;
	heigth: 300px;
}
</style>
<script src="/WeGo/js/jquery-3.3.1.min.js" type="text/javascript"></script>
<script src="/WeGo/js/bootstrap.js" type="text/javascript"></script>
</head>
<body>
<ul class="nav nav-wego nav-tabs-wego">
    <li id="receive-li"><a href="/WeGo/message/receivelist">받은 메세지</a></li>
    <li id="send-li"><a href="/WeGo/message/sendlist">보낸 메세지</a></li>
    <li><a href="/WeGo/message/write">메세지 쓰기</a></li>
    <li class="active"><a href="#">메세지상세</a></li>
</ul>
<div id="message-wrapper">
	<div>
		발신 : ${messageVO.senderVO.name }(${messageVO.senderEmail })
	</div>
	<div>
		수신 : ${messageVO.receiverVO.name }(${messageVO.receiverEmail })
	</div>
	<div>
		보낸날짜 : ${messageVO.sendDate }
	</div>
	<div>
	<c:choose>
		<c:when test="${not empty messageVO.readDate }">
			수신날짜 : ${messageVO.readDate }
		</c:when>
		<c:otherwise>
			수신 미확인
		</c:otherwise>
	</c:choose>
		
	</div>
	<div>
		제목 : ${messageVO.title }
	</div>
	<div>
		내용 : ${messageVO.detail }
	</div>
	<div>
		<a href="/WeGo/message/delete/${messageVO.id }">삭제</a>
	</div>	
</div>
</body>
</html>