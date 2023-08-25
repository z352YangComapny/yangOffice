import MainLayout from 'Layouts/MainLayout'
import IndexHeader from 'components/Headers/IndexHeader'
import Home from 'components/common/Home'
import SignIn from 'pages/SignIn'
import SignUp from 'pages/SignUp'
import React from 'react'
import { Route, Routes } from 'react-router-dom'
import RegisterPage from 'views/examples/RegisterPage'

const RouterComponent = () => {
  return (
    <Routes>
        <Route path="/" element={<MainLayout/>}>
          <Route path='' element={<IndexHeader/>}/>
          <Route path='signin' element={<SignIn/>}/>
          <Route path='signup' element={<SignUp/>}/>
        </Route>
      </Routes>
  )
}

export default RouterComponent