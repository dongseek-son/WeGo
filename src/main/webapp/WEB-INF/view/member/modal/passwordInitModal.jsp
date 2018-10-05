<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

  <!-- Modal -->
  <div class="modal fade" id="passwordInitModal" role="dialog">
    <div class="modal-dialog">  
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">비밀번호 재설정</h4>
        </div>
        <div class="modal-body">
        	<form action="/WeGo/member/passwordInit" method="post" id="passwordInitForm">
				<input type="hidden" name="authUrl" value="${emailAuthVOForPasswordInit.authUrl }">
				<input type="hidden" name="email" value="${emailAuthVOForPasswordInit.email }">
				<div class="form-group">
					<div class="input-group">
			  			<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
			  			<input type="password" name="password" placeholder="비밀번호 입력" class="form-control passwords"/>
			  	  		<span class="password-icons"></span>
			 		</div>
			 		<span class="help-block">재설정 할 비밀번호를 입력해주세요.</span>
				</div>
				<div class="form-group password2-groups" style="display:none">
					<div class="input-group">
						<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
						<input type="password" name="password2" placeholder="비밀번호 재입력" class="form-control password2s"/>
						<span class="password2-icons"></span>
					</div>
				<span class="help-block"></span>
				</div>
			</form>
        </div>
        <div class="modal-footer">
			<input type="button" id="passwordInitBtn" class="btn  btn-block" value="비밀번호 변경">
        </div>
      </div>
    </div>
  </div>