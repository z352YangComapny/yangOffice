/*!

=========================================================
* Paper Dashboard React - v1.3.2
=========================================================

* Product Page: https://www.creative-tim.com/product/paper-dashboard-react
* Copyright 2023 Creative Tim (https://www.creative-tim.com)

* Licensed under MIT (https://github.com/creativetimofficial/paper-dashboard-react/blob/main/LICENSE.md)

* Coded by Creative Tim

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

*/
/*eslint-disable*/
import React, { useContext, useEffect, useState } from "react";
// react plugin for creating notifications over the dashboard
import NotificationAlert from "react-notification-alert";
// reactstrap components
import {
  UncontrolledAlert,
  Alert,
  Button,
  Card,
  CardHeader,
  CardBody,
  CardTitle,
  Row,
  Col,
  Table,
  Pagination,
  PaginationItem,
  PaginationLink,
} from "reactstrap";
import { ReportContext } from "variables/ReportContextProvider";

function Notifications() {
  const notificationAlert = React.useRef();
  const notify = (place) => {
    var color = Math.floor(Math.random() * 5 + 1);
    var type;
    switch (color) {
      case 1:
        type = "primary";
        break;
      case 2:
        type = "success";
        break;
      case 3:
        type = "danger";
        break;
      case 4:
        type = "warning";
        break;
      case 5:
        type = "info";
        break;
      default:
        break;
    }
    var options = {};
    options = {
      place: place,
      message: (
        <div>
          <div>
            Welcome to <b>Paper Dashboard React</b> - a beautiful freebie for
            every web developer.
          </div>
        </div>
      ),
      type: type,
      icon: "nc-icon nc-bell-55",
      autoDismiss: 7,
    };
    notificationAlert.current.notificationAlert(options);
  };

  
  const { states: { totalReportCount,reports, totalReportPages, pageNo }, actions: { getTotalReportCount, getReport, setReports,setTotalReportPages, setPageNo } } = useContext(ReportContext);
  
  const pageSize= 10;
  const PageNoPerLine = 10;
  setTotalReportPages(Math.ceil(totalReportCount/pageSize));
  console.log("totalreportpages" + totalReportPages);
 console.log("pageNo"+pageNo);
  useEffect(()=>{
    getReport(pageNo)
    .then((response) => {
        console.log(response.data);
        setReports(response.data);
      })
  .catch((err)=>{
      console.log(err)
  })},[pageNo]);

  const renderReportRows =() =>{
  
    return reports ? 
    reports.map((report) => (
      <tr key={report.id}>
        <td>{report.id}</td>
        <td>{report.type}</td>
        <td>{report.writer}</td>
        <td>{report.content}</td>
        <td>{report.regDate}</td>
      </tr>
    )) : null ;
  };
  
  const renderPaginationItems = () => {
    const paginationItems = [];
    let end = Math.ceil(pageNo/PageNoPerLine)* PageNoPerLine;
    const start = (Math.ceil(pageNo/PageNoPerLine)-1)* PageNoPerLine+1;
    if(totalReportPages < end){
      end = totalReportPages
    }
    for (let pageIndex = start ; pageIndex <= end ; pageIndex++) {
      paginationItems.push(
        <PaginationItem key={pageIndex} active={pageIndex === pageNo}>
          <PaginationLink href="#table" onClick={() => {
            setPageNo(pageIndex);
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
      <div className='content'>
        <Card className='card-plain'>
          <CardHeader>
            <CardTitle tag="h4">Report 전체 조회</CardTitle>
            <p className="card-category">
              시간순으로 정렬되어 있습니다.
            </p>
          </CardHeader>
        </Card>
        <Table striped>
          <thead>
            <tr>
              <th>No</th>
              <th>Type</th>
              <th>Writer</th>
              <th>Content</th>
              <th>RegDate</th>
            </tr>
          </thead>
          <tbody>
            {renderReportRows()}
          </tbody>
        </Table>

        <Row>
          <Col />
          <Col>
            <Pagination size="">
              <PaginationItem>
                <PaginationLink
                  first
                  onClick={() => {setPageNo(1)}}
                />
              </PaginationItem>
              <PaginationItem>
                <PaginationLink
                   onClick={() => {
                    if(pageNo === 1) return;
                    setCurrentPage(pageNo-1);}}
                  previous
                />
              </PaginationItem>
              {renderPaginationItems()}
              <PaginationItem>
                <PaginationLink
                  next
                  onClick={() => {
                    if(pageNo === totalReportPages) return;
                    setPageNo(pageNo+1)}}
                />
              </PaginationItem>
              <PaginationItem>
                <PaginationLink
                  onClick={() => {setPageNo(totalReportPages)}}
                  last
                />
              </PaginationItem>
            </Pagination>
          </Col>
          <Col />
        </Row>
      </div>
    </>
  );
}

export default Notifications;
