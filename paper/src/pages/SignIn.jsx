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
import { useNavigate } from "react-router-dom";

// reactstrap components
import { Button, Card, Form, Input, Container, Row, Col, FormGroup, Label } from "reactstrap";

// core components

function SignIn() {
  const [isSignIn, setIsSignIn] = useState(false)
  const navigate = useNavigate();

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
                      onClick={()=>{navigate('/signup')}}
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

export default SignIn;
