import React from 'react'
import sportify from '../../assets/images/spotify.png'
import { Button } from 'reactstrap'

const Sportify = () => {
  
  const handleLogin = () => {

  }

  return (
    <Button
      className="btn-neutral btn-just-icon"
      color="twitter"
      href="#pablo"
      onClick={handleLogin}
    >
      <img src={sportify}></img>
    </Button>
  )
}

export default Sportify