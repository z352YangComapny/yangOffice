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
<meta name="_csrf" th:content="${_csrf.token}" />
<meta name="_csrf_header" th:content="${_csrf.headerName}" />
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
      <div class="col-md-10 col-lg-8 col-xl-6">

        <div class="card" id="chat2" style="top:10%;">
          <div class="card-header d-flex justify-content-between align-items-center p-3">
            <h5 class="mb-0">Direct Message</h5>
            <button type="button" class="btn btn-primary btn-sm" data-mdb-ripple-color="dark" onclick="goBack();">뒤로가기
             </button>
          </div>
          <div class="card-body" data-mdb-perfect-scrollbar="true" style="position: relative; height: 400px; overflow-y: auto;">
				 <c:set var="userId" value="${userId}" scope="page" />
				<c:forEach items="${dmDetails}" var="dm" varStatus="loop">
				    <c:if test="${dm.senderId == userId}">
				        <div class="d-flex flex-row justify-content-end mb-4 pt-1">
				            <div>
				                <p class="small p-2 me-3 mb-1 text-white rounded-3 bg-primary">${dm.content}</p>
				                <p class="small me-3 mb-3 rounded-3 text-muted d-flex justify-content-end">${dm.regDate}</p>
				            </div>
				            <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava4-bg.webp"
				                 alt="avatar 1" style="width: 45px; height: 100%;">
				        </div>
				    </c:if>
				    <c:if test="${dm.senderId != userId and loop.first}">
				        <c:set var="firstDmSenderId" value="${dm.senderId}" scope="page" />
				    </c:if>
				    <c:if test="${dm.senderId != userId or dm.senderId == firstDmSenderId}">
				        <div class="d-flex flex-row justify-content-start">
				            <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3-bg.webp"
				                 alt="avatar 1" style="width: 45px; height: 100%;">
				            <div>
				                <p class="small p-2 ms-3 mb-1 rounded-3" style="background-color: #f5f6f7;">
				                    ${dm.content}
				                </p>
				                <p class="small ms-3 mb-3 rounded-3 text-muted">${dm.regDate}</p>
				            </div>
				        </div>
				    </c:if>
				</c:forEach>
		 </div> <!--  카드바디 끝 !  -->
		 
 		<!--  dm 전송 인풋 시작 -->
          <div class="card-footer text-muted d-flex justify-content-start align-items-center p-3">
		<form:form id="sendDmForm" >
		    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		    <input type="hidden" name="dmRoomId" value="${dmRoomId}" />
		    <div class="input-group mb-0">
		        <input type="text" id="messageInput" class="form-control" placeholder="Type message" aria-label="Recipient's username" aria-describedby="button-addon2" path="content" />
		        <button class="btn btn-secondary" type="submit" id="button-addon2" style="padding-top: .55rem;">전송</button>
		    </div>
		</form:form>
		</div>


      </div>
    </div>

  </div>
</section>
<script>
document.sendDmForm.onsubmit = (e) => {
	const frm = document.sendDmForm;
	const dmRoomId = <%= dmRoomId %>
	frm.action = `${pageContext.request.contextPath}/dm/sendDm?dmRoomId=\${dmRoomId}`;
	frm.method = "POST";
	frm.submit();
};
<%-- document.addEventListener("DOMContentLoaded", function() {
    const sendDmForm = document.getElementById("sendDmForm");
    const dmRoomId = <%= dmRoomId %>; // dmRoomId 변수 설정

    sendDmForm.addEventListener("submit", function(event) {
        event.preventDefault(); // 기본 동작 중단 (페이지 새로고침 방지)

        const messageInput = document.getElementById("messageInput");

        // 입력된 메시지 가져오기
        const message = messageInput.value;

        // 서버로 전송할 데이터 준비
        const data = {
            dmRoomId: dmRoomId,
            content: message
        };

        // 서버로 데이터 전송
        fetch("${pageContext.request.contextPath}/dm/sendDm?dmRoomId=" + dmRoomId, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "X-CSRF-TOKEN": document.querySelector('meta[name="csrf-token"]').getAttribute('content')
            },
            body: JSON.stringify(data)
        })
        .then(response => response.json())
        .then(result => {
            // 서버 응답 처리 (예: 화면 갱신 등)
            console.log(result);
        })
        .catch(error => {
            // 에러 처리
            console.error(error);
        });
    });
});
 --%>

/* function sendDm() {
    const message = $("#messageInput").val(); // 입력된 메시지 가져오기
    
    const currentTime = new Date();
    const formattedTime = currentTime.toLocaleString(); // 시간을 원하는 형식으로 포맷팅
    
    $.ajax({
        type: "POST",
        url: "${pageContext.request.contextPath}/dm/sendDm?dmRoomId="+ dmRoomId, // sendDm URL 설정
        data: { message: message },
        success: function(response) {
            // 메시지 전송이 성공한 경우, 채팅 창에 메시지 추가
            console.log("message=" + response);
            const chatContainer = $(".card-body");
            const newMessage = `
                <div class="d-flex flex-row justify-content-end mb-4 pt-1">
                    <div>
                        <p class="small p-2 me-3 mb-1 text-white rounded-3 bg-primary">${message}</p>
                        <p class="small me-3 mb-3 rounded-3 text-muted d-flex justify-content-end">${formattedTime}</p>
                    </div>
                    <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava4-bg.webp"
                         alt="avatar 1" style="width: 45px; height: 100%;">
                </div>
            `;
            chatContainer.append(newMessage);
        },
        error: function() {
            console.error("Error sending message");
        }
    });
} */

function goBack() {
    // 이전 페이지로 돌아가기
    history.back();
}



</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
