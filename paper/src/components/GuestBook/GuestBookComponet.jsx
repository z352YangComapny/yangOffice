import React, { useContext, useEffect, useState } from 'react'
import { Button, Card, CardBody, CardFooter, CardHeader, Input, Pagination, PaginationItem, PaginationLink } from 'reactstrap'
import '../../assets/css/guestbook.css'
import MyPagination from 'components/Commons/MyPagination'
import { GuestBookContext } from 'contexts/GuestBookContextProvider'
import { useParams } from 'react-router-dom'
import { MemberContext } from 'contexts/MembetContextProvider'
import axios from 'axios'

const SpringBaseURL = "http://localhost:8080"

const GuestBookComponet = () => {
    const {
        states: {
            guestBookList
        },
        actions: {
            setGuestBookList,
            getGuestBookList
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
    const [pageNo, setPageNo] = useState(1);
    const [totalCount, setTotalCount] = useState(0);
    const [currentPage, setCurrentPage] = useState(1);
    const { id } = useParams();
    const PageNoPerLine = 5;

    useEffect(() => {
        if(!userProfile) return
        getTotalCount(userProfile.id)
            .then((resp) => {
                setTotalCount(resp.data);
            })
    }, [])


    useEffect(() => {
        console.log(id)
        getGuestBookList(userProfile && userProfile.id, pageNo)
            .then((resp) => {
                setGuestBookList(resp.data)
            })
            .catch((err) => {
                console.log(err)
            })
    }, [userProfile]);

    useEffect(() => {
    }, [guestBookList])


    const getTotalCount = async (id) => {
        return await axios.get(SpringBaseURL + `/guestbook/count?id=${id}`)
    }


    const renderPaginationItems = () => {
        const paginationItems = [];
        const maxPage = Math.ceil(totalCount / 2);
        let end = Math.ceil(currentPage / PageNoPerLine) * PageNoPerLine;
        const start = (Math.ceil(currentPage / PageNoPerLine) - 1) * PageNoPerLine + 1;
        if (maxPage < end) {
            end = maxPage
        }
        console.log(start + "," + end)
        for (let pageIndex = start; pageIndex <= end; pageIndex++) {
            paginationItems.push(
                <PaginationItem>
                    <PaginationLink onClick={() => { console.log("hi") }}>
                        {pageIndex}
                    </PaginationLink>
                </PaginationItem>
            );
        }
        return paginationItems;
    };



    const renderGuestbook = () => {
        const guestbookarr = []
        console.log(guestBookList)
        if (guestBookList.length > 0) {
            guestBookList.forEach(element => {
                guestbookarr.push(
                    <div className='guestbook-text-line'>
                        <p className='guestbook-text-content' style={{ marginLeft: "15px", fontWeight: "500" }}>{element.content}</p>
                        <p className='guestbook-text-writer' style={{ marginRight: "15px", fontWeight: "500" }}>{element.nickname}</p>
                        <p className='guestbook-text-regdate'>{element.regDate.replaceAll('-', '/')}</p>
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
                                        onClick={() => { console.log("hi") }}
                                    />
                                </PaginationItem>
                                <PaginationItem>
                                    <PaginationLink
                                        onClick={() => { console.log("hi") }}
                                        previous
                                    />
                                </PaginationItem>
                                {renderPaginationItems()}
                                <PaginationItem>
                                    <PaginationLink
                                        onClick={() => { console.log("hi") }}
                                        next
                                    />
                                </PaginationItem>
                                <PaginationItem>
                                    <PaginationLink
                                        onClick={() => { console.log("hi") }}
                                        last
                                    />
                                </PaginationItem>
                            </Pagination>
                        </div>
                        <div style={{ width: '50%', display: "flex" }}>
                            <Input type='text' name='guestbook' style={{ width: "85%", height: "35px" }} />
                            <Button color='primary' style={{ width: "15%", height: "35px" }}><i className="fa nc-icon nc-simple-add" title='등록하기'></i></Button>
                        </div>
                    </div>
                </div>
            </div>
        </Card>
    )
}

export default GuestBookComponet