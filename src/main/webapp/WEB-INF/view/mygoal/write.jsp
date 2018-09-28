<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
<link rel="stylesheet" href="/WeGo/daumeditor/css/editor.css" type="text/css" charset="utf-8" />
<script type="text/javascript" src="/WeGo/daumeditor/js/editor_loader.js" charset="utf-8"></script>
<script type="text/javascript" src="/WeGo/se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<script src="/WeGo/js/jquery-3.3.1.min.js" charset="utf-8" ></script>
<script type="text/javascript">

	$().ready( function() {

/* 		var oEditors = [];
		
		nhn.husky.EZCreator.createInIFrame({
		    oAppRef: oEditors,
		    elPlaceHolder: "ir1",
		    sSkinURI: "/WeGo/se2/SmartEditor2Skin.html",
		    fCreator: "createSEditor2"
		});
		
		// ‘저장’ 버튼을 누르는 등 저장을 위한 액션을 했을 때 submitContents가 호출된다고 가정한다.
		function submitContents(elClickedObj) {
		    // 에디터의 내용이 textarea에 적용된다.
		    oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
		    // 에디터의 내용에 대한 값 검증은 이곳에서
		    // document.getElementById("ir1").value를 이용해서 처리한다.
		    try {
		        elClickedObj.form.submit();
		    } catch(e) {}
		} */
		
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
		<form action="/WeGo/mygoal/write.do" method="post">
			<div id="write">
				<div id="title-div">
					<input type="text" id="title" name="title">
				</div>
				<div id="info-div">
				
				</div>
				<div id="detail-div">
					
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