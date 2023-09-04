<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.1/dist/minty/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<fmt:requestEncoding value="utf-8"/> <!-- 한글로 제목을 변경할 경우에는 인코딩이 깨질 수 있으니 해당 설정 잡아주기 -->
<style>
    #guestBookTitle{
        font-family:"DungGeunMo";
        font-size: 40px; font-weight: bold;
        margin-left: 15px;
        background: linear-gradient(to right, #F3969A, #78C2AD);
        -webkit-background-clip: text;
        -webkit-text-fill-color: transparent;}
</style>
<sec:authorize access="isAuthenticated()">
    <jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="d-flex flex-column justify-content-center align-item-center mb-2" id="userPageContainer">
    <div class="d-flex flex-column m-1 p-3">
        <div id="story" class="flex-grow-1" style="height : 28vh; margin-bottom : 10px; border-radius: 10px; box-shadow: 3px 3px 10px 5px rgba(120, 194, 173, 0.3);">
            <a href="${pageContext.request.contextPath}/story/storyTap" id="storyTapButton"
               style="font-size: 40px; font-weight: bold; margin-left: 2vw;
		        background: linear-gradient(to right, #F3969A, #78C2AD);
		        -webkit-background-clip: text;
		    	-webkit-text-fill-color: transparent;">STORY</a>
            <jsp:include page="/WEB-INF/views/story/storyMain.jsp"/>
        </div>
        <div class="d-flex flex-row" style="height: 70vh;">

            <div class ="d-flex justify-content-center" id="profile" style="width: 25vw; margin-top : 10px;  border-radius: 10px; box-shadow: 3px 3px 10px 5px rgba(120, 194, 173, 0.3);">
                <div id="profileMainContainer" class="mr-3">
                    <jsp:include page="/WEB-INF/views/profile/profileMain.jsp"/>
                </div>
            </div>
            <div class="d-flex justify-content-center flex-column" id="member_content" style="width: 65vw; margin-left : 20px;">
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
                        <div class="d-flex flex-row justify-content-between align-content-center flex-grow-1" style="">
                            <div class="mainGif">
                                <img src="${pageContext.request.contextPath}/resources/images/메인움짤구름.gif" id="guestbook-gif" style="margin-left: 13vw; border: none; box-shadow: 0 0 20px 10px rgba(195, 237, 255, 0.4); border-radius: 90px; height: 210px; margin-top: 0.6vw;"/>
                            </div>
                            <div style="margin-top: 3vw;">
                            <span id="guestBookTitle">GUEST BOOK</span>
                            <a href="${pageContext.request.contextPath}/member/userPage/${member.id}/guestbook/guestbook">
                                <img src="${pageContext.request.contextPath}/resources/images/wallet-symbol.png" id="guestbook-image" alt="guestbook-image" style="width:100px;"/>
                            </a>
                            </div>
                        </div>
                    <div id="photoFeed" class="flex-grow-1" data-mdb-perfect-scrollbar="true"
                         style="position: relative; height: 600px; overflow-y: auto; border-radius: 10px; box-shadow: 3px 3px 10px 5px rgba(120, 194, 173, 0.3); padding: 10px;">
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
