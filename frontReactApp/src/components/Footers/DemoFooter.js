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
/*eslint-disable*/
import React from "react";
import '../../assets/css/Footer.css'

// reactstrap components
import { Row, Container } from "reactstrap";

function DemoFooter() {
  return (
    <div className="my-footer">
      <ul className="footer-list">
        <li>
          <a>Yang World</a>
        </li>
        <li>
          <a>Terms of Serivce</a>
        </li>
        <li>
          <a>Licenses</a>
        </li>
      </ul>
      <p>이 커뮤니티 프로젝트는 화합과 소통을 위해 시작되었습니다.<br />
        함께하는 모든 분들의 노력으로 만들어진 소중한 공간이며, 우리는 더 평화로운 미래를 만드려 합니다.</p>
      <div className="ml-auto">
        <span className="copyright">
          © {new Date().getFullYear()}, made with{" "}
          <i className="fa fa-heart heart" /> by Yang World
        </span>
      </div>
    </div>
  );
}

export default DemoFooter;
