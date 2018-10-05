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
<script type="text/javascript" src="/WeGo/js/main.js"></script>
<script type="text/javascript">
$().ready(function() {
	
	if ( ${not empty memberVO} ) {
		$("#email").keyup();
		$("#email").blur();
		$("#name").keyup();
		$("#tel").keyup();
		$("#registModal").modal();
	}
	else {
		$("#registModal").on('shown.bs.modal', function () {
			isEmailPattern = false;
			isEmailDuplicate = true;
			isPasswordPattern = false;
			isSamePasswords = false;
			isNamePattern = false;
			isTelPattern = false;
		});
	}
	
	if ( ${not empty emailAuthVO} ) {
		$("#emailAuthModal").modal();
	}
	
	if ( ${not empty emailAuthVOForPasswordInit} ) {
		$("#passwordInitModal").modal();
	}
	
});
</script>

<div id="background" class="container-fluid">
	<jsp:include page="/WEB-INF/view/member/modal/registModal.jsp" />
	<jsp:include page="/WEB-INF/view/member/modal/loginModal.jsp" />
	<jsp:include page="/WEB-INF/view/member/modal/emailAuthModal.jsp" />
	<jsp:include page="/WEB-INF/view/member/modal/findEmailModal.jsp" />
	<jsp:include page="/WEB-INF/view/member/modal/passwordInitSendModal.jsp" />
	<jsp:include page="/WEB-INF/view/member/modal/passwordInitModal.jsp" />
</div>

<jsp:include page="/WEB-INF/view/common/layout/layout_footer.jsp" />