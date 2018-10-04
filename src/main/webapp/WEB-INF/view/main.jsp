<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="/WEB-INF/view/common/layout/layout_header.jsp" />
<style type="text/css">
#background {
	margin-top: -50px;
	width:100%;
  	height:95vh;
  	background: url("http://localhost:8080/WeGo/img/main.jpg") no-repeat center center fixed; 
  	background-size: cover;
}
</style>
<script type="text/javascript">
$().ready(function () {
	
	var isEmailPattern = false;
	var isEmailDuplicate = true;
	var isPasswordPattern = false;
	var isSamePasswords = false;
	var isNamePattern = false;
	var isTelPattern = false;
	
	function checkRegistBtn() {
		if( isEmailPattern && !isEmailDuplicate && isPasswordPattern 
				&& isSamePasswords && isNamePattern && isTelPattern ) {
			$("#registBtn").attr("class", "btn btn-success btn-block" );
		}
		else {
			$("#registBtn").attr("class", "btn btn-warning btn-block disabled" );
		}
	};
	
	$("input").blur(function() {
		checkRegistBtn();
	});
	
	$("#email").blur(function() {
		$.post("/WeGo/member/check/email/registed"
				, {
					"email": $(this).val()			
				}
				, function(response) {
					if ( response.registed ) {
						$("#email-group").attr("class","form-group has-warning has-feedback");
						$("#email-check").attr("class", "text-warning");
						$("#email-check").text("중복된 이메일 입니다.");
						$("#email-icon").attr("class", "glyphicon glyphicon-remove form-control-feedback");
						isEmailDuplicate = true;
					}
					else if ( !isEmailPattern ){
						$("#email-group").attr("class","form-group has-warning has-feedback");
						$("#email-check").attr("class", "text-warning");
						$("#email-check").text("올바른 이메일 형식이 아닙니다.");
						$("#email-icon").attr("class", "glyphicon glyphicon-remove form-control-feedback");
					}
					else {
						$("#email-group").attr("class","form-group has-success has-feedback");
						$("#email-check").attr("class", "text-success");
						$("#email-check").text("사용가능한 이메일 입니다.");
						$("#email-icon").attr("class", "glyphicon glyphicon-ok form-control-feedback");
						isEmailDuplicate = false;
					}
				});
	})
	
	$("#email").keyup(function() {
		$.post("/WeGo/member/check/email/pattern"
				, {
					"email": $(this).val()
				}
				, function(response) {
					if ( response.available ) {
						$("#email-group").attr("class","form-group has-success has-feedback");
						$("#email-check").attr("class", "text-success");
						$("#email-check").text("올바른 이메일 형식입니다.");
						$("#email-icon").attr("class", "glyphicon glyphicon-ok form-control-feedback");
						isEmailPattern = true;
					}
					else {
						$("#email-group").attr("class","form-group has-warning has-feedback");
						$("#email-check").attr("class", "text-warning");
						$("#email-check").text("올바른 이메일 형식이 아닙니다.");
						$("#email-icon").attr("class", "glyphicon glyphicon-remove form-control-feedback");
						isEmailPattern = false;
					}
				});
		checkRegistBtn();
	});
	
	$("#password").keyup(function () {
		$("#password2-group").show();
		$("#password2").keyup();
		$.post("/WeGo/member/check/password"
				, {
					"password": $(this).val()
				}
				, function(response) {
					if ( response.available ) {
						$("#password-group").attr("class","form-group has-success has-feedback");
						$("#password-check").attr("class", "text-success");
						$("#password-check").text("사용가능한 패스워드 입니다.");
						$("#password-icon").attr("class", "glyphicon glyphicon-ok form-control-feedback");
						isPasswordPattern = true;
					}
					else {
						$("#password-group").attr("class","form-group has-warning has-feedback");
						$("#password-check").attr("class", "text-warning");
						$("#password-check").text("패스워드는 영문,숫자,특수문자 조합으로 8자이상으로 만들어주세요.");
						$("#password-icon").attr("class", "glyphicon glyphicon-remove form-control-feedback");
						isPasswordPattern = false;
					}
				});
		checkRegistBtn();
	});
	
	$("#password2").keyup(function () {
		if ( $("#password").val() != $("#password2").val() ) {
			$("#password2-group").attr("class","form-group has-warning has-feedback");
			$("#password2-check").attr("class", "text-warning");
			$("#password2-check").text("비밀번호가 일치하지 않습니다.");
			$("#password2-icon").attr("class", "glyphicon glyphicon-remove form-control-feedback");
			isSamePasswords = false;
		}
		else {
			$("#password2-group").attr("class","form-group has-success has-feedback");
			$("#password2-check").attr("class", "text-success");
			$("#password2-check").text("비밀번호가 일치합니다.");
			$("#password2-icon").attr("class", "glyphicon glyphicon-ok form-control-feedback");
			isSamePasswords = true;
		};
		checkRegistBtn();
	});
	
	$("#name").keyup(function () {
		$.post("/WeGo/member/check/name"
				, {
					"name": $(this).val()
				}
				, function(response) {
					if ( response.available ) {
						$("#name-group").attr("class","form-group has-success has-feedback");
						$("#name-check").attr("class", "text-success");
						$("#name-check").text("사용가능한 이름 입니다.");
						$("#name-icon").attr("class", "glyphicon glyphicon-ok form-control-feedback");
						isNamePattern = true;
					}
					else {
						$("#name-group").attr("class","form-group has-warning has-feedback");
						$("#name-check").attr("class", "text-warning");
						$("#name-check").text("이름은 한글, 영어, 숫자만 허용됩니다.(2-18자)");
						$("#name-icon").attr("class", "glyphicon glyphicon-remove form-control-feedback");
						isNamePattern = false;
					}
				});
		checkRegistBtn();
	});
	
	$("#tel").keyup(function () {
		$.post("/WeGo/member/check/tel"
				, {
					"tel": $(this).val()
				}
				, function(response) {
					if ( response.available ) {
						$("#tel-group").attr("class","form-group has-success has-feedback");
						$("#tel-check").attr("class", "text-success");
						$("#tel-check").text("올바른 전화번호 입니다.");
						$("#tel-icon").attr("class", "glyphicon glyphicon-ok form-control-feedback");
						isTelPattern = true;
					}
					else {
						$("#tel-group").attr("class","form-group has-warning has-feedback");
						$("#tel-check").attr("class", "text-warning");
						$("#tel-check").text("전화번호는 숫자만 입력해주세요.(9-11자)");
						$("#tel-icon").attr("class", "glyphicon glyphicon-remove form-control-feedback");
						isTelPattern = false;
					}
				});
		checkRegistBtn();
	});
	
	$("#registBtn").click(function () {
 		if ( !isEmailPattern ) {
			alert("올바른 이메일 형식이 아닙니다.");
			$("#email").focus();
		}
		else if ( isEmailDuplicate ) {
			alert("중복된 이메일 입니다.");
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
			alert("전화번호는 숫자만 입력해주세요.(9-11자)");
			$("#tel").focus();
		}
		else { 
			$("#registForm").submit();
	 	} 
	});
	
	if ( ${not empty memberVO} ) {
		$("#email").keyup();
		$("#email").blur();
		$("#name").keyup();
		$("#tel").keyup();
		$("#registModal").modal();
	}
	
	$("#loginBtn").click(function() {
		$("#loginForm").submit();
	});
	
});
</script>

<div id="background" class="contaiver-fluid">
	<!-- Modal -->
  <div class="modal fade" id="registModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Join</h4>
        </div>
        <div class="modal-body">
          <form:form id="registForm" method="post" action="/WeGo/member/regist" enctype="multipart/form-data" >
			<div id="email-group" class="form-group">
				<div class="input-group">
			  	  <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
			  	  <input type="email" id="email" name="email" placeholder="이메일 입력" class="form-control" value="${memberVO.email }"/>
			  	  <span id="email-icon"></span>
			 	</div>
			 	<span id="email-check" class="help-block"></span>
			</div>
			<div id="password-group" class="form-group">
				<div class="input-group">
			  	  <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
			  	  <input type="password" id="password" name="password" placeholder="비밀번호 입력" class="form-control"/>
			  	  <span id="password-icon"></span>
			 	</div>
			 	<span id="password-check" class="help-block"></span>
			</div>
			<div id="password2-group" class="form-group" style="display:none">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
					<input type="password" id="password2" name="password2" placeholder="비밀번호 재입력" class="form-control"/>
					<span id="password2-icon"></span>
				</div>
				<span id="password2-check" class="help-block"></span>
			</div>
			<div id="name-group" class="form-group">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
					<input type="text" id="name" name="name" placeholder="이름 입력" class="form-control" value="${memberVO.name }"/>
					<span id="name-icon"></span>
				</div>
				<span id="name-check" class="help-block"></span>
			</div>
			<div id="tel-group" class="form-group">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-earphone"></i></span>
					<input type="tel" id="tel" name="tel" placeholder="전화번호 입력" class="form-control" value="${memberVO.tel }"/>
					<span id="tel-icon"></span>
				</div>
				<span id="tel-check" class="help-block"></span>
			</div>
			<div id="profile-group" class="form-group">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-camera"></i></span>
					<input type="file" name="profileFile" placeholder="프로필 사진 선택" class="form-control"/>
					<span id="profile-icon"></span>
				</div>
				<span id="profile-check" class="help-block">사진을 선택하지 않을시, 기본 이미지로 대체됩니다.</span>
			</div>
		</form:form>
        </div>
        <div class="modal-footer">
				<input type="button" id="registBtn" class="btn btn-warning btn-block disabled" value="회원 가입" />
        </div>
      </div>
      
    </div>
  </div>
  
  <!-- Modal -->
  <div class="modal fade" id="loginModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Login</h4>
        </div>
        <div class="modal-body">
          <form id="loginForm" method="post" action="/WeGo/memberlogin" >
			<div id="email-group" class="form-group">
				<div class="input-group">
			  	  <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
			  	  <input type="email" name="email" placeholder="이메일 입력" class="form-control"/>
			 	</div>
			</div>
			<div id="password-group" class="form-group">
				<div class="input-group">
			  	  <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
			  	  <input type="password" name="password" placeholder="비밀번호 입력" class="form-control"/>
			 	</div>
			</div>
			
		</form>
        </div>
        <div class="modal-footer">
			<input type="button" id="loginBtn" class="btn btn-sucess btn-block" value="로그인" />
        </div>
      </div>
      
    </div>
  </div>
  
</div>
<jsp:include page="/WEB-INF/view/common/layout/layout_footer.jsp" />