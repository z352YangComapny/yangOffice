import DemoFooter from 'components/Footers/DemoFooter';
import IndexNavbar from 'components/Navbars/IndexNavbar';
import React from 'react'
import { Outlet, useNavigate } from 'react-router-dom';
import '../assets/css/style.css'

const MainLayout = () => {
    const navigate = useNavigate();
    return (
        <>
        <IndexNavbar/>
        <div>
            <Outlet/>
        </div>
        </>
    )
}

export default MainLayout