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
<style>
	#photoFeedTitle{
		font-size : 30px; font-weight: bold;
		background: linear-gradient(to right, #F3969A, #78C2AD);
		-webkit-background-clip: text;
		-webkit-text-fill-color: transparent;
	}
	#photoFeedCreateBox{border : 1px solid white; border-radius : 10px; box-shadow: 3px 3px 10px 5px #d2d2d2; margin-left: 300px;}
</style>
<div class="container mt-5" id="photoFeedCreateBox">
    <p><span id="photoFeedTitle">포토피드 만들기</span></p>
    
    <form:form 
		name="feedFrm" 
		action="${pageContext.request.contextPath}/member/userPage/${id}/feedCreated"
		enctype="multipart/form-data" 
		method="post">
		<div class="input-group mb-3" style="padding:0px;">
		  <div class="input-group-prepend mr-2" style="padding:0px;">
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
		
	    <textarea class="form-control" name="content" placeholder="내용" style="resize:none; height: 300px;"required></textarea>
		<br />
		<div class="d-flex justify-content-end">
			<input type="submit" class="btn btn-outline-success mb-3" value="저장"/>
		</div>
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
