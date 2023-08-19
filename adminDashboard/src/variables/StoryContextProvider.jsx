import React, { createContext, useState } from 'react';
import axios from 'axios';

export const StoryContext = createContext()

const StoryContextProvider = (props) => {
    const [StoryList , setStoryList ] = useState([]);
    const [StoryPage , setStoryPage] = useState(0);
    const [StoryTotalPages, setStoryTotalPages] = useState(0);

    const getStory = (id) => {}
    const getStoryList = () => {}
    const updateStory = (Story) => {}
    const deletedStory = (id) => {}

    
    const value = {
      states : {
        StoryList, 
        StoryPage,
        StoryTotalPages
      },
      actions: {
        getStory,
        getStoryList,
        updateStory,
        deletedStory
      }
    };

  return (
    <StoryContext.Provider value={value}>
        {props.children}
    </StoryContext.Provider>
  )
}

export default StoryContextProvider