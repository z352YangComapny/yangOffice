import React, { useContext, useEffect, useState } from 'react'
import { Button, Card, CardBody, CardFooter, CardHeader, Input, Pagination, PaginationItem, PaginationLink } from 'reactstrap'
import '../../assets/css/guestbook.css'
import MyPagination from 'components/Commons/MyPagination'
import { GuestBookContext } from 'contexts/GuestBookContextProvider'
import { useParams } from 'react-router-dom'
import { MemberContext } from 'contexts/MembetContextProvider'
import axios from 'axios'
import { NotificationContext } from 'contexts/NotificationContextProvider'

const SpringBaseURL = "http://localhost:8080"

const GuestBookComponet = () => {
    const {
        states: {
          message,
        },
        actions: {
          setMessage
        },
      } = useContext(NotificationContext)
    const {
        states: {
            guestBookList
        },
        actions: {
            setGuestBookList,
            getGuestBookList,
            enrollguestBook,
            deleteGuestbook
        },
    } = useContext(GuestBookContext)
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
    } = useContext(MemberContext)

    const [totalCount, setTotalCount] = useState(0);
    const [currentPage, setCurrentPage] = useState(1);
    const [maxPage , setMaxPage] = useState(0);
    const [re , setRe] = useState(false)
    const [ guestbookInput , setGuestbookInput] = useState('');
    const { hostname } = useParams();
    const PageNoPerLine = 5;


    useEffect(() => {
        getTotalCount(hostname)
            .then((resp) => {
                setTotalCount(resp.data);
                setMaxPage(Math.ceil(resp.data / 2))
            })
    }, [hostname])


    useEffect(() => {
        setRe(false)
        getGuestBookList(hostname, currentPage)
            .then((resp) => {
                console.log(resp)
                setGuestBookList(resp.data)
            })
            .catch((err) => {
                console.log(err)
            })
            getTotalCount(hostname)
            .then((resp) => {
                setTotalCount(resp.data);
                setMaxPage(Math.ceil(resp.data / 2))
            })
            
    }, [currentPage, re]);


    const getTotalCount = async (hostname) => {
        return await axios.get(SpringBaseURL + `/guestbook/count?hostname=${hostname}`)
    }
    const handleDeleteGuetbook = (id) => {
        deleteGuestbook(id)
        .then((resp)=>{
            setMessage({
                color:"success", value:"방명록 삭제 성공!!"
            })
            setRe(true)
        })
        .catch((err)=>{
            console.log(err);
            setMessage({
                color:"danger", value:"방명록 삭제 실패!!"
            })
        })
    }


    const renderPaginationItems = () => {
        const paginationItems = [];
        let end = Math.ceil(currentPage / PageNoPerLine) * PageNoPerLine;
        const start = (Math.ceil(currentPage / PageNoPerLine) - 1) * PageNoPerLine + 1;
        if (maxPage < end) {
            end = maxPage
        }
        
        for (let pageIndex = start; pageIndex <= end; pageIndex++) {
            paginationItems.push(
                <PaginationItem>
                    <PaginationLink onClick={() => { setCurrentPage(pageIndex) }}>
                        {pageIndex}
                    </PaginationLink>
                </PaginationItem>
            );
        }
        return paginationItems;
    };

    const submitGuestbook = () => {
        enrollguestBook(hostname, guestbookInput)
        .then((resp)=>{
            setMessage({
                color:'success', value:'방명록 등록 완료😊'
            })
        })
        .catch((err)=>{
            setMessage({
                color:'danger', value:'방명록 등록 실패😵'
            })
        })
        .finally(()=>{
            setGuestbookInput('');
        })
        setRe(true)
    }

    const renderGuestbook = () => {
        const guestbookarr = []
        if (guestBookList.length > 0) {
            guestBookList.forEach(element => {
                guestbookarr.push(
                    <div className='guestbook-text-line'>
                        <p className='guestbook-text-content' style={{ marginLeft: "15px", fontWeight: "500" }}>{element.content}</p>
                        <p className='guestbook-text-writer' style={{ marginRight: "15px", fontWeight: "500" }}>{element.nickname}</p>
                        <p className='guestbook-text-regdate'>{element.regDate.replaceAll('-', '/')}</p>
                        <i className="fa nc-icon nc-simple-remove" title='삭제하기' onClick={()=>{handleDeleteGuetbook(element.id)}}></i>
                    </div>
                );
            });
        }
        return guestbookarr;
    }

    return (
        <Card style={{
            border: 'solid 3px rgba(81, 203, 206, 1)',
            transform: 'none',
            transition: 'none',
            marginTop: "20px",
            boxShadow: '4px 4px 8px rgba(81, 203, 206, 0.8)',
            height: "14.5%",
            display: "flex"
        }}>
            <div className='guestBook-container'>
                <div className='guestbook-title'>
                    <p className='inner-title'>&nbsp;BOOK</p>
                    <p className='inner-title2'>GUEST</p>
                </div>
                <div style={{ width: '100%' }}>
                    <div style={{ width: '100%', height: '69%' }}>
                        {renderGuestbook()}
                    </div>
                    <div className='guestbook-footer'>
                        <div style={{ width: '50%' }}>
                            <Pagination
                                className='pagination'
                                aria-label="Page navigation example"
                                size="sm"
                            >
                                <PaginationItem>
                                    <PaginationLink
                                        first
                                        onClick={() => { setCurrentPage(1) }}
                                    />
                                </PaginationItem>
                                <PaginationItem>
                                    <PaginationLink
                                        onClick={() => { 
                                            if( currentPage === 1 ) return;
                                            setCurrentPage(prev=>prev-1) }}
                                        previous
                                    />
                                </PaginationItem>
                                {renderPaginationItems()}
                                <PaginationItem>
                                    <PaginationLink
                                        onClick={() => { 
                                            if( currentPage === maxPage ) return;
                                            setCurrentPage(prev=>prev+1) }}
                                        next
                                    />
                                </PaginationItem>
                                <PaginationItem>
                                    <PaginationLink
                                        onClick={() => { setCurrentPage(maxPage) }}
                                        last
                                    />
                                </PaginationItem>
                            </Pagination>
                        </div>
                        <div style={{ width: '50%', display: "flex" }}>
                            <Input type='text' name='guestbook' style={{ width: "85%", height: "35px" }} value={guestbookInput} onChange={(e)=>{setGuestbookInput(e.target.value)}}/>
                            <Button color='primary' style={{ width: "15%", height: "35px" }} onClick={submitGuestbook}><i className="fa nc-icon nc-simple-add" title='등록하기'></i></Button>
                        </div>
                    </div>
                </div>
            </div>
        </Card>
    )
}

export default GuestBookComponet