import React, { createContext, useState } from 'react';
import axios from 'axios';

export const PhotofeedContext = createContext()

const PhotofeedContextProvider = (props) => {
    const [photofeed , setPhotofeed ] = useState([]);
    const [FeedNo , setFeedNo ] = useState(0);
    const [feedTotalNo, setFeedTotalNo] = useState(0);

    const getFeed = (id) => {}
    const getFeeds = () => {}
    const getTotalFeedCount = async () => {
      return await axios.get('/api/v1/photoFeedCount')
    }
    const deletedFeed = (id) => {}

    const value = {
      states : {
        photofeed, 
        FeedNo,
        feedTotalNo
      },
      actions: {
        getFeed,
        getFeeds,
        getTotalFeedCount,
        setFeedTotalNo,
        deletedFeed
      }
    };

  return (
    <PhotofeedContext.Provider value={value}>
        {props.children}
    </PhotofeedContext.Provider>
  )
}

export default PhotofeedContextProvider
