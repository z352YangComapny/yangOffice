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
    <div class="container py-5" >
        <div class="row d-flex justify-content-center" style="height: 690px; width:1300px;">
            <div class="col-md-10 col-lg-8 col-xl-6" style="height: 500px; width:900px;">
                <div class="card" id="chat2" style="top:10%;">
                    <div class="card-header d-flex justify-content-between align-items-center p-3">
                        <h5 class="mb-0">SSOY WOLRD</h5>
                    </div>
                    <div class="card-body" data-mdb-perfect-scrollbar="true"
                         style="position: relative; height: 400px; overflow-y: auto;">
                        <div id="chat-div"></div>
                    </div>
                    <form id="sendChatForm">
                        <div class="card-footer text-muted d-flex justify-content-start align-items-center p-3">
                            <div class="input-group mb-0">
                                <input type="text" id="messageInput" class="form-control"
                                       placeholder="메세지를 입력하세요." aria-label="Recipient's username"
                                       aria-describedby="button-addon2"/>
                                <button class="btn btn-secondary" type="button" id="sendButton"
                                        style="padding-top: .55rem;">전송
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
<script>

    let stompClient;
    document.addEventListener('DOMContentLoaded', () => {
    ChatConnect();

    const sendButton = document.getElementById('sendButton');
    const messageInput = document.getElementById('messageInput');
    const memberId = document.getElementById('memberId').value;

    sendButton.addEventListener('click', () => {
        const message = messageInput.value;
        if (message.trim() !== '') {
            const payload = {
                id: memberId,
                content: message
            };
            console.log(payload)
            stompClient.send("/app/chat", {}, JSON.stringify(payload));
            messageInput.value = '';
        }
    });
});


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

</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
