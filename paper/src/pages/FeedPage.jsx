import DemoFooter from 'components/Footers/DemoFooter';
import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom';
import '../assets/css/userpage.css'
import { Button, Card, CardBody, CardFooter, CardHeader, CardSubtitle, CardText, CardTitle, Col, DropdownItem, DropdownMenu, DropdownToggle, Row, UncontrolledDropdown } from 'reactstrap';
import StoryComponent from 'components/Story/StoryComponent';
import ProfileComponent from 'components/Member/ProfileComponent';
import PhotoFeedComponent from 'components/PhotoFeed/PhotoFeedComponent';
import GuestBookComponet from 'components/GuestBook/GuestBookComponet';

const FeedPage = (props) => {
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
              <StoryComponent/>
              <PhotoFeedComponent></PhotoFeedComponent>
              <GuestBookComponet></GuestBookComponet>
            </Col>
          </Row>
        </div>
      </div>
      <DemoFooter />
    </>

  )
}

export default FeedPage