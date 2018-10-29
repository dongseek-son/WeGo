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
.tag {
	width: 160px;
}
</style>
<script type="text/javascript" src="/WeGo/js/ckeditor/ckeditor.js" charset="utf-8"></script>
<script type="text/javascript" src="/WeGo/js/ckeditor/lang/ko.js" charset="utf-8"></script>
<script type="text/javascript">

	$().ready( function() {
		
		if ( ${noGoal} ) {
			$("#noGoalModal").modal();
		}
		
		CKEDITOR.config.enterMode = CKEDITOR.ENTER_BR;
		CKEDITOR.config.height = 280;
		CKEDITOR.config.uiColor = '#90C226';
		CKEDITOR.replace('editor',{
			filebrowserUploadUrl: 'http://localhost:8080/WeGo/mygoal/imageupload'
		});
		
		
		appendParentsTag( function() {
			$("#title").focus();
			console.log("끝");
		});
		
		function appendParentsTag( cb = function(){} ) {
			
			var size = 0;
			
			if ( "${parentGoal.id}" ) {
				for (var i = 0;  i < parseInt("${parentGoal.goalVOForMongo.tagList.size() }"); i++) {
					readParentsTag("${parentGoal.id}", i, function() {
						$("#append-tag").click();	
					});
				}
			}
			cb();
		}
		
		function readParentsTag(id, i, cb = function(){} ) {
			console.log(id + " / " + i);
			$.post("/WeGo/mygoal/getTag", {
				parentId : id
				, index : i
			}
			, function(response) {
				if ( response.isSuccess ) {
					console.log(response.tag);
					$(".tag").last().val(response.tag);
					cb();
				}
			});
		}
		 
		 var index = 1;
		 
		$("#append-tag").click(function() {
			var name = "tagList[" + index + "]";
		    var tag = $('<input type="text" class="form-control tag" name="'+ name +'" placeholder="Tag" style="margin-right:4px; width:175px;" />');
		    index = index+1;
		    $(this).before( tag );
		    tag.focus();
		  });
		
		$("#hashtags-div").on("keydown", ".tag", function(e) {
		    if ( e.key == "," ) {
		      if ( $(this).val() != "" ) {
		          $("#append-tag").click();
		      }
		      return false;
		    }
		 });
		
		function checkTagPattern() {
			
		}
		
		$("#submitBtn").click(function() {
			$.post("/WeGo/mygoal/check", $("#writeForm").serialize(), function(response) {
				console.log(response.isTitleOK);
				console.log(response.isTagListEmpty);
				console.log(response.isTagListOK);
				if ( !response.isTitleOK ) {
					alert("제목은 4~20자로 입력해주세요.")
				}
				else if ( response.isTagListEmpty ) {
					alert("tag는 하나 이상 입력해주세요.")
				}
				else if ( !response.isTagListOK ) {
					alert("tag는 공백없이 3~10자로 입력해주세요.")
				}
				else {
					console.log("success");
					$("#writeForm").submit();
				}
			});
		});
		
	});
</script>

<div class="container">
	<div class="row">
    	<div class="col-sm-2">
    		<%-- <div class="goal-mini">
    			<div>${parentGoal.title }</div>
    			<div>${parentGoal.modifyDate }</div>
    			<div>progressbar</div>
    			<c:forEach var="tag" items="${parentGoal.goalVOForMongo.tagList }">
    				<div class="tag-div">#${tag } </div>
    			</c:forEach>
    		</div>
    		<div>lv1</div> --%>
    	</div>
    	<div class="col-sm-8">
    		<form:form action="/WeGo/mygoal/write" method="post" id="writeForm" class="form-inline">
    		<input type="hidden" name="token" value="${sessionScope._CSRF_ }">
    		<input type="hidden" name="parentGoalId" value="${parentGoal.id }">
			<div id="write">
				<div id="info-div">
					<input type="checkbox" id="isOpen" name="isOpen" checked="checked" /> 공개
					<input type="checkbox" id="isDurablity" name="isDurablity" /> 지속성  
				</div>
				<div id="title-div">
					<input type="text" id="title" name="title" class="form-control" placeholder="제목 입력" style="width: 100%;">
				</div>
				<div id="detail-div">
					<textarea name="detail" id="editor" ></textarea>
				</div>
				<div id="hashtags-div">
					<input type="text" class="form-control tag" name="tagList[0]" placeholder="Tag" style="width:175px;"/>
	       			<input type="button" id="append-tag" value="+" />
				</div>
				<div>
					<input type="button" class="btn btn-default" id="submitBtn" value="등록">
				</div>
			</div>
			</form:form>
    	</div>
  		<div class="col-sm-2">
  			<!-- children -->
  		</div>
  	</div>
</div>

<!-- Modal -->
  <div class="modal fade" id="noGoalModal" role="dialog">
    <div class="modal-dialog">  
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title text-center">WeGo에 오신 것을 환영합니다.</h4>
        </div>
        <div class="modal-body text-center">
        	<span>
        		안녕하세요. ${sessionScope._USER_.name } 님.<br>
        		WeGo에 오신 것을 환영합니다.<br><br>
        		아직 등록하신 목표가 없으시군요.<br><br>
        		새로운 목표를 등록하여<br> WeGo와 함께 원하시는 목표를 꼭 성취하시길 바랍니다.<br> 
        	</span>
        </div>
        <div class="modal-footer">
			<input type="button" id="noGoalBtn" class="btn btn-block btn-success" data-dismiss="modal" value="목표 등록하러 가기">
        </div>
      </div>
    </div>
  </div>

<jsp:include page="/WEB-INF/view/common/layout/layout_footer.jsp" />
