console.log('Hello stomp.js');

const ws = new SockJS(`http://${location.host}/stomp`); // endpoint
const stompClient = Stomp.over(ws);

console.log('1.success');

stompClient.connect({}, (frame) => {
	console.log('open : ', frame);
	
	// 구독신청 
	stompClient.subscribe('/story/main', (payloads) => {
		console.log('/story/main : ', payloads);
		setConnected(true);
        const newPayloads = JSON.parse(payloads.body);

	});
	
}, (error) => {
    console.error('WebSocket connection error:', error);
});







