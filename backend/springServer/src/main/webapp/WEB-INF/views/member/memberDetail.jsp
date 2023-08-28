<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="회원정보" name="title"/>
</jsp:include>
<style>
div#update-container{width:400px; margin:0 auto; text-align:center;}
div#update-container input, div#update-container select {margin-bottom:10px;}
</style>
<div id="update-container" style="height: 75vh;" class="mt-5">
	<%-- principal을 변수 loginMember 저장 --%>
	<sec:authentication property="principal" var="loginMember"/>

	<form name="memberUpdateFrm">
		<h1 class="mt-4 mb-4">User Info</h1>
		<input type="text" class="form-control" placeholder="아이디 (4글자이상)" name="username" id="username" value='${loginMember.username}'  disabled readonly required/>
		<button type="button" class="form-control btn btn-outline-primary mb-2" id="showModalLink"><span>비밀번호 재설정</span></button>
		<input type="text" class="form-control" placeholder="NAME" name="name" id="name" value='${loginMember.name}' disabled readonly required/>
		<%--<input type="text" class="form-control" placeholder="별명" name="nickname" id="nickname" value='${loginMember.nickname}' required/>--%>
		<div class="form-group">
			<input type="text" class="form-control" id="nickname" name="nickname" placeholder="Nickname" value='${loginMember.nickname}' required/>
			<div class="valid-feedback" id="nicknameValid" >사용가능한 별명입니다.</div>
			<div class="invalid-feedback" id="nicknameInvalid">별명은 2~6글자의 한글 또는 영문자로 입력해주세요.</div>
			<div class="invalid-feedback" id="nicknameChkInvalid">이미 존재하는 별명입니다.</div>
		</div>
		<div class="form-group">
			<input type="text" class="form-control" id="phone" name="phone" placeholder="Phone" value='${loginMember.phone}'/>
			<div class="valid-feedback" id="phoneValid" >사용가능한 번호입니다.</div>
			<div class="invalid-feedback" id="phoneInvalid">'-'제외하고 숫자 11자리로 입력해주세요</div>
			<div class="invalid-feedback" id="phoneChkInvalid">이미 존재하는 번호입니다. 다시 확인해주세요.</div>
		</div>
<%--		<input type="email" class="form-control" placeholder="이메일" name="email" id="email" value='${loginMember.email}' required/>--%>
		<div class="form-group email-form">
			<div class="d-flex flex-row">
				<input type="text" class="form-control" id="email" name="email" style="width:250px; margin-right:5px;" placeholder="Email" value='${loginMember.email}' required/>
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
		</div>
		<input type="date" class="form-control" placeholder="생일" name="birthday" id="birthday" value='<sec:authentication property="principal.birthday"/>' required/>
		<br />
		<input type="submit" class="btn btn-outline-primary" value="수정" >&nbsp;
		<input type="reset" class="btn btn-outline-primary" value="취소" id="cancelButton">
	</form>
</div>
<div class="d-flex justify-content-end m-3">
	<
	<button type="button" class="btn btn-outline-danger" onclick="deleteMember()">회원탈퇴</button>
</div>

<%-- 비밀번호 찾기 모달 --%>
<div class="modal" id="resetPwdModal">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Reset PassWord</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" id="closeButton">
					<span aria-hidden="true"></span>
				</button>
			</div>
			<div id="modalContent2">
				<div class="column">
					<div class="col-md-12">
						<div class="form-group">
							<label class="form-label mt-4" for="password2">비밀번호</label>
							<input type="password" value="" class="form-control" id="password2" name="password" placeholder="6~12글자의 영소문자+ 숫자 + 특수문자(!,@,#)포함">
							<div class="valid-feedback" id="pwdValid">입력하신 비밀번호는 사용 가능합니다.</div>
							<div class="invalid-feedback" id="pwdInvalid">비밀번호는 6~12자, 1개 이상의 숫자와 특수문자 ! @ # 중 하나를 포함해야합니다.</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<label class="form-label mt-4" for="pwdConfirmation2">비밀번호 확인</label>
							<input type="password" value="" class="form-control" id="pwdConfirmation2">
							<div class="valid-feedback" id="pwdCfmValid">비밀번호 확인 되었습니다.</div>
							<div class="invalid-feedback" id="pwdCfmInvalid">비밀번호가 일치하지 않습니다.</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" onclick="resetPassword()">Reset Password</button>
					<button type="button" class="btn btn-secondary close-modal" id="close2"  data-bs-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
</div>


<%-- 비밀번호 찾기 모달 끝--%>

<script>
document.memberUpdateFrm.onsubmit = (e) =>{
	e.preventDefault();

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

	if(!$("#birthday").val()){
		alert("생년월일을 입력해주세요");
		return false;
	}



	const frmData = {
		nickname : document.querySelector("#nickname").value,
		phone : document.querySelector("#phone").value,
		email : document.querySelector("#email").value,
		birthday : document.querySelector("#birthday").value
	}
	const jsonData = JSON.stringify(frmData);
	console.log(frmData);
	$.ajax({
		url : "${pageContext.request.contextPath}/member/memberUpdate.do",
		data : jsonData,
		contentType : "application/json",
		beforeSend: function(xhr) {
			xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
		},
		type : "POST",
		dataType : "json",
		success(responseData){
			console.log(responseData);
			const{ msg, member} = responseData;
			alert(msg);

			$("#nickname").val(member.nickname);
			$("#phone").val(member.phone);
			$("#email").val(member.email);
			$("#birthday").val(member.birthday);

			$("#nickname").removeClass("is-invalid is-valid");
			$("#phone").removeClass("is-invalid is-valid");
			$("#email").removeClass("is-invalid is-valid");
			$("#birthday").removeClass("is-invalid is-valid");
			$("#pwdInvalid").hide();
			$("#pwdValid").hide();
			$("#pwdCfmInvalid").hide();
			$("#pwdCfmValid").hide();

		},
		complete(){
			location.reload();
		}
	});
	// 모든 유효성 검사 통과시에만 폼 제출
	return true;

}

$(document).ready(function() {
	// ...

	// 취소 버튼 클릭 시 폼 초기화 및 이전 정보로 복원
	$("#cancelButton").click(function() {
		document.memberUpdateFrm.reset();

		// 이전에 업데이트되었던 정보로 폼을 초기화하도록 처리
		$("#nickname").val("${loginMember.nickname}");
		$("#phone").val("${loginMember.phone}");
		$("#email").val("${loginMember.email}");
		$("#birthday").val("${loginMember.birthday}");

		// 각 입력 요소의 유효성 클래스 및 메시지 초기화
		$("#nickname").removeClass("is-invalid is-valid");
		$("#phone").removeClass("is-invalid is-valid");
		$("#email").removeClass("is-invalid is-valid");
		$("#birthday").removeClass("is-invalid is-valid");
		$("#pwdInvalid").hide();
		$("#pwdValid").hide();
		$("#pwdCfmInvalid").hide();
		$("#pwdCfmValid").hide();
		$("#nicknameValid").hide();
		$("#nicknameInvalid").hide();
		$("#nicknameChkInvalid").hide();
		$("#phoneValid").hide();
		$("#phoneInvalid").hide();
		$("#phoneChkInvalid").hide();
		$("#mailAuth").val("");
		$("#mailValid").hide();
		$("#mailInvalid").hide();
		$("#email").attr("readonly", false);
		$("#emailDomain").attr("readonly", false);
		$("#emailDomain")[0].selectedIndex = 0;
		$("#mail-Check-Btn").attr("disabled", false);
	});

	// ...
});













</script>

	<%--비밀번호 재설정 관련 JS--%>
	<script>
		document.addEventListener("DOMContentLoaded", function() {
		// openModalLink2 버튼 클릭 이벤트 처리
		const showModalLink = document.getElementById("showModalLink");
		const resetPwdModal = document.getElementById("resetPwdModal");
		/*const resetPwdModalContent1 = resetPwdModal.querySelector("#modalContent1");*/
		const resetPwdModalContent2 = resetPwdModal.querySelector("#modalContent2");

		showModalLink.addEventListener("click", function(event) {
		event.preventDefault(); // 기본 동작(링크 이동) 막기
		resetPwdModal.style.display = "block"; // 모달 보이기
		resetPwdModal.classList.add("show"); // 모달 클래스 추가 (부트스트랩 모달 스타일을 위해)

		// 초기화: modalContent1만 보이게 하고 next 버튼 비활성화
		//resetPwdModalContent1.style.display = "block";
		resetPwdModalContent2.style.display = "block";
		//document.querySelector("#next-btn").disabled = true;
	});

		// 비밀번호 재설정 모달 창 닫기
		const closeButton = resetPwdModal.querySelector("#closeButton");
		closeButton.addEventListener("click", function() {
		resetPwdModal.style.display = "none";
		resetPwdModal.classList.remove("show");
	});



	});


		$(document).ready(function () {

			$("#close2").click(function (event) {
			event.stopPropagation(); // 이벤트 전파 중지
			// 모달을 닫는 함수 호출
			$("#password2").val("");
			$("#pwdConfirmation2").val("");
			closeModal();
			});
		});

		// 모달을 닫는 함수
		function closeModal() {
		const resetPwdModal = document.getElementById("resetPwdModal");
		resetPwdModal.style.display = "none";
		resetPwdModal.classList.remove("show");
		}

		// 모달을 닫는 함수
		function closeModal() {
		const resetPwdModal = document.getElementById("resetPwdModal");
		resetPwdModal.style.display = "none";
		resetPwdModal.classList.remove("show");
		}

		// 비밀번호 재설정 버튼 클릭 시 Ajax 요청 수행
		function resetPassword() {
		const newPassword = document.getElementById("password2").value;
		const username = document.querySelector("#username").value;
		console.log(username);
		console.log(newPassword);
		// 여기서 Ajax 요청을 수행하고 서버에 newPassword 전송 등의 동작을 추가
		// 예시: jQuery를 사용한 Ajax 요청
		$.ajax({
		url: "${pageContext.request.contextPath}/member/resetPassword2.do", // 요청을 처리할 서버 URL
		method: "POST",         // 요청 메서드
		dataType : "json",
		data: { password : newPassword, username : username }, // 전송할 데이터
		beforeSend: function(xhr) {
		xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}')},
		success(responseData) {
		console.log(responseData);
		// 성공적으로 비밀번호가 재설정된 경우의 동작
		alert("비밀번호가 성공적으로 재설정되었습니다.");
		closeModal(); // 모달 닫기
	},
		error(error){
		// 오류 발생 시의 동작
		alert("비밀번호 재설정에 실패했습니다. 다시 시도해주세요.");
	}
	});
	}
</script>

<%--비밀번호 유효성 검사 --%>
<script>
	const $pwdInvalid = $("#pwdInvalid");
	const $pwdValid = $("#pwdValid");
	const $pwdCfmValid = $("#pwdCfmValid");
	const $pwdCfmInvalid = $("#pwdCfmInvalid");
	const $password = $("#password2");
	const $pwdConfirmation =$("#pwdConfirmation2");

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

</script>

<script>
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
			url : "${pageContext.request.contextPath}/member/checkNicknameDuplicate2.do",
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
			url : "${pageContext.request.contextPath}/member/checkPhoneDuplicate2.do",
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
		console.log('완성된 이메일 : ' + email); // 이메일 오는지 확인DM
		const checkInput = $('.mail-check-input') // 인증번호 입력하는곳

		$.ajax({

			url : "${pageContext.request.contextPath}/member/checkEmail2.do",
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

	/*document.memberUpdateFrm.onsubmit = (e) => {

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

		if(!$("#birthday").val()){
			alert("생년월일을 입력해주세요");
			return false;
		}

		// 모든 유효성 검사 통과시에만 폼 제출
		return true;

	};*/

</script>
<script>








</script>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
