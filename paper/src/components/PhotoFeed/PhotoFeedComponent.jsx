import React, { useContext, useEffect, useState } from 'react'
import { Button, Card, CardText, Col, DropdownItem, DropdownMenu, DropdownToggle, Input, Modal, ModalBody, ModalFooter, ModalHeader, Row, UncontrolledCarousel, UncontrolledDropdown } from 'reactstrap'
import '../../assets/css/photofeed.css'
import ErrImg from '../../assets/images/ErrImg.jpg';

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
  const [feedContent , setFeedContent] = useState('');

  const [addModal, setAddModal] = useState(false);
  const [updateModal , setUpdateModal] = useState(false);
  const [src, SetSrc] = useState('');

  const [upLoadFile1, setUpLoadFile1] = useState(null);
  const [preViewFile1, setPreViewFile1] = useState(null);

  const [upLoadFile2, setUpLoadFile2] = useState(null);
  const [preViewFile2, setPreViewFile2] = useState(null);

  const [upLoadFile3, setUpLoadFile3] = useState(null);
  const [preViewFile3, setPreViewFile3] = useState(null);

  const toggle = () => {
    const formData = new FormData();
    formData.append("content",JSON.stringify(feedContent));
  
    if(upLoadFile1) formData.append("upfile[]", upLoadFile1)
    if(upLoadFile2) formData.append("upfile[]", upLoadFile2)
    if(upLoadFile3) formData.append("upfile[]", upLoadFile3)


    formData.forEach((value, key) => {
      console.log(key)
      console.dir(value);
    });
    setAddModal(prev => !prev);
    setFeedContent('');
    setUpLoadFile1(null);
    setUpLoadFile2(null);
    setUpLoadFile3(null);
    setPreViewFile1(null);
    setPreViewFile2(null);
    setPreViewFile3(null);
  }

  const toggleUpdate = () => setUpdateModal(prev => !prev);

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
        alert('Please upload an image file under 3MB.');
      }
    }
  };

  const handleFrmChange = (e) => {
    console.log(e.target.value)
    setFeedContent(e.target.value);

  }

  // useEffect(()=>{},[selectedFeed])

  const renderFileSet = () => {
    return (
      <div style={{ width: "100%", height: "85%"}}>
        <h4>Add photos</h4>
        <div style={{ width: "100%", height: "85%", display: "flex", flexDirection: "column", justifyContent: "space-around", alignItems: "center" }}>
        { !preViewFile1 ? <div class="filebox">
          <label className='filebox-in thumb-nail' title='썸네일' for="file1"  >+</label>
          <input type="file" id="file1" onChange={handleFileChange1} />
        </div> : 
        <div className='filebox' style={{backgroundColor:"red"}}>
          <img src="preViewFile1" alt="" srcset="" className='fliebox-in' />
        </div> }
        { !preViewFile2 ? <div class="filebox">
          <label className='filebox-in' title='사진1' for="file2">+</label>
          <input type="file" id="file2" onChange={handleFileChange2} />
        </div>
        : "" }
        { !preViewFile3 ? <div class="filebox">
          <label className='filebox-in' title='사진2' for="file3">+</label>
          <input type="file" id="file3" onChange={handleFileChange3} />
        </div> : 
        ""
        }
        </div>
      </div>
    )
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
            <Col md={6} style={{ height: "450px", display: 'flex', justifyContent: 'center', alignItems: 'center' , paddingTop:"0px"}}>
              <img src={src} onError={(e) => {
                e.target.src = ErrImg; // Set the error image source on error
              }} className='add-img-tmp' />
            </Col>
          </Row>
        </ModalBody>
        <ModalFooter style={{ height: "70px" }}>
          <Button color="primary" onClick={toggle}>
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
                <Input type='textarea' className='feed-content-input' onChange={()=>{}}/>
              </div>
            </Col>
            <Col md={2} style={{ height: "450px" }}>
              {renderFileSet()}
            </Col>
            <Col md={6} style={{ height: "450px", display: 'flex', justifyContent: 'center', alignItems: 'center' , paddingTop:"0px"}}>
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
