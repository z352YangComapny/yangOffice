<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>

<!-- bootstrap js: jquery load 이후에 작성할것.-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap/dist/js/bootstrap.bundle.min.js"></script>

<!-- bootstrap css -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.1/dist/minty/bootstrap.min.css">
<!-- 사용자작성 css -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/style.css"/>

<section>
    <div class="d-flex mb-3  justify-content-evenly ">
        <div class="input-group align-items-center mr-4" style ="width:40vw;">
            <input type="text" class="form-control" placeholder="검색할 유저(영문자)를 입력하세요" id="inputText" aria-label="Recipient's username" aria-describedby="button-addon2">
            <button class="btn btn-outline-secondary" type="button" id="searchBtn" onclick="openList()">Search</button>
        </div>
        <div style ="width:10vw;">
            <a href="#" onclick=""><img src="${pageContext.request.contextPath}/resources/images/following.png" style="width:100px;"/></a>
        </div>
    </div>


<%--    1. 회원 검색용 모달--%>
    <div class="modal " id="searchFollow">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Member List</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true"></span>
                    </button>
                </div>
                <div class="modal-body overflow-auto">
                    <table class="table table-bordered table-hover" >
                        <thead>
                            <tr>
                                <th scope="col">No</th>
                                <th scope="col">ID</th>
                                <th scope="col">Nick Name</th>
                                <th scope="col">Follow</th>
                            </tr>
                        </thead>
                        <tbody id="memberListTable">

                        </tbody>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary">Confirm</button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

<%--    2. 팔로워 리스트용 모달 --%>

    <div class="modal fade" id="followerList" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="followList">Follower List</h4>
                </div>
                <div class="modal-body">
                    Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean. A small river named Duden flows by their place and supplies it with the necessary regelialia. It is a paradisematic country, in which roasted parts of sentences fly into your mouth. Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic life One day however a small line of blind text by the name of Lorem Ipsum decided to leave for the far World of Grammar.
                </div>
                <div class="modal-footer">
                    <div class="left-side">
                        <button type="button" class="btn btn-default btn-simple" data-dismiss="modal">Never mind</button>
                    </div>
                    <div class="divider"></div>
                    <div class="right-side">
                        <button type="button" class="btn btn-danger btn-simple">Delete</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<script>

 /*  const followList = () => {
       jQuery('#searchFollow').modal('show');
       getMemberList(inputText);
   }*/

   const openList = ()=>{
       const searchFollowModal = $("#searchFollow");
       searchFollowModal.modal("show");
       const inputText = $("#inputText").val();
       //console.log(inputText);
        getMemberList(inputText);
   }

    //페이지 번호와 크기
    let currentPage = 1;
    const limit = 10; //페이지 당 보여줄 테이터 수

   const getMemberList = (inputText)=>{
       console.log(inputText);
       $.ajax({
           url : "${pageContext.request.contextPath}/member/memberList",
           dataType : "json",
           data : {inputText : inputText,
                   page: currentPage,
                   limit : limit},
           success(responseData){
               console.log(responseData);
                const {memberList, followList} = responseData;

               const tableBody = document.querySelector("#memberListTable");
               tableBody.innerHTML = "";
               if (memberList.length > 0) {
                   for (let i = 0; i < memberList.length; i++) {
                       const member = memberList[i];
                       const{id, username, nickname}=member;
                      // console.log(id, username, nickname);
                       const row = document.createElement("tr");
                       row.classList.add("table-active");

                       const indexCell = document.createElement("td");
                       indexCell.textContent = i + 1;
                       row.appendChild(indexCell);

                       const usernameCell = document.createElement("td");
                       usernameCell.textContent = member.username;
                       row.appendChild(usernameCell);

                       const nicknameCell = document.createElement("td");
                       nicknameCell.textContent = member.nickname;
                       row.appendChild(nicknameCell);

                       let followCell = document.createElement("td");
                       let emptyfollowCell = document.createElement("td");
                       let isImg = true;
                       for (let j = 0; j < followList.length; j++) {
                           const follow = followList[j];
                           const{follower, followee} = follow;
                         //  console.log(follower, followee);
                           if (follow.followee === member.id) {
                              isImg=false;
                               break;
                           }
                       }

                       if (!isImg) {
                           // follow.followee === member.id 조건을 확인
                           row.appendChild(emptyfollowCell); // 빈 셀 추가
                       } else {
                           const imgLink = document.createElement("button");
                           //imgLink.href = "#";
                           //const img = document.createElement("img");
                           imgLink.classList.add("addBtn");
                           imgLink.innerHTML ="follow";
                           //img.src = "${pageContext.request.contextPath}/resources/images/add.png";
                          // img.style.width = "30px";
                           //imgLink.appendChild(img);
                           followCell.appendChild(imgLink);
                           isImg=true;
                           row.appendChild(followCell); // 이미지가 있는 셀 추가
                           
                          /*  imgLink.onclick = (e) => {
                        	   
                        	   console.log(e.target);
                               const tbodyTr = document.querySelector("#memberListTable").children; // 버튼에 저장한 멤버 ID 가져오기
                               const idTd = tbodyTr.children[1];
                               const memberId = idTd.value;
                               console.log("Clicked button for member ID:", memberId);

                               $.ajax({
                                   url: "${pageContext.request.contextPath}/member/addFollow",
                                   method: "POST",
                                   data: { memberId: memberId }, // memberId를 서버에 전달
                                   success(responseData){
                                       console.log("Follow request successful:", response);
                                   },
                                   error(error) {
                                       console.error("Follow request error:", error);
                                       // 오류 발생 시 처리할 로직을 작성합니다.
                                   }
                               });
                           } */
                       }
                       tableBody.appendChild(row);
                   }
               } else {
                   const noMatchRow = document.createElement("tr");
                   const noMatchCell = document.createElement("td");
                   noMatchCell.colSpan = 4;
                   noMatchCell.textContent = "일치하는 회원이 없습니다.";
                   noMatchRow.appendChild(noMatchCell);
                   tableBody.appendChild(noMatchRow);
               }

           }
       });
   }


</script>
<script>
	document.onclick = (e) => {
		if(e.target.matches(".addBtn")){
			 console.log(e.target);
		}
	}

    document.querySelectorAll(".addBtn").forEach(elem=>{
    elem.onclick = (e) => {
        console.log(e.target);
        const tbodyTr = document.querySelector("#memberListTable").children; // 버튼에 저장한 멤버 ID 가져오기
        const idTd = tbodyTr.children[1];
        const memberId = idTd.value;
        console.log("Clicked button for member ID:", memberId);

        $.ajax({
            url: "${pageContext.request.contextPath}/member/addFollow",
            method: "POST",
            data: { memberId: memberId }, // memberId를 서버에 전달
            success(responseData){
                console.log("Follow request successful:", response);
            },
            error(error) {
                console.error("Follow request error:", error);
                // 오류 발생 시 처리할 로직을 작성합니다.
            }
        });
    }
});



</script>