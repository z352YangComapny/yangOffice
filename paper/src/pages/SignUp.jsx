// import React, { useContext, useEffect, useState } from 'react'
// import { Button } from 'reactstrap'
// import { MemberContext } from 'contexts/MembetContextProvider'
// import '../assets/css/style.css'

// const SignIn = () => {
//   const {
//     states: {
//       isLogin,
//       userProfile
//     },
//     actions: {
//       setUserProfile,
//       setIsLogin,
//       LogOut,
//       signin
//     },
//   } = useContext(MemberContext);

//   const [signInFrm, setSignInFrm] = useState({
//     username: "user1",
//     password: "1234",
//   })


//   useEffect(()=>{
//     if(isLogin&&localStorage.getItem('token')){
//       alert('로그인 성공 환영합니다.')
//       setUserProfile({...userProfile,username:signInFrm.username})

//     } else { 
//       setIsLogin(false);
//     }

//   },[isLogin])

//   const onFrmChange = (e) => {
//     setSignInFrm({ ...signInFrm, [e.target.name]: e.target.value })
//   }

//   const onSubmit = () => {
//     signin(signInFrm).then((resp)=>{
//       localStorage.setItem("token",resp.headers.authorization);
//       setIsLogin(true);
//     })
//     .catch((err)=>{
//       switch (err.response.data.status) {
//         case 500:
//           alert('내부서버 에러 관리자에게 문의해주세요.');
//           break;
//         case 401:
//           alert('아이디 비밀번호를 다시 확인해주세요.');
//           break;
//         default:
//           alert('알 수 없는 에러 관리자에게 문의 해주세요 ERRCODE : '+err.response.data.status);
//       };
//     })
//   }

//   const renderLoginFrm = () => {
//     if(isLogin)
//     return(
//       <Button color='primary' className='btn-round' onClick={LogOut} outline>
//       <i className='fa fa-heart' />
//       로그아웃
//     </Button>
//   )
//     return(

//       <div className='loginView'>
//       <fieldset>
//         <div>
//           <input type="text" name='username' placeholder='아이디를 입력해주세요.' value={signInFrm.username ? signInFrm.username : ""} onChange={onFrmChange} />
//         </div>
//         <div>
//           <input type="password" name='password' placeholder='비밀번호를 입력해주세요.' value={signInFrm.password ? signInFrm.password : ""} onChange={onFrmChange} />
//         </div>
//         <Button color='primary' className='btn-round' onClick={onSubmit} outline>
//           <i className='fa fa-heart' />
//           로그인
//         </Button>
//       </fieldset>
//       </div>

//     )
//   }

//   return (
//     <div className='sigin-container'>
//       <div>SignIn</div>
//       {renderLoginFrm()}
//     </div>
//   )
// }

// export default SignIn

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
import '../assets/css/style.css';

// reactstrap components
import { Button, Card, Form, Input, Container, Row, Col, FormGroup, Label } from "reactstrap";
import WarningSign from "components/Icons/icons/warning-sign";
import Check_box from "components/Icons/icons/check_box";
import Check_box_outline_blank from "components/Icons/icons/check_box_outline_blank";

// core components

function SignUp() {
    const [isSignIn, setIsSignIn] = useState(false)
    const [hasError, setHasError] = useState("아이디를 입력해주세요.")
    const [procedure, setProcedure] = useState(1)

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

    const renderProcedure = () => {
        switch (procedure) {
            case 1:
                return (
                    <ul className="proccess">
                        <li style={{ textDecoration: "underline" }}><Check_box_outline_blank width={25} height={25} />필수정보 입력</li>
                        <li><Check_box_outline_blank width={25} height={25} />선택정보 입력</li>
                        <li><Check_box_outline_blank width={25} height={25} />프로필 입력</li>
                    </ul>
                );
                break;
            case 2:
                return (
                    <ul className="proccess" onClick={() => { setProcedure(1) }}>
                        <li style={{ color: "#11ff11" }}><Check_box width={25} height={25} />필수정보 입력</li>
                        <li style={{ textDecoration: "underline" }}><Check_box_outline_blank width={25} height={25} />선택정보 입력</li>
                        <li><Check_box_outline_blank width={25} height={25} />프로필 입력</li>
                    </ul>

                );
                break;
            case 3:
                return (
                    <ul className="proccess">
                        <li><i className="fa nc-icon nc-minimal-left" onClick={() => { setProcedure(2) }} /></li>
                        <li style={{ color: "#11ff11" }}><Check_box width={25} height={25} />필수정보 입력</li>
                        <li style={{ color: "#11ff11" }}><Check_box width={25} height={25} />선택정보 입력</li>
                        <li style={{ textDecoration: "underline" }}><Check_box_outline_blank width={25} height={25} />프로필 입력</li>
                    </ul>
                );
                break;
            default:
                break;
        }

    }

    const renderFrm = () => {
        switch (procedure) {
            case 1:
                return (
                    <Form className="signInFrmInputs">
                        <Row style={{ marginTop: "10px", marginBottom: "10px" }}>
                            <Col md={6}>
                                <FormGroup>
                                    <Label for="username">ID</Label>
                                    <Input type="text" name="username"></Input>
                                </FormGroup>
                            </Col>
                            <Col md={6}>
                                <FormGroup>
                                    <Label for="name">Name</Label>
                                    <Input type="text" name="name"></Input>
                                </FormGroup>
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
                                <Label>Nickname</Label>
                                <Input type="text" name="passwordCheck"></Input>
                            </Col>
                            <Col md={6}>
                                <Label>E-mail</Label>
                                <Input type="email" name="passwordCheck"></Input>

                            </Col>
                        </Row>
                        <Row style={{ marginTop: "10px", marginBottom: "10px" }}>

                            <Label>Phone</Label>

                            <Row>
                                <Col>
                                    <Input type="text" name="phone-start"></Input>
                                </Col>
                                <Col>
                                    <Input type="text" name="phone-middle"></Input>
                                </Col>
                                <Col>
                                    <Input type="text" name="phone-end"></Input>
                                </Col>
                            </Row>

                        </Row>

                        <div style={{ display: "flex", flexDirection: "row-reverse", justifyContent: "space-between" }}>
                            <Button color="danger" style={{ width: "50%", marginTop: "20px" }} onClick={() => {setProcedure(2)}} disabled={hasError !== null}>다음으로</Button>
                            {hasError !== null ? <div style={{ display: "flex", marginTop: "25px" }}><WarningSign height={30} width={30} fill={"#EE5634"} /><p style={{ margin: "5px" }}>{hasError}</p></div> : <i className="fa nc-icon nc-alert-circle-i" style={{ width: "100%", marginTop: "20px" }}>"에러가 없습니다."</i>}
                        </div>
                    </Form>

                )
                break;
            case 2:
                return (
                    <Form className="signInFrmInputs">
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
                                <div style={{display:"flex" , flexDirection:"Row"}}>
                                    <Label check style={{margin:"10px"}}>
                                        <Input type="radio" name="gender" value="M" /> 남성
                                    </Label>
                                    <Label check style={{margin:"10px"}}>
                                        <Input type="radio" name="gender" value="F" /> 여성
                                    </Label>
                                </div>
                            </FormGroup>
                        </Col>
                        </Row>
                        <div style={{ display: "flex", flexDirection: "row-reverse", justifyContent: "space-between", marginTop:"230px"}}>
                            <Button color="danger" style={{ width: "33%", marginTop: "20px" }} onClick={() => { setProcedure(3) }}>다음으로</Button>
                            <Button color="danger" style={{ width: "33%", marginTop: "20px" }} onClick={() => { setProcedure(1) }}>회원가입</Button>
                        </div>
                    </Form>
                )
                break;
            case 3:

                break;

            default:
                break;
        }
    }

    return (
        <>
            <div
                className="page-header"
                style={{
                    backgroundImage: "url(" + require("assets/img/6.jpg") + ")",
                }}
            >
                <div className="filter" style={{ zIndex: "-3" }} />

                <Row>
                    <Col>
                        <div className="signInFrm">
                            <div className="signInLeft">
                                <div className="signInProcedure"></div>
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
                            <div className="signInFrmDetail">
                                <div style={{ height: "9vh" }}>
                                    {renderProcedure()}
                                </div>
                                {renderFrm()}
                            </div>
                        </div>
                    </Col>
                </Row>



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

export default SignUp;

