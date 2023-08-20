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
        <button type="button" class="btn btn-primary btn-lg" onclick="addComment();">댓글 작성</button>
        
        
    </div>
</div>
<script>
function goBack() {
    // 이전 페이지로 돌아가기
    history.back();
}
function addComment() {
    var commentContent = document.getElementById('commentContent').value;
    if (commentContent.trim() !== '') {
        // 작성한 댓글 내용을 서버로 전송하는 로직을 추가
        var questionId = ${question.id}; // 해당 게시글 ID를 가져오는 방식으로 변경
        var formData = new FormData();
        formData.append('questionId', questionId);
        formData.append('content', commentContent);
        
        // Ajax를 이용해 서버로 데이터 전송
        $.ajax({
            type: 'POST',
            url: '/addComment', // 실제 댓글 추가를 처리하는 URL로 수정
            data: formData,
            processData: false,
            contentType: false,
            success: function(data) {
                alert('댓글이 작성되었습니다.');
                // 페이지 새로고침 또는 댓글만 추가하여 업데이트 (필요에 따라 선택)
                location.reload(); // 전체 페이지 새로고침
                // 또는 댓글 영역만 업데이트 (Ajax 등으로 서버로부터 댓글 데이터를 받아서 업데이트)
            },
            error: function() {
                alert('댓글 작성 중 오류가 발생했습니다.');
            }
        });
    } else {
        alert('댓글 내용을 입력해주세요.');
    }
}

// ... 이후 코드 ...
</script>
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
