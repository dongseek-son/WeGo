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
		${messageVO.senderId }
	</div>
	<div>
		<input type="text" id="receiverId" name="receiverId" placeholder="수신자 ID 입력">	
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
</body>
</html>