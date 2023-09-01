import React, { createContext, useState } from 'react'
import * as SockJS from "sockjs-client";
import { Client } from "@stomp/stompjs";

export const WebSocketContext = createContext();

const WebSocketContextProvider = (props) => {
    const [subject, setSubject] = useState();
    const [sendGoal, setSendGoal] = useState();
    const [wsJSON, setWsJSON] = useState();
    const [subsFn, setSubsFn] = useState();

    const webSocketConnect = (subject, sendGoal, wsJSON, subsFn) => {
        console.log('webSocketConnect 성공');
        const ws = new SockJS(`http://localhost:8080/stomp`);
        const stompClient = new Client({
          connectHeaders: {
            "Authorization": sessionStorage.getItem('token'),
          },
          webSocketFactory: () => ws,
        });
        stompClient.onConnect = () => {
            console.log('onConnect 성공');
            if(subject === '/storyMain'){
                console.log('subject = ', subject);
                console.log('sendGoal = ', sendGoal);
                console.log('wsJSON = ', wsJSON);
                console.log('subsFn = ', subsFn);
                stompClient.subscribe(subject, subsFn);
                stompClient.send(sendGoal, {}, JSON.stringify(wsJSON));
            }
        }
        stompClient.activate();
    };

    const value = {
        states : {
            subject,
            sendGoal,
            wsJSON,
            subsFn
        },
        actions: {
            setSubject,
            setSendGoal,
            setWsJSON,
            setSubsFn,
            webSocketConnect
        }
    };

    return(
        <WebSocketContext.Provider value={value}>
            {props.children}
        </WebSocketContext.Provider>
    )
}

export default WebSocketContextProvider