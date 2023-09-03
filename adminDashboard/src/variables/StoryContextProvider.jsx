import React, { createContext, useState } from 'react';
import axios from 'axios';

export const StoryContext = createContext()

const StoryContextProvider = (props) => {
    const SpringBaseUrl = "http://localhost:8080";
    const [storyList , setStoryList ] = useState([]);
    const [storyPage , setStoryPage] = useState(0);
    const [storyTotalCount, setStoryTotalCount] = useState(0);
    const [reportStory, setReportStory] = useState({});
    const [storyId, setStoryId] = useState(0);

    const getStory = (id) => {}
    const getStoryList = async (pageNo) => {
      return await axios.get(`/api/v1/story/${pageNo}`)
    }
    const getTotalStoryCount =async () =>{
      return await axios.get('/api/v1/totalStoryCount')
    }
    const updateStory = (Story) => {}
    const deletedStory = (id) => {}

    const insertReportStory = async (storyId) => {
      const axiosConfig = {
        headers:{
          "Authorization":localStorage.getItem('Authorization')
        }
      }
      console.log(axiosConfig);
      return await axios.post(SpringBaseUrl +`/api/v1/reportStory?storyId=`+ storyId,{},axiosConfig);
    }

    const getDailyStory = async () => {
      return await axios.get(SpringBaseUrl+`/api/v1/story/dailyStory`)
  }
    
    const value = {
      states : {
        storyList, 
        storyPage,
        storyTotalCount,
        reportStory,
        storyId

      },
      actions: {
        getStory,
        getStoryList,
        updateStory,
        deletedStory,
        setStoryList,
        setStoryTotalCount,
        getTotalStoryCount,
        setReportStory,
        setStoryId,
        insertReportStory,
        getDailyStory

      }
    };

  return (
    <StoryContext.Provider value={value}>
        {props.children}
    </StoryContext.Provider>
  )
}

export default StoryContextProvider