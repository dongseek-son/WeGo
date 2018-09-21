<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Wego - Login</title>
</head>
<body>
	<form action="/member/login.go" method="post">
		<div>
			<input type="text" id="id" name="id" placeholder="아이디 입력" />
		</div>
		<div>
			<input type="password" id="password" name="password" placeholder="비밀번호 입력" />
		</div>
		<div>
			<input type="submit" value="로그인" />
		</div>
		<input type="button" onclick="script: alert('복기')" value="복기" />
	</form>
</body>
</html>