<%@page import="com.yangworld.app.domain.question.entity.Question"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
String dmIdParam = request.getParameter("dmId");
int dmId = Integer.parseInt(dmIdParam);

String reportedIdParam = request.getParameter("reportedId");
int reportedId = Integer.parseInt(reportedIdParam);
%>
<jsp:include page="/WEB-INF/views/common/header.jsp">
    <jsp:param value="게시판" name="title"/>
</jsp:include>
    <script>
    $(document).ready(function() {
        $('#reportDmModal').modal({
            backdrop: 'static',  
            keyboard: false  
        });

        $('#closeModalButton').click(function() {
            location.href = '${pageContext.request.contextPath}/dm/dmList';
        });
    });
    </script>

	<!--  신고 모달 시작  -->
    <div class="modal" id="reportDmModal" tabindex="-1" role="dialog" aria-labelledby="dmModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
            <div class="modal-content" style="background-color: #585757;">
                <div class="modal-header">
                    <h5 class="modal-title" id="dmModalLabel" style="color : white;">신고하기</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"  id="closeModalButton" aria-label="Close" >
                        <span aria-hidden="true" ></span>
                    </button>
                </div>
		       		<form:form id="reportDmForm" action="${pageContext.request.contextPath}/report/insertReportDm" method="post">
						    <div class="modal-body"> 
						        <div class="card-footer text-muted d-flex justify-content-start align-items-center p-3" style="flex-wrap: wrap;">
						            <div class="input-group mb-3">
						                <input type="number" id="dmId" name="dmId" class="form-control" placeholder="신고할 DM 아이디"  value="<%= dmId %>" aria-label="Partner's ID" aria-describedby="button-addon2" required />
						                 <input type="hidden" name="reportedId" value="<%= reportedId %>" />
						            </div>
						            <br/>
						            <div class="input-group mb-3">
						                <input type="text" id="messageInput" name="content" class="form-control" placeholder="신고사유 입력" 
						                style="height: 200px;" aria-label="Recipient's username" aria-describedby="button-addon2" path="content" />
						                <button type="submit" class="btn btn-warning">신고</button>
						            </div>
						        </div>
						    </div>
						</form:form>

            </div>
        </div>
    </div>
    <!--  신고 모달 끝  -->

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
