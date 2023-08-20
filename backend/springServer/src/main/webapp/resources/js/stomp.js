const connect = () => {
	const ws = new SockJS(`http://${location.host}/stomp`); // endpoint
	const stompClient = Stomp.over(ws);
	
    // 구독신청 
    stompClient.connect({}, () => {
        console.log('WebSocket 연결 성공');
        stompClient.subscribe('/storyMain', (payloads) => {
            console.log('구독됨');
            console.log('/story : ', payloads);

            renderStory(payloads);
        });
		const userId = document.getElementById('userId').value;
		console.log('userId = ', userId);
        stompClient.send("/app/send", {}, JSON.stringify({ userId: userId }));
    });
};

const renderStory = (payloads) => {
	console.log('renderStory 호출 성공');
	console.log('payloads = ', payloads);
	const stories = JSON.parse(payloads.body);

	const view = document.querySelector('#storyMainUpdate');
	
	stories.forEach((story) => {
		const html = `
		<div>from : ${story.from}</div>
		<br>
		<div>content : ${story.content}</div>
		<br>
		<div>createdAt : ${story.createdAt}</div>
		`;
		view.innerHTML += html;
	});
};