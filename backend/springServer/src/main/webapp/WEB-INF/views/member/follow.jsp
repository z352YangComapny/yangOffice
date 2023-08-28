<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%--<style>
    .modal {
    z-index: 1000; /* 큰 값으로 설정 */
    }
</style>--%>
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
    <div class="d-flex mb-3">
        <div style ="width:10vw;">
            <a href="#" onclick="openList()"><img src="${pageContext.request.contextPath}/resources/images/follow_logo.png" style="width:50px;"/></a>
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
                <div class="input-group align-items-center mr-4 mt-2">
                    <button class="btn btn-outline-secondary" type="button" id="searchBtn" onclick="openList()">Search</button>
                    <input type="text" class="form-control" placeholder="검색할 유저(ID)를 입력하세요" id="inputText" aria-label="Recipient's username" aria-describedby="button-addon2">
                </div>
                <div class="d-flex flex-column justify-content-center align-items-center"  >
                    <div class="modal-body" style="height: 530px; width : 740px;">
                        <table class="table table-bordered table-hover" >
                            <thead>
                                <tr>
                                    <th scope="col" class="text-center">No</th>
                                    <th scope="col" class="text-center">ID</th>
                                    <th scope="col" class="text-center">Nick Name</th>
                                    <th scope="col" class="text-center">Follow</th>
                                </tr>
                            </thead>
                            <tbody id="memberListTable">

                            </tbody>
                        </table>
                    </div>
                    <div id="paginationContainer"><ul class="pagination"></ul></div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>


<script>
    // 전체 memberList 조회

    // 이미지 버튼 클릭시 모달창이 나오면서, 전체 memberLit를 띄운다.
   const openList = ()=>{
       const searchFollowModal = $("#searchFollow");
       searchFollowModal.modal("show");
       const inputText = $("#inputText").val();
       console.log("inputText", inputText);
        getMemberList(inputText);
   }

   //입력하는 텍스트에 따라 결과가 바로 보여진다.
   $("#inputText").on("input", function(e){
       const word = e.target.value;
       getMemberList(word);
   });

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
                const {memberList, followList, principal, totalPages} = responseData;
              // console.log("principal",principal);
               console.log("memberList",memberList);
               console.log("totalPages",totalPages);
               const tableBody = document.querySelector("#memberListTable");
               tableBody.innerHTML = "";
               if (memberList.length > 0) {
                   for (let i = 0; i < memberList.length; i++) {
                       const member = memberList[i];
                       const{id, username, nickname}=member;
                        console.log(id, username, nickname);
                       const row = document.createElement("tr");
                       row.classList.add("table-light");
                       row.classList.add("tableTr");

                       const indexCell = document.createElement("td");
                       indexCell.classList.add("text-center");
                       indexCell.textContent = String(limit*(currentPage-1)+(i+1));

                       row.appendChild(indexCell);

                       const usernameCell = document.createElement("td");
                       const usernameLink = document.createElement("a");
                       usernameLink.textContent = member.username;
                       usernameLink.href="${pageContext.request.contextPath}/member/userPage/"+id;
                       usernameCell.classList.add("text-center");
                       usernameCell.appendChild(usernameLink);
                       row.appendChild(usernameCell);

                       const nicknameCell = document.createElement("td");
                       nicknameCell.textContent = member.nickname;
                       nicknameCell.classList.add("text-center");
                       row.appendChild(nicknameCell);

                       let followCell = document.createElement("td");
                       followCell.classList.add("text-center");
                       let emptyfollowCell = document.createElement("td");
                       emptyfollowCell.classList.add("text-center");
                       let unfollowCell = document.createElement("td");
                       unfollowCell.classList.add("text-center");
                       let isImg = true;
                       for (let j = 0; j < followList.length; j++) {
                           const follow = followList[j];
                           const{follower, followee} = follow;
                            console.log(follower, followee);
                           if (follow.followee === member.id || member.id === principal) {
                              isImg=false;
                               break;
                           }
                       }

                       if (!isImg) {
                           // follow.followee === member.id 조건을 확인
                           if(member.id === principal) {
                               row.appendChild(emptyfollowCell); // 빈 셀 추가
                           } else{
                               const imgLink = document.createElement("a");
                               imgLink.href = "#";
                               const img = document.createElement("img");
                               imgLink.classList.add("unfollowBtn");
                               // imgLink.innerHTML ="follow";
                               img.src = "${pageContext.request.contextPath}/resources/images/substract.png";
                               img.style.width = "30px";
                               imgLink.appendChild(img);
                               unfollowCell.append(imgLink);
                               row.appendChild(unfollowCell);
                           }
                       } else {
                           const imgLink = document.createElement("a");
                           imgLink.href = "#";
                           const img = document.createElement("img");
                           imgLink.classList.add("addBtn");
                           // imgLink.innerHTML ="follow";
                           img.src = "${pageContext.request.contextPath}/resources/images/add.png";
                           img.style.width = "30px";
                           imgLink.appendChild(img);
                           followCell.appendChild(imgLink);
                           isImg=true;
                           row.appendChild(followCell); // 이미지가 있는 셀 추가

                           // 만약 비동기로 처리된 동적 버튼에 이벤트 핸들러하려면 여기에 작성
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
                   noMatchCell.classList.add("text-center");
                   noMatchRow.appendChild(noMatchCell);
                   tableBody.appendChild(noMatchRow);
               }

               updatePaginationUI(totalPages);


           }

       });
    }
    // 페이지네이션 UI 업데이트
    const updatePaginationUI = (totalPages) => {
        const paginationContainer = document.querySelector("#paginationContainer");
        const pagination = document.querySelector(".pagination");
        pagination.innerHTML = "";

        for (let i = 1; i <= totalPages; i++) {

            // console.log("list", list);
            if(i===1){
                const list = document.createElement("li");
                list.classList.add("page-item");
                //list.classList.add("disabled");
                const pageLink = document.createElement("a");
                pageLink.textContent = '«';
                pageLink.href = "#";
                pageLink.classList.add("page-link");
                pageLink.classList.add("front");
                list.append(pageLink);
                pagination.appendChild(list);
                makePagination(i, totalPages);
            }
            if(i=== totalPages){
                if(i !== 1){
                    makePagination(i,totalPages);
                }
                const list = document.createElement("li");
                list.classList.add("page-item");
                //list.classList.add("disabled");
                const pageLink = document.createElement("a");
                pageLink.textContent = '»';
                pageLink.href = "#";
                pageLink.classList.add("page-link");
                pageLink.classList.add("back");
                list.append(pageLink);
                pagination.appendChild(list);
            }
            if(i !== 1 && i !==totalPages){
                makePagination(i, totalPages);
            }

            if(currentPage ===1){
                $(".front").addClass("disabled");
            } else if(currentPage === totalPages){
                $(".back").addClass("disabled");
            }
            $(".front").click(() => {
                if (currentPage > 1) {
                    currentPage--;
                    openList();
                }
            });

            $(".back").click(() => {
                if (currentPage < totalPages) {
                    currentPage++;
                    openList();
                }
            });

        }


    };

   const makePagination = (i, totalPages) => {
       const pagination = document.querySelector(".pagination");
       const list = document.createElement("li");
       list.classList.add("page-item");
       const pageLink = document.createElement("a");
       pageLink.textContent = i;
       pageLink.href = "#";
       pageLink.classList.add("page-link");
       list.append(pageLink);
       console.log("list", list);

       if (i === currentPage) {
           pageLink.classList.add("active");
       }

       pageLink.addEventListener("click", () => {
           currentPage = i;
           openList();
       });



       pagination.appendChild(list);
   }


</script>
<script>
    //이 스크립트 단에서 작성한 코드라면, 해당 page에 로드된 내용을 기반으로 작동한다.
    // 동적으로 생성된 테이블의 경우에는 이벤트 버블링에 의해 이와 같이 작성
    // 또는 상단의 비동기요청으로 생성함과 동시에 그때 생성된 요소를 대상으로 이벤트 핸들러를 작성해야 한다.
<%--document.onclick = (e) => {--%>
<%--    if(e.target.matches(".addBtn")){--%>
<%--        const memberId = document.querySelector(".tableTr").children[1].innerHTML; // 버튼에 저장한 멤버 ID 가져오기--%>
<%--        $.ajax({--%>
<%--            url: "${pageContext.request.contextPath}/member/addFollowee",--%>
<%--            method: "POST",--%>
<%--            data: { memberId: memberId }, // memberId를 서버에 전달--%>
<%--            dataType:"json",--%>
<%--            beforeSend: function(xhr) {--%>
<%--                xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}')},--%>
<%--            success(responseData){--%>
<%--                const{msg} = responseData;--%>
<%--                alert(msg);--%>
<%--                getMemberList(inputText);--%>
<%--            },--%>
<%--            error(error) {--%>
<%--                console.error("Follow request error:", error);--%>
<%--                alert("follow 실패");--%>

<%--            }--%>
<%--        });--%>
<%--    }--%>
<%--}--%>

    /* follow */
    $(document).on("click", ".addBtn", function() {
        const memberId = $(this).closest("tr").find("td:eq(1)").text(); // 버튼이 속한 행의 두 번째 열에서 멤버 ID 가져오기

        $.ajax({
            url: "${pageContext.request.contextPath}/member/addFollowee",
            method: "POST",
            data: { memberId: memberId },
            dataType: "json",
            beforeSend: function(xhr) {
                xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}')
            },
            success(responseData) {
                const { msg } = responseData;
                alert(msg);
                const inputText = $("#inputText").val();
                getMemberList(inputText);
            },
            error(error) {
                console.error("Follow request error:", error);
                alert("follow 실패");
            }
        });
    });

    /* unfollow */
    $(document).on("click", ".unfollowBtn", function() {
        const memberId = $(this).closest("tr").find("td:eq(1)").text(); // 버튼이 속한 행의 두 번째 열에서 멤버 ID 가져오기

        $.ajax({
            url: "${pageContext.request.contextPath}/member/unfollow",
            method: "POST",
            data: { memberId: memberId },
            dataType: "json",
            beforeSend: function(xhr) {
                xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}')
            },
            success(responseData) {
                const { msg } = responseData;
                alert(msg);
                const inputText = $("#inputText").val();
                getMemberList(inputText);
            },
            error(error) {
                console.error("Follow request error:", error);
                alert("unfollow 실패");
            }
        });
    });
</script>
