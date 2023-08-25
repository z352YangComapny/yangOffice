import Phaser from "phaser";

class UI extends Phaser.Scene {
    constructor() {
        super({ key: 'UIScene', active: true });
        this.userPofile = window.userProfile;
        delete window.userProfile;
        this.network = null;
        this.dialogueBubbleText=[];
    }

    create() {
        //chatLog
        this.chatLog = [];
        this.chatLogTexts = [];
        this.createChatLog(0, 460, 700, 170);

        // 입력창 생성
        // 입력창 생성
        // this.createInput(1, 600, 698, 26); // 예시로 위치와 크기 지정

        // // Enter 키 이벤트 설정
        // this.input.keyboard.on('keydown-ENTER', this.handleInput, this);

        this.externalInputElement = document.getElementById('inputElement');

        // Phaser 캔버스에 키 다운 이벤트 리스너 추가
        this.externalInputElement.addEventListener('keydown', this.onSubmit.bind(this));

        //NotiFrm
        this.notificationText = this.add.text(
            150,
            10,
            '',
            {
                font: 'Arial',
                fontSize: '16px',
                fill: '#ffffff',
                backgroundColor: '#00000050',
                padding: {
                    x: 10,
                    y: 5
                },
                align: 'center'
            }
        );
        this.notificationText.setOrigin(0.5, 0);
        this.notificationText.setVisible(false);


        this.registry.set('bubble', this.dialogueBubbleText);
    }

    update() {

        //Network Handler.
        if (this.network === null || this.network === undefined ||
            this.myPlayer === null || this.myPlayer === undefined
            )
         {
            this.network = this.registry.get('network')
        }

        while (this.network.uiNotification.length > 0) {
            const notification = this.network.uiNotification.shift();
            switch (notification.message.msgType) {
                case "joinNotification":
                    this.showNotification(notification, 3500, '접속하셨습니다.');
                    break;
                case "leaveNotification":
                    this.showNotification(notification, 3500, '떠나셨습니다.');
                    break;
                case "chat":
                    this.handleOtherPlayerLog(notification.message)
                    break;
                default:
                    break;
            }
        }
    }

    handleOtherPlayerLog({msgType,text}){
        if(text.split(' ')[0] !== this.userPofile.username)
        this.addToChatLog(text)
    }

    showNotification(message, duration, mode) {
        console.log(message)
        this.notificationText.setVisible(true);
        this.notificationText.setText(`${message.message.name}님이 ${mode}`);

        this.time.delayedCall(duration, () => {
            this.notificationText.setVisible(false);
            this.notificationText.setText('');
        });
    }

    onSubmit(event) {
        console.log(event)
        // Enter 키(코드 13) 감지
        if(event.key == 'Enter'){
        // 외부 HTML 입력 요소의 값 가져와서 채팅 로그에 추가
        const inputValue = this.externalInputElement.value;
        const textValue = this.userPofile.username+" : "+inputValue;

        this.addToChatLog(textValue);
        this.dialogueBubbleText.push(textValue);
        this.externalInputElement.value = '';

        event.preventDefault();
        }
    }


    createInput(x, y, width, height) {
        this.inputBox = this.add.graphics();
        this.inputBox.fillStyle(0xffffff, 1);
        this.inputBox.fillRect(x, y, width, height);
        this.inputBox.setVisible(false);
        this.inputBox.lineStyle(2, 0x000000, 1);
        this.inputBox.strokeRect(x, y, width, height);

        this.inputBox.setVisible(false);

        this.inputBoxText = this.add.text(x + 5, y + 5, '', { fontSize: '16px', fill: '#000000' });
        
        

        this.input.keyboard.on('keydown', (event) => {
            if (this.inputBox.visible) {
                if(this.inputBoxText.text.length===0){
                    this.inputBoxText.text+=this.userPofile.username+" : ";
                }
                if (event.key === 'Backspace') {
                    this.inputBoxText.text = this.inputBoxText.text.slice(0, -1);
                } else if (event.key.length === 1) {
                    this.inputBoxText.text += event.key;
                }
                event.stopPropagation();
            }
        });
    }

    handleInput() {
        if (this.inputBox.visible) {
            const inputText = this.inputBoxText.text;
            if (inputText.trim() !== '') {
                this.addToChatLog(inputText);
                this.inputBoxText.setText('');
            }
            this.inputBox.setVisible(false);
        } else {
            this.inputBox.setVisible(true);
        }
    }


    createChatLog(x, y, width, height) {
        this.chatLogBox = this.add.graphics();
        this.chatLogBox.fillStyle(0x000000, 0.7);
        this.chatLogBox.fillRect(x, y, width, height);

        this.chatLogTexts.push(
            this.add.text(x + 10, y + 10, '대화 기록 : ', { fontSize: '16px', fill: '#ffffff' })
        );

        for (let i = 1; i <= 7; i++) {
            this.chatLogTexts.push(
                this.add.text(x + 10, y + 10 + i * 20, '', { fontSize: '14px', fill: '#ffffff' })
            );
        }
    }

    addToChatLog(message) {
        if (this.chatLog.length >= 7) {
            this.chatLog.shift();
        }

        this.chatLog.push(message);
        this.updateChatLog();
    }

    updateChatLog() {
        this.chatLogTexts.slice(1).forEach((text, index) => {
            text.setText(this.chatLog[index]);
        });
    }
}

export default UI;