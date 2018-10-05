$().ready(function () {
	
	var isEmailPattern = false;
	var isEmailDuplicate = true;
	var isPasswordPattern = false;
	var isSamePasswords = false;
	var isNamePattern = false;
	var isTelPattern = false;
	
	function checkRegistBtn() {
		if( isEmailPattern && !isEmailDuplicate && isPasswordPattern 
				&& isSamePasswords && isNamePattern && isTelPattern ) {
			$("#registBtn").attr("class", "btn btn-success btn-block" );
		}
		else {
			$("#registBtn").attr("class", "btn btn-warning btn-block disabled" );
		}
	};
	
	function checkPasswordInitBtn() {
		if( isPasswordPattern && isSamePasswords ) {
			$("#passwordInitBtn").attr("class", "btn btn-success btn-block" );
		}
		else {
			$("#registBtn").attr("class", "btn btn-warning btn-block disabled" );
		}
	}
	
	$("#regidtModal").find("input").blur(function() {
		checkRegistBtn();
	});
	
	$("#passwordInitBtn").find("input").blur(function() {
		checkPasswordInitBtn();
	});
	
	$(".emails").blur(function() {
		var group = $(this).closest(".form-group");
		var id = $(this).attr("id");
		var help = group.children(".help-block");
		var icon = group.find(".email-icons");
		$.post("/WeGo/member/check/email/registed"
				, {
					"email": $(this).val()			
				}
				, function(response) {
					if ( id === "email" ) {
						if ( response.registed ) {
							group.attr("class","form-group has-warning has-feedback");
							help.attr("class", "text-warning help-block");
							help.text("중복된 이메일 입니다.");
							icon.attr("class", "glyphicon glyphicon-remove form-control-feedback email-icons");
							isEmailDuplicate = true;						
						}
						else if ( !isEmailPattern ){
							group.attr("class","form-group has-warning has-feedback");
							help.attr("class", "text-warning help-block");
							help.text("올바른 이메일 형식이 아닙니다.");
							icon.attr("class", "glyphicon glyphicon-remove form-control-feedback email-icons");
						}
						else {
							group.attr("class","form-group has-success has-feedback");
							help.attr("class", "text-success help-block");
							help.text("사용가능한 이메일 입니다.");
							icon.attr("class", "glyphicon glyphicon-ok form-control-feedback email-icons");
							isEmailDuplicate = false;
						}
					}
					else {
						if ( response.registed ) {
							group.attr("class","form-group has-success has-feedback");
							help.attr("class", "text-success help-block");
							help.text("등록된 이메일 입니다.");
							icon.attr("class", "glyphicon glyphicon-ok form-control-feedback email-icons");
							isEmailDuplicate = true;						
						}
						else if ( !isEmailPattern ){
							group.attr("class","form-group has-warning has-feedback");
							help.attr("class", "text-warning help-block");
							help.text("올바른 이메일 형식이 아닙니다.");
							icon.attr("class", "glyphicon glyphicon-remove form-control-feedback email-icons");
						}
						else {
							group.attr("class","form-group has-warning has-feedback");
							help.attr("class", "text-warning help-block");
							help.text("등록이 되어있지 않은 이메일 입니다.");
							icon.attr("class", "glyphicon glyphicon-remove form-control-feedback email-icons");
							isEmailDuplicate = false;
						}
					}
				});
	})
	
	$(".emails").keyup(function() {
		var group = $(this).closest(".form-group");
		var id = $(this).attr("id");
		var help = group.children(".help-block");
		var icon = group.find(".email-icons");
		$.post("/WeGo/member/check/email/pattern"
				, {
					"email": $(this).val()
				}
				, function(response) {
					if ( response.available ) {
						group.attr("class","form-group has-success has-feedback");
						help.attr("class", "text-success help-block");
						help.text("올바른 이메일 형식입니다.");
						icon.attr("class", "glyphicon glyphicon-ok form-control-feedback email-icons");
						isEmailPattern = true;						
					}
					else {
						group.attr("class","form-group has-warning has-feedback");
						help.attr("class", "text-warning help-block");
						help.text("올바른 이메일 형식이 아닙니다.");
						icon.attr("class", "glyphicon glyphicon-remove form-control-feedback email-icons");
						isEmailPattern = false;	
					}
				});
		checkRegistBtn();
	});
	
	$(".passwords").keyup(function () {
		var group = $(this).closest(".form-group");
		var form = group.closest("form");
		var help = group.children(".help-block");
		var icon = group.find(".password-icons");
		form.children(".password2-groups").show();
		form.find(".password2s").keyup();
		$.post("/WeGo/member/check/password"
				, {
					"password": $(this).val()
				}
				, function(response) {
					if ( response.available ) {
						group.attr("class","form-group has-success has-feedback");
						help.attr("class", "text-success help-block");
						help.text("사용가능한 패스워드 입니다.");
						icon.attr("class", "glyphicon glyphicon-ok form-control-feedback password-icons");
						isPasswordPattern = true;
					}
					else {
						group.attr("class","form-group has-warning has-feedback");
						help.attr("class", "text-warning help-block");
						help.text("패스워드는 영문,숫자,특수문자 조합으로 8자이상으로 만들어주세요.");
						icon.attr("class", "glyphicon glyphicon-remove form-control-feedback password-icons");
						isPasswordPattern = false;
					}
				});
		checkRegistBtn();
		checkPasswordInitBtn();
	});
	
	$(".password2s").keyup(function () {
		var group = $(this).closest(".form-group");
		var help = group.children(".help-block");
		var icon = group.find(".password2-icons");
		var password = group.closest("form").find(".passwords");
		if ( password.val() != $(this).val() ) {
			group.attr("class","form-group has-warning has-feedback");
			help.attr("class", "text-warning help-block");
			help.text("비밀번호가 일치하지 않습니다.");
			icon.attr("class", "glyphicon glyphicon-remove form-control-feedback password2-icons");
			isSamePasswords = false;
		}
		else {
			group.attr("class","form-group has-success has-feedback");
			help.attr("class", "text-success help-block");
			help.text("비밀번호가 일치합니다.");
			icon.attr("class", "glyphicon glyphicon-ok form-control-feedback password2-icons");
			isSamePasswords = true;
		};
		checkRegistBtn();
		checkPasswordInitBtn();
	});
	
	$("#name").keyup(function () {
		$.post("/WeGo/member/check/name"
				, {
					"name": $(this).val()
				}
				, function(response) {
					if ( response.available ) {
						$("#name-group").attr("class","form-group has-success has-feedback");
						$("#name-check").attr("class", "text-success help-block");
						$("#name-check").text("사용가능한 이름 입니다.");
						$("#name-icon").attr("class", "glyphicon glyphicon-ok form-control-feedback");
						isNamePattern = true;
					}
					else {
						$("#name-group").attr("class","form-group has-warning has-feedback");
						$("#name-check").attr("class", "text-warning help-block");
						$("#name-check").text("이름은 한글, 영어, 숫자만 허용됩니다.(2-18자)");
						$("#name-icon").attr("class", "glyphicon glyphicon-remove form-control-feedback");
						isNamePattern = false;
					}
				});
		checkRegistBtn();
	});
	
	$(".tels").keyup(function () {
		var group = $(this).closest(".form-group");
		var help = group.children(".help-block");
		var icon = group.find(".tel-icons");
		$.post("/WeGo/member/check/tel"
				, {
					"tel": $(this).val()
				}
				, function(response) {
					if ( response.available ) {
						group.attr("class","form-group has-success has-feedback");
						help.attr("class", "text-success help-block");
						help.text("올바른 전화번호 입니다.");
						icon.attr("class", "glyphicon glyphicon-ok form-control-feedback tel-icons");
						isTelPattern = true;
					}
					else {
						group.attr("class","form-group has-warning has-feedback");
						help.attr("class", "text-warning help-block");
						help.text("전화번호는 숫자만 입력해주세요.(9-11자)");
						icon.attr("class", "glyphicon glyphicon-remove form-control-feedback tel-icons");
						isTelPattern = false;
					}
				});
		checkRegistBtn();
	});
	
	$("#registBtn").click(function () {
 		if ( !isEmailPattern ) {
			alert("올바른 이메일 형식이 아닙니다.");
			$("#email").focus();
		}
		else if ( isEmailDuplicate ) {
			alert("중복된 이메일 입니다.");
			$("#email").focus();
		}
		else if ( !isPasswordPattern ) {
			alert("패스워드는 영문,숫자,특수문자 조합으로 8자이상으로 만들어주세요.");
			$("#password").focus();
		}
		else if ( !isSamePasswords ) {
			alert("비밀번호가 일치하지 않습니다.");
			$("#password2").focus();
		}
		else if ( !isNamePattern ) {
			alert("이름은 한글, 영어, 숫자만 허용됩니다.(2-18자)");
			$("#name").focus();
		}
		else if ( !isTelPattern ) {
			alert("전화번호는 숫자만 입력해주세요.(9-11자)");
			$("#tel").focus();
		}
		else { 
			$("#registForm").submit();
	 	} 
	});
	
	$("#loginBtn").click(function() {
		$("#loginForm").submit();
	});
	
	$("#emailAuthBtn").click(function() {
		$("#emailAuthForm").submit();
	});
	
	$("#findEmailBtn").click(function() {
		$.post("/WeGo/member/findEmail"
				, {
					"name": $("#findEmail-name").val()
					, "tel": $("#findEmail-tel").val()
				}
				, function(response) {
					if ( response.findEmailVO === null ) {
						$("#findEmailResult").text("해당 조건에 맞는 이메일이 없습니다.");
						$("#findEmailResult").closest("div").attr("class", "alert alert-warning");
						$("#closeBtn").val("회원가입하러 가기");
						$("#closeBtn").attr("data-toggle", "modal");
						$("#closeBtn").attr("data-target", "#registModal");
					}
					else {
						$("#findEmailResult").html("찾으시는 이메일은 <strong>[" + response.findEmailVO.email + "]</strong> 입니다.");
						$("#findEmailResult").closest("div").attr("class", "alert alert-success");
						$("#closeBtn").val("로그인하러 가기");
						$("#closeBtn").attr("data-toggle", "modal");
						$("#closeBtn").attr("data-target", "#loginModal");
					}
				});
	});
	
	$("#passwordInitSendBtn").click(function() {
		if ( !isEmailPattern ) {
			alert("올바른 이메일 형식이 아닙니다.");
		}
		else if ( !isEmailDuplicate ) {
			alert("등록이 되어있지 않은 이메일 입니다.");
		}
		else if ( !isTelPattern ) {
			alert("전화번호는 숫자만 입력해주세요.(9-11자)");
		}
		else { 
			$("#passwordInitSendForm").submit();
	 	} 
	});
	
	$("#passwordInitBtn").click(function() {
		if ( !isPasswordPattern ) {
			alert("패스워드는 영문,숫자,특수문자 조합으로 8자이상으로 만들어주세요.");
		}
		else if ( !isSamePasswords ) {
			alert("비밀번호가 일치하지 않습니다.");
		}
		else {
			$("#passwordInitForm").submit();
		}
	});
	
/*	if ( '${not empty memberVO}' ) {
		$("#email").keyup();
		$("#email").blur();
		$("#name").keyup();
		$("#tel").keyup();
		$("#registModal").modal();
	}
	else {
		$("#registModal").on('shown.bs.modal', function () {
			isEmailPattern = false;
			isEmailDuplicate = true;
			isPasswordPattern = false;
			isSamePasswords = false;
			isNamePattern = false;
			isTelPattern = false;
		});
	}
	
	if ( '${not empty emailAuthVO}' ) {
		$("#emailAuthModal").modal();
	}
	
	if ( '${not empty emailAuthVOForPasswordInit}' ) {
		$("#passwordInitModal").modal();
	}*/
	
});