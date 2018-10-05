<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <!-- Modal -->
  <div class="modal fade" id="passwordInitSendModal" role="dialog">
    <div class="modal-dialog">  
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">비밀번호 재설정</h4>
        </div>
        <div class="modal-body">
        	<form id="passwordInitSendForm" action="/WeGo/member/passwordInitSend" method="post">
	        	<div class="form-group email-groups">
					<div class="input-group">
				  		<span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
				  		<input type="email" name="email" placeholder="가입된 이메일 입력" class="form-control emails" />
				  		<span class="email-icons"></span>
				 	</div>
				 	<span class="help-block email-checks"></span>
				</div>
				<div class="form-group">
					<div class="input-group">
				  		<span class="input-group-addon"><i class="glyphicon glyphicon-earphone"></i></span>
				  		<input type="tel" name="tel" placeholder="가입된 전화번호 입력" class="form-control tels"/>
				  		<span class="tel-icons"></span>
					</div>
					<span class="help-block"></span>
				</div>
			</form>
        </div>
        <div class="modal-footer">
			<input type="button" id="passwordInitSendBtn" class="btn btn-block" value="비밀번호 재설정 메일 보내기">
        </div>
      </div>
    </div>
  </div>
  
  