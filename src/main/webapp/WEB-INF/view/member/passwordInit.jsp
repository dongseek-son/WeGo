<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/view/common/layout/layout_header.jsp" />

<script>
$().ready(function() {
	
	var isPasswordPattern = false;
	var isSamePasswords = false;
	
	$("#password").keyup(function () {
		$.post("/WeGo/member/check/password"
				, {
					"password": $(this).val()
				}
				, function(response) {
					if ( response.available ) {
						$("#password-check").text("사용가능한 패스워드 입니다.");
						isPasswordPattern = true;
					}
					else {
						$("#password-check").text("패스워드는 영문,숫자,특수문자 조합으로 8자이상으로 만들어주세요.");
						isPasswordPattern = false;
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
	
	$("#passwordBtn").click(function() {
		if ( !isPasswordPattern ) {
			alert("패스워드는 영문,숫자,특수문자 조합으로 8자이상으로 만들어주세요.");
			$("#password").focus();
		}
		else if ( !isSamePasswords ) {
			alert("비밀번호가 일치하지 않습니다.");
			$("#password2").focus();
		}
		else {
			$("#passwordForm").submit();
		}
	})
	
})
</script>

<form action="/WeGo/member/passwordInit" method="post" id="passwordForm">
	<input type="hidden" id="authUrl" name="authUrl" value="${emailAuthVO.authUrl }">
	<input type="hidden" id="email" name="email" value="${emailAuthVO.email }">
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
		<input type="button" id="passwordBtn" value="비밀번호 바꾸기">
	</div>
</form>

<jsp:include page="/WEB-INF/view/common/layout/layout_footer.jsp" />