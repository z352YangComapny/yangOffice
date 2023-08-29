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

    /* 컨테이너 스타일 조절 */
    .carousel-and-content {
        display: flex;
        align-items: center;
        justify-content: center;
    }

    .feedContent-box {
        width: 30vw;
        height: 30vw;
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
        height: 25vw;
        width: 30vw;
    }

    .likes-box {
        margin-top: 30vw;
    }

    #likes {
        width: 30px;
        height: 30px;
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
        });
    });

    //     comments report start
    // '댓글 신고' 버튼 클릭 시
    function goReportComments(commentsId, reportedId, feedId) {
        var reporterId = ${principalDetails.id};

        // 모달 창 열기
        $("#commentReportModal").modal("show");

        // '신고' 버튼 클릭 시 AJAX 요청 전송
        $("#confirmReportButton").click(function () {
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
                    $("#commentReportModal").modal("hide");
                },
                error: function (error) {
                    alert("Error reporting comment: " + error.responseText);
                }
            });
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
                    <span aria-hidden="true">&times;</span>
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
                <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
                <button type="button" class="btn btn-primary" id="commentconfirmReportButton">신고</button>
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
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="reportForm">
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
                <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
                <button type="button" class="btn btn-primary" id="confirmReportButton">신고</button>
            </div>
        </div>
    </div>
</div>

<form:form name="goBackBtn"
           method="get"
           action="${pageContext.request.contextPath}/member/userPage/${id}/goBackPage">
    <button class="btn btn-primary">뒤로가기</button>
</form:form>
<hr style=" height: 3px
">
<div class="carousel-and-content">
    <div class="carousel-box">
        <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
            <div class="carousel-indicators">
                <c:forEach items="${photoDetail}" var="photo" varStatus="status">
                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="${status.index}"
                            class="${status.first ? 'active' : ''}" aria-current="${status.first ? 'true' : 'false'}"
                            aria-label="Slide ${status.index + 1}"></button>
                </c:forEach>
            </div>
            <div class="carousel-inner">
                <c:forEach items="${photoDetail}" var="photo" varStatus="status">
                    <div class="carousel-item ${status.first ? 'active' : ''}">
                        <img src="${pageContext.request.contextPath}/resources/upload/attachment/${photo.renamedFilename}"
                             class="d-block" alt="Image ${status.index + 1}">
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


    <!-- feed delete  -->
    <c:if test="${response.writerId eq principalDetails.id}">
        <div class="feedDelete-box">
            <form:form action="${pageContext.request.contextPath}/member/userPage/${id}/feedDetails/feedDelete"
                       method="post" id="deleteFeedForm">
                <input type="hidden" name="feedId" value="${response.id}">
                <button type="button" class="btn btn-danger" onclick="confirmDeleteFeed()">피드 삭제</button>
            </form:form>
        </div>
    </c:if>

    <%--  feed update button--%>
    <c:if test="${response.writerId eq principalDetails.id }">
        <div class="feedUpdate-box">
            <button class="btn btn-secondary edit-feed-btn" data-feed-id="${response.id}">피드 수정</button>
        </div>
    </c:if>

    <%--  feed content  --%>
    <div class="content-box">

        <div class="feedContent-box">${response.content}</div>

        <%--    feed update form   --%>
        <div class="edit-feed-form" id="edit-feed-form-${response.id}" style="display: none;">
            <textarea class="form-control">${response.content}</textarea>
            <button class="btn btn-primary update-feed-btn" data-feed-id="${response.id}">수정 완료</button>
        </div>
    </div>


    <div class="likes-box">
        <form:form action="${pageContext.request.contextPath}/member/userPage/${id}/feedDetails/feedLikeUpdate"
                   method="post">
            <input type="hidden" name="feedId" value="${response.id}">
            <input type="hidden" name="memberId" value="${principalDetails.id}">
            <button type="submit">
                <img id="likes" src="${pageContext.request.contextPath}/resources/images/like.png">
                <!-- 좋아요 수를 ${response.likeCount}로 변경 -->
                <div>${response.likeCount}</div>
            </button>
        </form:form>
    </div>

</div>
<hr style="border: 3px">
<!-- 댓글 작성 폼 시작 -->
<div class="comment-form">
    <form:form
            action="${pageContext.request.contextPath}/member/userPage/${id}/feedDetails/commentCreate"
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
                    <div class="comment-content" id="comment-${comment.id}">
                            ${comment.nickName} : <span class="comment-text">${comment.content}</span>
                    </div>
                    <div class="comment-info">
                        <c:if test="${comment.writerId ne principalDetails.id}">
                            <button class="btn btn-sm btn-light"
                                    style="margin-left: 10px; font-size:20px;"
                                    onclick="goReportComments(${comment.id}, ${comment.writerId}, ${response.id});">🚨
                            </button>
                        </c:if>
                        <c:if test="${comment.writerId eq principalDetails.id || response.writerId eq principalDetails.id}">
                            <form:form
                                    action="${pageContext.request.contextPath}/member/userPage/${id}/feedDetails/commentDelete"
                                    method="post" id="deleteCommentForm-${comment.id}">
                                <input type="hidden" name="commentId" id="commentId" value="${comment.id}">
                                <input type="hidden" name="photoFeedId" id="photoFeedId" value="${response.id}">
                                <button type="button" class="btn btn-secondary"
                                        onclick="confirmDeleteComment(${comment.id})">삭제
                                </button>
                            </form:form>
                        </c:if>
                        <!-- Edit button -->
                        <c:if test="${comment.writerId eq principalDetails.id}">
                            <button class="btn btn-secondary edit-comment-btn" data-comment-id="${comment.id}">수정
                            </button>
                        </c:if>
                            ${fn:substring(comment.regDate, 5, 10)} : ${fn:substring(comment.regDate, 11, 16)}
                    </div>
                </div>
                <div class="edit-comment-form" id="edit-comment-form-${comment.id}" style="display: none;">
                    <textarea class="form-control" rows="3">${comment.content}</textarea>
                    <button class="btn btn-primary update-comment-btn" data-comment-id="${comment.id}">확인</button>
                </div>
            </li>
        </c:forEach>
    </ul>
</div>

<!-- 댓글 목록 폼 끝 -->
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
            var newContent = $("#edit-comment-form-" + commentId + " textarea").val();

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