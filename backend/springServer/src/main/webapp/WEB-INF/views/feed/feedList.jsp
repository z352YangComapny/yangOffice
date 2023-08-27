<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:requestEncoding value="utf-8"/>

<style>
    .feed-container {
        display: flex;
        flex-wrap: wrap;
        justify-content: flex-start;
    }

    .feed-item {
        position: relative;
        margin: 5px;
        padding: 0;
        border: none;
        text-align: center;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }

    .feed-img {
        width: 100%;
        height: 200px;
        object-fit: contain;
    }

    .feed-item-right {
        margin-left: auto;
    }

    .plus-icon {
        position: absolute;
        bottom: 0;
        left: 0;
        background-color: rgba(0, 0, 0, 0.5);
        color: white;
        padding: 5px;
        font-size: 12px;
    }
</style>


<div class="container">
    <h1>피드</h1>
    <c:if test="${id eq PrincipalDetails.id}">
        <a href="${pageContext.request.contextPath}/member/userPage/${PrincipalDetails.id}/feedCreate"
           class="btn btn-primary">피드작성하기</a>
    </c:if>

    <sec:authorize access="isAuthenticated()">
        <c:choose>
            <c:when test="${not empty photoList}">
                <div class="feed-container">
                    <c:forEach items="${photoList}" var="photo">
                        <div class="feed-item">
                            <c:choose>
                                <c:when test="${not empty photo.attachments}">
                                    <a href="${pageContext.request.contextPath}/member/userPage/${photo.writerId}/feed/feedDetail?photoFeedId=${photo.id}">
                                        <img src="${pageContext.request.contextPath}/resources/upload/attachment/${photo.attachments[0].renamedFilename}"
                                             class="feed-img" alt="Image 1">
                                    </a>
                                </c:when>
                            </c:choose>

                            <c:if test="${fn:length(photo.attachments) > 1}">
                                <div class="plus-icon">+</div>
                                <!-- 피드에 2개 이상 있으면 나타나는 + -->
                            </c:if>
                            <div class="feed-img-overlay">
                                <p>좋아요 개수: ${photo.likeCount}</p>
                                <p>댓글 수: ${photo.commentCount}</p>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:when>
            <c:otherwise>
                <p>작성된 피드가 없습니다.</p>
            </c:otherwise>
        </c:choose>

    </sec:authorize>
</div>



