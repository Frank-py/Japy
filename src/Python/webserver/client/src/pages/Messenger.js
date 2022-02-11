import React, {useState} from 'react'
import Navbar from "./Navbar"

import styles from "./messenger.module.scss"
import {Routes, Route, Navigate} from "react-router-dom";
function Messenger() {
    const [loggedin, setlog] = useState(false)
    fetch("/messenger").then(
    response => response.json()).then(message => {
    message.response === "201" ? setlog(true) : (<Navigate to="/login"/>) 
})
  return (
    <div className={styles.container}>
      <div>
        <Navbar />
        <Routes>
            <Route path="/"/>
        </Routes>
    </div>
    </div>
  )
  }

export default Messenger;