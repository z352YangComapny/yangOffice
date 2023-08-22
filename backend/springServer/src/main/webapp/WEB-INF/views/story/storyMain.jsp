<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<sec:authorize access="isAuthenticated()">
   <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.6.1/sockjs.min.js" integrity="sha512-1QvjE7BtotQjkq8PxLeF6P46gEpBRXuskzIVgjFpekzFVF4yjRgrQvTG1MTOJ3yQgvTteKAcO7DSZI92+u/yZw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js" integrity="sha512-iKDtgDyTHjAitUDdLljGhenhPwrbBfqTKWO1mkhSFH3A7blITC9MhYon6SjnMhp4o0rADGw9yAC6EW4t5a4K3g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
   <script src="${pageContext.request.contextPath}/resources/js/stomp.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/storyMain.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</sec:authorize>
<sec:authentication property="principal" var="loginMember"/>
<section>
	<div id="storyMainUpdate"></div>
</section>

<!-- Modal -->
<div class="modal fade" id="storyModal" tabindex="-1" role="dialog" aria-labelledby="storyModalTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header storyModalWriterId">
        <h5 class="modal-title" id="storyModalTitle"></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-2">.col-md-4</div>
				<div class="col-md-9 ml-auto storyModalContent" style="word-wrap: break-word;"></div>
			</div>
    	</div>
      </div>
      <div class="modal-footer storyModalCreatedAt" id="storyModalCreatedAt"></div>
    </div>
  </div>
</div>

<input type='hidden' id='userId' value='${loginMember.id}' />
<input type='hidden' id='currentCard' value=""/>

<script>
document.addEventListener('DOMContentLoaded', () => {
    connect();
});
document.querySelector('#storyMainUpdate').addEventListener('wheel', (e) => {
    e.preventDefault();
    document.querySelector('#storyMainUpdate').scrollLeft += e.deltaY; 
});

</script>