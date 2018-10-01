<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/view/common/layout/layout_header.jsp" />

<script type="text/javascript">
$().ready(function () {
	
	var isEmailPattern = false;
	var isPasswordPattern = false;
	var isSamePasswords = false;
	var isNamePattern = false;
	var isTelPattern = false;
	
	$("#email").keyup(function() {
		$.post("/WeGo/member/check/email"
				, {
					"email": $(this).val()
				}
				, function(response) {
					if ( response.available ) {
						$("#email-check").text("올바른 이메일 형식입니다.");
						isEmailPattern = true;
					}
					else {
						$("#email-check").text("올바른 이메일 형식이 아닙니다.");
						isEmailPattern = false;
					}
				})
	})
	
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
	
	$("#name").keyup(function () {
		$.post("/WeGo/member/check/name"
				, {
					"name": $(this).val()
				}
				, function(response) {
					if ( response.available ) {
						$("#name-check").text("사용가능한 이름 입니다.");
						isNamePattern = true;
					}
					else {
						$("#name-check").text("이름은 한글, 영어, 숫자만 허용됩니다.(2-18자)");
						isNamePattern = false;
					}
				})
	})
	
	$("#tel").keyup(function () {
		$.post("/WeGo/member/check/tel"
				, {
					"tel": $(this).val()
				}
				, function(response) {
					if ( response.available ) {
						$("#tel-check").text("올바른 전화번호 입니다.");
						isTelPattern = true;
					}
					else {
						$("#tel-check").text("전화번호는 숫자만 입력해주세요.(10-11자)");
						isTelPattern = false;
					}
				})
	})
	
	$("#registBtn").click(function () {
		if ( !isEmailPattern ) {
			alert("올바른 이메일 형식이 아닙니다.");
			$("#email").focus();
		}
		else if ( !isPasswordPattern ) {
			alert("패스워드는 영문,숫자,특수문자 조합으로 8자이상으로 만들어주세요.");
			$("#password").focus();
		}
		else if ( !isSamePasswords ) {
			alert("비밀번호가 일치하지 않습니다.");
			$("#password2").focus();
		}
		else if ( !isNamePattern ) {
			alert("이름은 한글, 영어, 숫자만 허용됩니다.(2-18자)");
			$("#name").focus();
		}
		else if ( !isTelPattern ) {
			alert("전화번호는 숫자만 입력해주세요.(10-11자)");
			$("#tel").focus();
		}
		else {
			$("#registForm").submit();
		}
	});
});
</script>


	<div id="wrapper">
		<form id="registForm" method="post" action="/WeGo/member/regist" enctype="multipart/form-data" >
			<div>
				<input type="email" id="email" name="email" placeholder="이메일 입력" />
			</div>
			<div>
				<span id="email-check">
				</span>
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
				<span id="name-check">
					이름은 한글, 영어, 숫자만 허용됩니다.(2-18자)
				</span>
			</div>
			<div>
				<input type="tel" id="tel" name="tel" placeholder="전화번호 입력" />
			</div>
			<div>
				<span id="tel-check">
					전화번호는 숫자만 입력해주세요.(10-11자)
				</span>
			</div>		
			<div>
				<input type="file" name="profileFile" placeholder="프로필 사진 선택" />
			</div>
			<div>
				프로필 사진을 선택하지 않을시, 기본 사진으로 적용됩니다.
			</div>		
			<div>
				<input type="button" id="registBtn" value="회원 가입" />
			</div>
		</form>
	</div>
<jsp:include page="/WEB-INF/view/common/layout/layout_footer.jsp" />