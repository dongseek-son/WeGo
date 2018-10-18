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
.re-reply-active {
	text-decoration: underline;
	font-weight: bold;
	color: red;
}
.reply-detail { 
	text-align: justify;
	word-break: break-all;
}
textarea {
	background-color: whitesmoke;
}
.modal-content {
	width: 680px;
    margin: 30px auto;
}
}
.media-body {
	width: 600px;
}
</style>
<script type="text/javascript">

	$().ready( function() {
		
		var isRecommendEmail = ${isRecommendEmail};
		var recommendNumber = ${recommendNumber};
		var mentionName = null;
		
		if ( !${replyCount} ) {
			$("#replyBtn").removeClass("btn-success");
		}
		
		if ( ${isReplyModalOpen} ) {
			$("#replyModal").modal();
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
			$(this).val().replace(/(\r\n|\r|\n)/g,"<br />");
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
 				} 
 				$("#reply-detail").keyup();
			}
			else {
				if ( mentionName ) {
					$("#reply-detail").val("<strong>" + mentionName + " </strong>" + detail);
				}
				$("#replyForm").submit();
			}
		});
		
		$(".re-reply").click(function() {
			if ( $(this).hasClass("re-reply-active") ) {
				$(this).removeClass("re-reply-active");
				$("#parentReplyId").val(null);
				mentionName = null;
			}
			else {
				$(".reply-div").find(".re-reply").removeClass("re-reply-active");
				$(this).addClass("re-reply-active");
				$("#parentReplyId").val($(this).closest(".lv1-media").data("id"));
				mentionName = $(this).closest(".media-body").find(".reply-name").first().text();
			}
			$("#reply-detail").val("").focus();
			console.log($("#parentReplyId").val());
			console.log(mentionName);
		});
		
		$(".deleteReply").click(function() {
			var replyId = $(this).closest(".media").data("id");
			var result = confirm("삭제하시겠습니까?");
			if ( result ) {
				window.location = "/WeGo/reply/delete/${goalVO.id}/" + replyId;
			}
		});
		
		$("#deleteBtn").click(function() {
			var check = confirm("정말 삭제하시겠습니까? \n * 하위 목표가 있을시 하위 목표까지 모두 삭제됩니다.");
			if ( check ) {
				window.location = "/WeGo/mygoal/delete/${sessionScope._CSRF_}/${goalVO.id}";
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
    		<a href="/WeGo/mygoal/write">새 목표 만들기</a><br>
    		<a href="/WeGo/mygoal/explorer">Goal Explorer</a>
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
      
    </div><div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title text-center">${goalVO.title }</h4>
        </div>
        <div class="modal-body">
        	<div class="reply-div">
        		<c:forEach var="replyVO" items="${replyVOList }">
	        		<div class="media lv1-media" data-id="${replyVO.id }">
	        			<c:choose>
	        				<c:when test="${replyVO.isDelete() }">
        						<div class="media-left media-top">
									<img src="/WeGo/img/white.jpg" class="media-object" style="width:45px">
				 				</div>
				  				<div class="media-body">
				    				<div class="reply-detail">
				    					삭제된 댓글 입니다.
				    				</div>
									<c:forEach var="childReplyVO" items="${replyVO.childrenReplyVOList}">
										<c:choose>
											<c:when test="${childReplyVO.isDelete() }">
												<div class="media" data-id="${childReplyVO.id }">
													<div class="media-left media-top">
														<img src="/WeGo/img/white.jpg" class="media-object" style="width:45px">
									 				</div>
									  				<div class="media-body">
									    				<div class="reply-detail">
									    					삭제된 댓글 입니다.
									    				</div>
								    			</div>
											</div></c:when>
											<c:otherwise>
												<div class="media" data-id="${childReplyVO.id }">
										  			<div class="media-left media-top">
									    				<img src="/WeGo/member/profiledownload/${childReplyVO.memberVO.profileFilename }" class="media-object" style="width:45px">
									 				</div>
									  				<div class="media-body">
									  					<div style="display:inline-block">
									    					<h4 class="media-heading" title="${childReplyVO.memberVO.email }">
									    						<span class="reply-name">${childReplyVO.memberVO.name }</span> 
									    						<small>
									    							<c:if test="${childReplyVO.memberVO.email eq goalVO.email }">
									    								작성자 
									    							</c:if>
									    							<i>${childReplyVO.writeDate }</i>
									    						</small>
									    					</h4>
									    				</div>
									    				<div class="reply-a-div" style="float: right; font-size: 13px;">
									    					<a href="#" class="re-reply">답글달기</a>
									    					<a href="#" class="deleteReply">삭제</a>		    					
									    				</div>
									    				<div class="reply-detail">
									    					${childReplyVO.detail }
									    				</div>	
									  				</div>
												</div>
											</c:otherwise>
										</c:choose>
					  				</c:forEach>		  				
				  				</div>		
	        				</c:when>
	        				<c:otherwise>
	        					<div class="media-left media-top">
				    				<img src="/WeGo/member/profiledownload/${replyVO.memberVO.profileFilename }" class="media-object" style="width:45px">
				 				</div>
				  				<div class="media-body">
				  					<div style="display:inline-block">
				    					<h4 class="media-heading" title="${replyVO.memberVO.email }">
				    						<span class="reply-name">${replyVO.memberVO.name }</span> 
				    						<small>
				    							<c:if test="${replyVO.memberVO.email eq goalVO.email }">
				    								작성자 
				    							</c:if>
				    							<i>${replyVO.writeDate }</i>
				    						</small>
				    					</h4>
				    				</div>
				    				<div class="reply-a-div" style="float: right; font-size: 13px;">
				    					<a href="#" class="re-reply">답글달기</a>
				    					<a href="#" class="deleteReply">삭제</a>		    					
				    				</div>
				    				<div class="reply-detail">
				    					${replyVO.detail }
				    				</div>
									<c:forEach var="childReplyVO" items="${replyVO.childrenReplyVOList}">
										<c:choose>
											<c:when test="${childReplyVO.isDelete() }">
												<div class="media" data-id="${childReplyVO.id }">
													<div class="media-left media-top media-object" style="width:45px">
									 				</div>
									  				<div class="media-body">
									    				<div class="reply-detail">
									    					삭제된 댓글 입니다.
									    				</div>
								    			</div>
											</div>
											</c:when>
											<c:otherwise>
												<div class="media" data-id="${childReplyVO.id }">
										  			<div class="media-left media-top">
									    				<img src="/WeGo/member/profiledownload/${childReplyVO.memberVO.profileFilename }" class="media-object" style="width:45px">
									 				</div>
									  				<div class="media-body">
									  					<div style="display:inline-block">
									    					<h4 class="media-heading" title="${childReplyVO.memberVO.email }">
									    						<span class="reply-name">${childReplyVO.memberVO.name }</span> 
									    						<small>
									    							<c:if test="${childReplyVO.memberVO.email eq goalVO.email }">
									    								작성자 
									    							</c:if>
									    							<i>${childReplyVO.writeDate }</i>
									    						</small>
									    					</h4>
									    				</div>
									    				<div class="reply-a-div" style="float: right; font-size: 13px;">
									    					<a href="#" class="re-reply">답글달기</a>
									    					<a href="#" class="deleteReply">삭제</a>		    					
									    				</div>
									    				<div class="reply-detail">
									    					${childReplyVO.detail }
									    				</div>	
									  				</div>
												</div>
											</c:otherwise>
										</c:choose>					  				
									</c:forEach>		  				
				  				</div>	        					
	        				</c:otherwise>
	        			</c:choose>
					</div>
        		</c:forEach>
        		<!-- Media top -->
        	</div>
        </div>
        <div class="modal-footer">
        	<form action="/WeGo/reply/write" method="post" id="replyForm">
        		<input type="hidden" name="token" value="${sessionScope._CSRF_ }">
    			<input type="hidden" id="parentReplyId" name="parentReplyId" value="${parentReplyId }">
        		<input type="hidden" name="goalId" value="${goalVO.id }">
        		<div style="display: inline-block; width: 550px; vertical-align: middle;">
        			<textarea name="detail" id="reply-detail" rows="4" style="width: 100%; resize: none;"></textarea>
        		</div>
        		<div style="display: inline-block; vertical-align: middle; height: 86px; margin-left: 3px;">
        			<input type="button" id="replySubmitBtn" class="btn" value="댓글 달기" style="height: 80px;">
        		</div>
			</form>
        </div>
      </div>
  </div>

<jsp:include page="/WEB-INF/view/common/layout/layout_footer.jsp" />
