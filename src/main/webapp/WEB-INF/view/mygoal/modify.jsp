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
		
		CKEDITOR.config.enterMode = CKEDITOR.ENTER_BR;
		CKEDITOR.config.height = 280;
		CKEDITOR.config.uiColor = '#90C226';
		CKEDITOR.replace('editor',{
			filebrowserUploadUrl: 'http://localhost:8080/WeGo/mygoal/imageupload'
		});
		
		if ( ${goalVO.isOpen() }) {
			$("#isOpen").attr("checked", "checked");
		}
		
		if ( ${goalVO.isDurablity() }) {
			$("#isDurablity").attr("checked", "checked");
		}
		 
		var index = 1;
		var indexingTag = $(".tag").first();
		
		for ( index ; index < ${goalVO.goalVOForMongo.tagList.size()} ; index++ ) {
			indexingTag = indexingTag.next();
			console.log(indexingTag);
			indexingTag.attr("name", "tagList[" + index + "]");
		}
		 
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
		
		
		$("#submitBtn").click(function() {
			$.post("/WeGo/mygoal/check", $("#modifyForm").serialize(), function(response) {
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
					$("#modifyForm").submit();
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
    		<form:form action="/WeGo/mygoal/modify" method="post" id="modifyForm" class="form-inline">
    		<input type="hidden" name="id" value="${goalVO.id }">
    		<input type="hidden" name="token" value="${sessionScope._CSRF_ }">
    		<input type="hidden" name="parentGoalId" value="${goalVO.parentGoalId }">
			<div id="write">
				<div id="info-div">
					<input type="checkbox" id="isOpen" name="isOpen" checked="checked"/> 공개
					<input type="checkbox" id="isDurablity" name="isDurablity" /> 지속성  
				</div>
				<div id="title-div">
					<input type="text" id="title" name="title" class="form-control" placeholder="제목 입력" style="width: 100%;" value="${goalVO.title }">
				</div>
				<div id="detail-div">
					<textarea name="detail" id="editor" >${goalVO.detail }</textarea>
				</div>
				<div id="hashtags-div">
					<c:forEach var="tag" items="${goalVO.goalVOForMongo.tagList }">
						<input type="text" class="form-control tag" name="tagList[0]" placeholder="Tag" style="width:175px;" value="${tag }"/>
					</c:forEach>
	       			<input type="button" id="append-tag" value="+" />
				</div>
				<div>
					<input type="button" class="btn btn-default" id="submitBtn" value="수정">
				</div>
			</div>
			</form:form>
    	</div>
  		<div class="col-sm-2">
  			<!-- children -->
  		</div>
  	</div>
</div>

<jsp:include page="/WEB-INF/view/common/layout/layout_footer.jsp" />
