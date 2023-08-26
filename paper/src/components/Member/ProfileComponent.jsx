import SmilingFaceSunglasses from 'components/Icons/icons/smiling-face-sunglasses'
import React from 'react'
import { Button,  Card,  CardBody, CardFooter, CardHeader, DropdownItem, DropdownMenu, DropdownToggle, UncontrolledDropdown } from 'reactstrap'
import '../../assets/css/profile.css'

const ProfileComponent = (props) => {
  console.log(props)

  return (
    <Card style={{
      border: 'solid 3px rgba(81, 203, 206, 1)',
      transform: 'none',
      transition: 'none',
      marginTop: "20px",
      boxShadow: '4px 4px 8px rgba(81, 203, 206, 0.8)'
    }}>
      <CardHeader>
        <div style={{display:'flex', justifyContent:"space-between" }}>
          <UncontrolledDropdown>
            <DropdownToggle
              aria-expanded={false}
              aria-haspopup={true}
              caret
              color="primary"
              data-toggle="dropdown"
              id="dropdownMenuButton"
              type="button"
            >
              "USERNAME"
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
          <Button color='danger' outline title='신고하기' style={{marginLeft:"5px"}} >
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