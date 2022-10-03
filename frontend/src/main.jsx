import React from 'react'
import ReactDOM from 'react-dom/client'
import Login from './components/Login'
import './index.css'
import Navbar from './components/Navbar'
import { BrowserRouter, Route, Routes} from 'react-router-dom'
import Dashboard from './components/Dashboard'

ReactDOM.createRoot(document.getElementById('root')).render(
  <BrowserRouter>
    <Navbar/>
    <Routes>
      <Route path="/" element={<Login/>}/>
      <Route path="/dashboard" element={<Dashboard/>}/> 
    </Routes>
  </BrowserRouter>
)
