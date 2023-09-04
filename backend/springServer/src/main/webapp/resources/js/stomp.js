const connect = () => {
	const ws = new SockJS(`http://${location.host}/stomp`); // endpoint
	const stompClient = Stomp.over(ws);
	
    stompClient.connect({}, () => {

        const userId = document.getElementById('userId').value;
        
        // 구독
        stompClient.subscribe(`/storyMain/${userId}`, (payloads) => {

            renderStory(payloads);
        });
	    
	    // 메인페이지 스토리 요청
		stompClient.send(`/app/init/${userId}`, {}, JSON.stringify({ userId: userId }));
		
		document.querySelector("#btnCreateStory2").onclick = () => {
			const content = document.querySelector('#message-text-create').value;
			if(!/^.{1,100}$/.test(content)){
				alert('글자 수는 1 - 100글자 사이입니다');
				return false;
			}
			
			// 스토리 작성
			stompClient.send(`/app/create/${userId}`, {}, JSON.stringify({ userId: userId, content: content }));
			
			window.location.href = "http://localhost:8080/story/storyTap";

		};
		
    });
};

const renderStory = (payloads) => {

	const stories = JSON.parse(payloads.body);

	const view = document.querySelector('#storyMainUpdate');
	view.innerHTML = '';
	let i = 0;
	stories.forEach((story) => {
		const html = `
		<div class="card border-primary  ml-3 mb-2">
		  <ul class="list-group list-group-flush">
		    <li class="list-group-item writerId">${story.from}</li>
		    <li class="list-group-item content">${story.content}</li>
		    <li class="list-group-item createdAt">${story.formattedCreatedAt}</li>
		  </ul>
			<input type="hidden" id="cardIndex" value="${i + 1}"/>
			<input type="hidden" id="storyAttach" value="${story.attach}"/>
			<input type="hidden" id="storyCardId" value="${story.id}"/>
			<input type="hidden" id="storyFeedId" value="${story.storyFeed}"/>
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
				
				// 현재 카드 인덱스
			    const currentCardIndex = parseInt(document.querySelector('#currentCard').value);

			    const nextCardIndex = document.querySelector(`#cardIndex[value="${currentCardIndex + 1}"]`);
			    const beforeCardIndex = document.querySelector(`#cardIndex[value="${currentCardIndex - 1}"]`);

			    if (event.deltaY < 0){ // 마우스 휠 업
			    	if (beforeCardIndex) {
				    	const beforeCard = beforeCardIndex.closest('.card'); // 이전 카드 선택
				        updateModal(beforeCard);
				    }else{
				    	storyModal.modal('hide');
				    }
			    }else if (event.deltaY > 0){ // 마우스 휠 다운
				    if (nextCardIndex) {
				    	const nextCard = nextCardIndex.closest('.card'); // 다음 카드 선택
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
	const currentCardStoryFeed = e.querySelector('#storyFeedId').value;
	
//	console.log('currentCardStoryFeed = ', currentCardStoryFeed);

//	console.log(storyAttach);
//	console.log('currentCardIndex = ', currentCardIndex);
//	console.log('storyCardId =', storyCardId);
	
	document.querySelector('.storyModalWriterId').textContent = writerId;
	document.querySelector('.storyModalContent').textContent = content;
	document.querySelector('.storyModalCreatedAt').textContent = createdAt;
	document.querySelector('#reportStoryId').value = storyCardId;
	document.querySelector('#currentCardStoryFeed').value = currentCardStoryFeed;
	
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
    stompClient = Stomp.over(ws);

    // 구독신청 
    stompClient.connect({}, () => {
        console.log('챗구독챗구독챗구독');

        stompClient.subscribe('/chatting', (payloads) => {
        	console.log('구독구독');
            chatting(payloads);
            console.log(payloads);
        });
        
		const memberId = document.getElementById('memberId').value;
         const sendInterval = setInterval(() => {
			stompClient.send(`/chat`, {}, JSON.stringify({ memberId: memberId }));
        }, 1000);
    });
};

const chatting = (payload) => {
	const chat = JSON.parse(payload.body);
	console.log('chat = ', chat);
	console.log('chatting 호출호출호ㅜㄹ');

	const chatDiv = document.querySelector('#chat-div');
	const memberId = document.getElementById('userId').value;

	const chatMessageDiv = document.createElement('div');
	
	if (chat.id == memberId) {
		chatMessageDiv.classList.add('d-flex', 'flex-row', 'justify-content-end', 'mb-4', 'pt-1');
		chatMessageDiv.innerHTML = `
            <div>
                <p class="small p-2 me-3 mb-1 text-white rounded-3 bg-primary">${chat.content}</p>
                <p class="small me-3 mb-3 rounded-3 text-muted d-flex justify-content-end">${chat.time}</p>
            </div>
        `;
	} else {
		chatMessageDiv.classList.add('d-flex', 'flex-row', 'justify-content-start', 'align-items-center', 'mb-4', 'pt-1');
		chatMessageDiv.innerHTML = `
            <div class="d-flex flex-column">
                <p style="font-size: 14px; margin-bottom: 5px; font-weight: bold; margin-left: 10px">${chat.nickname}</p>
                <div class="d-flex align-items-center" onmouseover="showButton(this)" onmouseout="hideButton(this)">
                    <p class="small p-2 ms-3 mb-1 rounded-3" style="background-color: #f5f6f7;">
                        ${chat.content}
                    </p>
                    <button class="btn btn-sm btn-light d-none btn-toggle" style="margin-left: 10px; font-size:20px;"  onclick="goReport(${chat.nickname});">🚨</button>
                </div>
                <p class="small ms-3 mb-3 rounded-3 text-muted">${chat.time}</p>
            </div>
        `;
	}

	chatDiv.appendChild(chatMessageDiv);

	// 스크롤 아래로 이동
	const chatContainer = document.getElementById('chat-div');
	chatContainer.scrollTop = chatContainer.scrollHeight;
};