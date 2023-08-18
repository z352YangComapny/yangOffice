console.log('Hello stomp.js');

const ws = new SockJS(`http://${location.host}/???`); // endpoint, 달라질 거임
const stompClient = Stomp.over(ws); // SockJS 웹소켓 연결 객체를 Stomp 클라이언트 객체로 변환, Stomp 프로토콜을 사용하여 메시지 브로커와 통신 가능 

// 연결이 되면 해당 핸들러 함수가 실행된다.
stompClient.connect({}, (frame) =>{
	
	console.log('open : ', frame);
	
	stompClient.subscribe('/chatAll/all', (message)=>{
		console.log('/chatAll/all : ', message);
		renderMessage(message);
	});

});

const renderMessage = (message) =>{
	const {type, from, content, createdAt} = JSON.parse(message.body); // 받아온 JSON 객체를 변환 후 구조분해할당!
	
	//jquery 변수를 $표시로 시작
	const $noticeModal = $("#noticeModal");
	$noticeModal.find(".modal-body").html(content); 
	$noticeModal.find(".modal-footer .from").html(from); // 수정 예정
	$noticeModal.modal(); // 모달 시각화, 모달을 호출한다.
	
	
}