import React, { useContext, useEffect, useState } from 'react'
import { Col, Pagination, PaginationItem, PaginationLink, Row } from 'reactstrap';
import { StoryContext } from 'variables/StoryContextProvider'

const Story = () => {
  const {
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
      setStoryTotalCount,
      getTotalStoryCount
    }
  } = useContext(StoryContext);

  const [currentPage, setCurrentPage] = useState(1);
  const PageNoPerLine = 20;
  const MaxPageNo = Math.ceil(storyTotalCount/PageNoPerLine)

  useEffect(()=>{
    getTotalStoryCount()
    .then((resp)=>{
      setStoryTotalCount(resp.data);
    })
      .catch((err)=>{
        console.log(err)
      })
  },[currentPage]);

  const renderPaginationItems = () => {
    const paginationItems = [];
    let end = Math.ceil(currentPage / PageNoPerLine) * PageNoPerLine;
    const start = (Math.ceil(currentPage / PageNoPerLine) - 1) * PageNoPerLine + 1;
    if (MaxPageNo < end) {
      end = MaxPageNo
    }
    console.log("start : " + start + " / end : " + end + " / feedTotalNum : " + MaxPageNo)
    for (let pageIndex = start; pageIndex <= end; pageIndex++) {
      paginationItems.push(
        <PaginationItem key={pageIndex} active={pageIndex === currentPage}>
          <PaginationLink href="#" onClick={() => setCurrentPage(pageIndex)}>
            {pageIndex}
          </PaginationLink>
        </PaginationItem>
      );
    }
    return paginationItems;
  };
  

  return (
    <>
    <div className='content'>
      <h1>Story</h1>

      <Row>
          <Col />
          <Col>
            <Pagination size="">
              <PaginationItem>
                <PaginationLink
                  first
                  onClick={() => { setCurrentPage(1) }}
                />
              </PaginationItem>
              <PaginationItem>
                <PaginationLink
                  onClick={() => {
                    if (currentPage === 1) return;
                    setCurrentPage(currentPage - 1);
                  }}
                  previous
                />
              </PaginationItem>
              {renderPaginationItems()}
              <PaginationItem>
                <PaginationLink
                  next
                  onClick={() => {
                    if (currentPage === MaxPageNo) return;
                    setCurrentPage(currentPage + 1)
                  }}
                />
              </PaginationItem>
              <PaginationItem>
                <PaginationLink
                  onClick={() => { setCurrentPage(MaxPageNo) }}
                  last
                />
              </PaginationItem>
            </Pagination>
          </Col>
          <Col />
        </Row>
    </div>
    </>
    
  )
}

export default Story