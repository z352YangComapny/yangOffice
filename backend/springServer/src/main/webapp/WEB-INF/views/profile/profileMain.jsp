<%@page import="com.yangworld.app.domain.profile.entity.ProfileDetails"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<style>
	/* 이미지 최대 너비와 최대 높이 설정 */
	#selectedImage {
		width: 350px;
		height: 250px;
		border-radius: 10%;
	}

	.profile-box {
		/* border: 2px solid #ccc; */
		padding: 10px;
		border-radius: 5px;
		margin-top: 20px;
		text-align: center;
		width: 350px;
	}
	.emoji-image{
		width: 30px;
	}


</style>
<sec:authorize access="isAuthenticated()">
	<%-- 자바 코드를 사용하여 principalName 출력하기 --%>
	<%-- <% String principalName = (String) request.getAttribute("principalName"); %>
    <p>Principal Name: ${principalName}</p> --%>
	<sec:authentication property="principal" var="loginMember"/>
	<%-- <p>principalId: ${loginMember.id}</p>
    <p>profileId: ${profile.id}</p> --%>
	<%-- <p>gender : ${loginMember.gender}</p> --%>
	<div class="container mt-5" >
		<h1 class="mb-4"></h1>
		<div class="form-group" style="height: 230px; margin-bottom: 3vw;">
			<label for="upFile"></label>
			<c:choose>
				<c:when test="${not empty profileAttachments}">
					<c:forEach items="${profileAttachments}" var="attachment">
						<img id="selectedImage" class="preview-image" src="${pageContext.request.contextPath}/resources/upload/attachment/${attachment.renamedFilename}" alt="프로필 사진" >
					</c:forEach>
				</c:when>
				<c:otherwise>
					<!-- 프로필 사진이 없을 경우 기본 이미지 또는 아무것도 표시하거나 로직을 추가해주세요. -->
					<img id="selectedImage" class="preview-image rounded-circle" src="<c:url value='/resources/upload/attachment/default.jpg' />" alt="기본 프로필 사진" >
				</c:otherwise>
			</c:choose>
		</div>

		<div class="profile-box">
			<div class="status-emoji" style="font-size: 25px; text-decoration: underline; display: inline-block; margin-top: px;">
				today is...
				<img src="${pageContext.request.contextPath}/resources/images/stateA.png" alt="상태 A 이미지" class="emoji-image" style="${profile.state eq 'A' ? 'display: inline;' : 'display: none;'} margin-top: -15px;">
				<img src="${pageContext.request.contextPath}/resources/images/stateB.png" alt="상태 B 이미지" class="emoji-image" style="${profile.state eq 'B' ? 'display: inline;' : 'display: none;'} margin-top: -15px;">
				<img src="${pageContext.request.contextPath}/resources/images/stateC.png" alt="상태 C 이미지" class="emoji-image" style="${profile.state eq 'C' ? 'display: inline;' : 'display: none;'} margin-top: -15px;">
				<img src="${pageContext.request.contextPath}/resources/images/stateD.png" alt="상태 D 이미지" class="emoji-image" style="${profile.state eq 'D' ? 'display: inline;' : 'display: none;'} margin-top: -15px;">
				<img src="${pageContext.request.contextPath}/resources/images/stateE.png" alt="상태 E 이미지" class="emoji-image" style="${profile.state eq 'E' ? 'display: inline;' : 'display: none;'} margin-top: -15px;">
			</div>
		</div>

		<div class="form-group">
			<label for="introduction"></label>
			<textarea class="form-control" id="introduction" name="introduction" rows="4" required
					  style="width: 350px; height: 200px; resize: none; text-align: center; display: block; padding-top: 50px;"
					  disabled>${profile.introduction} </textarea>
		</div>

		<div style="display: flex; justify-content: center; align-items: center; font-size: 22px; margin-right: 150px;" >
				${principalName}&nbsp;<span style="color: ${loginMember.gender eq 'M' ? 'skyblue' : loginMember.gender eq 'F' ? 'pink' : 'black'};">${loginMember.gender eq 'M' ? '♀' : loginMember.gender eq 'F' ? '♂' : ''}</span>&nbsp;${principalBday}
			<c:if test="${loginMember.id eq profile.memberId}">
				<button type="button" class="btn btn-primary" onclick="location.href= '${pageContext.request.contextPath}/member/userPage/${loginMember.id}/profile/update.do';">수정</button>
			</c:if>
<%--			<c:if test="${loginMember.id ne profile.memberId}">--%>
<%--				<div class="feedReport-box">--%>
<%--					<button class="btn btn-sm btn-light btn-toggle"--%>
<%--							reported-id="${profileId}"--%>
<%--							repoter-id="${principalId}"--%>
<%--							Zprofile-id="${profile.memberId}">--%>
<%--						🚨 신고--%>
<%--					</button>--%>
<%--				</div>--%>
<%--			</c:if>--%>
		</div>
	</div>

<%--	<div class="modal fade" id="feedReportModal" tabindex="-1" role="dialog" aria-labelledby="feedReportModalLabel"--%>
<%--		 aria-hidden="true">--%>
<%--		<div class="modal-dialog" role="document">--%>
<%--			<div class="modal-content">--%>
<%--				<div class="modal-header">--%>
<%--					<h5 class="modal-title" id="feedReportModalLabel">프로필 신고</h5>--%>
<%--					<button type="button" class="close" data-dismiss="modal" aria-label="Close">--%>
<%--						<span aria-hidden="true" id="cancelModalButton">&times;</span>--%>
<%--					</button>--%>
<%--				</div>--%>
<%--				<div class="modal-body">--%>
<%--					<form id="reportForm">--%>
<%--						<div class="form-group">--%>
<%--							<label for="reportReason">신고 사유</label>--%>
<%--							<select class="form-control" id="reportReason" name="reportReason">--%>
<%--								<option value="inappropriate">불건전한 내용</option>--%>
<%--								<option value="spam">스팸</option>--%>
<%--								<option value="harassment">괴롭힘</option>--%>
<%--								<!-- 추가적인 신고 사유를 여기에 추가 가능 -->--%>
<%--							</select>--%>
<%--						</div>--%>
<%--						<div class="form-group">--%>
<%--							<label for="reportProfileContent">신고 내용</label>--%>
<%--							<textarea class="form-control" id="reportContent" name="reportContent" rows="3"></textarea>--%>
<%--						</div>--%>
<%--					</form>--%>
<%--				</div>--%>
<%--				<div class="modal-footer">--%>
<%--					<button type="button" class="btn btn-secondary" data-dismiss="modal" id="cancelReportModalButton">취소</button>--%>
<%--					<button type="button" class="btn btn-primary" id="confirmReportButton">신고</button>--%>
<%--				</div>--%>
<%--			</div>--%>
<%--		</div>--%>
<%--	</div>--%>
<%--	<script>--%>
<%--		$(document).ready(function () {--%>
<%--			// 'feed report' 버튼 클릭 시 모달 창 열기--%>
<%--			$(".btn-toggle").click(function () {--%>
<%--				var reportedId = $(this).data("reported-id");--%>
<%--				var reporterId = $(this).data("reporter-id");--%>
<%--				var profileId = $(this).data("profile-id");--%>

<%--				$("#feedReportModal").modal("show");--%>

<%--				$("#confirmReportButton").click(function () {--%>
<%--					var reportContent = $("#reportContent").val();--%>

<%--					$.ajax({--%>
<%--						method: "POST",--%>
<%--						url: "${pageContext.request.contextPath}/report/insertReportProfile",--%>
<%--						data: {--%>
<%--							reporterId: reporterId,--%>
<%--							reportedId: reportedId,--%>
<%--							content: reportContent,--%>
<%--							profileId: profileId--%>
<%--						},--%>
<%--						headers: {--%>
<%--							"X-CSRF-TOKEN": "<c:out value='${_csrf.token}'/>"--%>
<%--						},--%>
<%--						success: function (response) {--%>
<%--							alert("신고가 접수되었습니다.");--%>
<%--							$("#feedReportModal").modal("hide");--%>
<%--						},--%>
<%--						error: function (error) {--%>
<%--							alert("Error reporting: " + error.responseText);--%>
<%--						}--%>
<%--					});--%>
<%--				});--%>

<%--				// 취소 버튼 클릭 시 모달 창 닫기--%>
<%--				$("#cancelReportModalButton").click(function () {--%>
<%--					$("#feedReportModal").modal("hide");--%>
<%--				});--%>
<%--			});--%>
<%--		});--%>

<%--	</script>--%>

</sec:authorize>
