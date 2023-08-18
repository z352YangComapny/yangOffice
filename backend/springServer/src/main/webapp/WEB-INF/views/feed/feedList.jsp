<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:requestEncoding value = "utf-8"/> 
 <div class="container">
 
        <h1>피드</h1>
     <c:forEach items="${photoList}" var="photo">
        <h1>${photo}</h1> <!-- 여기서 name은 PhotoAttachmentFeedDto 클래스의 속성 이름 -->
    </c:forEach>
        <div class="row">
            <div class="col-md-4 mb-4">
                <div class="card">
                
                    <div class="card-body">
                   
                        <h5 class="card-title">
                         
                        </h5>
                        <p class="card-text">이미지 1에 대한 추가 정보</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-4">
                <div class="card">
                    <img src="your_image_url_2.jpg" class="card-img-top" alt="Image 2">
                    <div class="card-body">
                        <h5 class="card-title">이미지 2 설명</h5>
                        <p class="card-text">이미지 2에 대한 추가 정보</p>
                    </div>
                </div>
            </div>
            <!-- 추가적인 이미지 카드들을 이렇게 추가 -->
        </div>
    </div>