import SmilingFaceSunglasses from 'components/Icons/icons/smiling-face-sunglasses'
import React from 'react'
import { Button, Card, CardBody, CardFooter, CardHeader, DropdownItem, DropdownMenu, DropdownToggle, UncontrolledDropdown } from 'reactstrap'
import '../../assets/css/worldprofile.css'

const WorldProfileComponent = (props) => {
        return (
        <Card style={{
            border: 'solid 3px rgba(81, 203, 206, 1)',
            transform: 'none',
            transition: 'none',
            marginTop: "20px",
            boxShadow: '4px 4px 8px rgba(81, 203, 206, 0.8)'
        }}>
            <CardHeader>
                <div style={{ display: 'flex', justifyContent: "space-between" }}>
                    <UncontrolledDropdown>
                        <DropdownToggle
                            aria-expanded={false}
                            aria-haspopup={true}
                            outline
                            color="primary"
                            data-toggle="dropdown"
                            id="dropdownMenuButton"
                            type="button"
                        >
                            <i className="fa nc-icon nc-simple-add" title='등록하기'></i>
                        </DropdownToggle>
                        <DropdownMenu aria-labelledby="dropdownMenuButton">
                            <DropdownItem onClick={e => e.preventDefault()}>
                                Follow
                            </DropdownItem>
                            <DropdownItem onClick={e => e.preventDefault()}>
                                DM
                            </DropdownItem>
                            <DropdownItem onClick={e => e.preventDefault()}>
                                ProfileSetting
                            </DropdownItem>
                        </DropdownMenu>
                    </UncontrolledDropdown>
                    <Button color='danger' outline title='신고하기' style={{ marginLeft: "5px" }} >
                        <i className="fa nc-icon nc-bell-55"></i>
                    </Button>
                </div>
            </CardHeader>
            <CardBody>
                <div className='world-profile-body'>
                    <img
                        alt="..."
                        className="world-profile-img"
                        src={require("assets/img/faces/clem-onojeghuo-2.jpg")}
                    />
                    <div style={{width:"80%"}}>
                        <div className='world-nickname'>"NickName"</div>
                        <div className='world-username'>"Username"</div>
                        <div className='world-state'>
                            <p style={{ fontSize: "1.3rem", marginTop: "5px" }}>Today is ... </p>
                            <div style={{ marginLeft: "20px" }}><SmilingFaceSunglasses width={40} height={40} /></div>
                        </div>
                    </div>
                </div>
                <div className='world-introduction-container'>
                    <div className='world-introduction'>"Introduction"</div>
                </div>
            </CardBody>
        </Card>
    )
}

export default WorldProfileComponent