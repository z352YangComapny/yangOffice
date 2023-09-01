import SmilingFaceSunglasses from 'components/Icons/icons/smiling-face-sunglasses'
import React, { useState } from 'react'
import '../../assets/css/qna.css'
import { Button, Card, CardBody, CardHeader, CardTitle, DropdownItem, DropdownMenu, DropdownToggle, Pagination, PaginationItem, PaginationLink, Table, UncontrolledDropdown } from 'reactstrap'
import {
    Accordion,
    AccordionBody,
    AccordionHeader,
    AccordionItem,
} from 'reactstrap';

const Qna = () => {
    const [isQna, setIsQna] = useState(false);
    const [open, setOpen] = useState('1');
    const toggle = (id) => {
        if (open === id) {
            setOpen();
        } else {
            setOpen(id);
        }
    };

    const handleOnClickTitle = () => {
        setIsQna(prev => !prev)
    }

    const faqtitle = {
        width: "20%",
        height: "100%",
        backgroundColor: isQna ? "rgba(0,0,0,0.2)" : "rgba(0,0,0,0.4)",
        borderTopRightRadius: "10px",
        borderTopLeftRadius: "10px",
        textAlign: "center",
    }

    const qnatitle = {
        width: "20%",
        height: "100%",
        backgroundColor: !isQna ? "rgba(0,0,0,0.2)" : "rgba(0,0,0,0.4)",
        textAlign: "center",
        borderTopRightRadius: "10px",
        borderTopLeftRadius: "10px",
    }

    return (
        <Card style={{
            border: 'solid 3px rgba(81, 203, 206, 1)',
            transform: 'none',
            transition: 'none',
            boxShadow: '4px 4px 8px rgba(81, 203, 206, 0.8)',
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center'
        }}>
            <CardHeader style={{ display: "flex", backgroundColor: "transparent", paddingTop: "15px", width: "100%" }}>
                <CardTitle tag="h5" style={{ fontWeight: "600", color: "rgba(81, 203, 206, 0.8)" }}>#고객센터</CardTitle>
            </CardHeader>

            <div style={{ width: "100%", height: "40%", display: 'flex', paddingTop: "10px", paddingLeft: "20px" }}>
                <div style={faqtitle} className='nav-cursor' onClick={handleOnClickTitle}>
                    FAQ
                </div>
                <div style={qnatitle} className='nav-cursor' onClick={handleOnClickTitle}>
                    QNA
                </div>
            </div>
            {isQna ?
                <div className='qna-body'>
                    <Table>

                        <thead>
                            <tr>
                                <th>
                                    #
                                </th>
                                <th>
                                    구매 상품
                                </th>
                                <th>
                                    금액
                                </th>
                                <th>
                                    구매 시각
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                        <tr>
                        <th scope="row">
                          1
                        </th>
                        <td>
                          Mark
                        </td>
                        <td>
                          Otto
                        </td>
                        <td>
                          @mdo
                        </td>
                      </tr>
                      <tr>
                        <th scope="row">
                          1
                        </th>
                        <td>
                          Mark
                        </td>
                        <td>
                          Otto
                        </td>
                        <td>
                          @mdo
                        </td>
                      </tr>
                      <tr>
                        <th scope="row">
                          1
                        </th>
                        <td>
                          Mark
                        </td>
                        <td>
                          Otto
                        </td>
                        <td>
                          @mdo
                        </td>
                      </tr>
                      <tr>
                        <th scope="row">
                          1
                        </th>
                        <td>
                          Mark
                        </td>
                        <td>
                          Otto
                        </td>
                        <td>
                          @mdo
                        </td>
                      </tr>
                      <tr>
                        <th scope="row">
                          1
                        </th>
                        <td>
                          Mark
                        </td>
                        <td>
                          Otto
                        </td>
                        <td>
                          @mdo
                        </td>
                      </tr>
                        </tbody>
                    </Table>
                    <Pagination
                    className='qna-pagination'
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
                :
                <div className='qna-body'>
                    <p className='faq-content question'>Q: 어떻게 새로운 게시물을 작성하나요?</p>
                    <p className='faq-content answer'>A: 사진피드 톱니바퀴버튼을 클릭하여 <span className='tmp'>Add</span>를 눌러주세요.</p>
                    <p className='faq-content question'>Q: 친구를 어떻게 추가하나요?</p>
                    <p className='faq-content answer'>A: 사용자 프로필 상단의 + 버튼을 눌러 <span className='tmp'>Follow</span>버튼을 눌러주세요.</p>
                    <p className='faq-content question'>Q: 내 계정의 개인정보는 어떻게 관리하나요?</p>
                    <p className='faq-content answer'>A: 프로필 설정 페이지에서 개인정보를 관리할 수 있습니다.</p>
                    <p className='faq-content question'>Q: 상품을 환불하려면 어떻게 해야 하나요?</p>
                    <p className='faq-content answer'>A: 환불을 원하신다면 고객 서비스에 문의하여 환불 절차를 안내받을 수 있습니다.</p>
                </div>
            }

        </Card>
    )
}

export default Qna