<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/view/common/layout/layout_header.jsp" />

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
			            toolbar: [ 'imageTextAlternative', '|', 'imageStyle:alignLeft', 'imageStyle:full', 'imageStyle:alignRight' ],

			            styles: [
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

<div class="container">
	<div>
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
	</div>
</div>

<jsp:include page="/WEB-INF/view/common/layout/layout_footer.jsp" />
