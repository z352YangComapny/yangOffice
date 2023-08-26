<%@page import="com.yangworld.app.domain.profile.entity.ProfileDetails"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<style>
    /* 이미지 최대 너비와 최대 높이 설정 */
    #selectedImage {
        max-width: 350px; /* 최대 너비 */
        max-height: 300px; /* 최대 높이 */
    }
    
    .profile-box {
        border: 2px solid #ccc;
        padding: 10px;
        border-radius: 5px;
        margin-top: 20px;
        text-align: center;
        width: 350px;
    }
    
    /* 상태값에 따른 이모티콘 크기 설정 */
    .status-emoji {
        font-size: 1.5rem;
    }
</style>
<sec:authorize access="isAuthenticated()">
<%-- 자바 코드를 사용하여 principalName 출력하기 --%>
<%-- <% String principalName = (String) request.getAttribute("principalName"); %>
<p>Principal Name: ${principalName}</p> --%>
<sec:authentication property="principal" var="loginMember"/>
    <div class="container mt-5">
        <h1 class="mb-4"></h1>
		    <div class="form-group">
		        <label for="upFile"></label>
		        <c:choose>
		            <c:when test="${not empty profileAttachments}">
		                <c:forEach items="${profileAttachments}" var="attachment">
		                    <img id="selectedImage" class="preview-image rounded-circle" src="${context.request.contextPath}/resources/upload/attachment/${attachment.renamedFilename}" alt="프로필 사진" style="width: 350px; height: 350px;">
		                </c:forEach>
		            </c:when>
		            <c:otherwise>
		                <!-- 프로필 사진이 없을 경우 기본 이미지 또는 아무것도 표시하거나 로직을 추가해주세요. -->
		                <img id="selectedImage" class="preview-image rounded-circle" src="<c:url value='/resources/upload/attachment/default.jpg' />" alt="기본 프로필 사진" style="width=350px; height: 350px;">
		            </c:otherwise>
		        </c:choose>
		    </div>

			<div class="profile-box">
                <div class="status-emoji">today is... ${profile.state eq 'A' ? '😡' : profile.state eq 'B' ? '🤬' : profile.state eq 'C' ? '🥵' : profile.state eq 'D' ? '🤯' : profile.state eq 'E' ? '😵' : ''}</div>
            </div>

           
            <div class="form-group">
                <label for="introduction"></label>
                <textarea class="form-control" id="introduction" name="introduction" rows="4" required style="width: 350px; height: 200px; resize: none; " disabled>${profile.introduction} </textarea>
            </div>
            <div style="font-size: 30px; margin-top: 30px; margin-left: 10px;" >
            ${principalName}&nbsp;&nbsp;&nbsp;${principalGender eq 'M' ? '♀' : principalGender eq 'F' ? '♂' : ''}&nbsp;&nbsp;&nbsp;${principalBday}
            <button type="button" class="btn btn-primary" onclick="location.href= '${pageContext.request.contextPath}/profile/update.do';">수정</button>
            	
            </div>
    </div>
 
    </sec:authorize>
    
    
    
    
    
    

