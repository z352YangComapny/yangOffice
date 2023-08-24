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
<style>

.col-md-6 {
	border: 1px solid #ccc;
    padding: 20px; 
}
.preview-image {
    width: 200px;
    height: 200px;
    margin-top: 10px;
}
.radio-inline {
    margin-right: 20px;
    font-size: 1.5rem; 
}
.form-group {
    margin-bottom: 0; 
    border-bottom: 1px solid #ccc; 
}
      
</style>
</head>
<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <h1 class="mb-4 text-center">프로필 생성</h1>

                <form:form name="profileForm" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/profile/create.do">
                    <div class="form-group">
                        <label for="upFile">프로필 사진</label>
                        <div class="text-center">
                            <img id="imagePreview" class="preview-image rounded-circle" src="${pageContext.request.contextPath}/resources/upload/attachment/default.jpg" alt="프로필 사진">
                            <input type="file" class="form-control-file mt-2" id="upFile" name="upFile" multiple onchange="showPreview(this);">
                        </div>
                    </div>

                    <div class="form-group">
                    
                        <label>상태</label>
                        <div class="d-flex justify-content-center" style="margin-top: -20px;">
                            <label class="form-check-label radio-inline">
                                <input class="form-check-input" type="radio" name="state" id="A" value="A" checked/> 😡
                            </label>&nbsp;&nbsp;
                            <label class="form-check-label radio-inline">
                                <input class="form-check-input" type="radio" name="state" id="B" value="B"/> 🤬
                            </label>&nbsp;&nbsp;
                            <label class="form-check-label radio-inline">
                                <input class="form-check-input" type="radio" name="state" id="C" value="C"/> 🥵
                            </label>&nbsp;&nbsp;
                            <label class="form-check-label radio-inline">
                                <input class="form-check-input" type="radio" name="state" id="D" value="D"/> 🤯
                            </label>&nbsp;&nbsp;
                            <label class="form-check-label radio-inline">
                                <input class="form-check-input" type="radio" name="state" id="E" value="E"/> 😵
                            </label>&nbsp;&nbsp;
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="introduction">간단소개</label>
                        <textarea class="form-control" id="introduction" name="introduction" rows="4" required>안녕하세요.${pageContext.request.userPrincipal.name}입니다. </textarea>
                    </div>

                    <div class="text-center">
                        <button type="submit" class="btn btn-primary">생성</button>
                        <button type="submit" class="btn btn-secondary" formaction="${pageContext.request.contextPath}/profile/defaultcreate.do">나중에 하기</button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>

    <script>
        function showPreview(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();
                reader.onload = function(e) {
                    $('#imagePreview').attr('src', e.target.result);
                }
                reader.readAsDataURL(input.files[0]);
            }
        }
    </script>
</body>
</html>
