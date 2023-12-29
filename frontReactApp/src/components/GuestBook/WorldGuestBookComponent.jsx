import React from 'react'
import { Button, Card, CardBody, CardFooter, CardHeader, Input, Pagination, PaginationItem, PaginationLink } from 'reactstrap'
import '../../assets/css/guestbook.css'
import MyPagination from 'components/Commons/MyPagination'

const WorldGuestBookComponet = () => {

    const renderGuestbook = () => {
        return (
            <>
                <div className='guestbook-text-line'>
                    <p style={{ marginLeft: "15px", fontWeight: "500" }}>Guest Book Dummy Data</p>
                    <p style={{ marginRight: "15px", fontWeight: "500" }}>Writer1</p>
                </div>
                <div className='guestbook-text-line'>
                    <p style={{ marginLeft: "15px", fontWeight: "500" }}>Guest Book Dummy Data</p>
                    <p style={{ marginRight: "15px", fontWeight: "500" }}>Writer1</p>
                </div>
            </>
        )
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
                                <PaginationItem>
                                    <PaginationLink onClick={() => { console.log("hi") }}>
                                        1
                                    </PaginationLink>
                                </PaginationItem>
                                <PaginationItem>
                                    <PaginationLink onClick={() => { console.log("hi") }}>
                                        2
                                    </PaginationLink>
                                </PaginationItem>
                                <PaginationItem>
                                    <PaginationLink onClick={() => { console.log("hi") }}>
                                        3
                                    </PaginationLink>
                                </PaginationItem>
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

export default WorldGuestBookComponet