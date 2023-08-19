import axios from 'axios';
import React, { createContext } from 'react'
import { useState } from 'react';


export const ReportContext = createContext();

const ReportContextProvider = (props) => {
    const [totlaReportCount , setTotalReportCount] = useState(0)
    const [] = useState()
    const [] = useState()

    const getTotalReportCount = async () => {
        return await axios.get(`/api/v1/reportCount`)
    }

    const value = {
        states : {
            totlaReportCount,
        },
        actions: {
            getTotalReportCount,
            setTotalReportCount
        }
      };

  return (
    <ReportContext.Provider value={value}>
        {props.children}
    </ReportContext.Provider>
  )
}

export default ReportContextProvider