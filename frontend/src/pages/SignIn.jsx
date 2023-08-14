import React, { useState } from 'react'
import AppService from '../service/MemberService'
import Network from '../games/network/NetWork'

const SignIn = () => {
    const [signInFrm, setSignInFrm] = useState({})

    const onFrmChange = (e) => {
        setSignInFrm({...signInFrm,[e.target.name]:e.target.value})
    }
    const onSubmit = () => {
        AppService.singIn(signInFrm)
        .then((resp)=>{
          const auth = resp.headers.getAuthorization()
          localStorage.setItem("nktsca", auth)
        })
        .catch((err)=> {console.log(err)})
    }

  return (
    <div className='sigin-container'>
        <div>SignIn</div>
        <fieldset>
        <div>
          <input type="text" name='username' placeholder='아이디를 입력해주세요.' value={signInFrm.username ? signInFrm.username : ""} onChange={onFrmChange} />
        </div>
        <div>
          <input type="password" name='password' placeholder='비밀번호를 입력해주세요.' value={signInFrm.password ? signInFrm.password : ""} onChange={onFrmChange}/>
        </div>
          <button onClick={onSubmit}>제출</button>
      </fieldset>
    </div>
  )
}

export default SignIn