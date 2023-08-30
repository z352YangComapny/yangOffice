<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="/WEB-INF/views/common/header.jsp">
    <jsp:param value="프로필 수정" name="title"/>
</jsp:include>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>프로필 수정</title>
</head>
<style>
    /* 이미지 최대 너비와 최대 높이 설정 */
    #selectedImage {
        max-width: 350px; /* 최대 너비 */
        max-height: 350px; /* 최대 높이 */
      	border-radius : 10px;
    }
   .emoji-image{
   	width: 20px;
   	margin: -2px;
   }
</style>
<body>
<sec:authentication property="principal" var="loginMember"/>
       <div class="container mt-5" style="margin-left: 300px;">
    <h1 class="mb-4">프로필 정보</h1>
    <hr style="border: 0; border-top: 4px solid silver;">
    <form:form name="profileForm" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/member/userPage/${loginMember.id}/profile/update.do" class="col-md-6">
        <div class="d-flex flex-row">
            <div class="col-md-6">
                <div class="form-group">
                    <label for="upFile"></label>
                    <h4 style="margin-top: -9px;">프로필 사진</h4>
                    <c:choose>
                        <c:when test="${not empty profileAttachments}">
                            <c:forEach items="${profileAttachments}" var="attachment">
                            	<%-- <img id="selectedImage" src="${context.request.contextPath}/resources/upload/attachment/${not empty profileAttachments ? profileAttachments[0].renamedFilename : 'default.jpg'}" alt="프로필 사진" style="width: 350px; height: 350px;"> --%>
                                <img id="selectedImage" class="preview-image " src="${pageContext.request.contextPath}/resources/upload/attachment/${attachment.renamedFilename}" alt="프로필 사진" style="width: 350px; height: 350px;">
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <img id="selectedImage" src="<c:url value='/resources/upload/attachment/default.jpg' />" alt="기본 프로필 사진" style="width=350px; height: 350px;">
                            <input type="hidden" id="defaultImageChanged" name="defaultImageChanged" value="false">
                        </c:otherwise>
                    </c:choose>
                    <input type="file" class="form-control-file mt-3" id="upFile" name="upFile" multiple >
                </div>
                <div class="d-flex flex-column justify-content-center align-items-center">
	                <div class="form-group d-flex flex-column justify-content-center align-items-center" style="margin-left: 80px;">
	                    <span style="font-size: 22px; margin-top: 25px;">${principalName}</span>
	                    <hr style="width : 150px; border : 3px solid; margin: auto"/>
	                </div>
	                <div class="form-group d-flex flex-column justify-content-center align-items-center" style="margin-left: 80px;">
	                    <span style="font-size: 20px;">${principalBday}</span>
	                    <hr style="width : 200px; border : 3px solid; margin: auto"/>
	                </div>
                
                </div>
            </div>
            <div class="col-md-6" style="margin-left: 250px; margin-top: 20px;">
               <div class="form-group">
               	<br><hr style="border-top: 5px solid silver; margin-right: -330px;">
				    <label for="state">기분 상태</label>
				    <%-- <select class="form-control" id="state" name="state" style="width:50px; margin-left:90px;">
				        <option value="A" ${profile.state eq 'A' ? 'selected' : ''}>😡</option>
				        <option value="B" ${profile.state eq 'B' ? 'selected' : ''}>🤬</option>
				        <option value="C" ${profile.state eq 'C' ? 'selected' : ''}>🥵</option>
				        <option value="D" ${profile.state eq 'D' ? 'selected' : ''}>🤯</option>
				        <option value="E" ${profile.state eq 'E' ? 'selected' : ''}>😵</option>
				    </select> --%>	
				    <div class="status-images">
					    <label class="status-image">
					        <input type="radio" name="state" value="A" ${profile.state eq 'A' ? 'checked' : ''} />
					        <img src="${pageContext.request.contextPath}/resources/images/stateA.png" alt="상태 A 이미지" class="emoji-image" />
					    </label>
					    <label class="status-image">
					        <input type="radio" name="state" value="B" ${profile.state eq 'B' ? 'checked' : ''} />
					        <img src="${pageContext.request.contextPath}/resources/images/stateB.png" alt="상태 B 이미지" class="emoji-image" />
					    </label>
					    <label class="status-image">
					        <input type="radio" name="state" value="C" ${profile.state eq 'C' ? 'checked' : ''} />
					        <img src="${pageContext.request.contextPath}/resources/images/stateC.png" alt="상태 C 이미지" class="emoji-image" />
					    </label>
					    <label class="status-image">
					        <input type="radio" name="state" value="D" ${profile.state eq 'D' ? 'checked' : ''} />
					        <img src="${pageContext.request.contextPath}/resources/images/stateD.png" alt="상태 D 이미지" class="emoji-image" />
					    </label>
					    <label class="status-image">
					        <input type="radio" name="state" value="E" ${profile.state eq 'E' ? 'checked' : ''} />
					        <img src="${pageContext.request.contextPath}/resources/images/stateE.png" alt="상태 E 이미지" class="emoji-image" />
					    </label>
					</div>
				    <hr style="border-top: 5px solid silver; margin-right: -330px;">
				</div>

                <div class="row">
                    <div class="col-md-4">
                    	<br><br><br><br><br><br>
                        <label for="introduction" style="font-size:17px;">간단 소개</label>
                    </div>
                    <div class="col-md-8">
                    	<br><br>
                        <textarea class="form-control" id="introduction" name="introduction"  required style="height: 300px; width: 350px; resize: none;">${profile.introduction}</textarea>
                    </div>
                </div>
                        <br><hr style="border-top: 5px solid silver; margin-right: -330px;">
            </div>
        </div>
                <div style="margin-left: 130px">
			    <button type="submit" class="btn btn-primary" >수정</button>
			    <button type="button" class="btn btn-primary" id="defaultUpdate">초기화</button>
			    </div>
    </form:form>
</div>


<script>
const csrfToken = "${_csrf.token}";
$(document).ready(function() {
    var selectedImageChanged = false; // 이미지가 변경되었는지 여부
    
    $('#defaultUpdate').on('click', function() {
    	if (confirm("프로필을 초기화하시겠습니까?")) {
	        $.ajax({
	            type: 'POST',
	            url: '${pageContext.request.contextPath}/member/userPage/${loginMember.id}/profile/defaultupdate',
                beforeSend: function(xhr) {
                    xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}')},
	            success: function(data) {

					alert("초기화가 완료되었습니다");
                    location.reload();
                },
	            error: function(error) {
	                console.error('Error during profile reset:', error);
	            }
	        });
    	}
    });
    
    
    
    // 파일 선택(input 변경) 이벤트 핸들러
    $('#upFile').on('change', function() {
        var selectedFile = this.files[0];
        if (selectedFile) {
            var reader = new FileReader();
            reader.onload = function(e) {
                $('#selectedImage').attr('src', e.target.result);
                selectedImageChanged = true;
            };
            reader.readAsDataURL(selectedFile);
            console.log(selectedFile);
        } else {
            // 선택된 파일이 없을 때 기본 이미지 표시
            $('#selectedImage').attr('src', '<c:url value="/resources/upload/attachment/default.jpg" />');
            selectedImageChanged = false;
        }
    });
    
   
    
    
    
});

</script>

      
</body>
</html>

