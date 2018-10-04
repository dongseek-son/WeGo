<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<div id="message-wrapper">
	<table class="table table-hover">
	    <thead>
	    	<tr>
	        	<th>발신자</th>
	        	<th>수신자</th>
	        	<th>제목</th>
	      		<th>보낸날짜</th>
	    	</tr>
	    </thead>
	    <tbody>
		<c:choose>
			<c:when test="${not empty messageList }">
				<c:forEach items="${messageList }" var="message">
					<tr>
						<td>${message.senderEmail }</td>
						<td>${message.receiverEmail }</td>
						<td><a href="/WeGo/message/detail/${message.id }">${message.title }</a></td>
						<td>${fn:substring(message.sendDate,0,10) }</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="4">
						<c:if test="${condition eq 'receive'}">
							<span class="text-center">받은 쪽지가 없습니다.</span>
						</c:if>
						<c:if test="${condition eq 'send'}">
							<span class="text-center">보낸 쪽지가 없습니다.</span>
						</c:if>
					</td>
				</tr>
			</c:otherwise>
		</c:choose>
		</tbody>
	</table>
</div>
</body>
</html>