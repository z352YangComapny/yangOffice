
import IndexHeader from 'components/Headers/IndexHeader'
import MainLayout from 'Layouts/MainLayout'

import SignIn from 'pages/SignIn'
import UserPage from 'pages/UserPage'
import WroldPage from 'pages/World'
import React from 'react'
import { Route, Routes } from 'react-router-dom'
import Index from 'views/Index'

const RouterComponent = () => {
  return (
    <Routes>
        <Route path="/" element={<MainLayout/>}>
          <Route path='' element={<IndexHeader/>}/>
          <Route path='signin' element={<SignIn/>}/>
          <Route path='/docs' element={<Index/>}/>
          <Route path='/user/:id' element={<UserPage/>}></Route>
          <Route path='/world/:id' element={<WroldPage/>}></Route>
        </Route>
      </Routes>
  )
}

export default RouterComponent