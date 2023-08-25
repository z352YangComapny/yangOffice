/*!

=========================================================
* Paper Kit React - v1.3.2
=========================================================

* Product Page: https://www.creative-tim.com/product/paper-kit-react

* Copyright 2023 Creative Tim (https://www.creative-tim.com)
* Licensed under MIT (https://github.com/creativetimofficial/paper-kit-react/blob/main/LICENSE.md)

* Coded by Creative Tim

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

*/
import React, { useContext, useState } from "react";
// nodejs library that concatenates strings
import classnames from "classnames";
// reactstrap components
import {
  Button,
  Collapse,
  NavbarBrand,
  Navbar,
  NavItem,
  NavLink,
  Nav,
  Container,
  Input,
} from "reactstrap";
import '../../assets/css/style.css'
import { MemberContext } from "contexts/MembetContextProvider";
import { useNavigate } from "react-router-dom";

function IndexNavbar() {
  const {
    states: {
      isLogin,
      userProfile
    },
    actions: {
      setUserProfile,
      setIsLogin,
      LogOut,
      signin
    },
  } = useContext(MemberContext)


  const [navbarColor, setNavbarColor] = React.useState("navbar-transparent");
  const [navbarCollapse, setNavbarCollapse] = React.useState(false);

  const navigate = useNavigate();

  const toggleNavbarCollapse = () => {
    setNavbarCollapse(!navbarCollapse);
    document.documentElement.classList.toggle("nav-open");
  };

  React.useEffect(() => {
    const updateNavbarColor = () => {
      if (
        document.documentElement.scrollTop > 299 ||
        document.body.scrollTop > 299
      ) {
        setNavbarColor("");
      } else if (
        document.documentElement.scrollTop < 300 ||
        document.body.scrollTop < 300
      ) {
        setNavbarColor("navbar-transparent");
      }
    };

    window.addEventListener("scroll", updateNavbarColor);

    return function cleanup() {
      window.removeEventListener("scroll", updateNavbarColor);
    };
  });
  return (
    <Navbar className={classnames("fixed-top", navbarColor)} expand="lg">
      <Container>
        <div className="navbar-translate">
          <NavbarBrand
            data-placement="bottom"
            href="/"
          >
            Yang World
          </NavbarBrand>
          <button
            aria-expanded={navbarCollapse}
            className={classnames("navbar-toggler navbar-toggler", {
              toggled: navbarCollapse,
            })}
            onClick={toggleNavbarCollapse}
          >
            <span className="navbar-toggler-bar bar1" />
            <span className="navbar-toggler-bar bar2" />
            <span className="navbar-toggler-bar bar3" />
          </button>
        </div>
        <Collapse
          className="justify-content-end"
          navbar
          isOpen={navbarCollapse}
        >
          {isLogin? 
          <Nav navbar>
            
            <NavItem>
              <NavLink
                data-placement="bottom"
                href="https://twitter.com/CreativeTim?ref=creativetim"
                target="_blank"
                title="내 정보 보기"
              >
                <i className="fa nc-icon nc-circle-10" />
                <p style={{fontSize:"14px", fontStyle:"italic"}}>username</p>
                {/* <p className="d-lg-none">내 정보 보기</p> */}
              </NavLink>
            </NavItem>
             
            <NavItem>
              <NavLink
                data-placement="bottom"
                href="https://www.facebook.com/CreativeTim?ref=creativetim"
                target="_blank"
                title="내 피드 보기"
              >
                <i className="fa nc-icon nc-layout-11" />
                <p className="d-lg-none">내 피드 보기</p>
              </NavLink>
            </NavItem>
            <NavItem>
              <NavLink
                data-placement="bottom"
                href="https://www.instagram.com/CreativeTimOfficial?ref=creativetim"
                target="_blank"
                title="World 접속하기"
              >
                <i className="fa nc-icon nc-world-2" />
                <p className="d-lg-none">World 접속하기</p>
              </NavLink>
            </NavItem>
            <NavItem>
              <NavLink
                data-placement="bottom"
                href="https://www.github.com/CreativeTimOfficial/paper-kit-react?ref=creativetim"
                target="_blank"
                title="DM"
              >
                <i className="fa nc-icon nc-chat-33" />
                <p className="d-lg-none">DM</p>
              </NavLink>
            </NavItem>
            <NavItem>
              <NavLink
                data-placement="bottom"
                href="https://www.github.com/CreativeTimOfficial/paper-kit-react?ref=creativetim"
                target="_blank"
                title="알림"
              >
                <i className="fa nc-icon nc-bell-55" />
                <p className="d-lg-none">알림</p>
              </NavLink>
            </NavItem>
            <NavItem>
              <div className="sreach-container">
                <Input className="memberSearchBar" placeholder="검색 할 회원 정보 "></Input>
                <Button
                className="btn-round"
                color="warning"
                href="https://www.creative-tim.com/product/paper-kit-pro-react?ref=pkr-index-navbar"
                target="_blank"
              > <i className="nc-icon nc-zoom-split" /></Button>
              </div>
            </NavItem>



            
            <NavItem>
              <Button
                className="btn-round"
                color="danger"
                href="https://www.creative-tim.com/product/paper-kit-pro-react?ref=pkr-index-navbar"
                target="_blank"
                style={{marginLeft:"10px"}}
              >
                <i className="nc-icon nc-spaceship" style={{marginRight:"10px"}}></i> LogOut
              </Button>
            </NavItem>
          </Nav>
          : 
          <Nav navbar>
          <NavItem>
          <Button
            className="btn-round"
            color="danger"
            target="_blank"
            style={{marginLeft:"10px"}}
            onClick={()=>{navigate('/signin')}}
          >
            <i className="nc-icon nc-spaceship" style={{marginRight:"10px"}}></i> LogIn
          </Button>
        </NavItem>
        </Nav>
          }
        </Collapse>
      </Container>
    </Navbar>
  );
}

export default IndexNavbar;
