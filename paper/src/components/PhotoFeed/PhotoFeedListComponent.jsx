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
    const [feedId, setFeedId] = useState(0)
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
            .catch((err) => console.log(err))
        getFeedPage(page, hostname).then(resp => {
            console.log(resp.data)
            setData(resp.data)
            setFeedId(resp.data[0].id)
        })
            .catch((err) => {
                console.log(err)
            });
        const feedBoxContainer = feedBox.current;
        feedBoxContainer.addEventListener("scroll", handleScroll);

        return () => {
            feedBoxContainer.removeEventListener("scroll", handleScroll);
        };
    }, [hostname])

    useEffect(() => {
        data.forEach((feed, index) => {
            if (feed.id == feedId) {
                setComments(feed.comments)
                setSelectedFeed(data[index])
            }
        })
    }, [feedId])


    const handleImgClick = (itemId) => {
        setFeedId(itemId)
    };


    const renderComments = () => {
        console.log(comments)
        return (
            <>
                {comments.map((comment, index) => (
                    <div style={{ border: "rgba(0,0,0,0.3) solid 1px", borderRadius: "10px", padding:"5px", paddingTop:"15px" }}>
                        <div style={{ display: 'flex', justifyContent: "space-between", paddingLeft: "2%", paddingRight: "2%" }}>
                            <p key={index} className='comments'>{comment.content}</p>
                            <p key={index} className='comments'>{comment.nickName}</p>

                        </div>
                        <div style={{ display: 'flex', justifyContent: "space-between", alignItems:"center", paddingLeft: "2%", paddingRight: "2%" }}>
                            <p key={index} className='comments'>{comment.regDate ? comment.regDate : "2023-09-01T00:57:52"}</p>
                            <Button size="sm" className="btn-link"><i className="fa nc-icon nc-simple-remove" title='삭제하기'></i></Button>
                        </div>

                    </div>
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