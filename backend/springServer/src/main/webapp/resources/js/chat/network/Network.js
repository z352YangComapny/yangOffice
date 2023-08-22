

export default class Network {
    constructor(){
        this.token = localStorage.getItem('nktsca')
        this.socket = new WebSocket("ws://localhost:7070/ws");
        this.socket.onopen = this.handleSocketOpen.bind(this);
        this.socket.onclose = this.handleSocketClose.bind(this);
        this.socket.onerror= this.handleSocketClose.bind(this);
        console.log("const init")
        console.log(this.token)
    }

    handleSocketOpen(e) {
        if(this.token === null){
            console.log("로그인을 먼저 해주세요.");
            return
        }
        this.socket.send(JSON.stringify({
            msgType: 'authorization',
            token: this.token
        }));
        console.log("onOnpen")
    }
    handleSocketClose(close) {
        console.log(close)
        this.socket.close()
    }
    handleSocketError(error) {
        console.log(error)
    }

}