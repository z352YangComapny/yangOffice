import React, { useContext, useEffect } from 'react'
import { Button, Card, CardText, Col, DropdownItem, DropdownMenu, DropdownToggle, Row, UncontrolledCarousel, UncontrolledDropdown } from 'reactstrap'
import '../../assets/css/photofeed.css'


import { PhotoFeedContext } from 'contexts/PhotoFeedContextProvider';
import MyCarousel from 'components/Commons/MyCarousel';
import PhotoFeedListComponent from './PhotoFeedListComponent';



const PhotoFeedComponent = () => {
  const {
    states: {
      selectedFeed,
      data,
      maxPage
    },
    actions: {
      setSelectedFeed,
      setMaxPage,
      setData,
      getTotalPage,
      getFeedPage
    },
  } = useContext(PhotoFeedContext)

  // useEffect(()=>{},[selectedFeed])

  return (
    <Card style={{
      border: 'solid 3px rgba(81, 203, 206, 1)',
      transform: 'none',
      transition: 'none',
      marginTop: "20px",
      boxShadow: '4px 4px 8px rgba(81, 203, 206, 0.8)',
      height: "65%"
    }}>
      <Row className='feed'>
        <Col md={7} className='feed-detail'>
          <div style={{ marginTop: "2vh" }}></div>
          <MyCarousel />
          <div style={{ display: "flex", justifyContent: "space-between", marginTop: "5px" }}>
            <div>

              <UncontrolledDropdown
                className="me-2"
                direction="up"
              >
                <DropdownToggle
                  caret
                  color="primary"
                >
                  <i className="fa nc-icon nc-settings-gear-65"></i>
                </DropdownToggle>
                <DropdownMenu>
                  <DropdownItem header>
                    PhotoFeed Option
                  </DropdownItem>
                  <DropdownItem>
                    Add
                  </DropdownItem>
                  <DropdownItem>
                    Update
                  </DropdownItem>
                  <DropdownItem>
                    Delete
                  </DropdownItem>
                </DropdownMenu>
              </UncontrolledDropdown>
            </div>
            <div>
              <Button color='warning' title='좋아요' style={{ marginLeft: "5px", }} >
                <i className="fa nc-icon nc-favourite-28" style={{ width: "50px" }}>&nbsp;{"1k"}</i>
              </Button>
              <Button color='danger' title='신고하기' style={{ marginLeft: "5px" }} >
                <i className="fa nc-icon nc-bell-55"></i>
              </Button>
            </div>
          </div>
          <div className='feed-contest-text'>
            {selectedFeed.content}
          </div>
        </Col>
        <Col md={5} className='feed-total'>
          <PhotoFeedListComponent></PhotoFeedListComponent>
        </Col>
      </Row>
    </Card>
  )
}

export default PhotoFeedComponent
