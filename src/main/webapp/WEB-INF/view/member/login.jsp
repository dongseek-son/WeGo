<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Wego - Login</title>
<script type="text/javascript">
	var message = "${param.message}";
	if ( message != "" ) {
		alert(message);
	}
	var message = "${message}";
	if ( message != "" ) {
		alert(message);
	}
</script>
</head>
<body>
	<c:url value="/memberlogin" var="loginUrl"/>
	<form action="${loginUrl}" method="post"> 
		<div>
			<input type="text" id="id" name="id" placeholder="아이디 입력" />
		</div>
		<div>
			<input type="password" id="password" name="password" placeholder="비밀번호 입력" />
		</div>
		<div>
			<input type="submit" value="로그인" />
		</div>
	</form>
</body>
</html>