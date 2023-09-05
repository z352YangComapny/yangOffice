<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/storyTap.css">
   <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.6.1/sockjs.min.js" integrity="sha512-1QvjE7BtotQjkq8PxLeF6P46gEpBRXuskzIVgjFpekzFVF4yjRgrQvTG1MTOJ3yQgvTteKAcO7DSZI92+u/yZw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js" integrity="sha512-iKDtgDyTHjAitUDdLljGhenhPwrbBfqTKWO1mkhSFH3A7blITC9MhYon6SjnMhp4o0rADGw9yAC6EW4t5a4K3g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
   <script src="${pageContext.request.contextPath}/resources/js/stomp.js"></script>
<sec:authentication property="principal" var="loginMember"/>
<input type='hidden' id='userId' value='${loginMember.id}' />
<sec:authorize access = "isAuthenticated()">
    <jsp:include page ="/WEB-INF/views/common/header.jsp">
        <jsp:param name = "title" value = "안녕 스프링"/>
    </jsp:include>

        <div class="containerStoryTap d-flex flex-row">
            <div class ="" id="profile" >
            	<jsp:include page="/WEB-INF/views/profile/profileMain.jsp"/>
        	</div>
        	<div id="storyDiv">
	        	<div>
	        		<button class="btn btn-success mt-3 mx-3 " id="btnStoryCreate">추가</button>
	        	</div>
				<div id="story">
					<c:forEach items="${stories}" var="story">
						<div class="card m-3" id="storyCards">
						 	<input type="hidden" id="storyId" value="${story.id}"/>
						  <ul class="list-group list-group-flush">
						    <li class="list-group-item writerId">${loginMember.username}</li>
						    <input type="hidden" id="writerId" value="${story.writerId}"/>
						    <li class="list-group-item content">${story.content}</li>
						    <li class="list-group-item formattedRegDate">${story.formattedRegDate}</li>
						    <input type="hidden" class="storyFeed" value="${story.storyFeed}"/>
						  </ul>
						</div>
					</c:forEach>
				</div>
        	</div>
        </div>

	<input type="hidden" class="currentCardStoryFeed" value=""/>
	<div class="modal fade" id="storyModal" tabindex="-1" role="dialog" aria-labelledby="storyModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	      	${loginMember.username}
	      	<input type="hidden" class="storyModalWriterId" id="storyModalWriterId" value=""/>
			<c:choose>
			    <c:when test="${not empty currentCardStoryFeed and currentCardStoryFeed != '0'}">
			    	<img src="${pageContext.request.contextPath}/resources/images/arrow.png" onclick="storyFeedLink();" style="width: 25px;"/>
			    </c:when>
			</c:choose>
	      </div>
	      <div class="modal-body">
	        <form>
	          <div class="form-group">
	            <textarea class="form-control storyModalContent" id="message-text-modal-content" ></textarea>
	          </div>
	        </form>
	      </div>
	      <div class="modal-footer storyModalCreatedAt"></div>
			<button class="btn btn-primary" id="btnUpdateStory">수정</button>
			<button class="btn btn-secondary" id="btnDelete">삭제</button>
	       <input type="hidden" id="storyModalId" value=""/>
	    </div>
	  </div>
	</div>
    
	<div class="modal fade" id="createModal" tabindex="-1" role="dialog" aria-labelledby="createModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="createStoryWriterId">${loginMember.username}</h5>
	      </div>
	      <div class="modal-body">
	      	<form>
	          <div class="form-group">
	            <textarea class="form-control createStoryContent" id="message-text-create" style="resize:none;"></textarea>
	          </div>
	        </form>
	      </div>
	      <div class="modal-footer">

	        <button type="button" class="btn btn-primary" id="btnCreateStory2">추가</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<form:form id="creatFrm" method="POST" action="${pageContext.request.contextPath}/story/create">
		<input type="hidden" class="createModalWriterId" name="writerId" value="${loginMember.id}"/>
		<input type="hidden" class="createModalContent" name="content" value=""/>
	</form:form>
	
	<form:form id="updateFrm" method="POST" action="${pageContext.request.contextPath}/story/update">
		<input type="hidden" id="updateModalId" name="id" value=""/>
		<input type="hidden" id="updateModalWriterId" name="writerId" value=""/>
		<input type="hidden" id="updateModalContent" name="content" value=""/>
	</form:form>
	
	<form:form id="deleteFrm" method="POST" action="${pageContext.request.contextPath}/story/delete">
		<input type="hidden" id="deleteModalId" name="id" value=""/>
		<input type="hidden" id="deleteModalWriterId" name="writerId" value=""/>
	</form:form>
	
</sec:authorize>

<script>
document.addEventListener('DOMContentLoaded', () => {
	connect();
	
	const storyElements = document.querySelectorAll('#storyCards');
	const storyModal = $('#storyModal');
	
    storyElements.forEach((storyElement) => {
        storyElement.addEventListener('click', () => {
        	updateModal2(storyElement);

            storyModal.modal('show');

        });
	    
    });
});

const updateModal2 = (e) => {
	const writerId = e.querySelector('#writerId').value;
	const content = e.querySelector('.content').textContent;
	const createdAt = e.querySelector('.formattedRegDate').textContent;
	const id = e.querySelector("#storyId").value;
	const storyFeed = e.querySelector(".storyFeed").value;
	
	document.querySelector('#storyModalWriterId').value = writerId;
	document.querySelector('.storyModalContent').textContent = content;
	document.querySelector('.storyModalCreatedAt').textContent = createdAt;
	document.querySelector('#storyModalId').value = id;
	document.querySelector('.currentCardStoryFeed').value = storyFeed;

};

document.querySelector("#btnStoryCreate").onclick = () => {
	const createModal = $('#createModal');
	createModal.modal('show');
};

/*    document.querySelector("#btnCreateStory2").onclick = () => {
	const content = document.querySelector('#message-text-create').value;
	if(!/^.{1,100}$/.test(content)){
		alert('글자 수는 1 - 100글자 사이입니다');
		return false;
	}
	
	console.log('content = ', content);
	document.querySelector(".createModalContent").value = content;
	console.log(document.querySelector(".createModalContent").value);
	document.querySelector('#creatFrm').submit();
}; */

document.querySelector("#btnUpdateStory").onclick = () => {
	const id = document.querySelector("#storyId").value;
	const writerId = document.querySelector("#storyModalWriterId").value;
	const content = document.querySelector('#message-text-modal-content').value;
	
	if(!/^.{1,100}$/.test(content)){
		alert('글자 수는 1 - 100글자 사이입니다');
		return false;
	}
	
	const frm = document.querySelector("#updateFrm");
	document.querySelector("#updateModalId").value = id;
	document.querySelector("#updateModalWriterId").value = writerId;
	document.querySelector("#updateModalContent").value = content;
	
	frm.submit();
	window.location.href = "http://localhost:8080/story/storyTap";
};

document.querySelector("#btnDelete").onclick = () => {
	if(confirm('정말로 삭제하시겠습니까?')){
		const id = document.querySelector("#storyModalId").value;
		const writerId = document.querySelector('#storyModalWriterId').value;
		document.querySelector("#deleteModalId").value = id;
		document.querySelector("#deleteModalWriterId").value = id;
		
		const frm = document.querySelector("#deleteFrm");
		
		frm.submit();
	}
};

const storyFeedLink = () => {
	const id = document.querySelector('.currentCardStoryFeed').value;
	const addr = "http://localhost:8080/member/userPage/${loginMember.id}/feed/feedDetail?photoFeedId="
	window.location.href = addr + id;
};

const storyFeedLinkCreate = () => {
	const popup = window.open("${pageContext.request.contextPath}/story/storyFeedFind", "popup", "width=1000px, height=600px, left=0px, top=400px");
};
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
