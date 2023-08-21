<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/member.css" />


<!-- 사진이 있는 있는만큼  active가 늘어나고 사진이있는 만큼 그안에 담겨야됨 -->
<div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-indicators">
        <c:forEach items="${photoDetail}" var="photo" varStatus="status">
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="${status.index}" class="${status.first ? 'active' : ''}" aria-current="${status.first ? 'true' : 'false'}" aria-label="Slide ${status.index + 1}"></button>
        </c:forEach>
    </div>
    <div class="carousel-inner">
        <c:forEach items="${photoDetail}" var="photo" varStatus="status">
            <div class="carousel-item ${status.first ? 'active' : ''}">
                <img src="${pageContext.request.contextPath}/resources/upload/attachment/feed/${photo.attachments[0].renamedFilename}" class="d-block w-100" alt="...">
                <div class="carousel-caption d-none d-md-block">
                </div>
            </div>
        </c:forEach>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
    </button>
</div>


<jsp:include page="/WEB-INF/views/common/footer.jsp"/>