<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Kalam&display=swap" rel="stylesheet">

<fmt:requestEncoding value = "utf-8"/> <!-- 한글로 제목을 변경할 경우에는 인코딩이 깨질 수 있으니 해당 설정 잡아주기 -->
<jsp:include page ="/WEB-INF/views/common/header2.jsp">
    <jsp:param name = "title" value = "안녕 스프링"/>
</jsp:include>
<style>
@keyframes waveAnimation {
  0%, 100% { transform: translateX(0); }
  25% { transform: translateX(20px); }
  75% { transform: translateX(-20px); }
}

.wave-image {
  animation: waveAnimation 2s cubic-bezier(0.97, 0.71, 1, 1) infinite;
}
</style>
    <div class="index-container d-flex flex-column justify-content-center align-items-center" style="background-image: url('${pageContext.request.contextPath}/resources/images/index_bgc.jpg');">
     <img src="${pageContext.request.contextPath}/resources/images/main.png" id="main-image" alt="main-img" class="wave-image"/>
        <div class=" d-flex justify-content-center align-items-center row">
            <span style="font-weight : bold; font-size : 100px; text-align: center; color : black;">SSOY STORY</span>
            <span style="font-size : 30px; text-align: center; color: black">Yang Company</span>
            <button type="button" class="index-button btn btn-primary mt-4" onclick="location.href='${pageContext.request.contextPath}/member/memberLogin.do';">
                <span class="btn-index">로그인</span></button>
            &nbsp;&nbsp;
            <button type="button" class="index-button btn btn-primary mt-4" onclick="location.href='${pageContext.request.contextPath}/member/memberCreate.do';">
                <span class="btn-index">회원가입</span></button>
        </div>
    </div>

<sec:authorize access = "isAuthenticated()">
<sec:authentication property="principal" var="dmMember"/>
    <jsp:include page ="/WEB-INF/views/common/header.jsp">
        <jsp:param name = "title" value = "안녕 스프링"/>
    </jsp:include>
    <div class="d-flex flex-row">
	        <div class ="" id="profile" style="width: 30vw; height: 80vh; margin : 0 0;">
	        	<jsp:include page="/WEB-INF/views/profile/profileMain.jsp"/>
	         </div>
	         
	        <div class="d-flex justify-content-center row" id="member_content" style="width: 70vw; margin:0 0;">
            <div id ="dm"  class="flex-grow-1" style="height: 10vh; margin: 0; display: flex; align-items: center; justify-content: flex-end;">
            		<div id="notification-div"> </div>
            		<input type='hidden' id='userId' value='${dmMember.id}' />
            	 <a href="${pageContext.request.contextPath}/dm/dmList">
			        <img src="${pageContext.request.contextPath}/resources/images/dm-pixel-logo.png" id="dm-image" alt="dm-img" style="width: 140px;"/>
			    </a>
            </div>
            <div id="story"  class="flex-grow-1" style="height : 20vh; margin : 0;">
            	<a href="${pageContext.request.contextPath}/story/storyTap" id="storyTapButton" style="color: MediumSeaGreen; font-size: 40px; font-family: 'Kalam', cursive; text-decoration: none;">Story</a>
            	<jsp:include page="/WEB-INF/views/story/storyMain.jsp"/>
            </div>
            <div id="photoFeed" class="flex-grow-1" style="border: 1px solid #000; height: 40vh; margin : 0;">
                <jsp:include page="/WEB-INF/views/feed/feedList.jsp"/>
            </div>
            <div class="d-flex justify-content-end align-content-center flex-grow-1" style="height: 10vh; margin : 0;">
            <button type="button" class="btn btn-primary mt-4 mb-4" style="width:500px; height : 50px;" onclick="location.href='${pageContext.request.contextPath}/guestbook/guestbook.do';">Guests Book</button>
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



