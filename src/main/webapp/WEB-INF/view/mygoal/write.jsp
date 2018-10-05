<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="/WEB-INF/view/common/layout/layout_header.jsp" />
<style type="text/css">
.goal-mini {
	border: 1px solid;
}
</style>
<script type="text/javascript" src="/WeGo/js/ckeditor.js" charset="utf-8"></script>
<script type="text/javascript" src="/WeGo/js/translations/ko.js" charset="utf-8"></script>
<script type="text/javascript">

	$().ready( function() {
		
		 ClassicEditor.create(
				 document.querySelector( '#editor' ), {
					 language: 'ko'
		             , ckfinder: {
		                  uploadUrl: 'http://localhost:8080/WeGo/mygoal/imageupload'
		              }
				 	, image: {
			            // You need to configure the image toolbar, too, so it uses the new style buttons.
			            toolbar: [ 'imageTextAlternative', '|', 'imageStyle:alignLeft', 'imageStyle:full', 'imageStyle:alignRight' ]
			            , styles: [
			                // This option is equal to a situation where no style is applied.
			                'full',
			                // This represents an image aligned to the left.
			                'alignLeft',
			                // This represents an image aligned to the right.
			                'alignRight'
			            ]
			        }
				 } 
		);
		
		 var index = 1;
		 
		$("#append-tag").click(function() {
			var name = "tagList[" + index + "]";
		    var tag = $('<input type="text" class="tag" name="'+ name +'" placeholder="Tag" style="margin-right:4px;" />');
		    index = index+1;
		    $(this).before( tag );
		    tag.focus();
		  });
		
		$(".tags").on("keydown", ".tag", function(e) {
		    if ( e.key == "," ) {
		      if ( $(this).val() != "" ) {
		          $("#append-tag").click();
		      }
		      return false;
		    }
		 });
		
	});
</script>

<div class="container">
	<div class="row">
    	<div class="col-sm-2">
    		<div class="goal-mini">
    			<div>title</div>
    			<div>date</div>
    			<div>progressbar</div>
    			<div>hashtags</div>
    		</div>
    		<div>lv1</div>
    	</div>
    	<div class="col-sm-8">
    		<form:form action="/WeGo/mygoal/write" method="post">
    		<input type="hidden" name="token" value="${sessionScope._CSRF_ }">
			<div id="write">
				<div id="info-div">
					<div>
						<input type="checkbox" id="isOpen" name="isOpen" /> 비공개
					</div>
					<div>
						<input type="checkbox" id="isDurablity" name="isDurablity" /> 지속성  
					</div>
				</div>
				<div id="title-div">
					<input type="text" id="title" name="title" class="form-control" placeholder="제목 입력">
				</div>
				<div id="detail-div">
					<textarea name="detail" id="editor"></textarea>
				</div>
				<div id="hashtags-div">
					<input type="text" class="tags" name="tagList[0]" placeholder="Tag" />
	       			<input type="button" id="append-tag" value="+" />
				</div>
				<div>
					<input type="submit" value="등록">
				</div>
			</div>
			</form:form>
    	</div>
  		<div class="col-sm-2">
  			children
  		</div>
  	</div>
</div>

<jsp:include page="/WEB-INF/view/common/layout/layout_footer.jsp" />
