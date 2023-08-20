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
    <title>프로필 생성</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container mt-5">
        <h1 class="mb-4">프로필 생성</h1>
        
        <form:form name="profileForm" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/profile/create.do" class="col-md-6">
        	<div class="form-group">	
                <label for="upFile">프로필 사진</label>
                <%-- <img src="<c:url value='/resources/upload/profile/${profileDetails.attachments[0].renamedFilename}' />" alt="프로필 사진" width="100"> --%>
                
                <input type="file" class="form-control-file" id="upFile" name="upFile" multiple>
            </div>
            <div class="form-group">
                <label for="state">상태</label>
                <input type="text" class="form-control" id="state" name="state" value="A" required>
            </div>
            
            <div class="form-group">
                <label for="introduction">간단소개</label>
                <textarea class="form-control" id="introduction" name="introduction" rows="4" required>안녕하세요.${pageContext.request.userPrincipal.name}입니다. </textarea>
            </div>
            
            
            
            <button type="submit" class="btn btn-primary">생성</button>
			<button type="submit" class="btn btn-primary" formaction="${pageContext.request.contextPath}/profile/defaultcreate.do">나중에 하기</button>

        </form:form>
    </div>
<script>

/* $(document).ready(function() {
    $("#laterButton").click(function() {
        var csrfToken = $("meta[name='_csrf']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        
        $.ajax({
            url: "/profile/defaultcreate.do",
            type: "POST",
            beforeSend: function(xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function(response) {
                console.log("프로필 기본값 삽입 성공");
            },
            error: function(xhr, status, error) {
                console.error("프로필 기본값 삽입 에러: " + error);
            }
        });
    });
}); */




</script>

    
</body>
</html>

