<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <!-- Modal -->
  <div class="modal fade" id="loginModal" role="dialog">
    <div class="modal-dialog">  
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Login</h4>
        </div>
        <div class="modal-body">
          <form id="loginForm" method="post" action="/WeGo/memberlogin" >
			<div id="email-group" class="form-group">
				<div class="input-group">
			  	  <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
			  	  <input type="email" name="email" placeholder="이메일 입력" class="form-control"/>
			 	</div>
			</div>
			<div id="password-group" class="form-group">
				<div class="input-group">
			  	  <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
			  	  <input type="password" name="password" placeholder="비밀번호 입력" class="form-control"/>
			 	</div>
			</div>
				<a href="#" data-dismiss="modal" data-toggle="modal" data-target="#registModal"><span class="text-right">WeGo는 처음이신가요?</span></a><br>
				<a href="#" data-dismiss="modal" data-toggle="modal" data-target="#findEmailModal"><span class="text-right">이메일/비밀번호를 잊으셨나요?</span></a>
			</form>
        </div>
        <div class="modal-footer">
			<input type="button" id="loginBtn" class="btn  btn-block" value="로그인" />
        </div>
      </div>
    </div>
  </div>
