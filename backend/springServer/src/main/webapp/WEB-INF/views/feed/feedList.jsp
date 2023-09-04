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
        border-radius: 6%;
        text-align: center;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.4);
    }

    .feed-img {
        width: 200px;
        height: 200px;
        object-fit: contain;
    }


    .plus-icon {
        position: absolute;
        bottom: 3px;
        left: 5px;
        background-color: rgb(0 0 0 / 23%);
        color: white;
        padding: 5px;
        font-size: 12px;
        border-radius: 20%;
        width: 30px;
    }
    #feedTitle{
        font-family:"DungGeunMo";
        font-size: 40px; font-weight: bold;
        margin-left: 15px;
        background: linear-gradient(to right, #F3969A, #78C2AD);
        -webkit-background-clip: text;
        -webkit-text-fill-color: transparent;}
</style>


<div class="container">
    <div class="d-flex flex-row justify-content-between">
        <span id="feedTitle">PHOTO FEED</span>
        <div class="mt-4">
            <c:if test="${id eq PrincipalDetails.id}">
                <a href="${pageContext.request.contextPath}/member/userPage/${PrincipalDetails.id}/feedCreate"
                   class="btn btn-outline-primary btn-sm" style="height:30px;">피드 만들기</a>
            </c:if>
        </div>
    </div>
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
                                             class="feed-img" alt="Image 1" style="margin: 10px;">
                                    </a>
                                </c:when>
                            </c:choose>
                            <c:set var="attachmentCount" value="${fn:length(photo.attachments)}"/>
                            <c:if test="${fn:length(photo.attachments) > 1}">
                                <div class="plus-icon">${attachmentCount}+</div>
                                <!-- 피드에 2개 이상 있으면 나타나는 + -->
                            </c:if>
                            <div class="feed-img-overlay">
                                <p style="margin: 5px;">❤️ ${photo.likeCount}</p>
                                <p>💬 ${photo.commentCount}</p>
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



