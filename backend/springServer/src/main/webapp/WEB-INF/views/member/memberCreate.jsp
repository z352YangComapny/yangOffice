<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="회원등록" name="title"/>
</jsp:include>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/member.css" />

<div id="enroll-container" class="mx-auto text-center mt-5 mb-5">
	<form:form name="memberCreateFrm" action="" method="POST">
		<h1>Sign up</h1>
				<div class="form-group">
					<label class="form-label mt-4" for="username">아이디</label>
					<input type="text" value="" class="form-control" id="username" name="username" placeholder="4~10글자 영문자, 숫자 조합">
					<div class="valid-feedback" id ="usernameValid">사용가능한 아이디입니다.</div>
					<div class="invalid-feedback" id="usernameInvalid">4글자 이상 10글자 이하의 영문자, 숫자 조합으로 작성해주세요.</div>
					<div class="invalid-feedback" id="usernameTakenFeedback">이미 사용 중인 아이디입니다.</div>
				</div>
				<div class="form-group">
					<label class="form-label mt-4" for="password">비밀번호</label>
					<input type="password" value="" class="form-control" id="password" name="password" placeholder="6~12글자의 영소문자+ 숫자 + 특수문자(!,@,#)포함">
					<div class="valid-feedback" id="pwdValid">입력하신 비밀번호는 사용 가능합니다.</div>
					<div class="invalid-feedback" id="pwdInvalid">비밀번호는  6~12자, 1개 이상의 숫자와 특수문자 ! @ # 중 하나를 포함해야합니다.</div>

					<label class="form-label mt-4" for="pwdConfirmation">비밀번호 확인</label>
					<input type="password" value="" class="form-control" id="pwdConfirmation">
					<div class="valid-feedback" id="pwdCfmValid">비밀번호 확인 되었습니다.</div>
					<div class="invalid-feedback" id="pwdCfmInvalid">비밀번호가 일치하지 않습니다.</div>
				</div>

				<div class="form-group">
					<label class="form-label mt-4" for="name">이름</label>
					<input type="text" value="" class="form-control" id="name" name="name">
					<div class="invalid-feedback" id="nameInvalid">이름을 입력해주세요</div>
				</div>

				<div class="form-group">
					<label class="form-label mt-4" for="nickname">별명</label>
					<input type="text" value="" class="form-control" id="nickname" name="nickname">
					<div class="valid-feedback" id="nicknameValid" >사용가능한 별명입니다.</div>
					<div class="invalid-feedback" id="nicknameInvalid">별명은 2~6글자의 한글 또는 영문자로 입력해주세요.</div>
					<div class="invalid-feedback" id="nicknameChkInvalid">이미 존재하는 별명입니다.</div>
				</div>
				<div>
					<label class="form-label mt-4" for="gender">성별</label>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="gender" id="M" value="M" checked/>
						<label class="form-check-label" for="M">🧑 Male</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="gender" id="F" value="F"/>
						<label class="form-check-label" for="F">👩 Female</label>
					</div>
				</div>
				<div class="form-group">
					<label class="form-label mt-4" for="phone">연락처</label>
					<input type="text" value="" class="form-control" id="phone" name="phone">
					<div class="valid-feedback" id="phoneValid" >사용가능한 번호입니다.</div>
					<div class="invalid-feedback" id="phoneInvalid">'-'제외하고 숫자 11자리로 입력해주세요</div>
					<div class="invalid-feedback" id="phoneChkInvalid">이미 존재하는 번호입니다. 다시 확인해주세요.</div>
				</div>
				<div class="form-group email-form">
					<label for="email">이메일</label>
						<div class="d-flex flex-row">
							<input type="text" class="form-control" id="email" name="email" style="width:250px; margin-right:5px;">
							<div style="width:250px;">
								<select class="form-control" name="emailDomain" id="emailDomain" >
									<option>메일주소 선택</option>
									<option>@naver.com</option>
									<option>@daum.net</option>
									<option>@gmail.com</option>
									<option>@hanmail.com</option>
								</select>
							</div>
						</div>
						<div class="input-group-addon d-flex justify-content-end">
							<button type="button" class="btn btn-primary" id="mail-Check-Btn">이메일 인증</button>
						</div>
						<div class="mail-check-box mt-2">
							<input class="form-control mail-check-input" id="mailAuth" placeholder="인증번호 6자리를 입력해주세요!" disabled="disabled" maxlength="6">
							<div class="valid-feedback" id="mailValid" >인증번호가 확인되었습니다.</div>
							<div class="invalid-feedback" id="mailInvalid">인증번호가 일치하지 않습니다. 다시 확인해주세요.</div>
						</div>
						<%--<span id="mail-check-warn"></span>--%>
				</div>

				<div class="form-group">
					<label class="form-label mt-4" for="birthday">Birth Day</label>
					<input type="date" value="" class="form-control" id="birthday" name="birthday">
				</div>
			<button class = "btn btn-primary" type="submit" value="가입" style="width: 70px; height :35px;">가입</button>
			<button class = "btn btn-primary" type="reset" value="취소" style="width: 70px; height :35px;">취소</button>
	</form:form>
</div>
<script>
	// 아이디 유효성 검사
	const $validFeedback = $(".valid-feedback");
	const $invalidFeedback = $(".invalid-feedback");
	const $usernameValid = $("#usernameValid");
	const usernameInvalid = document.querySelector("#usernameInvalid");
	const usernameTakenFeedback = document.querySelector("#usernameTakenFeedback");
	const $username = $("#username");

	$username.on("input", function(){

		const username = $(this).val();

		if(!/^[a-zA-Z0-9]{4,10}$/.test(username)){
			$(this).addClass('is-invalid');
			$(this).removeClass('is-valid');
			usernameInvalid.style.display = "block";
			usernameTakenFeedback.style.display= "none";
			if(username.trim() === ''){
				usernameInvalid.style.display = "none";
				usernameTakenFeedback.style.display= "none";
				$(this).removeClass('is-invalid');
			}

		} else{

			$(this).addClass('is-valid');
			$(this).removeClass('is-invalid');
			usernameInvalid.style.display = "none";
			usernameTakenFeedback.style.display= "none";
			$usernameValid.show();

			checkIdDuplicate(username);

		}


	});

	const checkIdDuplicate = (inputUsername) => {
		$.ajax({

			url : "${pageContext.request.contextPath}/member/checkIdDuplicate.do",
			data : {
				username : inputUsername
			},
			method : "GET",
			dataType : "json",
			success(responseData){
				console.log(responseData);
				const {available, chkusername} = responseData;

				if(available === false){
					$username.removeClass("is-valid");
					$username.addClass("is-invalid");
					$usernameValid.hide();
					usernameInvalid.style.display = "none";
					usernameTakenFeedback.style.display= "block";
					if(chkusername !== inputUsername){
						$username.addClass("is-valid");
						$username.removeClass("is-invalid");
						$usernameValid.show();
						usernameInvalid.style.display = "none";
						usernameTakenFeedback.style.display= "none";
					}

				} else {
					$username.addClass("is-valid");
					$username.removeClass("is-invalid");
					$usernameValid.show();
					usernameInvalid.style.display = "none";
					usernameTakenFeedback.style.display= "none";
				}

			}
		});
	};

	// 비밀번호 유효성 검사
	const $pwdInvalid = $("#pwdInvalid");
	const $pwdValid = $("#pwdValid");
	const $pwdCfmValid = $("#pwdCfmValid");
	const $pwdCfmInvalid = $("#pwdCfmInvalid");
	const $password = $("#password");
	const $pwdConfirmation =$("#pwdConfirmation");

	$password.on("input", function(){

		const password = $(this).val();

		if(!/^(?=.*[a-z])(?=.*[0-9])(?=.*[!@#])[a-z0-9!@#]+$/.test(password)){
			$(this).addClass('is-invalid');
			$(this).removeClass('is-valid');
			$pwdInvalid.show();
			$pwdValid.hide();
			$pwdCfmInvalid.hide();

			if(password.trim() === ''){
				$pwdInvalid.hide();
				$pwdValid.hide();
				$(this).removeClass('is-invalid');
			}

		} else{
			$(this).removeClass('is-invalid');
			$(this).addClass('is-valid');
			$pwdInvalid.hide();
			$pwdValid.show();
			$pwdCfmValid.hide();

				$pwdConfirmation.on("input", function () {
					const password = $password.val();
					const pwdConfirmation = $(this).val();

					if (password !== pwdConfirmation) {
						if (pwdConfirmation.trim() === '') {
							$pwdCfmValid.hide();
							$pwdCfmInvalid.hide();
							$(this).removeClass('is-invalid');
							$(this).removeClass('is-valid');
						} else {
							$(this).addClass('is-invalid');
							$(this).removeClass('is-valid');
							$pwdCfmValid.hide();
							$pwdCfmInvalid.show();
						}
					} else {
						$(this).removeClass('is-invalid');
						$(this).addClass('is-valid');
						$pwdCfmValid.show();
						$pwdCfmInvalid.hide();
					}
				});

		}
	});

// 이름 유효성 검사
	const $name = $("#name");
	const $nameInvalid = $("#nameInvalid");

	$name.on("input", function(){

		const name = $name.val();
		if(name.trim()===''){
			$(this).addClass('is-invalid');
			$nameInvalid.show();

			$name.on("blur", function() {
				$(this).removeClass('is-invalid');
				$(this).removeClass('is-valid');
				$nameInvalid.hide();
			});

		} else{
			$(this).addClass('is-valid');
			$(this).removeClass('is-invalid');
			$nameInvalid.hide();

			$name.on("blur", function() {
				$(this).removeClass('is-invalid');
				$(this).addClass('is-valid');
				$nameInvalid.hide();
			});
		}
	});


	//별명 유효성 검사

	const $nickname = $("#nickname");
	const $nicknameValid = $("#nicknameValid");
	const $nicknameInvalid = $("#nicknameInvalid");
	const $nicknameChkInvalid = $("#nicknameChkInvalid");

	$nickname.on("input", function(){
		const nickname = $(this).val();

		if(!/^[a-zA-Z가-힣]{2,6}$/.test(nickname)){

			$(this).addClass('is-invalid');
			$(this).removeClass('is-valid');
			$nicknameInvalid.show();
			$nicknameChkInvalid.hide();

			if(nickname.trim() === ''){
				$nicknameChkInvalid.hide();
				$nicknameInvalid.hide();
				$(this).removeClass('is-invalid');
			}

		} else {

			$(this).addClass('is-valid');
			$(this).removeClass('is-invalid');
			$nicknameValid.show();
			$nicknameInvalid.hide();
			$nicknameChkInvalid.hide();

			checkNicknameDuplicate(nickname);

		}

	});

	const checkNicknameDuplicate = (inputNickname) =>{

		console.log(inputNickname);
		$.ajax({
			url : "${pageContext.request.contextPath}/member/checkNicknameDuplicate.do",
			data : {
				nickname : inputNickname
			},
			method : "GET",
			dataType : "json",
			success(responseData){
				console.log(responseData);
				const {available} =responseData;

				if(available === false){
					$nickname.removeClass('is-valid');
					$nickname.addClass('is-invalid');
					$nicknameValid.hide();
					$nicknameInvalid.hide();
					$nicknameChkInvalid.show();

				} else{
					$nickname.addClass('is-valid');
					$nickname.removeClass('is-invalid');
					$nicknameValid.show();
					$nicknameInvalid.hide();
					$nicknameChkInvalid.hide();

				}

			}

		});


	};

	// 휴대전화 유효성 검사
	const $phone = $("#phone");
	const $phoneValid = $("#phoneValid");
	const $phoneInvalid = $("#phoneInvalid");
	const $phoneChkInvalid = $("#phoneChkInvalid");

	$phone.on("input", function(){

		const phone = $phone.val();
		if(!/^\d{11}$/.test(phone)){

			$phone.addClass('is-invalid');
			$phone.removeClass('is-valid');
			$phoneValid.hide();
			$phoneInvalid.show();
			$phoneChkInvalid.hide();

			if(phone.trim() === ''){
				$phone.removeClass('is-invalid');
				$phone.removeClass('is-valid');
				$phoneValid.hide();
				$phoneInvalid.hide();
				$phoneChkInvalid.hide();
			}

		} else{

			$phone.removeClass('is-invalid');
			$phone.addClass('is-valid');
			$phoneValid.show();
			$phoneInvalid.hide();
			$phoneChkInvalid.hide();

			checkPhoneDuplicate(phone);



		}

	});

	const checkPhoneDuplicate = (inputPhone) =>{
		console.log(inputPhone);

		$.ajax({
			url : "${pageContext.request.contextPath}/member/checkPhoneDuplicate.do",
			data : {
				phone : inputPhone
			},
			method : "GET",
			dataType : "json",
			success(responseData) {
				console.log(responseData);
				const {available} = responseData;

				if(available === false){
					$phone.addClass('is-invalid');
					$phone.removeClass('is-valid');
					$phoneValid.hide();
					$phoneInvalid.hide();
					$phoneChkInvalid.show();
				} else{
					$phone.removeClass('is-invalid');
					$phone.addClass('is-valid');
					$phoneValid.show();
					$phoneInvalid.hide();
					$phoneChkInvalid.hide();

				}
			}
		})
	};

	let code;
	// 이메일 인증
	$('#mail-Check-Btn').click(function() {
		const email = $('#email').val();// 이메일 주소값 얻어오기!
		console.log('완성된 이메일 : ' + email); // 이메일 오는지 확인
		const checkInput = $('.mail-check-input') // 인증번호 입력하는곳

		$.ajax({

			url : "${pageContext.request.contextPath}/member/checkEmail.do", // GET방식이라 Url 뒤에 email을 뭍힐수있다.
			data : {
				email : email
			},
			method : "GET",
			dataType :"json",
			success(responseData){
				console.log(responseData);
				const {emailAuth} = responseData;
				checkInput.attr('disabled',false);
				code = emailAuth;
				alert('인증번호가 전송되었습니다.');
			}
		}); // end ajax
	}); // end send eamil

	//인증번호 비교
	const $mailValid = $("#mailValid");
	const $mailInvalid = $("#mailInvalid");
	const $mailAuth = $("#mailAuth");
	const $emailDomain = $('#emailDomain');
	$mailAuth.blur(function(){
		const inputCode = $(this).val();

		if(inputCode !== code){
			$mailAuth.addClass('is-invalid');
			$mailAuth.removeClass('is-valid');
			$mailValid.hide();
			$mailInvalid.show();
		} else {
			$mailAuth.removeClass('is-invalid');
			$mailAuth.addClass('is-valid');
			$mailValid.show();
			$mailInvalid.hide();
			$('#mail-Check-Btn').attr('disabled',true);
			$('#email').attr('readonly',true);
			$emailDomain.attr('readonly',true);
			$emailDomain.attr('onFocus', 'this.initialSelect = this.selectedIndex');
			$emailDomain.attr('onChange', 'this.selectedIndex = this.initialSelect');

		}

	});

// 이메일에 도메인 합치기
	$(document).ready(function() {
		const $email = $("#email");
		const $emailDomain = $("#emailDomain");

		$emailDomain.on("change", function() {
			const selectedDomain = $(this).val();
			const currentValue = $email.val();

			// 선택한 도메인이 없으면 무시
			if (!selectedDomain) {
				return;
			}

			// 첫 번째 옵션 선택 시, 입력값을 초기화
			if (selectedDomain === "메일주소 선택") {
				$email.val("");
				return;
			}

			// 입력값이 없거나 이미 선택한 도메인이 포함되어 있다면 새로운 값으로 업데이트
			if (!currentValue || currentValue.endsWith(selectedDomain)) {
				$email.val("");
				$email.val(selectedDomain);
			} else {
				// 입력값이 있고, 선택한 도메인이 포함되어 있지 않다면 도메인을 붙여서 업데이트
				if(currentValue.includes("@")){
					$email.val("");
					$email.val(selectedDomain);
				} else{
					$email.val(currentValue + selectedDomain);
				}

			}
		});
	});


	document.memberCreateFrm.onsubmit = (e) => {

	// 아이디 유효성 검사
	if (!/^[a-zA-Z0-9]{4,10}$/.test($("#username").val())) {
		// 오류 처리: 아이디 유효성 검사 실패
		alert("올바른 아이디를 입력해주세요.");
		return false;
	}

	// 비밀번호 유효성 검사
	if (!/^(?=.*[a-z])(?=.*[0-9])(?=.*[!@#])[a-z0-9!@#]+$/.test($("#password").val())) {
		// 오류 처리: 비밀번호 유효성 검사 실패
		alert("올바른 비밀번호를 입력해주세요.");
		return false;
	}

	//비밀번호 일치여부 검사
	if($("#password").val() !== $("#pwdConfirmation").val()){
		alert("비밀번호 확인과 일치하지 않습니다. 확인해주세요.");
		return false;
	}

	// 이름 유효성 검사
	if ($("#name").val().trim() === '') {
		// 오류 처리: 이름 유효성 검사 실패
		alert("입력하신 이름을 확인해주세요.");
		return false;
	}

	// 별명 유효성 검사
	if (!/^[a-zA-Z가-힣]{2,6}$/.test($("#nickname").val())) {
		// 오류 처리: 별명 유효성 검사 실패
		alert("올바른 별명을 입력해주세요.");
		return false;
	}

	// 휴대전화 유효성 검사
	if (!/^\d{11}$/.test($("#phone").val())) {
		// 오류 처리: 휴대전화 유효성 검사 실패
		alert("입력하신 전화번호를 확인해주세요");
		return false;
	}

	if(!$("#email").val()){
		alert("이메일을 입력 후 인증을 완료해주세요.");
		return false;
	}

	if(!$("$birthday").val()){
		alert("생년월일을 입력해주세요");
		return false;
	}

	// 모든 유효성 검사 통과시에만 폼 제출
	return true;

};
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>