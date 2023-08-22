class Network {
    constructor(){
        this.token=localStorage.getItem('token');
        this.socket = new WebSocket("ws://localhost:7070/ws");
        this.socket.onopen = this.handleSocketOpen.bind(this);
        this.socket.onclose = this.handleSocketClose.bind(this);
        this.socket.onerror= this.handleSocketClose.bind(this);
    }

    handleSocketOpen(e) {
        if(!this.token){
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

    updatePlayer(x, y, animkey) {
        console.log(x,y,animkey)
    }

}

export default Network