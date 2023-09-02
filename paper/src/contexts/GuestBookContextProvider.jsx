import axios from 'axios';
import React, { createContext, useState } from 'react'

// 변수 공유하기 위한 context 선언 자리
export const GuestBookContext = createContext();
const SpringBaseURL = "http://localhost:8080"

const GuestBookContextProvider = (props) => {
    // 변수 선언 자리 (내부)
    const [guestBookList,setGuestBookList] = useState([]);

    // 요청 함수 자리
    const getGuestBookList = async(memberId, pageNo) =>{
        return await axios.get(SpringBaseURL+`/guestbook/list?id=${memberId}&page=${pageNo}`)
    }

    // 공유 선언 자리
    const value = {
        states:{
            guestBookList
        },
        actions:{
            setGuestBookList,
            getGuestBookList
        },
    }
  return (
    <GuestBookContext.Provider value={value}>
      {props.children}
    </GuestBookContext.Provider>
  )
}

export default GuestBookContextProvider