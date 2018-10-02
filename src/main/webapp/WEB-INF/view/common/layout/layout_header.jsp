<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Going Together - WeGo</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/WeGo/css/bootstrap.css">
<style type="text/css">
.container {
	width: 1170px;
	margin-top: 50px;
}

footer {
	background-color: #90C226;
	position: fixed;
	bottom: 0px;
	width: 100%;
	height: 50px;
}
</style>
<script src="/WeGo/js/jquery-3.3.1.min.js" type="text/javascript"></script>
<script src="/WeGo/js/bootstrap.js" type="text/javascript"></script>
</head>
<body>

<div id = "wapper">
	<header>
		<c:choose>
			<c:when test="${empty sessionScope._USER_ }">
				<jsp:include page="/WEB-INF/view/common/navigationBeforeLogin.jsp" />
			</c:when>
			<c:otherwise>
				<jsp:include page="/WEB-INF/view/common/navigation.jsp" />
			</c:otherwise>
		</c:choose>
	</header>
	<section class="inline">
		<section>
			
		