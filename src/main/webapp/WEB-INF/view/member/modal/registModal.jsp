<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<!-- Modal -->
  <div class="modal fade" id="registModal" role="dialog">
    <div class="modal-dialog"> 
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Join</h4>
        </div>
        <div class="modal-body">
          <form:form id="registForm" method="post" action="/WeGo/member/regist" enctype="multipart/form-data" >
			<div id="email-group" class="form-group email-groups">
				<div class="input-group">
			  	  <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
			  	  <input type="email" id="email" name="email" placeholder="이메일 입력" class="form-control emails" value="${memberVO.email }"/>
			  	  <span id="email-icon" class="email-icons"></span>
			 	</div>
			 	<span id="email-check" class="help-block email-checks"></span>
			</div>
			<div id="password-group" class="form-group">
				<div class="input-group">
			  	  <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
			  	  <input type="password" id="password" name="password" placeholder="비밀번호 입력" class="form-control passwords"/>
			  	  <span id="password-icon" class="password-icons"></span>
			 	</div>
			 	<span id="password-check" class="help-block"></span>
			</div>
			<div id="password2-group" class="form-group password2-groups" style="display:none">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
					<input type="password" id="password2" name="password2" placeholder="비밀번호 재입력" class="form-control password2s"/>
					<span id="password2-icon" class="password2-icons"></span>
				</div>
				<span id="password2-check" class="help-block"></span>
			</div>
			<div id="name-group" class="form-group">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
					<input type="text" id="name" name="name" placeholder="이름 입력" class="form-control" value="${memberVO.name }"/>
					<span id="name-icon"></span>
				</div>
				<span id="name-check" class="help-block"></span>
			</div>
			<div id="tel-group" class="form-group">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-earphone"></i></span>
					<input type="tel" id="tel" name="tel" placeholder="전화번호 입력" class="form-control tels" value="${memberVO.tel }"/>
					<span id="tel-icon" class="tel-icons"></span>
				</div>
				<span id="tel-check" class="help-block"></span>
			</div>
			<div id="profile-group" class="form-group">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-camera"></i></span>
					<input type="file" name="profileFile" placeholder="프로필 사진 선택" class="form-control"/>
					<span id="profile-icon"></span>
				</div>
				<span id="profile-check" class="help-block">사진을 선택하지 않을시, 기본 이미지로 대체됩니다.</span>
			</div>
		</form:form>
        </div>
        <div class="modal-footer">
				<input type="button" id="registBtn" class="btn btn-warning btn-block disabled" value="회원 가입" />
        </div>
      </div> 
    </div>
  </div>
  