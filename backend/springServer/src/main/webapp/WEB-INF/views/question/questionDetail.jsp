<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="게시판 상세보기" name="title"/>
</jsp:include>
<style>
    div#board-container {
        width: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
        min-height: 80vh;
    }

    div#board-form {
        width: 1000px;
        text-align: center;
        padding: 20px;
        border: 1px solid #ccc;
        border-radius: 10px;
        height: 400px;
    }

    div#board-form input,
    div#board-form textarea {
        width: 100%;
        margin-bottom: 15px;
        padding: 15px;
        font-size: 16px;
        border-radius: 8px;
        border: 1px solid #ccc;
        box-sizing: border-box;
    }

    /* 부트스트랩 : 파일라벨명 정렬 */
    div#board-container label.custom-file-label {
        text-align: left;
    }
</style>

<div id="board-container">
    <div id="board-form">
        <input type="text" class="form-control" placeholder="제목" name="title" id="title" value="${question.title}" readonly required>
        <input type="text" class="form-control" name="memberId" value="${question.writerId}" readonly required>
        <textarea class="form-control" name="content" placeholder="문의사항" readonly required>${question.content}</textarea>
        <input type="datetime-local" class="form-control" name="createdAt" value='${question.regDate}'>
        <button type="button" class="btn btn-primary btn-lg" onclick="goBack();">뒤로가기</button>
    </div>
</div>
<script>
function goBack() {
    // 이전 페이지로 돌아가기
    history.back();
}
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
