import WarningSign from "components/Icons/icons/warning-sign";
import { MemberContext } from "contexts/MembetContextProvider";
import { NotificationContext } from "contexts/NotificationContextProvider";
import React, { useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

// reactstrap components
import { Button, Card, Form, Input, Container, Row, Col, FormGroup, Label, Modal } from "reactstrap";

// core components

function SignIn() {
  const {
    states: { isLogin, userProfile },
    actions: { setUserProfile, setIsLogin, LogOut, signin , getUserProfile},
  } = useContext(MemberContext)
  const {
    states: { message },
    actions: { setMessage }
  } = useContext(NotificationContext)
  const [signInFrm, setSignInFrm] = useState({
    username:"",
    password:""
  })
  const [largeModal, setLargeModal] = useState(false);
  const [smallModal, setSmallModal] = useState(false);
  const [hasError, setHasError] = useState("아이디를 입력해주세요.")

  const navigate = useNavigate();

  const onFrmChange = (e) => {
    setSignInFrm({ ...signInFrm, [e.target.name]: e.target.value })
  }

  useEffect(() => {
    if (isLogin && sessionStorage.getItem('token')) {
      
    } else {
      sessionStorage.removeItem('token');
      sessionStorage.removeItem('nickname');
      sessionStorage.removeItem('username');
      setIsLogin(false);
    }
  }, [isLogin])

  const onSubmit = (e) => {
    e.preventDefault();

    const regex = /^[a-zA-Z0-9]{4,}$/;
    if(!regex.test(signInFrm.username) || !regex.test(setSignInFrm.password)){
      setMessage({ color: "warning", value: `아이디와 비밀번호 4글자 이상 입력해주세요.`})
      return;
    }

    signin(signInFrm).then((resp) => {
      sessionStorage.setItem("token", resp.headers.authorization);
      getUserProfile(signInFrm.username)
      .then((resp)=>{
        console.log(resp.data)
        sessionStorage.setItem('nickname',resp.data.nickname);
        sessionStorage.setItem('username',resp.data.username);
        setMessage({ color: "success", value: `${resp.data.nickname}님 환영합니다.🖤` })
        navigate(`/feed/${sessionStorage.getItem('username')}`)
        setIsLogin(true);
      })
    })
      .catch((err) => {
        switch (err.response.data.status) {
          case 500:
            setMessage({ color: "danger", value: `내부 서부 에러, 관리자에게 문의해주세요.` })
            break;
          case 401:
            setMessage({ color: "danger", value: `아이디와 비밀번호를 확인해주세요.` })
            break;
          default:
            setMessage({ color: "danger", value: `알 수 없는 에러 관리자에게 문의 해주세요 ERRCODE : ${err.response.data.status}`})
        };
      })
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
        <Container style={{ marginTop: "70px" }}>
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
                <Form className="register-form" onSubmit={onSubmit}>
                  <label>Id</label>
                  <Input placeholder="Id" type="text" name="username" onChange={onFrmChange} />
                  <label>PASSWORD</label>
                  <Input placeholder="PASSWORD" type="password" name="password" onChange={onFrmChange} />
                  <Button block className="btn-round" color="danger">
                    Sign Up
                  </Button>
                </Form>
                <div className="forgot">
                  <Button
                    className="btn-link"
                    color="danger"
                    href="#pablo"
                    onClick={() => setSmallModal(true)}
                  >
                    비밀번호를 잊으셨나요?
                  </Button>
                  <Button
                    className="btn-link"
                    color="danger"
                    href="#pablo"
                    // onClick={()=>{navigate('/signup')}}
                    onClick={() => setLargeModal(true)}
                    style={{ margin: "0px" }}
                  >
                    아직 회원이 아니신가요?
                  </Button>
                </div>
              </Card>
            </Col>
          </Row>
        </Container>

        <Modal
          isOpen={largeModal}
          className="modal-xl"
          modalClassName="bd-example-modal-xl"
          // toggle={() => setLargeModal(false)}
        >
          <div className="modal-header">
            <Button color="neutral" style={{ float: "right" }}>
              <i className="fa nc-icon nc-simple-remove" onClick={() => setLargeModal(false)}></i>
            </Button>
            <h3 className="modal-title" id="myLargeModalLabel">
              회 원 가 입
            </h3>
          </div>

          <div className="modal-body">
            <Row>
              <Col md={5}>
                <div className="signInLeft">
                  <h3 style={{ textAlign: "center", marginBottom: "10px" }}>서비스 이용약관</h3>
                  <div className="TermsOfUse">
                    본 이용약관은 양프로젝트팀 (이하 "회사" 또는 "저희")가 제공하는 Yang World (이하 "서비스")의 이용 조건을 규정하는 것을 목적으로 합니다. 회원 여러분은 본 약관을 읽고 이해하신 후 서비스를 이용해 주시기 바랍니다. 이용 약관에 동의하지 않으실 경우, 서비스 이용을 중단하시기 바랍니다.
                    <br />
                    <br />
                    1. 회원의 의무 및 권리
                    <br />
                    1.1 회원은 본인의 개인정보를 정확하게 제공해야 하며, 제공한 정보가 변경되었을 경우에는 즉시 수정해야 합니다.
                    <br />
                    1.2 회원은 서비스를 이용함에 있어서 다음 각 호의 행위를 하여서는 아니 됩니다:
                    <br />
                    - 불법적인 목적으로 서비스를 이용하는 행위
                    <br />
                    - 타인의 개인정보를 무단으로 수집, 저장, 유포하는 행위
                    <br />
                    - 저작권 및 지적재산권을 침해하는 행위
                    <br />
                    <br />
                    2. 사진피드 등록 및 방명록에 관한 사항
                    <br />
                    2.1 회원은 본인이 업로드한 사진 및 피드 등록에 대한 책임을 가집니다. 모니터링 과정에서 부적절한 내용이 확인될 경우, 우리는 해당 내용을 삭제하고 회원에게 알림을 전송할 수 있습니다.
                    <br />
                    <br />
                    3. 회원 정보의 활용
                    <br />
                    3.1 회원 정보는 본인인증 및 서비스 제공을 위한 목적으로만 활용되며, 회원의 동의 없이 제3자에게 제공되지 않습니다.
                    <br />
                    <br />
                    4. 책임의 한계
                    <br />
                    4.1 회사는 천재지변 등 불가항력적인 사유로 인해 서비스가 중단될 경우에는 이로 인한 손해에 대해 책임을 지지 않습니다.
                    <br />
                    본 약관은 2023.08.25에 개정되었습니다.
                  </div>
                  <div style={{ display: "flex", flexDirection: "row-reverse" }}>
                    <p style={{ fontSize: "18px", marginTop: "5px" }}>동의</p><Input type="checkbox" name="agreeTerms" id="agreeTerms" style={{ height: "22px", width: "22px", margin: "5px" }} />
                  </div>
                </div>
              </Col>
              <Col md={7}>
                <Form className="signInFrmInputs">
                  <Row style={{ marginTop: "10px", marginBottom: "10px" }}>
                    <Col md={4}>
                      <FormGroup>
                        <Label for="username">ID</Label>
                        <Input type="text" name="username"></Input>
                      </FormGroup>
                    </Col>
                    <Col md={4}>
                      <FormGroup>
                        <Label for="name">Name</Label>
                        <Input type="text" name="name"></Input>
                      </FormGroup>
                    </Col>
                    <Col md={4}>
                      <Label>Nickname</Label>
                      <Input type="text" name="passwordCheck"></Input>
                    </Col>
                  </Row>
                  <Row>
                    <Col md={6}>
                      <FormGroup>
                        <Label for="password">Password</Label>
                        <Input type="password" name="password"></Input>
                      </FormGroup>
                    </Col>
                    <Col md={6}>
                      <FormGroup>
                        <Label for="passwordCheck">Password Check</Label>
                        <Input type="password" name="passwordCheck"></Input>
                      </FormGroup>
                    </Col>
                  </Row>
                  <Row style={{ marginTop: "10px", marginBottom: "10px" }}>


                    <Col md={6}>
                      <Label>E-mail</Label>
                      <Input type="email" name="passwordCheck"></Input>

                    </Col>
                    <Col md={6}>
                      <Label for="phone">Phone</Label>
                      <Input type="text" name="phone"></Input>
                    </Col>
                  </Row>
                  <Row style={{ marginTop: "10px", marginBottom: "10px" }}>

                  </Row>

                  <Row>
                    <Col md={6}>
                      <FormGroup>
                        <Label for="birthday">생년월일</Label>
                        <Input type="Date" name="birthday"></Input>
                      </FormGroup>
                    </Col>
                    <Col md={6}>
                      <FormGroup>
                        <Label for="gender">성별</Label>
                        <div style={{ display: "flex", flexDirection: "Row" }}>
                          <Label check style={{ margin: "10px" }}>
                            <Input type="radio" name="gender" value="M" /> 남성
                          </Label>
                          <Label check style={{ margin: "10px" }}>
                            <Input type="radio" name="gender" value="F" /> 여성
                          </Label>
                        </div>
                      </FormGroup>
                    </Col>
                  </Row>

                  <div style={{ display: "flex", flexDirection: "row-reverse", justifyContent: "space-between" }}>
                    <Button color="danger" style={{ width: "50%", marginTop: "20px" }} onClick={() => { }} disabled={hasError !== null}>회원가입</Button>
                    {hasError !== null ? <div style={{ display: "flex", marginTop: "25px" }}><WarningSign height={30} width={30} fill={"#EE5634"} /><p style={{ margin: "5px" }}>{hasError}</p></div> : <i className="fa nc-icon nc-alert-circle-i" style={{ width: "100%", marginTop: "20px" }}>"에러가 없습니다."</i>}
                  </div>
                </Form>
              </Col>
            </Row>
          </div>
        </Modal>
        <Modal
          isOpen={smallModal}
          className="modal-sm"
          modalClassName="bd-example-modal-sm"
          toggle={() => setSmallModal(false)}
        >
          <div className="modal-header">
            <Button color="neutral" style={{ float: "right" }}>
              <i className="fa nc-icon nc-simple-remove" onClick={() => setSmallModal(false)}></i>
            </Button>
            <h4 className="modal-title" id="mySmallModalLabel">
              Small modal
            </h4>
          </div>
          <div className="modal-body">...</div>
        </Modal>


        <div className="fog-low" style={{ zIndex: -5, opacity:0.5}}>
          <img alt="..." src={require("assets/img/fog-low.png")} />
        </div>
        <div className="fog-low right" style={{ zIndex: -5 , opacity:0.5}}>
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

export default SignIn;
