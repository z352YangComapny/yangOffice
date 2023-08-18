<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="게시판" name="title"/>
</jsp:include>
<style>
/*글쓰기버튼*/
input#btn-add{float:right; margin: 0 0 15px;}
</style>
<section id="board-container" class="container">
	<input type="button" value="글쓰기" id="btn-add" class="btn btn-outline-success"/>
	<table id="tbl-board" class="table table-striped table-hover">
		<thead>
			<tr>
				<th>번호</th>
				<th>분류</th> <!-- type --> 
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty questions}">
				<tr>
					<td colspan="5" class="text-center">조회된 게시글이 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${not empty questions}">
				<c:forEach items="${questions}" var="question" varStatus="vs">
					<tr>
						<td>${question.id}</td>
						<c:if test="${question.type ne 'N'}">
						    <td>
						        이용문의
						    </td>
						</c:if>
						<c:if test="${question.type ne 'Q'}">
						    <td>
						        🗣️공지사항
						    </td>
						</c:if>
						<td>
						    <a href="${pageContext.request.contextPath}/question/questionDetail?id=${question.id}">${question.title}</a>
						</td>
						<td>
						    <c:choose>
						        <c:when test="${question.type ne 'Q'}">관리자</c:when>
						        <c:otherwise>${question.writerId}</c:otherwise>
						    </c:choose>
						</td>
						<td>
							<fmt:parseDate value="${question.regDate}" pattern="yyyy-MM-dd'T'HH:mm" var="regDate"/>
							<fmt:formatDate value="${regDate}" pattern="yy/MM/dd HH:mm"/>
						</td>
					</tr>				
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</section> 
<script>
document.querySelector("#btn-add").onclick = () => {
	location.href = '${pageContext.request.contextPath}/question/questionCreate';
};
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
