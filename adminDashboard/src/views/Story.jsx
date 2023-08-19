import React, { useContext, useEffect } from 'react'
import { StoryContext } from 'variables/StoryContextProvider'

const Story = () => {
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
      deletedStory,
      setStoryTotalCount,
      getTotalStoryCount
    }
  } = useContext(StoryContext)
  const [currentPage, setCurrentPage] = useState(1);

  useEffect(()=>{
    getTotalStoryCount()
    .then((resp)=>{
      setStoryTotalCount(resp.data)})
      .catch((err)=>{
        console.log(err)
      })
  },[currentPage])
  

  return (
    <>
    <div className='content'>
      <h1>Story</h1>
    </div>
    </>
    
  )
}

export default Story