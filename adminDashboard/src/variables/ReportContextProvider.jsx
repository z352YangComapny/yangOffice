import axios from 'axios';
import React, { createContext } from 'react'
import { useState } from 'react';


export const ReportContext = createContext();
const SpringBaseUrl = "http://localhost:8080";
const ReportContextProvider = (props) => {
    const [totalReportCount , setTotalReportCount] = useState(0);
    const [reports, setReports] = useState([]);
    const [totalReportPages, setTotalReportPages] = useState(0);
    const [pageNo, setPageNo] = useState(1);

    const getTotalReportCount = async () => {
        return await axios.get(`/api/v1/reportCount`);
    }
    
    const getReport = async (pageNo) => {
        return await axios.get(SpringBaseUrl+`/api/v1/reportList?pageNo=`+ pageNo);            
    };

    const value = {
        states : {
            totalReportCount,
            totalReportPages,
            reports,
            pageNo
        },
        actions: {
            getTotalReportCount,
            setTotalReportCount,
            setTotalReportPages,
            getReport,
            setReports,
            setPageNo

        }
      };

  return (
    <ReportContext.Provider value={value}>
        {props.children}
    </ReportContext.Provider>
  )
}

export default ReportContextProvider