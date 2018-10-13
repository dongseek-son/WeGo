<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/view/common/layout/layout_header.jsp" />

<style type="text/css">
.goal-div {
	height: 250px;
	width: 100%;
	border: 2px solid #90C226;
	background-color: #F2FFED;
}
</style>
<script type="text/javascript">
	$().ready(function() {
		
		var page = 0;
		var isEnd = false;
		
		$(window).scroll(function()
				 {
			 		var sc = $(window).scrollTop();
			 		var scPer = 100 * sc / ($(document).height() - $(window).height());
			 		
			 		if ( scPer >= 100 ) {
			 			console.log(page);
			 			console.log(isEnd);
			 			
			 			page = page + 1;
			 			
			 			$.post( "/WeGo/ourgoal/list/page" , {
			 				page : page
			 			}, function(response) {
			 				
			 				var goalVOList = response.goalVOList;
			 				
							if ( goalVOList ) {
								for ( var index in goalVOList ) {
									var sdom = $('<div class="goal-div">' + goalVOList[index].id  + ' / ' + goalVOList[index].title + '</div>');
									$("#goals-div").append(sdom);
								}
							}
							else if ( !isEnd ) {
								var sdom = $('<div id="end-div">마지막 목표 입니다.</div>');
								$("#goals-div").append(sdom);
								isEnd = true;
							}
						});
			 		}
				 });
	});
</script>

<div class="container">
	<div class="row">
    	<div class="col-sm-1">
    	</div>
    	<div class="col-sm-10">
    		<div id="goals-div">
    			<c:forEach var="goal" items="${goalVOList }">
					<div class="goal-div">${goal.id } / ${goal.title }</div>
				</c:forEach>
    		</div>
    	</div>
    	<div class="col-sm-1">
    	</div>
	</div>
</div>

<jsp:include page="/WEB-INF/view/common/layout/layout_footer.jsp" />