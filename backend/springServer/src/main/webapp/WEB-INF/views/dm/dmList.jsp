<%@page import="com.yangworld.app.domain.question.entity.Question"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="게시판" name="title"/>
</jsp:include>
<section style="background-color: #eee;">
  <div class="container py-5">
    <div class="row justify-content-center" style="height: 690px; width:1300px; overflow-y: auto;">
  		<div class="col-md-6 col-lg-5 col-xl-4 mb-4 mb-md-0" style="height: 500px; width:900px;">
        <h5 class="font-weight-bold mb-3 text-center text-lg-start">Direct Message</h5>
	        <div class="card">
	          <div class="card-body" data-mdb-perfect-scrollbar="true" style="position: relative; height: 600px; overflow-y: auto;">
	          
	          <!-- 채팅방 create, delete button -->
	          <button type="button" class="btn btn-primary" id="btn-add" style="margin: 10px;"> NEW DM ROOM + </button>
	       
	           <!-- dm List 시작 -->
	            <ul class="list-unstyled mb-0">
				    <c:forEach items="${dmList}" var="dm" varStatus="loop">
				                <li class="p-2 border-bottom" style="background-color: #fff;">
				                    <a href="${pageContext.request.contextPath}/dm/dmDetail?dmRoomId=${dm.dmRoomId}" class="d-flex justify-content-between">
				                        <div class="d-flex flex-row">
				                            <img src="${pageContext.request.contextPath}/resources/upload/attachment/profile/${dm.renamedFileName}" 
				                                 class="rounded-circle d-flex align-self-center me-3 shadow-1-strong" width="60">
				                            <div class="pt-1">
				                                <p class="fw-bold mb-0">${dm.nickname} ( ${dm.name} )</p>
				                                <p class="small text-muted">${dm.content}</p>
				                            </div>
				                        </div>
				                        <div class="pt-1">
				                            <p class="small text-muted mb-1">
				                                <fmt:parseDate value="${dm.regDate}" pattern="yyyy-MM-dd'T'HH:mm" var="regDate"/>
				                                <fmt:formatDate value="${regDate}" pattern="yy/MM/dd HH:mm"/>
				                            </p>
				                            <!-- <span class="badge bg-light float-end" id="dmListSpan">🔵</span> -->
				                        </div>
				                    </a>
				                </li>
				    </c:forEach>
				</ul> <!--  dm List 끝 -->
	          </div>
	        </div>
	      </div>
	    </div>
	  </div>
</section>
<script>
document.querySelector("#btn-add").onclick = () => {
	location.href = '${pageContext.request.contextPath}/dm/dmCreate';
};
function goBack() {
    // 이전 페이지로 돌아가기
    history.back();
}

</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
