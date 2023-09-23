import DemoFooter from 'components/Footers/DemoFooter';
import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom';
import '../assets/css/userpage.css'
import { Button, Card, CardBody, CardFooter, CardHeader, CardSubtitle, CardText, CardTitle, Col, DropdownItem, DropdownMenu, DropdownToggle, Row, UncontrolledDropdown } from 'reactstrap';
import WorldStoryComponent from 'components/Story/WorldStoryComponent';

import WorldPhotoFeedComponent from 'components/PhotoFeed/WorldPhotoFeedComponent';
import WorldGuestBookComponet from 'components/GuestBook/WorldGuestBookComponent';
import WorldProfileComponent from 'components/Member/WorldProfileComponent';
import GameComponent from 'components/game/GameComponent';
import GuestBookComponet from 'components/GuestBook/GuestBookComponet';

const WroldPage = (props) => {
  const [leftState, setLeftState] = useState(0);
  const navigate = useNavigate();
  const params = useParams();
  const { id } = params;


  return (
    <>
      <div className='user-body'>
        <div className='user-content'>
          <div className='user-blank'></div>
          <Row style={{ height: "100%", width: "100%" }}>
            <Col className='user-left' md={4}>
              <WorldProfileComponent/>
              <WorldPhotoFeedComponent/>
            </Col>
            <Col className='user-right' md={8}>
            <WorldStoryComponent/>
            <GameComponent/>
            <GuestBookComponet/>
            </Col>
          </Row>
        </div>
      </div>
      <DemoFooter />
    </>

  )
}

export default WroldPage