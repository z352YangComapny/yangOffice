import React, { createContext, useState } from 'react';
import axios from 'axios';

export const StoryContext = createContext()

const StoryContextProvider = (props) => {
    const [storyList , setStoryList ] = useState([]);
    const [storyPage , setStoryPage] = useState(0);
    const [storyTotalCount, setStoryTotalCount] = useState(0);

    const getStory = (id) => {}
    const getStoryList = async (pageNo) => {
      return await axios.get(`/api/v1/story/${pageNo}`)
    }
    const getTotalStoryCount =async () =>{
      return await axios.get('/api/v1/totalStoryCount')
    }
    const updateStory = (Story) => {}
    const deletedStory = (id) => {}

    
    const value = {
      states : {
        storyList, 
        storyPage,
        storyTotalCount
      },
      actions: {
        getStory,
        getStoryList,
        updateStory,
        deletedStory,
        setStoryList,
        setStoryTotalCount,
        getTotalStoryCount
      }
    };

  return (
    <StoryContext.Provider value={value}>
        {props.children}
    </StoryContext.Provider>
  )
}

export default StoryContextProvider