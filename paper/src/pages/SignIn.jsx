import React, { useContext, useEffect, useState } from 'react'
import AppService from '../service/MemberService'
import Network from '../games/network/NetWork'
import { Button } from 'reactstrap'
import MembetContextProvider from 'contexts/MembetContextProvider'
import { MemberContext } from 'contexts/MembetContextProvider'

const SignIn = () => {
  const {
    states: {
      isLogin,
      accessToken
    },
    actions: {
      setIsLogin,
      setAccessToken,
      signin,
      LogOut
    },
  } = useContext(MemberContext);

  const [signInFrm, setSignInFrm] = useState({
    username: "",
    password: "",
  })


  useEffect(()=>{
    if(isLogin)
    alert('로그인 성공 환영합니다.')
  },[isLogin])

  const onFrmChange = (e) => {
    setSignInFrm({ ...signInFrm, [e.target.name]: e.target.value })
  }

  const onSubmit = () => {
    signin(signInFrm).then((resp)=>{
      setAccessToken(resp.headers.authorization);
      setIsLogin(true);
    })
    .catch((err)=>{
      switch (err.response.data.status) {
        case 500:
          alert('내부서버 에러 관리자에게 문의해주세요.');
          break;
        case 401:
          alert('아이디 비밀번호를 다시 확인해주세요.');
          break;
        default:
          alert('알 수 없는 에러 관리자에게 문의 해주세요 ERRCODE : '+err.response.data.status);
      };
    })
  }

  const renderLoginFrm = () => {
    if(isLogin)
    return(
      <Button color='primary' className='btn-round' onClick={LogOut} outline>
      <i className='fa fa-heart' />
      로그아웃
    </Button>
  )
    return(
      <fieldset>
        <div>
          <input type="text" name='username' placeholder='아이디를 입력해주세요.' value={signInFrm.username ? signInFrm.username : ""} onChange={onFrmChange} />
        </div>
        <div>
          <input type="password" name='password' placeholder='비밀번호를 입력해주세요.' value={signInFrm.password ? signInFrm.password : ""} onChange={onFrmChange} />
        </div>
        <Button color='primary' className='btn-round' onClick={onSubmit} outline>
          <i className='fa fa-heart' />
          로그인
        </Button>
      </fieldset>
    )
  }

  return (
    <div className='sigin-container'>
      <div>SignIn</div>
      {renderLoginFrm()}
    </div>
  )
}

export default SignIn