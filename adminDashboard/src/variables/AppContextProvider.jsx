import React, { createContext, useState } from 'react';
import axios from 'axios';

export const AppContext = createContext()

const AppContextProvider = (props) => {
    const [serverState , setServerState ] = useState(false);

    const getAppData = async (id) => {
      return await axios.get(`/api/v1/appData/${id}`)
    }
    const ping = async () => {
      return await axios.get(`/ping`)
    }

    const value = {
      states : {
        serverState, 
      },
      actions: {
        ping,
        setServerState
      }
    };
    

  return (
    <AppContext.Provider value={value}>
        {props.children}
    </AppContext.Provider>
  )
}

export default AppContextProvider
