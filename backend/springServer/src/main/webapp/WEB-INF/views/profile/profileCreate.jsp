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
	border: 2px solid #ccc;
	border-radius: 10px;
    padding: 20px; 
}
.preview-image {
    width: 350px;
	height: 250px;
	border-radius: 10%;
    margin-top: 10px;
}
.radio-inline {
    margin-right: 20px;
    font-size: 1.5rem; 
}
.form-group {
	margin-top: 20px;
    margin-bottom: 0; 
    border-bottom: 1px solid #ccc; 
}
.emoji-image{
	
	width: 40px;
	margin: 10px;
}
      
</style>
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <h1 class="mb-4 text-center">프로필 만들기</h1>

            <form:form name="profileForm" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/profile/profileCreate">
                <div class="form-group">
                    <label for="upFile">프로필 사진</label>
                    <div class="text-center">
                        <img id="imagePreview" class="preview-image " src="${pageContext.request.contextPath}/resources/upload/attachment/default.jpg" alt="프로필 사진">
                        <input type="file" class="form-control-file mt-2" id="upFile" name="upFile" multiple onchange="showPreview(this);">
                    </div>
                </div>

                <div class="form-group">
                
                    <label>상태</label>
                    <div class="d-flex justify-content-center" style="margin-top: -20px; margin-left: -30px;">
                        <label class="form-check-label radio-inline" >
                            <input class="form-check-input" type="radio" name="state" id="A" value="A" checked/>
                            <img src="${pageContext.request.contextPath}/resources/images/stateA.png" alt="상태 A 이미지" class="emoji-image"/>
                        </label>&nbsp;&nbsp;
                        <label class="form-check-label radio-inline">
                            <input class="form-check-input" type="radio" name="state" id="B" value="B"/> 
                            <img src="${pageContext.request.contextPath}/resources/images/stateB.png" alt="상태 B 이미지" class="emoji-image"/>
                        </label>&nbsp;&nbsp;
                        <label class="form-check-label radio-inline">
                            <input class="form-check-input" type="radio" name="state" id="C" value="C"/> 
                            <img src="${pageContext.request.contextPath}/resources/images/stateC.png" alt="상태 C 이미지" class="emoji-image"/>
                        </label>&nbsp;&nbsp;
                        <label class="form-check-label radio-inline">
                            <input class="form-check-input" type="radio" name="state" id="D" value="D"/> 
                            <img src="${pageContext.request.contextPath}/resources/images/stateD.png" alt="상태 D 이미지" class="emoji-image"/>
                        </label>&nbsp;&nbsp;
                        <label class="form-check-label radio-inline">
                            <input class="form-check-input" type="radio" name="state" id="E" value="E"/> 
                            <img src="${pageContext.request.contextPath}/resources/images/stateE.png" alt="상태 E 이미지" class="emoji-image"/>
                        </label>&nbsp;&nbsp;
                    </div>
                </div>
                <c:if test="${not empty member}">
                <div class="form-group">
                    <label for="introduction" style="margin-top: 20px;">간단소개</label>
                    <textarea class="form-control" id="introduction" name="introduction" rows="4" required style="resize: none;">안녕하세요.${member.nickname}입니다. </textarea>
                    <input type="hidden" value = "${member.id}" name ="memberId"/>
                </div>
              </c:if>
              	<div class="text-center" style="margin-top: 10px;">
                    <button type="submit" class="btn btn-primary">생성</button>
                </div>
            </form:form>
                <div class="text-center" >
                    <c:if test="${not empty member}">
			          	<form:form name = "defaultFrm" action = "${pageContext.request.contextPath}/profile/defaultcreate.do" method="POST">
			         	<input type="hidden" name="memberId" value="${member.id}"/>
			         	<input type="hidden" name="memberUsername" value="${member.username}"/>
			            <button type="submit" class="btn btn-secondary" style="margin-top: 10px;" >나중에 하기</button>
			        	</form:form>
		        	</c:if>
                </div>
            
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
