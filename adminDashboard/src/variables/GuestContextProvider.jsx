import React, { createContext, useState } from 'react';
import axios from 'axios';

export const GuestbookContext = createContext()

const GuestbookContextProvider = (props) => {
    const [guestbookList , setGuestbookList ] = useState([]);
    const [guestbookTotalNo, setGuestbookTotalNo] = useState(0);


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

    const value = {
      states : {
        guestbookList, 
        guestbookTotalNo
      },
      actions: {
        setGuestbookList,
        getGuestbookList,
        updateGuestbook,
        deletedGuestbook,
        setGuestbookTotalNo,
        getGuestbookTotalNo
      }
    };
    

  return (
    <GuestbookContext.Provider value={value}>
        {props.children}
    </GuestbookContext.Provider>
  )
}

export default GuestbookContextProvider