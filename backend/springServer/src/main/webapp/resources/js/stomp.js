const connect = () => {
	const ws = new SockJS(`http://${location.host}/stomp`); // endpoint
	const stompClient = Stomp.over(ws);
	
    // 구독신청 
    stompClient.connect({}, () => {
        console.log('WebSocket 연결 성공');
        stompClient.subscribe('/storyMain', (payloads) => {
//            console.log('구독됨');
//            console.log('/story : ', payloads);

            renderStory(payloads);
        });
	        
		const userId = document.getElementById('userId').value;
//		console.log('userId = ', userId);
        const sendInterval = setInterval(() => {
            stompClient.send("/app/send", {}, JSON.stringify({ userId: userId }));
        }, 1000);
    });
};

const renderStory = (payloads) => {
//	console.log('renderStory 호출 성공');
//	console.log('payloads = ', payloads);
	const stories = JSON.parse(payloads.body);

	const view = document.querySelector('#storyMainUpdate');
	view.innerHTML = '';
	let i = 0;
	stories.forEach((story) => {
		const html = `
		<div class="card m-3">
		  <ul class="list-group list-group-flush">
		    <li class="list-group-item writerId">${story.from}</li>
		    <li class="list-group-item content">${story.content}</li>
		    <li class="list-group-item createdAt">${story.createdAt}</li>
		  </ul>
			<input type="hidden" id="cardIndex" value="${i + 1}"/>
			<input type="hidden" id="storyAttach" value="${story.attach}"/>
			<input type="hidden" id="storyCardId" value="${story.id}"/>
		</div>
		`;
		i = i + 1;
//		console.log('i : ', i);
		view.innerHTML += html;
	});
	
	const storyElements = document.querySelectorAll('.card');
	const storyModal = $('#storyModal');
	    
    storyElements.forEach((storyElement) => {
        storyElement.addEventListener('click', () => {
			updateModal(storyElement);
			
            storyModal.modal('show');
            
            $('#storyModal').on('wheel', (e) => {
			    e.preventDefault();
//			    console.log('wheeeeeeeeeeeel');
			    const currentCardIndex = parseInt(document.querySelector('#currentCard').value);
//			    console.log('currentCardIndex : ', currentCardIndex);
			    const nextCardIndex = document.querySelector(`#cardIndex[value="${currentCardIndex + 1}"]`);
			    const beforeCardIndex = document.querySelector(`#cardIndex[value="${currentCardIndex - 1}"]`);
//			    console.log('nextCardIndex : ', nextCardIndex);
			    	
			    if (e.deltaY < 0){
			    	if (beforeCardIndex) {
				    	const beforeCard = beforeCardIndex.closest('.card');
				        updateModal(beforeCard);
				    }else{
				    	storyModal.modal('hide');
				    }
			    }else if (e.deltaY > 0){
				    if (nextCardIndex) {
				    	const nextCard = nextCardIndex.closest('.card');
				        updateModal(nextCard);
				    }else{
				    	storyModal.modal('hide');
				    }
			    }
			    
			});

        });
	    
    });
    
};

const updateModal = (e) => {
	const writerId = e.querySelector('.writerId').textContent;
	const content = e.querySelector('.content').textContent;
	const createdAt = e.querySelector('.createdAt').textContent;
	const storyAttach = e.querySelector('#storyAttach').value;
	const currentCardIndex = e.querySelector('#cardIndex').value;
	const storyCardId = e.querySelector('#storyCardId').value;

//	console.log(storyAttach);
	
	document.querySelector('.storyModalWriterId').textContent = writerId;
	document.querySelector('.storyModalContent').textContent = content;
	document.querySelector('.storyModalCreatedAt').textContent = createdAt;
	document.querySelector('#reportStoryId').value = storyCardId;
	
	const imgElement = document.createElement('img');
	imgElement.id = 'storyModalProfile';
	imgElement.src = `http://localhost:8080/resources/upload/attachment/${storyAttach}`;
	
	imgElement.style.maxWidth = '100px';
	imgElement.style.height = 'auto';

	document.querySelector('.storyProfileAttach').innerHTML = '';
	document.querySelector('.storyProfileAttach').appendChild(imgElement);

	document.querySelector('#currentCard').value = currentCardIndex;
	document.querySelector('#currentWriter').value = writerId;
};

const clearModal = () => {
    document.querySelector('.storyModalWriterId').textContent = '';
    document.querySelector('.storyModalContent').textContent = '';
    document.querySelector('.storyModalCreatedAt').textContent = '';
};


// ---------------------------------------------------------------------

const notifyConnect = () => {
	const ws = new SockJS(`http://${location.host}/stomp`); // endpoint
	const stompClient = Stomp.over(ws);
	
    // 구독신청 
    stompClient.connect({}, () => {
    
	    const userId = document.getElementById('userId').value;
	    
	   stompClient.subscribe(`/dm/notice/${userId}`, (notification) => {
	       noticeDm(notification);
	    });
	    
	  });
};

const noticeDm = (notification) => {
	const {type, from, to, content, createdAt} = JSON.parse(notification.body);
	
	const notificationDiv = $("#notification-div");
	notificationDiv.html(`
	    <div class="alert alert-dismissible alert-info" style="margin-right: 20px;">
	        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
	        <strong>❕</strong>${content}
	    </div>
	`);
		    
	     notificationDiv.find('.btn-close').click(() => {
        notificationDiv.empty();
   		 });
	
};

// ---------------------------------------------------------------------------------

const ChatConnect = () => {
	const ws = new SockJS(`http://${location.host}/stomp`); // endpoint
	const stompClient = Stomp.over(ws);
	
    // 구독신청 
    stompClient.connect({}, () => {
    	 console.log('챗구독챗구독챗구독');
	    const memberId = document.getElementById('memberId').value;
	    
	   stompClient.subscribe(`/chat/chatting/${memberId}`, (chat) => {
	   console.log(chat);
	       chatting(chat);
	    });
	    
	  });
};

const chatting = (chat) => {
	console.log('chatting 호출호출호ㅜㄹ');
	const chatDiv = $("#chat-div");

    const chatMessageDiv = $("<div>").addClass('d-flex', 'flex-row', 'justify-content-end', 'mb-4', 'pt-1');

    const chatContentDiv = $("<div>");
    const chatContentText = $("<p>")
        .addClass("small p-2 me-3 mb-1 text-white rounded-3 bg-primary")
        .text(chat.content);

    const chatRegDateText = $("<p>")
        .addClass("small me-3 mb-3 rounded-3 text-muted d-flex justify-content-end")
        .text(formatDate(chat.regDate)); 

    chatContentDiv.append(chatContentText, chatRegDateText);

    chatMessageDiv.append(chatContentDiv);
    chatDiv.append(chatMessageDiv);

};


