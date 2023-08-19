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
	
	<form:form name="memberUpdateFrm" action="${pageContext.request.contextPath}/member/memberUpdate.do" method="post">
		<h1 class="mt-4 mb-4">User Info</h1>
		<input type="text" class="form-control" placeholder="아이디 (4글자이상)" name="username" id="memberId" value='${loginMember.username}' readonly required/>
		<button type="button" class="form-control btn btn-outline-primary mb-2"><span>비밀번호 재설정</span></button>
		<input type="text" class="form-control" placeholder="이름" name="name" id="name" value='${loginMember.name}' readonly required/>
		<input type="text" class="form-control" placeholder="별명" name="nickname" id="nickname" value='${loginMember.nickname}' required/>
		<input type="text" class="form-control" placeholder="전화번호" name="phone" id="phone" value='${loginMember.phone}'/>
		<input type="email" class="form-control" placeholder="이메일" name="email" id="email" value='${loginMember.email}' required/>
		<input type="date" class="form-control" placeholder="생일" name="birthday" id="birthday" value='<sec:authentication property="principal.birthday"/>' required/>
		<br />
		<input type="submit" class="btn btn-outline-primary" value="수정" >&nbsp;
		<input type="reset" class="btn btn-outline-primary" value="취소">
	</form:form>
</div>

<script>

</script>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
