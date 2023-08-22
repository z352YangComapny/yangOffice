<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/member.css" />

<style>
    /* 스타일링 기본 설정 */
    body {
        font-family: Arial, sans-serif;
    }

    /* 컨테이너 스타일 조절 */
    .carousel-and-content {
        display: flex;
        align-items: center;
        justify-content: center;
    }

    /* 이미지 크기 및 정렬 조절 */
    .carousel-inner .carousel-item img {
        width: 500px; /* 이미지 최대 너비 */
        height: 500px;
    }

    /* 사진 박스 스타일 조절 */
    .carousel-box {
        flex: 1;
        margin-right: 20px;
    }

    /* 오른쪽 컨텐츠 스타일 조절 */
    .content-box {
        flex: 1;
        padding: 20px;
        background-color: #f5f5f5;
        border-radius: 5px;
    }
</style>
<hr style="height: 3px">
<div class="carousel-and-content">
    <div class="carousel-box">
        <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
            <div class="carousel-indicators">
                <c:forEach items="${photoDetail}" var="photo" varStatus="status">
                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="${status.index}" class="${status.first ? 'active' : ''}" aria-current="${status.first ? 'true' : 'false'}" aria-label="Slide ${status.index + 1}"></button>
                </c:forEach>
            </div>
            <div class="carousel-inner">
                <c:forEach items="${photoDetail}" var="photo" varStatus="status">
                    <div class="carousel-item ${status.first ? 'active' : ''}">
                        <img src="${pageContext.request.contextPath}/resources/upload/attachment/feed/${photo.attachments[0].renamedFilename}" class="d-block" alt="Image ${status.index + 1}">
                    </div>
                </c:forEach>
            </div>
            <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </a>
        </div>
    </div>
    <div class="content-box">
         <div>${response.content}</div>
    </div>
</div>
<hr style="border: 3px">
		<!-- 댓글 작성 폼 시작 -->
	   		<div class="comment-form">
	            <form:form
	            action="${pageContext.request.contextPath}/feedDetails/commentCreate" 
	            method="post">
	                <div class="mb-3">
	                    <label for="comment" class="form-label">댓글 내용</label>
	                    <textarea class="form-control" id="comment" name="comment" rows="3" required></textarea>
	                    <input type="hidden" name="photoFeedId" value="${response.id}">
	                    <input type="hidden" name="returnUrl" value="${requestScope.requestURL}">
	                </div>
	                <button type="submit" class="btn btn-primary">댓글 작성</button>
	            </form:form>
	        </div>
		<!-- 댓글 작성 폼 끝 -->
     		
   		<!-- 댓글 목록 폼 시작 -->
				<div class="comment-list">
				    <h2>댓글 목록</h2>
				    <ul class="list-group">
				        <c:forEach items="${commentList}" var="comment">
				            <li class="list-group-item">
				                <div class="d-flex justify-content-between">
				                    <div class="comment-content">${comment.writerId} : ${comment.content}</div>
				                    <div class="comment-info">
										<c:if test="${comment.writerId eq principalDetails.id}">
										    <button type="button" class="btn btn-secondary" onclick="deleteComment(${comment.id}, ${response.id})">삭제</button>
										</c:if>
				                        ${fn:substring(comment.regDate, 5, 10)} : ${fn:substring(comment.regDate, 11, 16)}
				                    </div>
				                </div>
				            </li>
				        </c:forEach>
				    </ul>
				</div>
		<!-- 댓글 목록 폼 끝 -->


<script>
    /* AJAX 요청 */
   function deleteComment(commentId, photoFeedId) {
    if (confirm("댓글을 삭제하시겠습니까?")) {
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/commentDelete",
            data: {
                comment: commentId,
                photoFeedId: photoFeedId
            },
            success: function(response) {
                location.reload(); 
            },
            error: function(error) {
                console.error("댓글 삭제 실패:", error);
            }
        });
    }
}

</script>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>