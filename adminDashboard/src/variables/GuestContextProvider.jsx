import React, { createContext, useState } from 'react';
import axios from 'axios';

export const GuestbookContext = createContext()

const GuestbookContextProvider = (props) => {
  const SpringBaseUrl = "http://localhost:8080";
    const [guestbookList , setGuestbookList ] = useState([]);
    const [guestbookTotalNo, setGuestbookTotalNo] = useState(0);
    const [guestbookId, setGuestbookId] = useState(0);
 

    const getGuestbookList = async (pageNo) => {
      return await axios.get(`/api/v1/guestbook/${pageNo}`)
    }
    const updateGuestbook = async (id, guestbook) => {
      return await axios.patch(`/api/v1/guestbook/${id}`)
    }
    const deletedGuestbook = async (id) => {
      return await axios.delete(`/api/v1/guestbook/${id}`)
    }
    const getGuestbookTotalNo = async () => {
      return await axios.get(`/api/v1/guestbookTotalNo`)
    }
    const insertReportGuestbook = async (guestbookId) => {
      const axiosConfig = {
        headers:{
          "Authorization":localStorage.getItem('Authorization')
        }
      }
      console.log(axiosConfig);
      return await axios.post(SpringBaseUrl +`/api/v1/reportGuestBook?guestbookId=`+ guestbookId,{},axiosConfig);
    }
    const getDailyGuestBook = async ()=>{
      return await axios.get(SpringBaseUrl + `/api/v1/guestbook/dailyGuestbook`);
    }

    const value = {
      states : {
        guestbookList, 
        guestbookTotalNo,
        guestbookId
      },
      actions: {
        setGuestbookList,
        getGuestbookList,
        updateGuestbook,
        deletedGuestbook,
        setGuestbookTotalNo,
        getGuestbookTotalNo,
        setGuestbookId,
        insertReportGuestbook,
        getDailyGuestBook
        
      }
    };
    

  return (
    <GuestbookContext.Provider value={value}>
        {props.children}
    </GuestbookContext.Provider>
  )
}

export default GuestbookContextProvider