import axios from 'axios';
import React, { createContext, useState } from 'react'

// 변수 공유하기 위한 context 선언 자리
export const GuestBookContext = createContext();
const SpringBaseURL = "http://localhost:8080"

const GuestBookContextProvider = (props) => {
    // 변수 선언 자리 (내부)
    const [guestBookList,setGuestBookList] = useState([]);

    const Autorization = {
      headers: {
        "Authorization": sessionStorage.getItem('token'),
      }
    };

    // 요청 함수 자리
    const getGuestBookList = async(hostname, pageNo) =>{
        return await axios.get(SpringBaseURL+`/guestbook/list?hostname=${hostname}&page=${pageNo}`)
    }
    const enrollguestBook = async (hostname, content) => {
      return await axios.post(SpringBaseURL+"/guestbook",{username:hostname, content:content},Autorization)
    }
    const deleteGuestbook = async (id) => {
      return await axios.delete(SpringBaseURL+`/guestbook?id=${id}`,Autorization)
    }

    // 공유 선언 자리
    const value = {
        states:{
            guestBookList
        },
        actions:{
            setGuestBookList,
            getGuestBookList,
            enrollguestBook,
            deleteGuestbook
        },
    }
  return (
    <GuestBookContext.Provider value={value}>
      {props.children}
    </GuestBookContext.Provider>
  )
}

export default GuestBookContextProvider