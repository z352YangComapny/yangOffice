<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="/WEB-INF/views/common/header.jsp">
    <jsp:param value="프로필 생성" name="title"/>
</jsp:include>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>프로필 수정</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container mt-5">
        <h1 class="mb-4">프로필 수정</h1>
        
        <form:form name="profileForm" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/profile/update.do" class="col-md-6">
        	<div class="form-group">	
                <label for="upFile">프로필 사진</label>
                <img src="<c:url value='/resources/upload/profile/${profile.attachments[0].renamedFilename}' />" alt="프로필 사진" width="100">
                <input type="file" class="form-control-file" id="upFile" name="upFile" multiple>
                <%-- <c:if test="${not empty profile.attachments}">
                	<img src="${pageContext.request.contextPath}/resources/upload/profile/${profile.attachments[0].renamedFilename}" alt="프로필 사진" width="200" height="200">
                </c:if>
          --%>
            </div>
            <div class="form-group">
                <label for="state">상태</label>
                <input type="text" class="form-control" id="state" name="state" value="${profile.state}" required>
            </div>
            
            <div class="form-group">
                <label for="introduction">간단소개</label>
                <textarea class="form-control" id="introduction" name="introduction" rows="4" required>${profile.introduction} </textarea>
            </div>
            
          
            <button type="submit" class="btn btn-primary">수정</button>
			<button type="button" class="btn btn-primary" id="resetButton">초기화</button>

        </form:form>
    </div>


<script>
$(document).ready(function() {
    
    $('#resetButton').click(function() {
        
        $('#upFile').val(''); 
        $('#state').val('A'); 
        $('#introduction').val('안녕하세요.${pageContext.request.userPrincipal.name}입니다.');
        // 표시된 이미지 제거
        $('img[src$="default.jpg"]').removeAttr('src');
    });

    
});





</script>

      <<%-- div>
            <c:if test="${not empty profileAttachments}">
    <h2>프로필 사진</h2>
    <c:forEach items="${profileAttachments}" var="attachment">
        <img src="<c:url value='/resources/upload/profile/${attachment[0].renamedFilename}' />" alt="프로필 사진" width="200" height="200">
    </c:forEach>
</c:if>
            </div> --%>
</body>
</html>

