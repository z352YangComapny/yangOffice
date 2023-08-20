import React, { useContext, useEffect, useState } from 'react'
import { Button, Card, CardHeader, CardTitle, Col, Pagination, PaginationItem, PaginationLink, Row, Table } from 'reactstrap';
import { StoryContext } from 'variables/StoryContextProvider'

const Story = () => {
  const {
    states: {
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
  } = useContext(StoryContext);

  const [currentPage, setCurrentPage] = useState(1);
  const PageNoPerLine = 10;
  const MaxPageNo = Math.ceil(storyTotalCount / PageNoPerLine)

  useEffect(() => {
    getTotalStoryCount()
      .then((resp) => {
        setStoryTotalCount(resp.data);
      })
      .catch((err) => {
        console.log(err)
      })

  }, [])

  useEffect(() => {
    getStoryList(currentPage)
      .then((resp) => {
        console.log(resp)
        setStoryList(resp.data)
      })
      .catch((err) => {
        console.log(err)
      })
  }, [currentPage]);


  const renderStoryList = () => {
    const storyItems = storyList.map((story, index) => (
      <tr key={index}>
        <th scope="row">{story.id}</th>
        <td>{story.username}</td>
        <td>{story.content}</td>
        <td>{story.regDate}</td>
        <td>
          <Button onClick={() => console.log(`${story.id}`)}>신고</Button>
      </td>
      </tr>
    ));
    return storyItems
  };

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
        <Card className='card-plain'>
          <CardHeader>
            <CardTitle tag="h4">Story 전체 조회</CardTitle>
            <p className="card-category">
              시간순으로 정렬되어 있습니다.
            </p>
          </CardHeader>
        </Card>
        <Table striped>
          <thead>
            <tr>
              <th>
                Id
              </th>
              <th>
                Writer
              </th>
              <th>
                Content
              </th>
              <th>
                RegDate
              </th>
            </tr>
          </thead>
          <tbody>
            {renderStoryList()}
          </tbody>
        </Table>

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