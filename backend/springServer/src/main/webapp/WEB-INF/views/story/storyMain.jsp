<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<section>
	<div id="storyMainUpdate">
		<c:forEach items="${payloads}" var="payload">
	       	<div>
				<strong>내용:</strong> ${payload.content}<br>
	       	</div>
	       	<div>
				<strong>보낸 사람:</strong> ${payload.from}<br>
	       	</div>
	       	<div>
				<strong>작성일:</strong> ${payload.createdAt}<br>
	       	</div>
		</c:forEach>
	</div>
</section>
<script>
	(() => {
	
	    const updateStoryMain = () => {
	        $.get('/story/main', (data) => {
	            $('#storyMainUpdate').html(data);
	        });
	    };
	
	    updateStoryMain();
	
	    setInterval(updateStoryMain, 1000);
	})();
</script>