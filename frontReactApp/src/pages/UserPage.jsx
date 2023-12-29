import MyPagination from "components/Commons/MyPagination";
import DemoFooter from "components/Footers/DemoFooter";
import UpdateMemberInfoComponents from "components/Member/UpdateMemberInfoComponents";
import WorldProfileComponent from "components/Member/WorldProfileComponent";
import Payment from "components/Payment/Payment";
import Qna from "components/Qna/Qna";

import { MemberContext } from "contexts/MembetContextProvider";
import React, { useContext, useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { Button, Card, CardBody, CardFooter, CardHeader, CardTitle, Col, Form, FormGroup, Input, Label, Pagination, PaginationItem, PaginationLink, Row, Table } from "reactstrap";

const UserPage = (props) => {
  const {
    states: {
      isLogin,
      userProfile
    },
    actions: {
      setUserProfile,
      setIsLogin,
      LogOut,
      signin,
      getUserProfile
    },
  } = useContext(MemberContext);
  const [leftState, setLeftState] = useState(0);
  const [memberFrm, setMemberFrm] = useState(userProfile);
  const navigate = useNavigate();
  const params = useParams();
  const { id } = params;
  console.log(id);
  console.log(userProfile);

  useEffect(() => {
    if(!userProfile)
    getUserProfile(sessionStorage.getItem('username'))
    .then((resp)=>{
      setUserProfile(resp.data)
      setMemberFrm(resp.data)
    })
    .catch((err)=>{
      console.log(err)
    })
  }, [])

  useEffect(() => {
    if(!userProfile)
    getUserProfile(sessionStorage.getItem('username'))
    .then((resp)=>{
      setUserProfile(resp.data)
    })
    .catch((err)=>{
      console.log(err)
    })
  }, [userProfile])


  return (
    <>
      <div className='user-body'>
        <div></div>
        <div className='user-content'>
          <div className='user-blank'></div>
          <Row style={{ height: "100%", width: "100%" }}>
            <Col className='user-left' md={4}>
              <WorldProfileComponent/>
              <Qna></Qna>
            </Col>
            <Col className='user-right' md={8}>
              <UpdateMemberInfoComponents userProfile={userProfile}/>
              <Payment></Payment>
            </Col>
          </Row>
        </div>
      </div>
      <DemoFooter />
    </>

  )
}

export default UserPage