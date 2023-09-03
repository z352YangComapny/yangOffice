<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/member.css"/>

<style>
    /* 스타일링 기본 설정 */
    body {
        font-family: Arial, sans-serif;
    }

    .photos {
        border-radius: 5%;
    }

    .content-box {
        width: 30vw;
        margin-top: 3vw;
        margin-left: 12vw;
        overflow-y: auto;
    }

    /* 이미지 크기 및 정렬 조절 */
    body .carousel-inner {
        border-radius: 5%;
        width: 20vw;
        margin-left: 12vw;
        border: 1px solid #585757;
    }

    .carousel-control-prev {
        margin-left: 10vw;
    }

    .carousel-control-next {
        margin-right: 6vw;
    }

    .carousel-item img {
        width: 20vw;
        height: 20vw;
    }


    .goBackBtn {
        margin-left: 15vw;
        margin-top: 2.5vw;
    }
    .feedDelete-box{
    	margin-left:75vw;
    }
	.feedUpdate-box{
	}
    .likes-btn {
        width: 50px;
        heght: 50px;
        margin-top: 27vw;
    }

    .likes-box {
       /*  position: absolute; */
        right: 30vw;
    }

    .comment-list {
        max-height: 18vw;
        overflow-y: auto;
        scrollbar-color: #c0c0c0 #f0f0f0;
        scrollbar-width: thin;
    }

    /* Firefox용 스크롤바 색상 설정 */
    .comment-list::-webkit-scrollbar {
        width: 5px;
    }

    .comment-list::-webkit-scrollbar-thumb {
        background-color: #c0c0c0;
        border-radius: 3px;
    }

    .feedReport-box{
        margin-left: 55vw;
    }

    #likes {
        width: 60px;
        height: 60px;
    }


      .btns {
        display: flex;
        flex-wrap:wrap;
    } 
    
    .FeedBox {
        width: 95vw;
        height: 30vw;
        display: flex;
        margin-bottom: 10vw;
    }

    .photoBox {
        width: 45vw;
        height: 20vw;
        margin-left: 2vw;
    }


    .commentsAll {
        width: 45vw;
        height: 30vw;
        margin-left: 7vw;
        margin-right : 10vw;
    }

    .edit-feed-form {
        display: flex;
    }
    textarea {
        width: 100%;
        height: 6.25em;
        border: none;
        resize: none;
    }

</style>
<script>
    $(document).ready(function () {
        // 'feed report' 버튼 클릭 시 모달 창 열기
        $(".btn-toggle").click(function () {
            var feedId = $(this).data("feed-id");
            var reportedId = $(this).data("reported-id");
            var reporterId = $(this).data("repoter-id");

            // 모달 창 열기
            $("#feedReportModal").modal("show");

            // '신고' 버튼 클릭 시 AJAX 요청 전송
            $("#confirmReportButton").click(function () {
                var content = $("#reportContent").val();

                // AJAX 요청 보내는 부분
                $.ajax({
                    method: "POST",
                    url: "${pageContext.request.contextPath}/member/userPage/${id}/insertReportFeed",
                    data: {
                        feedId: feedId,
                        reportedId: reportedId,
                        reporterId: reporterId,
                        content: content
                    },
                    beforeSend: function (xhr) {
                        xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
                    },
                    success: function (response) {
                        alert("신고가 접수되었습니다.");
                        $("#feedReportModal").modal("hide");
                    },
                    error: function (error) {
                        alert("Error reporting: " + error.responseText);
                    }
                });
            });

            // 취소 버튼 클릭 시 모달 창 닫기
            $("#cancelModalButton").click(function () {
                $("#feedReportModal").modal("hide");
            });

            // X 버튼 클릭 시 모달 창 닫기
            $("#closeModalButton").click(function () {
                $("#feedReportModal").modal("hide");
            });
        });
    });

    // 댓글 신고 모달 창 열기
    function goReportComments(commentsId, reportedId, feedId) {
        var reporterId = ${principalDetails.id};

        // 모달 창 열기
        $("#commentReportModal").modal("show");

        // '신고' 버튼 클릭 시 AJAX 요청 전송
        $("#commentconfirmReportButton").click(function () {
            var content = $("#commentreportContent").val();

            // AJAX 요청 보내는 부분
            $.ajax({
                method: "POST",
                url: "${pageContext.request.contextPath}/member/userPage/${id}/insertReportComment",
                data: {
                    commentsId: commentsId,
                    reportedId: reportedId,
                    reporterId: reporterId,
                    feedId: feedId,
                    content: content
                },
                beforeSend: function (xhr) {
                    xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
                },
                success: function (response) {
                    alert("신고가 접수되었습니다.");
                    $("#commentReportModal").modal("hide"); // 모달 창 닫기
                },
                error: function (error) {
                    alert("Error reporting comment: " + error.responseText);
                }
            });
        });

        // 취소 버튼 클릭 시 모달 창 닫기
        $("#cancelModalButton").click(function () {
            $("#commentReportModal").modal("hide");
        });

        // X 버튼 클릭 시 모달 창 닫기
        $("#closeModalButton").click(function () {
            $("#commentReportModal").modal("hide");
        });
    }
</script>


<div class="modal fade" id="feedReportModal" tabindex="-1" role="dialog" aria-labelledby="feedReportModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="feedReportModalLabel">피드 신고</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true" id="cancelModalButton">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="reportForm">
                    <div class="form-group">
                        <label for="reportReason">신고 사유</label>
                        <select class="form-control" id="reportReason" name="reportReason">
                            <option value="inappropriate">불건전한 내용</option>
                            <option value="spam">스팸</option>
                            <option value="harassment">괴롭힘</option>
                            <!-- 추가적인 신고 사유를 여기에 추가 가능 -->
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="reportContentz">신고 내용</label>
                        <textarea class="form-control" id="reportContent" name="reportContent" rows="3"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" id="cancelModalButton">취소</button>
                <button type="button" class="btn btn-primary" id="confirmReportButton">신고</button>
            </div>
        </div>
    </div>
</div>

<%-- 댓글 asdkljasdjkasdjkasndjkasnjkdnsjkdnasjkdnsakjdnksjdnjksandjkasndkjsandkjasndkjasndjkasndkjasndkasndkjsan--%>
<div class="modal fade" id="commentReportModal" tabindex="-1" role="dialog" aria-labelledby="commentReportModal"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="commentReportModalLabel">댓글 신고</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true" id="closeModalButton">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="commentreportForm">
                    <div class="form-group">
                        <label for="reportReason">신고 사유</label>
                        <select class="form-control" id="commentreportReason" name="commentreportReason">
                            <option value="inappropriate">불건전한 내용</option>
                            <option value="spam">스팸</option>
                            <option value="harassment">괴롭힘</option>
                            <!-- 추가적인 신고 사유를 여기에 추가 가능 -->
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="comment-reportContent">신고 내용</label>
                        <textarea class="form-control" id="commentreportContent" name="commentreportContent"
                                  rows="3"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" id="closeModalButton">취소</button>
                <button type="button" class="btn btn-primary" id="commentconfirmReportButton">신고</button>
            </div>
        </div>
    </div>
</div>



<div class="btns">
	<form:form name="goBackBtn"
	           method="get"
	           action="${pageContext.request.contextPath}/member/userPage/${id}/goBackPage">
	    <div class="goBackBtn">
	        <button class="btn btn-primary">뒤로가기</button>
	    </div>
	</form:form>
    <!-- feed delete -->
    <c:if test="${response.writerId eq principalDetails.id}">
        <div class="feedDelete-box">
            <form:form action="${pageContext.request.contextPath}/member/userPage/${id}/feedDetails/feedDelete"
                       method="post" id="deleteFeedForm">
                <input type="hidden" name="feedId" value="${response.id}">
                <button type="button" class="btn btn-outline-danger" onclick="confirmDeleteFeed()" style="margin-right: 10px;">
                    피드 삭제
                </button>
            </form:form>
        </div>
    </c:if>

    <%--  feed update button--%>
    <c:if test="${response.writerId eq principalDetails.id }">
        <div class="feedUpdate-box">
            <button class="btn btn-outline-secondary edit-feed-btn" data-feed-id="${response.id}">피드 수정</button>
        </div>
    </c:if>

    <%-- feed report --%>
    <c:if test="${response.writerId ne principalDetails.id}">
        <div class="feedReport-box">
            <button class="btn btn-sm btn-light btn-toggle"
                    style="margin-left: 10px; font-size:20px;"
                    data-feed-id="${response.id}" data-reported-id="${response.writerId}"
                    data-repoter-id="${principalDetails.id}">
                🚨 신고
            </button>
        </div>
    </c:if>

</div>

<div class="FeedBox">
    <div class="photoBox">
        <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
            <div class="carousel-indicators" style="margin-bottom: -38px; margin-left: 11vw;">
                <c:forEach items="${photoDetail}" var="photo" varStatus="status">
                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="${status.index}"
                            class="bg-dark ${status.first ? 'active' : ''}"
                            aria-current="${status.first ? 'true' : 'false'}"
                            aria-label="Slide ${status.index + 1}"></button>
                </c:forEach>
            </div>
            <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-bs-slide="prev">
	                <span class="bg-dark carousel-control-prev-icon" aria-hidden="true"
                          style="border-radius: 50%;"></span>
                <span class="visually-hidden">Previous</span>
            </a>
            <div class="carousel-inner ">
                <c:forEach items="${photoDetail}" var="photo" varStatus="status">
                    <div class="carousel-item ${status.first ? 'active' : ''}">
                        <img class="photos"
                             src="${pageContext.request.contextPath}/resources/upload/attachment/${photo.renamedFilename}"
                             alt="Image ${status.index + 1}">
                    </div>
                </c:forEach>
            </div>

            <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-bs-slide="next">
	                <span class="bg-dark carousel-control-next-icon" aria-hidden="true"
                          style="border-radius: 50%;"></span>
                <span class="visually-hidden">Next</span>
            </a>
        </div>
        <%--  feed content  --%>
        <div class="content-box">
    	
    	<div class="likes-box" style="display: flex; align-items: center;">
		    <form:form action="${pageContext.request.contextPath}/member/userPage/${id}/feedDetails/feedLikeUpdate"
		               method="post" id="likeForm">
		        <input type="hidden" name="feedId" value="${response.id}">
		        <input type="hidden" name="memberId" value="${principalDetails.id}">
		        <a href="#" id="likes-btn" onclick="submitLikeForm();" style="margin-right: 10px;">
		            <img id="likes" src="${pageContext.request.contextPath}/resources/images/favorite.png">
		        </a>
		    </form:form>
		    <!-- 좋아요 수를 ${response.likeCount}로 변경 -->
		    <span style="font-size: 30px;">${response.likeCount}</span>
		</div>
    	
            <div class="feedContent-box form-control">
                ${response.content}</div>
            <%--    feed update form   --%>
            <div class="edit-feed-form" id="edit-feed-form-${response.id}" style="display: none;">
                <textarea class="form-control">${response.content}</textarea>
                <button class="btn btn-primary update-feed-btn" data-feed-id="${response.id}"id="updateOk">수정  ❤️ 완료️️</button>
            </div>
        </div>


    </div>

    
    <!-- 댓글 작성 폼 시작 -->
    <div class="commentsAll">
        <p style="font-size: 30px;
        background: linear-gradient(to right, #F3969A, #78C2AD);
        -webkit-background-clip: text;
    	-webkit-text-fill-color: transparent;">@${response.nickName}</p>
        <div class="comment-form">
            <form:form
                    action="${pageContext.request.contextPath}/member/userPage/${id}/feedDetails/commentCreate"
                    method="post">
                <div class="input-group mb-3">
                    <input class="form-control" id="comment" name="comment" rows="3" required
                           placeholder="댓글을 입력하세요..."/>
                    <input type="hidden" name="photoFeedId" value="${response.id}">
                    <input type="hidden" name="returnUrl" value="${requestScope.requestURL}">
                    <button type="submit" class="btn btn-primary">댓글 작성</button>
                </div>
            </form:form>
        </div>
        <!-- 댓글 작성 폼 끝 -->

        <!-- 댓글 목록 폼 시작 -->
        <div class="comment-list">
            <ul class="list-group list-group-flush">
                <c:forEach items="${commentList}" var="comment">
                    <li class="list-group-item ">
                        <div class="d-flex justify-content-between">
                            <div class="comment-content" id="comment-${comment.id}">
                                    ${comment.nickName} : <span class="comment-text">${comment.content}</span>
                            </div>
                            <div class="comment-info" style="display: flex;">
                                <c:if test="${comment.writerId ne principalDetails.id}">
                                    <button class="btn btn-sm btn-light"
                                            style="margin-left: 10px; font-size:20px;"
                                            onclick="goReportComments(${comment.id}, ${comment.writerId}, ${response.id});">
                                        🚨
                                    </button>
                                </c:if>
                                <c:if test="${comment.writerId eq principalDetails.id || response.writerId eq principalDetails.id}">
                                    <form:form
                                            action="${pageContext.request.contextPath}/member/userPage/${id}/feedDetails/commentDelete"
                                            method="post" id="deleteCommentForm-${comment.id}">
                                        <input type="hidden" name="commentId" id="commentId" value="${comment.id}">
                                        <input type="hidden" name="photoFeedId" id="photoFeedId" value="${response.id}">
                                        <a href="#" onclick="confirmDeleteComment(${comment.id});"><img src="${pageContext.request.contextPath}/resources/images/delete.png" style="width: 40px;"/></a>
                                    </form:form>
                                </c:if>
                                <!-- Edit button -->
                                <c:if test="${comment.writerId eq principalDetails.id}">
                                <a href="#" class="edit-comment-btn" data-comment-id="${comment.id}">
                                <img src="${pageContext.request.contextPath}/resources/images/edit-button.png" style="width: 40px;"/>
                                </a>
                                </c:if>
                                    ${fn:substring(comment.regDate, 5, 10)} : ${fn:substring(comment.regDate, 11, 16)}
                            </div>
                        </div>
                        <div class="edit-comment-form input-group mb-3" id="edit-comment-form-${comment.id}" style="display: none;">
                            <input class="form-control" rows="3" value="${comment.content}"/>
                            <button class="btn btn-secondary update-comment-btn" data-comment-id="${comment.id}">수정</button>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <!-- 댓글 목록 폼 끝 -->

    
</div>


<hr style="border: 3px">

<script>
    $(document).ready(function () {
        // 좋아요 버튼 클릭 시 (비동기 요청)
        $(".feed-like-button").click(function () {
            var feedId = $(this).data("feed-id");
            var memberId = ${principalDetails.id}; // PrincipalDetails 객체에서 ID 가져오기

            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/feedDetails/feedLikeUpdate",
                data: {
                    feedId: feedId,
                    memberId: memberId
                },
                beforeSend: function (xhr) {
                    xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}')
                },
                success: function (response) {
                    alert(response);
                    location.reload();
                },
                error: function (error) {
                    alert("Error updating like: " + error.responseText);
                }
            });
        });


        // 피드 수정 버튼 클릭 시
        $(".edit-feed-btn").click(function () {
            var feedId = $(this).data("feed-id");
            $("#edit-feed-form-" + feedId).toggle();
        });

        // 수정 완료 버튼 클릭 시
        $(".update-feed-btn").click(function () {
            var feedId = $(this).data("feed-id");
            var newContent = $("#edit-feed-form-" + feedId + " textarea").val();

            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/member/userPage/${id}/feedDetails/feedUpdate",
                data: {
                    feedId: feedId,
                    content: newContent
                },
                beforeSend: function (xhr) {
                    xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}')
                },
                success: function (response) {
                    alert("피드가 변경이 완료되었습니다!");
                    location.reload();
                },
                error: function (error) {
                    alert("Error updating feed: " + error.responseText);
                }
            });
        });

        // Edit button 클릭 시
        $(".edit-comment-btn").click(function () {
            var commentId = $(this).data("comment-id");
            $("#edit-comment-form-" + commentId).toggle();
        });

        // 확인 버튼 클릭 시
        $(".update-comment-btn").click(function () {
            var commentId = $(this).data("comment-id");
            var newContent = $("#edit-comment-form-" + commentId + " input").val();

            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/member/userPage/${id}/feedDetails/commentUpdate",
                data: {
                    commentId: commentId,
                    content: newContent
                },
                beforeSend: function (xhr) {
                    xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}')
                },
                success: function (response) {
                    alert(response);
                    location.reload();
                },
                error: function (error) {
                    alert("Error updating comment: " + error.responseText);
                }
            });
        });
    });

    function submitLikeForm() {
        document.getElementById("likeForm").submit();
    }

    function confirmDeleteFeed() {
        if (confirm("피드를 삭제하시겠습니까?")) {
            document.getElementById("deleteFeedForm").submit();
        }
    }

    function confirmDeleteComment(commentId) {
        if (confirm("댓글을 삭제하시겠습니까?")) {
            document.getElementById("deleteCommentForm-" + commentId).submit();
        }
    }
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>