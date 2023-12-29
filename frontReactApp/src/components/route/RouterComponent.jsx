
import IndexHeader from 'components/Headers/IndexHeader'
import MainLayout from 'Layouts/MainLayout'

import SignIn from 'pages/SignIn'
import FeedPage from 'pages/FeedPage'

import WroldPage from 'pages/World'
import React from 'react'
import { Route, Routes } from 'react-router-dom'
import Index from 'views/Index'
import PageNotFound from 'components/Commons/PageNotFound'
import UserPage from 'pages/UserPage'
import AxiosEx from 'components/Commons/AxiosEx'

const RouterComponent = () => {
  return (
    <Routes>
      <Route path="/" element={<MainLayout />}>
        <Route path='' element={<IndexHeader />} />
        <Route path='signin' element={<SignIn />} />
        <Route path='docs' element={<Index />} />
        <Route path='feed/:hostname' element={<FeedPage />}>

        </Route>
        <Route path='world/:hostname' element={<WroldPage />}>

        </Route>
        <Route path='user/:username' element={<UserPage />}>

        </Route>
        <Route path='axiosex' element={<AxiosEx/>}></Route>
        <Route path='*' element={<PageNotFound />} />
      </Route>
    </Routes>
  )
}

export default RouterComponent