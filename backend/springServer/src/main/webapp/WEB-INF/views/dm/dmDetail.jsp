<%@page import="com.yangworld.app.domain.question.entity.Question"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
// dmRoomId 파라미터를 받아옴
String dmRoomIdParam = request.getParameter("dmRoomId");

// 받아온 파라미터를 정수로 변환
int dmRoomId = Integer.parseInt(dmRoomIdParam);
%>

<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="게시판" name="title"/>
</jsp:include>
<section style="background-color: #eee;">
  <div class="container py-5">

    <div class="row d-flex justify-content-center" style="height: 690px; width:1300px;">
      <div class="col-md-10 col-lg-8 col-xl-6" style="height: 500px; width:900px;">

        <div class="card" id="chat2" style="top:10%;">
          <div class="card-header d-flex justify-content-between align-items-center p-3">
            <h5 class="mb-0">Direct Message</h5>
            
            	<!-- 채팅방 삭제 버튼 -->
				<form:form action="${pageContext.request.contextPath}/dm/deleteDmRoom" name="dmDeleteFrm" method="post" id="deleteForm">
				    <input type="hidden" name="dmRoomId" value="<%= dmRoomId %>" />
				    <div class="d-flex justify-content-between align-items-center">
				        <button type="button" class="btn btn-warning" id="btn-delete" style="margin-left: 10px; margin-right: 10px;" onclick="deleteDm();">채팅방 나가기</button>
				        <!-- 뒤로가기 버튼 -->
				        <button type="button" class="btn btn-primary" data-mdb-ripple-color="dark" onclick="goBack();">뒤로가기</button>
				    </div>
				</form:form>
            
          </div>
          <div class="card-body" data-mdb-perfect-scrollbar="true" style="position: relative; height: 400px; overflow-y: auto;">
          	<div id="dmDetailsContainer"></div>
				 <c:set var="userId" value="${userId}" scope="page" />
				<c:forEach items="${dmDetails}" var="dm" varStatus="loop">
				    <c:if test="${dm.senderId == userId}">
				        <div class="d-flex flex-row justify-content-end mb-4 pt-1">
				            <div>
				                <p class="small p-2 me-3 mb-1 text-white rounded-3 bg-primary">${dm.content}</p>
				                <p class="small me-3 mb-3 rounded-3 text-muted d-flex justify-content-end">
				                	<fmt:parseDate value="${dm.regDate}" pattern="yyyy-MM-dd'T'HH:mm" var="regDate"/>
				                     <fmt:formatDate value="${regDate}" pattern="yy/MM/dd HH:mm"/>
				                </p>
				            </div>
				            <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava4-bg.webp"
				                 alt="avatar 1" style="width: 45px; height: 100%;">
				        </div>
				    </c:if>
				    <c:if test="${dm.senderId != userId and loop.first}">
				        <c:set var="firstDmSenderId" value="${dm.senderId}" scope="page" />
				    </c:if>
				    <c:if test="${dm.senderId != userId or dm.senderId == firstDmSenderId}">
				       <div class="d-flex flex-row justify-content-start align-items-center" id="otherDm" onmouseover="showButton(this)" onmouseout="hideButton(this)">
						    <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3-bg.webp"
						         alt="avatar 1" style="width: 45px; height: 100%;">
						    <div class="d-flex flex-column">
						        <div class="d-flex align-items-center">
						            <p class="small p-2 ms-3 mb-1 rounded-3" style="background-color: #f5f6f7;">
						                ${dm.content}
						            </p>
						            <button class="btn btn-sm btn-danger d-none btn-toggle" style="margin-left: 10px;" onclick="goReport(${dm.id}, ${dm.senderId});">신고</button>
						        </div>
						        <p class="small ms-3 mb-3 rounded-3 text-muted">
						            <fmt:parseDate value="${dm.regDate}" pattern="yyyy-MM-dd'T'HH:mm" var="regDate"/>
						            <fmt:formatDate value="${regDate}" pattern="yy/MM/dd HH:mm"/>
						        </p>
						    </div>
						</div>

				    </c:if>
				</c:forEach>
		 </div> <!--  카드바디 끝 !  -->
		 
 		<!--  dm 전송 인풋 시작 -->
		<form:form id="sendDmForm" action="${pageContext.request.contextPath}/dm/sendDm" method="post">
          <div class="card-footer text-muted d-flex justify-content-start align-items-center p-3">
		    <input type="hidden" name="dmRoomId" value="<%= dmRoomId %>" />
		    <div class="input-group mb-0">
		        <input type="text" id="messageInput" name="content" class="form-control" placeholder="메세지를 입력하세요." aria-label="Recipient's username" aria-describedby="button-addon2" path="content" />
		        <button class="btn btn-secondary" type="submit" id="button-addon2" style="padding-top: .55rem;">전송</button>
		    </div>
		</div>
		</form:form>
      </div>
    </div>
  </div>
</section>
<script>
	// Helper function to format date
	function formatDate(dateString) {
		const date = new Date(dateString);
		const options = { year: '2-digit', month: '2-digit', day: '2-digit', hour: 'numeric', minute: 'numeric' };
		return date.toLocaleString('en-US', options);
	}

$(document).ready(function() {
    // 페이지 로딩 시 dmDetails 데이터 받아오기

    // 삭제 버튼 클릭 이벤트 처리
    $('#btn-delete').click(deleteDm);
});

function loadDmDetails() {
	    const dmRoomId = <%= dmRoomId %>;
	    const url = '\${pageContext.request.contextPath}/dm/dmDetailList?dmRoomId=' + dmRoomId;

	    $.ajax({
	        url: url,
	        method: 'GET',
	        beforeSend: function(xhr) {
	            xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
	        },
	        dataType: "json",
	        success: function(data) {
	            const dmDetailsContainer = document.getElementById('dmDetailsContainer');
	            
	            // Clear existing content
	            dmDetailsContainer.innerHTML = '';

	            // Loop through the data and generate HTML
				ata.forEach(dm => {
					const dmDiv = document.createElement('div');

					if (dm.receiverId !== id) {
						// If the receiver ID is not the logged-in user, place on the left
						dmDiv.classList.add('d-flex', 'flex-row', 'justify-content-start', 'align-items-center', 'mb-4', 'pt-1');
						dmDiv.innerHTML = `
            <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3-bg.webp"
                 alt="avatar 1" style="width: 45px; height: 100%;">
            <div class="d-flex flex-column">
                <div class="d-flex align-items-center">
                    <p class="small p-2 ms-3 mb-1 rounded-3" style="background-color: #f5f6f7;">
                        \${dm.content}
                    </p>
                </div>
                <p class="small ms-3 mb-3 rounded-3 text-muted">\${formatDate(dm.regDate)}</p>
            </div>
        `;
					} else {
						// If the receiver ID is the logged-in user, place on the right
						dmDiv.classList.add('d-flex', 'flex-row', 'justify-content-end', 'mb-4', 'pt-1');
						dmDiv.innerHTML = `
            <div>
                <p class="small p-2 me-3 mb-1 text-white rounded-3 bg-primary">\${dm.content}</p>
                <p class="small me-3 mb-3 rounded-3 text-muted d-flex justify-content-end">\${formatDate(dm.regDate)}</p>
            </div>
            <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava4-bg.webp"
                 alt="avatar 1" style="width: 45px; height: 100%;">
        `;
					}

					dmDetailsContainer.appendChild(dmDiv);
				});
	scrollToBottom(dmDetailsContainer);
	        },
	        error: function(error) {
	            console.error('Error:', error);
	        }
	    });
	}
	// Helper function to scroll to the bottom
	function scrollToBottom(element) {
	    element.scrollTop = element.scrollHeight;
	}


	// Call the function to load DM details
	//window.onload = loadDmDetails();
	document.addEventListener('DOMContentLoaded', function() {
	    loadDmDetails(); // Initial call
	    setInterval(loadDmDetails, 1000); // Repeat every 1 second
	});

function showButton(container) {
    const button = container.querySelector('.btn');
    button.classList.remove('d-none'); // 버튼을 보이도록 클래스 제거
}

function hideButton(container) {
    const button = container.querySelector('.btn');
    button.classList.add('d-none'); // 버튼을 숨기도록 클래스 추가
}


function goReport(dmId,reportedId) {
    fetch("${pageContext.request.contextPath}/report/createDmReport?dmId=" + dmId + "&reportedId=" + reportedId)
        .then(response => {
            if (response.ok) {
                window.location.href = response.url;
            } else {
                console.error("Failed to fetch");
            }
        })
        .catch(error => {
            console.error("Error:", error);
        });
}


function goBack() {
    fetch("${pageContext.request.contextPath}/dm/dmList")
        .then(response => {
            if (response.ok) {
                window.location.href = response.url;
            } else {
                console.error("Failed to fetch");
            }
        })
        .catch(error => {
            console.error("Error:", error);
        });
}

const deleteDm = () => {
	if(confirm("채팅방을 삭제하시겠습니까?")) {
		document.dmDeleteFrm.submit();
	}
};


</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
