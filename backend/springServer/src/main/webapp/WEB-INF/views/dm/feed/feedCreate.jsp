<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:requestEncoding value = "utf-8"/>
<jsp:include page ="/WEB-INF/views/common/header.jsp">
    <jsp:param name = "title" value = "안녕 스프링"/>
</jsp:include>
    
<div class="container mt-4">
    <h1>피드 생성</h1>
    
    <form:form 
		name="feedFrm" 
		action="${pageContext.request.contextPath}/member/userPage/${id}/feedCreated"
		enctype="multipart/form-data" 
		method="post">
		<div class="input-group mb-3" style="padding:0px;">
		  <div class="input-group-prepend" style="padding:0px;">
		    <span class="input-group-text">첨부파일</span>
		  </div>
		  <div class="custom-file">
		    <input type="file" class="custom-file-input" name="photo" id="photo" multiple>
		    <label class="custom-file-label" for="photo">파일을 선택하세요</label>
		  </div>
		</div>
		<div id="previewContainer"></div> <!-- 이미지 미리보기를 보여줄 컨테이너 -->
		<div class="input-group mb-3" style="padding:0px;">
		  <div class="input-group-prepend" style="padding:0px;">
		  </div>
		</div>
		
	    <textarea class="form-control" name="content" placeholder="내용" required></textarea>
		<br />
		<input type="submit" class="btn btn-outline-success" value="저장" >
	</form:form>
</div>
<script>
document.getElementById('photo').addEventListener('change', function(event) {
    const previewContainer = document.getElementById('previewContainer');
    previewContainer.innerHTML = ''; // 기존에 표시된 미리보기 삭제
    
    const files = event.target.files;
    for (let i = 0; i < files.length; i++) {
        const file = files[i];
        const reader = new FileReader();
        
        reader.onload = function(event) {
            const img = document.createElement('img');
            img.src = event.target.result;
            img.style.maxWidth = '200px'; // 이미지의 크기 제한
            previewContainer.appendChild(img);
        };
        
        reader.readAsDataURL(file);
    }
});
</script>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
