import React, { useContext, useEffect, useState } from 'react'
import { useNavigate } from 'react-router';
import { Button, Card, CardBody, CardGroup, CardHeader, CardImg, CardSubtitle, CardText, CardTitle, Col, Pagination, PaginationItem, PaginationLink, Row } from 'reactstrap'
import { PhotofeedContext } from 'variables/PhotofeedContextProvider';

const Photofeed = () => {
  const imageUrlRoot = "http://localhost:8080/resources/upload/attachment/"
  const navigate = useNavigate();

  const {
    states: {
      photofeed,
      FeedNo,
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
    } } = useContext(PhotofeedContext);
  const feedSize = 5;
  const MaxPageNo = Math.ceil(feedTotalNo / feedSize);
  const PageNoPerLine = 10;
  const [currentPage, setCurrentPage] = useState(1);

  

  useEffect(() => {
    getFeeds(currentPage)
      .then((resp) => {
        //console.log(resp)
        setPhotofeed(resp.data)
      })
      .catch((err) => {
        console.log(err)
      })
  }, [currentPage])

  // 로드 될때 무조건 1번은 실행(컴포넌트가 마운트될때)
  useEffect(()=>{
    
  },[]);


  const handleReportPhotoFeed = (id) => {
    console.log(id);
    insertReportPhotoFeed(id)
    .then((resp)=>{
      console.log(resp)
      alert('포토피드 신고 완료')
      navigate('/admin/tables')
    })
    .catch((err)=>{
      console.log(err)
    })
  }

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
          <Button onClick={() => handleReportPhotoFeed(photofeed[i].id)}>
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
      <Card className='card-plain'>
          <CardHeader>
            <CardTitle tag="h4">사진피드 전체 조회</CardTitle>
            <p className="card-category">
              시간순으로 정렬되어 있습니다.
            </p>
          </CardHeader>
        </Card>
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