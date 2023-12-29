import axios from 'axios';
import SendMessage from 'components/Icons/icons/send-message';
import { MemberContext } from 'contexts/MembetContextProvider';
import React, { useContext, useEffect, useState } from 'react'
import { render } from 'react-dom';
import { useParams } from 'react-router-dom';
import { Input } from 'reactstrap';

const Dm = () => {
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
    const [isDetail, setIsdeTail] = useState(false);
    const [dmDetail, setDmDetail] = useState();
    const [inputValue, setInputValue] = useState();
    const [dmRoomList, setDmRoomList] = useState([]);
    const [isDown, setIsDown] = useState(false);

    const handleDmClick = () => {
        setIsDown((prevState) => !prevState);
    };

    const getDmRoomList = async () => {
        const axiosConfig = {
            headers: {
                "Authorization": sessionStorage.getItem('token'),
            }
        };
        return await axios.get(`http://localhost:8080/dm/roomList`, axiosConfig)
    }


    const handleKeyDown = (event) => {
        if (event.key === 'Enter') {
            console.log(inputValue)
            setInputValue("");
        }
    };

    const renderDmRoomDetail = () => {
        let dmRoom;
        console.log(dmDetail)
        if(!dmDetail || dmDetail.length==0) return null;
        dmRoomList.forEach((item) => {
            if (item.id === dmDetail[0].dmRoomId) {
                dmRoom = item
                console.log(dmRoom)
            }
        })
        return (
            <>
                <div className='dm-detail-header'>
                    <div style={{ fontSize: "24px", marginLeft: "10px" }}>
                        {dmRoom.participantNickname1 !== sessionStorage.getItem('nickname') ? dmRoom.participantNickname1 : dmRoom.participantNickname2}
                    </div>
                    <div style={{ alignItems: "center", display: "flex", marginRight: "10px" }}>
                        <i className="fa nc-icon nc-simple-remove nav-cursor" title='삭제하기'
                            onClick={() => { setIsdeTail(false) }}
                        ></i>
                    </div>
                </div>
                <div className='dm-detail-body'>
                    <div></div>
                </div>
                <div style={{ border: "solid 1px rgba(81,203,206,0.7)", width: "392px", position: 'fixed', bottom: `calc(11vh + 78px)`, right: `calc(0.5vw + 7px)`, borderRadius: "8px" }}>
                    <Input type='text' value={inputValue} onChange={(e) => { setInputValue(e.target.value) }} onKeyDown={handleKeyDown}></Input>
                </div>
            </>
        )
    };



    useEffect(() => {
        getDmRoomList()
            .then((resp) => {
                console.log(resp.data)
                setDmRoomList(resp.data)
            })
            .catch((err) => {
                console.log(err)
            })
    }, [isDown]);

    const getTimeAgo = (date) => {
        const currentDate = new Date();
        const pastDate = new Date(date);
        const timeDifference = currentDate - pastDate;

        const seconds = Math.floor(timeDifference / 1000);
        const minutes = Math.floor(seconds / 60);
        const hours = Math.floor(minutes / 60);
        const days = Math.floor(hours / 24);
        const months = Math.floor(days / 30); // Approximate, considering 30 days in a month
        const years = Math.floor(months / 12);

        if (years > 0) {
            return `${years}년 전`;
        } else if (months > 0) {
            return `${months}달 전`;
        } else if (days > 0) {
            return `${days}일 전`;
        } else if (hours > 0) {
            return `${hours}시간 전`;
        } else if (minutes > 0) {
            return `${minutes}분 전`;
        } else {
            return `${seconds}초 전`;
        }
    };

    const handleDetailDm = (dmRoomid) => {
        axios.get(`http://localhost:8080/dm/findDmDetails?dmRoomId=${dmRoomid}`)
            .then((resp) => {
                console.log(resp)
                setDmDetail(resp.data)
                setIsdeTail(true)
            })
            .catch((err) => { console.log(err) })
    };

    const renderDmRoomList = () => {
        const items = [];
        if(!dmRoomList || dmRoomList.length==0) return <p style={{textAlign:"center", fontSize:"20px", marginTop:"100px"}}>새로운 대화를 시작해보세요!</p>
        dmRoomList.forEach(dmRoom => {
            const timeAgo = getTimeAgo(dmRoom.lastTime);
            items.push(
                <div className="chat-room" onClick={() => { handleDetailDm(dmRoom.id) }}>
                    <div className='dm-room-profile-container'>
                        <img className="dm-room-profile-image" src={require("assets/img/faces/clem-onojeghuo-2.jpg")} />
                        <p>{dmRoom.participantNickname1 === sessionStorage.getItem('nickname') ? dmRoom.participantNickname2 : dmRoom.participantNickname1}</p>
                    </div>
                    <div className="dm-room-message">{dmRoom.lastMessage && dmRoom.lastMessage.length > 16 ? dmRoom.lastMessage.substr(0, 16) : dmRoom.lastMessage}</div>
                    <div className="dm-room-lasttime">
                        <p>{timeAgo}</p>
                    </div>
                </div>
            )
        })
        return items;
    };

    const dmContainer = {
        display: isDown ? 'block' : 'none',
        position: 'fixed',
        bottom: '12.2vh',
        right: '0.5vw',
        width: '400px',
        height: '50vh',
        // backgroundColor: 'red',
        flexDirection: 'column',
        opacity: isDown ? 1 : 0,
        transition: 'opacity 0.3s ease-in-out, display 0.3s ease-in-out', // 투명도 변화도 추가
        // alignItems : 'center'
    };

    const dmBody = {
        display: isDown ? 'block' : 'none',
        visibility : isDown ? 'visible' : 'hidden',
        width: '400px',
        height: '400px',
        border: 'solid 2px rgb(81,203,206)',
        borderRadius: '2%',
        boxShadow: '2px 2px 4px rgb(81,203,206)',
        backgroundColor: 'rgba(255,255,255,0.8)',
        transition: 'visibility 0.3s ease-in-out, opacity 0.3s ease-in-out', // 투명도 변화도 추가
        opacity: isDown ? 1 : 0,
        overflowY: 'scroll'
    };

    const dmStyles = {
        position: 'fixed',
        bottom: isDown ? '9.5vh' : '10vh',
        right: '1vw',
        backgroundColor: isDown ? "rgba(81,203,206,0.33)" : "white",
        border: 'solid 2px rgb(81,203,206)',
        marginRight: '1.5vw',
        width: '80px',
        height: '80px',
        padding: '18px',
        borderRadius: '50%',
        boxShadow: '2px 2px 4px rgb(81,203,206)',
        transition: 'bottom 0.3s ease-in-out',
    };

    return (
        <>
            <div style={dmContainer}>
                <div className="dm-body" style={dmBody}>
                    {isDetail ? renderDmRoomDetail() : renderDmRoomList()}
                </div>
            </div>
            <div className="dm nav-cursor" style={dmStyles} title="DM" onClick={handleDmClick}>
                <SendMessage width={40} fill={"#51CBCE"} height={40} ></SendMessage>
            </div>
        </>
    )
}

export default Dm;