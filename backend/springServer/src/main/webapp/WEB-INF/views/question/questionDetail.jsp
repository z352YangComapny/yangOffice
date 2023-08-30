<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
        height: 700px;
    }

    div#board-form input,
    div#board-form textarea {
        /*width: 100%;*/
  /*      margin-bottom: 15px;*/
        padding: 10px;
        font-size: 17px;
      /*  border-radius: 8px;*/
        border: 1px solid #ccc;
        box-sizing: border-box;
    }

    /* 부트스트랩 : 파일라벨명 정렬 */
    div#board-container label.custom-file-label {
        text-align: left;
    }
    .qnaForm{width : 100px;}
   .cmtBtn{width: 70px;}
</style>
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">
<sec:authentication property="principal" var="loginMember"/>
<div id="board-container" class="d-flex flex-column">
    <div style="margin-right:650px; margin-bottom: 20px;">
        <p style="font-size: 37px;
            background: linear-gradient(to right, #F3969A, #78C2AD);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;"
        >공지사항 & 이용문의</p>
    </div>
    <div id="board-form">
        <form name="boardDetailFrm" action="${pageContext.request.contextPath}/question/updateQuestion" method="POST">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <input type="hidden" name="id" value="${question.id}">
            <%--<input type="text" class="form-control" placeholder="제목을 작성하세요." name="title" id="title" required>--%>
            <div class="input-group mb-3">
                <button class="btn btn-primary qnaForm" id="qnaTitle" disabled>제 목</button>
                <input type="text" class="form-control" placeholder="제목을 작성하세요"  name="title" id="title" value="${question.title}" required aria-describedby="button-addon2">
            </div>
            <div class="input-group mb-3">
                <button class="btn btn-primary qnaForm" id="qnaWriter" disabled>작 성 자 </button>
                <input type="text" class="form-control" name="writerId" id="writerId" value="${writerId}" readonly disabled  aria-describedby="button-addon2">
            </div>
            <div class="d-flex justify-content-end">
                <fmt:parseDate value="${question.regDate}" pattern="yyyy-MM-dd'T'HH:mm" var="regDate"/>
                <fmt:formatDate value="${regDate}" pattern="yy/MM/dd HH:mm"/>
            </div>
            <%-- <input type="text" class="form-control" name="writerId" id="writerId" placeholder="${pageContext.request.userPrincipal.name}" value="${pageContext.request.userPrincipal.name}" readonly disabled>--%>
            <%-- <input type="text" class="form-control" name="writerId" id="writerId" placeholder="${writerId}" value="${writerId}" readonly > --%>
            <div class="form-group">
                <label for="exampleSelect1" class="form-label mt-1"></label>
                <select class="form-select mb-2" id="exampleSelect1" name="questionType" aria-readonly="true" disabled>
                    <c:if test="${questionType eq 'Q'}">
                        <option value="Q">이용문의</option>
                    </c:if>
                    <c:if test="${questionType eq 'N'}">
                        <option value="N">공지사항</option>
                    </c:if>
                </select>
                <textarea class="form-control" name="content" value="${question.content}" style="resize:none; height:180px;" required>${question.content}</textarea>
                <div id="qnaFrmBtn" class="d-flex justify-content-end mt-2">
                   <c:if test="${(writerId eq loginMemberId) || isAdmin}">
                    <button type="submit" class="btn btn-outline-primary btn-sm mr-2">수정</button>
                    <button class="btn btn-outline-danger btn-sm mr-1" onclick="deleteBoard()">삭제</button>
                   </c:if>
                </div>
            </div>
        </form>
        <hr/>
        <div class="d-flex justify-content-start">
            <p class="ml-2">문의 답변</p>
        </div>
        <div class="d-flex flex-row" id="commentAreaContainer">
            <label for="commentContent"></label>
            <div id="commentArea">
                <c:if test="${isAdmin && questionType eq 'Q'}">
                <%--<div style="display: flex; align-items: center;">--%>
                    <textarea class="form-control" id="commentContent" rows="1" placeholder="답글을 입력해주세요." style="width: 850px; height: 100px; resize: none;"></textarea>
                <%--</div>--%>
                </c:if>
                <c:if test="${!isAdmin && not empty qnaComments && questionType eq 'Q'}">
                    <textarea class="form-control" id="commentContent" rows="1" placeholder = "${qnaComments}" style="resize: none; flex:1;">${qnaComments}<textarea>
                </c:if>
                <c:if test="${!isAdmin && (qnaComments == null || empty qnaComments) && questionType eq 'Q'}">
                    <textarea class="form-control" id="commentContent" rows="1" placeholder = "문의주신 내용 확인중입니다." style= "resize:none; flex: 1;"></textarea>
                </c:if>
            </div>
            <div class="d-flex flex-column ml-3">
                <c:if test="${empty qnaComments && isAdmin}">
                    <button type="button" class="btn btn-outline-primary btn-sm mt-1 cmtBtn" id="commentCreate">답글</button>
                </c:if>
                <c:if test="${not empty qnaComments && isAdmin}">
                    <button type="button" class="btn btn-outline-primary btn-sm mt-1 edit-button cmtBtn" id="editComment">편집</button>
                    <button type="button" class="btn btn-outline-danger btn-sm mt-1 cmtBtn" id="commentDelete">삭제</button>
                </c:if>
            </div>
        </div>
        <div class="d-flex justify-content-end">
            <a href="#" onclick="goBack()"><img  class="mt-2" src="${pageContext.request.contextPath}/resources/images/back.png" style="width:40px;"/></a>
            <%--<button type="button" class="btn btn-outline-info btn-sm mt-3" onclick="goBack();">뒤로가기</button>--%>
        </div>
    </div>
</div>


<%--&lt;%&ndash;기존 detail form&ndash;%&gt;
<div id="board-container">
    <div id="boardform">
        <input type="text" class="form-control" placeholder="제목" name="title" id="title" value="${question.title}" readonly required>
        <input type="text" class="form-control" name="memberId" value="${principalName}" readonly required>
        <textarea class="form-control" name="content" placeholder="문의사항" readonly required>${question.content}</textarea>
        <input type="datetime-local" class="form-control" name="createdAt" value='${question.regDate}'>
        
	    <c:if test="${isAdmin && questionType eq 'Q'}">
            <label for="commentContent"></label>
            <div>
			    <div style="display: flex; align-items: center;">
			    	
			        <textarea class="form-control" id="commentContent" rows="1" placeholder="댓글을 입력해주세요." style="flex: 1;"></textarea>
			        <button type="button" class="btn btn-danger btn-sm" id="commentDelete" style="display: none;">x</button>
			    </div>
			</div>
            <input type="hidden" id="commentId" value="">
			    <button type="button" class="btn btn-primary btn-lg" id="commentCreate">댓글 작성</button>
			    <button type="button" class="btn btn-primary btn-lg edit-button" id="editComment" style="display:none">댓글 수정</button>
        </c:if> 
        
        <c:if test="${!isAdmin && not empty qnaComments && questionType eq 'Q'}">
        	<textarea class="form-control" id="commentContent" rows="1" placeholder = "${qnaComments}" style="flex: 1;"></textarea>
        </c:if>
        <c:if test="${!isAdmin && (qnaComments == null || empty qnaComments) && questionType eq 'Q'}">
        	<textarea class="form-control" id="commentContent" rows="1" placeholder = "문의주신 내용 확인중입니다.?" style="flex: 1;"></textarea>
        </c:if>
        
        <button type="button" class="btn btn-primary btn-lg" onclick="goBack();">뒤로가기</button>
        
    </div>
</div>--%>

<script>
const csrfToken = "${_csrf.token}";
/*const commentId = document.getElementById('commentId').value;*/

function goBack() {
    window.history.back();
}


document.querySelector('#commentCreate').onclick = () => {
    const commentContent = document.getElementById('commentContent').value;
	
    if (commentContent.trim() !== '') {
        const qnaCommentDto = {
            commentId: 0,
            questionId: ${questionId} 
        };

        const qnaCommentAllDto = {
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
            body: JSON.stringify(qnaCommentAllDto)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('댓글 작성 중 오류 발생');
            }
            return response.json();
        })
        .then(data => {
            alert('댓글이 작성되었습니다.');
            const{qnaComments} = data;

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


document.querySelector('#editComment').onclick = () => {
    const commentContent = document.getElementById('commentContent').value;

    if (commentContent.trim() !== '') {
        const qnaCommentDto = {
			commentId: 0,
            questionId: ${questionId} 
        };

        const qnaCommentAllDto = {
            content: commentContent, 
            writerId: "${principalId}",
            commentQna: qnaCommentDto 
        };

        fetch('/qnaCommentUpdate', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'X-CSRF-TOKEN': csrfToken // CSRF 토큰 추가
            },
            body: JSON.stringify(qnaCommentAllDto)
        })
        .then(response => {
        	console.log(response)
            if (!response.ok) {
                throw new Error('댓글 수정 중 오류 발생');
            }
            return response.json();
        })
        .then(data => {
        	console.log(data)
            alert('댓글이 수정되었습니다.');
            location.reload();
            
        })
        .catch(error => {
            console.error('댓글 수정 중 오류:', error);
            alert('댓글 수정 중 오류가 발생했습니다.');
            
        });
    } else {
        alert('수정 내용을 입력해주세요.');
    }
};

document.querySelector('#commentDelete').onclick = () => {
	const commentContent = document.getElementById('commentContent').value;
	
	if (commentContent.trim() !== '') {
    const qnaCommentDto = {
		commentId: 0,
        questionId: ${questionId} 
     };
	
    const qnaCommentAllDto = {
        content: commentContent, 
        writerId: "${principalId}",
        commentQna: qnaCommentDto 
    };
    fetch('/qnaCommentDelete', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': csrfToken
        },
        body: JSON.stringify(qnaCommentAllDto) 
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('댓글 삭제 중 오류 발생');
        }
        return response.json();
    })
    .then(data => {
        alert('댓글이 삭제되었습니다.');
        location.reload();
1    })
    .catch(error => {
        console.error('댓글 삭제 중 오류:', error);
        alert('댓글 삭제 중 오류가 발생했습니다.');
    });
	}
};





document.addEventListener("DOMContentLoaded", () => {
	
	/* const isAdmin = ${isAdmin}; 

    const commentCreateButton = document.querySelector('#commentCreate');
    const editCommentButton = document.querySelector('#editComment');

    if (isAdmin) {
        // 관리자인 경우 댓글 작성과 수정 버튼을 보이게 설정
        commentCreateButton.style.display = 'inline';
        editCommentButton.style.display = 'inline';
    } else {
        // 일반회원인 경우 댓글 작성과 수정 버튼을 숨김 처리
        commentCreateButton.style.display = 'none';
        editCommentButton.style.display = 'none';
    } */

    
    fetch('/getQnaComments?questionId=${questionId}', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': csrfToken // CSRF 토큰 추가
        }
    })
    .then(response => {
        if (!response.ok) {
        	console.log("zlzllzlzlzlzl");
            throw new Error('댓글 가져오기 중 오류 발생');
        }
        return response.json();
    })
    .then(data => {
        if (data !== "no") {
            // 댓글 데이터가 있는 경우
            console.log(data);
            const [{id}] = data;
	        const comments = data;
	        const commentContent = document.getElementById("commentContent");
	        const commentIdInput = document.getElementById("commentId"); // Get the hidden input
	        if (comments.length > 0) {
	            commentContent.value = comments[0].content;
	            commentIdInput.value = comments[0].commentId; // commentId 업데이트
	            
	            
	        }
	        commentIdInput.value = id;
	        console.log(commentIdInput.value);
	        document.getElementById('commentDelete').style.display = 'inline';
	        document.getElementById('commentCreate').style.display = 'none';
	        document.getElementById('editComment').style.display = 'inline';
	        
        } else {
            // 댓글 데이터가 없는 경우
            const commentContent = document.getElementById("commentContent");
            commentContent.placeholder = "댓글을 입력해주세요.";
        }
    })
    .catch(error => {
        console.error('댓글 가져오기 중 오류:', error);
    });
    
    
    /* const commentContent = document.getElementById("commentContent");
    const editButton = document.querySelector("#editComment");
    
    
    editButton.onclick = () => {
        if (editButton.textContent === "댓글 수정") {
            // 수정 버튼 클릭 시 댓글 수정 가능 상태로 변경
            commentContent.readOnly = false;
            editButton.textContent = "수정 완료";
        } else {
            // 수정 완료 버튼 클릭 시 수정 내용 저장 및 수정 불가능 상태로 변경
            const updatedComment = commentContent.value;

            // 서버로 수정 내용 전송
            const qnaCommentDto = {
                commentId: comments[0].commentId, // 댓글 ID
                questionId: ${questionId}
            };

            const qnaCommentAllDto = {
                content: updatedComment,
                writerId: "${principalId}",
                commentQna: qnaCommentDto
            };

            fetch('/qnaCommentUpdate', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'X-CSRF-TOKEN': csrfToken
                },
                body: JSON.stringify(qnaCommentAllDto)
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('댓글 수정 중 오류 발생');
                }
                return response.json();
            })
            .then(data => {
                alert('댓글이 수정되었습니다.');
                location.reload();
            })
            .catch(error => {
                console.error('댓글 수정 중 오류:', error);
                alert('댓글 수정 중 오류가 발생했습니다.');
            });

            // 수정 완료 후 수정 불가능 상태로 변경
            commentContent.readOnly = true;
            commentContent.value = updatedComment; // 화면에 변경된 내용 표시
            editButton.textContent = "댓글 수정";
        }
    }; */
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
<script>
    const deleteBoard = () =>{
        const questionId = ${question.id};
        console.log(questionId);
        $.ajax({

            url: "${pageContext.request.contextPath}/question/deleteBoard",
            data: {questionId : questionId},
            method:"POST",
            dataType : "json",
            beforeSend: function(xhr) {
                xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}')},
            success(responseData){
                const{msg} = responseData;
                alert(msg);

                location.href = '${pageContext.request.contextPath}/question/questionList';

            }
        });

    }
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
