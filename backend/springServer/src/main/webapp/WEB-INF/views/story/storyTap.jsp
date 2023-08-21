<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/storyTap.css">
<sec:authorize access = "isAuthenticated()">
    <jsp:include page ="/WEB-INF/views/common/header.jsp">
        <jsp:param name = "title" value = "안녕 스프링"/>
    </jsp:include>
    <div class="d-flex flex-row">
        <div class ="" id="profile" style="width: 30vw; height: 80vh; margin : 0 0;"></div>
        <div class="d-flex justify-content-center row" id="member_content" style="width: 70vw; margin:0 0;">
			<div id="story"  class="flex-grow-1">
				<c:forEach items="${stories}" var="story" varStatus="vs">
					<div class="card m-3">
					  <ul class="list-group list-group-flush">
					    <li class="list-group-item writerId">${story.writerId}</li>
					    <li class="list-group-item content">${story.content}</li>
					    <li class="list-group-item">
					    	<div class="createdAt">${story.regDate}</div>
					    	<button class="btn btn-primary">삭제</button>
					    </li>
					  </ul>
					</div>
				</c:forEach>
			</div>
        </div>
    </div>
	    
	<div class="modal fade" id="storyModal" tabindex="-1" role="dialog" aria-labelledby="storyModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header storyModalWriterId"></div>
	      <div class="modal-body">
	        <form>
	          <div class="form-group">
	            <textarea class="form-control storyModalContent" id="message-text"></textarea>
	          </div>
	        </form>
	      </div>
	      <div class="modal-footer storyModalCreatedAt"></div>
	       <button class="btn btn-primary">수정</button>
	    </div>
	  </div>
	</div>
    
</sec:authorize>

<script>
document.addEventListener('DOMContentLoaded', () => {
	const storyElements = document.querySelectorAll('.card');
	const storyModal = $('#storyModal');
	
    storyElements.forEach((storyElement) => {
        storyElement.addEventListener('click', () => {
        	updateModal2(storyElement);

            storyModal.modal('show');

        });
	    
    });
});

const updateModal2 = (element) => {
	const writerId = element.querySelector('.writerId').textContent;
	const content = element.querySelector('.content').textContent;
	const createdAt = element.querySelector('.createdAt').textContent;
	
	document.querySelector('.storyModalWriterId').textContent = writerId;
	document.querySelector('.storyModalContent').textContent = content;
	document.querySelector('.storyModalCreatedAt').textContent = createdAt;
};
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
