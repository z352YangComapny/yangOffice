import React from 'react'
import { Button } from 'reactstrap'


const Google = () => {
  const handleLogin = () => {

  }
  
  return (
    <Button
    className="btn-neutral btn-just-icon mr-1"
    color="google"
    title="google"
    onClick={handleLogin}
  >
    <i className="fa fa-google-plus" />
  </Button>
  )
}

export default Google