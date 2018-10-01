<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/view/common/layout/layout_header.jsp" />

	<c:url value="/memberlogin" var="loginUrl"/>
	<form action="${loginUrl}" method="post"> 
		<div>
			<input type="email" id="email" name="email" placeholder="이메일 입력" />
		</div>
		<div>
			<input type="password" id="password" name="password" placeholder="비밀번호 입력" />
		</div>
		<div>
			<input type="submit" value="로그인" />
		</div>
	</form>

<jsp:include page="/WEB-INF/view/common/layout/layout_footer.jsp" />