class Network {
    constructor(name, notification) {
        this.name = name;
        this.token = localStorage.getItem('token');
        this.socket = new WebSocket("ws://localhost:7070/ws");
        this.socket.onopen = this.handleSocketOpen.bind(this);
        this.socket.onclose = this.handleSocketClose.bind(this);
        this.socket.onerror = this.handleSocketClose.bind(this);
        this.socket.onmessage = this.handleSocketMessage.bind(this);
        this.logoffMember = [];
        this.pendingNotification = [];
        this.uiNotification = [];
        this.access = false;
    }

    handleSocketOpen(e) {
        if (!this.token) {
            console.log("로그인을 먼저 해주세요.");
            this.access = false;
            this.disconnect();
            return
        }
        this.socket.send(JSON.stringify({
            msgType: 'authorization',
            token: this.token,
            name: this.name,
        }));
        this.access = true;
        console.log("onOnpen")
    }
    handleSocketClose(close) {
        console.log(close)
        this.access = false;
        this.socket.close();
    }
    handleSocketError(error) {
        console.log(error)
        this.access = false;
        this.socket.close();
    }
    handleSocketMessage(event) {
        const message = JSON.parse(event.data);
        if (message.msgType === "joinNotification"||message.msgType === "leaveNotification"||message.msgType === "chat") {
            this.uiEnqueueNotification(message);
        }
        this.mainEnqueueNotification(message);
    }

    mainEnqueueNotification(message) {
        this.pendingNotification.push({ message });
    }
    uiEnqueueNotification(message) {
        this.uiNotification.push({ message });
    }

    handleSendChatMsg(message){
        const msg = {
            msgType:"chat",
            text:message
        }
        this.socket.send(JSON.stringify(msg))
    }


    updatePlayer(x, y, animkey, name) {
        const player = {
            msgType: "position",
            name: name,
            x: x,
            y: y,
            animkey: animkey,
        }
        this.socket.send(JSON.stringify(player))
    }
    disconnect() {
        if (this.socket) {
            this.socket.close();
            console.log("서버 접속 종료");
        }
    }



}

export default Network