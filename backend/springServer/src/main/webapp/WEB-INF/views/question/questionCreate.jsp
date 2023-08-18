<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="문의사항 작성" name="title"/>
</jsp:include>
<style>
div#board-container{width:400px; margin:0 auto; text-align:center;}
div#board-container input{margin-bottom:15px;}
/* 부트스트랩 : 파일라벨명 정렬*/
div#board-container label.custom-file-label{text-align:left;}
</style>

<div id="board-container">
	<form:form 
		name="boardFrm" 
		action="${pageContext.request.contextPath}/question/createQna"
		method="post">
		<input type="text" class="form-control" placeholder="제목" name="title" id="title" required>
		<input type="text" class="form-control" name="writerId" id="writerId" value="${writerId}" >
		<div class="input-group mb-3" style="padding:0px;">
			<select name="questionType">
			    <option value="Q">이용문의</option>
			</select>
		</div>
	    <textarea class="form-control" name="content" placeholder="문의사항을 작성하세요." required></textarea>
		<br />
		<input type="submit" class="btn btn-outline-success" value="저장" >
	</form:form>
</div>
<script>

</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
