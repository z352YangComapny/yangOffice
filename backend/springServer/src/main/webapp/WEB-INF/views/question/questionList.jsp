<%@page import="com.yangworld.app.domain.question.entity.Question"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	List<Question> questions = (List<Question>) request.getAttribute("questions");
%>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="게시판" name="title"/>
</jsp:include>
<style>
/*글쓰기버튼*/
input#btn-add{float: left;
   						 margin: 40px;}
</style>
<section id="board-container" class="container">
	<div style="display: flex; justify-content: space-between; align-items: center; margin-top: 60px;">
        <p style="font-size: 37px; 
        background: linear-gradient(to right, #F3969A, #78C2AD);
        -webkit-background-clip: text;
    	-webkit-text-fill-color: transparent;"
        >공지사항 & 이용문의</p>
        <a href="${pageContext.request.contextPath}/question/questionCreate">
            <img src="${pageContext.request.contextPath}/resources/images/edit-button.png" id="btn-add" alt="edit-button" style="width: 70px; margin: 10px;"/>
        </a>
    </div>
	<table id="tbl-board" class="table table-bordered border-primary table-hover align-middle">
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
						        💭이용문의
						    </td>
						</c:if>
						<c:if test="${question.type ne 'Q'}">
						    <td>
						        📢공지사항
						    </td>
						</c:if>
						<td>
						    <a href="${pageContext.request.contextPath}/question/questionDetail?id=${question.id}">${question.title}
						    </a>
						     <c:choose>
						        <c:when test="${hasCommentsList[vs.index]}"><span>[1]</span></c:when>
						        <c:otherwise><span></span></c:otherwise>
						    </c:choose>
						</td>
						<td>
						   <c:choose>
						    <c:when test="${question.type ne 'Q'}">관리자</c:when>
						    <c:otherwise>
						        <c:choose>
						            <c:when test="${writerNames[vs.index] eq principalUsername}">
						                ${writerNames[vs.index]} 👻
						            </c:when>
						            <c:otherwise>${writerNames[vs.index]}</c:otherwise>
						        </c:choose>
						    </c:otherwise>
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
	<div style="display: flex; justify-content: center; margin:80px">
    <ul class="pagination">
        <li class="page-item disabled">
            <a class="page-link" href="#">&laquo;</a>
        </li>
        <c:forEach begin="1" end="${totalPages}" varStatus="pageStatus">
            <c:choose>
                <c:when test="${page eq pageStatus.index}">
                    <li class="page-item active">
                        <a class="page-link" href="#">${pageStatus.index}</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item">
                        <a class="page-link" href="${pageContext.request.contextPath}/question/questionList?page=${pageStatus.index}">${pageStatus.index}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <li class="page-item">
            <a class="page-link" href="#">&raquo;</a>
        </li>
    </ul>
</div>
	
</section> 
<script>
document.querySelector("#btn-add").onclick = () => {
	location.href = '${pageContext.request.contextPath}/question/questionCreate';
};
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
