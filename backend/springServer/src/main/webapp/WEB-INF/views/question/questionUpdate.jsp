<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="/WEB-INF/views/common/header.jsp">
    <jsp:param value="게시글 수정" name="title"/>
</jsp:include>

<div class="container">
    <h2>게시글 수정</h2>
    <form action="${pageContext.request.contextPath}/question/updateQuestion" method="post">
        <!-- CSRF 토큰 추가 -->
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <input type="hidden" name="id" value="${question.id}">
        <div class="form-group">
            <label for="title">제목</label>
            <input type="text" class="form-control" id="title" name="title" value="${question.title}" required>
        </div>
        <div class="form-group">
            <label for="content">내용</label>
            <textarea class="form-control" id="content" name="content" rows="5" required>${question.content}</textarea>
        </div>
        
        <button type="submit" class="btn btn-primary">수정</button>
        <a href="${pageContext.request.contextPath}/question/deleteNotice?questionId=${question.id}" 
   			class="btn btn-primary ">삭제</a>
    </form>
</div>


<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
