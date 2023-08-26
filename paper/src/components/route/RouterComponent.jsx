
import IndexHeader from 'components/Headers/IndexHeader'
import MainLayout from 'layouts/MainLayout'
import SignIn from 'pages/SignIn'
import UserPage from 'pages/UserPage'
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
          <Route path='/user/:id' element={<UserPage/>}>

          </Route>
        </Route>
      </Routes>
  )
}

export default RouterComponent