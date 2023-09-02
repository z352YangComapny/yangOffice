import { PhotoFeedContext } from 'contexts/PhotoFeedContextProvider';
import { max } from 'moment';
import React, { useContext, useEffect, useRef, useState } from 'react'
import { useInView } from 'react-intersection-observer';
import { useParams } from 'react-router-dom';
import { Button, Input } from 'reactstrap';

const imgBaseUrl = "http://localhost:8080/resources/upload/attachment/"

const PhotoFeedListComponent = () => {
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
    } = useContext(PhotoFeedContext);
    const { hostname } = useParams();

    const [page, setPage] = useState(1);
    const [isloading, setIsloading] = useState(false);
    const [feedId , setFeedId] = useState(0)
    const [comments, setComments] = useState([]);
    const feedBox = useRef(null);

    const loadFeed = () => {
        getFeedPage(page, hostname).then(resp => {
            setData(prev => [...prev, ...resp.data])
            setIsloading(false);
        });
        setIsloading(false)
    }

    useEffect(() => {
        if (page > maxPage && isloading) return;
        loadFeed();
    }, [page])

    const handleScroll = () => {
        const feedBoxContainer = feedBox.current;
        const contentHeight = feedBoxContainer.scrollHeight;
        const scrollTop = feedBoxContainer.scrollTop;
        const containerHeight = feedBoxContainer.clientHeight;

        if (scrollTop / (contentHeight - containerHeight) >= 0.85 && !isloading) {
            setIsloading(true)
            setPage(pre => pre + 1)
        }
    };

    useEffect(() => {
        const pageSize = 15;
        getTotalPage()
            .then((resp) => {
                setMaxPage(Math.ceil(resp.data / pageSize))
            })
            .catch((err)=> console.log(err))
        getFeedPage(page, hostname).then(resp => {
            setData(resp.data)
            setFeedId(resp.data[0].id)
        })
        .catch((err)=>{
            console.log(err)
        });
        const feedBoxContainer = feedBox.current;
        feedBoxContainer.addEventListener("scroll", handleScroll);

        return () => {
            feedBoxContainer.removeEventListener("scroll", handleScroll);
        };
    }, [hostname])

    useEffect(()=>{
        data.forEach((feed,index)=>{
            if(feed.id==feedId){
                setComments(feed.comments)
                setSelectedFeed(data[index])
            }
        })
    },[feedId])


    const handleImgClick = (itemId) => {
        setFeedId(itemId)
    };


    console.log(data)
    const renderComments = () => {
        return (
            <>
                {comments.map((comment, index) => (
                    <p key={index} className='comments'>{comment}</p>
                ))}
            </>
        );
    };

    return (
        <>
            <h5>Comments</h5>
            <div className='feed-comments'>
                {renderComments()}
            </div>
            <div style={{ display: 'flex' }}>
                <Input className='feed-comment-input' type='text'></Input>
                <Button style={{ width: "30%", height: "7%" }}>등록</Button>
            </div>
            <div className='feed-box' ref={feedBox}>
                <div className='grid-container'>
                    {data.map((item, index) => (
                        <div key={index} className="grid-item item">
                            {item.attachments && item.attachments.length > 0 && (
                                <img
                                    src={`http://localhost:8080/resources/upload/attachment/${item.attachments[0].renamedFilename}`}
                                    alt="Attachment"
                                    onClick={() => handleImgClick(item.id)}
                                />
                            )}
                        </div>
                    ))}
                </div>
            </div>
        </>
    );
};
export default PhotoFeedListComponent