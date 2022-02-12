import React, {useState, useContext} from 'react'
import Navbar from "./Navbar"
import "./Navbar.css"
import {credentials, loggedin, Context} from "./Login"
import {Routes, Route, Navigate} from "react-router-dom";
function Messenger() {
  console.log(credentials)
  const value = useContext(Context);
  console.log(credentials, loggedin, value)
  return (
    <div className="mess">
      <div className='ano'>
        <Navbar />
        <Routes>
            <Route path="/"/>
        </Routes>
    </div>
    </div>
  )
  }

export default Messenger;