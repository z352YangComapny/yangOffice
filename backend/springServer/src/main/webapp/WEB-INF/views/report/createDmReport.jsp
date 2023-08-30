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
<div class="container">
    <div class="row">
        <div class="col-md-8 offset-md-2" style="margin-bottom: 11vw;">
            <div class="card" style="margin-top: 200px;">
                <div class="card-header bg-dark text-white">
                    <h5 class="card-title">신고하기</h5>
                </div>
                <div class="card-body">
                    <form:form id="reportDmForm" action="${pageContext.request.contextPath}/report/insertReportDm" method="post">
                        <div class="mb-4">
                            <label for="dmId" class="form-label">신고할 DM 아이디</label>
                            <input type="number" class="form-control" id="dmId" name="dmId" value="<%= dmId %>" required>
                        </div>
                        <input type="hidden" name="reportedId" value="<%= reportedId %>">
                        <div class="mb-4">
                            <label for="messageInput" class="form-label">신고사유 입력</label>
                            <textarea class="form-control" id="messageInput" name="content" rows="6" required></textarea>
                        </div>
                        <button type="submit" class="btn btn-warning btn-lg">신고</button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>