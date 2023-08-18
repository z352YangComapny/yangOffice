<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>

<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>

<!-- bootstrap js: jquery load 이후에 작성할것.-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

<!-- bootstrap css -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">

<!-- 사용자작성 css -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/style.css" />
<script>
window.onload = () => {
	$("#loginModal")
		.modal()
		.on('hide.bs.modal', () => {
			location.href = '${pageContext.request.contextPath}';
		});
}
</script>
</head>
<body>

	<!-- Modal시작 -->
	<!-- https://getbootstrap.com/docs/4.1/components/modal/#live-demo -->
	<div class="modal fade" id="loginModal" tabindex="-1" role="dialog"
		aria-labelledby="loginModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="loginModalLabel">로그인</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<!--로그인폼 -->
				
				<!-- https://getbootstrap.com/docs/4.1/components/forms/#overview -->
				<form:form
					action="${pageContext.request.contextPath}/login"
					method="post">
					<div class="modal-body">
						<c:if test="${param.error ne null}">
							<div class="alert alert-danger" role="alert">
								아이디 또는 비밀번호가 일치하지 않습니다.
							</div>
						</c:if>
						<input 
							type="text" class="form-control" name="memberId"
							placeholder="아이디" value="user1" required> 
						<br /> 
						<input
							type="password" class="form-control" name="password"
							placeholder="비밀번호" value="1234" required>
					</div>
					<div class="modal-footer d-flex flex-column" style="align-items: unset;">
						<div>
							<%-- <a href="${pageContext.request.contextPath}/oauth/kakao/login.do">카카오 로그인</a> --%>
							<a href="${pageContext.request.contextPath}/oauth2/authorization/kakao">카카오 로그인</a>
						</div>
						<div class="d-flex justify-content-between">
							<div>
								<input type="checkbox" class="form-check-input" name="remember-me" id="remember-me"/>
								<label for="remember-me" class="form-check-label">Remember me</label>
							</div>
							<div>
								<button type="submit" class="btn btn-outline-success">로그인</button>
								<button type="button" class="btn btn-outline-success" data-dismiss="modal">취소</button>
							</div>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<!-- Modal 끝-->
</body>
</html>
