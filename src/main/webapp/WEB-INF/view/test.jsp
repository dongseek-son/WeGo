<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/view/common/layout/layout_header.jsp" />

<script type="text/javascript">
	$().ready(function() {
		
		var page = 0;
		var isEnd = false;
		
		$(window).scroll(function()	// 네비바 고정, 프로그레스바 
				 {
			 		var sc = $(window).scrollTop();
			 		var scPer = 100 * sc / ($(document).height() - $(window).height());
			 		
			 		if ( scPer >= 100 ) {
			 			page = page + 1;
			 			
			 			$.post( "/WeGo/ourgoal/list/tag" , {
			 				tag : ${tag}
			 				, page : page
			 			}, function(response) {
			 				
			 				var goalVOList = response.goalVOList;
			 				
							if ( goalVOList ) {
								for ( var index in goalVOList ) {
									var sdom = $('<div style="height: 500px">' + goalVOList[index].id  + ' / ' + goalVOList[index].title + '</div>');
									$(".container").append(sdom);
								}
							}
							else if ( !isEnd ) {
								var sdom = $('<div style="height: 500px">마지막 목표 입니다.</div>');
								$(".container").append(sdom);
								isEnd = true;
							}
						});
			 		}
				 });
	});
</script>

<div class="container">
	<c:forEach var="goal" items="${goalVOList }">
		<div style="height: 500px">${goal.id } / ${goal.title }</div>
	</c:forEach>
</div>

<jsp:include page="/WEB-INF/view/common/layout/layout_footer.jsp" />