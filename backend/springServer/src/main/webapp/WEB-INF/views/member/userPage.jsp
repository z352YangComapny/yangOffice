<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:requestEncoding value = "utf-8"/> <!-- 한글로 제목을 변경할 경우에는 인코딩이 깨질 수 있으니 해당 설정 잡아주기 -->

<sec:authorize access = "isAuthenticated()">
    <jsp:include page ="/WEB-INF/views/common/header.jsp"/>
    <h1>새로운 userPage</h1>
    <div class="d-flex flex-row">
        <div class ="" id="profile" style="width: 30vw; height: 80vh; margin : 0 0;"></div>
        <div class="d-flex justify-content-center row" id="member_content" style="width: 70vw; margin:0 0;">
            <div class="d-flex justify-content-center column align-items-center">
                <div class="flex-grow-1 mt-4" style="width:50vw;">
                    <jsp:include page="/WEB-INF/views/member/follow.jsp"/>
                </div>
                <div id ="dm"  class="flex-grow-1" style="width :20vw; height: 10vh; margin: 0; display: flex; align-items: center; justify-content: flex-end;">
                    <a href="${pageContext.request.contextPath}/dm/dmList">
                        <img src="${pageContext.request.contextPath}/resources/images/dm_logo.png" id="dm-image" alt="dm-img" style="width: 140px;"/>
                    </a>
                </div>
            </div>
            <div id="story"  class="flex-grow-1" style="height : 20vh; margin : 0;">
                <jsp:include page="/WEB-INF/views/story/storyMain.jsp"/>
            </div>
            <div id="photoFeed" class="flex-grow-1" style="border: 1px solid #000; height: 40vh; margin : 0;">
                <jsp:include page="/WEB-INF/views/feed/feedList.jsp"/>
            </div>
            <div class="d-flex justify-content-end align-content-center flex-grow-1" style="height: 10vh; margin : 0;">
                <button type="button" class="btn btn-primary mt-4 mb-4" style="width:500px; height : 50px;" onclick="location.href='${pageContext.request.contextPath}/';">Guests Book</button>
            </div>
        </div>
    </div>
</sec:authorize>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>