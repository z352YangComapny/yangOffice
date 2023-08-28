<%@ page language="java" contentType="text/html; charset=UTF-8"
       pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <%--<title>${param.title}</title>--%>

   <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
   <!-- bootstrap js: jquery load 이후에 작성할것.-->
   <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
   <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

   <sec:authorize access="isAuthenticated()">
      <script>
         const username = '<sec:authentication property = "principal.username"/>';
       	 const id = '<sec:authentication property = "principal.id"/>';
      </script>
      <!--위에 변수 선언을 해주면 하단 stomp.js에서 참조가 가능하다! 기존 js에서는 jstl문법 등을 사용할 수 없으니까! -->
      <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.6.1/sockjs.min.js" integrity="sha512-1QvjE7BtotQjkq8PxLeF6P46gEpBRXuskzIVgjFpekzFVF4yjRgrQvTG1MTOJ3yQgvTteKAcO7DSZI92+u/yZw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js" integrity="sha512-iKDtgDyTHjAitUDdLljGhenhPwrbBfqTKWO1mkhSFH3A7blITC9MhYon6SjnMhp4o0rADGw9yAC6EW4t5a4K3g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
      <script src="${pageContext.request.contextPath}/resources/js/stomp.js"></script>
   </sec:authorize>

   <!-- bootstrap css -->
   <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.1/dist/minty/bootstrap.min.css">
   <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
   <!-- 사용자작성 css -->
   <c:if test="${not empty msg}">
      <script>
         alert('${msg}');
      </script>
   </c:if>
   <c:if test="${not empty accessToken}">
      <script>
         localStorage.setItem("accessToken", '${accessToken}')
      </script>
   </c:if>
</head>
<body>
<script>
   const accessToken = '<%= request.getAttribute("accessToken") %>';
   const username = '<%= request.getAttribute("username")%>';
   console.log(accessToken, username)
   if (accessToken !== "null" && username !== "null" ) {
      localStorage.setItem("accessToken", accessToken);
      alert(`\${username}님 환영합니다.!`)
   }
</script>
<!-- 인증되었을 때에만 해당 폼이 나오도록! -->
<sec:authorize access = "isAuthenticated()">
   <sec:authentication property="principal" var="loginMember"/>
<!-- Modal -->
<div class="modal fade" id="noticeModal" tabindex="-1" role="dialog" aria-labelledby="noticeModalLabel" aria-hidden="true">
   <div class="modal-dialog" role="document">
      <div class="modal-content">
         <div class="modal-header">
            <h5 class="modal-title" id="noticeModalLabel"></h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
               <span aria-hidden="true">&times;</span>
            </button>
         </div>
         <div class="modal-body"></div>
         <div class="modal-footer flex-column">
            <div class="d-flex justify-content-between w-100">
               <div>보낸사람 : <span class='badge badge-primary from'></span></div>
               <div>작성일 : <span class='when'></span></div>
            </div>
            <button type="button" class="btn btn-secondary" data-dismiss="modal">확인</button>
         </div>
      </div>
   </div>
</div>
   <form:form name = "memberLogoutFrm" action ="${pageContext.request.contextPath}/member/memberLogout.do" method="POST"></form:form>
</sec:authorize>
<div id="container">
   <header>
     <%-- <sec:authentication property="principal" var="dmMember"/>--%>
      <!-- https://getbootstrap.com/docs/4.0/components/navbar/ -->
      <nav class="navbar navbar-expand-lg bg-primary">
         <div class="container-fluid">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/member/userPage/${loginMember.id}">
               <img src="${pageContext.request.contextPath}/resources/images/logo2.png" alt="쏘이스토리_로고" width="70px" />
               <span style = "font-size : 30px; font-weight: bold; color: white;">SSOY STORY<span>
            </a>
            <div class="collapse navbar-collapse d-flex justify-content-evenly" id="navbarColor01">
               <div>
                  <ul class="navbar-nav me-auto">

                     <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/member/userPage/${loginMember.id}">Home</a></li>
                     <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/chat/chatList.do">월드에 놀러가기</a></li>
                     <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/question/questionList">QNA</a></li>
                     <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/feed/feedDetail.do">피드디테일</a></li>
                  </ul>
               </div>
               <div style="width:500px;">
                  <jsp:include page="/WEB-INF/views/member/follow.jsp"/>
               </div>
               <div class="d-flex justify-content-center column align-items-center">
                  <div id ="dm"  class="flex-grow-1" style="height: 10vh; margin: 0; display: flex; align-items: center; justify-content: flex-end;">
                     <div id="notification-div"> </div>
                      <input type='hidden' id='userId' value='${loginMember.id}' />
                     <a href="${pageContext.request.contextPath}/dm/dmList">
                        <img src="${pageContext.request.contextPath}/resources/images/send-message-w.png" id="dm-image" alt="dm-img" style="width: 70px;"/>
                     </a>
                  </div>
               </div>


               <div>
                  <form class="d-flex ml-5">
                     <sec:authorize access="isAuthenticated()">
                            <span class="align-middle" style="font-weight: bold; color : white; font-size : 15px;"><a  href="${pageContext.request.contextPath}/member/memberDetail.do"
                            title=" <sec:authentication property="authorities"/>" style="font-weight: bold; color : white;"><sec:authentication property="principal.nickname"/></a>님, 안녕하세요</span>
                        &nbsp;
                        <button
                                class="btn btn-secondary my-2 my-sm-0 ml-10"
                                type="button"
                                onclick="document.memberLogoutFrm.submit();">로그아웃</button>
                     </sec:authorize>
                  </form>
               </div>
               <div>

               </div>

            </div>
         </div>
      </nav>



   </header>

   <script>
      <c:choose>
      <c:when test="${not empty loginMember}">
      const userId = ${loginMember.id}; // 인증된 멤버의 ID를 가져옵니다.
      document.addEventListener('DOMContentLoaded', () => {
         notifyConnect(userId);
      });
      </c:when>
      <c:otherwise>
      console.log("로그인되지 않았습니다. DM 알림을 구독하지 않습니다.");
      </c:otherwise>
      </c:choose>

   </script>
   <section id="content">
