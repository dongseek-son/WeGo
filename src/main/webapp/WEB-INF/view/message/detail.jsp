<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/WeGo/css/bootstrap.css">
<script src="/WeGo/js/jquery-3.3.1.min.js" type="text/javascript"></script>
<script src="/WeGo/js/bootstrap.js" type="text/javascript"></script>
</head>
<body>
	<div>
		발신 : ${messageVO.senderEmail }
	</div>
	<div>
		수신 : ${messageVO.receiverEmail }
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
</body>
</html>