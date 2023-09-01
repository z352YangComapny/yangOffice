import React, { createContext, useState } from 'react';
import axios from 'axios';

export const PhotofeedContext = createContext()

const PhotofeedContextProvider = (props) => {
  const SpringBaseUrl = "http://localhost:8080";
    const [photofeed , setPhotofeed ] = useState([]);
    const [feedNo , setFeedNo ] = useState(0);
    const [feedTotalNo, setFeedTotalNo] = useState(0);
    const [reportPhotoFeed, setReportPhotoFeed] = useState({});

    const getFeed = (id) => {}
    const getFeeds = async (pageNo) => {
      return await axios.get(`/api/v1/feed/${pageNo}`)
    }
    const getTotalFeedCount = async () => {
      return await axios.get('/api/v1/photoFeedCount')
    }
    const deletedFeed = (id) => {}

    const insertReportPhotoFeed = async (feedId) =>{
      return await axios.post(SpringBaseUrl +`/api/v1/insertReportFeed?feedId=`+ feedId);
  }

    const value = {
      states : {
        photofeed, 
        feedNo,
        feedTotalNo,
        reportPhotoFeed
      },
      actions: {
        getFeed,
        getFeeds,
        getTotalFeedCount,
        setFeedTotalNo,
        setPhotofeed,
        deletedFeed,
        setReportPhotoFeed,
        insertReportPhotoFeed
      }
    };

  return (
    <PhotofeedContext.Provider value={value}>
        {props.children}
    </PhotofeedContext.Provider>
  )
}

export default PhotofeedContextProvider
