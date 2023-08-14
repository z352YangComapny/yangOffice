import React from "react";
import '../../assets/css/Footer.css'

const Footer = () => {
    return(
        <div className="footer-container">
            <p>이 커뮤니티 프로젝트는 화합과 소통을 위해 시작되었습니다.</p>
                
                <p>함께하는 모든 분들의 노력으로 만들어진 소중한 공간이며, 우리의 목표는 더 평화로운 미래를 찾는 것입니다.</p>
            <div class="contact-info">
                <a href="/"><p>웹사이트</p></a>
                &nbsp;&nbsp;
                <a href="/"><p>개인정보 이용 약관</p></a>
                &nbsp;&nbsp;
                <a href="/"><p>이메일: info@yangcompany.com</p></a>
            </div>
            <div class="copyright">
                <p>&copy; 2023 yangWorld. CopyRight Reserved @ YangCompay.</p>
            </div>
        </div>
    )
}

export default Footer