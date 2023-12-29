import { PhotoFeedContext } from 'contexts/PhotoFeedContextProvider';
import { max } from 'moment';
import React, { useContext, useEffect, useRef, useState } from 'react'
import { useInView } from 'react-intersection-observer';
import { Button, Input } from 'reactstrap';
import '../../assets/css/worldphotofeed.css'
import { useParams } from 'react-router-dom';

const imgBaseUrl = "http://localhost:8080/resources/upload/attachment/"

const WorldPhotoFeedListComponent = () => {
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
            .catch((err) => {
                console.log(err)
            })

        getFeedPage(page, hostname).then(resp => {
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
            if (feed.id == feedId) setSelectedFeed(data[index])
        })
    }, [feedId])


    const handleImgClick = (itemId) => {
        setIsDetail(true);
        setFeedId(itemId);
    };



    return (
        <>
            <h4>Photo Feed</h4>
            <div className='feed-box' ref={feedBox}>
                <div className='grid-container'>
                    {data.map((item, index) => (
                        <div key={index} className="grid-item item">
                            {item.attachmentNames && item.attachmentNames.length > 0 && (
                                <img
                                    src={`http://localhost:8080/resources/upload/attachment/${item.attachmentNames[0]}`}
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
export default WorldPhotoFeedListComponent