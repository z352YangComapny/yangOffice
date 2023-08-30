import React, { useContext, useEffect, useState } from 'react'
import { Button, Card, CardHeader, CardText, Col, DropdownItem, DropdownMenu, DropdownToggle, Row, UncontrolledCarousel, UncontrolledDropdown } from 'reactstrap'
import '../../assets/css/worldphotofeed.css'


import { PhotoFeedContext } from 'contexts/PhotoFeedContextProvider';

import WorldPhotoFeedListComponent from './WorldPhotoFeedListComponents';
import WorldPhotoFeedDetail from './WorldPhotoFeedDetail';
import MyCarousel from 'components/Commons/MyCarousel';




const WorldPhotoFeedComponent = () => {
  const {
    states: {
      selectedFeed,
      data,
      maxPage,
      isDetail
    },
    actions: {
      setSelectedFeed,
      setMaxPage,
      setData,
      getTotalPage,
      getFeedPage,
      setIsDetail

    },
  } = useContext(PhotoFeedContext)
  
  const [detail, setDetail] = useState(null);

  useEffect(() => {
    setDetail(selectedFeed)
  }, [selectedFeed])

  useEffect(() => {
    console.log(detail)
  }, [detail])

  useEffect(()=>{
    console.log(isDetail)
  },[isDetail])

  return (
    <Card style={{
      border: 'solid 3px rgba(81, 203, 206, 1)',
      transform: 'none',
      transition: 'none',
      boxShadow: '4px 4px 8px rgba(81, 203, 206, 0.8)',
      height: "49.5%",
      padding: "5px"
    }}>
      <Row className='feed'>
        {!isDetail ? <WorldPhotoFeedListComponent></WorldPhotoFeedListComponent>
          :<WorldPhotoFeedDetail></WorldPhotoFeedDetail>
        }
      </Row>
    </Card>
  )
}

export default WorldPhotoFeedComponent

