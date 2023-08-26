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
.form-inline{display:block;}
</style>
<br><br>
<div id="guestbook-container">
<h6>✨방명록 남기기✨</h6>
	<form:form action="${pageContext.request.contextPath}/guestbook/create.do" class="form-inline" method="post">
		<input type="text" class="form-control col-sm-10 ml-1" name="memberId" placeholder="memberId" required/>&nbsp;
		<input type="text" class="form-control col-sm-10 ml-1" name="content" placeholder="내용" required/>&nbsp;
		<button class="btn btn-outline-success" type="submit">저장</button>
	</form:form> 
	<br><br>
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
						<td>${guestbook.nickname}</td>
						<td id="originalContent">${guestbook.content}</td>
						<td>${guestbook.regDate}</td>
						<td>
						   <!--  <input type="text" class="form-control col-sm-10 ml-1 content" name="content" placeholder="내용" required/>&nbsp; -->
						    <button class="btn btn-outline-success updateGuestbook" id="openModalLink" name="updateGuestbook" value="${guestbook.id}">수정</button>
						</td>
						<td>
							<button type="button" class="btn btn-outline-danger deleteGuestbook" id = "deleteGuestbook" name = "deleteGuestbook" value ="${guestbook.id}">삭제</button>
						</td>
						<td>
							<%-- <form:form action="${pageContext.request.contextPath}/report/insertReportGuestBook.do" class="form-inline" method="post">
 							    <input type="text" class="form-control col-sm-10 ml-1 reportContent" name="reportContent" placeholder="사유" required/>&nbsp;
							    <input type="hidden" class="form-control col-sm-10 ml-1 reportedId" name="reportedId" value="${guestbook.writerId }"/>&nbsp; 
							</form:form> --%>
								<button type="submit" class="btn reportGuestbook" id = "reportGuestbook" name = "reportGuestbook" value ="${guestbook.id}" onclick="goReport(${guestbook.id}, ${guestbook.nickname});">🚨</button>
						</td>	
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</div>

<%--방명록 수정 모달 --%>
	<div class="modal" id="guestbookUpdateModal">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">방명록 수정</h5>
					<button type="button" class="btn-close close-modal" data-bs-dismiss="modal" aria-label="Close" id="closeModalButton">
						<span aria-hidden="true"></span>
					</button>
				</div>
				<div class="modal-body">
					<div class="form-group row">
						<p>수정할 내용을 입력해주세요</p>
						<label for="content" class="col-sm-2 col-form-label">내용</label>
						<div class="d-flex flex-row">
							<input type="text" class="form-control" id="content" name="content" style="width:500px; margin-right:5px;">
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary update" >수정하기</button>
					<button type="button" class="btn btn-secondary close-modal"  data-bs-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
		<div style="display: flex; justify-content: center; margin:80px">
    <ul class="pagination">
        <li class="page-item disabled">
            <a class="page-link" href="#">&laquo;</a>
        </li>
        <c:forEach begin="1" end="${totalPages}" varStatus="pageStatus">
            <c:choose>
                <c:when test="${page eq pageStatus.index}">
                    <li class="page-item active">
                        <a class="page-link" href="#">${pageStatus.index}</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item">
                        <a class="page-link" href="${pageContext.request.contextPath}/guestbook/guestbook.do?page=${pageStatus.index}">${pageStatus.index}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <li class="page-item">
            <a class="page-link" href="#">&raquo;</a>
        </li>
    </ul>
</div>
<script>

document.querySelectorAll(".updateGuestbook").forEach(btn => {
    btn.onclick = (e) => {
        const guestbookId = e.target.value; // 수정 버튼의 value 속성에 게시물의 id가 들어가도록 설정되어야 합니다.
        const modal = document.getElementById("guestbookUpdateModal");
        const contentInput = modal.querySelector("#content");
        
        // 모달 내용 초기화 및 기존 내용 채우기
        contentInput.value = ""; // 모달 내용 초기화
        const guestbookContent = e.target.parentElement.parentElement.querySelector("#originalContent").textContent;
        contentInput.value = guestbookContent; // 기존 내용 채우기
        
        // 모달 열기
        modal.style.display = "block";
        modal.classList.add("show");
        
        const closeBtn = [...modal.querySelectorAll(".close-modal")]
        for(let i = 0 ; i<closeBtn.length ; i++){
        	closeBtn[i].onclick = ()=> {
        		modal.style.display="none";
        		modal.classList.remove("show");
       	
        	}
        }
        
        // 모달의 수정 버튼 클릭 이벤트 핸들러
        modal.querySelector(".update").onclick = () => {
            const newContent = contentInput.value;
            // Ajax 요청 등 수정 작업 수행
            $.ajax({
				url : "${pageContext.request.contextPath}/guestbook/update.do",
				data : {
					updateGuestbook : guestbookId,
					content : newContent
				},
				beforeSend : function(xhr){
					xhr.setRequestHeader('${_csrf.headerName}','${_csrf.token}');
				},
				method:"POST",
				dataType:"json",
				success(responseData){
					console.log(responseData);
					const {updateGuestbook} = responseData;
					const updateGuestbookCell = e.target.parentElement.parentElement.querySelector("#originalContent");
					updateGuestbookCell.textContent = newContent;
					location.reload();
		            // 모달 닫기
		            modal.style.display = "none";
		            modal.classList.remove("show");
				}
				});
            
        };
    };
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
				success: function(responseData) {
	                console.log(responseData);
	                const { result } = responseData;
	                if (result > 0) {
	                    const tr = e.target.parentElement.parentElement;
	                    tr.remove();
	                } else {
	                    console.error("Delete operation failed.");
	                }
	            },
	            error: function(xhr, textStatus, errorThrown) {
	            	 if (xhr.status === 403) {
	                     const errorResponse = JSON.parse(xhr.responseText);
	                     alert(errorResponse.result); // 실패한 경우 에러 메시지를 alert으로 띄움
	                 } else {
	                     console.error("AJAX request failed:", textStatus, errorThrown);
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


