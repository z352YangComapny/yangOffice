<%@page import="com.yangworld.app.domain.question.entity.Question"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
%>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="게시판" name="title"/>
</jsp:include>
<style>
/*글쓰기버튼*/
input#btn-add{float: left;
   						 margin: 40px;}
</style>
<section style="background-color: #eee;">
  <div class="container py-5">
    <div class="row justify-content-center" style="height: 690px; width:1300px; overflow-y: auto;">
  		<div class="col-md-6 col-lg-5 col-xl-4 mb-4 mb-md-0" style="height: 500px; width:900px;">
        <h5 class="font-weight-bold mb-3 text-center text-lg-start">Direct Message</h5>
	        <div class="card">
	          <div class="card-body">
	            <ul class="list-unstyled mb-0">
				    <c:set var="myDmListVar" value="${myDmList}" />
				    <c:set var="myDmsVar" value="${myDms}" />
				
				    <c:forEach items="${myDmListVar}" var="dm" varStatus="loop">
				    <c:set var="showId" value="${dm.receiverId != Id ? dm.senderId : dm.receiverId}" />
				                <li class="p-2 border-bottom" style="background-color: #fff;">
				                    <a href="${pageContext.request.contextPath}/dm/dmDetail?dmRoomId=${dm.dmRoomId}" class="d-flex justify-content-between">
				                        <div class="d-flex flex-row">
				                            <img src="https://mdbcdn.b-cdn.net/img/Photos/Avatars/avatar-8.webp" alt="avatar"
				                                 class="rounded-circle d-flex align-self-center me-3 shadow-1-strong" width="60">
				                            <div class="pt-1">
				                                <p class="fw-bold mb-0">${showId}</p>
				                                <p class="small text-muted">${dm.content}</p>
				                            </div>
				                        </div>
				                        <div class="pt-1">
				                            <p class="small text-muted mb-1">
				                                <fmt:parseDate value="${dm.regDate}" pattern="yyyy-MM-dd'T'HH:mm" var="regDate"/>
				                                <fmt:formatDate value="${regDate}" pattern="yy/MM/dd HH:mm"/>
				                            </p>
				                            <span class="badge bg-danger float-end">1</span>
				                        </div>
				                    </a>
				                </li>
				    </c:forEach>
				</ul>
	          </div>
	        </div>
	      </div>
	    </div>
	  </div>
</section>
<script>
document.querySelector("#btn-add").onclick = () => {
	location.href = '${pageContext.request.contextPath}/question/questionCreate';
};
function goBack() {
    // 이전 페이지로 돌아가기
    history.back();
}
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
