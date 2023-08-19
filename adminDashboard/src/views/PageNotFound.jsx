import React from 'react'
import { useNavigate } from 'react-router-dom'
import { Button } from 'reactstrap'

const PageNotFound = () => {
  const navigate = useNavigate();
  const handleReturnToIndex = () => {
    navigate('/login')
  }

  return (
    <div style={{margin:"280px", justifyContent:"center"}}>
      <div style={{fontSize:"100px"}}>PageNotFound</div>
      <Button style={{margin:"0 auto"}} onClick={handleReturnToIndex}>Return To IndexPage</Button>
    </div>
  )
}

export default PageNotFound