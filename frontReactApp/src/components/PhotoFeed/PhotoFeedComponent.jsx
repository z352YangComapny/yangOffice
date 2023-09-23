import React, { useContext, useEffect, useState } from 'react'
import { Button, Card, CardText, Col, DropdownItem, DropdownMenu, DropdownToggle, Input, Modal, ModalBody, ModalFooter, ModalHeader, Row, UncontrolledCarousel, UncontrolledDropdown } from 'reactstrap'
import '../../assets/css/photofeed.css'
import ErrImg from '../../assets/images/ErrImg.jpg';

import { PhotoFeedContext } from 'contexts/PhotoFeedContextProvider';
import MyCarousel from 'components/Commons/MyCarousel';
import PhotoFeedListComponent from './PhotoFeedListComponent';
import { MemberContext } from 'contexts/MembetContextProvider';
import { NotificationContext } from 'contexts/NotificationContextProvider';
import { useParams } from 'react-router-dom';




const PhotoFeedComponent = () => {
  const {
    states: {
      message,
    },
    actions: {
      setMessage
    },
  } = useContext(NotificationContext);
  const {
    states: {
      isLogin,
      userProfile
    },
    actions: {
      setUserProfile,
      setIsLogin,
      LogOut,
      signin,
      getUserProfile
    },
  } = useContext(MemberContext);
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
      getFeedPage,
      insertFeed
    },
  } = useContext(PhotoFeedContext)
  const { hostname } = useParams();
  const [feedContent, setFeedContent] = useState('');

  const [addModal, setAddModal] = useState(false);
  const [updateModal, setUpdateModal] = useState(false);
  const [src, setSrc] = useState(0);

  const [upLoadFile1, setUpLoadFile1] = useState(null);
  const [preViewFile1, setPreViewFile1] = useState(null);

  const [upLoadFile2, setUpLoadFile2] = useState(null);
  const [preViewFile2, setPreViewFile2] = useState(null);

  const [upLoadFile3, setUpLoadFile3] = useState(null);
  const [preViewFile3, setPreViewFile3] = useState(null);

  const toggle = () => {   
    setAddModal(prev => !prev);
    setFeedContent('');
    setUpLoadFile1(null);
    setUpLoadFile2(null);
    setUpLoadFile3(null);
    setPreViewFile1(null);
    setPreViewFile2(null);
    setPreViewFile3(null);
    setSrc()
  }

  const toggleUpdate = () => setUpdateModal(prev => !prev);

  const addFeed = () => {
    const formData = new FormData();
    formData.append("content", JSON.stringify(feedContent));

    if (upLoadFile1) formData.append("upFile", upLoadFile1)
    if (upLoadFile2) formData.append("upFile", upLoadFile2)
    if (upLoadFile3) formData.append("upFile", upLoadFile3)

    insertFeed(formData)
    .then((resp)=>{
      setMessage({ color: "success", value: `피드 등록 성공👌`})
      getFeedPage(1,hostname).then((resp)=> setData(resp.data))
    })
    .catch((err)=>{
      setMessage({ color: "waring", value: `피드 등록 실패😔`})
    })
    toggle();
  }

  const handleFileChange1 = (event) => {
    console.log(event)
    const file = event.target.files[0];
    if (file) {
      if (file.size <= 3 * 1024 * 1024 && file.type.startsWith('image/')) {
        console.log(file.size)
        console.log(file.type.startsWith)
        setUpLoadFile1(file);
        const reader = new FileReader();
        reader.onload = (e) => {
          setPreViewFile1(e.target.result);
        };
        reader.readAsDataURL(file);
      } else {
        alert('Please upload an image file under 3MB.');
      }
    }
  };
  const handleFileChange2 = (event) => {
    console.log(event)
    const file = event.target.files[0];
    if (file) {
      if (file.size <= 3 * 1024 * 1024 && file.type.startsWith('image/')) {
        console.log(file.size)
        console.log(file.type.startsWith)
        setUpLoadFile2(file);
        const reader = new FileReader();
        reader.onload = (e) => {
          setPreViewFile2(e.target.result);
        };
        reader.readAsDataURL(file);
      } else {
        alert('Please upload an image file under 3MB.');
      }
    }
  };
  const handleFileChange3 = (event) => {
    console.log(event)
    const file = event.target.files[0];
    if (file) {
      if (file.size <= 3 * 1024 * 1024 && file.type.startsWith('image/')) {
        console.log(file.size)
        console.log(file.type.startsWith)
        setUpLoadFile3(file);
        const reader = new FileReader();
        reader.onload = (e) => {
          setPreViewFile3(e.target.result);
        };
        reader.readAsDataURL(file);
      } else {
        alert('3MB 이하의 이미지만 가능합니다.');
      }
    }
  };

  const handleFrmChange = (e) => {
    console.log(e.target.value)
    setFeedContent(e.target.value);
  }

  const removeData = (e) => {
    console.log(e.target.name)
    switch (e.target.name) {
      case "1":
        setPreViewFile1(null);
        setUpLoadFile1(null);
        break;

      case "2":
        setPreViewFile2(null);
        setUpLoadFile2(null);

        break;

      case "3":
        setPreViewFile3(null);
        setUpLoadFile3(null);

        break;

      default:
        break;
    }
  }

  useEffect(()=>{console.log(hostname)},[hostname])

  const renderFileSet = () => {
    return (
      <div style={{ width: "100%", height: "85%" }}>
        <h4>Add photos</h4>
        <div style={{ width: "100%", height: "85%", display: "flex", flexDirection: "column", justifyContent: "space-around", alignItems: "center" }}>
          {!preViewFile1 ?
            <div class="filebox">
              <label className='filebox-in thumb-nail' title='썸네일' for="file1"  >+</label>
              <input type="file" id="file1" onChange={handleFileChange1} />
            </div> :
            <div className='filebox' >
              <img src={preViewFile1} alt="" srcset="" name="1" className='preview-img' onClick={removeData} onMouseEnter={() => { setSrc(1) }} />
            </div>}
          {!preViewFile2 ? <div class="filebox">
            <label className='filebox-in' title='사진1' for="file2">+</label>
            <input type="file" id="file2" onChange={handleFileChange2} />
          </div>
            : <div className='filebox' >
              <img src={preViewFile2} alt="" srcset="" name="2" className='preview-img' onClick={removeData} onMouseEnter={(e) => { setSrc(2) }} />
            </div>}
          {!preViewFile3 ? <div class="filebox">
            <label className='filebox-in' title='사진2' for="file3">+</label>
            <input type="file" id="file3" onChange={handleFileChange3} />
          </div> :
            <div className='filebox' >
              <img src={preViewFile3} alt="" srcset="" name="3" className='preview-img' onClick={removeData} onMouseEnter={() => { setSrc(3) }} />
            </div>
          }
        </div>
      </div>
    )
  }

  const rednerLikeCount = () => {
    if(selectedFeed.likeCount>=1000){
      return Math.floor(selectedFeed.likeCount/1000)+"k"
    }else{
      return selectedFeed.likeCount;
    }
  }

  return (
    <Card style={{
      border: 'solid 3px rgba(81, 203, 206, 1)',
      transform: 'none',
      transition: 'none',
      marginTop: "20px",
      boxShadow: '4px 4px 8px rgba(81, 203, 206, 0.8)',
      height: "64%"
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
                  <DropdownItem onClick={toggle}>
                    Add
                  </DropdownItem>
                  <DropdownItem onClick={toggleUpdate}>
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
                <i className="fa nc-icon nc-favourite-28" style={{ width: "50px" }}>&nbsp;{rednerLikeCount()}</i>
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
      <Modal
        isOpen={addModal}
        toggle={toggle}
        size='xl'
        backdrop='static'
        fade='true'
        centered='true'
        scrollable='true'
      >
        <ModalHeader>
          <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center", fontSize: "30px", marginLeft: "50px" }}>
            <div>Add PhotoFeed</div>
            <Button color="neutral" style={{ float: "right" }}>
              <i className="fa nc-icon nc-simple-remove" onClick={toggle}></i>
            </Button>
          </div>
        </ModalHeader>
        <ModalBody>
          <Row>
            <Col md={4} style={{ height: "450px", padding: "17px" }}>
              <h4 style={{ margin: "10px" }}>Content</h4>
              <div>
                <Input type='textarea' className='feed-content-input' name='addcontent' value={feedContent} onChange={handleFrmChange} />
              </div>
            </Col>
            <Col md={2} style={{ height: "450px" }}>
              {renderFileSet()}
            </Col>
            <Col md={6} style={{ height: "450px", display: 'flex', justifyContent: 'center', alignItems: 'center', paddingTop: "0px" }}>

              <div>
                <img src={src == 1 ? preViewFile1 : (src == 2 ? preViewFile2 : (src ==3 ? preViewFile3 : ErrImg))} onError={(e) => {
                  e.target.src = ErrImg; // Set the error image source on error
                }} className='add-img-tmp' />
              </div>

            </Col>
          </Row>
        </ModalBody>
        <ModalFooter style={{ height: "70px" }}>
          <Button color="primary" onClick={addFeed}>
            Confirm
          </Button>{' '}
          <Button color="secondary" onClick={toggle}>
            Cancel
          </Button>
        </ModalFooter>
      </Modal>
      <Modal
        isOpen={updateModal}
        toggle={toggle}
        size='xl'
        backdrop='static'
        fade='true'
        centered='true'
        scrollable='true'
      >
        <ModalHeader>
          <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center", fontSize: "30px", marginLeft: "50px" }}>
            <div>Update PhotoFeed</div>
            <Button color="neutral" style={{ float: "right" }}>
              <i className="fa nc-icon nc-simple-remove" onClick={toggleUpdate}></i>
            </Button>
          </div>
        </ModalHeader>
        <ModalBody>
          <Row>
            <Col md={4} style={{ height: "450px", padding: "17px" }}>
              <h4 style={{ margin: "10px" }}>Content</h4>
              <div>
                <Input type='textarea' className='feed-content-input' onChange={() => { }} />
              </div>
            </Col>
            <Col md={2} style={{ height: "450px" }}>
              {renderFileSet()}
            </Col>
            <Col md={6} style={{ height: "450px", display: 'flex', justifyContent: 'center', alignItems: 'center', paddingTop: "0px" }}>
              <img src={src} onError={(e) => {
                e.target.src = ErrImg; // Set the error image source on error
              }} className='add-img-tmp' />
            </Col>
          </Row>
        </ModalBody>
        <ModalFooter style={{ height: "70px" }}>
          <Button color="primary" onClick={toggleUpdate}>
            Confirm
          </Button>{' '}
          <Button color="secondary" onClick={toggleUpdate}>
            Cancel
          </Button>
        </ModalFooter>
      </Modal>
    </Card>
  )
}

export default PhotoFeedComponent
