<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- Modal -->
  <div class="modal fade" id="emailAuthModal" role="dialog">
    <div class="modal-dialog">  
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">이메일 인증</h4>
        </div>
        <div class="modal-body">
        	<form action="/WeGo/member/emailAuth" method="post" id="emailAuthForm">
				<input type="hidden" name="authUrl" value="${emailAuthVO.authUrl }">
				<input type="hidden" name="email" value="${emailAuthVO.email }">
				<div>
					<span>환영합니다. 이메일 인증을 완료하시려면 아래 버튼을 클릭해주세요.</span>	
				</div>
			</form>
        </div>
        <div class="modal-footer">
			<input type="button" id="emailAuthBtn" class="btn  btn-block" value="이메일 인증완료">
        </div>
      </div>
    </div>
  </div>
  
  