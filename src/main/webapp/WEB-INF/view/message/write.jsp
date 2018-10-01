<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/js/jquery-3.3.1.min.js" charset="UTF-8"></script>
<script type="text/javascript">
	$().ready() {
		
	}
</script>
</head>
<body>
<jsp:include page="/WEB-INF/view/common/navigation.jsp" />
<form action="/WeGo/message/write" method="post">
	<div>
		<input type="hidden" name="token" value="${sessionScope._CSRF_ }">	
	</div>
	<div>
		<input type="text" id="senderId" name="senderId" value="${sessionScope._USER_.email }" readonly="readonly">	
	</div>
	<div>
		<input type="text" id="receiverId" name="receiverId" value="${receiverEmail }" placeholder="수신자 ID 입력">	
	</div>
	<div>
		<input type="text" id="title" name="title" placeholder="제목 입력">
	</div>
	<div>
		<textarea id="detail" name="detail" placeholder="내용 입력"></textarea>
	</div>
	<div>
		<input type="submit" value="전송">
	</div>	
</form>
</body>
</html>