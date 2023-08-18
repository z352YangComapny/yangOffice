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
</head>
<body>
    <h1>프로필 생성</h1>
    
    <form:form
        name="profileForm"
        method="post"
        enctype="multipart/form-data"
        action="${pageContext.request.contextPath}/profile/create.do">
        
        <label for="state">상태</label>
        <input type="text" id="state" name="state" required>
        <br>
        
        <label for="introduction">간단소개</label>
        <textarea id="introduction" name="introduction" rows="4" required></textarea>
        <br>
        
        <label for="upFile">프로필 사진</label>
        <input type="file" id="upFile" name="upFile" multiple>
        <br>
        
        <input type="submit" value="생성">
    </form:form>
    
</body>
</html>
