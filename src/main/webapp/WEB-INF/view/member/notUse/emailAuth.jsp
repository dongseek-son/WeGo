<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/view/common/layout/layout_header.jsp" />

<form action="/WeGo/member/emailAuth" method="post">
	<input type="hidden" id="authUrl" name="authUrl" value="${emailAuthVO.authUrl }">
	<input type="hidden" id="email" name="email" value="${emailAuthVO.email }">
	<div>
		<span>환영합니다. 이메일 인증을 완료하시려면 아래 버튼을 클릭해주세요.</span>	
	</div>
	<div>
		<input type="submit" value="이메일 인증완료">
	</div>
</form>

<jsp:include page="/WEB-INF/view/common/layout/layout_footer.jsp" />