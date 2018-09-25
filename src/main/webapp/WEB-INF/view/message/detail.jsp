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
	<div>
		발신 : ${messageVO.senderId }
	</div>
	<div>
		수신 : ${messageVO.receiverId }
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
		<a href="/WeGo/message/delete.go/${messageVO.id }">삭제</a>
	</div>	
</body>
</html>