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
import React, { useContext, useEffect, useState } from "react";
// react plugin used to create charts
import { Line, Pie } from "react-chartjs-2";
// reactstrap components
import {
  Card,
  CardHeader,
  CardBody,
  CardFooter,
  CardTitle,
  Row,
  Col,
} from "reactstrap";
// core components
import {
  dashboard24HoursPerformanceChart,
  dashboardEmailStatisticsChart,
  dashboardNASDAQChart,
  photofeedValues,
  storyValues,
  guestbookValues
} from "variables/charts.js";
import '../assets/css/style.css'
import { AppContext } from "variables/AppContextProvider";
import { PhotofeedContext } from "variables/PhotofeedContextProvider";
import { MemberContext } from "variables/MemberContnextProvider";
import { ReportContext } from "variables/ReportContextProvider";
import { StoryContext } from "variables/StoryContextProvider";
import { GuestbookContext } from "variables/GuestContextProvider";


function Dashboard() {
  const { states: { serverState }, actions: { ping, setServerState } } = useContext(AppContext)
  const { states: { feedTotalNo }, actions: { getTotalFeedCount, setFeedTotalNo } } = useContext(PhotofeedContext)
  const { states: { memberTotalCount }, actions: { getMemberTotlaCount, setMemberTotalCount } } = useContext(MemberContext);
  const { states: { totalReportCount }, actions: { getTotalReportCount, setTotalReportCount } } = useContext(ReportContext);
  const { states: { dailyFeed }, actions: { setDailyFeed, getDailyFeed } } = useContext(PhotofeedContext);
  const { actions: { getDailyStory } } = useContext(StoryContext);
  const { actions: { getDailyGuestBook } } = useContext(GuestbookContext);
  const [re, setRe] = useState(false);

  useEffect(() => {
    console.log("mount")
    handlePing();
    handleGetTotalFeedCount();
    handleGetMemberTotalCount();
    handleGetReportTotalCount();
    //  handleGetDailyFeed();
    //  handleGetDailyStory();
    //  handleGetDailyGuestBook();
    setRe(false)
  }, [re])

  //const temp = dashboard24HoursPerformanceChart.values;
  //temp();
  //console.log(temp);

  const handlePing = () => {
    ping()
      .then((resp) => {
        setServerState(true)
      })
      .catch((err) => {
        console.log(err)
        setServerState(false)
      })
  }

  const handleGetTotalFeedCount = () => {
    getTotalFeedCount()
      .then((resp) => {
        setFeedTotalNo(resp.data)
      })
      .catch((err) => {
        console.log(err)
      })
  }

  const handleGetMemberTotalCount = () => {
    getMemberTotlaCount()
      .then((resp) => {
        setMemberTotalCount(resp.data)
      })
      .catch((err) => {
        console.log(err)
      })
  }
  const handleGetReportTotalCount = () => {
    getTotalReportCount()
      .then((resp) => {
        setTotalReportCount(resp.data);
      })
      .catch((err) => {
        console.log(err)
      })
  }
  // const handleGetDailyFeed = () => {
  //   photofeedValues = [];
  //   getDailyFeed()
  //   .then((resp) => {
  //       //console.log(resp.data);
  //       //dashboard24HoursPerformanceChart.values = [...dashboard24HoursPerformanceChart.values, ...resp.data];
  //       //console.log(dashboard24HoursPerformanceChart.values);
  //       resp.data.forEach((item) =>{   
  //         console.log(item.photofeedCount);
  //         photofeedValues.push(item.photofeedCount);

  //       });
  //       console.log(photofeedValues);

  //   })
  //   .catch((err)=>{
  //     console.log(err);
  //   })
  // }

  const handleRefresh = () => {
    console.log("hi")
    setRe(true);
  }
  // const handleGetDailyStory = () => {
  //   storyValues = [];
  //   getDailyStory()
  //     .then((resp) => {

  //       resp.data.forEach((item) => {
  //         storyValues.push(item.storyCount);
  //         // console.log("storyCount",item.storyCount);

  //       });
  //       console.log(storyValues);
  //     })
  //     .catch((err) => {
  //       console.log(err);
  //     })
  // }

  // const handleGetDailyGuestBook = () => {
  //   guestbookValues = [];
  //   getDailyGuestBook()
  //     .then((resp) => {
  //       resp.data.forEach((item) => {
  //         guestbookValues.push(item.guestbookCount);
  //         //console.log("guestbookitem",item.guestbookCount);
  //       });
  //       console.log(guestbookValues);
  //     })
  //     .catch((err) => {
  //       console.log(err);
  //     })
  // }

  return (
    <>
      <div className="content">
        <Row>
          <Col lg="3" md="6" sm="6">
            <Card className="card-stats">
              <CardBody>
                <Row>
                  <Col md="4" xs="5">
                    <div className="icon-big text-center icon-warning">
                      <i className="nc-icon nc-globe text-warning" />
                    </div>
                  </Col>
                  <Col md="8" xs="7">
                    <div className="numbers">
                      <p className="card-category">서버 상태</p>
                      <CardTitle tag="p">{serverState ? "OPEN" : "CLOSE"}</CardTitle>
                      <p />
                    </div>
                  </Col>
                </Row>
              </CardBody>
              <CardFooter onClick={handlePing}>
                <hr />
                <div className="stats">
                  <i className="fas fa-sync-alt" /> Update Now
                </div>
              </CardFooter>

            </Card>
          </Col>
          <Col lg="3" md="6" sm="6">
            <Card className="card-stats">
              <CardBody>
                <Row>
                  <Col md="4" xs="5">
                    <div className="icon-big text-center icon-warning">
                      <i className="nc-icon nc-camera-compact text-success" />
                    </div>
                  </Col>
                  <Col md="8" xs="7">
                    <div className="numbers">
                      <p className="card-category">포토 피드</p>
                      <CardTitle tag="p">{feedTotalNo}</CardTitle>
                      <p />
                    </div>
                  </Col>
                </Row>
              </CardBody>
              <CardFooter onClick={handleGetTotalFeedCount}>
                <hr />
                <div className="stats">
                  <i className="fas fa-sync-alt" /> Update Now
                </div>
              </CardFooter>
            </Card>
          </Col>
          <Col lg="3" md="6" sm="6">
            <Card className="card-stats">
              <CardBody>
                <Row>
                  <Col md="4" xs="5">
                    <div className="icon-big text-center icon-warning">
                      <i className="nc-icon nc-vector text-danger" />
                    </div>
                  </Col>
                  <Col md="8" xs="7">
                    <div className="numbers">
                      <p className="card-category">접수된 신고</p>
                      <CardTitle tag="p">{totalReportCount}</CardTitle>
                      <p />
                    </div>
                  </Col>
                </Row>
              </CardBody>
              <CardFooter onClick={handleGetReportTotalCount}>
                <hr />
                <div className="stats">
                  <i className="fas fa-sync-alt" /> Update Now
                </div>
              </CardFooter>
            </Card>
          </Col>
          <Col lg="3" md="6" sm="6">
            <Card className="card-stats">
              <CardBody>
                <Row>
                  <Col md="4" xs="5">
                    <div className="icon-big text-center icon-warning">
                      <i className="nc-icon nc-favourite-28 text-primary" />
                    </div>
                  </Col>
                  <Col md="8" xs="7">
                    <div className="numbers">
                      <p className="card-category">총 회원 수</p>
                      <CardTitle tag="p">{memberTotalCount}</CardTitle>
                      <p />
                    </div>
                  </Col>
                </Row>
              </CardBody>
              <CardFooter onClick={handleGetMemberTotalCount}>
                <hr />
                <div className="stats">
                  <i className="fas fa-sync-alt" /> Update Now
                </div>
              </CardFooter>
            </Card>
          </Col>
        </Row>
        <Row>
          <Col md="12">
            <Card>
              <CardHeader>
                <CardTitle tag="h5">게시물 등록 추이</CardTitle>
                <p className="card-category">24 Hours performance</p>
              </CardHeader>
              <CardBody>
                <Line
                  data={dashboard24HoursPerformanceChart.data}
                  options={dashboard24HoursPerformanceChart.options}
                  width={400}
                  height={100}
                />
              </CardBody>
              <CardFooter>
                <hr />
                <div className="stats" onClick={handleRefresh} style={{ cursor: "point" }}>
                  <i className="fa fa-history" /> Refresh
                </div>
              </CardFooter>
            </Card>
          </Col>
        </Row>
        <Row>
          <Col md="4">
            <Card>
              <CardHeader>
                <CardTitle tag="h5">OAuth2.0 가입 비율</CardTitle>
                <p className="card-category">Last Campaign Performance</p>
              </CardHeader>
              <CardBody style={{ height: "266px" }}>
                <Pie
                  data={dashboardEmailStatisticsChart.data}
                  options={dashboardEmailStatisticsChart.options}
                />
              </CardBody>
              <CardFooter>
                <div className="legend">
                  <i className="fa fa-circle custom-text-three" /> Spotify&nbsp;&nbsp;
                  <i className="fa fa-circle text-danger" /> Google&nbsp;&nbsp;
                  <i className="fa fa-circle text-warning" /> Kakao&nbsp;&nbsp;
                  <i className="fa fa-circle text-primary" /> Naver&nbsp;&nbsp;
                  <br />
                  <i className="fa fa-circle custom-text-two" /> SSoy Story{" "}
                  {/* <i className="fa fa-circle custom-text-one" /> Instagram{" "} */}
                  {/* <i className="fa fa-circle custom-text-two" /> Apple{" "} */}
                  

                </div>
                <hr />
                <div className="stats" onClick={handleRefresh} style={{ cursor: "point" }}>
                  <i className="fa fa-history" /> Refresh
                </div>
              </CardFooter>
            </Card>
          </Col>
          <Col md="8">
            <Card className="card-chart">
              <CardHeader>
                <CardTitle tag="h5">가입자, 탈퇴자 추이</CardTitle>
                <p className="card-category">Line Chart with Points</p>
              </CardHeader>
              <CardBody>
                <Line
                  data={dashboardNASDAQChart.data}
                  options={dashboardNASDAQChart.options}
                  width={400}
                  height={100}
                />
              </CardBody>
              <CardFooter>
                <div className="chart-legend">
                  <i className="fa fa-circle text-info" /> 가입자 수{" "}
                  <i className="fa fa-circle text-warning" /> 탈퇴자 수
                </div>
                <hr />
                <div className="card-stats">
                  <i className="fa fa-check" /> Data information certified
                </div>
              </CardFooter>
            </Card>
          </Col>
        </Row>
      </div>
    </>
  );
}

export default Dashboard;