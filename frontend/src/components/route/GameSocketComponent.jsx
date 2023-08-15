import React, { useState, useRef, useEffect } from "react";

const GameSocketComponent = () => {
    const [socketConnected, setSocketConnected] = useState(false);
    const [sendMsg, setSendMsg] = useState(false);
    const [items, setItems] = useState([]);
    const [ws , setWs] = useState(null);
    const [t, setT] = useState(false);

    const socketUrl = `ws://localhost:7070/ws`

    useEffect(() => {
        if (!ws && t) {
            const _ws = new WebSocket('ws://localhost:7070/ws');
            _ws.onopen = () => {
                setWs(_ws)
                const token=localStorage.getItem("nktsca")
                _ws.send(JSON.stringify({
                    msgType : "authorization",
                    token : token,
                }))
                console.log("onOpen")
                setSocketConnected(true)
            }
            _ws.onclose = (error) => {
                console.log("disconnect from " + socketUrl);
                console.log(error);
            };
            _ws.onerror = (error) => {
                console.log("connection error " + socketUrl);
                console.log(error);
            };
            _ws.onmessage = (e) => {
                const data = JSON.parse(e.data);
                console.log(data);
                setItems((prevItems) => [...prevItems, data]);
            };
        }

        return () => {
            if(ws){
            console.log("clean up");
            ws.close();
            }
        }
    }, [t]);


    useEffect(() => {
        if (socketConnected && ws) {
            const sendMessage=localStorage.getItem("nktsca")
            // ws.send(
            //     JSON.stringify({
            //         message: sendMessage,
            //     })
            // );
            setSendMsg(true);
        }
    }, [socketConnected]);

    useEffect(() => {
        if (sendMsg) {
            ws.onmessage = (evt) => {
                console.log(evt);
                setItems((prevItems) => [...prevItems, evt.data]);
            };
        }
    }, [sendMsg]);

    return (
        <div>
            <button onClick={()=>{setT(true)}}>connet</button>
            <div>socket</div>
            <div>socket connected : {`${socketConnected}`}</div>
            <div>res : </div>
            <div>
                {items.map((item) => {
                    return <div>{item}</div>;
                })}
            </div>
        </div>
    );
}

export default GameSocketComponent;