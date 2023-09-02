import React from 'react'
import kakao from '../../assets/images/kakao-talk.png'
import { Button } from 'reactstrap'


const Kakao = () => {
  const handleLogin = () => {
    
  }
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