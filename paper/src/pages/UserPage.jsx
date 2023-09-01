import { Tab } from "bootstrap";
import DemoFooter from "components/Footers/DemoFooter";
import ProfileComponent from "components/Member/ProfileComponent";
import WorldProfileComponent from "components/Member/WorldProfileComponent";
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
    if (!userProfile) navigate(`/`)
  }, [])

  const handleOnChange = (e) => {
    setMemberFrm({
      ...memberFrm,
      [e.target.name]: e.target.value
    });
  }

  const handleCheckboxChange = (authority) => {
    let updatedAuthorities = [...memberFrm.authorities];
    if (updatedAuthorities.some(auth => auth.authority === authority)) {
      updatedAuthorities = updatedAuthorities.filter(auth => auth.authority !== authority);
    } else {
      updatedAuthorities.push({ authority: authority });
    }
    setMemberFrm({
      ...memberFrm,
      authorities: updatedAuthorities
    });
  };

  const handleSelectChange = (e) => {
    const selectedProvider = e.target.value;
    setMemberFrm({
      ...memberFrm,
      provider: selectedProvider
    });
  };

  const handleRadioChange = (e) => {
    const selectedGender = e.target.value;
    setMemberFrm({
      ...memberFrm,
      gender: selectedGender
    });
  };

  const handleBirthDayOnChange = (e) => {
    const selectedDay = e.target.value;
    setMemberFrm({
      ...memberFrm,
      birthday: selectedDay
    })
  }

  const handleBirthday = () => {
    return memberFrm && memberFrm.birthday.substring(0, 10)
  }

  const handleRegDate = () => {
    return memberFrm && memberFrm.regDate.substring(0, 10)
  }

  const handleResetPassword = (e) => {
    e.preventDefault();
    console.log('Reset PW init')
  }

  const handleUpdateMember = (e) => {
    e.preventDefault();
    console.log(memberFrm);
  }

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
              <Card style={{
                border: 'solid 3px rgba(81, 203, 206, 1)',
                width: '100%',
                transform: 'none',
                transition: 'none',
                marginTop: "20px",
                boxShadow: '4px 4px 8px rgba(81, 203, 206, 0.8)',
                height: '50%'
              }}>
                <CardHeader style={{ backgroundColor: "transparent", paddingTop: "15px" }}>
                  <CardTitle tag="h5" style={{ fontWeight: "600", color: "rgba(81, 203, 206, 0.8)" }}>#User Information</CardTitle>
                </CardHeader>
                <CardBody>
                  <Form>
                    <Row>
                      <Col className="pr-1" md="3">
                        <FormGroup>
                          <label>Username</label>
                          <Input
                            defaultValue={memberFrm && memberFrm.username}
                            disabled
                            placeholder="username"
                            type="text"
                          />
                        </FormGroup>
                      </Col>
                      <Col className="px-1" md="3">
                        <FormGroup>
                          <label>password</label>
                          <Input
                            defaultValue="Credentials"
                            placeholder="password"
                            disabled
                            type="text"
                          />
                        </FormGroup>
                      </Col>
                      <Col className="pl-1" md="6">
                        <FormGroup>
                          <label htmlFor="exampleInputEmail1">
                            Email address
                          </label>
                          <Input placeholder="Email" type="email" name="email" value={memberFrm && memberFrm.email} onChange={handleOnChange} />
                        </FormGroup>
                      </Col>
                    </Row>
                    <Row>
                      <Col className="pr-1" md="6">
                        <FormGroup>
                          <label>Name</label>
                          <Input
                            defaultValue={memberFrm && memberFrm.name}
                            placeholder="Name"
                            type="text"
                            disabled
                          />
                        </FormGroup>
                      </Col>
                      <Col className="pl-1" md="6">
                        <FormGroup>
                          <label>Nick Name</label>
                          <Input
                            value={memberFrm && memberFrm.nickname}
                            name="nickname"
                            onChange={handleOnChange}
                            placeholder="Nick Name"
                            type="text"
                          />
                        </FormGroup>
                      </Col>
                    </Row>
                    <Row>
                      <Col md="6">
                        <FormGroup>
                          <label>PhoneNumber</label>
                          <Input
                            value={memberFrm && memberFrm.phone}
                            onChange={handleOnChange}
                            name="phone"
                            placeholder="PhoneNumber"
                            type="text"
                          />
                        </FormGroup>
                      </Col>
                      <Col md="6">
                        <FormGroup>
                          <label>RegDate</label>
                          <Input
                            value={handleRegDate()}
                            placeholder="regDate"
                            type="date"
                            disabled
                          />
                        </FormGroup>
                      </Col>
                    </Row>
                    <Row>
                      <Col className="pr-1" md="6">
                        <FormGroup>
                          <label>Birthday</label>
                          <Input
                            value={handleBirthday()}
                            placeholder="Birthday"
                            type="date"
                            onChange={handleBirthDayOnChange}
                          />
                        </FormGroup>
                      </Col>
                      <Col className="px-1" md="6">
                        <FormGroup style={{ marginTop: "5px" }} >
                          <label>Gender</label>
                          <div style={{ display: "flex", height: "25px" }}>
                            <div>
                              <label className="radio-label" style={{ display: "flex", alignItems: "center", marginLeft: "40px", height: "100%", }}>
                                <Input type="radio" name="gender" value="M" checked={memberFrm && memberFrm.gender === "M"} onChange={handleRadioChange} disabled />
                                Male
                              </label>
                            </div>
                            <div>
                              <label className="radio-label" style={{ display: "flex", alignItems: "center", marginLeft: "40px", height: "100%", }}>
                                <Input type="radio" name="gender" value="F" checked={memberFrm && memberFrm.gender === "F"} onChange={handleRadioChange} disabled />
                                Female
                              </label>
                            </div>
                          </div>
                        </FormGroup>
                      </Col>
                    </Row>
                    <Row>
                      <div className="update ml-auto mr-auto" style={{ display: "flex", justifyContent: "end" }}>
                        <Button
                          className="btn-round"
                          color="primary"

                          onClick={handleUpdateMember}
                        >
                          Update Profile
                        </Button>
                      </div>
                    </Row>
                  </Form>
                </CardBody>
              </Card>
              <Card style={{
                border: 'solid 3px rgba(81, 203, 206, 1)',
                transform: 'none',
                transition: 'none',
                marginTop: "20px",
                width: "100%",
                height: "40.9%",
                boxShadow: '4px 4px 8px rgba(81, 203, 206, 0.8)'
              }}>
                <CardHeader style={{ display: "flex", justifyContent: "space-between", backgroundColor: "transparent", paddingTop: "15px" }}>
                  <CardTitle tag="h5" style={{ fontWeight: "600", color: "rgba(81, 203, 206, 0.8)" }}>#Point Shop</CardTitle>
                  <div style={{width:"67%"}}>
                    <Pagination
                      className='pagination'
                      aria-label="Page navigation example"
                      size="sm"
                    >
                      <PaginationItem>
                        <PaginationLink
                          first
                          onClick={() => { console.log("hi") }}
                        />
                      </PaginationItem>
                      <PaginationItem>
                        <PaginationLink
                          onClick={() => { console.log("hi") }}
                          previous
                        />
                      </PaginationItem>
                      <PaginationItem>
                        <PaginationLink onClick={() => { console.log("hi") }}>
                          1
                        </PaginationLink>
                      </PaginationItem>
                      <PaginationItem>
                        <PaginationLink onClick={() => { console.log("hi") }}>
                          2
                        </PaginationLink>
                      </PaginationItem>
                      <PaginationItem>
                        <PaginationLink onClick={() => { console.log("hi") }}>
                          3
                        </PaginationLink>
                      </PaginationItem>
                      <PaginationItem>
                        <PaginationLink
                          onClick={() => { console.log("hi") }}
                          next
                        />
                      </PaginationItem>
                      <PaginationItem>
                        <PaginationLink
                          onClick={() => { console.log("hi") }}
                          last
                        />
                      </PaginationItem>
                    </Pagination>
                  </div>
                </CardHeader>
                <CardBody style={{ display: "flex", padding: "0px", height: "100%" }}>
                  <div className="pointshop-body pointshop-body-left">
                    <div className="current-point">
                      <div className="grade">🎉Vip🎉 grade</div>
                      <div className="totla-cash">누적 결제 금액 <br /> 400,000,000₩ </div>
                      <div className="rest-month-cash">이번달 잔여 한도 <br /> 1,500,000₩ </div>
                      <div className="rest-cash">남은 잔액 <br />12,000,000₩ </div>
                      <Button color="primary" style={{ justifySelf: "end" }}>충전하기</Button>
                    </div>
                  </div>
                  <Table className="purchase-list" hover>
                    <thead>
                      <tr>
                        <th>
                          #
                        </th>
                        <th>
                          구매 상품
                        </th>
                        <th>
                          금액
                        </th>
                        <th>
                          구매 시각
                        </th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <th scope="row">
                          1
                        </th>
                        <td>
                          Mark
                        </td>
                        <td>
                          Otto
                        </td>
                        <td>
                          @mdo
                        </td>
                      </tr>
                      <tr>
                        <th scope="row">
                          1
                        </th>
                        <td>
                          Mark
                        </td>
                        <td>
                          Otto
                        </td>
                        <td>
                          @mdo
                        </td>
                      </tr>
                      <tr>
                        <th scope="row">
                          1
                        </th>
                        <td>
                          Mark
                        </td>
                        <td>
                          Otto
                        </td>
                        <td>
                          @mdo
                        </td>
                      </tr>
                      <tr>
                        <th scope="row">
                          1
                        </th>
                        <td>
                          Mark
                        </td>
                        <td>
                          Otto
                        </td>
                        <td>
                          @mdo
                        </td>
                      </tr>
                      <tr>
                        <th scope="row">
                          1
                        </th>
                        <td>
                          Mark
                        </td>
                        <td>
                          Otto
                        </td>
                        <td>
                          @mdo
                        </td>
                      </tr>

                    </tbody>
                  </Table>
                </CardBody>

              </Card>
            </Col>
          </Row>
        </div>
      </div>
      <DemoFooter />
    </>

  )
}

export default UserPage