import MyCarousel from 'components/Commons/MyCarousel'
import React, { useContext, useEffect, useState } from 'react'
import '../../assets/css/worldphotofeeddetail.css'
import { PhotoFeedContext } from 'contexts/PhotoFeedContextProvider'

const WorldPhotoFeedDetail = () => {
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
  } = useContext(PhotoFeedContext);
  


  const [currentIndex, setCurrentIndex] = useState(0);
  const [prevIndex, setPrevIndex] = useState(0);
  const [isContents, setIsContents] = useState(true);

  const images = [
    'http://localhost:8080/resources/upload/attachment/1.jpg',
    'http://localhost:8080/resources/upload/attachment/2.jpg',
    'http://localhost:8080/resources/upload/attachment/3.jpg'
  ];


  const handleClose = () => {
    setIsDetail(false);
  };
  const handleRight = () => {
    setIsContents(prev => !prev);
  };
  const handleLeftCarousel = () => {
    setCurrentIndex((prevIndex) => (prevIndex === 0 ? images.length - 1 : prevIndex - 1));
  };

  const handleRightCarousel = () => {
    setCurrentIndex((prevIndex) => (prevIndex === images.length - 1 ? 0 : prevIndex + 1));
  };

  useEffect(() => {
    const interval = setInterval(() => {
      handleRightCarousel();
    }, 3000);

    return () => {
      clearInterval(interval);
    };
  }, [currentIndex]);

  const renderFeedContents = () => {
    if (isContents) {
      return (
        <div style={{ width: "92%", height: "100%" }}>
          <div style={{ fontSize: "20px" }}>Contents</div>
          <div>
            {selectedFeed.content}
          </div>
        </div>
      )
    } else {
      return (
        <div style={{ width: "96%", height: "100%" }}>
          <div style={{ fontSize: "20px" }}>Comments</div>
          <div className='comments-mini' style={{ width: "96%", height: "68px", overflowY: "scroll" }}>
            <p style={{ textAlign: "center" }}>1234</p>
            <p style={{ textAlign: "center" }}>1234</p>
            <p style={{ textAlign: "center" }}>1234</p>
            <p style={{ textAlign: "center" }}>1234</p>
            <p style={{ textAlign: "center" }}>1234</p>
            <p style={{ textAlign: "center" }}>1234</p>
            <p style={{ textAlign: "center" }}>1234</p>
          </div>
          <div style={{ display: "flex" }}>
            <p className='addbtn'>등록</p><input type="text" style={{ width: "80%" }} />
          </div>
        </div>
      )

    }
  }



  return (
    <div style={{ width: "100%", height: "100%", }}>

      <div style={{ display: "flex", width: "100%", height: "35%" }}>
        {renderFeedContents()}
        <div className='nav-cursor' style={{ width: "8%", height: "100%", justifyContent: "center", alignItems: 'center', display: "flex", flexDirection: "column" }}>
          <div style={{ height: "50%" }} onClick={handleClose}>
            <i className='fa nc-icon nc-simple-remove' title='창 닫기'></i>
          </div>

          <div style={{ height: "50%" }} onClick={handleRight}>
            <i className='fa nc-icon nc-refresh-69' title='창 전환'></i>
          </div>
        </div>
      </div>

      <div className='my-carousel'>
        <div className='nav-cursor' style={{ width: "8%", justifyContent: "center", alignItems: 'center', display: "flex" }} onClick={handleLeftCarousel}>
          <i className='fa nc-icon nc-minimal-left'></i>
        </div>
        <div style={{ width: '100%', overflow: 'hidden' }}>
          <div style={{ display: 'flex', transform: `translateX(-${currentIndex * 100}%)`, transition: 'transform 1s ease-in-out' }}>
            {images.map((image, index) => (
              <img className='feed-img-detail' key={index} src={image} alt={`Image ${index}`} style={{ width: '100%' }} />
            ))}

          </div>
          <div style={{ textAlign: "center" }}>{currentIndex + 1}</div>
        </div>
        <div className='nav-cursor' style={{ width: "8%", justifyContent: "center", alignItems: 'center', display: "flex" }} onClick={handleRightCarousel}>
          <i className='fa nc-icon nc-minimal-right'></i>
        </div>
      </div>
    </div>
  )
}

export default WorldPhotoFeedDetail
