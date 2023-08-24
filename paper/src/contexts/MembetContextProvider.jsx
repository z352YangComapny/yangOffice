import axios from 'axios';
import React, { createContext, useState } from 'react'

export const MemberContext = createContext();
const SpringBaseURL = "http://localhost:8080"

const MembetContextProvider = (props) => {
  const [isLogin, setIsLogin] = useState(localStorage.getItem('token') ? true : false);
  const [userProfile, setUserProfile] = useState({
    id: 0,
    username: "default",
    introduction: "default",
    username: "default",
    profileImg: "default"
  });

  const LogOut = () => {
    localStorage.removeItem('token')
    setIsLogin(false);
  }

  const signin = async (loginFrm) => {
    const axiosConfig = {
      headers: {
        "Content-Type": "application/json",
      }
    }
    return await axios.post(SpringBaseURL + '/login', loginFrm, axiosConfig)
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
      signin
    },
  }


  return (
    <MemberContext.Provider value={value}>
      {props.children}
    </MemberContext.Provider>

  )
}

export default MembetContextProvider