<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
body {
	text-align: center;
}
nav {
	position: absolute;
	top: 0;
	width: 100%;
}
#wrapper {
	position: relative;
	top: 46px;
	min-width: 1100px;
}
#left, #right {
	width: 200px;
	background-color: #eee;
}
#middle {
	width: 700px;
}
.inline {
	display: inline-block;
	min-height: 1000px;
	top: 0px;
}
</style>
<script type="text/javascript" src="/WeGo/js/ckeditor.js" charset="utf-8"></script>
<script src="/WeGo/js/jquery-3.3.1.min.js" charset="utf-8" ></script>
<script type="text/javascript">

	$().ready( function() {
		
		 ClassicEditor.create(
				 document.querySelector( '#editor' ), {
		              ckfinder: {
		                  uploadUrl: 'http://localhost:8080/WeGo/mygoal/imageupload'
		              }
				 } 
		);
		
		$("#append-tag").click(function() {
		    var tag = $('<input type="text" class="tag" name="tags" placeholder="Tag" style="margin-right:4px;" />');
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
</head>
<body>
<jsp:include page="/WEB-INF/view/common/navigation.jsp" />
<div id="wrapper">
	<div id="left" class="inline">
		<div id="parent">
			<span>parent</span>
		</div>
		<div id="lv1">
			<span>lv1</span>
		</div>
	</div><!--
	--><div id="middle" class="inline">
		<form action="/WeGo/mygoal/write" method="post">
			<div id="write">
				<div id="title-div">
					<input type="text" id="title" name="title">
				</div>
				<div id="info-div">
				
				</div>
				<div id="detail-div">
					<textarea name="content" id="editor"></textarea>
				</div>
				<div id="hashtags-div">
					<input type="text" class="tag" name="tags" placeholder="Tag" />
	       			<input type="button" id="append-tag" value="+" />
				</div>
			</div>
		</form>
	</div><!-- 
	--><div id="right" class="inline">
		<div id="children">
			<span>children</span>
		</div>
	</div>
</div>
</body>
</html>