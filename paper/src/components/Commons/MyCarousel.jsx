import { PhotoFeedContext } from 'contexts/PhotoFeedContextProvider';
import React, { useContext, useEffect, useState } from 'react';
import {
  Carousel,
  CarouselItem,
  CarouselControl,
  CarouselIndicators,
  CarouselCaption,
} from 'reactstrap';




function MyCarousel(args) {
  const{
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
  const [activeIndex, setActiveIndex] = useState(0);
  const [animating, setAnimating] = useState(false);
  const [ items , setItems] = useState([
    {
      src:'1234',
      altText:"logo",
      key:1
    }
  ])

  useEffect(()=>{
    const parseItems = [];
    console.log(selectedFeed)
    if (selectedFeed.attachmentNames && selectedFeed.attachmentNames.length>0) {
      selectedFeed.attachmentNames.forEach((renamedFilename, index) => {
        console.log(renamedFilename)
        const parsedItem = {
          src: `http://localhost:8080/resources/upload/attachment/${renamedFilename}`,
          altText: `Slide ${index + 1}`,
          key: index + 1,
        };
        parseItems.push(parsedItem);
      });
      console.log(parseItems); // Output parsed items to the console
    }
    setItems(parseItems);
  },[selectedFeed])

  const next = () => {
    if (animating) return;
    const nextIndex = activeIndex === items.length - 1 ? 0 : activeIndex + 1;
    setActiveIndex(nextIndex);
  };

  const previous = () => {
    if (animating) return;
    const nextIndex = activeIndex === 0 ? items.length - 1 : activeIndex - 1;
    setActiveIndex(nextIndex);
  };

  const goToIndex = (newIndex) => {
    if (animating) return;
    setActiveIndex(newIndex);
  };

  // AttachmentNames >> items 를 파싱해서 준비할것.
  const slides = items.map((item) => {
    return (
      <CarouselItem
        onExiting={() => setAnimating(true)}
        onExited={() => setAnimating(false)}
        key={item.src}
      >
        <img src={item.src} alt={item.altText} className='feed-detail-image'/>
      </CarouselItem>
    );
  });

  return (
    <Carousel
      activeIndex={activeIndex}
      next={next}
      previous={previous}
      {...args}
      style={{height:"100px"}}
    >
      <CarouselIndicators
        items={items} // AttachmentNames 를 파싱해서 준비할것.
        activeIndex={activeIndex}
        onClickHandler={goToIndex}
      />
      {slides}
      <CarouselControl
        direction="prev"
        directionText="Previous"
        onClickHandler={previous}
      />
      <CarouselControl
        direction="next"
        directionText="Next"
        onClickHandler={next}
      />
    </Carousel>
  );
}

export default MyCarousel;