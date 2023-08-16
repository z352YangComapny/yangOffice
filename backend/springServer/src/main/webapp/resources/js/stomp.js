console.log('Hello stomp.js');

// const ws = new WebSocket(`http://${location.host}/spring/stomp`); // endpoint
const ws = new SockJS(`http://${location.host}/spring/stomp`); // endpoint
const stompClient = Stomp.over(ws); // SockJS 웹소켓 연결 객체를 Stomp 클라이언트 객체로 변환, Stomp 프로토콜을 사용하여 메시지 브로커와 통신 가능 

// 연결이 되면 해당 핸들러 함수가 실행된다.
stompClient.connect({}, (frame) =>{
	
	console.log('open : ', frame);
	
	// 구독신청(연결이 되면!) 이 주소로 요청이 날라왔을 때의 메시지 처리!
	// /topic/a를 구독하면 message 출력해줘!
	stompClient.subscribe('/app/notice', (message)=>{
		console.log('/app/notice : ', message);
		renderMessage(message);
	});
	stompClient.subscribe(`/app/notice/${memberId}`, (message)=>{
		console.log(`/app/notice/${memberId}`, message);
		renderMessage(message);
	
	});

});

const renderMessage = (message) =>{
	const {type, from, to, content, createdAt} = JSON.parse(message.body); // 받아온 JSON 객체를 변환 후 구조분해할당!
	
	//jquery 변수를 $표시로 시작
	const $noticeModal = $("#noticeModal");
	$noticeModal.find(".modal-title").html(`<span class = 'badge badge-primary from'>${to}</span>`);
	$noticeModal.find(".modal-body").html(content);
	$noticeModal.find(".modal-footer .from").html(from);
	$noticeModal.modal(); // 모달 시각화, 모달을 호출한다.
	
	
}