import DemoFooter from 'components/Footers/DemoFooter';
import React from 'react'
import { useNavigate, useParams } from 'react-router-dom';

const WorldPage = (props) => {
    const navigate = useNavigate();
  // 경로변수 
  const params = useParams();
  console.log(params);
  const {id} = params;

  return (
    <>
    <div>WorldPage</div>
    <DemoFooter/>
    </>
    
  )
}

export default WorldPage