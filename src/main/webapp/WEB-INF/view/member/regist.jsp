<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>We-Go 회원가입</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/common/navigation.jsp" />
	<div id="wrapper">
		<form id="registForm" method="post" action="/WeGo/member/regist.go" enctype="multipart/form-data" >
			<div>
				<input type="email" id="eamil" name="email" placeholder="이메일 입력" />
			</div>
			<div>
				<input type="password" id="password" name="password" placeholder="비밀번호 입력" />
			</div>
			<div>
				<input type="password" id="password_ck" name="password_ck" placeholder="비밀번호 재입력" />
			</div>
			<div>
				<input type="text" id="name" name="name" placeholder="이름 입력" />
			</div>
			<div>
				<input type="tel" id="tel" name="tel" placeholder="전화번호 입력" />
			</div>		
			<div>
				<input type="file" name="profileFile" placeholder="프로필 사진 선택" />
			</div>
			<div>
				<input type="submit" id="registBtn" value="회원 가입" />
			</div>
		</form>
	</div>
</body>
</html>