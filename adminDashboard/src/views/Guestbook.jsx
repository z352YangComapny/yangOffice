import React, { useContext, useEffect, useState } from 'react'
import { useNavigate } from 'react-router';
import { Button, Card, CardHeader, CardTitle, Col, Pagination, PaginationItem, PaginationLink, Row, Table } from 'reactstrap'
import { GuestbookContext } from 'variables/GuestContextProvider';

const Guestbook = () => {
  const navigate = useNavigate();
  const {
    states : {
      guestbookList, 
      guestbookTotalNo,
      guestbookId
    },
    actions: {
      setGuestbookList,
      getGuestbookList,
      updateGuestbook,
      deletedGuestbook,
      setGuestbookTotalNo,
      getGuestbookTotalNo,
      setGuestbookId,
      insertReportGuestbook
    }
  } = useContext(GuestbookContext)
  const [currentPage, setCurrentPage] = useState(1);
  const PageNoPerLine = 10;
  const MaxPageNo = Math.ceil(guestbookTotalNo / PageNoPerLine)

  useEffect(()=>{
    getGuestbookTotalNo()
    .then((resp)=>{
      setGuestbookTotalNo(resp.data)
    })
    .catch((err)=>{
      console.log(err)
    })
  },[])

  useEffect(()=>{
    getGuestbookList(currentPage)
    .then((resp)=>{
      setGuestbookList(resp.data)
    })
    .catch((err)=>{
      console.log(err)
    })
  },[currentPage])

  const handleReportGuestbook = (id) => {
    console.log(id);
    insertReportGuestbook(id)
    .then((resp)=>{
      console.log(resp)
      alert('방명록 신고 완료')
      navigate('/admin/tables')
    })
    .catch((err)=>{
      console.log(err)
    })
  }



  const renderGuestBookList = () => {
    const guestBookItems = guestbookList.map((guestbook, index) => (
      <tr key={index}>
        <th scope="row">{guestbook.id}</th>
        <td>{guestbook.writer}</td>
        <td>{guestbook.toMember}</td>
        <td>{guestbook.content}</td>
        <td>{guestbook.regDate}</td>
        <td>
          <Button onClick={() => handleReportGuestbook(guestbook.id)}>신고</Button>
      </td>
      </tr>
    ));
    return guestBookItems
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
            <CardTitle tag="h4">방명록 전체 조회</CardTitle>
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
                toMember
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
            {renderGuestBookList()}
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

export default Guestbook