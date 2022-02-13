import React, {useEffect} from 'react'
import Navbar from "./Navbar"
import "./Navbar.css"
import {Routes, Route} from "react-router-dom";
import Logout from "./Logout"
function Messenger() {
  const tokenString = sessionStorage.getItem('token');
  const userToken = JSON.parse(tokenString);
  if (userToken === null){
    window.location.href = "/login"
  }

  return (
    <div className="mess">
      <div className='ano'>
        <Navbar />
        <Routes>
            <Route path="/" />
        </Routes>
    </div>
    </div>
  )
  }

export default Messenger;