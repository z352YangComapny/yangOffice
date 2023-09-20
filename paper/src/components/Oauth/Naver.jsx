import React, { useContext, useEffect } from 'react'
import naver from '../../assets/images/네이버__2_100.png'
import { Button } from 'reactstrap'
import { useNavigate } from 'react-router-dom'
import { MemberContext } from 'contexts/MembetContextProvider'
import axios from 'axios'
import jwtDecode from 'jwt-decode'


const Naver = () => {
  const {
    states: { isLogin, userProfile },
    actions: { setUserProfile, setIsLogin, LogOut, signin , getUserProfile},
  } = useContext(MemberContext)
  const navigate = useNavigate();

  const SpringBaseUrl = 'http://localhost:8080';


  const handleLogin = () => {

    const client_id = "qMXozarpkt37ZQhal0iY";
    const redirect_uri = "http://localhost:3000/signin";
    const state = "ssoystory";
    const NaverURL = `https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=${client_id}&redirect_uri=${redirect_uri}&state=${state}`;
    
    sessionStorage.setItem("provider","naver");

    window.location.href = NaverURL;

  }

  useEffect(()=>{
    const searchParams = new URLSearchParams(window.location.search);
    if(sessionStorage.getItem('provider')=='naver' && searchParams) {
      const [code, state] = [...searchParams];
      sessionStorage.removeItem('provider');
      axios.post(SpringBaseUrl + `/oauth`,{provider:'naver', naverCode:code[1], naverState : state[1]})
      .then((resp)=> {
        console.log(resp);
        if(resp.data[0]){
          console.log(jwtDecode(resp.data[0]))  
          sessionStorage.setItem('token',"Bearer "+resp.data[0]);
          sessionStorage.setItem('nickname',jwtDecode(resp.data[0]).nickname);
          sessionStorage.setItem('username',jwtDecode(resp.data[0]).username);
          setIsLogin(true);
          navigate('/user/'+jwtDecode(resp.data[0]).username);
        }else {
          console.log(jwtDecode(resp.data[1]))  ;
          sessionStorage.setItem('token',"Bearer "+resp.data[1]);
          sessionStorage.setItem('nickname',jwtDecode(resp.data[1]).nickname);
          sessionStorage.setItem('username',jwtDecode(resp.data[1]).username);
          setIsLogin(true);
          navigate('/feed/'+jwtDecode(resp.data[1]).username);
        }
      })
      .catch((err)=>{
        console.log(err);
      })
    }
  },[])


  return (
    <Button
      className="btn-neutral btn-just-icon"
      color="twitter"
      href="#pablo"
      onClick={handleLogin}
    >
      <img src={naver} />
    </Button>
  )
}

export default Naver;