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

	<div class="d-flex justify-content-center align-items-center" style = "min-width: 100vw; margin-top : 50px;">
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
						<input type="text" value="user1" name = "username" placeholder="Input" class="form-control" />
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
		<a href="">아이디찾기</a>
		&nbsp;
		|
		&nbsp;
		<a href="">비밀번호찾기</a>
	</div>
	<div class="sns-icon d-flex justify-content-center align-items-center"  style ="margin-top : 30px;" >
		<a href=""><img src="${pageContext.request.contextPath}/resources/images/apple.png"></a>
		&nbsp;
		<a href=""><img src="${pageContext.request.contextPath}/resources/images/code.png"></a>
		&nbsp;
		<a href=""><img src="${pageContext.request.contextPath}/resources/images/google.png"></a>
		&nbsp;
		<a href="${pageContext.request.contextPath}/oauth/kakao/member/memberLogin.do"><img src="${pageContext.request.contextPath}/resources/images/instagrampng.png">123</a>
		&nbsp;
		<a href=""><img src="${pageContext.request.contextPath}/resources/images/naver.png"></a>
		&nbsp;
		<a href=""><img src="${pageContext.request.contextPath}/resources/images/steam.png"></a>

	</div>

	<div class="d-flex flex-column justify-content-center align-items-center mt-5">
		<p>아직 회원이 아니신가요?</p>
		<button type = "submit" id = "login" class="btn btn-primary" style="border-radius: 50px; width : 20vw;" onclick="location.href='${pageContext.request.contextPath}/member/memberCreate.do';">
			회원가입</button>
	</div>






			<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
