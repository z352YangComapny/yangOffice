import SmilingFaceSunglasses from 'components/Icons/icons/smiling-face-sunglasses'
import React, { useContext } from 'react'
import { Button, Card, CardBody, CardFooter, CardHeader, DropdownItem, DropdownMenu, DropdownToggle, UncontrolledDropdown } from 'reactstrap'
import '../../assets/css/profile.css'
import { useParams } from 'react-router-dom'
import axios from 'axios'
import { NotificationContext } from 'contexts/NotificationContextProvider'

const ProfileComponent = (props) => {
  const {
    states: { message },
    actions: { setMessage }
  } = useContext(NotificationContext)
  const { hostname } = useParams();

  const handleFollow = () => {
    const headers = {
      headers: {
        "Authorization": sessionStorage.getItem('token')
      }
    }
    axios.post('http://localhost:8080/member/follow', { hostname: hostname }, headers)
      .then((resp) => {
        setMessage({
          color: "success", value: `${hostname}님 팔로우를 시작합니다💓`
        })
      })
      .catch((err) => {
        setMessage({
          color: "danger", value: `팔로우실패😰 관리자에게 문의해주세요.`
        })
      })
  }

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
          <UncontrolledDropdown
            className="me-2"
            direction="down"
          >
            <DropdownToggle
              caret
              color="primary"
            >
              {hostname}
            </DropdownToggle>
            <DropdownMenu>
              <DropdownItem header>
                {hostname}
              </DropdownItem>
              {hostname != sessionStorage.getItem('username') ? <DropdownItem onClick={handleFollow}>
                Follow
              </DropdownItem> : ""}
              {hostname != sessionStorage.getItem('username') ? <DropdownItem>
                Dm
              </DropdownItem>
                :
                <DropdownItem>
                  Profile Setting
                </DropdownItem>}
            </DropdownMenu>
          </UncontrolledDropdown>
          <Button color='danger' outline title='신고하기' style={{ marginLeft: "5px" }} >
            <i className="fa nc-icon nc-bell-55"></i>
          </Button>
        </div>
      </CardHeader>
      <CardBody>
        <div className='profile-card-body'>
          <img
            alt="..."
            className="profile-img"
            src={require("assets/img/faces/clem-onojeghuo-2.jpg")}
          />
        </div>
        <div className='line'></div>
        <div className='profile-info'>
          <div className='nickname'>"NickName"</div>
          <div className='state'>
            <p style={{ fontSize: "1.3rem", marginTop: "5px" }}>Today is ... </p>
            <div style={{ marginLeft: "20px" }}><SmilingFaceSunglasses width={40} height={40} /></div>
          </div>
          <div className='introduction'>"Introduction"</div>
        </div>

      </CardBody>
      <CardFooter>
      </CardFooter>
    </Card>
  )
}

export default ProfileComponent