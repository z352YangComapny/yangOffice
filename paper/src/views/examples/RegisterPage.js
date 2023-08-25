/*!

=========================================================
* Paper Kit React - v1.3.2
=========================================================

* Product Page: https://www.creative-tim.com/product/paper-kit-react

* Copyright 2023 Creative Tim (https://www.creative-tim.com)
* Licensed under MIT (https://github.com/creativetimofficial/paper-kit-react/blob/main/LICENSE.md)

* Coded by Creative Tim

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

*/
import React, { useState } from "react";

// reactstrap components
import { Button, Card, Form, Input, Container, Row, Col, FormGroup, Label } from "reactstrap";

// core components
import ExamplesNavbar from "components/Navbars/ExamplesNavbar.js";

function RegisterPage() {
  const [isSignIn, setIsSignIn] = useState(false)

  const handleChangeSignUpFrm = (e) => {
    e.preventDefault();
    setIsSignIn(false)
  }

  document.documentElement.classList.remove("nav-open");
  React.useEffect(() => {
    document.body.classList.add("register-page");
    return function cleanup() {
      document.body.classList.remove("register-page");
    };
  });

  return (
    <>
      <div
        className="page-header"
        style={{
          backgroundImage: "url(" + require("assets/img/6.jpg") + ")",
        }}
      >
        <div className="filter" />
          <Container>
            <Row>
              <Col className="ml-auto mr-auto" lg="4">
                <Card className="card-register ml-auto mr-auto">
                  <h3 className="title mx-auto">Welcome</h3>
                  <div className="social-line text-center">
                    <Button
                      className="btn-neutral btn-just-icon mr-1"
                      color="facebook"
                      href="#pablo"
                      onClick={(e) => e.preventDefault()}
                    >
                      <i className="fa fa-facebook-square" />
                    </Button>
                    <Button
                      className="btn-neutral btn-just-icon mr-1"
                      color="google"
                      href="#pablo"
                      onClick={(e) => e.preventDefault()}
                    >
                      <i className="fa fa-google-plus" />
                    </Button>
                    <Button
                      className="btn-neutral btn-just-icon"
                      color="twitter"
                      href="#pablo"
                      onClick={(e) => e.preventDefault()}
                    >
                      <i className="fa fa-twitter" />
                    </Button>
                  </div>
                  <Form className="register-form">
                    <label>Id</label>
                    <Input placeholder="Id" type="text" />
                    <label>PASSWORD</label>
                    <Input placeholder="PASSWORD" type="password" />
                    <Button block className="btn-round" color="danger">
                      Sign Up
                    </Button>
                  </Form>
                  <div className="forgot">
                    <Button
                      className="btn-link"
                      color="danger"
                      href="#pablo"
                      onClick={(e) => e.preventDefault()}
                    >
                      비밀번호를 잊으셨나요?
                    </Button>
                    <Button
                      className="btn-link"
                      color="danger"
                      href="#pablo"
                      onClick={handleChangeSignUpFrm}
                      style={{ margin: "0px" }}
                    >
                      아직 회원이 아니신가요?
                    </Button>
                  </div>
                </Card>
              </Col>
            </Row>
          </Container>
        <div className="fog-low" style={{ zIndex: -5 }}>
          <img alt="..." src={require("assets/img/fog-low.png")} />
        </div>
        <div className="fog-low right" style={{ zIndex: -5 }}>
          <img alt="..." src={require("assets/img/fog-low.png")} />
        </div>
        <div
          className="moving-clouds"
          style={{
            backgroundImage: "url(" + require("assets/img/clouds.png") + ")",
            zIndex: -6
          }}
        />
        <div className="footer register-footer text-center">
          <h6>
            2023 @ Yang world Project
          </h6>
        </div>
      </div>
    </>
  );
}

export default RegisterPage;
