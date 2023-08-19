<%@page import="com.yangworld.app.domain.question.entity.Question"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="/WEB-INF/views/common/header.jsp">
    <jsp:param value="게시판" name="title"/>
</jsp:include>
    <div class="modal" id="dmModal" tabindex="-1" role="dialog" aria-labelledby="dmModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="dmModalLabel">채팅방 만들기</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"  id="closeModalButton" aria-label="Close">
                        <span aria-hidden="true"></span>
                    </button>
                </div>
                <!--  채팅방만들기 폼  -->
		       		<form:form id="sendDmForm" action="${pageContext.request.contextPath}/dm/createDmRoom" method="post">
						    <div class="modal-body">
						        <div class="card-footer text-muted d-flex justify-content-start align-items-center p-3">
						            <div class="input-group mb-3">
						                <input type="number" id="partner" name="partner" class="form-control" placeholder="받는사람 ..." aria-label="Partner's ID" aria-describedby="button-addon2" required />
						            </div>
						            
						            <div class="input-group mb-3">
						                <input type="text" id="messageInput" name="content" class="form-control" placeholder="메세지 입력..." aria-label="Recipient's username" aria-describedby="button-addon2" path="content" />
						                <button type="submit" class="btn btn-primary">SEND</button>
						                <button type="button" class="btn btn-secondary" id="closeModalButton" data-bs-dismiss="modal">Close</button>
						            </div>
						        </div>
						    </div>
						</form:form>

            </div>
        </div>
    </div>
<script>
$(document).ready(function() {
    // 모달 열기
    $('#dmModal').modal({
        backdrop: 'static',  // 클릭해도 모달이 닫히지 않도록 설정
        keyboard: false  // ESC 키로 모달을 닫지 못하도록 설정
    });

    // 모달 닫기 버튼 누를 때 페이지 이동
    $('#closeModalButton').click(function() {
        location.href = '${pageContext.request.contextPath}/dm/dmList';
    });
});

</script>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
