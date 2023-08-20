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

<div id="enroll-container" class="mx-auto text-center">
	<form:form name="memberCreateFrm" action="" method="POST">
		<h1>Sign up</h1>
		<table class="mx-auto w-75">
			<tr>
				<div class="form-group">
					<label class="form-label mt-4" for="usrname">아이디</label>
					<input type="text" value="" class="form-control is-valid" id="username" name="username">
					<div class="valid-feedback">사용가능한 아이디입니다.</div>
					<div class="invalid-feedback">4글자 이상 10글자 이하의 영문자, 숫자 조합으로 작성해주세요.</div>
				</div>
			<tr>
			<tr>
				<div class="form-group">
					<label class="form-label mt-4" for="password">비밀번호</label>
					<input type="password" value="" class="form-control is-valid" id="password" name="password">
					<label class="form-label mt-4" for="pwd_confirmation">비밀번호</label>
					<input type="password" value="" class="form-control is-valid" id="pwd_confirmation">
					<div class="valid-feedback">비밀번호 확인 되었습니다.</div>
					<div class="invalid-feedback">비밀번호를 입력해주세요</div>
					<div class="invalid-feedback">비밀번호가 일치하지 않습니다.</div>
				</div>
			</tr>
			<tr>
				<div class="form-group">
					<label class="form-label mt-4" for="name">이름</label>
					<input type="password" value="" class="form-control is-valid" id="name" name="name">
					<div class="valid-feedback">사용가능한 아이디입니다.</div>
					<div class="invalid-feedback">이름을 입력해주세요</div>
				</div>
			</tr>
			<tr>
				<div class="form-group">
					<label class="form-label mt-4" for="name">별명</label>
					<input type="password" value="" class="form-control is-valid" id="name" name="name">
					<div class="valid-feedback">사용가능한 별명입니다.</div>
					<div class="invalid-feedback">이미 존재하는 별명입니다.</div>
				</div>
			</tr>
			<tr>
				<label class="form-label mt-4" for="gender">성별</label>
				<div class="form-check">
					<input class="form-check-input" type="radio" name="gender" id="F" value="M" checked/>
					<label class="form-check-label" for="M">M</label>
				</div>
				<div class="form-check">
					<input class="form-check-input" type="radio" name="gender" id="F" value="F"/>
					<label class="form-check-label" for="F">F</label>
				</div>
			</tr>



				<th>아이디</th>
				<td>
					<div id="memberId-container">
						<input type="text"
							   class="form-control"
							   placeholder="4글자이상"
							   name="username"
							   id="username"
							   value="song"
							   pattern="\w{4,}"
							   required>
						<span class="guide ok">이 아이디는 사용가능합니다.</span>
						<span class="guide error">이 아이디는 이미 사용중입니다.</span>
						<input type="hidden" id="idValid" value="0"/>
					</div>
				</td>
			</tr>
			<tr>
				<th>패스워드</th>
				<td>
					<input type="password" class="form-control" name="password" id="password" value="1234" required>
				</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>
					<input type="text" class="form-control" name="name" id="name" value="홍길동" required>
				</td>
			</tr>
			<tr>
				<th>별명</th>
				<td>
					<input type="text" class="form-control" name="nickname" id="nickname" value="홍길동" required>
				</td>
			</tr>
			<tr>
				<th>성별</th>
				<td>
					<input type="text" class="form-control" name="gender" id="gender" value="F" required>
				</td>
			</tr>
			<tr>
				<th>연락처</th>
				<td>	
					<input type="text" class="form-control" name="phone" id="phone" value="01033333333"/>
				</td>
			</tr> 
			<tr>
				<th>이메일</th>
				<td>	
					<input type="email" class="form-control" placeholder="abc@xyz.com" name="email" id="email" value="honggd@naver.com">
				</td>
			</tr>
			<tr>
				<th>생일</th>
				<td>
					<input type="date" class="form-control" placeholder="abc@xyz.com" name="birthday" id="birthday" value="1990-09-09">
				</td>
			</tr>
		</table>
		<input type="submit" value="가입" >
		<input type="reset" value="취소">
	</form:form>
</div>
<script>
document.querySelector("#username").onkeyup = (e) => {
	const value = e.target.value;
	console.log(value);
	
	const guideOk = document.querySelector(".guide.ok");
	const guideError = document.querySelector(".guide.error");
	const idValid = document.querySelector("#idValid");
	
	if(value.length >= 4) {
		$.ajax({
			url : "${pageContext.request.contextPath}/member/checkIdDuplicate.do",
			data : {
				memberId : value
			},
			method : "GET",
			dataType : "json",
			success(responseData) {
				console.log(responseData);
				const {available} = responseData;
				if(available) {
					guideOk.style.display = "inline";
					guideError.style.display = "none";
					idValid.value = "1";
				}
				else {
					guideOk.style.display = "none";
					guideError.style.display = "inline";
					idValid.value = "0";
				}
				
			}
		});
	}
	else {
		guideOk.style.display = "none";
		guideError.style.display = "none";
		idValid.value = "0";
	}
};

document.memberCreateFrm.onsubmit = (e) => {
	const idValid = document.querySelector("#idValid");
	const password = document.querySelector("#password");
	const passwordConfirmation = document.querySelector("#passwordConfirmation");
	
	if(idValid === "0") {
		alert("사용가능한 아이디를 작성해주세요.");
		return false;
	}
	
	
	if(password.value !== passwordConfirmation.value) {
		alert("비밀번호가 일치하지 않습니다.");
		return false;
	}
};
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
