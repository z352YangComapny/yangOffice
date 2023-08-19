<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:requestEncoding value = "utf-8"/> <!-- 한글로 제목을 변경할 경우에는 인코딩이 깨질 수 있으니 해당 설정 잡아주기 -->
<jsp:include page ="/WEB-INF/views/common/header.jsp">
        <jsp:param name = "title" value = "안녕 스프링"/>
    </jsp:include>
    
    <div class="container mt-4">
    <h1>피드 생성</h1>
    
    <form:form action="${pageContext.request.contextPath}/feedCreate.do" method="POST" enctype="multipart/form-data">
        
        <div class="form-group">
            <label for="content">내용</label>
            <textarea class="form-control" id="content" name="content" rows="4" required></textarea>
        </div>
        
        <div class="form-group">
            <label for="photo">사진 업로드</label>
            <input type="file" class="form-control-file" id="photo" name="photo" accept="image/*" required>
        </div>
        
        <button type="submit" class="btn btn-primary">피드 작성</button>
    </form:form>
</div>
    
    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>