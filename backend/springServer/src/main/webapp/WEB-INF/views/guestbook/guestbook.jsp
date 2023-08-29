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
	<form:form action="${pageContext.request.contextPath}/member/userPage/${id}/guestbook/create.do" class="form-inline" name="createFrm" method="post">
		<input type="text" id="create" class="form-control col-sm-10 ml-1" name="content" placeholder="내용" required/>&nbsp;
		<button class="btn btn-outline-success" type="submit" onclick="alert('방명록이 등록되었습니다.')">저장</button>
	</form:form> 
	<br>
	<br>
	<table class="table">
		<thead>
			<tr>
				<th>번호</th>
				<th>작성자</th>
				<th>내용</th>
				<th>작성일</th>
				<th>수정</th>
				<th>삭제</th>
				<th>신고하기</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty guestBooks}">
				<tr>
					<td colspan="4" class="text-center">작성된 방명록이 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${not empty guestBooks}">
			<c:set var="currentIndex" value="${(page-1)*5}"/>
				<c:forEach items="${guestBooks}" var="guestbook" varStatus="vs">
				<input type ="hidden" value = "${guestbook.writerId}" id="guestbookWriter"/>
					<tr>
						<td id="index"></td>
						<td>${guestbook.nickname}</td>
						<td id="originalContent">${guestbook.content}</td>	
						<td>
						 <fmt:parseDate value="${guestbook.regDate}" pattern="yyyy-MM-dd'T'HH:mm" var="regDate"/>
				         <fmt:formatDate value="${regDate}" pattern="yy/MM/dd HH:mm"/>
						</td>
						 <c:if test="${myId eq guestbook.writerId}">
						<td>
						   <!--  <input type="text" class="form-control col-sm-10 ml-1 content" name="content" placeholder="내용" required/>&nbsp; -->
						    <button class="btn btn-outline-success updateGuestbook" id="openModalLink" name="updateGuestbook" value="${guestbook.id}" onclick="alert('방명록이 수정되었습니다.')">수정</button>
						</td>
						</c:if>
						<td>
							<button type="button" class="btn btn-outline-danger deleteGuestbook" id = "deleteGuestbook" name = "deleteGuestbook" value ="${guestbook.id}" onclick="alert('방명록이 삭제되었습니다.')">삭제</button>
						</td>
						<td>
        					<div class="guestbookReport-box">
            					<button class="btn btn-sm btn-light btn-reportGuestbook"
                   						 style="margin-left: 10px; font-size:20px;"
                   						 data-guestbook-id="${guestbook.id}" data-reported-id="${guestbook.writerId}"
                   						 data-repoter-id="${myId}">
               						 🚨
            					</button>
        					</div>
						</td>	
					</tr>
					<input type="hidden" id= "guestbookId" value ="${guestbook.id}"/>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</div>
<!-- 방명록 신고 모달  -->
<div class="modal fade" id="guestbookReportModal" tabindex="-1" role="dialog" aria-labelledby="guestbookReportModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="guestbookReportModalLabel">방명록 신고</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="reportForm">
                    <div class="form-group">
                        <label for="reportReason">신고 사유</label>
                        <select class="form-control" id="reportReason" name="reportReason">
                            <option value="inappropriate">불건전한 내용</option>
                            <option value="spam">스팸</option>
                            <option value="harassment">괴롭힘</option>
                            <!-- 추가적인 신고 사유를 여기에 추가 가능 -->
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="reportContentz">신고 내용</label>
                        <textarea class="form-control" id="reportContent" name="reportContent" rows="3"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
                <button type="button" class="btn btn-primary" id="confirmReportButton">신고</button>
            </div>
        </div>
    </div>
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
                        <a class="page-link" href="${pageContext.request.contextPath}/member/userPage/${id}/guestbook/guestbook?page=${pageStatus.index}">${pageStatus.index}</a>
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
            const guestbookId = document.querySelector("#guestbookId").value;
            console.log(guestbookId);
            // Ajax 요청 등 수정 작업 수행
            $.ajax({
				url : "${pageContext.request.contextPath}/member/userPage/${id}/guestbook/update.do",
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
    	   const guestbookWriter = document.querySelector("#guestbookWriter").value;
    	   console.log(value);
    	   console.log(guestbookWriter);
	    	 $.ajax({
	    		url : "${pageContext.request.contextPath}/member/userPage/${id}/guestbook/delete.do",
				data : {
					deleteGuestbook : value,
					guestbookWriter : guestbookWriter
				},
				beforeSend: function(xhr) {
				        xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
				},
				method : "POST",
				dataType : "json",
				success: function(responseData) {
	                const {msg} = responseData;
	                console.log("responseData" ,responseData);
	                console.log("msg" ,msg);
	                if (msg != null) {
	                    const tr = e.target.parentElement.parentElement;
	                    location.reload();
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

// 신고 모달창

document.querySelectorAll(".btn-reportGuestbook").forEach(btn => {
	
    btn.onclick = (e) => {
    	console.log(e);
	    // 'guestbook report' 버튼 클릭 시 모달 창 열기
	    $(".btn-reportGuestbook").click(function () {
	        var guestbookId = $(this).data("guestbook-id");
	        var reportedId = $(this).data("reported-id");
	        var reporterId = $(this).data("repoter-id"); 
			console.log(guestbookId);
			console.log(reportedId);
			console.log(reporterId);
	        
	        // 모달 창 열기
	        $("#guestbookReportModal").modal("show");
	
	        // '신고' 버튼 클릭 시 AJAX 요청 전송
	        $("#confirmReportButton").click(function () {
	            var content = $("#reportContent").val();
				console.log(content);
	            // AJAX 요청 보내는 부분
	            $.ajax({
	                method: "POST",
	                url: "${pageContext.request.contextPath}/member/userPage/${id}/insertReportGuestBook.do",
	                data: {
	                    guestbookId: guestbookId,
	                    reportedId: reportedId,
	                    reporterId: reporterId,
	                    content: content
	                },
	                beforeSend: function (xhr) {
	                    xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
	                },
	                success: function (response) {
	                    alert("신고가 접수되었습니다.");
	                    $("#guestbookReportModal").modal("hide");
	                },
	                error: function (error) {
	                    alert("Error reporting: " + error.responseText);
	                }
	            });
	        });
	    });
    }
});
</script>


