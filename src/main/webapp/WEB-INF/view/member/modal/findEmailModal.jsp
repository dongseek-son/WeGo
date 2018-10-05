<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

  <!-- Modal -->
  <div class="modal fade" id="findEmailModal" role="dialog">
    <div class="modal-dialog">  
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">이메일 찾기</h4>
        </div>
        <div class="modal-body">
        	<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
				  	<input type="text" name="name" id="findEmail-name" placeholder="가입된 이름 입력" class="form-control"/>
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
			  		<span class="input-group-addon"><i class="glyphicon glyphicon-earphone"></i></span>
			 		<input type="tel" name="tel" id="findEmail-tel" placeholder="가입된 전화번호 입력" class="form-control tels"/>
			  		<span class="tel-icons"></span>
				</div>
				<span class="help-block"></span>
			</div>
			<div>
				<input type="button" id="findEmailBtn" class="btn  btn-block" value="이메일 찾기">
			</div>
			<div>
				<span id="findEmailResult" style="margin: 1px"></span>
			</div>
			<div>
				<input type="button" id="passwordInitPageBtn" class="btn btn-block" 
					data-dismiss="modal" data-toggle="modal" data-target="#passwordInitSendModal" 
					value="비밀번호 재설정하러 가기">
			</div>
        </div>
        <div class="modal-footer">
			<input type="button" id="closeBtn" class="btn btn-block" data-dismiss="modal" value="닫기">
        </div>
      </div>
    </div>
  </div>
  