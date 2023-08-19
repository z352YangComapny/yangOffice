import React, { createContext, useState } from 'react';
import axios from 'axios';

export const GuestbookContext = createContext()

const GuestbookContextProvider = (props) => {
    const [guestbook , setGuestbook ] = useState([]);
    const [guestbookNo , setGuestbookNo ] = useState(0);
    const [guestbookTotalNo, setGuestbookTotalNo] = useState(0);

    const getGuestbook = async (id) => {
      return await axios.get(`/api/v1/guestbook/${id}`)
    }
    const getGuestbooks = async (pageNo) => {
      return await axios.get(`/api/v1/guestbook/${pageNo}`)
    }
    const updateGuestbook = async (id, guestbook) => {
      return await axios.patch(`/api/v1/guestbook/${id}`)
    }
    const deletedGuestbook = async (id) => {
      return await axios.delete(`/api/v1/guestbook/${id}`)
    }

    const value = {
      states : {
        guestbook, 
        guestbookNo,
        guestbookTotalNo
      },
      actions: {
        getGuestbook,
        getGuestbooks,
        updateGuestbook,
        deletedGuestbook,
      }
    };
    

  return (
    <GuestbookContext.Provider value={value}>
        {props.children}
    </GuestbookContext.Provider>
  )
}

export default GuestbookContextProvider