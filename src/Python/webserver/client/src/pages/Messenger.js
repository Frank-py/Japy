import React, {useState} from 'react'
import Navbar from "./Navbar"
import {Routes, Route, Navigate} from "react-router-dom";
function Messenger() {
    const [loggedin, setlog] = useState(false)
    fetch("/messenger").then(
    response => response.json()).then(message => {
    message.response === "201" ? setlog(true) : (<Navigate to="/login"/>) 
})
  return (
      <div>
        <Navbar />
        <Routes>
            <Route path="/"/>
        </Routes>
    </div>
  )
  }

export default Messenger;