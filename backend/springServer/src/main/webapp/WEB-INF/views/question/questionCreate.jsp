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
        height: 600px;
    }

    div#board-container input,
    div#board-container textarea,
    div#board-container select {
       /* width: 100%;*/
        padding: 10px;
       /* margin: 5px 0;*/
        font-size: 16px;
        /*border-radius: 8px;*/
        border: 1px solid #ccc;
        box-sizing: border-box;
    }
    .qnaForm{width : 100px;}
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
    .qnaBtn{width:100px;}
</style>

<div id="board-container" class="d-flex flex-column">
    <div style="margin-right:650px; margin-bottom: 20px;">
        <p style="font-size: 37px;
            background: linear-gradient(to right, #F3969A, #78C2AD);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;"
        >공지사항 & 이용문의</p>
    </div>
    <div id="board-form">
        <form:form name="boardFrm" action="${pageContext.request.contextPath}/question/createQna" method="post">
            <%--<input type="text" class="form-control" placeholder="제목을 작성하세요." name="title" id="title" required>--%>
            <div class="input-group mb-3">
                <button class="btn btn-primary qnaForm" id="qnaTitle" disabled>제 목</button>
                <input type="text" class="form-control" placeholder="제목을 작성하세요"  name="title" id="title" required aria-label="Recipient's username" aria-describedby="button-addon2">
            </div>
            <div class="input-group mb-3">
                <button class="btn btn-primary qnaForm" id="qnaWriter" disabled>작 성 자 </button>
                <input type="text" class="form-control" name="writerId" id="writerId" value="${pageContext.request.userPrincipal.name}" readonly disabled aria-label="Recipient's username" aria-describedby="button-addon2">
            </div>
           <%-- <input type="text" class="form-control" name="writerId" id="writerId" placeholder="${pageContext.request.userPrincipal.name}" value="${pageContext.request.userPrincipal.name}" readonly disabled>--%>
            <%-- <input type="text" class="form-control" name="writerId" id="writerId" placeholder="${writerId}" value="${writerId}" readonly > --%>
            <div class="form-group">
                <label for="exampleSelect1" class="form-label mt-1"></label>
                <select class="form-select mb-2" id="exampleSelect1" name="questionType">
                	<c:if test="${!isAdmin}">
                    <option value="Q">이용문의</option>
                    </c:if>
                    <c:if test="${isAdmin}">
      				  <option value="N">공지사항</option>
   					 </c:if>
                </select>
                <textarea class="form-control" name="content" placeholder="내용을 작성하세요." style="resize:none; height:300px;" required></textarea>
            </div>
            <div class="d-flex flex-row justify-content-end mt-3">
                <button type="submit" class="btn btn-outline-primary btn-sm qnaBtn">등록</button>
                &nbsp;
                <button type="reset" class="btn btn-outline-danger btn-sm qnaBtn">초기화</button>
            </div>
        </form:form>
    </div>
</div>
<script>

</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
