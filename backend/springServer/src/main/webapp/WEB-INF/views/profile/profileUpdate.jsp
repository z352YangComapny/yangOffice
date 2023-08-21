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
		        <label for="upFile"></label>
		        <c:choose>
		            <c:when test="${not empty profileAttachments}">
		                <c:forEach items="${profileAttachments}" var="attachment">
		                    <img id="selectedImage" src="${context.request.contextPath}/resources/upload/attachment/${attachment.renamedFilename}" alt="프로필 사진" style="width: 200px; height: 200px;">
		                </c:forEach>
		            </c:when>
		            <c:otherwise>
		                <!-- 프로필 사진이 없을 경우 기본 이미지 또는 아무것도 표시하거나 로직을 추가해주세요. -->
		                <img id="selectedImage" src="<c:url value='/resources/upload/profile/default.jpg' />" alt="기본 프로필 사진" width="200">
		            </c:otherwise>
		        </c:choose>
		        <input type="file" class="form-control-file" id="upFile" name="upFile" multiple>
		    </div>

			<div class="form-group">
			    <label for="state">상태</label>
			    <div class="form-check-inline">
			        <input type="radio" id="A" name="state" value="A" ${profile.state eq 'A' ? 'checked' : ''}>
			        <label class="form-check-label" for="A">😡</label>
			    </div>
			    <div class="form-check-inline">
			        <input type="radio" id="B" name="state" value="B" ${profile.state eq 'B' ? 'checked' : ''}>
			        <label class="form-check-label" for="B">🤬</label>
			    </div>
			    <div class="form-check-inline">
			        <input type="radio" id="C" name="state" value="C" ${profile.state eq 'C' ? 'checked' : ''}>
			        <label class="form-check-label" for="C">🥵</label>
			    </div>
			    <div class="form-check-inline">
			        <input type="radio" id="D" name="state" value="D" ${profile.state eq 'D' ? 'checked' : ''}>
			        <label class="form-check-label" for="D">🤯</label>
			    </div>
			    <div class="form-check-inline">
			        <input type="radio" id="E" name="state" value="E" ${profile.state eq 'E' ? 'checked' : ''}>
			        <label class="form-check-label" for="E">😵</label>
			    </div>
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
    // 파일 선택(input 변경) 이벤트 핸들러
    $('#upFile').on('change', function() {
        var selectedFile = this.files[0];
        if (selectedFile) {
            var reader = new FileReader();
            reader.onload = function(e) {
                $('#selectedImage').attr('src', e.target.result);
            };
            reader.readAsDataURL(selectedFile);
        } else {
            // 선택된 파일이 없을 때 기본 이미지 표시
            $('#selectedImage').attr('src', '<c:url value="/resources/upload/profile/default.jpg" />');
        }
    });

    $('#resetButton').click(function() {
        $('#upFile').val('');
        $('#selectedImage').attr('src', '<c:url value="/resources/upload/profile/default.jpg" />');
        $('input[name="state"][value="A"]').prop('checked', true);
        $('#introduction').val('안녕하세요.${pageContext.request.userPrincipal.name}입니다.');
    });
});
</script>









      
</body>
</html>

