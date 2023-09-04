<%@ page language="java" contentType="text/html; charset=UTF-8"
       pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <%--<title>${param.title}</title>--%>

   <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
   <!-- bootstrap js: jquery load 이후에 작성할것.-->
   <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
   <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

   <!-- bootstrap css -->
   <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.1/dist/minty/bootstrap.min.css">
   <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
   <!-- 사용자작성 css -->

</head>
<style>
#loginContent {
    height: 745px;
}
</style>
<body>
<!-- <div id="container"> -->
   <header>
      <!-- https://getbootstrap.com/docs/4.0/components/navbar/ -->
      <nav class="navbar navbar-expand-lg bg-light">
         <div class="container-fluid">
             <a class="navbar-brand" href="${pageContext.request.contextPath}/member/userPage/${loginMember.id}">
               <img src="${pageContext.request.contextPath}/resources/images/sologo.png" alt="쏘이스토리_로고" width="70px" style="margin-bottom: 20px;" />
               <span style = "font-size : 50px; font-weight: bold; 
                background: linear-gradient(to right, #F3969A, #78C2AD);
		        -webkit-background-clip: text;
		    	-webkit-text-fill-color: transparent;">SSOY STORY<span>
            </a>
         </div>
      </nav>
</header>
   <section id="loginContent">