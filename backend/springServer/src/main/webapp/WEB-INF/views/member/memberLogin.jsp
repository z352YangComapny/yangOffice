<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page ="/WEB-INF/views/common/header.jsp"></jsp:include>
<title>로그인</title>

<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>

<!-- bootstrap js: jquery load 이후에 작성할것.-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap/dist/js/bootstrap.bundle.min.js"></script>

<!-- bootstrap css -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.1/dist/minty/bootstrap.min.css">
<!-- 사용자작성 css -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/style.css"/>

<script>

</script>
</head>
<body>

	<div class="d-flex justify-content-center align-items-center" style = "min-width: 100vw; margin-top : 50px; ">

	<form:form action="${pageContext.request.contextPath}/member/memberLogin.do"
			   method="post">
		<c:if test="${param.error ne null}">
			<div class="toast show" role="alert" aria-live="assertive" aria-atomic="true">
				<div class="toast-header">
					<strong class="me-auto">❗ Confirmation</strong>
					<button type="button" class="btn-close ms-2 mb-1" data-bs-dismiss="toast" aria-label="Close">
						<span aria-hidden="true"></span>
					</button>
				</div>
				<div class="toast-body">
					아이디 또는 비밀번호가 일치하지 않습니다.
					<br>
					다시 확인해주세요.
				</div>
			</div>
		</c:if>
		<fieldset style="color : rgba(120,194,173);">
			<legend style="font-weight: bold; font-size : 50px; text-align: center;">Login</legend>
			<div class="form-group row">
				<div>
					<label for="username" class="col-sm-2 col-form-label">ID</label>
					<div class="form-group col-sm-15">
						<input type="text" value="user1" name = "username"  id="username" placeholder="Input" class="form-control" />
					</div>
				</div>
				<div>
					<label for="password" class="col-sm-2 col-form-label">PASSWORD</label>
					<div class="form-group col-sm-15">
						<input type="password" value="1234" name = "password" placeholder="Input" class="form-control" />
					</div>
				</div>
			</div>
			<div class="d-flex justify-content-center align-items-center" style="margin-top:20px;">
				<button type = "submit" id = "login" class="btn btn-primary" style="border-radius: 50px; width : 20vw;">로그인</button>
			</div>
		</fieldset>
	</form:form>
	</div>
	<div class="d-flex justify-content-center align-items-center" style ="margin-top : 30px;">
		<a href="#" id="openModalLink1" >아이디찾기</a>
		&nbsp;
		|
		&nbsp;
		<a href="#" id="openModalLink2">비밀번호찾기</a>
	</div>
	<div class="sns-icon d-flex justify-content-center align-items-center"  style ="margin-top : 30px;" >
		<a href=""><img src="${pageContext.request.contextPath}/resources/images/apple.png"></a>
		&nbsp;
		<a href=""><img src="${pageContext.request.contextPath}/resources/images/code.png"></a>
		&nbsp;
		<a href=""><img src="${pageContext.request.contextPath}/resources/images/google.png"></a>
		&nbsp;
		<a href="${pageContext.request.contextPath}/oauth/kakao/login.do"><img src="${pageContext.request.contextPath}/resources/images/instagrampng.png" /></a>
		&nbsp;
		<a href=""><img src="${pageContext.request.contextPath}/resources/images/naver.png"></a>
		&nbsp;
		<a href=""><img src="${pageContext.request.contextPath}/resources/images/steam.png"></a>
	</div>

	<div class="d-flex flex-column justify-content-center align-items-center mt-5">
		<p>아직 회원이 아니신가요?</p>
		<button type = "submit" id = "memberCreate" class="btn btn-primary" style="border-radius: 50px; width : 20vw;" onclick="location.href='${pageContext.request.contextPath}/member/memberCreate.do';">
			회원가입</button>
	</div>


	<%--아이디 찾기 모달 시작--%>
	<div class="modal" id="searchIdModal">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Search ID</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" id="closeModalButton">
						<span aria-hidden="true"></span>
					</button>
				</div>
				<div class="modal-body">
					<div class="form-group row">
						<p>༼ つ ◕_◕ ༽つ 아이디 찾기는 이메일 인증 후 확인 가능합니다</p>
						<label for="email" class="col-sm-2 col-form-label">Email</label>
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
						<div class="input-group-addon d-flex justify-content-end mt-2">
							<button type="button" class="btn btn-outline-primary" id="mailCheckSearch">이메일 인증</button>
						</div>
						<div class="mail-check-box mt-2">
							<input class="form-control mail-check-input" id="mailAuth" placeholder="인증번호 6자리를 입력해주세요!" disabled="disabled" maxlength="6">
							<div class="valid-feedback" id="mailValid" >인증번호가 확인되었습니다.</div>
							<div class="invalid-feedback" id="mailInvalid">인증번호가 일치하지 않습니다. 다시 확인해주세요.</div>
						</div>
						<div class="mt-2">
							<label for="email" class="col-form-label">Searched ID</label>
							<input class="form-control" id="searchedID" disabled="disabled" readonly/>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary searchId" >Search Id</button>
					<button type="button" class="btn btn-secondary close-modal"  data-bs-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<%--아이디 찾기 모달 끝--%>

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
				<div id = "modalContent1">
					<div class="modal-body">
						<div class="form-group row">
							<p>༼ つ ◕_◕ ༽つ 이메일과 아이디를 확인 후 비밀번호 재설정을 하실 수 있습니다.</p>
							<label for="email" class="col-sm-2 col-form-label">Email</label>
							<div class="d-flex flex-row">
								<input type="text" class="form-control" id="email2" name="email" style="width:250px; margin-right:5px;">
								<div style="width:250px;">
									<select class="form-control" name="emailDomain" id="emailDomain2" >
										<option>메일주소 선택</option>
										<option>@naver.com</option>
										<option>@daum.net</option>
										<option>@gmail.com</option>
										<option>@hanmail.com</option>
									</select>
								</div>
							</div>
							<div class="input-group-addon d-flex justify-content-end mt-2">
								<button type="button" class="btn btn-outline-primary" id="mailCheckPwd">이메일 인증</button>
							</div>
							<div class="mail-check-box mt-2">
								<input class="form-control mail-check-input" id="mailAuth2" placeholder="인증번호 6자리를 입력해주세요!" disabled="disabled" maxlength="6">
								<div class="valid-feedback" id="mailValid2" >인증번호가 확인되었습니다.</div>
								<div class="invalid-feedback" id="mailInvalid2">인증번호가 일치하지 않습니다. 다시 확인해주세요.</div>
							</div>
							<div class="mt-2">
								<label class="form-label mt-4" for="username">아이디</label>
								<input type="text" value="" class="form-control" id="usernameId" name="username" placeholder="아이디 입력">
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" id = "next-btn" onclick = "nextStep()">Next</button>
						<button type="button" class="btn btn-secondary close-modal"  id="close1" data-bs-dismiss="modal">Close</button>
					</div>
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
						<button type="button" class="btn btn-primary searchId" onclick="resetPassword()">Reset Password</button>
						<button type="button" class="btn btn-secondary close-modal" id="close2"  data-bs-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
	</div>


	<%-- 비밀번호 찾기 모달 끝--%>

	<script>
		// 모달 창 X 버튼으로 닫기
		document.addEventListener("DOMContentLoaded", function() {
			const closeModalButton = document.getElementById("closeModalButton");
			closeModalButton.addEventListener("click", function() {
				const modal = document.getElementById("searchIdModal");
				modal.style.display = "none";
				modal.classList.remove("show");
			});
		});

		document.addEventListener("DOMContentLoaded", function() {
			const openModalLink = document.getElementById("openModalLink1");
			const modal = document.getElementById("searchIdModal");
			const searchButton = modal.querySelector(".searchId");
			const closeButton = modal.querySelector(".close-modal");

			openModalLink.addEventListener("click", function(event) {
				event.preventDefault(); // 기본 동작(링크 이동) 막기
				modal.style.display = "block"; // 모달 보이기
				modal.classList.add("show"); // 모달 클래스 추가 (부트스트랩 모달 스타일을 위해)
			});

			// 모달 창 외부를 클릭하면 모달이 닫히도록 설정
		/*	modal.addEventListener("click", function(event) {
				if (event.target === modal) {
					modal.style.display = "none";
					modal.classList.remove("show");
				}
			});*/
			const $searchedID =  $("#searchedID");
			// 모달 내부의 확인 버튼 클릭 이벤트
			searchButton.addEventListener("click", function() {
				// 여기에 확인 버튼 클릭 시 수행할 동작 추가
				const inputCode = $mailAuth.val();

				if (inputCode === code) {
					$searchedID.val(userId); // userId 값을 searchedID input 태그에 할당

				}
			});

			closeButton.addEventListener("click", function(){
				$("#username").val($searchedID.val());

				// 모달 내부의 필드들을 초기화
				$("#email").val(""); // 이메일 입력 필드 초기화
				$("#emailDomain").val("메일주소 선택"); // 이메일 도메인 선택 초기화
				$("#mailAuth").val(""); // 인증번호 입력 필드 초기화
				$("#mailValid").hide(); // 인증번호 유효성 표시 초기화
				$("#mailInvalid").hide(); // 인증번호 무효성 표시 초기화
				$searchedID.val("");
				// 모달 초기화
				modal.style.display = "none";
				modal.classList.remove("show");
			});




		});

	</script>

<script>
	// 이메일 인증
	let code;
	let userId;
	$('#mailCheckSearch').click(function() {
		const email = $('#email').val();// 이메일 주소값 얻어오기!
		console.log('완성된 이메일 : ' + email); // 이메일 오는지 확인
		const checkInput = $('.mail-check-input') // 인증번호 입력하는곳

		$.ajax({

			url : "${pageContext.request.contextPath}/member/checkEmailSearch.do",
			data : {
				email : email
			},
			beforeSend: function(xhr) {
				xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}')},
			method : "POST",
			dataType :"json",
			success(responseData){
				console.log(responseData);
				const {emailAuth, username} = responseData;
				checkInput.attr('disabled',false);
				code = emailAuth;
				userId = username;
				alert('인증번호가 전송되었습니다.');
			}
		}); // end ajax
	}); // end send eamil*/

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

</script>

	<%--비밀번호 재설정 관련 JS--%>
	<script>
		document.addEventListener("DOMContentLoaded", function() {
			// openModalLink2 버튼 클릭 이벤트 처리
			const openModalLink2 = document.getElementById("openModalLink2");
			const resetPwdModal = document.getElementById("resetPwdModal");
			const resetPwdModalContent1 = resetPwdModal.querySelector("#modalContent1");
			const resetPwdModalContent2 = resetPwdModal.querySelector("#modalContent2");

			openModalLink2.addEventListener("click", function(event) {
				event.preventDefault(); // 기본 동작(링크 이동) 막기
				resetPwdModal.style.display = "block"; // 모달 보이기
				resetPwdModal.classList.add("show"); // 모달 클래스 추가 (부트스트랩 모달 스타일을 위해)

				// 초기화: modalContent1만 보이게 하고 next 버튼 비활성화
				resetPwdModalContent1.style.display = "block";
				resetPwdModalContent2.style.display = "none";
				document.querySelector("#next-btn").disabled = true;
			});

			// 비밀번호 재설정 모달 창 닫기
			const closeButton = resetPwdModal.querySelector("#closeButton");
			closeButton.addEventListener("click", function() {
				resetPwdModal.style.display = "none";
				resetPwdModal.classList.remove("show");
			});



		});


		$(document).ready(function () {
			$("#close1").click(function (event) {
				event.stopPropagation(); // 이벤트 전파 중지

				// 모달 초기화
				$("#email2").val("");
				$("#emailDomain2").val("메일주소 선택");
				$("#mailAuth2").val(""); // 인증번호 입력 필드 초기화
				$("#mailValid2").hide(); // 인증번호 유효성 표시 초기화
				$("#mailInvalid2").hide(); // 인증번호 무효성 표시 초기화

				// 모달을 닫는 함수 호출
				closeModal();
			});

			$("#close2").click(function (event) {
				event.stopPropagation(); // 이벤트 전파 중지
				// 모달을 닫는 함수 호출
				$("#email2").val("");
				$("#emailDomain2").val("메일주소 선택");
				$("#mailAuth2").val(""); // 인증번호 입력 필드 초기화
				$("#mailValid2").hide(); // 인증번호 유효성 표시 초기화
				$("#mailInvalid2").hide(); // 인증번호 무효성 표시 초기화
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






		// 비밀번호 재설정의 이메일 인증
		let code2;
		let userId2;
		$('#mailCheckPwd').click(function() {
			const email = $('#email2').val();// 이메일 주소값 얻어오기!
			console.log('완성된 이메일 : ' + email); // 이메일 오는지 확인
			const checkInput = $('.mail-check-input') // 인증번호 입력하는곳

			$.ajax({

				url : "${pageContext.request.contextPath}/member/checkEmailSearch.do",
				data : {
					email : email
				},
				beforeSend: function(xhr) {
					xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}')},
				method : "POST",
				dataType :"json",
				success(responseData){
					console.log(responseData);
					const {emailAuth, username} = responseData;
					checkInput.attr('disabled',false);
					code2= emailAuth;
					userId2 = username;
					alert('인증번호가 전송되었습니다.');
				}
			}); // end ajax
		}); // end send eamil*/

		//비밀 번호 재설정의 인증번호 비교
		const $mailValid2 = $("#mailValid2");
		const $mailInvalid2 = $("#mailInvalid2");
		const $mailAuth2 = $("#mailAuth2");
		const $emailDomain2 = $('#emailDomain2');
		$mailAuth2.blur(function(){
			const inputCode = $(this).val();

			if(inputCode !== code2){
				$mailAuth2.addClass('is-invalid');
				$mailAuth2.removeClass('is-valid');
				$mailValid2.hide();
				$mailInvalid2.show();
			} else {
				$mailAuth2.removeClass('is-invalid');
				$mailAuth2.addClass('is-valid');
				$mailValid2.show();
				$mailInvalid2.hide();
				$('#mailCheckPwd').attr('disabled',true);
				$('#email2').attr('readonly',true);
				$emailDomain.attr('readonly',true);
				$emailDomain.attr('onFocus', 'this.initialSelect = this.selectedIndex');
				$emailDomain.attr('onChange', 'this.selectedIndex = this.initialSelect');

			}

		});

		// 비밀번호 재설정의 이메일에 도메인 합치기
		$(document).ready(function() {
			const $email2 = $("#email2");
			const $emailDomain2 = $("#emailDomain2");

			$emailDomain2.on("change", function() {
				const selectedDomain = $(this).val();
				const currentValue = $email2.val();

				// 선택한 도메인이 없으면 무시
				if (!selectedDomain) {
					return;
				}

				// 첫 번째 옵션 선택 시, 입력값을 초기화
				if (selectedDomain === "메일주소 선택") {
					$email2.val("");
					return;
				}

				// 입력값이 없거나 이미 선택한 도메인이 포함되어 있다면 새로운 값으로 업데이트
				if (!currentValue || currentValue.endsWith(selectedDomain)) {
					$email2.val("");
					$email2.val(selectedDomain);
				} else {
					// 입력값이 있고, 선택한 도메인이 포함되어 있지 않다면 도메인을 붙여서 업데이트
					if(currentValue.includes("@")){
						$email2.val("");
						$email2.val(selectedDomain);
					} else{
						$email2.val(currentValue + selectedDomain);
					}

				}
			});
		});
		// 비밀번호 재설정의 이메일 인증 끝!

			// 비밀번호 재설정 모달 화면 전환 관련 JS
			// 초기 설정: modalContent1만 보이게 하고 next 버튼 비활성화
			document.getElementById("modalContent2").style.display = "none";
			document.querySelector("#next-btn").disabled = true;

			// 이메일 인증 후, ID와 동일하면 next 버튼 활성화
			function enableNextButton() {
				const inputUsername = document.getElementById("usernameId").value;

				if (userId2 === inputUsername && code2 === $mailAuth2.val()) {
					document.querySelector("#next-btn").disabled = false;
				} else {
					document.querySelector("#next-btn").disabled = true;
				}
			}

			// 인증번호 입력 시마다 enableNextButton() 호출
			$mailAuth2.on("input", function () {
				enableNextButton();
			});
			// 아이디 입력 시마다 enableNextButton() 호출
			$("#usernameId").on("input", function () {
				enableNextButton();
			});

			// next 버튼 클릭 시 modalContent2로 전환
			function nextStep() {
			document.getElementById("modalContent1").style.display = "none";
			document.getElementById("modalContent2").style.display = "block";
			}
			// 모달을 닫는 함수
			function closeModal() {
				const resetPwdModal = document.getElementById("resetPwdModal");
				resetPwdModal.style.display = "none";
				resetPwdModal.classList.remove("show");
			}

			// 비밀번호 재설정 버튼 클릭 시 Ajax 요청 수행
			function resetPassword() {
			const newPassword = document.getElementById("password").value;

				// 여기서 Ajax 요청을 수행하고 서버에 newPassword 전송 등의 동작을 추가
				// 예시: jQuery를 사용한 Ajax 요청
				$.ajax({
				url: "${pageContext.request.contextPath}/member/resetPassword.do", // 요청을 처리할 서버 URL
				method: "POST",         // 요청 메서드
				dataType : "json",
				data: { password : newPassword, username : userId2 }, // 전송할 데이터
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

	</script>


<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
