<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<jsp:include page="/WEB-INF/view/common/layout/layout_header.jsp" />

<style>
.arrow-div {
	display: inline-block;
	height: 100px;
	width: 30px;
	vertical-align: center;
}
.goal-div {
	display: inline-block;
	height: 200px;
	width: 16%;
	border: 2px solid #90C226;
	background-color: #F2FFED;
}
</style>
<script>
$().ready(function() {

	var firstIndex = 0;
	var size = ${size};
	var firstId = "${goalVOList[0].id}";
	
	function listChange(div) {
		$.post("/WeGo/test2/listChange", {
			firstIndex : firstIndex
			, size : size
			, firstId : firstId
		}, function(response) {
			if ( !response.status ) {
				window.location = "/WeGo/test2";
			} 
			else {
				firstIndex = response.firstIndex;
				console.log(firstIndex);
				div.find(".span1").text(response.goalVOList[0].id);
				div.find(".span2").text(response.goalVOList[1].id);
				div.find(".span3").text(response.goalVOList[2].id);
				div.find(".span4").text(response.goalVOList[3].id);
				div.find(".span5").text(response.goalVOList[4].id);
			}
		});
	}
		
	$(".left").click(function() {
		var div = $(this).closest("#goals-div");
		firstIndex = firstIndex - 1;
		if( firstIndex < 0 ) {
			firstIndex = size - 1;
		}
		listChange(div);
	});
	
	$(".right").click(function() {
		var div = $(this).closest("#goals-div");
		firstIndex = firstIndex + 1;
		if( firstIndex >= size ) {
			firstIndex = 0;
		}
		listChange(div);
	});
	
});
</script>

<div class="container">
	<div class="row">
    	<div class="col-sm-1">
    	</div>
    	<div class="col-sm-10">
    		<div id="goals-div">
    			<div class="arrow-div">
					<a class="left" href="#myCarousel" role="button">
					    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
						<span class="sr-only">Previous</span>
					</a>   			
    			</div>
    			<div class="goal-div">
    				<span class="span1">${goalVOList[0].id }</span>
    			</div>
    			<div class="goal-div" data-column="2">
    				<span class="span2">${goalVOList[1].id }</span>
    			</div>
    			<div class="goal-div" data-column="3">
    				<span class="span3">${goalVOList[2].id }</span>
    			</div>
    			<div class="goal-div" data-column="4">
    				<span class="span4">${goalVOList[3].id }</span>
    			</div>
    			<div class="goal-div" data-column="5">
    				<span class="span5">${goalVOList[4].id }</span>
    			</div>
    			<div class="arrow-div">
    				<a class="right" href="#myCarousel" role="button">
					    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
						<span class="sr-only">Next</span>
					</a>
    			</div>
    		</div>
    	</div>
    	<div class="col-sm-1">
    	</div>
	</div>
</div>

<jsp:include page="/WEB-INF/view/common/layout/layout_footer.jsp" />