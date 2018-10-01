<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>We-Go 회원가입</title>
<script type="text/javascript" src="/WeGo/js/jquery-3.3.1.min.js" charset="utf-8"></script>
<script type="text/javascript">
$().ready(function () {
	
	var isPasswordPatternOK = false;
	var isSamePasswords = false;
	
	$("#password").keyup(function () {
		$.post("/WeGo/member/check/password"
				, {
					"password": $(this).val()
				}
				, function(response) {
					if ( response.available ) {
						$("#password-check").text("사용가능한 패스워드 입니다.");
						isPasswordPatternOK = true;
					}
					else {
						$("#password-check").text("패스워드는 영문,숫자,특수문자 조합으로 8자이상으로 만들어주세요.");
						isPasswordPatternOK = false;
					}
				})
	})
	
	$("#password2").keyup(function () {
		if ( $("#password").val() != $("#password2").val() ) {
			$("#password-check2").text("비밀번호가 일치하지 않습니다.");
			isSamePasswords = false;
		}
		else {
			$("#password-check2").text("비밀번호가 일치합니다.");
			isSamePasswords = true;
		}
	})
	
	$("#registBtn").click(function () {
		if ( !isPasswordPatternOK ) {
			alert("패스워드는 영문,숫자,특수문자 조합으로 8자이상으로 만들어주세요.");
			$("#password").focus();
		}
		else if ( !isSamePasswords ) {
			alert("비밀번호가 일치하지 않습니다.");
			$("#password2").focus();
		}
		else {
			$("#registForm").submit();
		}
	});
});
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/view/common/navigation.jsp" />
	<div id="wrapper">
		<form id="registForm" method="post" action="/WeGo/member/regist" enctype="multipart/form-data" >
			<div>
				<input type="email" id="eamil" name="email" placeholder="이메일 입력" />
			</div>
			<div>
				<input type="password" id="password" name="password" placeholder="비밀번호 입력" />
			</div>
			<div>
				<span id="password-check">
					패스워드는 영문,숫자,특수문자 조합으로 8자이상으로 만들어주세요.
				</span>
			</div>
			<div>
				<input type="password" id="password2" name="password2" placeholder="비밀번호 재입력" />
			</div>
			<div>
				<span id="password-check2">
				</span>
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
				<input type="button" id="registBtn" value="회원 가입" />
			</div>
		</form>
	</div>
</body>
</html>