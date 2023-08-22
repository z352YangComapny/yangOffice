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
        height: 500px;
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
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">
<div id="board-container">
    <div id="board-form">
        <input type="text" class="form-control" placeholder="제목" name="title" id="title" value="${question.title}" readonly required>
        <input type="text" class="form-control" name="memberId" value="${principalName}" readonly required>
        <textarea class="form-control" name="content" placeholder="문의사항" readonly required>${question.content}</textarea>
        <input type="datetime-local" class="form-control" name="createdAt" value='${question.regDate}'>
        
        <c:if test="${isAdmin && questionType eq 'Q'}">
                <label for="commentContent"></label>
                <textarea class="form-control" id="commentContent" rows="1" placeholder="댓글을 입력해주세요."></textarea>
            <button type="button" class="btn btn-primary btn-lg" id="commentCreate">댓글 작성</button>
        </c:if>
        
        
        
        
        <button type="button" class="btn btn-primary btn-lg" onclick="goBack();">뒤로가기</button>
        
    </div>
</div>

<script>
const csrfToken = "${_csrf.token}";


document.querySelector('#commentCreate').onclick = () => {
    const commentContent = document.getElementById('commentContent').value;

    if (commentContent.trim() !== '') {
        const qnaCommentDto = {
            commentId: 0,
            questionId: ${questionId} 
        };

        const qnaCommentCreateDto = {
            content: commentContent, 
            writerId: "${principalId}",
            commentQna: qnaCommentDto 
        };

        fetch('/qnaCommentCreate', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'X-CSRF-TOKEN': csrfToken // CSRF 토큰 추가
            },
            body: JSON.stringify(qnaCommentCreateDto)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('댓글 작성 중 오류 발생');
            }
            return response.json();
        })
        .then(data => {
            alert('댓글이 작성되었습니다.');
            location.reload();
        })
        .catch(error => {
            console.error('댓글 작성 중 오류:', error);
            alert('댓글 작성 중 오류가 발생했습니다.');
        });
    } else {
        alert('댓글 내용을 입력해주세요.');
    }
};

document.addEventListener("DOMContentLoaded", () => {
	
    fetch('/getQnaComments?questionId=${questionId}', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': csrfToken // CSRF 토큰 추가
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('댓글 가져오기 중 오류 발생');
        }
        return response.json();
    })
    .then(data => {
        if (data !== "no") {
            // 댓글 데이터가 있는 경우
            const comments = data;
            const commentContent = document.getElementById("commentContent");
            commentContent.value = comments[0].content; // 첫 번째 댓글의 내용을 가져와서 출력
            
        } else {
            // 댓글 데이터가 없는 경우
            const commentContent = document.getElementById("commentContent");
            commentContent.placeholder = "댓글을 입력해주세요.";
        }
    })
    .catch(error => {
        console.error('댓글 가져오기 중 오류:', error);
    });
});

/* function addComment() {
    const commentContent = document.getElementById('commentContent').value;
    if (commentContent.trim() !== '') {
        const questionId = ${question.id};
        const formData = new FormData();
        formData.append('questionId', questionId);
        formData.append('content', commentContent);
        
        $.ajax({
            type: 'POST',
            url: '/qnaCommentCreate', 
            data: formData,
            processData: false,
            contentType: false,
            beforeSend: function(xhr) {
                xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}')},
            success: function(data) {
                alert('댓글이 작성되었습니다.');
                location.reload(); 
            },
            error: function() {
                alert('댓글 작성 중 오류가 발생했습니다.');
            }
        });
    } else {
        alert('댓글 내용을 입력해주세요.');
    }
} */
/* function deleteNotice(questionId) {
    if (confirm('정말로 공지사항을 삭제하시겠습니까?')) {
        const jsonData = { questionId: questionId }; // JSON 데이터 생성

        $.ajax({
            type: 'POST',
            url: "${pageContext.request.contextPath}/question/deleteNotice",
            data: JSON.stringify(jsonData), // JSON 형식으로 데이터 변환
            contentType: "application/json", // 데이터 형식을 JSON으로 지정
            headers: {
                "X-CSRF-TOKEN": csrfToken // CSRF 토큰을 헤더에 추가 (csrfToken 변수는 클라이언트 측에서 가져와야 함)
            },
            success: function(data) {
                alert('공지사항이 삭제되었습니다.');
                location.reload(); // 페이지 새로고침
            },
            error: function() {
                alert('공지사항 삭제 중 오류가 발생했습니다.');
            }
        });
    }
} */


</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
