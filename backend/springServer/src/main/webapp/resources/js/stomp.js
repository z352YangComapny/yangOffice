console.log('Hello stomp.js');

const ws = new SockJS(`http://${location.host}/stomp`); // endpoint
const stompClient = Stomp.over(ws);

stompClient.connect({}, (frame) => {
	console.log('open : ', frame);
	
	// 구독신청 
	stompClient.subscribe('/story/main', (payloads) => {
		console.log('/story/main : ', payloads);
		
		const newPayloads = JSON.parse(payloads.body);
	});
	
});







