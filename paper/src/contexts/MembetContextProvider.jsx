import axios from 'axios';
import React, { createContext, useState } from 'react'

export const MemberContext = createContext();
const SpringBaseURL = "http://localhost:8080"

const MembetContextProvider = (props) => {
  const [isLogin, setIsLogin] = useState(sessionStorage.getItem('token') ? true : false);
  const [userProfile, setUserProfile] = useState();

  const LogOut = () => {
    sessionStorage.removeItem('token')
    setIsLogin(false);
  }
  const getUserProfile = async (username) => {
    const axiosConfig = {
      headers: {
        "Authorization": sessionStorage.getItem('token'),
      }
    };
    return await axios.get(SpringBaseURL + '/member/memberDetail',axiosConfig);
  }

  const signin = async (loginFrm) => {
    const axiosConfig = {
      headers: {
        "Content-Type": "application/json",
      }
    };
    return await axios.post(SpringBaseURL + '/login', loginFrm, axiosConfig);
  }

  const checkIdDuplicate = async(username) => {
    return await axios.get(SpringBaseURL + '/checkIdDuplicate?username=' + username);
  }

  const checkNicknameDuplicate = async(nickname) => {
    return await axios.get(SpringBaseURL + '/checkNicknameDuplicate?nickname=' + nickname);
  }

  const checkPhoneDuplicate = async(phone) => {
    return await axios.get(SpringBaseURL + '/checkPhoneDuplicate?phone=' + phone);
  }

  const value = {
    states: {
      isLogin,
      userProfile
    },
    actions: {
      setUserProfile,
      setIsLogin,
      LogOut,
      signin,
      getUserProfile,
      checkIdDuplicate,
      checkNicknameDuplicate,
      checkPhoneDuplicate
    },
  }


  return (
    <MemberContext.Provider value={value}>
      {props.children}
    </MemberContext.Provider>

  )
}

export default MembetContextProvider