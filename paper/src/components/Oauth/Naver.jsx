import React from 'react'
import naver from '../../assets/images/네이버__2_100.png'
import { Button } from 'reactstrap'


const Naver = () => {
  const handleLogin = () => {}

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

export default Naver