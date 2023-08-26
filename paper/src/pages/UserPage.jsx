import DemoFooter from 'components/Footers/DemoFooter';
import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom';
import '../assets/css/userpage.css'
import { Button, Card, CardBody, CardFooter, CardHeader, CardSubtitle, CardText, CardTitle, Col, DropdownItem, DropdownMenu, DropdownToggle, Row, UncontrolledDropdown } from 'reactstrap';
import SmilingFaceSunglasses from 'components/Icons/icons/smiling-face-sunglasses';
import StoryComponent from 'components/Story/StoryComponent';
import ProfileComponent from 'components/Member/ProfileComponent';
import PhotoFeedComponent from 'components/PhotoFeed/PhotoFeedComponent';

const UserPage = (props) => {
  const [leftState, setLeftState] = useState(0);
  

  const navigate = useNavigate();
  const params = useParams();
  const { id } = params;


  return (
    <>
      <div className='user-body'>
        <div></div>
        <div className='user-content'>
          <div className='user-blank'></div>
          <Row style={{ height: "100%", width: "100%" }}>
            <Col className='user-left' md={4}>
              <ProfileComponent/>
            </Col>
            <Col className='user-right' md={8}>
              <StoryComponent memberid={id}/>
              <PhotoFeedComponent></PhotoFeedComponent>
              
              <Card style={{
                border: 'solid 3px rgba(81, 203, 206, 1)',
                transform: 'none',
                transition: 'none',
                marginTop: "20px",
                boxShadow: '4px 4px 8px rgba(81, 203, 206, 0.8)',
                height: "14.5%"
              }}>GuestBook</Card>
            </Col>
          </Row>
        </div>
      </div>
      <DemoFooter />
    </>

  )
}

export default UserPage