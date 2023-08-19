<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="/WEB-INF/views/common/header.jsp">
    <jsp:param value="문의사항 작성" name="title" />
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
        height: 500px;
    }

    div#board-container input,
    div#board-container textarea,
    div#board-container select {
        width: 100%;
        padding: 15px;
        margin: 5px 0;
        font-size: 16px;
        border-radius: 8px;
        border: 1px solid #ccc;
        box-sizing: border-box;
    }

    /* 부트스트랩 : 파일라벨명 정렬 */
    div#board-container label.custom-file-label {
        text-align: left;
    }

    div#board-container select {
        appearance: none;
        -webkit-appearance: none;
        -moz-appearance: none;
        background: transparent;
        background-image: url('path-to-arrow-icon.png'); /* 화살표 아이콘 이미지 경로로 대체 */
        background-repeat: no-repeat;
        background-position: right center;
    }
</style>
		<!-- 		
        	<h3>
			  문의사항
			  <small class="text-body-secondary">작성</small>
			</h3> -->
<div id="board-container">
    <div id="board-form">
        <form:form name="boardFrm" action="${pageContext.request.contextPath}/question/createQna" method="post">
            <input type="text" class="form-control" placeholder="제목을 작성하세요." name="title" id="title" required>
            <input type="text" class="form-control" name="writerId" id="writerId" placeholder="${writerId}" value="${writerId}" readonly >
            <div class="form-group">
                <label for="exampleSelect1" class="form-label mt-4"></label>
                <select class="form-select" id="exampleSelect1" name="questionType">
                    <option value="Q">이용문의</option>
                </select>
            </div>
            <textarea class="form-control" name="content" placeholder="문의사항을 작성하세요." required></textarea>
            <br />
            <button type="submit" class="btn btn-primary btn-lg">등록</button>
        </form:form>
    </div>
</div>
<script>
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
