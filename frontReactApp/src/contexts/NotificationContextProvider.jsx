import axios from 'axios';
import React, { createContext, useState } from 'react'

export const NotificationContext = createContext();

const NotificationContextProvider = (props) => {
    const [message , setMessage ] = useState(null)

  const value = {
    states: {
      message,
    },
    actions: {
      setMessage
    },
  }


  return (
    <NotificationContext.Provider value={value}>
      {props.children}
    </NotificationContext.Provider>

  )
}

export default NotificationContextProvider