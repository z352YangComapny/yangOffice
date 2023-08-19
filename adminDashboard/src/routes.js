/*!

=========================================================
* Paper Dashboard React - v1.3.2
=========================================================

* Product Page: https://www.creative-tim.com/product/paper-dashboard-react
* Copyright 2023 Creative Tim (https://www.creative-tim.com)

* Licensed under MIT (https://github.com/creativetimofficial/paper-dashboard-react/blob/main/LICENSE.md)

* Coded by Creative Tim

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

*/
import Dashboard from "views/Dashboard.js";
import Notifications from "views/Notifications.js";
import Icons from "views/Icons.js";
import Typography from "views/Typography.js";
import TableList from "views/Tables.js";
import Maps from "views/Map.js";
import UserPage from "views/User.js";
import UpgradeToPro from "views/Upgrade.js";
import Photofeed from "views/Photofeed";
import { layouts } from "chart.js";
import Guestbook from "views/Guestbook";
import Story from "views/Story";

var routes = [
  {
    path: "/dashboard",
    name: "대시보드",
    icon: "nc-icon nc-bank",
    component: <Dashboard />,
    layout: "/admin",
  },
  {
    path: "/notifications",
    name: "신고 관리",
    icon: "nc-icon nc-bell-55",
    component: <Notifications />,
    layout: "/admin",
  },
  {
    path: "/tables",
    name: "전체회원조회",
    icon: "nc-icon nc-tile-56",
    component: <TableList />,
    layout: "/admin",
  },
  {
    path: "/user/:id",
    name: "회원상세조회",
    icon: "nc-icon nc-single-02",
    component: <UserPage />,
    layout: "/admin",
  },
  
  {
    path:"/photofeeds",
    name:"사진피드 관리",
    icon:"nc-icon nc-camera-compact",
    component:<Photofeed/>,
    layout:"/admin",
  },
  {
    path:"/guestbook",
    name:"방명록 관리",
    icon:"nc-icon nc-bullet-list-67",
    component:<Guestbook/>,
    layout:"/admin",
  },
  {
    path:"/story",
    name:"스토리 관리",
    icon:"nc-icon nc-align-left-2",
    component:<Story/>,
    layout:"/admin",
  }
];
export default routes;
