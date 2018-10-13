<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/view/common/layout/layout_header.jsp" />
<style type="text/css">
.goal-mini {
	border: 1px solid;
}
.tag-div {
	display: inline-block;
}
#detail {
	width: 100%;
	border: 2px solid #90C226;
	background-color: #F2FFED;
}
#title-bar div {
	display: inline-block;
	font-size: 24px;
}
#info-div {
	float: right;
}
.tag-ul {
	list-style: none;
	padding: 0px;
	display: inline-block;
}
.dropdown-menu-tag {
	background-color: #90C226;
}
.dropdown-menu-tag .dropdown-header {
	font-size: 17px;
}
.dropdown-menu-tag .divider {
	margin: 4px 0 9px 0;
}
.dropdown-menu-tag > li > a {
	color: #22741C;
}
.dropdown-menu-tag > li > a:hover, .dropdown-menu > li > a:focus {
	color: #90C226;
	background-color: #22741C;
}
#detail-hashtags-div {
	min-height: 500px;
	position: relative;
	border-top: 1px dashed #999;
}
#hashtags-div {
	position: absolute;
	bottom: 0px;
	width: 100%;
	border-top: 1px dashed #999;
	padding: 10px 10px 0px 10px ;
}
#button-div {
	text-align: center;
	margin-top: 5px;
}
</style>
<script type="text/javascript">

	$().ready( function() {
		
		$(".concernTag").click(function() {
			var concernTag = $(this).data("tag");
			$.post("/WeGo/member/concerntag/add"
					, {
						"concernTag" : concernTag
					}
					, function(response) {
						if ( response.isSuccess ) {
							alert("#" + concernTag + " 을(를) 관심태그에 추가하였습니다.");
						}
						else {
							alert("오류가 발생하였습니다. \\n관리자에게 문의하세요.");
						}
					});
		});
		
		$("#replyBtn").click( function() {
			$("#replyModal").modal({backdrop: "static"});
		} );
		
		$("#writeChildBtn").click(function() {
			window.location = "/WeGo/mygoal/write/${goalVO.id}";
		});
		
	});
</script>

<div class="container">
	<div class="row">
    	<div class="col-sm-2">
    		<c:choose>
    			<c:when test="${not empty parentGoal }">
		    		<div class="goal-mini">
		    			<div><a href="/WeGo/mygoal/detail/${parentGoal.id}">${parentGoal.title }</a></div>
		    			<div>${parentGoal.modifyDate }</div>
		    			<div>progressbar</div>
		    			<c:forEach var="tag" items="${parentGoal.goalVOForMongo.tagList }">
		    				<ul class="tag-ul">
								<li class="dropdown">
									<a class="dropdown-toggle" data-toggle="dropdown" href="#">
										<div class="tag-div">#${tag } </div>
									</a>
									<ul class="dropdown-menu dropdown-menu-tag">
										<li class="dropdown-header">#${tag }</li>
										<li class="divider"></li>
										<li><a href="#">채팅</a></li>
										<li><a class="concernTag" href="#" data-tag="${tag}">관심 태그 추가</a></li>
									</ul>
								</li>
							</ul>
		    			</c:forEach>
		    		</div>
    			</c:when>
    			<c:otherwise>
    				<div>
    					<span>최상위 목표 입니다.</span>
    				</div>
    			</c:otherwise>
    		</c:choose>
    		<hr>
    		<div id="lv1-goals-div">
    			<c:forEach var="lv1Goal" items="${lv1GoalList }">
	    			<div class="goal-mini">
			    		<div><a href="/WeGo/mygoal/detail/${lv1Goal.id}">${lv1Goal.title }</a></div>
			    		<div>${lv1Goal.modifyDate }</div>
			    		<div>progressbar</div>
			    		<c:forEach var="tag" items="${lv1Goal.goalVOForMongo.tagList }">
			    			<ul class="tag-ul">
								<li class="dropdown">
									<a class="dropdown-toggle" data-toggle="dropdown" href="#">
										<div class="tag-div">#${tag } </div>
									</a>
									<ul class="dropdown-menu dropdown-menu-tag">
										<li class="dropdown-header">#${tag }</li>
										<li class="divider"></li>
										<li><a href="#">채팅</a></li>
										<li><a class="concernTag" href="#" data-tag="${tag}">관심 태그 추가</a></li>
									</ul>
								</li>
							</ul>
			    		</c:forEach>
			    	</div>
    			</c:forEach>
    		</div>
    	</div>
    	<div class="col-sm-8">
    		<input type="hidden" name="token" value="${sessionScope._CSRF_ }">
			<div id="detail">
				<div id="title-bar">
					<div id="title-div">
						<span>${goalVO.title }</span>
					</div>
					<div id="info-div">
						<c:if test="${!goalVO.isOpen() }">
							<div>
								<span class="glyphicon glyphicon-lock" title="비공개"></span>
							</div>
						</c:if>
						<c:if test="${goalVO.isDurablity() }">
							<div>
								<span class="glyphicon glyphicon-repeat" title="지속성"></span>
							</div>
						</c:if>
					</div>
				</div>
				<div id="date-div">
					<span>작성일 : ${goalVO.writeDate } / </span>
					<span>최근수정일 : ${goalVO.modifyDate }</span>			
				</div>
				<div id="detail-hashtags-div">
					<div id="detail-div" class="cke_editable cke_editable_themed cke_contents_ltr cke_show_borders">
						${goalVO.detail }
					</div>
					<div id="hashtags-div">
						<c:forEach var="tag" items="${goalVO.goalVOForMongo.tagList }">
			    			<ul class="tag-ul">
								<li class="dropup">
									<a class="dropdown-toggle" data-toggle="dropdown" href="#">
										<div class="tag-div">#${tag } </div>
									</a>
									<ul class="dropdown-menu dropdown-menu-tag">
										<li><a href="#">채팅</a></li>
										<li><a class="concernTag" href="#" data-tag="${tag}">관심 태그 추가</a></li>
										<li class="divider"></li>
										<li class="dropdown-header">#${tag }</li>
									</ul>
								</li>
							</ul>
			    		</c:forEach>
					</div>
				</div>
			</div>
			<div id="button-div">
				<div id="button-div-left" style="float: left;">
					<input type="button" id="replyBtn" class="btn btn-success" value="댓글 보기" />
				</div>
				<div id="button-div-right" style="float: right;">
					<input type="button" id="writeChildBtn" class="btn btn-success" value="하위 목표 만들기" />
					<input type="button" id="modifyBtn" class="btn btn-success" value="목표 업데이트" />
					<input type="button" id="deleteBtn" class="btn btn-warning" value="목표 삭제" />
				</div>
			</div>
    	</div>
  		<div class="col-sm-2">
  			<c:choose>
	  			<c:when test="${not empty childrenGoalList }">
	  				<c:forEach var="childGoal" items="${childrenGoalList }">
			    		<div class="goal-mini">
			    			<div><a href="/WeGo/mygoal/detail/${childGoal.id}">${childGoal.title }</a></div>
			    			<div>${childGoal.modifyDate }</div>
			    			<div>progressbar</div>
			    			<c:forEach var="tag" items="${childGoal.goalVOForMongo.tagList }">
			    				<ul class="tag-ul">
									<li class="dropdown">
										<a class="dropdown-toggle" data-toggle="dropdown" href="#">
											<div class="tag-div">#${tag } </div>
										</a>
										<ul class="dropdown-menu dropdown-menu-tag">
											<li class="dropdown-header">#${tag }</li>
											<li class="divider"></li>
											<li><a href="#">채팅</a></li>
											<li><a class="concernTag" href="#" data-tag="${tag}">관심 태그 추가</a></li>
										</ul>
									</li>
								</ul>
			    			</c:forEach>
			    		</div>
	  				</c:forEach>
	    		</c:when>
				<c:otherwise>
					<div>
    					<span>하위 목표가 없습니다.</span>
    				</div>
				</c:otherwise>  			
  			</c:choose>
  		</div>
  	</div>
</div>

<!-- Modal -->
  <div class="modal fade" id="replyModal" role="dialog">
    <div class="modal-dialog">  
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title text-center">${goalVO.title }</h4>
        </div>
        <div class="modal-body">
        	<div class="reply-div">
        		<!-- Media top -->
				<div class="media">
				  <div class="media-left media-top">
				    <img src="/WeGo/member/profiledownload/${sessionScope._USER_.profileFilename }" class="media-object" style="width:45px">
				  </div>
				  <div class="media-body">
				    <h4 class="media-heading">John Doe <small><i>Posted on February 19, 2016</i></small></h4>
				    <p>Lorem ipsum...</p>
				    <div class="media">
					  <div class="media-left media-top">
					    <img src="/WeGo/member/profiledownload/${sessionScope._USER_.profileFilename }" class="media-object" style="width:45px">
					  </div>
					  <div class="media-body">
					    <h4 class="media-heading">John Doe <small><i>Posted on February 19, 2016</i></small></h4>
					    <p>Lorem ipsum...</p>
					  </div>
					</div>
				  </div>
				</div>
				<div class="media">
				  <div class="media-left media-top">
				    <img src="/WeGo/member/profiledownload/${sessionScope._USER_.profileFilename }" class="media-object" style="width:45px">
				  </div>
				  <div class="media-body">
				    <h4 class="media-heading">John Doe <small><i>Posted on February 19, 2016</i></small></h4>
				    <p>Lorem ipsum...</p>
				  </div>
				</div>
        	</div>
        </div>
        <div class="modal-footer">
        	<textarea name="detail" rows="4" style="width:84%; resize: none;"></textarea>
			<input type="button" class="btn btn-success" data-dismiss="modal" value="댓글 달기">
        </div>
      </div>
    </div>
  </div>

<jsp:include page="/WEB-INF/view/common/layout/layout_footer.jsp" />
