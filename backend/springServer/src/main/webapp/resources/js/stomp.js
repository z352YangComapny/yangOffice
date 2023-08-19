console.log('Hello stomp.js');

const ws = new SockJS(`http://${location.host}/stomp`); // endpoint
const stompClient = Stomp.over(ws);

console.log('1.success');

stompClient.connect({}, (frame) => {
	console.log('open : ', frame);
	stompClient.send('/story', {}, JSON.stringify({}));
	
	// 구독신청 
	stompClient.subscribe('/story', (payloads) => {
		console.log('/story : ', payloads);
		
		const stories = JSON.parse(payloads.body);
		
		renderStory(stories);
		
	});
	
});

const renderStory = (stories) => {
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





