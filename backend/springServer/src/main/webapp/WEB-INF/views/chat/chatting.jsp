<%@page import="com.yangworld.app.domain.question.entity.Question" %>
<%@page import="java.util.List" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="/WEB-INF/views/common/header.jsp">
    <jsp:param value="게시판" name="title"/>
</jsp:include>
 <sec:authentication property="principal" var="dmMember"/>
  <input type='hidden' id='memberId' value='${dmMember.id}' />
<section style="background-color: #eee;">
    <div class="container py-5">

        <div class="row d-flex justify-content-center" style="height: 690px; width:1300px;">
            <div class="col-md-10 col-lg-8 col-xl-6" style="height: 500px; width:900px;">

                <div class="card" id="chat2" style="top:10%;">
                    <div class="card-header d-flex justify-content-between align-items-center p-3">
                        <h5 class="mb-0">SSOY WOLRD</h5>
                    </div>
                    
                    <div class="card-body" data-mdb-perfect-scrollbar="true"
                         style="position: relative; height: 400px; overflow-y: auto;">
                        <div id="chat-div"></div>
                    </div> <!--  카드바디 끝 !  -->

                    <!--  dm 전송 인풋 시작 -->
                    <form:form id="sendChatForm" action="${pageContext.request.contextPath}/chat/sendChat" method="post">
                        <div class="card-footer text-muted d-flex justify-content-start align-items-center p-3">
                            <div class="input-group mb-0">
                                <input type="text" id="messageInput" name="chatContent" class="form-control"
                                       placeholder="메세지를 입력하세요." aria-label="Recipient's username"
                                       aria-describedby="button-addon2" path="chatContent"/>
                                <button class="btn btn-secondary" type="submit" id="button-addon2"
                                        style="padding-top: .55rem;">전송
                                </button>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
</section>
<script>

<c:choose>
<c:when test="${not empty dmMember}">
    const memberId = ${dmMember.id}; // 인증된 멤버의 ID를 가져옵니다.
    document.addEventListener('DOMContentLoaded', () => {
    	console.log("챗연결함니다");
    	loadDmDetails();
        ChatConnect(memberId);
    });
</c:when>
<c:otherwise>
console.log("로그인되지 않았습니다. Chat을 구독하지 않습니다.");
</c:otherwise>
</c:choose>

    // Helper function to format date
    function formatDate(dateString) {
        const date = new Date(dateString);
        const options = {year: '2-digit', month: '2-digit', day: '2-digit', hour: 'numeric', minute: 'numeric'};
        return date.toLocaleString('en-US', options);
    }

    $(document).ready(function () {
        // 페이지 로딩 시 dmDetails 데이터 받아오기

    });
    
    function showButton(container) {
        const button = container.querySelector('.btn');
        button.classList.remove('d-none');
    }

    function hideButton(container) {
        const button = container.querySelector('.btn');
        button.classList.add('d-none'); 
    }


function loadDmDetails() {
        const url = '${pageContext.request.contextPath}/chat/chatting';

        $.ajax({
            url: url,
            method: 'GET',
            beforeSend: function (xhr) {
                xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
            },
            dataType: "json",
            success: function (data) {
                const chatContainer = document.getElementById('chat-div');

                // Clear existing content
                chatContainer.innerHTML = '';

                // Loop through the data and generate HTML
                data.forEach(chat => {
                    const chatDiv = document.createElement('div');
                    if (chat.memberId != id) {
                        console.log("왼짝")
                        // If the receiver ID is not the logged-in user, place on the left
                        chatDiv.classList.add('d-flex', 'flex-row', 'justify-content-start', 'align-items-center', 'mb-4', 'pt-1');
                        chatDiv.innerHTML = `
					            <img src="${pageContext.request.contextPath}/resources/upload/attachment/profile/\${dm.renamedFileName}"
					                 alt="avatar 1" style="width: 45px; height: 100%;">
					            <div class="d-flex flex-column">
					                	<p style="font-size : 14px; margin-bottom:5px; font-weight: bold; margin-left: 10px">\${chat.memberId}</p>
					                <div class="d-flex align-items-center" onmouseover="showButton(this)" onmouseout="hideButton(this)">
					                    <p class="small p-2 ms-3 mb-1 rounded-3" style="background-color: #f5f6f7;">
					                        \${chat.chatContent}
					                    </p>
					                    <button class="btn btn-sm btn-light d-none btn-toggle" style="margin-left: 10px; font-size:20px;"  onclick="goReport(\${dm.id}, \${dm.senderId});">🚨</button>
					                </div>
					                <p class="small ms-3 mb-3 rounded-3 text-muted">\${formatDate(chat.sendDate)}</p>
					            </div>
					        `;
					        
                    } else {
                        console.log("오른짝")
                        // If the receiver ID is the logged-in user, place on the right
                        chatDiv.classList.add('d-flex', 'flex-row', 'justify-content-end', 'mb-4', 'pt-1');
                        chatDiv.innerHTML = `
			            <div>
			                <p class="small p-2 me-3 mb-1 text-white rounded-3 bg-primary">\${dm.content}</p>
			                <p class="small me-3 mb-3 rounded-3 text-muted d-flex justify-content-end">\${formatDate(dm.regDate)}</p>
			            </div>
			        `;
                    }

                    chatContainer.appendChild(chatDiv);
                });
                scrollToBottom(chatContainer);
            },
            error: function (error) {
                console.error('Error:', error);
            }
        });
    }

    function scrollToBottom(element) {
        element.scrollTop = element.scrollHeight;
    }

    function goReport(chatId, reportedId) {
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

</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
