<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/WeGo/css/bootstrap.css">
<script src="/WeGo/js/jquery-3.3.1.min.js" type="text/javascript"></script>
<script src="/WeGo/js/bootstrap.js" type="text/javascript"></script>
<script>
$().ready(function() {
	if ( ${condition eq "receive"} )
		{
			$("#receive-li").attr("class", "active");		
		}
	else if ( ${condition eq "send"} )
		{
			$("#send-li").attr("class", "active");		
		}
});
</script>
</head>
<body>
<ul class="nav nav-wego nav-tabs-wego">
    <li id="receive-li"><a href="/WeGo/message/receivelist">받은 메세지</a></li>
    <li id="send-li"><a href="/WeGo/message/sendlist">보낸 메세지</a></li>
    <li><a href="/WeGo/message/write">메세지 쓰기</a></li>
</ul>
<div id="wrapper">
	<c:choose>
		<c:when test="${not empty messageList }">
			<c:forEach items="${messageList }" var="message">
				 <div class="messageWrapper">
						<div class="subject box">
							<a href="/WeGo/message/detail/${message.id }">${message.title }</a>
					   </div><!--
					--><div class="writer box">${message.senderEmail }</div><!--
					--><div class="writer box">${message.receiverEmail }</div><!--
					--><div class="create-date box">${board.sendDate }</div>
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<div id="no-articles">
				쪽지가 없습니다.
			</div>
		</c:otherwise>
	</c:choose>
</div>
</body>
</html>