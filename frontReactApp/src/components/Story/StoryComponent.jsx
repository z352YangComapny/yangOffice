import React, { useEffect, useState } from 'react'
import { Button, Card, CardText, Input, Modal } from 'reactstrap';
import '../../assets/css/story.css'

const StoryComponent = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [currentStoryIndex, setCurrentStoryIndex] = useState(0);
  const [stories, setStories] = useState([
    "User1 : Story Content 1",
    "User2 :Story Content 2",
    "User3 :Story Content 3",
    "User4 :Story Content 4",
    "User5 :Story Content 5",
    "User6 :Story Content 6",
    "User7 :Story Content 7"
  ])


  const showNextStory = () => {
    setCurrentStoryIndex((prevIndex) => (prevIndex + 1) % stories.length);
  };

  const showPreviousStory = () => {
    setCurrentStoryIndex((prevIndex) => (prevIndex - 1 + stories.length) % stories.length);
  };

  const handleScroll = (event) => {
    event.preventDefault(); // 기본 스크롤 동작 방지

    const delta = Math.sign(event.deltaY);
    if (delta > 0) {
      showNextStory();
    } else if (delta < 0) {
      showPreviousStory();
    }
  };


  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentStoryIndex((prevIndex) => (prevIndex + 1) % stories.length);
    }, 4000);
    return () => clearInterval(interval);
  }, [stories]);


  const deleteCurrentStory = () => {
    const updatedStories = stories.filter((story, index) => index !== currentStoryIndex);
    setStories(updatedStories);
  };

  const renderStory = () => {
    return (
      <>
        <CardText
          className="story-card-text"
          style={{
            display: "flex",
            justifyContent: "space-between",
            marginTop: "0.75%",
            marginLeft: "2%",
            width: "90%",
            height: "80%",
            animation: "rollAndFade 4s infinite",
            transformOrigin: "center bottom",
            transform: "translateY(33%)",
            opacity: 1,
            position: "absolute",
            // boxShadow: "3px 3px 6px rgb(0, 0, 0 , 0.5) !important",
          }}
        >
          <p className='story-text' >{stories[currentStoryIndex]}</p>
        </CardText>
      </>
    );
  }

  return (
    <Card
      onWheel={(event) => {
        handleScroll(event);
      }}
      style={{
        border: 'solid 3px rgba(81, 203, 206, 1)',
        marginTop: "25px",
        boxShadow: '4px 4px 8px rgba(81, 203, 206, 0.8)',
        height: "9%",
        display: 'flex'
      }}
    >
      {renderStory()}
      <div className='story-btns'>
        <Button
          className="btn-link btn-icon"
          color="primary"
          type="button"
          onClick={deleteCurrentStory}
          title='삭제하기'
        >
          <i className="fa nc-icon nc-simple-remove" title='삭제하기'></i>
        </Button>
        <Button className="btn-round add-btn" outline color="primary" onClick={() => { setIsModalOpen(true) }}>
          <i className="fa nc-icon nc-simple-add" title='등록하기'></i>
        </Button>
      </div>

      <Modal
        isOpen={isModalOpen}
        className="story-modal modal-lg"
        modalClassName="bd-example-modal-lg"
        // toggle={() => setIsModalOpen(false)}
      >
        <div className="story-create-header">
          <h5 style={{marginTop:"15px", marginLeft:"15px", fontWeight:"900"}}>
            Story 쓰기
          </h5>
          <Button color="neutral">
            <i className="fa nc-icon nc-simple-remove" onClick={() => setIsModalOpen(false)}></i>
          </Button>
        </div>
        <div className='story-create-body'>
          <Input className='story-create'></Input>
          <Button className='story-btn'
          onClick={()=>{
            setIsModalOpen(false)
          }}
          >등록하기</Button>
        </div>
      </Modal>
    </Card>
  )
}

export default StoryComponent