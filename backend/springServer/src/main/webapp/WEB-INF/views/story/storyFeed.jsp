<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
   <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
   <!-- bootstrap js: jquery load 이후에 작성할것.-->
   <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
   <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
   <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.1/dist/minty/bootstrap.min.css">
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
</style>


<div class="container">
<input type="hidden" id="choosedFeed"/>
    <sec:authorize access="isAuthenticated()">
        <c:choose>
            <c:when test="${not empty photoList}">
                <div class="feed-container">
                    <c:forEach items="${photoList}" var="photo">
                        <div class="feed-item m-3" id="photoFeeds">
                            <c:choose>
                                <c:when test="${not empty photo.attachments}">
                                        <img src="${pageContext.request.contextPath}/resources/upload/attachment/${photo.attachments[0].renamedFilename}"
                                             class="feed-img" alt="Image 1" style="margin: 10px;">
                                    <input type="hidden" id="photoAddr" value="${pageContext.request.contextPath}/member/userPage/${photo.writerId}/feed/feedDetail?photoFeedId=${photo.id}"/>
                                </c:when>
                            </c:choose>
                            <c:set var="attachmentCount" value="${fn:length(photo.attachments)}"/>
                            <c:if test="${fn:length(photo.attachments) > 1}">
                                <div class="plus-icon">${attachmentCount}+</div>
                                <!-- 피드에 2개 이상 있으면 나타나는 + -->
                            </c:if>
                        </div>
                    </c:forEach>
                </div>
            </c:when>
            <c:otherwise>
                <p>작성된 피드가 없습니다.</p>
            </c:otherwise>
        </c:choose>
	<div >
		<button type="button" class="btn btn-primary">확인</button>
		<button type="button" class="btn btn-secondary">닫기</button>
	</div>
    </sec:authorize>
</div>

<script>
document.addEventListener('DOMContentLoaded', () => {
	const storyFeeds = document.querySelectorAll('#photoFeeds');
	
    storyFeeds.forEach((feed) => {
        feed.addEventListener('click', () => {
			feed.style.border = "2px solid #78C2AD";
			console.log('feeddddddddddddddddddd');
        });
	    
    });
});
</script>

