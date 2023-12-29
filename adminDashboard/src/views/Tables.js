/*
* Product Page: https://www.creative-tim.com/product/paper-dashboard-react
* Copyright 2023 Creative Tim (https://www.creative-tim.com)
*/

import React, { useContext, useEffect, useRef, useState } from "react";
import { Link } from "react-router-dom";

// reactstrap components
import {
  Card,
  CardHeader,
  CardBody,
  CardTitle,
  Table,
  Row,
  Col,
  Pagination,
  PaginationItem,
  PaginationLink,
} from "reactstrap";
import { MemberContext } from "variables/MemberContnextProvider";

function Tables() {
  const [currentPage, setCurrentPage] = useState(1);
  const PageNoPerLine = 10;

  const { states: { memberPage, memberTotalCount, memberTotalPages, pageNo }, actions: { setMemberTotalPages, getMemberPage, setMemberPage, setPageNo } } = useContext(MemberContext);
  

  const PageSize = 10;

  setMemberTotalPages(Math.ceil(memberTotalCount / PageSize))
  useEffect(()=>{
    getMemberPage(currentPage)
    .then((resp)=>{
      console.log(resp)
      setMemberPage(resp.data);
    })
    .catch((err)=>{
      console.log(err);
    })
    window.scrollTo(0, 0);
  },[currentPage])



   const renderMemberRows = () => {
    return memberPage ? 
    memberPage.map((member) => (
      <tr key={member.id}>
        <td>{member.id}</td>
        <td><Link to={`/admin/user/${member.id}`} state={member}>{member.username}</Link></td>
        <td>{member.name}</td>
        <td>CREDENTIALS</td>
        <td>{member.nickname}</td>
        <td>{member.birthday}</td>
        <td>{member.gender}</td>
        <td>{member.phone}</td>
        <td>{member.email}</td>
        <td>{member.provider}</td>
        <td>{member.regDate}</td>
        <td className="text-right">
          {member.authorities.map((authority) => authority.authority).join(', ')}
        </td>
      </tr>
    )) : null ;
  };


  const renderPaginationItems = () => {
    const paginationItems = [];
    let end = Math.ceil(currentPage/PageNoPerLine)* PageNoPerLine;
    const start = (Math.ceil(currentPage/PageNoPerLine)-1)* PageNoPerLine+1;
    if(memberTotalPages < end){
      end = memberTotalPages
    }
    for (let pageIndex = start ; pageIndex <= end ; pageIndex++) {
      paginationItems.push(
        <PaginationItem key={pageIndex} active={pageIndex === currentPage}>
          <PaginationLink href="#table" onClick={() => {
            setCurrentPage(pageIndex);
          }}>
            {pageIndex}
          </PaginationLink>
        </PaginationItem>
      );
    }
    return paginationItems;
  };

  return (
    <>
      <div className="content" id="table">
        <Row>
          <Col md="12">
            <Card className="card-plain">
              <CardHeader>
                <CardTitle tag="h4">전체 회원 정보</CardTitle>
                <p className="card-category">
                  상세 정보를 확인하시려면, 회원 Username을 클릭해주세요.
                </p>
              </CardHeader>
              <CardBody>
                <Table responsive>
                  <thead className="text-primary">
                    <tr>
                      <th>Id</th>
                      <th>Username</th>
                      <th>Name</th>
                      <th>Password</th>
                      <th>Nickname</th>
                      <th>Birthday</th>
                      <th>Gender</th>
                      <th>Phone</th>
                      <th>Email</th>
                      <th>Provider</th>
                      <th>Reg_date</th>
                      <th className="text-right">Authorities</th>
                    </tr>
                  </thead>
                  <tbody>
                    {renderMemberRows()}
                  </tbody>
                </Table>
              </CardBody>
            </Card>
          </Col>
        </Row>
        <Row>
          <Col></Col>
          <Col>
            <Pagination size="">
              <PaginationItem>
                <PaginationLink
                  first
                  onClick={()=>{setCurrentPage(1)}}
                />
              </PaginationItem>
              <PaginationItem>
                <PaginationLink
                  onClick={()=>{
                    if(currentPage === 1) return;
                    setCurrentPage(currentPage-1);}}
                  previous
                />
              </PaginationItem>
              {renderPaginationItems()}
              <PaginationItem>
                <PaginationLink
                  next
                  onClick={()=>{
                    if(currentPage === memberTotalPages) return;
                    setCurrentPage(currentPage+1)}}
                />
              </PaginationItem>
              <PaginationItem>
                <PaginationLink
                  onClick={()=>{setCurrentPage(memberTotalPages)}}
                  last
                />
              </PaginationItem>
            </Pagination>
          </Col>
          <Col></Col>
        </Row>
      </div>
    </>
  );
}

export default Tables;