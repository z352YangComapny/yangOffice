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

    <div class="row d-flex justify-content-center" style="height: 690px; width:1300px;">
      <div class="col-md-10 col-lg-8 col-xl-6">

        <div class="card" id="chat2" style="top:10%;">
          <div class="card-header d-flex justify-content-between align-items-center p-3">
            <h5 class="mb-0">Direct Message</h5>
            <button type="button" class="btn btn-primary btn-sm" data-mdb-ripple-color="dark" onclick="goBack();">뒤로가기
             </button>
          </div>
          <div class="card-body" data-mdb-perfect-scrollbar="true" style="position: relative; height: 400px; overflow-y: auto;">

           <%--  <div class="d-flex flex-row justify-content-start">
              <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3-bg.webp"
                alt="avatar 1" style="width: 45px; height: 100%;">
              <div>
              <c:forEach  items="${dmDetails}" var="dm">
                <p class="small p-2 ms-3 mb-1 rounded-3" style="background-color: #f5f6f7;">
                	  ${dm.senderId != Id ? dm.content : ''}
                </p> <!-- 상대방이 보낸거  -->
                <p class="small ms-3 mb-3 rounded-3 text-muted">${dm.regDate }</p> <!-- 상대방 전송 시간 -->
                </c:forEach>
              </div>
            </div>

            <div class="divider d-flex align-items-center mb-4">
              <p class="text-center mx-3 mb-0" style="color: #a2aab7;">Today</p>
            </div>

            <div class="d-flex flex-row justify-content-end mb-4 pt-1">
              <div>
                <p class="small p-2 me-3 mb-1 text-white rounded-3 bg-primary">Hiii, I'm good.</p> <!-- 내가 보낸거  -->
                <p class="small me-3 mb-3 rounded-3 text-muted d-flex justify-content-end">00:06</p>   <!-- 내가 보낸 시간  -->
              </div>
              <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava4-bg.webp"
                alt="avatar 1" style="width: 45px; height: 100%;">
            </div> --%>


<c:forEach items="${dmDetails}" var="dm">
        <c:if test="${dm.receiverId == Id}">
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
        <c:if test="${dm.receiverId != Id}">
            <div class="d-flex flex-row justify-content-end mb-4 pt-1">
                <div>
                    <p class="small p-2 me-3 mb-1 text-white rounded-3 bg-primary">${dm.content}</p>
                    <p class="small me-3 mb-3 rounded-3 text-muted d-flex justify-content-end">${dm.regDate}</p>
                </div>
                <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava4-bg.webp"
                     alt="avatar 1" style="width: 45px; height: 100%;">
            </div>
        </c:if>
</c:forEach>


          </div>
          <div class="card-footer text-muted d-flex justify-content-start align-items-center p-3">
            <div class="input-group mb-0">
              <input type="text" class="form-control" placeholder="Type message"
                aria-label="Recipient's username" aria-describedby="button-addon2" />
              <button class="btn btn-secondary" type="button" id="button-addon2" style="padding-top: .55rem;">
                전송
              </button>
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
