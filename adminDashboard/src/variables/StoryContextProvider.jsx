import React, { createContext, useState } from 'react';
import axios from 'axios';

export const StoryContext = createContext()

const StoryContextProvider = (props) => {
    const [StoryList , setStoryList ] = useState([]);
    const [StoryPage , setStoryPage] = useState(0);
    const [StoryTotalCount, setStoryTotalCount] = useState(0);

    const getStory = (id) => {}
    const getStoryList = () => {}
    const getTotalStoryCount =async () =>{
      return await axios.get('/api/v1/totalStoryCount')
    }
    const updateStory = (Story) => {}
    const deletedStory = (id) => {}

    
    const value = {
      states : {
        StoryList, 
        StoryPage,
        StoryTotalCount
      },
      actions: {
        getStory,
        getStoryList,
        updateStory,
        deletedStory,
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