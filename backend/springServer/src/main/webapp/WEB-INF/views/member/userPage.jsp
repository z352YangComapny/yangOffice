<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:requestEncoding value="utf-8"/> <!-- 한글로 제목을 변경할 경우에는 인코딩이 깨질 수 있으니 해당 설정 잡아주기 -->

<sec:authorize access="isAuthenticated()">
    <jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="d-flex flex-column justify-content-center align-item-center" id="userPageContainer">
    <div class="d-flex flex-column m-1 p-3">
        <div id="story" class="flex-grow-1" style="height : 25vh; margin : 0;">
            <a href="${pageContext.request.contextPath}/story/storyTap" id="storyTapButton"
               style="font-size: 40px; font-weight: bold; margin-left: 2vw;
		        background: linear-gradient(to right, #F3969A, #78C2AD);
		        -webkit-background-clip: text;
		    	-webkit-text-fill-color: transparent;">STORY</a>
            <jsp:include page="/WEB-INF/views/story/storyMain.jsp"/>
        </div>
        <div class="d-flex flex-row" style="height: 70vh;">

            <div class ="" id="profile" style="width: 30vw; margin-left: 2vw;">
               <jsp:include page="/WEB-INF/views/profile/profileMain.jsp"/>
            </div>
            <div class="d-flex justify-content-center flex-column" id="member_content" style="width: 70vw; margin:0 0;">
                    <%--<div class="d-flex justify-content-center column align-items-center">
                        <div id ="dm"  class="flex-grow-1" style="height: 10vh; margin: 0; display: flex; align-items: center; justify-content: flex-end;">
                            <div id="notification-div"> </div>
                            <input type='hidden' id='userId' value='${dmMember.id}' />
                            <a href="${pageContext.request.contextPath}/dm/dmList">
                                <img src="${pageContext.request.contextPath}/resources/images/dm-pixel-logo.png" id="dm-image" alt="dm-img" style="width: 140px;"/>
                            </a>
                        </div>
                    </div>--%>
                    <%--<div id="story"  class="flex-grow-1" style="height : 20vh; margin : 0;">
                        <a href="${pageContext.request.contextPath}/story/storyTap" id="storyTapButton" style="color: MediumSeaGreen; font-size: 40px; font-family: 'Kalam', cursive; text-decoration: none;">Story</a>
                        <jsp:include page="/WEB-INF/views/story/storyMain.jsp"/>
                    </div>--%>
                <div class="d-flex justify-content-end align-content-center flex-grow-1" style="margin :0;">
                    <a href="${pageContext.request.contextPath}/member/userPage/${member.id}/guestbook/guestbook">
                        <img src="${pageContext.request.contextPath}/resources/images/wallet-symbol.png" id="guestbook-image" alt="guestbook-image" />
                     </a>
                </div>
                <div id="photoFeed" class="flex-grow-1" data-mdb-perfect-scrollbar="true"
                     style="position: relative; height: 600px; overflow-y: auto;">
                    <jsp:include page="/WEB-INF/views/feed/feedList.jsp"/>
                </div>

            </div>
        </div>
    </div>
   </div>
</sec:authorize>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>

<script>
    <c:choose>
    <c:when test="${not empty dmMember}">
    const userId = ${dmMember.id}; // 인증된 멤버의 ID를 가져옵니다.
    document.addEventListener('DOMContentLoaded', () => {
        notifyConnect(userId);
    });
    </c:when>
    <c:otherwise>
    console.log("로그인되지 않았습니다. DM 알림을 구독하지 않습니다.");
    </c:otherwise>
    </c:choose>

</script>
