<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/member.css" />

<style>
    /* 이미지 크기 및 정렬 조절 */
    .carousel-inner .carousel-item img {
        max-width: 100%; /* 이미지 최대 너비 */
        height: auto;
        display: block; /* 중앙 정렬을 위한 display 설정 */
        margin: 0 auto; /* 가운데 정렬 */
    }

    /* 버튼 위치 및 정렬 조절 */
    .carousel-indicators {
        bottom: -20px; /* 버튼 아래로 이동 */
    }
    .carousel-control-prev,
    .carousel-control-next {
        top: 50%; /* 상단 위치 */
        transform: translateY(-50%); /* 수직 가운데 정렬 */
        width: auto; /* 기본 너비 */
        font-size: 20px; /* 버튼 크기 */
        background-color: transparent; /* 배경색 투명 */
        border: none; /* 테두리 없음 */
    }
    .carousel-control-prev-icon,
    .carousel-control-next-icon {
        color: #000; /* 버튼 색상 */
    }
    .carousel-control-prev {
        left: 10px; /* 왼쪽 버튼 위치 */
    }
    .carousel-control-next {
        right: 10px; /* 오른쪽 버튼 위치 */
    }
</style>

<div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-indicators">
        <c:forEach items="${photoDetail}" var="photo" varStatus="status">
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="${status.index}" class="${status.first ? 'active' : ''}" aria-current="${status.first ? 'true' : 'false'}" aria-label="Slide ${status.index + 1}"></button>
        </c:forEach>
    </div>
    <div class="carousel-inner">
        <c:forEach items="${photoDetail}" var="photo" varStatus="status">
            <div class="carousel-item ${status.first ? 'active' : ''}">
                <img src="${pageContext.request.contextPath}/resources/upload/attachment/feed/${photo.attachments[0].renamedFilename}" class="d-block" alt="Image ${status.index + 1}">
            </div>
        </c:forEach>
    </div>
    <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
    </a>
    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
    </a>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
