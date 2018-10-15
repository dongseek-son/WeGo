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
.goals-div .active {
	border: 2px solid red;
}
.goals-contents-div {
	display: inline-block;
	width: 88%;
}
</style>
<script>
$().ready(function() {
	
	var lastLevel = 1;
	var activeIdList = new Array();
	
	function listChange( div, firstIndex, size, cb = function(){} ) {
		
		$.post("/WeGo/test2/listChange", {
			firstIndex : firstIndex
			, size : size
			, firstId : div.data("first_id")
		}, function(response) {
			if ( !response.status ) {
				window.location = "/WeGo/test2";
			} 
			else {
				div.find(".goal-div").first().attr("data-id", response.goalVOList[0].id);
				div.find(".goal-div").first().next().attr("data-id", response.goalVOList[1].id);
				div.find(".goal-div").first().next().next().attr("data-id", response.goalVOList[2].id);
				div.find(".goal-div").last().prev().attr("data-id", response.goalVOList[3].id);
				div.find(".goal-div").last().attr("data-id", response.goalVOList[4].id);
				
				div.find(".goal-div").first().html("<span>" + response.goalVOList[0].id + "</span>");
				div.find(".goal-div").first().next().html("<span>" + response.goalVOList[1].id + "</span>");
				div.find(".goal-div").first().next().next().html("<span>" + response.goalVOList[2].id + "</span>");
				div.find(".goal-div").last().prev().html("<span>" + response.goalVOList[3].id + "</span>");
				div.find(".goal-div").last().html("<span>" + response.goalVOList[4].id + "</span>");
				
				cb();
			}
		});
	}
	
	function newGoalsDiv(lev, id) {
		var goalsDiv = $(`<div class="goals-div" data-first_index="0" data-level="` + lev + `">
							<div class="arrow-div"></div>
							<div class="goals-contents-div"></div>
							<div class="arrow-div"></div>
						</div>`);
		var left = `<a class="left" href="#myCarousel" role="button">
						<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
						<span class="sr-only">Previous</span>
					</a>`;
		var right = `<a class="right" href="#myCarousel" role="button">
						<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
						<span class="sr-only">Next</span>
					</a>`;
		var size = 0;
		var firstId = null;
					
		$.post("/WeGo/test2/childrenList", {
			id : id
		}, function(response) {
			
			if ( lastLevel != 1 ) {
				for (lastLevel; lastLevel >= lev; lastLevel--) {
					$(".col-sm-10").children("[data-level='" + lastLevel + "']").remove();
				}
			}
			
			$(".col-sm-10").append(goalsDiv);
			var goalsDivDom = $(".col-sm-10").children("[data-level='" + lev + "']");
			
			
			if ( response.size ) {
				size = response.size;
				goalsDivDom.attr("data-first_id", response.goalVOList[0].id);
				goalsDivDom.attr("data-size", size);
			}
			
			if ( !response.status ) {
				goalsDivDom.find(".goals-contents-div").html("하위 항목이 없습니다.");
			}
			else if ( size > 5 ) {
				goalsDivDom.find(".arrow-div").first().html(left);
				goalsDivDom.find(".arrow-div").last().html(right);
			}
			
			for ( var i = 0 ; i < size; i++ ) {
				var eachDom = $(`<div class="goal-div" data-id="` + response.goalVOList[i].id + `">
									<span>` + response.goalVOList[i].id + `</span>
							   </div>`)
				goalsDivDom.find(".goals-contents-div").append(eachDom);
			}
			
			lastLevel = lev;
		});
	}
		
	$(".col-sm-10").on("click", ".left" ,function() {
		var div = $(this).closest(".goals-div");
		var level = parseInt(div.data("level"));
		div.find(".active").removeClass("active");
		var firstIndex = parseInt(div.data("first_index"));
		var size = parseInt(div.data("size"));
		firstIndex = firstIndex - 1;
		if( firstIndex < 0 ) {
			firstIndex = size - 1;
		}
		div.data("first_index", firstIndex);
		listChange(div, firstIndex, size, function() {
			div.find("div[data-id='" + activeIdList[level-1] + "']").addClass("active");
		});
	});
	
	$(".col-sm-10").on("click", ".right" ,function() {
		var div = $(this).closest(".goals-div");
		var level = parseInt(div.data("level"));
		div.find(".active").removeClass("active");
		var firstIndex = parseInt(div.data("first_index"));
		var size = parseInt(div.data("size"));
		firstIndex = firstIndex + 1;
		if( firstIndex >= size ) {
			firstIndex = 0;
		}
		div.data("first_index", firstIndex);
		listChange(div, firstIndex, size, function() {
			div.find("div[data-id='" + activeIdList[level-1] + "']").addClass("active");
		});
	});
	
	$(".col-sm-10").on("click", ".goal-div", function() {
		$(this).closest(".goals-div").find(".active").removeClass("active");
		$(this).addClass("active");
		var id = $(this).data("id");
		var lev = $(this).closest(".goals-div").data("level");
		if ( activeIdList.length ) {
			for( var i = activeIdList.length; i >= lev; i-- ) {
				activeIdList.pop();
			}
		}
		activeIdList.push(id);
		$(this).data("id", id);
		console.log(activeIdList);
		newGoalsDiv(lev+1, id);
	});
	
});
</script>

<div class="container">
	<div class="row">
    	<div class="col-sm-1">
    	</div>
    	<div class="col-sm-10">
    		<div class="goals-div" data-level="1" data-first_index="0" data-size="${size }" data-first_id="${goalVOList[0].id}" }>
    			<div class="arrow-div">
    				<c:if test="${size > 5 }">
						<a class="left" href="#" role="button">
						   	<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
							<span class="sr-only">Previous</span>
						</a>
					</c:if>   			
    			</div>
    			<div class="goals-contents-div">
	    			<c:forEach var="goalVO" items="${goalVOList }">
	    				<div class="goal-div" data-id="${goalVO.id }">
	    					<span>${goalVO.id }</span>
	    				</div>
	    			</c:forEach>
    			</div>
    			<div class="arrow-div">
    				<c:if test="${size > 5 }">
    					<a class="right" href="#" role="button">
					   		<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
							<span class="sr-only">Next</span>
						</a>
					</c:if>
    			</div>
    		</div>
    	</div>
    	<div class="col-sm-1">
    	</div>
	</div>
</div>

<jsp:include page="/WEB-INF/view/common/layout/layout_footer.jsp" />