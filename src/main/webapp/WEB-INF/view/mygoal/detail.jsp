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
		
		var isRecommendEmail = ${isRecommendEmail};
		var recommendNumber = ${recommendNumber};
		
		if ( !${replyCount} ) {
			$("#replyBtn").removeClass("btn-success");
		}
		
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
		
		$("#recommendBtn").click( function() {
			if ( isRecommendEmail ) {
				recommendDown();
			}
			else {
				recommendUp();
			}
		});
		
		function recommendUp() {
			$.post("/WeGo/goal/recommend/up"
					, {
						"goalId" : "${goalVO.id}"
					}
					, function(response) {
						if( response.isSuccess ) {
							isRecommendEmail = true;
							$("#recommendBtn").addClass("btn-success");
							$("#recommendBtn").children(".badge").text(++recommendNumber);
						}
			});
		}
		
		function recommendDown() {
			$.post("/WeGo/goal/recommend/down"
					, {
						"goalId" : "${goalVO.id}"
					}
					, function(response) {
						if( response.isSuccess ) {
							isRecommendEmail = false;
							$("#recommendBtn").removeClass("btn-success");
							$("#recommendBtn").children(".badge").text(--recommendNumber);
						}
			});
		}
		
		var isReplyPattern = false;
		
		function checkReplyPattern(detail, cb = function(){}) {
			$.post("/WeGo/reply/check"
					, {
						"detail" : detail
					}
					, function(response) {
						if ( response.available ) {
							$("#replySubmitBtn").addClass("btn-success");
							isReplyPattern = true;
						}
						else {
							$("#replySubmitBtn").removeClass("btn-success");
							isReplyPattern = false;
							cb();
						}
						console.log(isReplyPattern);
					}
			);
		}
		
		$("#reply-detail").keyup(function() {
			var detail = $(this).val();
			checkReplyPattern(detail, function() {
				if ( detail.length > 1000 ) {
					$("#reply-detail").val(detail.slice(0, 999));
					$("#reply-detail").keyup();
				}
			});
		});
		
		$("#replySubmitBtn").click(function() {
			var detail = $("#reply-detail").val();
			if ( !isReplyPattern ) {
 				if ( detail.length < 10 ) {
 					alert("10자 이상 입력해주세요.");
 				} else if ( detail.length > 1000 ) {
 					alert("1000자 이하로 입력해주세요.")
 				} else {
 					alert("공백으로 시작할 수 없습니다.")
 				}
 				$("#reply-detail").keyup();
			}
			else {
				$.post("/WeGo/reply/write", $("#replyForm").serialize(), function(response) {
					if( response.status ) {
						var id = response.replyVO.id;
						console.log(id);
						var profileFilename = response.replyVO.memberVO.profileFilename;
						console.log(profileFilename);
						var name = response.replyVO.memberVO.name;
						console.log(name);
						var email = response.replyVO.memberVO.email;
						console.log(email);
						var writeDate = response.replyVO.writeDate;
						console.log(writeDate);
						var detail = response.replyVO.detail;
						var media = $(`<div class="media" data-id="` + id + `">
							  			<div class="media-left media-top">
						    				<img src="/WeGo/member/profiledownload/` + profileFilename + `" class="media-object" style="width:45px">
						 				</div>
						  				<div class="media-body">
						    				<h4 class="media-heading" title="` + email + `">` + name + ` <small><i>` + writeDate + `</i></small></h4>
						    				<p>` + detail + `</p>
						  				</div>
									</div>`);
						console.log(media);
						$(".reply-div").append(media);
					}
				});
			}
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
    		<a href="/WeGo/mygoal/explorer">Goal Explorer</a>
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
					<button type="button" id="replyBtn" class="btn btn-success">댓글 보기 <span class="badge">${replyCount }</span></button>
				 	<c:choose>
						<c:when test="${isRecommendEmail }">
							<button type="button" id="recommendBtn" class="btn btn-success">
								좋아요 
								<span class="badge">
									${recommendNumber }
								</span>
							</button>
						</c:when>
						<c:otherwise>
							<button type="button" id="recommendBtn" class="btn">
								좋아요 
								<span class="badge">
									${recommendNumber }
								</span>
							</button>
						</c:otherwise>
					</c:choose>
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
        		<c:forEach var="replyVO" items="${replyVOList }">
	        		<div class="media" data-id="${replyVO.id }">
			  			<div class="media-left media-top">
		    				<img src="/WeGo/member/profiledownload/${replyVO.memberVO.profileFilename }" class="media-object" style="width:45px">
		 				</div>
		  				<div class="media-body">
		    				<h4 class="media-heading" title="` + email + `">${replyVO.memberVO.name } <small><i>${replyVO.writeDate }</i></small></h4>
		    				<p>${replyVO.detail }</p>
							<c:forEach var="childReplyVO" items="${replyVO.childrenReplyVOList}">
			  					<div class="media" data-id="${childReplyVO.id }">
						  			<div class="media-left media-top">
					    				<img src="/WeGo/member/profiledownload/${childReplyVO.memberVO.profileFilename }" class="media-object" style="width:45px">
					 				</div>
					  				<div class="media-body">
					    				<h4 class="media-heading" title="` + email + `">${childReplyVO.memberVO.name } <small><i>${childReplyVO.writeDate }</i></small></h4>
					    				<p>${childReplyVO.detail }</p>
					  				</div>
								</div>
			  				</c:forEach>		  				
		  				</div>
					</div>
        		</c:forEach>
        		<!-- Media top -->
        	</div>
        </div>
        <div class="modal-footer">
        	<form action="/WeGo/reply/write" method="post" id="replyForm">
        		<input type="hidden" name="token" value="${sessionScope._CSRF_ }">
    			<input type="hidden" name="parentReplyId" value="${parentReplyId }">
        		<input type="hidden" name="goalId" value="${goalVO.id }">
        		<textarea name="detail" id="reply-detail" rows="4" style="width:84%; resize: none;"></textarea>
				<input type="button" id="replySubmitBtn" class="btn" data-dismiss="modal" value="댓글 달기">
			</form>
        </div>
      </div>
    </div>
  </div>

<jsp:include page="/WEB-INF/view/common/layout/layout_footer.jsp" />
