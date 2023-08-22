<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="방명록" name="title"/>
</jsp:include>

<style>
div#guestbook-container{width:60%; margin:0 auto; text-align:center;}
</style>
<div id="guestbook-container">
	<form:form action="${pageContext.request.contextPath}/guestbook/create.do" class="form-inline" method="post">
		<input type="text" class="form-control col-sm-10 ml-1" name="memberId" placeholder="memberId" required/>&nbsp;
		<input type="text" class="form-control col-sm-10 ml-1" name="content" placeholder="내용" required/>&nbsp;
		<button class="btn btn-outline-success" type="submit">저장</button>
	</form:form> 
	
	<table class="table">
		<thead>
			<tr>
				<th>번호</th>
				<th>작성자</th>
				<th>내용</th>
				<th>작성일</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty guestBooks}">
				<tr>
					<td colspan="4" class="text-center">작성된 방명록이 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${not empty guestBooks}">
				<c:forEach items="${guestBooks}" var="guestbook" varStatus="vs">
					<tr>
						<td>${guestbook.id}</td>
						<td>${guestbook.writerId}</td>
						<td>${guestbook.content}</td>
						<td>${guestbook.regDate}</td>
						<td>
						    <input type="text" class="form-control col-sm-10 ml-1 content" name="content" placeholder="내용" required/>&nbsp;
						    <button class="btn btn-outline-success updateGuestbook" id="updateGuestbook" name="updateGuestbook" value="${guestbook.id}">수정</button>
						</td>
						<td>
							<button type="button" class="btn btn-outline-danger deleteGuestbook" id = "deleteGuestbook" name = "deleteGuestbook" value ="${guestbook.id}">삭제</button>
						</td>
						<td>
							<%-- <form:form action="${pageContext.request.contextPath}/report/insertReportGuestBook.do" class="form-inline" method="post">
 							    <input type="text" class="form-control col-sm-10 ml-1 reportContent" name="reportContent" placeholder="사유" required/>&nbsp;
							    <input type="hidden" class="form-control col-sm-10 ml-1 reportedId" name="reportedId" value="${guestbook.writerId }"/>&nbsp; 
							</form:form> --%>
								<button type="submit" class="btn reportGuestbook" id = "reportGuestbook" name = "reportGuestbook" value ="${guestbook.id}" onclick="goReport(${guestbook.id}, ${guestbook.writerId});">🚨</button>
						</td>	
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</div>
<script>
document.querySelectorAll(".updateGuestbook").forEach(btn=>{
	btn.onclick = (e) =>{
		const value = e.target.value;
		const content = document.querySelector(".content").value;
		console.log(value);
		console.log(content);
		
			$.ajax({
				url : "${pageContext.request.contextPath}/guestbook/update.do",
				data : {
					updateGuestbook : value,
					content : content
				},
				beforeSend : function(xhr){
					xhr.setRequestHeader('${_csrf.headerName}','${_csrf.token}');
				},
				method:"POST",
				dataType:"json",
				success(responseData){
					console.log(responseData);
					const {updateGuestbook} = responseData;
					const updateGuestbookCell = e.target.closest("tr").querySelector(".content");
					updateGuestbookCell.textContent = content;
					location.reload();
				}
				});
	}
});

document.querySelectorAll(".deleteGuestbook").forEach(btn => {
	
    btn.onclick = (e) => {
      
    	   const value = e.target.value;
    	   console.log(value);
	    	 $.ajax({
	    		url : "${pageContext.request.contextPath}/guestbook/delete.do",
				data : {
					deleteGuestbook : value
				},
				beforeSend: function(xhr) {
				        xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
				},
				method : "POST",
				dataType : "json",
				success(responseData){
					
					console.log(responseData);
					const {result} = responseData;
					if(result>0){
						const tr = e.target.parentElement.parentElement;
	                    tr.remove(); 
					}
				}
	    	}); 
       
      
    };
});

function goReport(guestbookId,reportedId) {
    fetch("${pageContext.request.contextPath}/report/guestbookReport?guestbookId=" + guestbookId + "&reportedId=" + reportedId)
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


