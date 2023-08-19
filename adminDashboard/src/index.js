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
import React from "react";
import ReactDOM from "react-dom/client";
import { BrowserRouter, Route, Routes, Navigate } from "react-router-dom";

import "bootstrap/dist/css/bootstrap.css";
import "assets/scss/paper-dashboard.scss?v=1.3.0";
import "assets/demo/demo.css";
import "perfect-scrollbar/css/perfect-scrollbar.css";

import AdminLayout from "layouts/Admin.js";
import PageNotFound from "views/PageNotFound";
import LoginView from "views/LoginView";
import AppContextProvider from "variables/AppContextProvider";
import PhotofeedContextProvider from "variables/PhotofeedContextProvider";
import MemberContextProvider from "variables/MemberContnextProvider";
import StoryContextProvider from "variables/StoryContextProvider";
import ReportContextProvider from "variables/ReportContextProvider";
import GuestbookContextProvider from "variables/GuestContextProvider";

const root = ReactDOM.createRoot(document.getElementById("root"));

root.render(
  <BrowserRouter>
    <AppContextProvider>
      <PhotofeedContextProvider>
        <MemberContextProvider>
          <StoryContextProvider>
            <ReportContextProvider>
              <GuestbookContextProvider>
                <Routes>
                  <Route path="/login" element={<LoginView />} />
                  <Route path="/admin/*" element={<AdminLayout />} />
                  <Route path="/" element={<Navigate to="/login" replace />} />
                  <Route path="*" element={<PageNotFound />} />
                </Routes>
              </GuestbookContextProvider>
            </ReportContextProvider>
          </StoryContextProvider>
        </MemberContextProvider>
      </PhotofeedContextProvider>
    </AppContextProvider>
  </BrowserRouter>
);
