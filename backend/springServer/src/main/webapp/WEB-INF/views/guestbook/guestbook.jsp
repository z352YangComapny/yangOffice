<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="방명록" name="title"/>
</jsp:include>

<style>
div#guestbook-container{width:60%; margin:0 auto; text-align:center;}
</style>
<div id="guestbook-container">
	<form:form action="${pageContext.request.contextPath}/guestbook/create.do" class="form-inline" method="post">
		<input type="text" class="form-control col-sm-10 ml-1" name="memberId" placeholder="memberId" required/>&nbsp;
		<input type="text" class="form-control col-sm-10 ml-1" name="writerId" placeholder="writerId" required/>&nbsp;
		<input type="text" class="form-control col-sm-10 ml-1" name="content" placeholder="내용" required/>&nbsp;
		<button class="btn btn-outline-success" type="submit">저장</button>
	</form:form> 
	
	<table class="table">
		<thead>
			<tr>
				<th>번호</th>
				<th>작성자</th>
				<th>내용</th>
				<th>작성일</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty guestBooks}">
				<tr>
					<td colspan="4" class="text-center">작성된 방명록이 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${not empty guestBooks}">
				<c:forEach items="${guestBooks}" var="guestbook" varStatus="vs">
					<tr>
						<td>${guestbook.id}</td>
						<td>${guestbook.writerId}</td>
						<td>${guestbook.content}</td>
						<td>${guestbook.regDate}</td>
						<td>
							<input type="text" class="form-control col-sm-10 ml-1" name="content" placeholder="내용" required/>&nbsp;
							<button type="button" class="btn btn-outline-danger" onclick="showInput()">수정</button>
							<<!--  button class="btn-update btn btn-outline-primary" value="${dev.id}">-->
						</td>
						<td>
							<button type="button" class="btn btn-outline-danger">삭제</button>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</div>
<form:form action="${pageContext.request.contextPath}/guestbook/update.do" class="form-inline" name="guestbookUpdateFrm" method="post">
	<input type="hidden" name="content" />
</form:form>
<script>
function showInput() {
    var inputField = document.querySelector('input[name="content"]');
    inputField.style.display = 'block';
}
</script>
