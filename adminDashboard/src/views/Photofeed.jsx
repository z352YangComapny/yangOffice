import React, { useContext, useEffect, useState } from 'react'
import { Button, Card, CardBody, CardGroup, CardImg, CardSubtitle, CardText, CardTitle, Col, Pagination, PaginationItem, PaginationLink, Row } from 'reactstrap'
import { PhotofeedContext } from 'variables/PhotofeedContextProvider';

const Photofeed = () => {
  const imageUrlRoot = "http://localhost:8080/resources/upload/feed/"

  const {
    states: {
      photofeed,
      FeedNo,
      feedTotalNo
    },
    actions: {
      getFeed,
      getFeeds,
      getTotalFeedCount,
      setFeedTotalNo,
      setPhotofeed,
      deletedFeed
    } } = useContext(PhotofeedContext);
  const feedSize = 5;
  const MaxPageNo = Math.ceil(feedTotalNo / feedSize);
  const PageNoPerLine = 10;
  const [currentPage, setCurrentPage] = useState(1);

  

  useEffect(() => {
    getFeeds(currentPage)
      .then((resp) => {
        console.log(resp)
        setPhotofeed(resp.data)
      })
      .catch((err) => {
        console.log(err)
      })
  }, [currentPage])

  const renderImageCard = () => {
    if(!photofeed) return;
    const cards = [];
    for (let i = 0; i < photofeed.length; i++) {
      cards.push(
        <Card>
        <CardImg
          alt={photofeed[i].id}
          src={imageUrlRoot+photofeed[i].attachmentNames[0]}
          top
          width="100%"
        />
        <CardBody>
          <CardTitle tag="h5">
            {photofeed[i].username}
          </CardTitle>
          <CardSubtitle
            className="mb-2 text-muted"
            tag="h6"
          >
            {photofeed[i].regDate}
          </CardSubtitle>
          <CardText>
            {photofeed[i].content}
          </CardText>
          <Button>
            신고하기
          </Button>
        </CardBody>
      </Card>
      )
    }
    return cards;
  };

  const renderPaginationItems = () => {
    const paginationItems = [];
    let end = Math.ceil(currentPage / PageNoPerLine) * PageNoPerLine;
    const start = (Math.ceil(currentPage / PageNoPerLine) - 1) * PageNoPerLine + 1;
    if (MaxPageNo < end) {
      end = MaxPageNo
    }
    console.log("start : " + start + " / end : " + end + " / feedTotalNum : " + feedTotalNo)
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
      <div className="content">
        <Row>
          <Col>
            <CardGroup>
              {renderImageCard()}
            </CardGroup>
          </Col>
        </Row>
        <div style={{ height: "20px" }}></div>
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

export default Photofeed