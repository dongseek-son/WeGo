<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/common/navigation.jsp" />
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