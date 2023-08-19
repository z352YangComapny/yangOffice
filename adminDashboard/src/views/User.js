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
import React from "react";

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

function User(props) {

  const handleResetPassword = (e) => {
    e.preventDefault();
    console.log('Reset PW init')
  }

  const handleUpdateMember = (e) => {
    e.preventDefault();
    console.log('Update Member init')
    console.log(e)
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
                      src={require("assets/img/mike.jpg")}
                    />
                    <h5 className="title">Chet Faker</h5>
                  </a>
                  <p className="description">@chetfaker</p>
                </div>
                <p className="description text-center">
                  "I like the way you work it <br />
                  No diggity <br />I wanna bag it up"
                </p>
              </CardBody>
              <CardFooter>
                <hr />
                <div className="button-container">
                  <Row>
                    <Col className="ml-auto" lg="3" md="6" xs="6">
                      <h5>
                        12 <br />
                        <small>Follower</small>
                      </h5>
                    </Col>
                    <Col className="ml-auto mr-auto" lg="4" md="6" xs="6">
                      <h5>
                        2GB <br />
                        <small>Followee</small>
                      </h5>
                    </Col>
                    <Col className="mr-auto" lg="3">
                      <h5>
                        24,6$ <br />
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
                          defaultValue="Creative Code Inc."
                          disabled
                          placeholder="Company"
                          type="text"
                        />
                      </FormGroup>
                    </Col>
                    <Col className="px-1" md="3">
                      <FormGroup>
                        <label>password</label>
                        <Input
                          defaultValue="Credentials"
                          placeholder="Username"
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
                        <Input placeholder="Email" type="email" />
                      </FormGroup>
                    </Col>
                  </Row>
                  <Row>
                    <Col className="pr-1" md="6">
                      <FormGroup>
                        <label>Name</label>
                        <Input
                          defaultValue="Chet"
                          placeholder="Name"
                          type="text"
                        />
                      </FormGroup>
                    </Col>
                    <Col className="pl-1" md="6">
                      <FormGroup>
                        <label>Nick Name</label>
                        <Input
                          defaultValue="Faker"
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
                          defaultValue="Melbourne, Australia"
                          placeholder="PhoneNumber"
                          type="text"
                        />
                      </FormGroup>
                    </Col>
                    <Col md="4">
                      <FormGroup>
                        <label>RegDate</label>
                        <Input
                          value="1992-06-03"
                          placeholder="PhoneNumber"
                          type="date"
                          disabled
                        />
                      </FormGroup>
                    </Col>
                    <Col md="4">
                      <FormGroup>
                        <label>Authorities</label>
                        <div style={{display:"flex"}}>
                        <div style={{marginLeft:"40px"}}>
                          <Label check>
                            <Input type="checkbox" />
                            User
                          </Label>
                        </div>
                        <div style={{marginLeft:"40px"}}>
                          <Label check>
                            <Input type="checkbox" />
                            Manager
                          </Label>
                        </div>
                        <div style={{marginLeft:"40px"}}>
                          <Label check>
                            <Input type="checkbox" />
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
                          value="1992-06-03"
                          placeholder="Birthday"
                          type="date"
                        />
                      </FormGroup>
                    </Col>
                    <Col className="px-1" md="4">
                      <FormGroup >
                        <label>Gender</label>
                        <div style={{ display: "flex", height: "40px" }}>
                          <div>
                            <label className="radio-label" style={{ display: "flex", alignItems: "center", marginLeft: "40px", height: "100%", }}>
                              <Input type="radio" name="gender" value="M" />
                              Male
                            </label>
                          </div>
                          <div>
                            <label className="radio-label" style={{ display: "flex", alignItems: "center", marginLeft: "40px", height: "100%", }}>
                              <Input type="radio" name="gender" value="F" />
                              Female
                            </label>
                          </div>
                        </div>
                      </FormGroup>
                    </Col>
                    <Col className="pl-1" md="4">
                      <FormGroup>
                        <label>Provider</label>
                        <Input type="select" placeholder="Select a provider">
                          <option>Yang</option>
                          <option>Git</option>
                          <option>Google</option>
                          <option>Instagram</option>
                          <option>Naver</option>
                          <option>Kakao</option>
                          <option>Steam</option>
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
