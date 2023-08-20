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
import { useLocation, useNavigate } from "react-router-dom";
import axios from "axios";

// reactstrap components
import {
  Button,
  Card,
  CardHeader,
  CardBody,
  CardFooter,
  CardTitle,
  FormGroup,
  Form,
  Input,
  Row,
  Col,
  Label,
} from "reactstrap";
import { MemberContext } from "variables/MemberContnextProvider";

function User(props) {
  const imageUrlRoot = "http://localhost:8080/resources/upload/attachment/"
  const navigate = useNavigate();
  let { state } = useLocation();
  const [memberFrm, setMemberFrm] = useState(state);
  const [profile , setProfile] = useState({});
  const {actions:{updateMember}} = useContext(MemberContext)

  useEffect(()=>{
    axios.get(`/api/v1/profile/${state.id}`)
    .then((resp)=>{
      setProfile(resp.data)
      console.log(resp)
    })
    .catch((err)=>{
      console.log(err)
    })
  },[state.id])

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
      birthday:selectedDay
    })
  }

  const handleBirthday = () => {
    return memberFrm.birthday.substring(0, 10)
  }

  const handleRegDate = () => {
    return memberFrm.regDate.substring(0, 10)
  }

  const handleResetPassword = (e) => {
    e.preventDefault();
    console.log('Reset PW init')
  }

  const handleUpdateMember = (e) => {
    e.preventDefault();
    updateMember(memberFrm)
    .then((resp)=>{
      console.log(resp)
      alert('회원정보 변경 완료')
      navigate('/admin/tables')
    })
    .catch((err)=>{
      console.log(err)
    })
  }

  return (
    <>
      <div className="content">
        <Row>
          <Col md="4">
            <Card className="card-user">
              <div className="image">
                <img alt="..." src={require("assets/img/damir-bosnjak.jpg")} />
              </div>
              <CardBody>
                <div className="author">
                  <a href="#pablo" onClick={(e) => e.preventDefault()}>
                    <img
                      alt="..."
                      className="avatar border-gray"
                      src={imageUrlRoot+profile.renamedFilename}
                    />
                    <h5 className="title">{profile.username}</h5>
                  </a>
                  <p className="description">{memberFrm.name}</p>
                </div>
                <p className="description text-center">
                  {profile.introduction}
                </p>
              </CardBody>
              <CardFooter>
                <hr />
                <div className="button-container">
                  <Row>
                    <Col className="ml-auto" lg="3" md="6" xs="6">
                      <h5>
                        {profile.follower} <br />
                        <small>Follower</small>
                      </h5>
                    </Col>
                    <Col className="ml-auto mr-auto" lg="4" md="6" xs="6">
                      <h5>
                        {profile.followee} <br />
                        <small>Followee</small>
                      </h5>
                    </Col>
                    <Col className="mr-auto" lg="3">
                      <h5>
                        구현중.. <br />
                        <small>누적 신고 수</small>
                      </h5>
                    </Col>
                  </Row>
                </div>
              </CardFooter>
            </Card>
          </Col>
          <Col md="8">
            <Card className="card-user">
              <CardHeader>
                <CardTitle tag="h5">Edit Profile</CardTitle>
              </CardHeader>
              <CardBody>
                <Form>
                  <Row>
                    <Col className="pr-1" md="3">
                      <FormGroup>
                        <label>Usernmae</label>
                        <Input
                          defaultValue={state.username}
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
                        <Input placeholder="Email" type="email" name="email" value={memberFrm.email} onChange={handleOnChange} />
                      </FormGroup>
                    </Col>
                  </Row>
                  <Row>
                    <Col className="pr-1" md="6">
                      <FormGroup>
                        <label>Name</label>
                        <Input
                          defaultValue={memberFrm.name}
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
                          value={memberFrm.nickname}
                          name="nickname"
                          onChange={handleOnChange}
                          placeholder="Nick Name"
                          type="text"
                        />
                      </FormGroup>
                    </Col>
                  </Row>
                  <Row>
                    <Col md="4">
                      <FormGroup>
                        <label>PhoneNumber</label>
                        <Input
                          value={memberFrm.phone}
                          onChange={handleOnChange}
                          name="phone"
                          placeholder="PhoneNumber"
                          type="text"
                        />
                      </FormGroup>
                    </Col>
                    <Col md="4">
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
                    <Col md="4">
                      <FormGroup>
                        <label>Authorities</label>
                        <div style={{ display: "flex" }}>
                          <div style={{ marginLeft: "40px" }}>
                            <Label check>
                              <Input type="checkbox"
                                checked={memberFrm.authorities.some(authority => authority.authority === "ROLE_USER")}
                                onChange={() => handleCheckboxChange("ROLE_USER")}
                              />
                              User
                            </Label>
                          </div>
                          <div style={{ marginLeft: "40px" }}>
                            <Label check>
                              <Input type="checkbox"
                                checked={memberFrm.authorities.some(authority => authority.authority === "ROLE_MANAGER")}
                                onChange={() => handleCheckboxChange("ROLE_MANAGER")}
                              />
                              Manager
                            </Label>
                          </div>
                          <div style={{ marginLeft: "40px" }}>
                            <Label check>
                              <Input type="checkbox"
                                checked={memberFrm.authorities.some(authority => authority.authority === "ROLE_ADMIN")}
                                onChange={() => handleCheckboxChange("ROLE_ADMIN")}
                              />
                              Admin
                            </Label>
                          </div>
                        </div>
                      </FormGroup>
                    </Col>
                  </Row>
                  <Row>
                    <Col className="pr-1" md="4">
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
                    <Col className="px-1" md="4">
                      <FormGroup >
                        <label>Gender</label>
                        <div style={{ display: "flex", height: "40px" }}>
                          <div>
                            <label className="radio-label" style={{ display: "flex", alignItems: "center", marginLeft: "40px", height: "100%", }}>
                              <Input type="radio" name="gender" value="M" checked={memberFrm.gender === "M"} onChange={handleRadioChange} />
                              Male
                            </label>
                          </div>
                          <div>
                            <label className="radio-label" style={{ display: "flex", alignItems: "center", marginLeft: "40px", height: "100%", }}>
                              <Input type="radio" name="gender" value="F" checked={memberFrm.gender === "F"} onChange={handleRadioChange} />
                              Female
                            </label>
                          </div>
                        </div>
                      </FormGroup>
                    </Col>
                    <Col className="pl-1" md="4">
                      <FormGroup>
                        <label>Provider</label>
                        <Input type="select" placeholder="Select a provider" value={memberFrm.provider} onChange={handleSelectChange}>
                          <option value="Yang">Yang</option>
                          <option value="Git">Git</option>
                          <option value="Google">Google</option>
                          <option value="Instagram">Instagram</option>
                          <option value="Naver">Naver</option>
                          <option value="Apple">Apple</option>
                          <option value="Steam">Steam</option>
                        </Input>
                      </FormGroup>
                    </Col>
                  </Row>
                  <Row>
                    <Col md="12">
                      <FormGroup>
                        <label>Report Log</label>
                        <Input
                          type="textarea"
                          defaultValue="Oh so, your weak rhyme You doubt I'll bother, reading into it"
                        />
                      </FormGroup>
                    </Col>
                  </Row>
                  <Row>
                    <div className="update ml-auto mr-auto">
                      <Button
                        className="btn-round"
                        color="primary"
                        type="submit"
                        onClick={handleResetPassword}
                      >
                        Reset Password
                      </Button>

                      <Button
                        className="btn-round"
                        color="primary"
                        type="submit"
                        onClick={handleUpdateMember}
                      >
                        Update Profile
                      </Button>
                    </div>
                  </Row>
                </Form>
              </CardBody>
            </Card>
          </Col>
        </Row>
      </div>
    </>
  );
}

export default User;
