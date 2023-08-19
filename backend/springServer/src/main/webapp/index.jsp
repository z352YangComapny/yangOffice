<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:requestEncoding value = "utf-8"/> <!-- 한글로 제목을 변경할 경우에는 인코딩이 깨질 수 있으니 해당 설정 잡아주기 -->

<sec:authorize access="isAnonymous()">
<jsp:include page ="/WEB-INF/views/common/header2.jsp">
    <jsp:param name = "title" value = "안녕 스프링"/>
</jsp:include>
<div class="container" style ="">

<img src="${pageContext.request.contextPath}/resources/images/index_bgc.jpg" id="center-image" alt="스프링로고" class="d-block mx-auto mt-5"/>

</div>
</sec:authorize>
<sec:authorize access = "isAuthenticated()">

    <jsp:include page ="/WEB-INF/views/common/header.jsp">
        <jsp:param name = "title" value = "안녕 스프링"/>
    </jsp:include>
    <span>memberHome</span>
   	<div style="border: 1px solid #000;">
   		<jsp:include page="/WEB-INF/views/feed/feedList.jsp"/>
   	</div>
</sec:authorize>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>