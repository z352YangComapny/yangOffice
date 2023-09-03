import React, { useContext, useEffect } from 'react'
import kakao from '../../assets/images/kakao-talk.png'
import { Button } from 'reactstrap'
import jwtDecode from 'jwt-decode'
import axios from 'axios'
import { MemberContext } from 'contexts/MembetContextProvider'
import { useNavigate } from 'react-router-dom'


const Kakao = () => {
  const {
    states: { isLogin, userProfile },
    actions: { setUserProfile, setIsLogin, LogOut, signin , getUserProfile},
  } = useContext(MemberContext)
  const navigate = useNavigate();

  const SpringBaseUrl = 'http://localhost:8080'

  const handleLogin = () => {
    // 3000포트에서 해야 할 작업
    // Credential code 받아서 > 8080서버로 넘겨주기.

    // 0. RedirectURL = http://localhost:3000으로 하기.

    // 1.인가 코드 요청 받기
    // KAKAO 의 인가 URL https://kauth.kakao.com/oauth/authorize
    // 쿼리 파라미터 필수 : {client_id , redirect_uri , respones_type , response_type} 
    // https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api 명세서 참조하기!
    const client_id = "a7b86ff96d50db1785b75938758aeb44";
    const redirect_id = "http://localhost:3000/signin";
    const response_type = "code";
    const KakaoURL = `https://kauth.kakao.com/oauth/authorize?response_type=${response_type}&client_id=${client_id}&redirect_uri=${redirect_id}`

    sessionStorage.setItem("provider","kakao");

    window.location.href = KakaoURL; // 인가 코드 요청!
    //즉 로그인 버튼을 누르면 요 바로 위 22번째줄이 날리고 , url 이 바껴있을껴임
    //이를 카카오 컴포넌트가 마운트 될때 가져올 예정 >> useEffect 사용
  }

  useEffect(()=>{
    const searchParams = new URLSearchParams(window.location.search);
    let credentailcode;
    searchParams.forEach((item)=> {
      console.log("KAKAO OAUTH CODE"+item) // 콘솔창에 파람 뽑아 찍어봄.
      credentailcode = item;
    })
    if(sessionStorage.getItem('provider')=='kakao') {
      sessionStorage.removeItem('provider')
      axios.post(SpringBaseUrl + `/oauth`,{provider:'kakao', kakaoCode:credentailcode})
      .then((resp)=> {
        console.log(resp);
        if(resp.data[0]){
          console.log(jwtDecode(resp.data[0]))  
          sessionStorage.setItem('token',"Bearer "+resp.data[0])
          sessionStorage.setItem('nickname',jwtDecode(resp.data[0]).nickname)
          sessionStorage.setItem('username',jwtDecode(resp.data[0]).username)
          setIsLogin(true)
          navigate('/user/'+jwtDecode(resp.data[0]).username)
        }else {
          console.log(jwtDecode(resp.data[1]))  
          sessionStorage.setItem('token',"Bearer "+resp.data[1])
          sessionStorage.setItem('nickname',jwtDecode(resp.data[1]).nickname)
          sessionStorage.setItem('username',jwtDecode(resp.data[1]).username)
          setIsLogin(true)
          navigate('/feed/'+jwtDecode(resp.data[1]).username)
        }
      })
      .catch((err)=>{
        console.log(err);
      })
    }
  },[])

  return (
    <Button
      title="kakao"
      className="btn-neutral btn-just-icon mr-1"
      onClick={handleLogin}
    >
      <img src={kakao} style={{ width: "20px", height: "20px" }}></img>
    </Button>
  )
}

export default Kakao